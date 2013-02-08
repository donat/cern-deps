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

import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import cern.devtools.deps.bean.ArtifactDescriptor;
import cern.devtools.deps.domain.ApiClass;
import cern.devtools.deps.domain.Field;
import cern.devtools.deps.domain.Method;
import cern.devtools.deps.domain.Product;
import cern.devtools.deps.domain.creation.DomainObjectCreator;

/**
 * Parses all information from a product.
 * 
 * @author Donat Csikos
 */
public class JavaStructureParser {

	/**
	 * Should not process java base classes.
	 */
	public static final String[] IGNORED_PACKAGES = { "java.", "javax." };

	/**
	 * Indicates if the external references in the class files should be traversed.
	 */
	private final boolean deepScan;

	/**
	 * The parsed product.
	 */
	private Product product;

	/**
	 * Cache for all methods contained in the product. Used when searching for internal references.
	 */
	private Set<String> methodCache = null;

	/**
	 * Cache for all field contained in the product. Used when searching for internal references.
	 */
	private Set<String> fieldCache = null;

	/**
	 * Cache for all class names contained in the product. Used when searching for internal references.
	 */
	private Set<String> classNameCache = null;

	/**
	 * Object which instantiate implementation classes for the domain model.
	 */
	private final DomainObjectCreator creator;

	/**
	 * Constructor, which initiates parsing when a new instance is about to be created.
	 * @param creator 
	 * 
	 * @param descriptor Descriptor, which contains the jar location.
	 * @throws IOException if the jar opening fails.
	 */
	private JavaStructureParser(DomainObjectCreator creator, ArtifactDescriptor descriptor, boolean deepScan) throws IOException {
		this.creator = creator;
		if (descriptor == null) {
			throw new NullPointerException("descriptor can't be null");
		}
		this.deepScan = deepScan;
		this.product = creator.createProduct(descriptor.getName(), descriptor.getVersion(),
				descriptor.getContainingFolders(), descriptor.getJarPath());
	}

	/**
	 * Initiates the product parsing.
	 * 
	 * @param creator domain object factory to return the proper implementation types.
	 * @param descriptor The descriptor to parse.
	 * @param deepScan True if the external dependencies should be parsed.
	 * @return A new {@link JavaStructureParser} object, which contains the result.
	 * @throws IOException If the resource referenced by the descriptor is not accessible.
	 */
	public static JavaStructureParser parseDescriptor(DomainObjectCreator creator, ArtifactDescriptor descriptor, boolean deepScan)
			throws IOException {
		// Creates new instance.
		JavaStructureParser parser = new JavaStructureParser(creator, descriptor, deepScan);
		parser.parseInternalStrucure(JarClassStream.fromDescriptor(descriptor));
		return parser;
	}

	/**
	 * Returns the parsed product.
	 * 
	 * @return The parsed product.
	 */
	public Product getProduct() {
		return product;
	}

	/**
	 * Parses product class names, constant pool and methods declaration.
	 * 
	 * @param streams The .class file's input stream
	 * @throws IOException If one of the streams is not well formed or not accesible.
	 */
	private void parseInternalStrucure(Iterable<InputStream> streams) throws IOException {
		for (InputStream is : streams) {
			parseStream(is);
		}

		// Remove constant pools items which points inside the product
		if (deepScan) {
			removeInternalReferences();
		}
	}

	/**
	 * Initiates class parsing and returns a class representation in source format.
	 * 
	 * @param classStream The binary stream of a class file
	 * @return The {@link ApiClass} representation of the binary.
	 * @throws IOException If the stream is not well-formed or not accessible.
	 */
	private ApiClass parseStream(InputStream classStream) throws IOException {
		// get class information
		ByteCodeAnalyser bca = ByteCodeAnalyser.analyseClassStream(creator, classStream, deepScan);
		String className = bca.getPackageName();
		if (className == null || "".equals(className)) {
			className = bca.getSimpleName();
		}
		else {
			className += "." + bca.getSimpleName();
		}

		// get method and field information from the classes
		List<Method> methods = sourceMethods(bca.getMethods());
		List<Field> fields = sourceFields(bca.getFields());

		// get extended and implemented interfaces
		String ext = findExtends(bca);
		String impl = findImplements(bca);

		// Create apiclass instance
		ApiClass ac = creator.createApiClass(className, ext, impl, bca.getModifiers());
		ac.setProduct(product);
		product.getClasses().add(ac);
		ac.getMethods().addAll(methods);
		ac.getFields().addAll(fields);

		// set if the class is private on anonymous and adds used classes to the metadata
		ac.getReferencedClasses().addAll(bca.getUsedClassNames());

		// convert source metadata
		sourceApiClassMetadata(ac);

		// return all data
		return ac;
	}

