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
package cern.devtools.depanalysis.service;

import java.util.Collection;
import java.util.EnumSet;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import cern.devtools.depanalysis.bean.DatabaseDao;
import cern.devtools.depanalysis.domain.Dependency;
import cern.devtools.depanalysis.domain.Modifiers;
import cern.devtools.depanalysis.domain.creation.impl.JavaBeanObjectCreator;

/**
 * SDT-824
 * Dependency plugin - analysis job never finishes <p>
 * 
 * @author Donat Csikos
 *
 */
public class TestInfiniteLoop {
	public static void main(String[] args) throws Exception {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"classpath:/config/ctx-test-service-oracledev.xml");
		DatabaseDao db = (DatabaseDao) context.getBean("database_dao");
		JavaBeanObjectCreator c = new JavaBeanObjectCreator();
		Collection<Dependency> deps = db.findClassDependencies(c.createApiClass("cern.japc.ParameterValueListener",
				EnumSet.noneOf(Modifiers.class)));
		
		// Print the result.
		for (Dependency d : deps) {
			System.out.println(d);
		}
		
		context.close();
	}
}
