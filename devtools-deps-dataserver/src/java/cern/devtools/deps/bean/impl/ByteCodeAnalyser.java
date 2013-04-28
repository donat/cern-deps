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

import static org.apache.bcel.Constants.CONSTANT_Class;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.apache.bcel.classfile.ClassParser;
import org.apache.bcel.classfile.Constant;
import org.apache.bcel.classfile.ConstantClass;
import org.apache.bcel.classfile.ConstantUtf8;
import org.apache.bcel.classfile.Field;
import org.apache.bcel.classfile.JavaClass;
import org.apache.bcel.generic.Type;

import cern.devtools.depanalysis.repomodel.RField;
import cern.devtools.depanalysis.repomodel.RMethod;
import cern.devtools.depanalysis.repomodel.RModifier;
import cern.devtools.deps.domain.creation.DomainObjectCreator;

/**
 * A class that uses Byte Code analysis to find dependencies Based on BCEL (Byte Code Engineering Library)
 * https://svn.apache.org/repos/asf/jakarta/bcel/trunk/src/examples/listclass.java
 * 
 * @author Vito Baggiolini, Pavel Tarasenko, Donat Csikos modified by: Donat Csikos
 */
public class ByteCodeAnalyser {

	/**
	 * BCEL parser object.
	 */
	private ClassParser classParser;

	/**
	 * .class file stream.
	 */
	private final InputStream classStream;

	/**
	 * True if the analyser should process the constant pool too.
	 */
	private final boolean deepScan;

	/**
	 * BCEL class file representation.
	 */
	private JavaClass javaClass;

	/**
	 * Domain object instantiation.
	 */
	private final DomainObjectCreator creator;

	/**
	 * Creates a new ByteCodeAnalyser, which automatically discovers the structure of the passed .class file and
	 * provides an interface for accessing the internal structure.
	 * 
	 * @param creator
	 *            domain object implementation creator
	 * @param classStream
	 *            .class file in input stream format.
	 * @param deepScan
	 *            performs dependency information extracting which is accessible through
	 *            CodeElement.transientMetadata().
	 * @throws IOException
	 *             if .class file parsing failed.
	 */
	private ByteCodeAnalyser(DomainObjectCreator creator, InputStream classStream, boolean deepScan) throws IOException {
		this.creator = creator;
		this.classStream = classStream;
		this.deepScan = deepScan;
	}

	/**
	 * Starts byte code analysis.
	 * 
	 * @throws IOException
	 *             If the class file is not accessible.
	 */
	public void analyse() throws IOException {
		this.classParser = new ClassParser(classStream, "");
		this.javaClass = classParser.parse();
	}

	/**
	 * Returns the superclass of the analysed class.
	 * 
	 * @return The base class.
	 */
	public String getExtends() {
		return getJavaClass().getSuperclassName();
	}

	/**
	 * List all methods contained in the class file.
	 * 
	 * @return The defined methods in the class file.
	 */
	public List<RField> getFields() {
		List<RField> result = new LinkedList<RField>();
		org.apache.bcel.classfile.Field[] fields = getJavaClass().getFields();
		for (Field f : fields) {
			String name = f.getName();
			String signature = getJavaClass().getClassName() + "." + name;
			String type = f.getType().getSignature();
			List<RModifier> modifiers = new LinkedList<RModifier>();

			if (f.isPrivate()) {
				modifiers.add(RModifier.PRIVATE);
			}
			if (f.isProtected()) {
				modifiers.add(RModifier.PROTECTED);
			}
			if (f.isPublic()) {
				modifiers.add(RModifier.PUBLIC);
			}
			if (f.isStatic()) {
				modifiers.add(RModifier.STATIC);
			}
			if (f.isFinal()) {
				modifiers.add(RModifier.FINAL);
			}
			if (f.isVolatile()) {
				modifiers.add(RModifier.VOLATILE);
			}
			if (f.isTransient()) {
				modifiers.add(RModifier.TRANSIENT);
			}

			RField rf = creator.createField(name, signature, type, modifiers);
			result.add(rf);
		}
		return result;
	}

	/**
	 * Returns the list of implemented interfaces of the analysed class.
	 * 
	 * @return List of the implemented interfaces.
	 */
	public List<String> getImplements() {
		return Arrays.asList(getJavaClass().getInterfaceNames());
	}

	/**
	 * Returns the BCEL library representation if the analysed class.
	 * 
	 * @return BCEL class file representations.
	 */
	public JavaClass getJavaClass() {
		return javaClass;
	}

