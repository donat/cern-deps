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
import java.net.URI;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.conversion.EndResult;
import org.springframework.data.neo4j.support.Neo4jTemplate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cern.devtools.deps.bean.DatabaseDao;
import cern.devtools.deps.bean.DatabaseException;
import cern.devtools.deps.domain.ApiClass;
import cern.devtools.deps.domain.CodeElement;
import cern.devtools.deps.domain.Dependency;
import cern.devtools.deps.domain.DependencyType;
import cern.devtools.deps.domain.Field;
import cern.devtools.deps.domain.Method;
import cern.devtools.deps.domain.Product;
import cern.devtools.deps.domain.neo4j.ClassNode;
import cern.devtools.deps.domain.neo4j.DependencyRelation;
import cern.devtools.deps.domain.neo4j.FieldNode;
import cern.devtools.deps.domain.neo4j.MethodNode;
import cern.devtools.deps.domain.neo4j.ProductNode;
import cern.devtools.deps.domain.neo4j.RemovedNode;
import cern.devtools.deps.domain.neo4j.TransitiveDependencyRelation;
import cern.devtools.deps.springdata.repos.ApiClassRepository;
import cern.devtools.deps.springdata.repos.FieldRepository;
import cern.devtools.deps.springdata.repos.MethodRepository;
import cern.devtools.deps.springdata.repos.ProductRepsitory;

public class SpringDataNeo4jDatabaseDao implements DatabaseDao {

	@Autowired
	Neo4jTemplate neo4jTemplate;

	@Autowired
	ProductRepsitory productRepsitory;

	@Autowired
	ApiClassRepository apiClassRepository;

	@Autowired
	MethodRepository methodRepository;

	@Autowired
	FieldRepository fieldRepository;

	public Product findProduct(Product product, boolean prefetch) throws DatabaseException {
		// Find the product.
		ProductNode result = productRepsitory.findByPropertyValue("name", product.getName());

		Set<ApiClass> fetchedClasses = new HashSet<ApiClass>();
		if (result != null && prefetch == true) {
			for (ApiClass ac : result.getClasses()) {

				ClassNode cn = apiClassRepository.findOne(ac.getId());
				cn.setProduct(product);
				fetchedClasses.add(cn);

				Set<Method> fetchedMethods = new HashSet<Method>();
				Set<Field> fetchedFields = new HashSet<Field>();

				for (Method m : cn.getMethods()) {
					Method nm = methodRepository.findOne(m.getId());
					nm.setApiClass(cn);
					fetchedMethods.add(nm);
				}

				cn.getMethods().clear();
				cn.getMethods().addAll(fetchedMethods);

				for (Field f : cn.getFields()) {
					Field nf = fieldRepository.findOne(f.getId());
					nf.setApiClass(cn);
					fetchedFields.add(nf);
				}

				cn.getFields().clear();
				cn.getFields().addAll(fetchedFields);
			}

			result.getClasses().clear();
			result.getClasses().addAll(fetchedClasses);
		}
		return result;
	}

	public void saveProduct(Product product) throws DatabaseException {
		if (!(product instanceof ProductNode)) {
			throw new RuntimeException("Object should be graph instance but it is  a " + product.getClass()
					+ " instead");
		}

		// update all elements to have the same version.
		for (ApiClass ac : product.getClasses()) {
			ac.addVersions(product.getVersions().toArray(new String[0]));
			for (Method m : ac.getMethods()) {
				m.addVersions(product.getVersions().toArray(new String[0]));
			}
			for (Field f : ac.getFields()) {
				f.addVersions(product.getVersions().toArray(new String[0]));
			}
		}

		// Store product.
		addProduct(product);

	}

	public void deleteProduct(Product product) throws DatabaseException {
		if (!(product instanceof ProductNode)) {
			throw new RuntimeException("Object should be graph instance but it is  a " + product.getClass()
					+ " instead");
		}
		product = productRepsitory.findByPropertyValue("name", product.getName());
		if (product != null) {
			productRepsitory.delete((ProductNode) product);
		}

	}

