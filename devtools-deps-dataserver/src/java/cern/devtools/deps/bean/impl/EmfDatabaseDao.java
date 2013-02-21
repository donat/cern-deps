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

import hu.bme.incquery.deps.cp3model.Cp3modelPackage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.EcoreUtil.Copier;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.emf.ecore.xmi.impl.XMLParserPoolImpl;
import org.eclipse.emf.ecore.xmi.impl.XMLResourceImpl;
import org.springframework.expression.spel.ast.Projection;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cern.devtools.deps.bean.DatabaseDao;
import cern.devtools.deps.bean.DatabaseException;
import cern.devtools.deps.bean.DepBeanException;
import cern.devtools.deps.bean.DependencyExtractor;
import cern.devtools.deps.domain.ApiClass;
import cern.devtools.deps.domain.CodeElement;
import cern.devtools.deps.domain.Dependency;
import cern.devtools.deps.domain.DependencyType;
import cern.devtools.deps.domain.Field;
import cern.devtools.deps.domain.Method;
import cern.devtools.deps.domain.Modifiers;
import cern.devtools.deps.domain.Product;
import cern.devtools.deps.domain.creation.DomainFactory;
import cern.devtools.deps.domain.creation.DomainObjectCreator;
import cern.devtools.deps.domain.creation.impl.EmfObjectCreator;
import cern.devtools.deps.repomodel.RClass;
import cern.devtools.deps.repomodel.RCodeElement;
import cern.devtools.deps.repomodel.RDependency;
import cern.devtools.deps.repomodel.RField;
import cern.devtools.deps.repomodel.RMethod;
import cern.devtools.deps.repomodel.RProject;
import cern.devtools.deps.repomodel.RRepository;
import cern.devtools.deps.repomodel.RepomodelFactory;
import cern.devtools.deps.repomodel.RepomodelPackage;
import cern.devtools.deps.transformer.cp3.SubCp3ModelFinder;
import cern.devtools.deps.transformer.cp3.TransformRepoToCP3;
import cern.devtools.deps.transformer.cp3.TransformRepoToCP3withSaxParser;

/**
 * In-memory database dao.
 * 
 * @author Donat Csikos <dcsikos@cern.ch>
 */
@SuppressWarnings("all")
public final class EmfDatabaseDao implements DatabaseDao {
    // id generator
    private static long idSeq = 0l;

    //
    private String file = "";

    // Extension definition
    private static final String OUTPUT_FILE_EXT = "repomodel";

    // log
    private static final Logger LOG = Logger.getLogger(DependencyExtractor.class);

    // class cache
    private final Map<String, List<ApiClass>> cacheClass = new HashMap<String, List<ApiClass>>();

    // field cache
    private final Map<String, List<Field>> cacheField = new HashMap<String, List<Field>>();

    // method cache
    private final Map<String, List<Method>> cacheMethod = new HashMap<String, List<Method>>();

    // model root
    RRepository root = RepomodelFactory.eINSTANCE.createRRepository();

    private Resource repoModelRes;

    private Resource cp3Modelres;

    private EmfDatabaseDao(String repoModelLoc, String cp3ModelLoc) {
        try {
            loadRepoModelRes(repoModelLoc, cp3ModelLoc);
        } catch (IOException e) {
            throw new RuntimeException("Init emf database dao failed.", e);
        }
    }

    public static EmfDatabaseDao fromFile(String repoModelLoc, String cp3ModelLoc) throws Exception {
        EmfDatabaseDao dao = new EmfDatabaseDao(repoModelLoc, cp3ModelLoc);
        dao.fillCaches();
        return dao;
    }

    private void fillCaches() {
        // Obtain root object.
        EList<EObject> contents = repoModelRes.getContents();
        if (contents.isEmpty()) {
            return;
        } else {
            root = (RRepository) contents.get(0);
        }

        // Fill caches.
        for (Object rpo : root.getRProjects()) {
            RProject rp = (RProject) rpo;
            for (ApiClass ac : rp.getClasses()) {
                cacheApiClass(ac);
                for (Method m : ac.getMethods()) {
                    cacheMethod(m);
                }
                for (Field f : ac.getFields()) {
                    cacheField(f);
                }
            }
        }
    }

