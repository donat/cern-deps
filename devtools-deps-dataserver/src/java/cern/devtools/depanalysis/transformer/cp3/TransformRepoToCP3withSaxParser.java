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
package cern.devtools.depanalysis.transformer.cp3;

import java.io.File;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class TransformRepoToCP3withSaxParser {

	public static void transform(String fromFile, String toFile) throws Exception {
		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser saxParser = factory.newSAXParser();
		Cp3ModelDetails details = new Cp3ModelDetails(toFile);
		saxParser.parse(new File(fromFile), new Cp3StructureExtractor(details));
		saxParser.parse(new File(fromFile), new Cp3DepsExtractor(details));
		details.write();

	}

	public static void main(String[] args) throws Exception {
		// TransformRepoToCP3withSaxParser.transform("tmp.repomodel", "tmp.cp3model");
		// new TransformRepoToCP3withSaxParser().transform("sampleprojects/bin/sampleprojects.repomodel",
		// "sampleprojects/bin/sampleprojects.cp3model");
		TransformRepoToCP3withSaxParser.transform("sampleprojects/bin/sampleprojects-japc.repomodel",
				"sampleprojects/bin/sampleprojects-japc.cp3model");
	}
}
