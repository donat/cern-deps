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
package cern.devtools.deps.emftest;

import java.util.LinkedList;
import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import cern.devtools.deps.bean.ArtifactDescriptor;
import cern.devtools.deps.bean.DependencyExtractor;
import cern.devtools.deps.bean.impl.EmfDatabaseDao;
import cern.devtools.deps.bean.impl.FileDescriptor;

public class ServiceProviderFrameworkGeneration {

	public static void main(String[] args) throws Exception {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"classpath:/config/ctx-test-emf.xml");

		EmfDatabaseDao db = (EmfDatabaseDao) context.getBean("database_dao");
		DependencyExtractor extractor = (DependencyExtractor) context.getBean("dependency_extractor");

		List<ArtifactDescriptor> artifacts = new LinkedList<ArtifactDescriptor>();	
		artifacts.add(new FileDescriptor("service", "1.0.0", "C:/opt/workspace/eclipse4/service-provider-framework/service.jar", ""));
		artifacts.add(new FileDescriptor("client", "1.0.0", "C:/opt/workspace/eclipse4/service-provider-framework/client.jar", ""));
		artifacts.add(new FileDescriptor("impl", "1.0.0", "C:/opt/workspace/eclipse4/service-provider-framework/impl.jar", ""));
		
		extractor.executeAnalysis(artifacts);
		
		((EmfDatabaseDao)db).printStructure();
		((EmfDatabaseDao)db).printDeps();
		
		db.flush("spf.repomodel");
	}

}
