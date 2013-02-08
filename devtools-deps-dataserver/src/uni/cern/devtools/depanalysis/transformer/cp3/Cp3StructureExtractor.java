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

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class Cp3StructureExtractor extends DefaultHandler {

	private final Cp3ModelDetails details;
	
	String selectedProjectUuid = null;
	String selectedProjectName = null;
	String selectedClassUuid = null;
	String selectedClassName = null;

	public Cp3StructureExtractor(Cp3ModelDetails details) {
		this.details = details;
		
	}
	
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		if (qName.equals("rProjects")) {
			String uuid = attributes.getValue("uuid");
			String name = attributes.getValue("name");
			details.addProject(uuid, name);
			selectedProjectUuid = uuid;
			selectedProjectName= name;
		}
		
		else if (qName.equals("rClasses")) {
			String uuid = attributes.getValue("uuid");
			String name = attributes.getValue("simpleName");
			details.addClass(uuid, name, selectedProjectUuid);
			selectedClassUuid = uuid;
			selectedClassName = name;
		}
		
		else if (qName.equals("rMethods")){
			String uuid = attributes.getValue("uuid");
			String name = attributes.getValue("signature");
			name = name.split("\\#")[1].split("\\(")[0];
			if (name.startsWith("<")){
				name = name.replace("<", "&lt;").replace(">", "&gt;");
			}
			details.addMethod(uuid, selectedClassName + "#" + name, selectedClassUuid);
		}
		
		else if (qName.equals("rFields")){
			String uuid = attributes.getValue("uuid");
			String name = attributes.getValue("signature");
			String[] names = name.split("\\.");
			name = names[names.length-1];
			
			details.addField(uuid, selectedClassName + "#" + name, selectedClassUuid);
		}
	}
}
