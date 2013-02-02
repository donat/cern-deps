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
