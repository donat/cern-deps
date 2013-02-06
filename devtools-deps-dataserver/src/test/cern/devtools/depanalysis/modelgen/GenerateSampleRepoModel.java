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
package cern.devtools.depanalysis.modelgen;

import java.util.LinkedList;
import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import cern.devtools.depanalysis.bean.ArtifactDescriptor;
import cern.devtools.depanalysis.bean.DependencyExtractor;
import cern.devtools.depanalysis.bean.impl.EmfDatabaseDao;
import cern.devtools.depanalysis.bean.impl.FileDescriptor;

public class GenerateSampleRepoModel {
	public static void main(String[] args) throws Exception {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"classpath:/config/ctx-test-discovery-emf.xml");
		
		
		FileDescriptor ad1 = new FileDescriptor("japc", "1.0.0",
				"sampleprojects/bin/japc.jar", "japc/japc");
		FileDescriptor ad2 = new FileDescriptor("accsoft-gui-alarms", "1.0.0",
				"sampleprojects/bin/accsoft-gui-alarms.jar", "accsoft/gui/accsoft-gui-alarms");
		FileDescriptor ad3 = new FileDescriptor("japc-ext-cmw", "1.0.0",
				"sampleprojects/bin/japc-ext-cmw.jar", "japc/japc-ext-remote");

		List<ArtifactDescriptor> lad = new LinkedList<ArtifactDescriptor>();
		lad.add(ad1);
		lad.add(ad2);
		lad.add(ad3);

		// start execution
		DependencyExtractor extractor = (DependencyExtractor) context.getBean("dependency_extractor");
		extractor.executeAnalysis(lad);
		
		// flush data onto the disk
		EmfDatabaseDao db = (EmfDatabaseDao) context.getBean("database_dao");
		db.flush("sampleprojects/bin/sampleprojects-japc.repomodel");
	}
}
