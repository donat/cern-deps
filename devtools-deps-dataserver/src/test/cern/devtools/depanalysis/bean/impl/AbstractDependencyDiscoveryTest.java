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

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

import cern.devtools.depanalysis.bean.ArtifactDescriptor;
import cern.devtools.depanalysis.bean.DatabaseDao;
import cern.devtools.depanalysis.bean.DepBeanException;
import cern.devtools.depanalysis.bean.DependencyExtractor;
import cern.devtools.depanalysis.domain.creation.DomainObjectCreator;
import cern.devtools.depanalysis.memcomp.InMemoryClassLoader;
import cern.devtools.depanalysis.memcomp.InMemoryCompiler;

/**
 * <p>
 * Test framework class for testing dependency extraction mechanism.
 * </p>
 * <p>
 * The inherited classes should provide the source code of the product in the {@link #from()}, {@link #to()} and
 * {@link #trans()} methods. While the first two is obligatory, the third can optionally return <code>null</code>. Each
 * function should return the source and the destination of a potential dependency relation which should be analysed in
 * the test method.
 * </p>
 * <p>
 * In the {@link #test()} method should contains the actual assertions. While it is not required, but one inherited
 * class should contain only one test, as it represents one particular usecase.
 * </p>
 * <p>
 * The execution first starts by collecting the source code through the abstract methods, then compiles them. After
 * that, it fires up the spring framework and initiates dependency discovery, which follows the execution of the
 * {@link #test()} method. As the cleanup, the test deletes the created data from the database. The names of the stored
 * products are respectively example-from, example-to and optionally example-trans.
 * </p>
 * 
 * @author Donat Csikos
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/config/ctx-test-discovery-oracledev.xml")
public abstract class AbstractDependencyDiscoveryTest {
	/**
	 * Holder class for the details of the source code.
	 * 
	 * @author Donat Csikos
	 */
	public static class Source {
		private final List<String> names;
		private final List<String> sources;
		private final String pkg;

		/**
		 * @param names Names of the class without the package name.
		 * @param sources Source code of the class.
		 * @param pkg package name of the classes.
		 */
		public Source(List<String> names, List<String> sources, String pkg) {
			this.names = names;
			this.sources = sources;
			this.pkg = pkg;
		}

		public List<String> names() {
			return names;
		}

		public List<String> sources() {
			return sources;
		}

		public String pkg() {
			return pkg;
		}
	}

	/**
	 * Holder of the compiled classes.
	 */
	private static InMemoryClassLoader classLoader;

	/**
	 * Database object.
	 */
	@Autowired
	protected DatabaseDao db;

	@Autowired
	private DomainObjectCreator creator;

	@Autowired
	private DependencyExtractor extractor;

	public abstract Source from();

	public abstract Source to();

	public abstract Source trans();

	@Test
	public abstract void test() throws Exception;

	@Before
	public final void init() throws DepBeanException {

		// save sources
		Source from = from();
		Source to = to();
		Source trans = trans();

		// build source
		compile(from, to, trans);

		// obtain bytecode
		ByteCodeDescriptor adFrom = new ByteCodeDescriptor("example-from", classLoader.getPackageByteCode(from.pkg()));
		ByteCodeDescriptor adTo = new ByteCodeDescriptor("example-to", classLoader.getPackageByteCode(to.pkg()));
		ByteCodeDescriptor adTrans = null;
		if (trans != null) {
			adTrans = new ByteCodeDescriptor("example-trans", classLoader.getPackageByteCode(trans.pkg()));
		}

		// start discovery
		List<ArtifactDescriptor> lad = new LinkedList<ArtifactDescriptor>();
		lad.addAll(Arrays.asList(adFrom, adTo));

		if (adTrans != null) {
			lad.add(adTrans);
		}
		extractor.executeAnalysis(lad);
	}

	private void compile(Source from, Source to, Source trans) {
		ArrayList<String> sources = new ArrayList<String>();
		sources.addAll(from.sources());
		sources.addAll(to.sources());
		if (trans != null) {
			sources.addAll(trans.sources());
		}

		ArrayList<String> names = new ArrayList<String>();
		names.addAll(from.names());
		names.addAll(to.names());
		if (trans != null) {
			names.addAll(trans.names());
		}
		classLoader = InMemoryCompiler.compileClasses(sources.toArray(new String[0]), names.toArray(new String[0]));
	}

	@After
	public final void cleanup() throws Exception {
		db.deleteProduct(creator.createProduct("example-from", "", "", "example-from"));
		db.deleteProduct(creator.createProduct("example-to", "", "", "example-to"));
		db.deleteProduct(creator.createProduct("example-trans", "", "", "example-trans"));
	}
}
