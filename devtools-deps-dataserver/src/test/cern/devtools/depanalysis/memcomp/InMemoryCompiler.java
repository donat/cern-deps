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
package cern.devtools.depanalysis.memcomp;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.Writer;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import javax.tools.JavaCompiler;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

/**
 * Compiler that compiles a string of source code into byte code, as described in
 * 
 * <pre>
 * http://blog.nobel-joergensen.com/2008/07/16/using-eclipse-compiler-to-create-dynamic-java-objects-2/
 * </pre>
 */
public class InMemoryCompiler {
	private final static String CLASS_NAME = "HelloWorldTest";
	private final static String SOURCE_CODE = // \
	"public class " + CLASS_NAME + "{\n" // \
			+ "    public static void main(String[] args) {\n" // \
			+ "        System.out.println(\"Hello world\");\n" // \
			+ "    }\n" + "}";

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Class<?> compiledClass = compileToClass(SOURCE_CODE, CLASS_NAME);
			if (compiledClass == null) {
				return;
			}
			Method m = compiledClass.getMethod("main", String[].class);
			m.invoke(null, new Object[] { null });
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Compile the passed source code and return the produced byte code as an {@link InputStream}
	 * 
	 * @param sourceCode valid java source code representing a class
	 * @param className the name of the class (must correspond to the name inside the source code)
	 * 
	 * @return the InputStream with byte code corresponding to the compiled source code or {@code null} if compilation
	 *         doesn't work
	 */
	public static ByteArrayInputStream compileToByteCode(String sourceCode, String className) {
		InMemoryClassLoader cl = new InMemoryClassLoader();
		boolean res = compileSourceCode(sourceCode, className, cl);
		if (res) {
			return new ByteArrayInputStream(cl.getByteCode(className));
		}
		return null;
	}

	/**
	 * Compile the passed source code and return the produced class
	 * 
	 * @param sourceCode valid java source code representing a class
	 * @param className the name of the class (must correspond to the name inside the source code)
	 * @return the class corresponding to the compiled source code or {@code null} if compilation doesn't work
	 * @throws ClassNotFoundException
	 */
	public static Class<?> compileToClass(String sourceCode, String className) throws ClassNotFoundException {
		InMemoryClassLoader cl = new InMemoryClassLoader();
		boolean res = compileSourceCode(sourceCode, className, cl);
		if (res) {
			return cl.findClass(className);
		}
		return null;
	}

	public static InMemoryClassLoader compileClasses(String[] sourceCodes, String[] classNames) {
		if (sourceCodes.length != classNames.length) {
			throw new RuntimeException(
					"The number of the class names should be equal with the number of the provided source codes.");
		}
		InMemoryClassLoader cl = new InMemoryClassLoader();
		boolean res = compileSourceCode(sourceCodes, classNames, cl);
		if (res) {
			return cl;
		}
		return null;
	}

	/**
	 * Do the compilation
	 * 
	 * @param sourceCode valid java source code representing a class
	 * @param className the name of the class (must correspond to the name inside the source code)
	 * @param cl the class loader in which the resulting byte code is stored by the compiler
	 * @return true if the compilation was successful
	 */
	private static boolean compileSourceCode(String sourceCode, String className, InMemoryClassLoader cl) {
		JavaCompiler javac = ToolProvider.getSystemJavaCompiler();

		StandardJavaFileManager sjfm = javac.getStandardFileManager(null, null, null);
		InMemoryJavaFileManager fileManager = new InMemoryJavaFileManager(sjfm, cl);

		List<String> options = Collections.<String> emptyList();
		List<MemorySource> compilationUnits = Arrays.asList(new MemorySource(className, sourceCode));
		Writer out = new PrintWriter(System.err);
		JavaCompiler.CompilationTask compile = javac.getTask(out, fileManager, null, options, null, compilationUnits);
		boolean res = compile.call();
		return res;
	}

	private static boolean compileSourceCode(String[] sourceCodes, String[] classNames, InMemoryClassLoader cl) {
		JavaCompiler javac = ToolProvider.getSystemJavaCompiler();

		StandardJavaFileManager sjfm = javac.getStandardFileManager(null, null, null);
		InMemoryJavaFileManager fileManager = new InMemoryJavaFileManager(sjfm, cl);

		List<String> options = Collections.<String> emptyList();
		
		List<MemorySource> compilationUnits = new LinkedList<MemorySource>();
		for (int i = 0; i < sourceCodes.length; ++i) {
			compilationUnits.add(new MemorySource(classNames[i], sourceCodes[i]));
		}
		
		Writer out = new PrintWriter(System.err);
		JavaCompiler.CompilationTask compile = javac.getTask(out, fileManager, null, options, null, compilationUnits);
		boolean res = compile.call();
		return res;
	}
}
