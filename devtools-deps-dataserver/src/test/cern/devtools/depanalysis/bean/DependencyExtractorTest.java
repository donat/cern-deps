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
package cern.devtools.depanalysis.bean;

import java.util.LinkedList;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import cern.devtools.depanalysis.bean.impl.FileDescriptor;
import cern.devtools.depanalysis.bean.impl.EmfDatabaseDao;

public class DependencyExtractorTest extends AbstractBeanTest {

	@Test
	@Ignore
	public void test() throws Exception {

		FileDescriptor ad1 = new FileDescriptor("japc-value", "2.11.0",
				"\\\\cs-ccr-nfsdev\\pcrops\\dist\\japc\\japc-value\\2.11.0\\build\\dist\\japc-value.jar", "japc");

		FileDescriptor ad2 = new FileDescriptor("japc", "2.19.0",
				"\\\\cs-ccr-nfsdev\\pcrops\\dist\\japc\\japc\\2.19.0\\build\\dist\\japc.jar", "japc");

		FileDescriptor ad3 = new FileDescriptor(
				"fnal-patrick-waterfalldemo",
				"0.4.0",
				"\\\\cs-ccr-nfsdev\\pcrops\\dist\\fnal\\fnal-patrick-waterfalldemo\\0.4.0\\build\\dist\\fnal-patrick-waterfalldemo.jar",
				"fnal");

		List<ArtifactDescriptor> lad = new LinkedList<ArtifactDescriptor>();
		lad.add(ad1);
		lad.add(ad2);
		lad.add(ad3);

		// execute analysis
		DependencyExtractor extractor = (DependencyExtractor) context.getBean("dependency_extractor");
		extractor.executeAnalysis(lad);

		EmfDatabaseDao db = (EmfDatabaseDao) context.getBean("database_dao");
		db.printDeps();

	}

	@Test
    @Ignore
	public void testVisitorJars() throws Exception {

		FileDescriptor ad1 = new FileDescriptor("example-service", "1.0.0",
				"c:/opt/workspace/eclipse/devtools-deps-example-service/dist/devtools-deps-example-service.jar",
				"/dev/null");
		FileDescriptor ad2 = new FileDescriptor("example-impl", "1.0.0",
				"c:/opt/workspace/eclipse/devtools-deps-example-impl/dist/devtools-deps-example-impl.jar", "/dev/null");
		FileDescriptor ad3 = new FileDescriptor("example-impl", "2.0.0",
				"c:/opt/workspace/eclipse/devtools-deps-example-impl2/dist/devtools-deps-example-impl.jar", "/dev/null");

		List<ArtifactDescriptor> lad = new LinkedList<ArtifactDescriptor>();
		lad.add(ad1);
		lad.add(ad2);
		lad.add(ad3);

		DependencyExtractor extractor = (DependencyExtractor) context.getBean("dependency_extractor");
		// execute analysis
		extractor.executeAnalysis(lad);

		// DatabaseDao db = (DatabaseDao)context.getBean("database_dao");
		// db.commit();
		// db.printStructure();
		// db.printDeps();
	}

}
