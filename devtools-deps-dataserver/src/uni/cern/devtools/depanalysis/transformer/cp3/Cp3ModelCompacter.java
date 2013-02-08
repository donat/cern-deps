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
package cern.devtools.depanalysis.transformer.cp3;

import hu.bme.incquery.deps.cp3model.CP3AbstractItem;
import hu.bme.incquery.deps.cp3model.CP3Class;
import hu.bme.incquery.deps.cp3model.CP3Dep;
import hu.bme.incquery.deps.cp3model.CP3Field;
import hu.bme.incquery.deps.cp3model.CP3Method;
import hu.bme.incquery.deps.cp3model.CP3Project;
import hu.bme.incquery.deps.cp3model.CP3Repo;
import hu.bme.incquery.deps.cp3model.Cp3modelFactory;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.ecore.resource.Resource;

import cern.devtools.depanalysis.repomodel.RClass;
import cern.devtools.depanalysis.repomodel.RCodeElement;
import cern.devtools.depanalysis.repomodel.RDependency;
import cern.devtools.depanalysis.repomodel.RField;
import cern.devtools.depanalysis.repomodel.RMethod;
import cern.devtools.depanalysis.repomodel.RProject;
import cern.devtools.depanalysis.repomodel.RRepository;

public class Cp3ModelCompacter {
	private RRepository inRepository;
	private Resource outResource;

	private HashMap<String, String> projectUuidToName = new HashMap<String, String>();
	private HashMap<String, Long> projectUuidToId = new HashMap<String, Long>();
	private HashMap<String, Long> codeElementUuidToId = new HashMap<String, Long>();
	private HashMap<Long, Set<Long>> projectIdToClassIds = new HashMap<Long, Set<Long>>();
	private HashMap<Long, Set<Long>> classIdToProjectIds = new HashMap<Long, Set<Long>>();
	private HashMap<Long, Set<Long>> classIdToFieldIds = new HashMap<Long, Set<Long>>();
	private HashMap<Long, Set<Long>> fieldIdToClassIds = new HashMap<Long, Set<Long>>();
	private HashMap<Long, Set<Long>> classIdToMethodIds = new HashMap<Long, Set<Long>>();
	private HashMap<Long, Set<Long>> methodIdToClassIds = new HashMap<Long, Set<Long>>();
	private HashMap<String, Long> classNameToId = new HashMap<String, Long>();
	private HashMap<Long, String> classIdToName = new HashMap<Long, String>();
	private HashMap<String, Long> fieldNameToId = new HashMap<String, Long>();
	private HashMap<Long, String> fieldIdToName = new HashMap<Long, String>();
	private HashMap<String, Long> methodNameToId = new HashMap<String, Long>();
	private HashMap<Long, String> methodIdToName = new HashMap<Long, String>();
	private Set<Triplet<Long, Long, String>> deps = new HashSet<Triplet<Long, Long, String>>();

	private static long id;

	private static long nextId() {
		return ++id;
	}

	private static void addToMap(HashMap<Long, Set<Long>> map, Long key, Long value) {
		Set<Long> list = map.get(key);
		if (list == null) {
			list = new HashSet<Long>();
			map.put(key, list);
		}
		list.add(value);
	}

	public Cp3ModelCompacter(RRepository rRepository, Resource outResource) {
		this.inRepository = rRepository;
		this.outResource = outResource;
	}

	public Resource compact() {
		traverseRepoModel();
		constructCp3Model();
		return outResource;
	}

	Map<Long, CP3Project> projectIdToCp3Project = new HashMap<Long, CP3Project>();
	Map<Long, CP3Class> classIdToCp3Class = new HashMap<Long, CP3Class>();
	Map<Long, CP3AbstractItem> cp3IdToCp3Object = new HashMap<Long, CP3AbstractItem>();

