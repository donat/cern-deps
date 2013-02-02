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
package cern.devtools.depanalysis.etc;

import java.util.LinkedList;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cern.devtools.depanalysis.bean.ArtifactDescriptor;
import cern.devtools.depanalysis.bean.DependencyExtractor;
import cern.devtools.depanalysis.bean.impl.FileDescriptor;

public class Mytest {

	@Test
	@Ignore
	public void test() throws Exception {

		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"classpath:/config/ctx-test.xml");

		//ArtifactFinder finder = (ArtifactFinder) context.getBean("artifact_finder");
		List<ArtifactDescriptor> descriptors = new LinkedList<ArtifactDescriptor>();
		 descriptors.add(new FileDescriptor("lsa-client", "7.0.11",
		 "\\\\cs-ccr-nfsdev\\pcrops\\dist\\lsa\\lsa-client\\\\7.0.11\\build\\dist\\lsa-client.jar",
		 "lsa/lsa-client"));
		// descriptors.add(new FileDescriptor("lsa-app-optics", "6.0.3",
		// "\\\\cs-ccr-nfsdev\\pcrops\\dist\\lsa\\lsa-app-optics\\\\6.0.3\\build\\dist\\lsa-app-optics.jar",
		// "lsa/lsa-app-optics"));
		// descriptors.add(new FileDescriptor("lsa-app-optics-3t", "6.0.3",
		// "\\\\cs-ccr-nfsdev\\pcrops\\dist\\lsa\\lsa-app-optics\\\\6.0.3\\build\\dist\\lsa-app-optics.jar",
		// "lsa/lsa-app-optics"));

		// execute analysis
		DependencyExtractor extractor = (DependencyExtractor) context.getBean("dependency_extractor");

		//List<? extends ArtifactDescriptor> la = finder.findArtifacts();
		// for (ArtifactDescriptor ad : la) {
		// if (!new File(ad.getJarPath()).exists()) {
		// System.out.println("Invalid artifact: " + ad.getJarPath());
		// System.out.println("   " + ad.getContainingFolders());
		// }
		// }
		//
		// System.out.println("Total: " + la.size());

		extractor.executeAnalysis(descriptors);
		// DependencyService service = (DependencyService) context.getBean("dependency_service");
		// Collection<Dependency> result = service.getIncomingDependencies(DomainFactory.creator().createMethod(
		// "cern.lsa.client.ClientKnobController#findKnobsByNames(java.lang.String[]):java.util.Map",
		// EnumSet.noneOf(Modifiers.class)));
		// for (Dependency d : result) {
		// System.out.println(d);
		// }
	}
}
