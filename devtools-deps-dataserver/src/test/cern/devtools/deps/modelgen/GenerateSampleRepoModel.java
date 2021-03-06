package cern.devtools.deps.modelgen;

import java.util.LinkedList;
import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import cern.devtools.deps.bean.ArtifactDescriptor;
import cern.devtools.deps.bean.DependencyExtractor;
import cern.devtools.deps.bean.impl.EmfDatabaseDao;
import cern.devtools.deps.bean.impl.CmmnbuildArtifactDescriptor;

public class GenerateSampleRepoModel {
	public static void main(String[] args) throws Exception {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"classpath:/config/ctx-test-discovery-emf.xml");
		
		
		CmmnbuildArtifactDescriptor ad1 = new CmmnbuildArtifactDescriptor("japc", "1.0.0",
				"sampleprojects/bin/japc.jar", "japc/japc");
		CmmnbuildArtifactDescriptor ad2 = new CmmnbuildArtifactDescriptor("accsoft-gui-alarms", "1.0.0",
				"sampleprojects/bin/accsoft-gui-alarms.jar", "accsoft/gui/accsoft-gui-alarms");
		CmmnbuildArtifactDescriptor ad3 = new CmmnbuildArtifactDescriptor("japc-ext-cmw", "1.0.0",
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
