/***********************************************************************************************************************
 * © Copyright 2013 CERN. This software is distributed under the terms of the GNU General Public Licence version 3 (GPL
 * Version 3), copied verbatim in the file "COPYING". In applying this licence, CERN does not waive the privileges and
 * immunities granted to it by virtue of its status as an Intergovernmental Organization or submit itself to any
 * jurisdiction.
 * 
 * If you modify this Program, or any covered work, by linking or combining it with the Eclipse Integrated Development
 * Environment Plugin libraries (or a modified version of these libraries), containing parts covered by the terms of EPL
 * (Eclipse Public licence), the licensors of this Program grant you additional permission to convey the resulting work.
 * Corresponding Source for a non-source form of such a combination shall include the source code for the parts of
 * Eclipse Integrated Development Environment Plugin libraries used as well as that of the covered work.
 **********************************************************************************************************************/
package cern.devtools.deps.bean.impl;

import java.util.ArrayList;
import java.util.List;
/**
 * Method to convert binary signatures into source signatures,e.g.
 * {@code java/lang/String.subSequence:(II)Ljava/lang/CharSequence; } into
 * {@code java.lang.String.subSequence:(int,int):java.langCharSequence[]}
 * 
 * Main entry point is {@link #toSourceSignature(String)}
 * @author Vito Baggiolini
 */
public class SignatureMapper {
	private static final char SIG_VOID = 'V';
	private static final char SIG_BOOLEAN = 'Z';
	private static final char SIG_BYTE = 'B';
	private static final char SIG_CHAR = 'C';
	private static final char SIG_SHORT = 'S';
	private static final char SIG_INT = 'I';
	private static final char SIG_LONG = 'J';
	private static final char SIG_FLOAT = 'F';
	private static final char SIG_DOUBLE = 'D';

	
	/**
	 * Top level method that converts the binary representation of a whole method invocation
	 * <p>
	 * <ul>
	 * e.g. 
	 * <li>{@code java/lang/Exception.<init>:(Ljava/lang/String;Ljava/lang/Throwable;)V} is translated to 
	 * {@code java.lang.Exception#<init>(java.lang.String,java.lang.Throwable):void}</li>
	 * <li>{@code java/lang/String.subSequence:(II)Ljava/lang/CharSequence; } is translated to 
	 * {@code java.lang.String#subSequence(int,int):java.lang.CharSequence;}</li>
	 * </ul>
	 * @param binaryMethodSignature as indicated
	 * @return the full signature, e.g. 
	 */
    public static String toSourceSignature(String binaryMethodSignature) {
    	if (binaryMethodSignature.equals("cern/example/F$1.this$0:Lcern/example/F;")) {
    		System.out.println(binaryMethodSignature);
    	}
    	
    	if("".equals(binaryMethodSignature))return "";
    	
        try {
            StringBuilder sb = new StringBuilder();
            // treat class and method name "my.pack.TheClass#doSomething":
            final String binTarget = binaryMethodSignature.substring(0, binaryMethodSignature.indexOf(':'));
            String srcTarget = binTarget.replace('.', '#').replace('/', '.');
            // replace "<init>" by the constructor name: 
            if (srcTarget.contains("<init>")) {
                srcTarget = replaceInit(srcTarget);
            }
            sb.append(srcTarget);
            
            // treat the arguments block:
            final int openBracket = binaryMethodSignature.indexOf('(') + 1;
            final int closeBracket = binaryMethodSignature.indexOf(')');
            String binArgs = binaryMethodSignature.substring(openBracket, closeBracket);
            String srcArgs = toSourceArguments(binArgs);
            sb.append('(');
            sb.append(srcArgs);
            sb.append(')');
            String binRetType = binaryMethodSignature.substring(closeBracket + 1);
            String srcRetType = toSourceType(binRetType);
//            if(!isInit) {
            	sb.append(':');
                sb.append(srcRetType);
//            }
            return sb.toString();
        } catch (RuntimeException ex) {
        	System.err.println(">>>unable to translate: " + binaryMethodSignature);
            return "";
        }
    }    
    
