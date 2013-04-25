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

package cern.devtools.deps.emftest;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

import cern.devtools.deps.repomodel.RField;
import cern.devtools.deps.repomodel.RProject;
import cern.devtools.deps.repomodel.RRepository;
import cern.devtools.deps.repomodel.RepomodelFactory;

public class ContainerSlicingRepoModelStorage {

    private File location;
    private ResourceSet resourceSet;
    private Map<String, Resource> resources;
    private static final String RESOURCE_EXT = ".repomodel";

    public ContainerSlicingRepoModelStorage(String locationFolder) {
        location = new File(locationFolder);
        if (!location.isDirectory() || !location.exists()) {
            throw new RuntimeException(String.format("The location %s specified is not a valid folder", locationFolder));
        }

        resourceSet = new ResourceSetImpl();
        resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap()
                .put("repomodel", new XMIResourceFactoryImpl());
        resources = new HashMap<String, Resource>();
    }

    public void addRRepositoryItem(EObject eo) {
        String resourceName = constructResourceNameForObject(eo);
        Resource resource = getOrCreateResource(resourceName);
        resource.getContents().add(eo);
    }

    public void saveAll() throws IOException {
        for (Resource r : resources.values()) {
            r.save(Collections.EMPTY_MAP);
        }
    }

    private Resource getOrCreateResource(String resourceName) {
        Resource result = resources.get(resourceName);
        if (result == null) {
            result = resourceSet.createResource(URI.createFileURI(location.getAbsolutePath() + File.separator
                    + resourceName + RESOURCE_EXT));
            resources.put(resourceName, result);
        }
        return result;
    }

    private String constructResourceNameForObject(EObject eo) {
        String result = "";
        if (eo.eContainer() != null) {
            result += constructResourceNameForObject(eo.eContainer()) + "_";
        }
        result += eo.eClass().getName();

        return result;
    }

    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws IOException {
        ContainerSlicingRepoModelStorage s = new ContainerSlicingRepoModelStorage("./tmpstorage");
        RRepository repo = RepomodelFactory.eINSTANCE.createRRepository();
        RProject project = RepomodelFactory.eINSTANCE.createRProject();
        repo.getRProjects().add(project);
        System.out.println(project.eContainer());

        s.addRRepositoryItem(repo);
        s.addRRepositoryItem(project);
        s.saveAll();
    }
}
