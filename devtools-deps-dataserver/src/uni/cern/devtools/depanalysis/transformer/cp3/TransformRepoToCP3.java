package cern.devtools.depanalysis.transformer.cp3;

import hu.bme.incquery.deps.cp3model.Cp3modelPackage;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.emf.ecore.xmi.impl.XMLParserPoolImpl;

import cern.devtools.depanalysis.repomodel.RRepository;
import cern.devtools.depanalysis.repomodel.RepomodelPackage;

public class TransformRepoToCP3 {

	public static void main(String[] args) throws Exception {
		TransformRepoToCP3.transform("commonbuild-full-pro.repomodel", "commonbuild-full-pro.cp3model");
	}

	public static void transform(String fromFile, String toFile) throws Exception {
		new TransformRepoToCP3(fromFile, toFile).transform();
	}

	private ResourceSet resourceSet;

	private Resource inResource;

	private Resource outResource;

	public TransformRepoToCP3(String inFileLoc, String outFileLoc) {
//		if (!(new File(outFileLoc).canWrite())) {
//			throw new RuntimeException("Output " + outFileLoc + " is not writeable.");
//		}

		resourceSet = new ResourceSetImpl();
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap()
				.put("repomodel", new XMIResourceFactoryImpl());
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap()
				.put("cp3model", new XMIResourceFactoryImpl());
//		EPackage.Registry.INSTANCE.put(RepomodelPackage.eNS_URI, RepomodelPackage.eINSTANCE);
//		EPackage.Registry.INSTANCE.put(Cp3modelPackage.eNS_URI, Cp3modelPackage.eINSTANCE);

		inResource = resourceSet.createResource(URI.createFileURI(inFileLoc));
		outResource = resourceSet.createResource(URI.createFileURI(outFileLoc));
	}



	@SuppressWarnings("all")
	public Map loadOptions() {
		Map loadOptions = new HashMap();
		loadOptions.put(XMLResource.OPTION_DEFER_ATTACHMENT, Boolean.TRUE);
		loadOptions.put(XMLResource.OPTION_DEFER_IDREF_RESOLUTION, Boolean.TRUE);
		loadOptions.put(XMLResource.OPTION_USE_DEPRECATED_METHODS, Boolean.TRUE);
		loadOptions.put(XMLResource.OPTION_USE_PARSER_POOL, new XMLParserPoolImpl());
		loadOptions.put(XMLResource.OPTION_PROCESS_DANGLING_HREF, XMLResource.OPTION_PROCESS_DANGLING_HREF_DISCARD);
		loadOptions.put(XMLResource.OPTION_USE_XML_NAME_TO_FEATURE_MAP, new HashMap());
		return loadOptions;
	}

	public void transform() {
		try {
			inResource.load(loadOptions());
			convertRepoToCp3();
			new Cp3ModelCompacter((RRepository)inResource.getContents().get(0), outResource).compact();
			outResource.save(Collections.EMPTY_MAP);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		
	}

	private void convertRepoToCp3() {
		System.out.println(inResource.getContents());
	}
}