	/**
	 * Convert all methods to source format.
	 * 
	 * @param list The list of methods.
	 * @return The converted method list.
	 */
	private List<Method> sourceMethods(List<Method> list) {
		Iterator<Method> it = list.iterator();
		while (it.hasNext()) {
			Method m = it.next();
			m.setSignature(SignatureMapper.toSourceSignature(m.getSignature()));
		}
		return list;
	}

	/**
	 * Sets fields to source format.
	 * 
	 * @param list The list of fields to convert.
	 * @return The converted field list.
	 */
	private List<Field> sourceFields(List<Field> list) {
		Iterator<Field> it = list.iterator();
		while (it.hasNext()) {
			Field f = it.next();
			f.setSignature(SignatureMapper.toSourceField(f.getSignature()));
		}
		return list;
	}

	/**
	 * Finds the extended class, if any (and does not come from the jre base libraries)
	 * 
	 * @param bca The {@link ByteCodeAnalyser} instance which holds the class information.
	 * @return The superclass name, or empty string if there no non-internal classes.
	 */
	private String findExtends(ByteCodeAnalyser bca) {
		// Acquire base class name.
		String ext = bca.getExtends();

		// Check if ignored; if the base name starts with any of the ignored package prefix, than ignore it.
		if (isIgnoredPackage(ext)) {
			ext = "";
		}
		return ext;
	}

	/**
	 * Finds the implemented classes if any (and does not come from the jre base libraries) and returns the a
	 * comma-separated list of the implemented classes.
	 * 
	 * @param bca The {@link ByteCodeAnalyser} instance which holds the class information.
	 * @return The String containing all the implemented interfaces separated with a space (' ') character.
	 */
	private String findImplements(ByteCodeAnalyser bca) {
		// Acquire interface names.
		List<String> li = bca.getImplements();
		StringBuilder implementz = new StringBuilder();

		// Check every interfaces.
		for (String i : li) {
			// Find out if the interface should be ignored.
			if (!isIgnoredPackage(i)) {
				// If not ignored, than add to the result
				implementz.append(i + " ");
			}
		}

		// Remove the trailing ' ' and return.
		String impl = implementz.toString().trim();
		return impl;
	}

	/**
	 * Convert list of called methods/referenced classes/fields to source format.
	 * 
	 * @param ac The class which should be converted.
	 */
	private void sourceApiClassMetadata(ApiClass ac) {
		// Don't do the converting, if the references are not extracted..
		if (!deepScan) {
			return;
		}

		// Format the class usage references to source format.
		List<String> usedClasses = (List<String>) ac.getReferencedClasses();
		for (int i = 0; i < usedClasses.size(); ++i) {
			String bin = usedClasses.get(i);
			// As a referenced class, we don't need the array version of a class.
			String src = SignatureMapper.toSourceType(bin).replace("[", "").replace("]", "");
			usedClasses.remove(i);
			usedClasses.add(i, src);
		}

		// Format the method call and the field references to source format.
		for (Method m : ac.getMethods()) {
			List<String> fields = (List<String>) m.getReferencedFields();
			List<String> methods = (List<String>) m.getReferencedMethods();

			// Format the referenced fields.
			if (fields != null) {
				for (int i = 0; i < fields.size(); ++i) {
					String bin = fields.get(i);
					String src = SignatureMapper.toSourceField(bin);
					fields.remove(i);
					fields.add(i, src);
				}
			}

			// Format the called methods.
			if (methods != null) {
				for (int i = 0; i < methods.size(); ++i) {
					String bin = methods.get(i);
					String src = SignatureMapper.toSourceSignature(bin);
					methods.remove(i);
					methods.add(i, src);
				}
			}
		}
	}

	/**
	 * Remove the referenced from the classes, if they are pointing inside the analysed product.
	 */
	private void removeInternalReferences() {
		for (ApiClass ac : product.getClasses()) {

			// Remove unnecessary class references.
			removeIgnoredClassRefs(ac);

			// Remove unnecessary method invocations and field references.
			removeIgnoredMethodsFields(ac);
		}
	}

