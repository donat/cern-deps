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
package cern.devtools.depanalysis.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import cern.devtools.depanalysis.bean.ArtifactDescriptor;
import cern.devtools.depanalysis.bean.DatabaseDao;
import cern.devtools.depanalysis.bean.DependencyExtractor;
import cern.devtools.depanalysis.bean.impl.FileDescriptor;
import cern.devtools.depanalysis.domain.creation.DomainFactory;

public class TestServerWithCmw {
	public static void main(String[] args) throws Exception {

		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"classpath:/config/ctx-test-service.xml");

		FileDescriptor ad1 = new FileDescriptor("cmw-rda", "2.9.7",
				"\\\\cs-ccr-nfsdev\\pcrops\\dist\\cmw\\cmw-rda\\2.9.7\\build\\dist\\cmw-rda.jar", "cmw/cmw-rda");
		FileDescriptor ad2 = new FileDescriptor("japc-ext-cmwrda", "2.13.0",
				"\\\\cs-ccr-nfsdev\\pcrops\\dist\\japc\\japc-ext-cmwrda\\2.13.0\\build\\dist\\japc-ext-cmwrda.jar",
				"japc/japc-ext-cmwrda");

		List<ArtifactDescriptor> lad = new LinkedList<ArtifactDescriptor>();
		lad.add(ad1);
		lad.add(ad2);

		DependencyExtractor extractor = (DependencyExtractor) context.getBean("dependency_extractor");
		DatabaseDao db = (DatabaseDao) context.getBean("database_dao");
		db.deleteProduct(DomainFactory.creator().createProduct("", "", "",
				"\\\\cs-ccr-nfsdev\\pcrops\\dist\\cmw\\japc-rda\\2.9.7\\build\\dist\\cmw-rda.jar"));
		db.deleteProduct(DomainFactory.creator().createProduct("", "", "",
				"\\\\cs-ccr-nfsdev\\pcrops\\dist\\japc\\japc-ext-cmwrda\\2.13.0\\build\\dist\\japc-ext-cmwrda.jar"));
		// execute analysis
		extractor.executeAnalysis(lad);
	}
}
