package cern.devtools.deps.bean;

import java.util.LinkedList;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import cern.devtools.deps.bean.ArtifactDescriptor;
import cern.devtools.deps.bean.DependencyExtractor;
import cern.devtools.deps.bean.impl.EmfDatabaseDao;
import cern.devtools.deps.bean.impl.CmmnbuildArtifactDescriptor;

public class DependencyExtractorTest extends AbstractBeanTest {

    @Test
    @Ignore
    public void test() throws Exception {

        CmmnbuildArtifactDescriptor ad1 = new CmmnbuildArtifactDescriptor("japc-value", "2.11.0",
                "\\\\cs-ccr-nfsdev\\pcrops\\dist\\japc\\japc-value\\2.11.0\\build\\dist\\japc-value.jar", "japc");

        CmmnbuildArtifactDescriptor ad2 = new CmmnbuildArtifactDescriptor("japc", "2.19.0",
                "\\\\cs-ccr-nfsdev\\pcrops\\dist\\japc\\japc\\2.19.0\\build\\dist\\japc.jar", "japc");

        CmmnbuildArtifactDescriptor ad3 = new CmmnbuildArtifactDescriptor(
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

        CmmnbuildArtifactDescriptor ad1 = new CmmnbuildArtifactDescriptor("example-service", "1.0.0",
                "c:/opt/workspace/eclipse/devtools-deps-example-service/dist/devtools-deps-example-service.jar",
                "/dev/null");
        CmmnbuildArtifactDescriptor ad2 = new CmmnbuildArtifactDescriptor("example-impl", "1.0.0",
                "c:/opt/workspace/eclipse/devtools-deps-example-impl/dist/devtools-deps-example-impl.jar", "/dev/null");
        CmmnbuildArtifactDescriptor ad3 = new CmmnbuildArtifactDescriptor("example-impl", "2.0.0",
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
