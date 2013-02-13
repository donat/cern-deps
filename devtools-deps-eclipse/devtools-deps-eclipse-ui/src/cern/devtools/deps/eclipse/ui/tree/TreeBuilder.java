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
package cern.devtools.deps.eclipse.ui.tree;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import cern.devtools.deps.domain.ApiClass;
import cern.devtools.deps.domain.CodeElement;
import cern.devtools.deps.domain.Dependency;
import cern.devtools.deps.domain.DependencyType;
import cern.devtools.deps.domain.Method;
import cern.devtools.deps.domain.Product;
import cern.devtools.deps.domain.TransitiveDependency;
import cern.devtools.deps.eclipse.ui.LoggingUtil;
import cern.devtools.deps.eclipse.ui.views.GroupingOptions;
import cern.devtools.deps.repomodel.RDependency;

public class TreeBuilder {

	/**
	 * Builds the tree for the view.
	 * 
	 * @param objectToAnalyse The queried object.
	 * @param dependencies The result set returned by the server.
	 * @param grouping
	 * @return The reference of the tree item, which is displayable by the treeViewer.
	 * 
	 * @author Donat Csikos <dcsikos@cern.ch>
	 */
	public static TreeItem buildTree(CodeElement objectToAnalyse, Collection<Dependency> dependencies,
			GroupingOptions grouping) {
		// Create root node. This node won't be displayed.
		TreeItem root = TreeItem.createNewNode(TreeItemType.ROOT, null);

		// If no dependencies exist then only add the special NO_DEPENDENCIES node.
		if (dependencies.size() == 0) {
			TreeItem.createNewNode(TreeItemType.NO_DEPENDENCIES, root, objectToAnalyse);
		}
		// Else build the dependency tree.
		else {
			// Add a visible root which shows which item was queried for incoming dependencies.
			TreeItem subRoot = TreeItem.createNewNode(TreeItemType.ROOT, root, objectToAnalyse);
			buildTree(dependencies, subRoot, grouping);
		}

		// Return the root of the node hierarchy.
		return root;
	}

	/**
	 * Builds The tree based on the grouping options.
	 * 
	 * @param dependencies The result set returned by the server.
	 * @param root The parent node for the created items.
	 */
	private static void buildTree(Collection<Dependency> dependencies, TreeItem root, GroupingOptions grouping) {
		switch (grouping) {
		case GROUP_BY_DEPENDENCY_TYPE:
			buildTreeDepGrouped(dependencies, root);
			break;
		case GROUP_BY_PRODUCT:
			buildTreeProductGrouped(dependencies, root);
			break;
		default:
			throw new RuntimeException("No TreeBuilder strategy defined for: " + grouping);
		}
	}

	/**
	 * Builds The tree with associated with the {@link GroupingOptions#GROUP_BY_DEPENDENCY_TYPE}.
	 * 
	 * @param dependencies The result set returned by the server.
	 * @param root The parent node for the created items.
	 */
	private static void buildTreeDepGrouped(Collection<Dependency> dependencies, TreeItem root) {
		// Categorise dependencies by type to display.
		Map<DependencyType, List<Dependency>> typedDeps = new HashMap<DependencyType, List<Dependency>>();
		for (Dependency d : dependencies) {
			List<Dependency> deps = typedDeps.get(d.getType());
			if (deps == null) {
				deps = new LinkedList<Dependency>();
				typedDeps.put(d.getType(), deps);
			}
			deps.add(d);
		}

		// Add the dependencies to the tree by types.
		for (DependencyType type : typedDeps.keySet()) {
			// Subroot for each type of dependencies
			TreeItem dependencyNode = TreeItem.createNewNode(TreeItemType.DEPENDENCY_TYPE, root, type);

			// Gather the different package names from the result.
			Set<String> packageSet = new HashSet<String>();
			Set<ApiClass> apiClassSet = new HashSet<ApiClass>();
			Set<Method> methodSet = new HashSet<Method>();
			for (Dependency d : typedDeps.get(type)) {
				CodeElement ce = d.getFrom();
				if (ce instanceof ApiClass) {
					ApiClass ac = (ApiClass) ce;
					packageSet.add(ac.getPackageName());
					apiClassSet.add(ac);
				} else if (ce instanceof Method) {
					Method m = (Method) ce;
					packageSet.add(m.getApiClass().getPackageName());
					methodSet.add(m);
				} else if (ce instanceof Product) {
					TreeItem.createNewNode(TreeItemType.PRODUCT, dependencyNode, ce);
				} else {
					throw new RuntimeException("Dependency contains a wrong element: " + ce);
				}
			}

			// Put the sets to a list and sort it.
			List<String> packageList = new LinkedList<String>(packageSet);
			Collections.sort(packageList);

			// Create a node for each package and cache it.
			Map<String, TreeItem> packageNodeCache = new HashMap<String, TreeItem>();
			for (String p : packageList) {
				TreeItem subSubNode = TreeItem.createNewNode(TreeItemType.PACKAGE, dependencyNode, p);
				packageNodeCache.put(p, subSubNode);
			}

			// Add the dependencies below the package nodes.
			for (Dependency d : typedDeps.get(type)) {

				// Reference for the node lies on the lowest level. Used for adding the transitive dependencies.
				TreeItem leafNode = null;
				if (d.getFrom() instanceof Method) {
					Method m = (Method) d.getFrom();
					// Find proper package node which will be the parent of the method.
					TreeItem packageNode = packageNodeCache.get(m.getApiClass().getPackageName());

					// Find if it is already added to the list
					TreeItem classNode = null;
					for (TreeItem ti : packageNode.getChildren()) {
						ApiClassTreeItem acti = (ApiClassTreeItem) ti;
						if (acti.getValue().equals(m.getApiClass())) {
							classNode = ti;
							break;
						}
					}

					// If the class node is not yet added, then create it.
					if (classNode == null) {
						classNode = TreeItem.createNewNode(TreeItemType.CLASS, packageNode, m.getApiClass(), "PRO");
					}

					// First add the container class below the package, then add the method itself as the leaf node.
					leafNode = TreeItem.createNewNode(TreeItemType.METHOD, classNode, d.getFrom(), "PRO");
				} else if (d.getFrom() instanceof ApiClass) {
					ApiClass ac = (ApiClass) d.getFrom();
					// Find proper package node which will be the parent of the class.
					TreeItem packageNode = packageNodeCache.get(ac.getPackageName());
					// Add the class node as the leaf node.
					leafNode = TreeItem.createNewNode(TreeItemType.CLASS, packageNode, d.getFrom(), "PRO");
				} else if (d.getFrom() instanceof Product) {

				} else {
					throw new RuntimeException("Not legal type for this level of hierarchy: " + d.getFrom().getClass());
				}

				// If the current dependency is a transitive one, the build a subtree about the dependency structure
				// using the previouslt defined leaf node as the new root.
				if (d instanceof TransitiveDependency) {
					buildTree(((TransitiveDependency) d).getTransitiveFrom(), leafNode,
							GroupingOptions.GROUP_BY_DEPENDENCY_TYPE);
				}
			}
		}
	}