	/**
	 * @param srcTarget
	 * @return
	 */
	private static String replaceInit(String srcTarget) {
		int lastDot = srcTarget.lastIndexOf('.');
		int hash = srcTarget.indexOf('#');
		String constr = srcTarget.substring(lastDot+1, hash);
		String INIT_CODE = "<init>";
		return srcTarget.replace(INIT_CODE, constr);
	}

	/**
	 * Converts a group of binary arguments (without brackets) into a group of source arguments
	 * e.g. "java/util/Date;ILjava/lang/Object;" =>
	 * "java.util.Date,int,java.lang.Object[]"
	 * 
	 * @param binArgs
	 * @return 
	 */
	static String toSourceArguments(String binArgs) {
		if (binArgs.isEmpty()) {
			return "";
		}
		List<String> argsFound = new ArrayList<String>();
		String rest = binArgs;
		do {
			rest = consumeOneArgument(argsFound, rest);
		} while (!rest.isEmpty());

		StringBuilder sb = new StringBuilder();
		sb.append(toSourceType(argsFound.get(0)));
		for (int i = 1; i < argsFound.size(); i++) {
			sb.append(',');
			sb.append(toSourceType(argsFound.get(i)));
		}
		return sb.toString();
	}

	/**
	 * Converts a single type from the binary to the corresponding source
	 * representation e.g. {@code [Ljava/beans/PropertyDescriptor;} or {@code C}
	 * 
	 * @param binType
	 * @return the source type, e.g. java.beans.PropertyDescriptor[] or char 
	 */
	public static String toSourceType(String binType) {
		try {
		    int arrayDim = countArrayTokens(binType);
		    if (binType.charAt(arrayDim) == 'L') {
		        if (arrayDim > 0) {
		            return toSourceReferenceType(binType, arrayDim);
		        } else {
		            return toSourceReferenceType(binType);
		        }
		    } else {
                if (arrayDim > 0) {
                    return toSourcePrimitiveArray(binType.charAt(arrayDim), arrayDim);
                } else {
                    return toSourcePrimitive(binType.charAt(0));
                }
		    }
		} catch (IllegalArgumentException ex) {
			//throw new IllegalArgumentException("unable to convert '" + binType + "'", ex);
			return toSourceClass(binType);
		}
	}

    public static String toSourceClass(String binClass) {
    	return binClass.replace('/', '.');
    }
    
    /**
     * Handles {@code package/subpackage/Classname.fieldname:FQBinType} string representation of the fields. 
     * Returns {@code package.subpackage.Classname.fieldname}
     * 
     * @param binField TODO document
     * @return TODO document
     */
    public static String toSourceField(String binField) {
    	if (binField == null || binField.length() == 0) {
    		System.err.println("Cannot convert field to source format: " + binField);
    		return "";
    	}
    	
    	String[] ba = binField.split("\\:");
    	binField = ba[0];
    	return binField.replace("/", ".");
    }
	
	/**
     * Handles {@code [Ljava/beans/PropertyDescriptor;} or {@code [[Ljava/beans/PropertyDescriptor;}}
     * @param binRefType
     * @param arrayDim the array dimension (=number of '[' elements before the reference type
     * @return the source type, e.g {@code java.beans.PropertyDescriptor[]}
     */
    private static String toSourceReferenceType(String binRefType, int arrayDim) {
        StringBuilder sb = new StringBuilder();
        String typeString = binRefType.substring(arrayDim + 1, binRefType.length() - 1);
        typeString = typeString.replace('/', '.');
        sb.append(typeString);

        for(int i=0; i< arrayDim; i++) {
            sb.append("[]");
        }
        return sb.toString();
    }

    /**
	 * Handles {@code Ljava/beans/PropertyDescriptor;}
	 * 
	 * @param binRefType e.g. {@code Ljava/beans/PropertyDescriptor;}
	 * @return e.g. {@code java.beans.PropertyDescriptor}
	 */
	static String toSourceReferenceType(String binRefType) {
		String typeString = binRefType.substring(1, binRefType.length() - 1);
		return typeString.replace('/', '.');
	}