	/**
	 * List all methods contained in the class file.
	 * 
	 * @return all defined methods in a binary format.
	 */
	public List<RMethod> getMethods() {
		// Get BCEL methods, sets up result container and the analyser class.
		org.apache.bcel.classfile.Method[] bma = getJavaClass().getMethods();
		List<RMethod> result = new LinkedList<RMethod>();
		ByteCodeTraversal bt = new ByteCodeTraversal(javaClass);

		// Find called methods and referenced fields, if deepScan is set to true.
		if (deepScan) {
			try {
				bt.traverse();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		// Create new method instance for every original method.
		for (org.apache.bcel.classfile.Method m : bma) {
			String name = m.getName();
			String signature = getJavaClass().getClassName().replace(".", "/") + "." + m.getName() + ":"
					+ m.getSignature();
			String returnType = m.getReturnType().getSignature();
			List<String> argumentTypes = new LinkedList<>();
			for (Type argType : m.getArgumentTypes()) {
				argumentTypes.add(argType.getSignature());
			}
			List<RModifier> modifiers = new LinkedList<>();

			if (m.isAbstract()) {
				modifiers.add(RModifier.ABSTRACT);
			}
			if (m.isFinal()) {
				modifiers.add(RModifier.FINAL);
			}
			if (m.isNative()) {
				modifiers.add(RModifier.NATIVE);
			}
			if (m.isPrivate()) {
				modifiers.add(RModifier.PRIVATE);
			}
			if (m.isProtected()) {
				modifiers.add(RModifier.PROTECTED);
			}
			if (m.isPublic()) {
				modifiers.add(RModifier.PUBLIC);
			}
			if (m.isStatic()) {
				modifiers.add(RModifier.STATIC);
			}
			if (m.isSynchronized()) {
				modifiers.add(RModifier.SYNCHRONIZED);
			}

			RMethod rm = creator.createMethod(name, signature, returnType, argumentTypes, modifiers);

			// Creates domain model instance.
			// Method m = creator.createMethod(
			// getJavaClass().getClassName().replace(".", "/") + "." + m.getName() + ":" + m.getSignature(),
			// modifiers);

			// Setup called methods and referenced fields.
			if (deepScan && m.getCode() != null) {
				rm.getReferencedMethods().addAll(bt.calledMethods(m));
				rm.getReferencedFields().addAll(bt.referencedFields(m));
			}

			// Finally add it to the result set.
			result.add(rm);
		}
		return result;
	}

	/**
	 * Returns the analysed class' package name.
	 * 
	 * @return Package containing the class.
	 */
	public String getPackageName() {
		return getJavaClass().getPackageName();
	}

	/**
	 * Returns the name of the analysed class without the package declaration.
	 * 
	 * @return Class name without package declaration.
	 */
	public String getSimpleName() {
		if (getJavaClass().getPackageName().length() == 0) {
			return getJavaClass().getClassName();
		} else {
			return getJavaClass().getClassName().substring(getJavaClass().getPackageName().length() + 1);
		}

	}

	/**
	 * Returns all the class names, what the analysed class uses (instantiates, references, etc.).
	 * 
	 * @return All the class names, what the analysed class uses (instantiates, references, etc.).
	 */
	public List<String> getUsedClassNames() {
		// If the execution does not have any interest in the constant pool.
		if (!deepScan) {
			return Collections.emptyList();
		}

		// Go through the constant pool searching for used classes.
		List<String> result = new LinkedList<String>();
		for (int idx = 0; idx < javaClass.getConstantPool().getLength(); idx++) {
			Constant c = javaClass.getConstantPool().getConstant(idx);
			if (c != null) {
				switch (c.getTag()) {
				case CONSTANT_Class:
					ConstantClass cls = (ConstantClass) c;
					ConstantUtf8 c1 = (ConstantUtf8) javaClass.getConstantPool().getConstant(cls.getNameIndex());
					String clsName = c1.getBytes();
					result.add(clsName);
					break;
				}
			}
		}
		return result;
	}

	/**
	 * Returns a set of modifiers, which applies for the analysed class. If the class is an anonymous class, the result
	 * set will contain the {@link Modifiers#ANONYMOUS} element. If the class is an inner private class the result will
	 * contain the {@link Modifiers#PRIVATE} element.
	 * 
	 * @return Set of the class' modifiers.
	 */
	public List<RModifier> getModifiers() {
		List<RModifier> result = new LinkedList<>();
		// anonymous classes have a dollar sign and a number at the end of their names
		if (BeanUtils.endsWithDollarNumber(getJavaClass().getClassName())) {
			result.add(RModifier.ANONYMOUS);
		}
		if (javaClass.isPrivate()) {
			result.add(RModifier.PRIVATE);
		}
		if (javaClass.isProtected()) {
			result.add(RModifier.PROTECTED);
		}
		if (javaClass.isPublic()) {
			result.add(RModifier.PUBLIC);
		}
		if (javaClass.isAbstract()) {
			result.add(RModifier.ABSTRACT);
		}
		if (javaClass.isStatic()) {
			result.add(RModifier.STATIC);
		}
		if (javaClass.isFinal()) {
			result.add(RModifier.FINAL);
		}

		return result;
	}

	/**
	 * Static factory for analysing a class stream. Instantiates a new {@link ByteCodeAnalyser} instance with the
	 * provided arguments, then performs the analization and finally returns the {@link ByteCodeAnalyser} instance.
	 * 
	 * @param creator
	 * @param classStream
	 *            The class stream.
	 * @param deepScan
	 *            true, if the caller needs the externally referenced items.
	 * @return The {@link ByteCodeAnalyser} instance which holds the results.
	 * @throws IOException
	 *             If the class stream is not accessible or not well-formed.
	 */
	public static ByteCodeAnalyser analyseClassStream(DomainObjectCreator creator, InputStream classStream,
			boolean deepScan) throws IOException {
		ByteCodeAnalyser bca = new ByteCodeAnalyser(creator, classStream, deepScan);
		bca.analyse();
		return bca;
	}
}
