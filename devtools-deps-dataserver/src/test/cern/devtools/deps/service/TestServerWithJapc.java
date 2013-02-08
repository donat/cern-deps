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
package cern.devtools.deps.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import cern.devtools.deps.bean.ArtifactDescriptor;
import cern.devtools.deps.bean.DatabaseDao;
import cern.devtools.deps.bean.DependencyExtractor;
import cern.devtools.deps.bean.impl.FileDescriptor;
import cern.devtools.deps.domain.creation.DomainFactory;

public class TestServerWithJapc {
	public static void main(String[] args) throws Exception {

		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"classpath:/config/ctx-test-service.xml");

		FileDescriptor ad1 = new FileDescriptor("japc-value", "2.11.0",
				"\\\\cs-ccr-nfsdev\\pcrops\\dist\\japc\\japc-value\\2.11.0\\build\\dist\\japc-value.jar",
				"japc/japc-value");
		FileDescriptor ad2 = new FileDescriptor("japc", "2.22.1",
				"\\\\cs-ccr-nfsdev\\pcrops\\dist\\japc\\japc\\2.22.1\\build\\dist\\japc.jar", "japc/japc");

		List<ArtifactDescriptor> lad = new LinkedList<ArtifactDescriptor>();
		lad.add(ad1);
		lad.add(ad2);

		DependencyExtractor extractor = (DependencyExtractor) context.getBean("dependency_extractor");
		DatabaseDao db = (DatabaseDao) context.getBean("database_dao");
		db.deleteProduct(DomainFactory.creator().createProduct("japc-value", "", "",
				"\\\\cs-ccr-nfsdev\\pcrops\\dist\\japc\\japc-value\\2.11.0\\build\\dist\\japc-value.jar"));
		db.deleteProduct(DomainFactory.creator().createProduct("japc", "", "",
				"\\\\cs-ccr-nfsdev\\pcrops\\dist\\japc\\japc\\2.22.1\\build\\dist\\japc.jar"));
		// execute analysis
		extractor.executeAnalysis(lad);
	}
}
