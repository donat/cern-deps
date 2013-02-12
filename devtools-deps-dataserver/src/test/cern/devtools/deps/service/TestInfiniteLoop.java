package cern.devtools.deps.service;

import java.util.Collection;
import java.util.EnumSet;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import cern.devtools.deps.bean.DatabaseDao;
import cern.devtools.deps.domain.Dependency;
import cern.devtools.deps.domain.Modifiers;
import cern.devtools.deps.domain.creation.impl.JavaBeanObjectCreator;

/**
 * SDT-824
 * Dependency plugin - analysis job never finishes <p>
 * 
 * @author Donat Csikos <dcsikos@cern.ch>
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