    /*
     * returns repo and cp3 models.
     */
    private void loadRepoModelRes(String repoModelLoc, String cp3ModelLoc) throws IOException {
        ResourceSet resourceSet = new ResourceSetImpl();
        resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap()
                .put("repomodel", new XMIResourceFactoryImpl());
        resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap()
                .put("cp3model", new XMIResourceFactoryImpl());
        EPackage.Registry.INSTANCE.put(RepomodelPackage.eNS_URI, RepomodelPackage.eINSTANCE);
        EPackage.Registry.INSTANCE.put(Cp3modelPackage.eNS_URI, Cp3modelPackage.eINSTANCE);

        repoModelRes = resourceSet.createResource(URI.createFileURI(repoModelLoc));
        cp3Modelres = resourceSet.createResource(URI.createFileURI(cp3ModelLoc));

        Map loadOptions = ((XMLResourceImpl) repoModelRes).getDefaultLoadOptions();
        loadOptions.put(XMLResource.OPTION_DEFER_ATTACHMENT, Boolean.TRUE);
        loadOptions.put(XMLResource.OPTION_DEFER_IDREF_RESOLUTION, Boolean.TRUE);
        loadOptions.put(XMLResource.OPTION_USE_DEPRECATED_METHODS, Boolean.TRUE);
        loadOptions.put(XMLResource.OPTION_USE_PARSER_POOL, new XMLParserPoolImpl());
        loadOptions.put(XMLResource.OPTION_USE_XML_NAME_TO_FEATURE_MAP, new HashMap());

        ((XMLResourceImpl) repoModelRes).setIntrinsicIDToEObjectMap(new HashMap<String, EObject>());
        ((XMLResourceImpl) cp3Modelres).setIntrinsicIDToEObjectMap(new HashMap<String, EObject>());

        if (!new File(repoModelLoc).exists()) {
            repoModelRes.save(Collections.EMPTY_MAP);
        }
        if (!new File(cp3ModelLoc).exists()) {
            cp3Modelres.save(Collections.EMPTY_MAP);
        }
        
        repoModelRes.load(loadOptions);
        cp3Modelres.load(loadOptions);
    }

    private void addNewVersionToProduct(Product oldProduct, Product newProduct, String version) {
        oldProduct.getVersions().add(newProduct.getVersions().get(0));

        List<ApiClass> newApiclassList = new LinkedList<ApiClass>();

        for (ApiClass nac : newProduct.getClasses()) {
            ApiClass ac = null;
            for (ApiClass oac : oldProduct.getClasses()) {
                if (oac.getFqName().equals(nac.getFqName())) {
                    ac = oac;
                    break;
                }
            }
            // the apiclass did not existed in the previous product versions
            if (ac == null) {
                newApiclassList.add(nac);
                // nac.getVersions().add(newProduct.getVersions().get(0));
                // oldProduct.getClasses().add(nac);
                for (Method m : nac.getMethods()) {
                    // m.setId(++idSeq);
                    m.getVersions().add(version);
                }
                for (Field f : nac.getFields()) {
                    // f.setId(++idSeq);
                    f.getVersions().add(version);
                }
            }

            // the apiclass existed before
            else {
                if (!ac.getVersions().contains(version)) {
                    ac.getVersions().add(version);
                }
                copyMethodsAndFields(ac, nac, newProduct.getVersions().get(0));
            }
        }

        ApiClass[] newApiClassArray = newApiclassList.toArray(new ApiClass[0]);
        for (int i = 0; i < newApiClassArray.length; ++i) {
            newApiClassArray[i].getVersions().add(newProduct.getVersions().get(0));
            oldProduct.getClasses().add(newApiClassArray[i]);
        }

    }

