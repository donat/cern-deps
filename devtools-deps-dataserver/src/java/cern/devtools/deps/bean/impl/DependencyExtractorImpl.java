/***********************************************************************************************************************
 * © Copyright 2013 CERN. This software is distributed under the terms of the GNU General Public Licence version 3 (GPL
 * Version 3), copied verbatim in the file "COPYING". In applying this licence, CERN does not waive the privileges and
 * immunities granted to it by virtue of its status as an Intergovernmental Organization or submit itself to any
 * jurisdiction. If you modify this Program, or any covered work, by linking or combining it with the Eclipse Integrated
 * Development Environment Plugin libraries (or a modified version of these libraries), containing parts covered by the
 * terms of EPL (Eclipse Public licence), the licensors of this Program grant you additional permission to convey the
 * resulting work. Corresponding Source for a non-source form of such a combination shall include the source code for
 * the parts of Eclipse Integrated Development Environment Plugin libraries used as well as that of the covered work.
 **********************************************************************************************************************/
package cern.devtools.deps.bean.impl;

import java.io.File;
import java.io.IOException;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import cern.devtools.deps.bean.ArtifactDescriptor;
import cern.devtools.deps.bean.DatabaseDao;
import cern.devtools.deps.bean.DatabaseException;
import cern.devtools.deps.bean.DependencyExtractor;
import cern.devtools.deps.domain.ApiClass;
import cern.devtools.deps.domain.DependencyType;
import cern.devtools.deps.domain.Field;
import cern.devtools.deps.domain.Method;
import cern.devtools.deps.domain.Modifiers;
import cern.devtools.deps.domain.Product;
import cern.devtools.deps.domain.creation.DomainObjectCreator;

/**
 * Implementation if the {@link DependencyExtractor} interface. On instantiation it stores the database reference which
 * is used to store all both the structure of the product and the dependencies in between.
 * 
 * @author Donat Csikos <dcsikos@cern.ch>
 */
public class DependencyExtractorImpl implements DependencyExtractor {

    /**
     * Logger.
     */
    private static final Logger LOG = Logger.getLogger(DependencyExtractor.class);

    @Autowired
    DomainObjectCreator creator;

    /**
     * Reference for the dependency database bean.
     */
    @Autowired
    private DatabaseDao db;

    /**
     * Simple implementation of the {@link DependencyExtractor} interface.
     * 
     * @param db Database access bean.
     */
    public DependencyExtractorImpl() {
    }