	public void updateProduct(Product product) throws DatabaseException {
		if (!(product instanceof ProductNode)) {
			throw new RuntimeException("Object should be graph instance but it is  a " + product.getClass()
					+ " instead");
		}
		addProduct(product);
	}

	public int findAndSaveDependencyRelation(CodeElement source, Collection<? extends CodeElement> targets,
			DependencyType type) throws DatabaseException {
		switch (type) {
		case CLASS_USAGE:
			return findClassreferenceDeps(source, targets);
		case CLASS_INHERITANCE:
			return findInheritanceDeps(source, targets);
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

	
	public Collection<Dependency> findClassDependencies(ApiClass ac) throws DatabaseException {
		Collection<Dependency> result = new LinkedList<Dependency>();

		EndResult<ClassNode> results = apiClassRepository.findAllByPropertyValue("fqName", ac.getFqName());
		for (ClassNode cn : results) {
			result.addAll(findIncomingClassUsages(cn));
			result.addAll(findIncomingInheritances(cn));
		}

		return result;
	}

	
	public Collection<Dependency> findMethodDependencies(Method m) throws DatabaseException {
		Collection<Dependency> result = new LinkedList<Dependency>();

		EndResult<MethodNode> results = methodRepository.findAllByPropertyValue("signature", m.getSignature());
		for (MethodNode mn : results) {
			result.addAll(findIncomingMethodCalls(mn));
			result.addAll(findIncomingOverrides(mn));
		}

		return result;
	}

	
	public Collection<Dependency> findFieldDependencies(Field f) throws DatabaseException {
		Collection<Dependency> result = new LinkedList<Dependency>();

		EndResult<FieldNode> results = fieldRepository.findAllByPropertyValue("signature", f.getSignature());
		for (FieldNode fn : results) {
			result.addAll(findIncomingFieldRefs(fn));
		}
		return result;
	}

	
	public Collection<Dependency> findProductDependencies(Product element) throws DatabaseException {
		// TODO Auto-generated method stub
		return null;
	}

	/* ------------------------------------------------------------------------------------------------------------- */

	private void addProduct(Product product) throws DatabaseException {
		Product existingProduct = findProduct(product, true);

		if (existingProduct == null) {
			productRepsitory.save((ProductNode) product);
		} else {
			replaceExistingProduct(existingProduct, product);
		}
	}

	private void replaceExistingProduct(Product oldProduct, Product newProduct) throws DatabaseException {
		// String oldProductName = oldProduct.getName();
		// String oldProductVersion = oldProduct.getVersions().get(0);

		// Maps for storing old relationships.
		Map<String, Long> oldClassIhDeps = new HashMap<String, Long>();
		Map<String, Long> oldClUsageDeps = new HashMap<String, Long>();
		Map<String, Long> oldMcallDeps = new HashMap<String, Long>();
		Map<String, Long> oldOverrideDeps = new HashMap<String, Long>();
		Map<String, Long> oldFRefDeps = new HashMap<String, Long>();

		// Gather relationships.
		for (ApiClass oldAc : oldProduct.getClasses()) {

			findInhDepsIds(oldClassIhDeps, oldAc);
			findClusageIds(oldClUsageDeps, oldAc);

			for (Method oldM : oldAc.getMethods()) {
				findMCallIds(oldMcallDeps, oldM);
				findOverrideIds(oldOverrideDeps, oldM);
			}
			for (Field oldF : oldAc.getFields()) {
				findFrefDeps(oldFRefDeps, oldF);
			}
		}

		// Delete product.
		productRepsitory.delete(oldProduct.getId());

		// Add the new product to the database.
		newProduct = productRepsitory.save((ProductNode) newProduct);

		// Merge back the dependencies in the graph.
		for (ApiClass newAc : oldProduct.getClasses()) {
			reAddDep(oldClassIhDeps, newAc.getFqName(), newAc, DependencyType.CLASS_INHERITANCE);
			reAddDep(oldClUsageDeps, newAc.getFqName(), newAc, DependencyType.CLASS_USAGE);

			for (Method newM : newAc.getMethods()) {
				reAddDep(oldMcallDeps, newM.getSignature(), newM, DependencyType.METHOD_CALL);
				reAddDep(oldMcallDeps, newM.getSignature(), newM, DependencyType.METHOD_CALL);
			}
			for (Field newF : newAc.getFields()) {
				reAddDep(oldFRefDeps, newF.getSignature(), newF, DependencyType.FIELD_REFERENCE);
			}
		}

		// If there is a dependency which does not have a correspondent item in a new version, than mark it as a
		// non-existing item.
		// addNonExistingNodes(oldFRefDeps, oldProductName, oldProductVersion, DependencyType.FIELD_REFERENCE);
		// addNonExistingNodes(oldClUsageDeps, oldProductName, oldProductVersion, DependencyType.CLASS_USAGE);
		// addNonExistingNodes(oldClassIhDeps, oldProductName, oldProductVersion, DependencyType.CLASS_INHERITANCE);
		// addNonExistingNodes(oldMcallDeps, oldProductName, oldProductVersion, DependencyType.METHOD_CALL);
		// addNonExistingNodes(oldOverrideDeps, oldProductName, oldProductVersion, DependencyType.METHOD_OVERRIDE);

	}

	@SuppressWarnings("unused")
	private void addNonExistingNodes(Map<String, Long> refs, String productName, String productVersion,
			DependencyType type) {
		for (String key : refs.keySet()) {
			RemovedNode rn = new RemovedNode(key, productName, productVersion);
			neo4jTemplate.save(rn);
			CodeElement ceFrom = apiClassRepository.findOne(refs.get(key));
			if (ceFrom == null) {
				ceFrom = methodRepository.findOne(refs.get(key));
			}

			DependencyRelation rel = new DependencyRelation(ceFrom, rn, type);
			neo4jTemplate.save(rel);
		}

	}

	private void reAddDep(Map<String, Long> map, String key, CodeElement keyObj, DependencyType type) {
		Long val = map.get(key);
		if (val != null) {
			DependencyRelation rel = new DependencyRelation(apiClassRepository.findOne(val), keyObj, type);
			neo4jTemplate.save(rel);

			// remove item from the list.
			map.remove(key);
		}
	}

	private void findInhDepsIds(Map<String, Long> oldClassIhDeps, ApiClass oldAc) {
		Collection<DependencyRelation> inhDeps = apiClassRepository
				.findIncomingInheritanceRelationships((ClassNode) oldAc);
		for (DependencyRelation dr : inhDeps) {
			oldClassIhDeps.put(oldAc.getFqName(), dr.getFrom().getId());
		}
	}

	private void findClusageIds(Map<String, Long> oldClassIhDeps, ApiClass oldAc) {
		Collection<DependencyRelation> clUsageDeps = apiClassRepository
				.findIncomingClassUsageRelationships((ClassNode) oldAc);
		for (DependencyRelation dr : clUsageDeps) {
			oldClassIhDeps.put(oldAc.getFqName(), dr.getFrom().getId());
		}
	}

	private void findMCallIds(Map<String, Long> oldMcallDeps, Method oldM) {
		Collection<DependencyRelation> clUsageDeps = methodRepository
				.findIncomingMethodCallRelationships((MethodNode) oldM);
		for (DependencyRelation dr : clUsageDeps) {
			oldMcallDeps.put(oldM.getSignature(), dr.getFrom().getId());
		}
	}

	private void findOverrideIds(Map<String, Long> oldOverrideDeps, Method oldM) {
		Collection<DependencyRelation> clUsageDeps = methodRepository
				.findIncomingMethodOverrideRelationships((MethodNode) oldM);
		for (DependencyRelation dr : clUsageDeps) {
			oldOverrideDeps.put(oldM.getSignature(), dr.getFrom().getId());
		}

	}

	private void findFrefDeps(Map<String, Long> oldFRefDeps, Field oldF) {
		Collection<DependencyRelation> clUsageDeps = fieldRepository
				.findIncomingFieldReferenceRelationships((FieldNode) oldF);
		for (DependencyRelation dr : clUsageDeps) {
			oldFRefDeps.put(oldF.getSignature(), dr.getFrom().getId());
		}
	}

	private int findClassreferenceDeps(CodeElement source, Collection<? extends CodeElement> targets) {
		// Gathers the signatures of the target classes.
		List<String> sigs = getSignatures(targets);
		if (sigs.size() == 0) {
			return 0;
		}

		// TODO: replace it with a cypher query which contains the where clause: WHERE a.name IN ["Peter", "Tobias"].
		// http://docs.neo4j.org/chunked/milestone/query-where.html

		// Look for the signatures in the database.
		LinkedList<ClassNode> classList = new LinkedList<ClassNode>();
		for (String sig : sigs) {
			EndResult<ClassNode> classes = apiClassRepository.findAllByPropertyValue("fqName", sig);
			for (ClassNode cl : classes) {
				classList.add(cl);
			}
		}

		// Stores that the source is dependent on all of the found items as it has a reference on the items.
		int result = 0;
		for (ClassNode found : classList) {
			DependencyRelation rel = new DependencyRelation(source, found, DependencyType.CLASS_USAGE);
			neo4jTemplate.save(rel);
			result++;
		}
		return result;
	}

	private int findInheritanceDeps(CodeElement source, Collection<? extends CodeElement> targets) {
		// Gathers the signatures of the target classes.
		List<String> sigs = getSignatures(targets);
		if (sigs.size() == 0) {
			return 0;
		}

		// Look for the signatures in the database.
		LinkedList<ClassNode> classList = new LinkedList<ClassNode>();
		for (String sig : sigs) {
			EndResult<ClassNode> classes = apiClassRepository.findAllByPropertyValue("fqName", sig);
			for (ClassNode cl : classes) {
				classList.add(cl);
			}
		}

		// Stores that the source is dependent on all of the found items as it has a reference on the items.
		int result = 0;
		for (ClassNode found : classList) {
			DependencyRelation rel = new DependencyRelation(source, found, DependencyType.CLASS_INHERITANCE);
			neo4jTemplate.save(rel);
			result++;
		}
		return result;
	}

	private int findFieldreferenceDeps(CodeElement source, Collection<? extends CodeElement> targets) {
		// Gathers the signatures of the target classes.
		List<String> sigs = getSignatures(targets);
		if (sigs.size() == 0) {
			return 0;
		}

		// Look for the signatures in the database.
		LinkedList<FieldNode> fieldList = new LinkedList<FieldNode>();
		for (String sig : sigs) {
			EndResult<FieldNode> fields = fieldRepository.findAllByPropertyValue("signature", sig);
			for (FieldNode fn : fields) {
				fieldList.add(fn);
			}
		}

		// Stores that the source is dependent on all of the found items as it has a reference on the items.
		int result = 0;
		for (FieldNode found : fieldList) {
			DependencyRelation rel = new DependencyRelation(source, found, DependencyType.FIELD_REFERENCE);
			neo4jTemplate.save(rel);
			result++;
		}
		return result;
	}

	private int findMethodCallDeps(CodeElement source, Collection<? extends CodeElement> targets) {
		// Gather called methods' signature.
		List<String> sigs = getSignatures(targets);
		if (sigs.size() == 0) {
			return 0;
		}

		// Look for the signatures in the database.
		LinkedList<MethodNode> methodList = new LinkedList<MethodNode>();
		for (String sig : sigs) {
			EndResult<MethodNode> methods = methodRepository.findAllByPropertyValue("signature", sig);
			for (MethodNode mn : methods) {
				methodList.add(mn);
			}
		}

		// Stores that the source is dependent on all of the found items as it has a reference on the items.
		int result = 0;
		for (MethodNode found : methodList) {
			DependencyRelation rel = new DependencyRelation(source, found, DependencyType.METHOD_CALL);
			neo4jTemplate.save(rel);
			result++;
		}
		return result;
	}

	private int findOverridedFunc(CodeElement source) {
		// Parameter check: we wait for stored apiclass instances.
		if (source == null || !(source instanceof ApiClass) || source.getId() == 0) {
			throw new IllegalArgumentException("Parameter should be a persisted ApiClass instance.");
		}

		// Get class.
		ApiClass subClass = (ApiClass) source;
		int result = 0;

		// Check if the class has valid superclass
		if (subClass.getExtends() == null || "".equals(subClass.getExtends())) {
			return 0;
		}

		for (ApiClass superClass : apiClassRepository.findAllByPropertyValue("fqName", subClass.getExtends())) {
			for (Method superMethod : superClass.getMethods()) {
				superMethod = methodRepository.findOne(superMethod.getId());

				for (Method subMethod : subClass.getMethods()) {

					if (subMethod.isStatic()) {
						continue;
					}

					boolean isOverride = BeanUtils.hasMethodsSameSignature(superClass.getSimpleName(),
							subClass.getSimpleName(), superMethod, subMethod);

					// If we found an actual method override, store it in the database.
					if (isOverride) {
						DependencyRelation rel = new DependencyRelation(subMethod, superMethod,
								DependencyType.METHOD_OVERRIDE);

						neo4jTemplate.save(rel);
						result++;
					}
				}
			}
		}

		return result;
	}

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

	private Collection<DependencyRelation> findIncomingClassUsages(ClassNode cn) {
		return apiClassRepository.findIncomingClassUsageRelationships(cn);
	}

	private Collection<Dependency> findIncomingInheritances(ClassNode cn) {
		Collection<DependencyRelation> from = apiClassRepository.findIncomingInheritanceRelationships(cn);
		Collection<Dependency> result = new LinkedList<Dependency>();

		// Search for transitive dependencies.
		for (DependencyRelation rel : from) {
			Collection<Dependency> trans = findIncomingInheritances((ClassNode) rel.getFrom());

			if (trans == null || trans.size() == 0) {
				result.add(rel);
			} else {
				result.add(new TransitiveDependencyRelation(rel, trans));
			}
		}
		return result;
	}

	private Collection<DependencyRelation> findIncomingMethodCalls(MethodNode m) {
		return methodRepository.findIncomingMethodCallRelationships(m);
	}

	private Collection<Dependency> findIncomingOverrides(MethodNode element) {
		Collection<DependencyRelation> from = methodRepository.findIncomingMethodOverrideRelationships(element);
		Collection<Dependency> result = new LinkedList<Dependency>();

		// Search for transitive dependencies.
		for (DependencyRelation rel : from) {
			Collection<Dependency> trans = findIncomingOverrides((MethodNode) rel.getFrom());

			if (trans == null || trans.size() == 0) {
				result.add(rel);
			} else {
				result.add(new TransitiveDependencyRelation(rel, trans));
			}
		}
		return result;
	}

	private Collection<DependencyRelation> findIncomingFieldRefs(FieldNode f) {
		return fieldRepository.findIncomingFieldReferenceRelationships(f);
	}

	
	public long getNumOfDeps() {
		return -1;
	}

	
	public void flush(String string) throws IOException {
		// TODO Auto-generated method stub
		
	}

	
	public void reset() {
		// TODO Auto-generated method stub
		
	}

	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public URI findCp3ModelForDirectDeps(List<String> projects) {
		// TODO Auto-generated method stub
		return null;
	}
}