    private void cacheApiClass(ApiClass ac) {
        List<ApiClass> lac = cacheClass.get(ac.getFqName());
        if (lac == null) {
            lac = new LinkedList<ApiClass>();
            cacheClass.put(ac.getFqName(), lac);
        }
        lac.add(ac);
    }

    private void cacheField(Field f) {
        List<Field> lf = cacheField.get(f.getSignature());
        if (lf == null) {
            lf = new LinkedList<Field>();
            cacheField.put(f.getSignature(), lf);
        }
        lf.add(f);
    }

    private void cacheMethod(Method m) {
        // m.setId(++idSeq);
        List<Method> lm = cacheMethod.get(m.getSignature());
        if (lm == null) {
            lm = new LinkedList<Method>();
            cacheMethod.put(m.getSignature(), lm);
        }
        lm.add(m);
    }

    private void copyMethodsAndFields(ApiClass oldClass, ApiClass newClass, String version) {

        ArrayList<Method> newMethodList = new ArrayList<Method>();
        ArrayList<Field> newFieldList = new ArrayList<Field>();

        for (Method nm : newClass.getMethods()) {
            Method m = null;
            for (Method om : oldClass.getMethods()) {
                if (om.getSignature().equals(nm.getSignature())) {
                    m = om;
                    break;
                }
            }

            if (m == null) {
                newMethodList.add(nm);
            } else {
                if (!m.getVersions().contains(version)) {
                    m.getVersions().add(version);
                }
            }
        }

        Method[] newMethodsArray = newMethodList.toArray(new Method[0]);
        for (int i = 0; i < newMethodsArray.length; ++i) {
            newMethodsArray[i].getVersions().add(version);
            oldClass.getMethods().add(newMethodsArray[i]);
        }

        for (Field nf : newClass.getFields()) {
            Field f = null;
            for (Field of : oldClass.getFields()) {
                if (of.getSignature().equals(nf.getSignature())) {
                    f = of;
                    break;
                }
            }
            if (f == null) {
                newFieldList.add(nf);
            } else {
                if (!f.getVersions().contains(version)) {
                    f.getVersions().add(version);
                }
            }
        }

        Field[] newFieldsArray = newFieldList.toArray(new Field[0]);
        for (int i = 0; i < newFieldsArray.length; ++i) {
            newFieldsArray[i].getVersions().add(version);
            oldClass.getFields().add(newFieldsArray[i]);
        }
    }

    private void createNewProduct(Product newProduct) {
        // newProduct.setId(++idSeq);

        root.getRProjects().add(newProduct);
        String version = newProduct.getVersions().get(0);

        for (ApiClass ac : newProduct.getClasses()) {
            // ac.setId(++idSeq);
            ac.getVersions().add(version);
            cacheApiClass(ac);

            for (Field f : ac.getFields()) {
                // f.setId(++idSeq);
                f.getVersions().add(version);
                cacheField(f);
            }
            for (Method m : ac.getMethods()) {
                // m.setId(++idSeq);
                m.getVersions().add(version);
                cacheMethod(m);
            }
        }
    }

    public void reset() {
        idSeq = 0l;
        cacheClass.clear();
        cacheField.clear();
        cacheMethod.clear();
        root = RepomodelFactory.eINSTANCE.createRRepository();
    }

    public void deleteProduct(Product product) throws DatabaseException {
        for (Product p : (List<Product>) root.getRProjects()) {
            if (p.getName().equals(product.getName())) {
                root.getRProjects().remove(p);
                break;
            }
        }

    }

