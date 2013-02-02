/*******************************************************************************
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
 ******************************************************************************/
package cern.devtools.depanalysis.bean.impl;

import static org.junit.Assert.assertEquals;

import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

/**
 * @author Vito Baggiolini
 * 
 */
public class SignatureMapperTest {

	private final static String BIN_TYPE_REF = "Ljava/lang/String;";
	private final static char BIN_TYPE_PRIM = 'I';

	private final static String SRC_TYPE_REF = "java.lang.String";
	private final static String SRC_TYPE_PRIM = "int";

	private final static String BIN_ARGS = "Ljava/util/Date;I[J[Ljava/lang/Object;Z";
	private final static String SRC_ARGS = "java.util.Date,int,long[],java.lang.Object[],boolean";

	/**
	 * Test method for {@link cern.cern.devtools.depanalysis.bean.impl.SignatureMapper#toSourceArguments(String)}.
	 */
	@Test
	public void testToSourceArguments() {
		String sourceArguments = SignatureMapper.toSourceArguments(BIN_ARGS);

		assertEquals(SRC_ARGS, sourceArguments);
	}

	/**
	 * Test for method
	 * {@link SignatureMapper#consumeOneReferenceType(List, String)}
	 */
	@Test
	public void testConsumeReferenceType() {
		{ // block to make variables local
			// for normal reference type
			final String argsToConsume = "Ljava/util/Date;ILjava/lang/Object;";
			final String exp_refType = "Ljava/util/Date;";
			String exp_rest = "ILjava/lang/Object;";
			List<String> argsFound = new LinkedList<String>();
			String rest = SignatureMapper.consumeOneReferenceType(argsFound, argsToConsume);
			assertEquals(exp_rest, rest);
			assertEquals(1, argsFound.size());
			assertEquals(exp_refType, argsFound.get(0));
		}

		{ // same for array reference type
			final String argsToConsume = "[Ljava/util/Date;ILjava/lang/Object;";
			final String exp_refType = "[Ljava/util/Date;";
			String exp_rest = "ILjava/lang/Object;";
			List<String> argsFound = new LinkedList<String>();
			String rest = SignatureMapper.consumeOneReferenceType(argsFound, argsToConsume);
			assertEquals(exp_rest, rest);
			assertEquals(1, argsFound.size());
			assertEquals(exp_refType, argsFound.get(0));
		}
	}

	/**
	 * Failure if string doesn't contain a reference type
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testConsumeReferenceType_Failure() {
		List<String> argsFound = new LinkedList<String>();
		SignatureMapper.consumeOneReferenceType(argsFound, "LIC");
	}

	/**
	 * Test method for {@link cern.cern.devtools.depanalysis.bean.impl.SignatureMapper#toSourcePrimitive(char)}.
	 */
	@Test
	public void testToSourcePrimitive() {
		assertEquals(SRC_TYPE_PRIM, SignatureMapper.toSourcePrimitive(BIN_TYPE_PRIM));
	}


	private static String REST = "REST";
	@Test 
	public void testConsumeOneArgument() {
	    final String[] toConsume =  {"Ljava/lang/String;", "[Ljava/lang/String;", "[[Ljava/lang/String;", "[[[Ljava/lang/String;", "I", "[Z", "[[D", "[[[D"  };
	    for(int i=0; i< toConsume.length; i++) {
	        List<String> args = new LinkedList<String>();
	        String rest = SignatureMapper.consumeOneArgument(args, toConsume[i] + REST);
	        assertEquals(REST, rest);
	        assertEquals(1,args.size());
	        assertEquals(toConsume[i], args.get(0));
	    }
	}

	@Test 
	public void testToSourceType() {
	    final String[] binTypes  =  {"Ljava/lang/String;", "[Ljava/lang/String;", "[[Ljava/lang/String;", "[[[Ljava/lang/String;", "I", "[Z", "[[D", "[[[D"  };
	    final String[] expSrcTypes  =  {"java.lang.String", "java.lang.String[]", "java.lang.String[][]", "java.lang.String[][][]", "int", "boolean[]", "double[][]", "double[][][]"  };
	    for(int i=0; i< binTypes.length; i++) {
	        String srcType = SignatureMapper.toSourceType(binTypes[i]);
	        assertEquals(expSrcTypes[i], srcType);
	    }
	}
	/**
	 * Test method for
	 * {@link cern.cern.devtools.depanalysis.bean.impl.SignatureMapper#toSourceReferenceType(java.lang.String)}.
	 */
	@Test
	public void testToSourceReferenceType() {
		assertEquals(SRC_TYPE_REF, SignatureMapper.toSourceReferenceType(BIN_TYPE_REF));
	}

	@Test 
	public void testToSourceSignature() {
        final String[] BIN_SIG = { 
                "cern/spsea/info/Histogram.<init>:(Ljava/lang/String;I[[DDDLjava/util/Date;)V",
                "javax/swing/JTable.<init>:([[Ljava/lang/Object;[Ljava/lang/Object;)V",
                "Jama/Matrix.<init>:([[D)V",
                "cern/jdve/data/DefaultDataSet3D.set:([D[D[[DZZ)V",};
	    final String[] SRC_SIG = {
	            "cern.spsea.info.Histogram#Histogram(java.lang.String,int,double[][],double,double,java.util.Date):void",
	            "javax.swing.JTable#JTable(java.lang.Object[][],java.lang.Object[]):void",
	            "Jama.Matrix#Matrix(double[][]):void",
	            "cern.jdve.data.DefaultDataSet3D#set(double[],double[],double[][],boolean,boolean):void",};
	    for (int i = 0; i < BIN_SIG.length; i++) {
	        String srcSIg = SignatureMapper.toSourceSignature(BIN_SIG[i]);
            assertEquals(SRC_SIG[i], srcSIg);
        }
	}
}