    public void executeAnalysis(List<? extends ArtifactDescriptor> artifacts) throws DatabaseException {

        Iterator<? extends ArtifactDescriptor> it = artifacts.iterator();
        while (it.hasNext()) {
            ArtifactDescriptor ad = it.next();
            // First check if the product is already in the database.
            Product p = db.findProduct(creator.createProduct(ad.getName()), false);

            // Remove if the project with the specific version is already in the database.
            if (p != null && p.getVersions().contains(ad.getVersion())) {
                it.remove();
            }
            // Also remove if the artifact does not exist.
            else if (!new File(ad.getJarPath()).exists() && ad instanceof CmmnbuildArtifactDescriptor) {
                it.remove();
            }
        }

        // 1) Analyse the new artifacts.
        findAndStoreStructures(artifacts);

        // 2) Find dependencies.
        findArtifactsDependencies(artifacts);

        try {
            db.flush("");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * Retrieves the jar file, extract it's structure and store it in the database.
     * 
     * @param ad Descriptor pointing to the jar file.
     * @throws IOException The file does not exist or not accessible.
     * @throws DatabaseException If database operation fails.
     */
    private void findAndStoreStructure(ArtifactDescriptor ad) throws IOException, DatabaseException {
        JavaStructureParser jsp = JavaStructureParser.parseDescriptor(creator, ad, false);
        Product p = jsp.getProduct();
        db.saveProduct(p);
        Measurement.getInstance().addClassesNum(p.getClasses().size());
        for (ApiClass ac : p.getClasses()) {
            Measurement.getInstance().addMethodsNum(ac.getMethods().size());
            Measurement.getInstance().addFieldsNum(ac.getFields().size());
        }
    }

    /**
     * Stores internal structure of the artifacts in the database.
     * 
     * @param artifacts
     */
    private void findAndStoreStructures(List<? extends ArtifactDescriptor> artifacts) {
        int size = artifacts.size();
        for (ArtifactDescriptor ad : artifacts) {

            try {
                findAndStoreStructure(ad);
                int cur = artifacts.indexOf(ad) + 1;
                LOG.info(cur + "/" + size + " Structure extracted: " + ad.getName() + "(" + ad.getVersion() + ")");
            } catch (Exception e) {
                LOG.warn(e);
            } finally {
                Measurement.getInstance().registerStructHeapSizeOnDiscovery();
            }
        }
    }

    /**
     * Find dependencies for specified artifact. The structure of the dependent items should already stored in the
     * database before this function is executed.
     * 
     * @param artifact The descriptor to check for dependencies.
     */
    private void findArtifactDependencies(ArtifactDescriptor artifact) {
        try {
            // parse structure.
            JavaStructureParser jsp = JavaStructureParser.parseDescriptor(creator, artifact, true);

            // temporary product
            Product tProduct = jsp.getProduct();

            // stored product
            Product storedProduct = db.findProduct(tProduct, true);

            // check, if the product was stored previously
            if (storedProduct == null) {
                LOG.warn("Error in processing - product should be stored:" + tProduct);
                return;
            }

            // cache a small cache for the stored apiclasses to access them
            // faster
            Map<String, ApiClass> storedCache = new HashMap<String, ApiClass>();
            for (ApiClass ac : storedProduct.getClasses()) {
                storedCache.put(ac.getFqName(), ac);
            }

            // go through all the classes defined in the product
            for (ApiClass acTemp : tProduct.getClasses()) {
                // assert if the class is stored and get reference of it
                ApiClass from = storedCache.get(acTemp.getFqName());
                if (from == null) {
                    LOG.warn("Error in processing - apiclass should be stored:" + acTemp);
                    return;
                }

                // look for dependencies
                findInheritanceDependencies(from);
                findClassUsageDependencies(from, acTemp);
                findMethodAndFieldDependencies(from, acTemp, artifact.getVersion());
                findOverrideDependencies(from);
            }

        } catch (Exception e) {
            e.printStackTrace();
            LOG.warn(e.getMessage());
        } finally {
            Measurement.getInstance().registerDepsHeapSizeOnDiscovery();
        }
    }

    /**
     * Finds dependencies between artifacts.
     * 
     * @param artifacts The list of artifacts where the dependencies should be discovered berween.
     */
    private void findArtifactsDependencies(List<? extends ArtifactDescriptor> artifacts) {
        Iterator<? extends ArtifactDescriptor> it = artifacts.iterator();
        int size = artifacts.size();
        while (it.hasNext()) {
            final ArtifactDescriptor ad = it.next();
            try {
                Thread t = new Thread() {
                    @Override
                    public void run() {
                        try {
                            findArtifactDependencies(ad);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    };
                };
                t.start();
                t.join();

                int cur = artifacts.indexOf(ad) + 1;
                LOG.info(cur + "/" + size + " Dependencies discovered: " + ad.getName() + "(" + ad.getVersion() + ")");

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void findClassUsageDependencies(ApiClass from, ApiClass acTemp) throws DatabaseException {
        Object metadata = acTemp.getReferencedClasses();
        if (metadata == null) {
            return;
        }

        @SuppressWarnings("unchecked")
        List<String> usedClasses = (List<String>) metadata;
        List<ApiClass> cDefCands = new LinkedList<ApiClass>();
        for (String s : usedClasses) {
            ApiClass cDef = creator.createApiClass(s, EnumSet.noneOf(Modifiers.class));
            cDefCands.add(cDef);
        }

        db.findAndSaveDependencyRelation(from, cDefCands, DependencyType.CLASS_USAGE);
    }

    private void findInheritanceDependencies(ApiClass from) throws DatabaseException {
        // find and store inheritance dependencies
        List<ApiClass> extOrImpltCands = new LinkedList<ApiClass>();
        if (from.getExtends() != null || !"".equals(from.getExtends())) {
            extOrImpltCands.add(creator.createApiClass(from.getExtends(), EnumSet.noneOf(Modifiers.class)));
        }
        if ((from.getImplements() != null) && !("".equals(from.getImplements()))) {
            String[] impls = from.getImplements().split("\\ ");
            for (String impl : impls) {
                if (!"".equals(impl)) {
                    ApiClass ac = creator.createApiClass(impl, EnumSet.noneOf(Modifiers.class));
                    extOrImpltCands.add(ac);
                }
            }
        }

        db.findAndSaveDependencyRelation(from, extOrImpltCands, DependencyType.CLASS_INHERITANCE);
    }

    private void findMethodAndFieldDependencies(ApiClass from, ApiClass acTemp, String version)
            throws DatabaseException {
        // create cache of the temporary elements
        Map<String, Method> cache = new HashMap<String, Method>();
        for (Method m : acTemp.getMethods()) {
            cache.put(m.getSignature(), m);
        }

        for (Method m : from.getMethods()) {
            if (!m.getVersions().contains(version)) {
                continue;
            }
            Method tm = cache.get(m.getSignature());

            if (tm == null) {
                LOG.warn("Error in processing - method should be stored:" + m.getSignature());
                return;
            }

            Object metadata = tm.getReferencedMethods();
            if (metadata != null) {
                List<Method> mRefCand = new LinkedList<Method>();
                @SuppressWarnings("unchecked")
                List<String> extMethods = (List<String>) metadata;
                for (String s : extMethods) {
                    Method mRef = creator.createMethod(s, EnumSet.noneOf(Modifiers.class));
                    mRefCand.add(mRef);
                }
                db.findAndSaveDependencyRelation(m, mRefCand, DependencyType.METHOD_CALL);
            }

            metadata = tm.getReferencedFields();
            if (metadata != null) {
                List<Field> fRefCand = new LinkedList<Field>();
                @SuppressWarnings("unchecked")
                List<String> extFields = (List<String>) metadata;
                for (String s : extFields) {
                    Field fRef = creator.createField(s, EnumSet.noneOf(Modifiers.class));
                    fRefCand.add(fRef);
                }
                db.findAndSaveDependencyRelation(m, fRefCand, DependencyType.FIELD_REFERENCE);

            }
        }
    }

    private void findOverrideDependencies(ApiClass from) throws DatabaseException {
        db.findAndSaveDependencyRelation(from, null, DependencyType.METHOD_OVERRIDE);
    }
}
