/*
 * File GroupingOptions.java.
 * Created by Donat Csikos<dcsikos@cern.ch> at 14 Mar 2012.
 *
 * Copyright CERN 2012, All Rights Reserved.
 */
package cern.devtools.deps.eclipse.ui.views;

/**
 * The available groupings for {@link DependencyView#treeViewer}.
 * 
 * @author Donat Csikos
 * 
 */
public enum GroupingOptions {
	/**
	 * Organise the hierarchy structures to the following levels: Product &lt; Version &lt; Class &lt; Method.
	 */
	GROUP_BY_PRODUCT {
		public GroupingOptions next() {
			return GROUP_BY_DEPENDENCY_TYPE;
		}
	},

	/**
	 * Organise the hierarchy structures to the following levels: Type of dependency &lt; {Method, Class}  &lt; Transitive dependency.
	 */
	GROUP_BY_DEPENDENCY_TYPE {
		@Override
		public GroupingOptions next() {
			return GROUP_BY_PRODUCT;
		}
	};

	/**
	 * Returns the order of the organising options. Used for determining a strategy, what happens if a next grouping
	 * option action is executed.
	 * 
	 * @return The next used grouping.
	 */
	public abstract GroupingOptions next();
}
