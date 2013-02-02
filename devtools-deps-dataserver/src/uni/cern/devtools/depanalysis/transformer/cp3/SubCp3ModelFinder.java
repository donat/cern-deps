package cern.devtools.depanalysis.transformer.cp3;

import hu.bme.incquery.deps.cp3model.CP3AbstractItem;
import hu.bme.incquery.deps.cp3model.CP3Class;
import hu.bme.incquery.deps.cp3model.CP3Dep;
import hu.bme.incquery.deps.cp3model.CP3Field;
import hu.bme.incquery.deps.cp3model.CP3Method;
import hu.bme.incquery.deps.cp3model.CP3Project;
import hu.bme.incquery.deps.cp3model.CP3Repo;
import hu.bme.incquery.deps.cp3model.Cp3modelFactory;
import hu.bme.incquery.deps.cp3model.Cp3modelPackage;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil.Copier;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.emf.ecore.xmi.impl.XMLParserPoolImpl;
import org.eclipse.emf.ecore.xmi.impl.XMLResourceImpl;

public class SubCp3ModelFinder {

	private Resource inResource;
	private Resource outResource;
	private String cp3modelLoc;
	private ResourceSet resourceSet;

	public SubCp3ModelFinder(String cp3modelLoc) {
		this.cp3modelLoc = cp3modelLoc;
	}

	public void modelChanged() {
		try {
			loadCp3Model();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private String findSubmodel(List<String> projects) {
		Set<String> found = new HashSet<String>();
		found.addAll(projects);

		CP3Repo repo = (CP3Repo) inResource.getContents().get(0);
		for (CP3Dep d : repo.getDeps()) {
			// search only for class usage deps; it covers all the others
			if (d.getType() == 4) {
				CP3Class to = (CP3Class) d.getTo();
				CP3Class from = (CP3Class) d.getFrom();

				for (CP3Project toProject : to.getProjects()) {
					if (projects.contains(toProject.getName())) {

						for (CP3Project fromProject : from.getProjects()) {
							found.add(fromProject.getName());
						}
						break;
					}
				}
			}
		}

		Set<EObject> resultItems = new HashSet<EObject>();
		// Set<CP3Dep> resultDeps = new HashSet<CP3Dep>();
		// build a cache of the model
		for (CP3AbstractItem item : repo.getItems()) {
			if (item instanceof CP3Project) {
				CP3Project p = (CP3Project) item;

				if (found.contains(p.getName())) {
					resultItems.add(p);

					for (CP3Class c : p.getClasses()) {
						resultItems.add(c);

						for (CP3Method m : c.getMethods()) {
							resultItems.add(m);
						}

						for (CP3Field f : c.getFields()) {
							resultItems.add(f);
						}
					}
				}
			}
		}

		for (CP3Dep d : repo.getDeps()) {
			if (resultItems.contains(d.getFrom()) && resultItems.contains(d.getTo())) {
				resultItems.add(d);
			}
		}

		Copier copier = new Copier();
		CP3Repo subRepo = Cp3modelFactory.eINSTANCE.createCP3Repo();
		Collection<EObject> resultCopies = copier.copyAll(resultItems);
		copier.copyReferences();

		for (EObject o : resultCopies) {
			if (o instanceof CP3AbstractItem) {
				subRepo.getItems().add((CP3AbstractItem) o);
			}
			if (o instanceof CP3Dep) {
				subRepo.getDeps().add((CP3Dep) o);
			}

		}

		try {
			Resource out = getNextResource();
			out.getContents().add(subRepo);
			out.save(saveOptions(out));

			return new File(out.getURI().toFileString()).getAbsolutePath();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public Object findSubModel(List<String> projects) {
		try {
			if (inResource == null) {
				loadCp3Model();
			}
			return findSubmodel(projects);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private void loadCp3Model() throws IOException {
		resourceSet = new ResourceSetImpl();
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap()
				.put("cp3model", new XMIResourceFactoryImpl());
		EPackage.Registry.INSTANCE.put(Cp3modelPackage.eNS_URI, Cp3modelPackage.eINSTANCE);

		inResource = resourceSet.createResource(org.eclipse.emf.common.util.URI.createFileURI(cp3modelLoc));

		inResource.load(loadOptions(inResource));
	}

	int i = 0;

	private Resource getNextResource() throws IOException {
		if (outResource != null) {
			outResource.delete(Collections.EMPTY_MAP);
		}

		outResource = resourceSet.createResource(org.eclipse.emf.common.util.URI.createFileURI("submodel_" + (++i)
				+ ".cp3model"));
		((XMLResourceImpl) outResource).setIntrinsicIDToEObjectMap(new HashMap<String, EObject>());

		return outResource;
	}

	@SuppressWarnings("all")
	private static Map loadOptions(Resource resource) {
		Map loadOptions = ((XMLResourceImpl) resource).getDefaultLoadOptions();
		((XMLResourceImpl) resource).setIntrinsicIDToEObjectMap(new HashMap<String, EObject>());
		loadOptions.put(XMLResource.OPTION_DEFER_ATTACHMENT, Boolean.TRUE);
		loadOptions.put(XMLResource.OPTION_DEFER_IDREF_RESOLUTION, Boolean.TRUE);
		loadOptions.put(XMLResource.OPTION_USE_DEPRECATED_METHODS, Boolean.TRUE);
		loadOptions.put(XMLResource.OPTION_USE_PARSER_POOL, new XMLParserPoolImpl());
		loadOptions.put(XMLResource.OPTION_USE_XML_NAME_TO_FEATURE_MAP, new HashMap());

		return loadOptions;
	}

	@SuppressWarnings("all")
	private static Map saveOptions(Resource resource) {
		Map saveOptions = ((XMLResourceImpl) resource).getDefaultSaveOptions();
		saveOptions.put(XMLResource.OPTION_DEFER_ATTACHMENT, Boolean.TRUE);
		saveOptions.put(XMLResource.OPTION_DEFER_IDREF_RESOLUTION, Boolean.TRUE);
		saveOptions.put(XMLResource.OPTION_USE_DEPRECATED_METHODS, Boolean.TRUE);
		saveOptions.put(XMLResource.OPTION_USE_PARSER_POOL, new XMLParserPoolImpl());
		saveOptions.put(XMLResource.OPTION_USE_XML_NAME_TO_FEATURE_MAP, new HashMap());
		saveOptions.put(XMLResource.OPTION_USE_FILE_BUFFER, true);
		return saveOptions;
	}

	public static void main(String[] args) throws Exception {
//		System.out.println(new SubCp3ModelFinder("sampleprojects/bin/sampleprojects.cp3model").findSubModel(Arrays
//				.asList("projectA")));
		
		System.out.println(new SubCp3ModelFinder("commonbuild-full-pro.cp3model").findSubModel(Arrays
				.asList("seq-core", "projectA", "projectB", "projectC")));

	}

}