    public int findAndSaveDependencyRelation(CodeElement sDbItem, Collection<? extends CodeElement> tCand,
            DependencyType type) throws DatabaseException {
        List<CodeElement> found = new LinkedList<CodeElement>();

        switch (type) {
        case CLASS_USAGE:
            ApiClass source = (ApiClass) sDbItem;
            for (CodeElement ce : tCand) {
                ApiClass target = (ApiClass) ce;
                List<ApiClass> f = cacheClass.get(target.getFqName());
                if (f != null) {
                    found.addAll(f);
                }
            }

            for (CodeElement ce : found) {
                createDependency(source, ce, DependencyType.CLASS_USAGE);
            }

            break;
        case FIELD_REFERENCE:
            Method mSource = (Method) sDbItem;

            for (CodeElement ce : tCand) {
                Field target = (Field) ce;
                List<Field> f = cacheField.get(target.getSignature());
                if (f != null) {
                    found.addAll(f);
                }
            }

            for (CodeElement ce : found) {
                createDependency(mSource, ce, DependencyType.FIELD_REFERENCE);
            }
            break;
        case CLASS_INHERITANCE:
            source = (ApiClass) sDbItem;

            for (CodeElement ce : tCand) {
                ApiClass target = (ApiClass) ce;
                List<ApiClass> lf = cacheClass.get(target.getFqName());
                if (lf != null) {
                    for (ApiClass f : lf) {
                        if (!f.getProduct().equals(source.getProduct())) {
                            found.add(f);
                        }
                    }

                }
            }

            for (CodeElement ce : found) {
                createDependency(source, ce, DependencyType.CLASS_INHERITANCE);
            }
            break;
        case METHOD_CALL:
            mSource = (Method) sDbItem;

            for (CodeElement ce : tCand) {
                Method target = (Method) ce;
                List<Method> f = cacheMethod.get(target.getSignature());
                if (f != null) {
                    found.addAll(f);
                }
            }

            for (CodeElement ce : found) {
                createDependency(mSource, ce, DependencyType.METHOD_CALL);
            }
            break;
        case METHOD_OVERRIDE:
            ApiClass sourceClass = (ApiClass) sDbItem;

            for (Method mSource2 : sourceClass.getMethods()) {
                String extClName = mSource2.getApiClass().getExtends();
                if (extClName != null && !"".equals(extClName)) {
                    List<ApiClass> superClassCands = cacheClass.get(extClName);
                    if (superClassCands != null) {
                        for (ApiClass ac : superClassCands) {
                            // We don't care the internal dependency.
                            if (ac.getProduct().equals(mSource2.getApiClass().getProduct())) {
                                continue;
                            }
                            for (Method m : ac.getMethods()) {
                                String ma[] = m.getSignature().split("#");
                                String msa[] = mSource2.getSignature().split("#");

                                if (ma[1].equals(msa[1])) {
                                    createDependency(mSource2, m, DependencyType.METHOD_OVERRIDE);
                                }
                            }
                        }
                    }
                }
            }

            break;
        default:
            throw new RuntimeException("Not supported dependency type:" + type);
        }
        return found.size();
    }

    @SuppressWarnings("unchecked")
    private void createDependency(CodeElement dbSource, CodeElement dbTarget, DependencyType type) {
        RDependency d = (RDependency) RepomodelFactory.eINSTANCE.createRDependency();
        d.setRFrom((RCodeElement) dbSource);
        d.setRTo((RCodeElement) dbTarget);
        d.setDepType(type.value());
        root.getRDependencies().add(d);
    }

    public Product findProduct(Product product, boolean deep) throws DatabaseException {
        Product result = null;
        for (Product p : (List<Product>) root.getRProjects()) {
            if (p.getName().equals(product.getName())) {
                result = p;
                break;
            }
        }
        return result;
    }

    public void flush(String file) throws IOException {
        if (!repoModelRes.getContents().contains(root)) {
            repoModelRes.getContents().add(root);
        }
        
        repoModelRes.save(Collections.EMPTY_MAP);
        cp3Modelres.save(Collections.EMPTY_MAP);
    }

    public void saveProduct(Product newProduct) throws DatabaseException {
        clearMetadata(newProduct);

        Product old = null;
        for (Product p : (List<Product>) root.getRProjects()) {
            if (p.getName().equals(newProduct.getName())) {
                old = p;
                break;
            }
        }

        if (old == null) {
            createNewProduct(newProduct);
        } else {
            addNewVersionToProduct(old, newProduct, newProduct.getVersions().get(0));
        }
    }

