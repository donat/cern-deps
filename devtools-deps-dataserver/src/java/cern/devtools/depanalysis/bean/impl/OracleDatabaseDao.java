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
package cern.devtools.depanalysis.bean.impl;

import java.io.IOException;
import java.net.URI;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cern.devtools.depanalysis.bean.DatabaseDao;
import cern.devtools.depanalysis.bean.DatabaseException;
import cern.devtools.deps.domain.ApiClass;
import cern.devtools.deps.domain.CodeElement;
import cern.devtools.deps.domain.Dependency;
import cern.devtools.deps.domain.DependencyType;
import cern.devtools.deps.domain.Field;
import cern.devtools.deps.domain.Method;
import cern.devtools.deps.domain.Product;
import cern.devtools.deps.domain.TransitiveDependency;
import cern.devtools.deps.domain.creation.DomainFactory;

/**
 * <p>
 * Relation database implementation of the {@link DatabaseDao} interface.
 * </p>
 * <p>
 * This class holds the strategy, how a method should be stored or how should a product be updated. The SQL commands are
 * in the {@link OracleUtils} class.
 * </p>
 * 
 * @author Donat Csikos
 */
public final class OracleDatabaseDao implements DatabaseDao {
	/**
	 * Logger.
	 */
	// private static final Logger LOG = Logger.getLogger(DatabaseDao.class);

	/**
	 * Separator character used in the database.
	 */
	private static final char SEPARATOR = ' ';

	/**
	 * Class holding the SQL operations.
	 */
	private final OracleUtils utils;

	public OracleDatabaseDao(OracleUtils utils) {
		this.utils = utils;
	}

