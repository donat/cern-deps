package cern.devtools.deps.service;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import cern.devtools.deps.bean.ArtifactFinder;
import cern.devtools.deps.bean.DepBeanException;
import cern.devtools.deps.bean.DependencyExtractor;

public class TestServerWithAllProJars {
    public static void main(String[] args) throws DepBeanException {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                "classpath:/config/ctx-test-service-oracledev.xml");
        ArtifactFinder finder = (ArtifactFinder) context.getBean("artifact_finder");
        DependencyExtractor extractor = (DependencyExtractor) context.getBean("dependency_extractor");
        // execute analysis
        extractor.executeAnalysis(finder.findArtifacts());
    }
}