	/**
	 * Builds The tree with associated with the {@link GroupingOptions#GROUP_BY_PRODUCT}.
	 * 
	 * @param dependencies The result set returned by the server.
	 * @param root The parent node for the created items.
	 */
	private static void buildTreeProductGrouped(Collection<Dependency> dependencies, TreeItem root) {
		// Gather all product involved in the dependencies.
		Set<Product> products = new HashSet<Product>();
		Map<CodeElement, Dependency> dependencyCache = new HashMap<CodeElement, Dependency>();

		for (Object dO : dependencies) {
			RDependency d = (RDependency)dO;
			CodeElement ce = d.getFrom();
			dependencyCache.put(ce, d);
			if (ApiClass.class.isAssignableFrom(ce.getClass())) {
				products.add(((ApiClass) ce).getProduct());
			} else if (Method.class.isAssignableFrom(ce.getClass())) {
				products.add(((Method) ce).getApiClass().getProduct());
			} else if (Product.class.isAssignableFrom(ce.getClass())) {
				products.add((Product) ce);
			} else {
				// do not throw an exception, because we want the editor to stay intact.
				LoggingUtil.warnAndLog("TreeItem type is not displayable " + ce.getClass(), new RuntimeException());
			}
		}

		// Sort the products
		List<Product> productList = new LinkedList<Product>(products);
		Collections.sort(productList, new Comparator<Product>() {
			@Override
			public int compare(Product o1, Product o2) {
				return o1.getName().compareTo(o2.getName());
			}
		});

		for (final Product p : productList) {
			// Gather the different package names from the result.
			Set<String> packageSet = new HashSet<String>();
			for (ApiClass ac : p.getClasses()) {
				packageSet.add(ac.getPackageName());
			}
			// Put the package to the list and sort it.
			List<String> packageList = new LinkedList<String>(packageSet);
			Collections.sort(packageList);

			// Create product node.
			TreeItem productNode = TreeItem.createNewNode(TreeItemType.PRODUCT, root, p);

			// Create version nodes below and index them.
			// Map<String, TreeItem> versionNodeCache = new HashMap<String, TreeItem>();
			for (final String version : p.getVersions()) {
				TreeItem versionNode = TreeItem.createNewNode(TreeItemType.VERSION, productNode, version);
				// versionNodeCache.put(version, versionNode);

				// Create a node for each package and cache it.
				Map<String, TreeItem> packageNodeCache = new HashMap<String, TreeItem>();
				for (String pkg : packageList) {
					TreeItem packageNode = TreeItem.createNewNode(TreeItemType.PACKAGE, versionNode, pkg);
					packageNodeCache.put(pkg, packageNode);

					// for each package add all of the contained classes and methods
					for (ApiClass ac : p.getClasses()) {
						if (ac.getPackageName().equals(pkg) && ac.getVersions().contains(version)) {

							// Create apiclass node if not exists.
							TreeItem classNode  = TreeItem.createNewNode(TreeItemType.CLASS, packageNode, ac, version);

							// If it is a transitive dependency, then recursively build the tree..
							Dependency d = dependencyCache.get(ac);
							if (d != null && d instanceof TransitiveDependency) {
								TreeItem transRootNode = TreeItem.createNewNode(TreeItemType.ROOT, classNode, ac);
								buildTreeProductGrouped(((TransitiveDependency) d).getTransitiveFrom(), transRootNode);
							}

							// Do the same with the method nodes.
							for (Method m : ac.getMethods()) {
								if (m.getVersions().contains(version)) {

									TreeItem methodNode = TreeItem.createNewNode(TreeItemType.METHOD, classNode, m,
											version);

									d = dependencyCache.get(m);
									if (d != null && d instanceof TransitiveDependency) {
										TreeItem transRootNode = TreeItem.createNewNode(TreeItemType.ROOT, methodNode,
												m);
										buildTreeProductGrouped(((TransitiveDependency) d).getTransitiveFrom(),
												transRootNode);
									}
								}
							}
						}
					}

					// if the current version does not contain any packages, then remove it
					if (packageNode.getChildren().size() == 0) {
						// Same as versionNode.getChildren().remove(packageNode);.
						packageNode.getParent().getChildren().remove(packageNode);
					}
				}
			}
		}
	}
}
