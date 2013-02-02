/*******************************************************************************
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
 ******************************************************************************/
package cern.devtools.depanalysis.emftest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

import cern.devtools.depanalysis.domain.Dependency;
import cern.devtools.depanalysis.repomodel.RClass;
import cern.devtools.depanalysis.repomodel.RCodeElement;
import cern.devtools.depanalysis.repomodel.RDependency;
import cern.devtools.depanalysis.repomodel.RField;
import cern.devtools.depanalysis.repomodel.RMethod;
import cern.devtools.depanalysis.repomodel.RProject;
import cern.devtools.depanalysis.repomodel.RRepository;
import cern.devtools.depanalysis.repomodel.RepomodelFactory;
import cern.devtools.depanalysis.repomodel.RepomodelPackage;

@SuppressWarnings("all")
public class DummyModelGeneration {
	public static final int VERSION_NUM = 10;
	public static final int PROJECT_NUM = 1000;
	public static final int CLASS_NUM = 100;
	public static final int METHOD_NUM = 20;
	public static final int FIELD_NUM = 2;
	
	public static final int DEP_NUM = 10000;
	
	public static final List<RCodeElement> elems = new ArrayList<RCodeElement>();

	public static void main(String[] args) throws IOException, InterruptedException {

		EPackage.Registry.INSTANCE.put(RepomodelPackage.eNS_URI, RepomodelPackage.eINSTANCE);

		ResourceSet resourceSet = new ResourceSetImpl();
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap()
				.put("repomodel", new XMIResourceFactoryImpl());
		;
		Resource resource = resourceSet.createResource(URI.createFileURI("dummy.repomodel"));

		resource.getContents().add(getDummyContents());
		System.out.println("started");
		resource.save(null);
		System.out.println("finished");
	
		
	}

	private static RRepository getDummyContents() {
		RRepository repo = RepomodelFactory.eINSTANCE.createRRepository();
		createProducts(repo);
		createDeps(repo);
		return repo;

	}

	private static void createDeps(RRepository repo ) {
		Random r = new Random();
		r.setSeed(434534534l);
		for (int i = 0; i < DEP_NUM; i++) {
			RDependency d = RepomodelFactory.eINSTANCE.createRDependency();
			d.setDepType(1);
			d.setRFrom(elems.get(r.nextInt(elems.size())));
			d.setRTo(elems.get(r.nextInt(elems.size())));
			repo.getRDependencies().add(d);
		}
		
	}

	private static void createProducts(RRepository repo) {
		for (int i = 0; i < PROJECT_NUM; i++) {
			RProject p = RepomodelFactory.eINSTANCE.createRProject();
			addVersionsAndCache(p);

			p.setName("p-" + i);
			repo.getRProjects().add(p);
			createClasses(p);
		}
	}

	private static void createClasses(RProject p) {
		for (int i = 0; i < CLASS_NUM; i++) {
			RClass cls = RepomodelFactory.eINSTANCE.createRClass();
			addVersionsAndCache(cls);

			cls.setFqName("cls-" + i);
			p.getClasses().add(cls);
			createMethods(cls);
			createFields(cls);

		}

	}

	private static void createMethods(RClass cls) {
		for (int i = 0; i < METHOD_NUM; i++) {
			RMethod m = RepomodelFactory.eINSTANCE.createRMethod();
			addVersionsAndCache(m);

			m.setSignature("m-" + i + "()");
			cls.getMethods().add(m);
		}
	}

	private static void createFields(RClass cls) {
		for (int i = 0; i < FIELD_NUM; i++) {
			RField f = RepomodelFactory.eINSTANCE.createRField();
			addVersionsAndCache(f);

			f.setSignature("f-" + i);
			cls.getFields().add(f);
		}
	}

	private static void addVersionsAndCache(RCodeElement p) {
		elems.add(p);
		
		for (int i = 1; i <= VERSION_NUM; i++) {
			p.getVersions().add(i + ".0.0");
		}
	}

}