    private void clearMetadata(Product product) {
        for (ApiClass ac : product.getClasses()) {
            ac.getReferencedClasses().clear();
            for (Method m : ac.getMethods()) {
                m.getReferencedFields().clear();
                m.getReferencedMethods().clear();
            }
        }

    }

    /**
     * Testing purposes: prints out the products' structure to the stdout.
     */
    @SuppressWarnings("unchecked")
    public void printStructure() {
        System.out.println("Structure");
        int productNum = 0;
        int classNum = 0;
        int methodNum = 0;
        int fieldNUm = 0;

        for (Product p : (List<Product>) root.getRProjects()) {
            productNum++;
            System.out.println(">>>product: " + p.getName() + "<<<");
            for (ApiClass ac : p.getClasses()) {
                classNum++;
                System.out.println("Class " + ac.getFqName());
                for (Field f : ac.getFields()) {
                    fieldNUm++;
                    System.out.println("  field: " + f.getSignature());
                }
                for (Method m : ac.getMethods()) {
                    methodNum++;
                    System.out.println("  method: " + m.getSignature());
                }
            }
        }
        System.out.println(productNum);
        System.out.println(classNum);
        System.out.println(methodNum);
        System.out.println(fieldNUm);
    }

    /**
     * Testing purposes: prints out the dependencies to the stdout.
     */
    public void printDeps() {
        for (Object dObj : root.getRDependencies()) {
            Dependency d = (Dependency) dObj;
            String from = null;
            String to = null;
            if (d.getType().equals(DependencyType.CLASS_INHERITANCE)) {
                from = ((ApiClass) d.getFrom()).getFqName();
                to = ((ApiClass) d.getTo()).getFqName();
            } else if (d.getType().equals(DependencyType.METHOD_CALL)) {
                from = ((Method) d.getFrom()).getSignature();
                to = ((Method) d.getTo()).getSignature();
            } else if (d.getType().equals(DependencyType.FIELD_REFERENCE)) {
                from = ((Method) d.getFrom()).getSignature();
                to = ((Field) d.getTo()).getSignature();
            } else if (d.getType().equals(DependencyType.CLASS_USAGE)) {
                from = ((ApiClass) d.getFrom()).getFqName();
                to = ((ApiClass) d.getTo()).getFqName();
            } else if (d.getType().equals(DependencyType.METHOD_OVERRIDE)) {
                from = ((Method) d.getFrom()).getSignature();
                to = ((Method) d.getTo()).getSignature();
            }
            System.out.print(d.getType().toString().substring(0, 11) + " ");
            System.out.print(from);
            System.out.print(" ----> ");
            System.out.println(to);
        }
        for (int i = 0; i < root.getRDependencies().size(); ++i) {

        }
    }

    public int getDepsNum() {
        return root.getRDependencies().size();
    }

    public Collection<cern.devtools.deps.domain.Dependency> findClassDependencies(ApiClass element) {
        // find container
        Product p = element.getProduct();

        // results
        Collection<Dependency> result = new LinkedList<Dependency>();

        // find class
        EList projects = root.getRProjects();
        for (Object dbProjectObject : projects) {
            RProject dbProject = (RProject) dbProjectObject;

            if (dbProject.getName().equals(p.getName())) {
                for (ApiClass dbAc : dbProject.getClasses()) {
                    if (element.getFqName().equals(dbAc.getFqName())) {
                        addIncomingDeps((RClass) dbAc, result);
                        return result;
                    }
                }
            }
        }

        return result;
    }

