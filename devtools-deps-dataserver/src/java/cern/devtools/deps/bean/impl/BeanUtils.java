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

import java.util.Calendar;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import cern.devtools.deps.domain.Method;

/**
 * Utility class for providing supplementary functionality for the bean implementation.
 * 
 * @author Donat Csikos
 */
public class BeanUtils {

	/**
	 * Private constructor: prevents instantiating this class.
	 */
	private BeanUtils() {
		// Also prevent reflective instantiations.
		throw new RuntimeException("BeanUtils class is not designed for instantiation");
	}

	/**
	 * <p>
	 * Converts the provided list to a string. It calls the {@link #toString()} function and joins the element with the
	 * provided separator. If the following code is executed:<br/>
	 * <code>List l = Arrays.asList("a", "b", "c"); <br/>listToString(l, ',');</code><br/>
	 * than the result is: <code>a,b,c</code>.
	 * </p>
	 * <p>
	 * If one of the arguments is <code>null</code> the method throws {@link RuntimeException}. If the provided list is
	 * empty, than an empty string is returned.
	 * </p>
	 * 
	 * @param list The list of items to join.
	 * @param separator Separator character.
	 * @return The joined string.
	 */
	public static String listToString(List<?> list, char separator) {
		return listToString(list, String.valueOf(separator));
	}

	/**
	 * <p>
	 * Converts the provided list to a string. It calls the {@link #toString()} function and joins the element with the
	 * provided separator. If the following code is executed:<br/>
	 * <code>List l = Arrays.asList("a", "b", "c"); <br/>listToString(l, "---",);</code><br/>
	 * than the result is: <code>a---b---c</code>.
	 * </p>
	 * <p>
	 * If one of the arguments is <code>null</code> the method throws {@link RuntimeException}. If the provided list is
	 * empty, than an empty string is returned.
	 * </p>
	 * 
	 * @param list The list of items to join.
	 * @param separator Separator string.
	 * @return The joined string.
	 */
	public static String listToString(List<?> list, String separator) {
		if (list == null || separator == null) {
			throw new IllegalArgumentException("Argument can't be null");
		}
		StringBuilder builder = new StringBuilder();
		for (Object s : list) {
			builder.append(s);
			builder.append(separator);
		}
		String result = builder.toString();
		if (list.size() > 0) {
			result = result.substring(0, result.length() - separator.length());
		}
		return result;
	}

	/**
	 * Recreates a string list from the argument by splitting it with the provided separator.
	 * 
	 * @param string The string to split into a list.
	 * @param separator The separator.
	 * @return The list representation.
	 */
	public static List<String> stringToList(String string, char separator) {
		if (string == null) {
			throw new IllegalArgumentException("Argument can't be null");
		}

		if ("".equals(string)) {
			return Collections.emptyList();
		}
		List<String> result = new LinkedList<String>();
		for (String s : string.split(Character.toString(separator))) {
			result.add(s);
		}
		return result;
	}

	/**
	 * Returns the elapsed milisecs from 1970 to 2007.
	 * 
	 * @return elapsed milisecs from 1970 to 2007.
	 */
	public static long getMillis2007() {
		Calendar cal = Calendar.getInstance();
		cal.set(2007, Calendar.JANUARY, 1);
		long timeInMillis = cal.getTimeInMillis();
		return timeInMillis;
	}

	/**
	 * Returns <code>true</code>, if the string in the argument has got a $ symbol and a number afterwards, e.g.
	 * "MyClass$1".
	 * 
	 * @param s the string to analyse.
	 * @return <code>true</code> if ends with $ and a number.
	 */
	public static boolean endsWithDollarNumber(String s) {
		String[] names = s.split("\\$");
		if (names.length == 1) {
			return false;
		}

		String last = names[names.length - 1];
		try {
			Integer.parseInt(last);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
	
	/**
	 * Determines if the two methods has the same signature and therefore the one in the subclass overrides the other.
	 * 
	 * @param superClassName The container superclass' name.
	 * @param subClassName The container subclass'name.
	 * @param superClassMethod The method from the superclass.
	 * @param subclassMethod The method from the subclass.
	 * @return <code>true</code>, if the two method overrides each other.
	 */
	public static boolean hasMethodsSameSignature(String superClassName, String subClassName, Method superClassMethod,
			Method subclassMethod) {
		// Disassemble the methods name
		// if the subclass method is com.example.Foo#bar():void, than
		// 1) subsig = {"com.example.Foo", "bar():void"}
		// 2) subsig2 = {"bar", "):void"}
		String[] subsig = subclassMethod.getSignature().split("#");
		String[] supersig = superClassMethod.getSignature().split("#");
		String[] subsig2 = subsig[1].split("\\(");
		String[] supersig2 = supersig[1].split("\\(");

		// First use-case: both method is a constructor (1st and 2nd condition), and thay have the same parameter list
		// (3rd condition).
		if (superClassName.equals(supersig2[0]) && subClassName.equals(subsig2[0]) && supersig2[1].equals(subsig2[1])) {
			return true;
		}

		// Second use-case: both method has the same signature (method name + parameter list).
		if (subsig[1].equals(supersig[1])) {
			return true;
		}

		// By default, return false.
		return false;
	}
	

    public static final boolean IS_WINDOWS = System.getProperty("os.name").startsWith("Win");
    
    public static final boolean IS_LINUX = System.getProperty("os.name").startsWith("Linux");

//    public static String arrayToCommaDelimitedString(String[] arr) {
//        if ((arr == null || arr.length == 0)) {
//            return "";
//        }
//        StringBuffer sb = new StringBuffer();
//        for (int i = 0; i < arr.length; i++) {
//            if (i > 0) {
//                sb.append(",");
//            }
//            sb.append(arr[i]);
//        }
//        return sb.toString();   
//    }
//
//    public static String[] commaDelimitedListToStringArray(String line) {
//        if (str == null) {
//            return new String[0];
//        }
//        if (delimiter == null) {
//            return new String[] { str };
//        }
//        List result = new ArrayList();
//        if ("".equals(delimiter)) {
//            for (int i = 0; i < str.length(); i++) {
//                result.add(str.substring(i, i + 1));
//            }
//        } else {
//            int pos = 0;
//            int delPos = 0;
//            while ((delPos = str.indexOf(delimiter, pos)) != -1) {
//                result.add(str.substring(pos, delPos));
//                pos = delPos + delimiter.length();
//            }
//            if (str.length() > 0 && pos <= str.length()) {
//                // Add rest of String, but not in case of empty input.
//                result.add(str.substring(pos));
//            }
//        }
//        return toStringArray(result);
//    }
    

}
