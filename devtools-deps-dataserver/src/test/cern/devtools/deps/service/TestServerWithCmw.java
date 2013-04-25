package cern.devtools.deps.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import cern.devtools.deps.bean.ArtifactDescriptor;
import cern.devtools.deps.bean.DatabaseDao;
import cern.devtools.deps.bean.DependencyExtractor;
import cern.devtools.deps.bean.impl.CmmnbuildArtifactDescriptor;
import cern.devtools.deps.domain.creation.DomainFactory;

public class TestServerWithCmw {
	public static void main(String[] args) throws Exception {

		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"classpath:/config/ctx-test-service.xml");

		CmmnbuildArtifactDescriptor ad1 = new CmmnbuildArtifactDescriptor("cmw-rda", "2.9.7",
				"\\\\cs-ccr-nfsdev\\pcrops\\dist\\cmw\\cmw-rda\\2.9.7\\build\\dist\\cmw-rda.jar", "cmw/cmw-rda");
		CmmnbuildArtifactDescriptor ad2 = new CmmnbuildArtifactDescriptor("japc-ext-cmwrda", "2.13.0",
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
