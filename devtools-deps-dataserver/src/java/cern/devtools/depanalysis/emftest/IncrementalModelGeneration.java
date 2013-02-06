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
package cern.devtools.depanalysis.emftest;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import cern.devtools.depanalysis.bean.ArtifactDescriptor;
import cern.devtools.depanalysis.bean.ArtifactFinder;
import cern.devtools.depanalysis.bean.DatabaseDao;
import cern.devtools.depanalysis.bean.DependencyExtractor;
import cern.devtools.depanalysis.bean.impl.Measurement;

public class IncrementalModelGeneration {

	public static void main(String[] args) throws Exception {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"classpath:/config/ctx-test-emf.xml");

		Measurement.select(Measurement.EMF);
		
		ArtifactFinder finder = (ArtifactFinder) context.getBean("artifact_finder");
		DatabaseDao db = (DatabaseDao) context.getBean("database_dao");
		DependencyExtractor extractor = (DependencyExtractor) context.getBean("dependency_extractor");

		List<? extends ArtifactDescriptor> artifacts = finder.findArtifacts();
		int max = artifacts.size();
		int step = 100;

		
		for (int i = 1; i <= max; i = next(i, step, max)) {
			long start = System.currentTimeMillis();
			extractor.executeAnalysis(new LinkedList<ArtifactDescriptor>(artifacts.subList(0, i)));
			
			long analtime = System.currentTimeMillis();
			db.flush("submodel_" + i + ".repomodel");
			Measurement.getInstance().setFileSize(new File("submodel_" + i + ".repomodel").length());
			long savetime = System.currentTimeMillis();
			db.reset();

			Measurement.save();
			System.out.println(String.format("%d product anal finished. Analysis time: %d, Save time: %d.", i, analtime
					- start, savetime - analtime));
		}
		
		Measurement.getInstance().prettyPrint();

	}

	public static int next(int cur, int step, int max) {
		if (cur == max) {
			return cur + 1;
		}
		return (cur += step) >= max ? max : cur;
	}
}