	public Product findProduct(Product product, boolean deep) throws DatabaseException {
		try {
			// Get product by name.
			Product result = null;
			try {
				result = utils.findProductByName(product.getName());
				// result = utils.findProductByPath(product.getStoreLocation());
			} catch (EmptyResultDataAccessException e) {
				return null;
			}

			// if no result is present, then try to find it by name.
			// if (result == null) {
			// result = utils.findProductByName(product.getName());
			// }
			if (deep) {
				// Add apiclasses.
				List<ApiClass> classes = utils.findByForeignKey(ApiClass.class, result.getId());
				for (ApiClass ac : classes) {
					ac.setProduct(result);
					result.getClasses().add(ac);

					// Add all methods to the class.
					List<Method> methods = utils.findByForeignKey(Method.class, ac.getId());
					for (Method m : methods) {
						m.setApiClass(ac);
						ac.getMethods().add(m);
					}

					// Add all fields to the class.
					List<Field> fields = utils.findByForeignKey(Field.class, ac.getId());
					for (Field f : fields) {
						f.setApiClass(ac);
						ac.getFields().add(f);
					}
				}
			}

			return result;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	public void saveProduct(Product newProduct) throws DatabaseException {
		// Check if the product is already stored.
		Product storedProduct = findProduct(newProduct, true);
		// If it a fresh, non-stored product, than save it as a new one.
		if (storedProduct == null) {
			createNewProduct(newProduct);
		}
		// Otherwise update the product with new version's differences.
		else {
			addNewVersionToProduct(newProduct, storedProduct);
		}
	}

	public void deleteProduct(Product product) throws DatabaseException {
		// Simply call the delete function.
		utils.deleteProduct(product);
	}

	public int findAndSaveDependencyRelation(CodeElement source, Collection<? extends CodeElement> targets,
			DependencyType type) throws DatabaseException {
		switch (type) {
		case CLASS_INHERITANCE:
			return findInheritanceDeps(source, targets);
		case CLASS_USAGE:
			return findClassreferenceDeps(source, targets);
		case FIELD_REFERENCE:
			return findFieldreferenceDeps(source, targets);
		case METHOD_CALL:
			return findMethodCallDeps(source, targets);
		case METHOD_OVERRIDE:
			return findOverridedFunc(source);
		default:
			throw new RuntimeException("Not implemented yet");
		}
	}

	public Collection<Dependency> findClassDependencies(ApiClass element) {
		Collection<Dependency> result = new LinkedList<Dependency>();
		result.addAll(findIncomingInheritances(element));
		result.addAll(findIncomingClassUsages(element));
		setupContainerReferences(result);
		return result;
	}

	public Collection<Dependency> findMethodDependencies(Method element) {
		Collection<Dependency> result = new LinkedList<Dependency>();
		result.addAll(findIncomingMethodCalls(element));
		result.addAll(findIncomingOverrides(element));
		setupContainerReferences(result);
		return result;
	}

	public Collection<Dependency> findFieldDependencies(Field element) {
		Collection<Dependency> result = findIncomingFieldRefs(element);
		setupContainerReferences(result);
		return result;
	}

	public Collection<Dependency> findProductDependencies(Product element) throws DatabaseException {
		Collection<Dependency> result = new LinkedList<Dependency>();
		for (Product p : utils.findIncomingProducts(element)) {
			result.add(DomainFactory.creator().createDependency(DependencyType.PRODUCT_DEPENDENCY, p, null));
		}
		return result;
	}

	public void updateProduct(Product product) {
		utils.update(product);
	}

	private void createNewProduct(Product product) {
		final String version = BeanUtils.listToString(product.getVersions(), SEPARATOR);

		// insert the product with it's basic properties
		long productId = utils.persistProduct(product);

		// iterate over referenced classes
		for (ApiClass ac : product.getClasses()) {
			// save classes
			final Number apiClassId = storeNewApiClass(version, productId, ac);

			// save methods
			final Collection<Method> methods = ac.getMethods();
			storeNewMethods(version, apiClassId, methods);

			// save fields
			final Collection<Field> fields = ac.getFields();
			storeNewFields(version, apiClassId, fields);
		}
	}

	private void addNewVersionToProduct(Product newProduct, Product storedProduct) {
		// Add new version to the stored product.
		String version = newProduct.getVersions().get(0);
		if (storedProduct.getVersions().contains(version)) {
			// If we already stored the existing version, then don't store it again.
			return;
		} else {
			storedProduct.getVersions().add(version);
			updateProduct(storedProduct);
		}

		// Sweep across the apiclass of the apiclasses
		for (ApiClass nac : newProduct.getClasses()) {

			// Look for the selected class if it exists in the stored version. If it is a new class, than the only
			// thing to do is simply store it with all the references to the database. But if it already exists, this
			// it has to be merged with the stored items.
			ApiClass storedClass = null;
			for (ApiClass oac : storedProduct.getClasses()) {
				if (oac.getFqName().equals(nac.getFqName())) {
					storedClass = oac;
					break;
				}
			}

			// if the new apiclass does not exist, store the entire structure.
			if (storedClass == null) {
				Number classId = storeNewApiClass(version, storedProduct.getId(), nac);
				storeNewMethods(version, classId, nac.getMethods());
				storeNewFields(version, classId, nac.getFields());
				// Or else compare with the stored state.
			}
			// If the class is stored, than merge it.
			else {
				// update the version list of the class
				Number classId = storedClass.getId();
				addNewVersionToCodeElement(storedClass, version);

				// Merge the new methods with the existing ones.
				for (Method newMethod : nac.getMethods()) {
					Method storedMethod = null;
					// Search if the new method exists in the database.
					for (Method sm : storedClass.getMethods()) {

						if (sm.getSignature().equals(newMethod.getSignature())) {
							storedMethod = sm;
							break;
						}
					}

					// If we have not found a new method, it means, that the method became the member of the class
					// with new version, so it should be stored alone.
					if (storedMethod == null) {
						storeNewMethods(version, classId, Arrays.asList(newMethod));
					}

					// Or else the the version has been in the database already, which means, only the version
					// information has to be updated.
					else {
						addNewVersionToCodeElement(storedMethod, version);
					}
				}

				// The same with the fields. Look for existing field and it it is stored than update the versions
				// parameter, otherwise, insert a new field.
				for (Field f : nac.getFields()) {
					Field storedField = null;
					for (Field sf : storedClass.getFields()) {
						if (sf.getSignature().equals(f.getSignature())) {
							storedField = sf;
							break;
						}
					}

					if (storedField == null) {
						storeNewFields(version, classId, Arrays.asList(f));
					} else {
						addNewVersionToCodeElement(storedField, version);
					}
				}
			}
		}
	}

	/**
	 * Stores new apiclass in the database.
	 * 
	 * @param version The one and only version number which is assigned to the class.
	 * @param productId The id of the container product.
	 * @param ac The class to store.
	 * @return The id of the stored class.
	 */
	private Number storeNewApiClass(final String version, Number productId, ApiClass ac) {
		ac.getVersions().add(version);
		if (ac.getProduct() == null) {
			ac.setProduct(DomainFactory.creator().createProduct(""));
		}
		ac.getProduct().setId(productId.longValue());

		return utils.persistApiclass(ac);
	}

	/**
	 * Inserts a list of methods into the database.
	 * 
	 * @param version Default version for the methods.
	 * @param apiClassId Container's id.
	 * @param methods The instances to store.
	 */
	private void storeNewMethods(final String version, final Number apiClassId, final Collection<Method> methods) {
		utils.batchInsertMethods(version, apiClassId.longValue(), methods);
	}

	/**
	 * Inserts a list of fields into the database.
	 * 
	 * @param version Default version for the fields.
	 * @param apiClassId Container's id.
	 * @param fields The instances to store.
	 */
	private void storeNewFields(final String version, final Number apiClassId, final Collection<Field> fields) {
		utils.batchInsertFields(version, apiClassId.longValue(), fields);
	}

	/**
	 * Adds new version to the passed element and synchronises with the databases.
	 * 
	 * @param element the element to add the new version number.
	 * @param version The version to be added to the element.
	 */
	private void addNewVersionToCodeElement(CodeElement element, String version) {
		if (!element.getVersions().contains(version)) {
			element.getVersions().add(version);
			utils.update(element);
		}
	}

	/**
	 * Finds and stores inheritance dependencies.
	 * 
	 * @param source The item which is the source of the dependency relation. Should have a database id.
	 * @param targets The candidates as a target of the dependency relation. The function queries for these items and
	 *        creates the dependency in the database between the source and the result items.
	 * @return The number of the found dependencies.
	 */
	private int findInheritanceDeps(CodeElement source, Collection<? extends CodeElement> targets) {
		// Gathers all interfaces and superclass names.
		List<String> sigs = getSignatures(targets);
		if (sigs.size() == 0) {
			return 0;
		}

		// Look for the signatures in the database.
		List<ApiClass> found = utils.findBySignature(ApiClass.class, sigs);

		// Stores that the source is dependent on all of the found items as it inherits from them.
		utils.persistDependencies(source, found, DependencyType.CLASS_INHERITANCE);
		return found.size();
	}

	/**
	 * Finds and stores class reference dependencies.
	 * 
	 * @param source The item which is the source of the dependency relation. Should have a database id.
	 * @param targets The candidates as a target of the dependency relation. The function queries for these items and
	 *        creates the dependency in the database between the source and the result items.
	 * @return The number of found dependencies.
	 */
	private int findClassreferenceDeps(CodeElement source, Collection<? extends CodeElement> targets) {
		// Gathers the signatures of the target classes.
		List<String> sigs = getSignatures(targets);
		if (sigs.size() == 0) {
			return 0;
		}

		// Look for the signatures in the database.
		List<ApiClass> found = utils.findBySignature(ApiClass.class, sigs);

		// Stores that the source is dependent on all of the found items as it has a reference on the items.
		utils.persistDependencies(source, found, DependencyType.CLASS_USAGE);
		return found.size();
	}

	/**
	 * Finds and stores field reference dependencies.
	 * 
	 * @param source The item which is the source of the dependency relation. Should have a database id.
	 * @param targets The candidates as a target of the dependency relation. The function queries for these items and
	 *        creates the dependency in the database between the source and the result items.
	 * @return The number of found dependencies.
	 */
	private int findFieldreferenceDeps(CodeElement source, Collection<? extends CodeElement> targets) {
		// Gather the signature of the target fields.
		List<String> sigs = getSignatures(targets);
		if (sigs.size() == 0) {
			return 0;
		}

		// Look for the signatures in the database.
		List<Field> found = utils.findBySignature(Field.class, sigs);

		// Stores that the source is dependent on all of the found items as it references them.
		utils.persistDependencies(source, found, DependencyType.FIELD_REFERENCE);
		return found.size();
	}

	/**
	 * Finds and stores field reference dependencies.
	 * 
	 * @param source The item which is the source of the dependency relation. Should have a database id.
	 * @param targets The candidates as a target of the dependency relation. The function queries for these items and
	 *        creates the dependency in the database between the source and the result items.
	 * @return The number of found dependencies.
	 */
	private int findMethodCallDeps(CodeElement source, Collection<? extends CodeElement> targets) {
		// Gather called methods' signature.
		List<String> sigs = getSignatures(targets);
		if (sigs.size() == 0) {
			return 0;
		}

		// Look for the signatures in the database.
		List<Method> found = utils.findBySignature(Method.class, sigs);

		// Stores that the source is dependent on all of the found items are called by the source function.
		utils.persistDependencies(source, found, DependencyType.METHOD_CALL);
		return found.size();
	}

	/**
	 * Finds the overridden methods for the specified class.
	 * 
	 * @param source class instance to search for overridden classes.
	 * @return the number of found overridden methods.
	 */
	private int findOverridedFunc(CodeElement source) {
		// Parameter check: we wait for stored apiclass instances.
		if (source == null || !(source instanceof ApiClass) || source.getId() == 0) {
			throw new IllegalArgumentException("Parameter should be a persisted ApiClass instance.");
		}

		// Find super apiclass.
		ApiClass subClass = (ApiClass) source;
		String superClassName = subClass.getExtends();

		// Exclude object which has only the java.lang.Object as a superclass.
		if (superClassName == null) {
			return 0;
		}

		List<ApiClass> superClassList = utils.findBySignature(ApiClass.class, Arrays.asList(superClassName));

		// If there is no class stored with this name.
		if (superClassList.size() == 0) {
			return 0;
		}

		// Find methods of the superclass candidates.
		Collection<Method> subClassMethods = subClass.getMethods();
		int resultSize = 0;
		for (ApiClass superClass : superClassList) {

			// We want to store only dependencies between different product, so overrides are dismissed inside a
			// product.
			if (superClass.getProduct().getId() == subClass.getProduct().getId()) {
				continue;
			}

			// Get all methods of the superclass from the database and iterate over it.
			List<Method> superClassMethods = utils.findByForeignKey(Method.class, superClass.getId());
			for (Method superClassMethod : superClassMethods) {

				// Method override is not defined in java, so if we have two same name in inherited classes, we ignore
				// them.
				if (superClassMethod.isStatic()) {
					continue;
				}

				for (Method subClassMethod : subClassMethods) {

					// Also ignore static methods in the subclass.
					if (subClassMethod.isStatic()) {
						continue;
					}

					// Determine if the two method has a same signature
					// SDT-600: should be supported by the Method interface.
					boolean isOverride = BeanUtils.hasMethodsSameSignature(superClass.getSimpleName(),
							subClass.getSimpleName(), superClassMethod, subClassMethod);

					// If we found an actual method override, store it in the database.
					if (isOverride) {
						utils.persistDependencies(subClassMethod, Arrays.asList(superClassMethod),
								DependencyType.METHOD_OVERRIDE);
					}
				}
			}
		}

		// Return the overall number of found overridden methods.
		return resultSize;
	}

	/**
	 * Get codeElement signatures.
	 * 
	 * @param elements The list of items which we need the signature.
	 * @return The signature list.
	 */
	private List<String> getSignatures(Collection<? extends CodeElement> elements) {

		// If zero elements passed, than return empty list.
		if (elements.size() == 0) {
			return Collections.emptyList();
		}

		// Result container.
		List<String> sigs = new LinkedList<String>();

		// If the argument is an ApiClass instance, then extract the fully qualified name.
		if (elements.iterator().next() instanceof ApiClass) {
			for (CodeElement it : elements) {
				ApiClass ac = (ApiClass) it;
				if (!"".equals(ac.getFqName())) {
					sigs.add(ac.getFqName());
				}
			}
		}
		// Else extract the signature of the item.
		else if (elements.iterator().next() instanceof Field) {
			for (CodeElement it : elements) {
				Field f = (Field) it;
				if (!"".equals(f.getSignature())) {
					sigs.add(f.getSignature());
				}
			}
		} else if (elements.iterator().next() instanceof Method) {
			for (CodeElement it : elements) {
				Method f = (Method) it;
				if (!"".equals(f.getSignature())) {
					sigs.add(f.getSignature());
				}
			}
		}

		// Return the signatures.
		return sigs;
	}

	int isrec = 0;

	/**
	 * Returns all the items, which inherits from the class outside of the product. This method may return transitive
	 * dependencies ({@link TransitiveDependency}) as the incoming dependencies also have incoming equivalents.
	 * 
	 * @param element The method returns incoming dependencies for this instance.
	 * @return The list of dependencies which points to this instance.
	 */
	private Collection<Dependency> findIncomingInheritances(ApiClass element) {
		Set<ApiClass> resultSet = new HashSet<ApiClass>();
		return findIncomingInheritances(element, resultSet, 0, 2);
	}

	private Collection<Dependency> findIncomingInheritances(ApiClass element, Set<ApiClass> resultSet, int level,
			int maxLevel) {
		if (level > maxLevel) {
			return Collections.emptyList();
		}

		// Retrieves the stored equivalent of the element from the database.
		List<ApiClass> toList = utils.findBySignature(ApiClass.class, Arrays.asList(element.getFqName()));

		// Query the proper dependency tables for the incoming pairs.
		Set<ApiClass> fromList = utils.findIncomingPairs(ApiClass.class, toList, DependencyType.CLASS_INHERITANCE);

		// Initialise result holder.
		List<Dependency> result = new LinkedList<Dependency>();
		for (ApiClass from : fromList) {

			if (resultSet.contains(from)) {
				continue;
			}

			// Recursive call: look for transitive incoming inheritance dependencies.
			Collection<Dependency> transDeps = Collections.emptyList();
			transDeps = findIncomingInheritances(from, resultSet, level + 1, maxLevel);

			// Based on whether or not a transitive dependencies are present instantiate the dependency and add to the
			// result list.
			if (transDeps.size() > 0) {
				result.add(DomainFactory.creator().createDependency(DependencyType.CLASS_INHERITANCE, from,
						toList.get(0), transDeps));
			} else {
				result.add(DomainFactory.creator().createDependency(DependencyType.CLASS_INHERITANCE, from,
						toList.get(0)));
			}

			// Refresh the result set accordingly.
			resultSet.add(from);
		}

		return result;
	}

	/**
	 * Finds incoming class usages dependencies for the specified class.
	 * 
	 * @param element The method returns incoming dependencies for this instance.
	 * @return The list of dependencies which points to this instance.
	 */
	private Collection<Dependency> findIncomingClassUsages(ApiClass element) {
		// Retrieves the stored equivalent of the element from the database.
		List<ApiClass> to = utils.findBySignature(ApiClass.class, Arrays.asList(element.getFqName()));

		// Query the proper dependency tables for the incoming pairs.
		Set<ApiClass> from = utils.findIncomingPairs(ApiClass.class, to, DependencyType.CLASS_USAGE);

		// Initialise result holder.
		Collection<Dependency> result = new LinkedList<Dependency>();

		// Create dependency list from the result.
		for (ApiClass ac : from) {
			result.add(DomainFactory.creator().createDependency(DependencyType.CLASS_USAGE, ac, null));
		}

		return result;
	}

	/**
	 * Finds incoming method overrides for the specified method. This method may return transitive dependencies (
	 * {@link TransitiveDependency}) as the incoming dependencies also have incoming equivalents.
	 * 
	 * @param element The method returns incoming dependencies for this instance.
	 * @return The list of dependencies which points to this instance.
	 */
	private Collection<Dependency> findIncomingOverrides(Method element) {
		// Retrieves the stored equivalent of the parameter from the database.
		List<Method> to = utils.findBySignature(Method.class, Arrays.asList(element.getSignature()));

		// Query the proper dependency tables for the incoming pairs.
		Set<Method> from = utils.findIncomingPairs(Method.class, to, DependencyType.METHOD_OVERRIDE);

		// Initialise result holder.
		Collection<Dependency> result = new LinkedList<Dependency>();
		for (Method m : from) {
			// Recursive call: look for transitive incoming override dependencies.
			Collection<Dependency> transDeps = findIncomingOverrides(m);

			// Based on whether or not a transitive dependencies are present instantiate the dependency and add to the
			// result list.
			if (transDeps.size() > 0) {
				result.add(DomainFactory.creator().createDependency(DependencyType.METHOD_OVERRIDE, m, null, transDeps));
			} else {
				result.add(DomainFactory.creator().createDependency(DependencyType.METHOD_OVERRIDE, m, null));
			}
		}

		return result;
	}

	/**
	 * Find incoming method call dependencies for the specified method.
	 * 
	 * @param element The method returns incoming dependencies for this instance.
	 * @return The list of dependencies which points to this instance.
	 */
	private Collection<Dependency> findIncomingMethodCalls(Method element) {
		// Retrieves the stored equivalent of the parameter from the database.
		List<Method> to = utils.findBySignature(Method.class, Arrays.asList(element.getSignature()));

		// Query the proper dependency tables for the incoming pairs.
		Set<Method> from = utils.findIncomingPairs(Method.class, to, DependencyType.METHOD_CALL);

		// Initialise result holder.
		Collection<Dependency> result = new LinkedList<Dependency>();

		// Create dependency list from the result.
		for (Method m : from) {
			result.add(DomainFactory.creator().createDependency(DependencyType.METHOD_CALL, m, null));
		}

		return result;
	}

	/**
	 * Find incoming field reference dependencies for the specified fields.
	 * 
	 * @param element The method returns incoming dependencies for this instance.
	 * @return The list of dependencies which points to this instance.
	 */
	private Collection<Dependency> findIncomingFieldRefs(Field element) {
		// Retrieves the stored equivalent of the parameter from the database.
		List<Field> to = utils.findBySignature(Field.class, Arrays.asList(element.getSignature()));

		// Query the proper dependency tables for the incoming pairs.
		Set<Method> from = utils.findIncomingPairs(Method.class, to, DependencyType.FIELD_REFERENCE);

		// Initialise result holder.
		Collection<Dependency> result = new LinkedList<Dependency>();

		// Create dependency list from the result.
		for (Method m : from) {
			result.add(DomainFactory.creator().createDependency(DependencyType.FIELD_REFERENCE, m, null));
		}

		return result;
	}

	/**
	 * Sets up the containment references in the result list. This is required because the database operations are are
	 * operation on single instances and we would like to retrieve a consistent Product/apiclass/method/field graph
	 * instead of having multiple instances from the same CodeElement. For example, if we have two ApiClass instance in
	 * our result with the product as a container, we want them to be the same Product instance. This is why we have to
	 * sanitise the result.
	 * 
	 * @param results the dependencies holding the instances to cleanup.
	 */
	private void setupContainerReferences(Collection<Dependency> results) {
		// Product ids in the result.
		Set<Long> pIds = new HashSet<Long>();

		// Class ids in the result.
		Set<Long> acIds = new HashSet<Long>();

		Map<Long, Product> productsMap = new HashMap<Long, Product>();
		Map<Long, ApiClass> classesMap = new HashMap<Long, ApiClass>();
		List<ApiClass> classes = new LinkedList<ApiClass>();
		List<Method> methods = new LinkedList<Method>();

		// Gather all the ids of the codeelements in the previously defined collections.
		collectMaps(results, pIds, acIds, classes, methods);

		// First look for the result's class instances. Find them, then store it in a map
		// And put their ProductIds to the id set, to find them later.
		for (Long acId : acIds) {
			ApiClass ac = utils.find(ApiClass.class, acId);
			classesMap.put(acId, ac);
			classes.add(ac);
			pIds.add(ac.getProduct().getId());
		}

		// Now, find all the products.
		for (Long pId : pIds) {
			Product found = utils.find(Product.class, pId);
			productsMap.put(pId, found);
		}

		// If we have all the class and product reference, setup the classes container.
		for (ApiClass ac : classes) {
			Product p = productsMap.get(ac.getProduct().getId());
			ac.setProduct(p);
			p.getClasses().add(ac);
			classesMap.put(ac.getId(), ac);
		}

		// Finally, we can re-assign the methods container classes. With this the cleanup is done.
		for (Method m : methods) {
			ApiClass container = classesMap.get(m.getApiClass().getId());
			m.setApiClass(container);
			container.getMethods().add(m);
		}
	}

	private void collectMaps(Collection<Dependency> results, Set<Long> pIds, Set<Long> acIds, List<ApiClass> classes,
			List<Method> methods) {
		// Gathers the items from all dependency
		for (Dependency d : results) {
			CodeElement from = d.getFrom();
			if (from instanceof ApiClass) {
				ApiClass ac = (ApiClass) from;
				// Stores the apiclass and the container product id to later 1) retrieve the proper product 2) set the
				// product instance as the container of the apiclass
				classes.add(ac);
				pIds.add(ac.getProduct().getId());
			} else if (from instanceof Method) {
				Method m = (Method) from;
				// Like above, store the method and it's container class.
				methods.add(m);
				acIds.add(m.getApiClass().getId());
			} else {
				// The result set only contains classes and methods so we don't have to check for other types.
				throw new RuntimeException(
						"The dependency should contain only ApiClass or Method references, but found: "
								+ from.getClass() + ".");
			}

			// recursively gather the ids
			if (d instanceof TransitiveDependency) {
				collectMaps(((TransitiveDependency) d).getTransitiveFrom(), pIds, acIds, classes, methods);
			}
		}
	}

	public long getNumOfDeps() {
		return utils.getDepsNumber();
	}

	public void flush(String string) throws IOException {
		// Do nothing
		
	}

	public void reset() {
		utils.deleteAll();
	}

	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public URI findCp3ModelForDirectDeps(List<String> projects) {
		// TODO Auto-generated method stub
		return null;
	}
}