    private static void addIncomingDeps(RCodeElement to, Collection<Dependency> resultHolder) {
        Collection<Dependency> result = new LinkedList<Dependency>();
        Map<String, RProject> projects = new HashMap<String, RProject>();
        Map<String, RClass> classes = new HashMap<String, RClass>();
        Map<String, RMethod> methods = new HashMap<String, RMethod>();

        for (Object incDepObject : to.getRIncoming()) {
            RDependency incDep = (RDependency) incDepObject;
            RCodeElement from = (RCodeElement) incDep.getFrom();

            RClass fromClass = null;
            RMethod fromMethod = null;
            RProject fromProject = null;

            if (RepomodelPackage.eINSTANCE.getRMethod().isSuperTypeOf(from.eClass())) {
                fromMethod = (RMethod) from;
                fromClass = (RClass) fromMethod.getApiClass();
                fromProject = (RProject) fromClass.getProduct();
            } else if (RepomodelPackage.eINSTANCE.getRClass().isSuperTypeOf(from.eClass())) {
                fromClass = (RClass) from;
                fromProject = (RProject) fromClass.getProduct();
            } else {
                throw new RuntimeException("Unsupported type: " + from.eClass());
            }

            RProject rp = projects.get(fromProject.getName());
            if (rp == null) {
                rp = (RProject) copyAttributes(fromProject);
                projects.put(fromProject.getName(), rp);
            }

            RClass rc = classes.get(fromClass.getFqName());
            if (rc == null) {
                rc = (RClass) copyAttributes(fromClass);
                classes.put(fromClass.getFqName(), rc);
            }

            RMethod rm = null;
            if (fromMethod != null) {
                rm = methods.get(fromMethod.getSignature());
                if (rm == null) {
                    rm = (RMethod) copyAttributes(fromMethod);
                    methods.put(fromMethod.getSignature(), rm);
                }
            }

            // connect elements
            if (!rp.getClasses().contains(rc)) {
                rp.getClasses().add(rc);
                rc.setProduct(rp);

            }

            if (rm != null && !rc.getMethods().contains(rm)) {
                rc.getMethods().add(rm);
                rm.setApiClass(rc);
            }

            // create dependency
            DomainObjectCreator creator = new EmfObjectCreator();
            if (RepomodelPackage.eINSTANCE.getRMethod().isSuperTypeOf(from.eClass())) {
                RMethod rmf = methods.get(fromMethod.getSignature());
                Dependency dep = creator.createDependency(incDep.getType(), methods.get(fromMethod.getSignature()),
                        null);
                result.add(dep);

            } else if (RepomodelPackage.eINSTANCE.getRClass().isSuperTypeOf(from.eClass())) {
                RClass rcf = classes.get(fromClass.getFqName());
                Dependency dep = creator.createDependency(incDep.getType(), classes.get(fromClass.getFqName()), null);
                result.add(dep);
            }

        }

        resultHolder.addAll(result);
    }

    public static void main(String[] args) {
        DomainFactory.setDomainObjectCreator(new EmfObjectCreator());
        RProject p1 = (RProject) DomainFactory.creator().createProduct("p1");
        RClass c1 = (RClass) DomainFactory.creator().createApiClass("ApiClass", EnumSet.noneOf(Modifiers.class));
        p1.getClasses().add(c1);
        System.out.println(((RProject) copyAttributes(p1)).getClasses());
    }

    public static EObject copyAttributes(EObject source) {
        Copier copier = new Copier() {
            @Override
            protected void copyContainment(EReference eReference, EObject eObject, EObject copyEObject) {
                // We need just to copy the attributes
            }

            @Override
            protected void copyReference(EReference eReference, EObject eObject, EObject copyEObject) {
                // We need just to copy the attributes
            }
        };

        return copier.copy(source);
    }

    public Collection<cern.devtools.deps.domain.Dependency> findMethodDependencies(Method element) {
        // find containers
        ApiClass ac = element.getApiClass();
        Product p = ac.getProduct();

        // results
        Collection<Dependency> result = new LinkedList<Dependency>();

        // find class
        EList projects = root.getRProjects();
        for (Object dbProjectObject : projects) {
            RProject dbProject = (RProject) dbProjectObject;

            if (dbProject.getName().equals(p.getName())) {
                for (ApiClass dbAc : dbProject.getClasses()) {
                    if (ac.getFqName().equals(dbAc.getFqName())) {

                        for (Method dbMethod : dbAc.getMethods()) {
                            if (dbMethod.getSignature().equals(element.getSignature())) {
                                addIncomingDeps((RMethod) dbMethod, result);
                                return result;
                            }
                        }
                    }
                }
            }
        }

        return result;
    }

