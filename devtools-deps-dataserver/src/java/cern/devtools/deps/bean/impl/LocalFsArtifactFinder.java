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
package cern.devtools.deps.bean.impl;

import java.io.File;
import java.io.FileFilter;
import java.util.LinkedList;
import java.util.List;

import cern.devtools.deps.bean.ArtifactDescriptor;
import cern.devtools.deps.bean.ArtifactFinder;
import cern.devtools.deps.bean.DepBeanException;

public class LocalFsArtifactFinder implements ArtifactFinder {

    final File dir;

    public LocalFsArtifactFinder(String containerDir) {
        dir = new File(containerDir);
        if (!dir.exists()) {
            throw new RuntimeException("Directory does not exist: " + containerDir);
        } else if (!dir.isDirectory()) {
            throw new RuntimeException("Not a directory: " + containerDir);
        }
    }

    public List<? extends ArtifactDescriptor> findArtifacts() throws DepBeanException {
        List<ArtifactDescriptor> foundJars = new LinkedList<ArtifactDescriptor>();
        
        File[] jars = dir.listFiles(new FileFilter() {
            public boolean accept(File pathname) {
                return pathname.getName().endsWith("jar");
            }
        });
        
        for (File jar : jars) {
            String name = jar.getName().substring(0, jar.getName().length()-4);
            String version = "1.0.0";
            String containingFolders = dir.getAbsolutePath();
            CmmnbuildArtifactDescriptor jarDesc = new CmmnbuildArtifactDescriptor(name, version, jar.getAbsolutePath(), containingFolders);
            foundJars.add(jarDesc);
        }

        return foundJars;

    }

}