	/**
	 * Modify the binary primitive array to a source array, e.g. "[[D]" to "double[][]"  
	 * @param binPrimitive the primitive character ('D')
	 * @param arrayDim the array dimension (e.g. 2)
	 * @return the source array (e.g. double[][])
	 */
	static String toSourcePrimitiveArray(char binPrimitive, int arrayDim) {
	    StringBuilder sb = new StringBuilder();
	    sb.append(toSourcePrimitive(binPrimitive));
	    for(int i=0; i< arrayDim; i++) {
	        sb.append("[]");
	    }
	    return sb.toString();
	}
	
	/**
	 * Helper method, returns the source type for the specified binary signature
	 * character (e.g. B, I, J)
	 * 
	 * @param binPrimitive the binary signature character
	 * @return the primitive source type
	 */
	static String toSourcePrimitive(char binPrimitive) {
		switch (binPrimitive) {
		case SIG_BYTE:
			return "byte";
		case SIG_CHAR:
			return "char";
		case SIG_DOUBLE:
			return "double";
		case SIG_FLOAT:
			return "float";
		case SIG_INT:
			return "int";
		case SIG_LONG:
			return "long";
		case SIG_SHORT:
			return "short";
		case SIG_VOID:
			return "void";
		case SIG_BOOLEAN:
			return "boolean";
		}
		throw new IllegalArgumentException("unknown primitive signature type '" + binPrimitive + "'");
	}

	/**
	 * Consumes (isolates) one binary argument, adds it to the argsFound list without modifying it and returns the rest
	 * @param argsFound in/out parameter, to which the isolated argument found is added
	 * @param binArgs the whole argument string, with several arguments
	 * @return the non-consumed part of the arguments
	 */
	static String consumeOneArgument(List<String> argsFound, String argsToConsume) {
	    int arrayTokens = countArrayTokens(argsToConsume);
	    if (argsToConsume.charAt(arrayTokens) == 'L') {
            return consumeOneReferenceType(argsFound, argsToConsume);	        
	    } else { 
	        return consumeOnePrimitive(argsFound, argsToConsume, arrayTokens);
	    }
	}

	/** 
	 * Consumes (isolates) one primitive argument and adds it to the argsFound list without modifying it. 
	 * 
	 * @param argsFound in/out parameter, to which the isolated 
	 * @param argsToConsume the string from which to consume one primitive argument
	 * @param arrayDim the array dimension (>0)
	 * @return
	 */
	private static String consumeOnePrimitive(List<String> argsFound, String argsToConsume, int arrayTokens) {
	    argsFound.add(argsToConsume.substring(0, arrayTokens+1));
	    return argsToConsume.substring(arrayTokens+1);
    }

    private static int countArrayTokens(String argsToConsume) {
        int arrayTokens = 0;
	    while(argsToConsume.charAt(arrayTokens)== '[') { arrayTokens++; }
        return arrayTokens;
    }

    /**
	 * Consumes a reference type from the argsToConsume parameter, adds it to
	 * the argsFound and returns the rest
	 * 
	 * @param argsFound a list of arguments to which the consumed argument is
	 *            added
	 * @param argsToConsume the raw string of concatenated binary types to
	 *            consume
	 * @return the rest of the argsToConsume parameter, after the reference type
	 *         has been consumed
	 */
	static String consumeOneReferenceType(List<String> argsFound, String argsToConsume) {
		int indexOfSemicolon = argsToConsume.indexOf(';');
		if (indexOfSemicolon < 0) {
			throw new IllegalArgumentException("No reftype in '" + argsToConsume + "'");
		}
		String refType = argsToConsume.substring(0,indexOfSemicolon+1);
		argsFound.add(refType);
		return argsToConsume.substring(indexOfSemicolon+1);
	}
	
}
