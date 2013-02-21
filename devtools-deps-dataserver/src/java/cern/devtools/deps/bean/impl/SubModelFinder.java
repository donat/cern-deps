/**
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

import org.eclipse.emf.ecore.resource.Resource;

import cern.devtools.deps.transformer.cp3.SubCp3ModelFinder;
import cern.devtools.deps.transformer.cp3.TransformRepoToCP3withSaxParser;

public class SubModelFinder {

    private final Resource repoModelResource;
    private final Resource cp3ModelResource;

    public SubModelFinder(Resource repoModelResource, Resource cp3ModelResource) {
        this.repoModelResource = repoModelResource;
        this.cp3ModelResource = cp3ModelResource;
    }

    public Object findSubModel(List<String> projects) {
        try {
            //  update the cp3 model
            TransformRepoToCP3withSaxParser.transform(repoModelResource.getURI().toFileString(), cp3ModelResource
                    .getURI().toFileString());
            
            // Get the shrieked model
            SubCp3ModelFinder submodelfinder = new SubCp3ModelFinder(cp3ModelResource
                    .getURI().toFileString());
            return submodelfinder.findSubModel(projects);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