    public Collection<cern.devtools.deps.domain.Dependency> findFieldDependencies(Field element) {
        // find containers
        ApiClass ac = element.getApiClass();
        Product p = ac.getProduct();

        // results
        Collection<Dependency> result = new LinkedList<Dependency>();

        // find class
        EList projects = root.getRProjects();
        for (Object dbProjectObject : projects) {
            RProject dbProject = (RProject) dbProjectObject;

            if (dbProject.getName().equals(p.getName())) {
                for (ApiClass dbAc : dbProject.getClasses()) {
                    if (ac.equals(dbAc.getFqName())) {
                        for (Field dbField : dbAc.getFields()) {
                            if (dbField.getSignature().equals(element.getSignature())) {
                                addIncomingDeps((RField) dbField, result);
                                return result;
                            }
                        }
                    }
                }
            }
        }

        return result;
    }

    public void updateProduct(Product product) throws DatabaseException {
        // TODO
    }

    public Collection<Dependency> findProductDependencies(Product element) throws DatabaseException {
        throw new UnsupportedOperationException("Auto-generated method stub; not implemented yet.");
    }

    public long getNumOfDeps() {
        return root.getRDependencies().size();
    }

    // TODO: add it again when possible.
    // final SubCp3ModelFinder sc;

    public Object findCp3ModelForDirectDeps(List<String> projects) {
        SubModelFinder finder = new SubModelFinder(repoModelRes, cp3Modelres);
        return finder.findSubModel(projects);
    }

    private void addProjectsToResource(Set<RProject> projects, Resource targetResource) {
        RRepository root = RepomodelFactory.eINSTANCE.createRRepository();

        root.getRProjects().addAll(projects);
        for (RProject rp : projects) {
            for (Object rco : rp.getClasses()) {
                RClass rc = (RClass) rco;
                root.getRDependencies().addAll(rc.getRIncoming());
                root.getRDependencies().addAll(rc.getROutgoing());
                // for (Object rmo : rc.getMethods()) {
                // RMethod rm = (RMethod) rmo;
                // root.getRDependencies().addAll(rm.getRIncoming());
                // root.getRDependencies().addAll(rm.getROutgoing());
                // }
                //
                // for (Object rfo : rc.getFields()) {
                // RField rf = (RField) rfo;
                // root.getRDependencies().addAll(rf.getRIncoming());
                // root.getRDependencies().addAll(rf.getROutgoing());
                // }
            }
        }

        targetResource.getContents().add(root);
    }

    private Set<RProject> findProjectsToRetrieve(List<String> projects) {
        Set<RProject> foundProjects = new HashSet<RProject>();

        for (Object po : root.getRProjects()) {
            Product p = (RProject) po;
            if (projects.contains(p.getName())) {
                foundProjects.add((RProject) p);
                for (ApiClass ac : p.getClasses()) {
                    addContainerProjectsToSet(((RClass) ac).getRIncoming(), foundProjects);

                    for (Method m : ac.getMethods()) {
                        addContainerProjectsToSet(((RMethod) m).getRIncoming(), foundProjects);
                    }

                    for (Field f : ac.getFields()) {
                        addContainerProjectsToSet(((RField) f).getRIncoming(), foundProjects);
                    }
                }
            }
        }
        return foundProjects;
    }

    private void addContainerProjectsToSet(EList rIncoming, Set<RProject> foundProjects) {
        for (Object inc : rIncoming) {
            RDependency dep = (RDependency) inc;
            CodeElement from = dep.getFrom();
            if (from instanceof RClass) {
                RClass ic = (RClass) from;
                foundProjects.add(ic.getProduct());
            } else if (from instanceof RMethod) {
                RMethod im = (RMethod) from;
                foundProjects.add((RProject) im.getApiClass().getProduct());
            } else
                throw new RuntimeException("Wrong type as incoming dep:" + inc.getClass());
        }

    }

}
