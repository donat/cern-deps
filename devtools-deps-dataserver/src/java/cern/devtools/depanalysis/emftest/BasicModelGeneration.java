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
package cern.devtools.depanalysis.emftest;

import java.util.LinkedList;
import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import cern.devtools.depanalysis.bean.ArtifactDescriptor;
import cern.devtools.depanalysis.bean.DependencyExtractor;
import cern.devtools.depanalysis.bean.impl.FileDescriptor;
import cern.devtools.depanalysis.bean.impl.EmfDatabaseDao;

/**
 * Test for exporting small model from two depending jar files.
 * 
 * @author Donat Csikos
 */
public class BasicModelGeneration {
	public static void main(String[] args) throws Exception {

		// Prepare context and artifacts.
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"classpath:/config/ctx-test-emf.xml");

		ArtifactDescriptor ad1 = new FileDescriptor("devtools-deps-example-service", "1.0.0",
				"/C:/opt/workspace/eclipse/devtools-deps-example-service/dist/devtools-deps-example-service.jar",
				"ignore");
		ArtifactDescriptor ad2 = new FileDescriptor("devtools-deps-example-impl", "1.0.0",
				"/C:/opt/workspace/eclipse/devtools-deps-example-impl/dist/devtools-deps-example-impl.jar", "ignore");
		List<ArtifactDescriptor> lad = new LinkedList<ArtifactDescriptor>();
		lad.add(ad1);
		lad.add(ad2);

		// Execute analysis.
		DependencyExtractor extractor = (DependencyExtractor) context.getBean("dependency_extractor");
		extractor.executeAnalysis(lad);

		// Store database.
		EmfDatabaseDao db = (EmfDatabaseDao) context.getBean("database_dao");
		db.flush("visitor.repomodel");
		
		db.printDeps();
	}
}
