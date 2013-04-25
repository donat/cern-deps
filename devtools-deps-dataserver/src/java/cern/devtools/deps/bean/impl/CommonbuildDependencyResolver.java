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

package cern.devtools.deps.bean.impl;

import java.util.List;

import cern.devtools.deps.bean.ArtifactDescriptor;

public class CommonbuildDependencyResolver {

    private final List<? extends ArtifactDescriptor> artifacts;

    public CommonbuildDependencyResolver(List<? extends ArtifactDescriptor> artifacts) {
        this.artifacts = artifacts;
    }

    public void resolve() {
        for (ArtifactDescriptor d : artifacts) {
            if (d instanceof CmmnbuildArtifactDescriptor) {
                CmmnbuildArtifactDescriptor cd = ((CmmnbuildArtifactDescriptor) d);
                List<ArtifactDescriptor> refs = cd.getReferencedArtifacts();

                for ()
            }
        }
    }
}