	/**
	 * Removes all class references which are pointing inside the product.
	 * 
	 * @param ac The instance to cleanup.
	 */
	private void removeIgnoredClassRefs(ApiClass ac) {
		// The class reference.
		List<String> usedClasses = (List<String>) ac.getReferencedClasses();

		// The String set which contains all class names inside the project.
		Iterator<String> iter = usedClasses.iterator();

		// Remove, if the reference points inside.
		while (iter.hasNext()) {
			String uc = iter.next();
			if (isIgnoredClassName(uc)) {
				iter.remove();
			}
		}
	}

	/**
	 * Removes all method invocations from the passed arguments which points inside the analysed product.
	 * 
	 * @param ac The instance to cleanup.
	 */
	private void removeIgnoredMethodsFields(ApiClass ac) {
		// Cleanup the methods, because they hold the references.
		for (Method m : ac.getMethods()) {

			List<String> referencedMethods = (List<String>) m.getReferencedMethods();
			List<String> referencedFields = (List<String>) m.getReferencedFields();

			// Cleanup called methods list.
			if (referencedMethods != null) {
				Iterator<String> iter2 = referencedMethods.iterator();
				while (iter2.hasNext()) {
					String rm = iter2.next();
					if (isIgnoredMethodRef(rm)) {
						iter2.remove();
					}
				}
			}

			// Cleanup referenced fields list.
			if (referencedFields != null) {
				Iterator<String> iter3 = referencedFields.iterator();
				while (iter3.hasNext()) {
					String rf = iter3.next();
					if (isIgnoredFieldRef(rf)) {
						iter3.remove();
					}
				}
			}
		}
	}

	/**
	 * Checks if a class name should be ignored.
	 * 
	 * @param className The name to check.
	 * @return <code>true</code> if the class is defined in the analysed project.
	 */
	private boolean isIgnoredClassName(String className) {
		// If ignored package name, then throw out.
		if (isIgnoredPackage(className)) {
			return true;
		}

		// Else check if it is inside the project.
		if (getClassNameCache().contains(className)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Getter for lazily initialise the cache for the contained class name set.
	 * 
	 * @return The class name set.
	 */
	public Set<String> getClassNameCache() {
		if (classNameCache == null) {
			classNameCache = new HashSet<String>();
			for (ApiClass ac : product.getClasses()) {
				classNameCache.add(ac.getFqName());
			}
		}

		return classNameCache;
	}

	/**
	 * Getter for lazily initialise the cache for the contained field set.
	 * 
	 * @return The set of contained field names.
	 */
	private Set<String> getFieldCache() {
		// lazy init
		if (fieldCache == null) {
			fieldCache = new HashSet<String>();
			for (ApiClass ac : product.getClasses()) {
				for (Field f : ac.getFields()) {
					fieldCache.add(f.getSignature());
				}
			}
		}
		return fieldCache;
	}

	/**
	 * Getter for lazily initialise the cache for contained method name set.
	 * 
	 * @return The set of contained method names.
	 */
	private Set<String> getMethodCache() {
		// lazy init
		if (methodCache == null) {
			methodCache = new HashSet<String>();
			for (ApiClass ac : product.getClasses()) {
				for (Method m : ac.getMethods()) {
					methodCache.add(m.getSignature());
				}
			}
		}
		return methodCache;
	}

	/**
	 * Determines if a method name should be ignored; 1) it is in an ignored package, or 2) it is inside the analysed
	 * project.
	 * 
	 * @param methodName The method to determine if it should be ignored.
	 * @return <code>true</code>, if it should be ignored.
	 */
	private boolean isIgnoredMethodRef(String methodName) {
		// If ignored package name, then throw out.
		if (isIgnoredPackage(methodName)) {
			return true;
		}

		if (getMethodCache().contains(methodName)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Determines if a field should be ignored; 1) it is in an ignored package, or 2) it is inside the analysed project.
	 * 
	 * @param fieldName The field to determine.
	 * @return <code>true</code>, if it should be ignored.
	 */
	private boolean isIgnoredFieldRef(String fieldName) {
		// If ignored package name, then throw out.
		if (isIgnoredPackage(fieldName)) {
			return true;
		}

		if (getFieldCache().contains(fieldName)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Determines if a package should be ignored.
	 * 
	 * @param codeElementName The item name to determine if it should be ignored.
	 * @return <code>true</code>, if the name starts with one of the ignored package's name.
	 */
	private boolean isIgnoredPackage(String codeElementName) {
		for (String s : IGNORED_PACKAGES) {
			if (codeElementName.startsWith(s)) {
				return true;
			}
		}
		return false;
	}
}
