/*
 * © Copyright 2011 CERN. This software is distributed under the terms of the GNU General Public Licence version 3 (GPL
 * Version 3), copied verbatim in the file “COPYING”. In applying this licence, CERN does not waive the privileges and
 * immunities granted to it by virtue of its status as an Intergovernmental Organization or submit itself to any
 * jurisdiction.
 * 
 * If you modify this Program, or any covered work, by linking or combining it with the Eclipse Integrated Development
 * Environment Plugin libraries (or a modified version of these libraries), containing parts covered by the terms of EPL
 * (Eclipse Public licence), the licensors of this Program grant you additional permission to convey the resulting work.
 * Corresponding Source for a non-source form of such a combination shall include the source code for the parts of
 * Eclipse Integrated Development Environment Plugin libraries used as well as that of the covered work.
 */

package cern.devtools.deps.bean;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import cern.devtools.deps.bean.impl.ArtifactFinderImpl;
import cern.devtools.deps.bean.impl.CommonbuildDependencyResolver;
import cern.devtools.deps.bean.impl.DependencyExtractorImpl;

public class ArtifactFinderTestWithRealRepoXml {
    
    ArtifactFinder finder;
    DependencyExtractorImpl extractor;
    
    @Before
    public void before() {
        finder = new ArtifactFinderImpl(false, false);
        extractor = new DependencyExtractorImpl();
    }
    
    @Test
    public void listAllArtifacts() throws DepBeanException {
        List<? extends ArtifactDescriptor> artifacts = finder.findArtifacts();
        CommonbuildDependencyResolver resolver = new CommonbuildDependencyResolver(artifacts);
        resolver.resolve();
    }
}