	private void constructCp3Model() {

		CP3Repo repo = Cp3modelFactory.eINSTANCE.createCP3Repo();

		// Add projects
		for (String projectUuid : projectUuidToName.keySet()) {
			String projectName = projectUuidToName.get(projectUuid);
			Long projectId = projectUuidToId.get(projectUuid);

			CP3Project cp = Cp3modelFactory.eINSTANCE.createCP3Project();
			cp.setId(projectId);
			cp.setName(projectName);

			// save reference and put into the repo.
			projectIdToCp3Project.put(projectId, cp);
			repo.getItems().add(cp);
		}

		// Add classes
		for (Long classId : classIdToName.keySet()) {
			String className = classIdToName.get(classId);
			CP3Class cc = Cp3modelFactory.eINSTANCE.createCP3Class();
			cc.setId(classId);
			cc.setName(className);

			// set container projects.
			Set<Long> containerProjectIds = classIdToProjectIds.get(classId);
			for (Long containerProjectId : containerProjectIds) {
				cc.getProjects().add(projectIdToCp3Project.get(containerProjectId));
			}

			// save reference and put into the repo
			classIdToCp3Class.put(classId, cc);
			cp3IdToCp3Object.put(classId, cc);
			repo.getItems().add(cc);
		}

		// Add methods
		for (Long methodId : methodIdToName.keySet()) {
			String methodName = methodIdToName.get(methodId).split("\\#")[1];

			CP3Method cm = Cp3modelFactory.eINSTANCE.createCP3Method();
			cm.setId(methodId);
			cm.setName(methodName);

			Set<Long> containerClassIds = methodIdToClassIds.get(methodId);
			for (Long containerClassId : containerClassIds) {
				cm.getClasses().add(classIdToCp3Class.get(containerClassId));
			}

			// save reference and put into the repo
			repo.getItems().add(cm);
			cp3IdToCp3Object.put(methodId, cm);
		}

		// Add fields
		for (Long fieldId : fieldIdToName.keySet()) {
			String fieldName = fieldIdToName.get(fieldId).split("\\#")[1];

			CP3Field cf = Cp3modelFactory.eINSTANCE.createCP3Field();
			cf.setId(fieldId);
			cf.setName(fieldName);

			Set<Long> containerClassIds = fieldIdToClassIds.get(fieldId);
			for (Long containerClassId : containerClassIds) {
				cf.getClasses().add(classIdToCp3Class.get(containerClassId));
			}

			// save reference and put into the repo
			repo.getItems().add(cf);
			cp3IdToCp3Object.put(fieldId, cf);
		}

		// Add dependencies
		for (Triplet<Long, Long, String> dep : deps) {
			CP3Dep cd = Cp3modelFactory.eINSTANCE.createCP3Dep();
			cd.setFrom(cp3IdToCp3Object.get(dep.getA()));
			cd.setTo(cp3IdToCp3Object.get(dep.getB()));
			Short depType = Short.valueOf(dep.getC());
			cd.setType(depType);
			
			repo.getDeps().add(cd);
		}

		// Finally save the root object in the outout
		outResource.getContents().add(repo);
	}

	private void traverseRepoModel() {

		// First for the structure.
		for (Object rpo : inRepository.getRProjects()) {
			RProject rp = (RProject) rpo;
			addProject(rp.getUuid(), rp.getName());

			for (Object rco : rp.getClasses()) {
				RClass rc = (RClass) rco;
				addClass(rc.getUuid(), rc.getSimpleName(), rp.getUuid());

				for (Object rmo : rc.getMethods()) {
					RMethod rm = (RMethod) rmo;
					addMethod(rm.getUuid(),
							rc.getSimpleName() + "#" + extractMethodNameFromSignature(rm.getSignature()), rc.getUuid());
				}

				for (Object rfo : rc.getFields()) {
					RField rf = (RField) rfo;
					addField(rf.getUuid(), rc.getSimpleName() + "#" + extractFieldNameFromSignature(rf.getSignature()),
							rc.getUuid());
				}
			}
		}

		// Second, for the dependencies
		for (Object rdo : inRepository.getRDependencies()) {
			RDependency rd = (RDependency) rdo;
			addDependenency(((RCodeElement) rd.getFrom()).getUuid(), ((RCodeElement) rd.getTo()).getUuid(),
					String.valueOf(rd.getDepType()));
		}
	}

	private static String extractMethodNameFromSignature(String signature) {
		String name = signature;
		name = name.split("\\#")[1].split("\\(")[0];
		if (name.startsWith("<")) {
			name = name.replace("<", "&lt;").replace(">", "&gt;");
		}
		return name;
	}

	private static String extractFieldNameFromSignature(String signature) {
		String name = signature;
		String[] names = name.split("\\.");
		name = names[names.length - 1];

		return name;
	}

	private void addProject(String uuid, String name) {
		projectUuidToName.put(uuid, name);
		projectUuidToId.put(uuid, nextId());
	}

	private void addClass(String uuid, String name, String selectedProjectUuid) {
		Long classId = classNameToId.get(name);
		if (classId == null) {
			classId = nextId();
			classNameToId.put(name, classId);
			classIdToName.put(classId, name);
		}

		Long selectedProjectId = projectUuidToId.get(selectedProjectUuid);
		addToMap(projectIdToClassIds, selectedProjectId, classId);
		addToMap(classIdToProjectIds, classId, selectedProjectId);

		codeElementUuidToId.put(uuid, classId);
	}

	private void addField(String uuid, String name, String selectedClassUuid) {
		Long fieldId = fieldNameToId.get(name);
		if (fieldId == null) {
			fieldId = nextId();
			fieldNameToId.put(name, fieldId);
			fieldIdToName.put(fieldId, name);
		}

		Long selectedClassId = codeElementUuidToId.get(selectedClassUuid);
		addToMap(fieldIdToClassIds, fieldId, selectedClassId);
		addToMap(classIdToFieldIds, selectedClassId, fieldId);

		codeElementUuidToId.put(uuid, fieldId);

	}

	private void addMethod(String uuid, String name, String selectedClassUuid) {
		Long methodId = methodNameToId.get(name);
		if (methodId == null) {
			methodId = nextId();
			methodNameToId.put(name, methodId);
			methodIdToName.put(methodId, name);
		}

		Long selectedClassId = codeElementUuidToId.get(selectedClassUuid);
		addToMap(methodIdToClassIds, methodId, selectedClassId);
		addToMap(classIdToMethodIds, selectedClassId, methodId);

		codeElementUuidToId.put(uuid, methodId);
	}

	private void addDependenency(String from, String to, String type) {
		Long fromId = codeElementUuidToId.get(from);
		Long toId = codeElementUuidToId.get(to);
		deps.add(new Triplet<Long, Long, String>(fromId, toId, type));
	}

}
