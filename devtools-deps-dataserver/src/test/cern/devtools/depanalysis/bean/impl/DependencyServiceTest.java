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
package cern.devtools.depanalysis.bean.impl;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collection;
import java.util.EnumSet;
import java.util.LinkedList;

import org.junit.Test;

import cern.devtools.depanalysis.bean.Controller;
import cern.devtools.depanalysis.bean.Controller.DependencyUpdateListener;
import cern.devtools.depanalysis.bean.DatabaseDao;
import cern.devtools.depanalysis.bean.DepBeanException;
import cern.devtools.depanalysis.bean.DependencyService;
import cern.devtools.depanalysis.domain.ApiClass;
import cern.devtools.depanalysis.domain.CodeElement;
import cern.devtools.depanalysis.domain.Dependency;
import cern.devtools.depanalysis.domain.Field;
import cern.devtools.depanalysis.domain.Method;
import cern.devtools.depanalysis.domain.Modifiers;
import cern.devtools.depanalysis.domain.Product;
import cern.devtools.depanalysis.domain.creation.DomainFactory;

public class DependencyServiceTest {

	private DependencyService getMockedService() {
		DatabaseDao db = mock(DatabaseDao.class);
		
		Controller controller = mock(Controller.class);
		DependencyService service = new DependencyServiceImpl(db, controller);
		return service;
	}
 
	@Test
	public void getIncomingDependencyTest() throws Exception {
		Collection<Dependency> classDepStore = new LinkedList<Dependency>();
		Collection<Dependency> fieldDepStore = new LinkedList<Dependency>();
		Collection<Dependency> methodDepStore = new LinkedList<Dependency>();

		DatabaseDao db = mock(DatabaseDao.class);
		Controller controller = mock(Controller.class);
		
		when(db.findClassDependencies(any(ApiClass.class))).thenReturn(classDepStore);
		when(db.findFieldDependencies(any(Field.class))).thenReturn(fieldDepStore);
		when(db.findMethodDependencies(any(Method.class))).thenReturn(methodDepStore);

		//when(controller.addDependencyUpdateListener(any(DependencyUpdateListener.class)));

		DependencyService service = new DependencyServiceImpl(db, controller);

		Collection<Dependency> res = service.getIncomingDependencies(DomainFactory.creator().createApiClass("",
				EnumSet.noneOf(Modifiers.class)));
		assertEquals(classDepStore, res);

		res = service
				.getIncomingDependencies(DomainFactory.creator().createMethod("", EnumSet.noneOf(Modifiers.class)));
		assertEquals(methodDepStore, res);

		res = service.getIncomingDependencies(DomainFactory.creator().createField("", EnumSet.noneOf(Modifiers.class)));
		assertEquals(fieldDepStore, res);
	}

	@Test(expected = DepBeanException.class)
	public void testWrongInput() throws Exception {
		// Pass a non-handled type class.
		getMockedService().getIncomingDependencies(mock(CodeElement.class));
	}

	@Test
	public void getCommittersName_ok() throws Exception {
		DependencyService service = getMockedService();
		Product p = DomainFactory.creator().createProduct("japc", "2.22.2", "japc/japc", "/fakeroot");
		Collection<String> committers = service.getCommittersName(p);
		assertTrue(committers
				.containsAll(Arrays.asList("vbaggiol", "eroux", "arey", "rgorbono", "wbuczak")));
	}

	@Test(expected = DepBeanException.class)
	public void getCommittersName_nullParam() throws Exception {
		DependencyService service = getMockedService();
		service.getCommittersName(null);
	}

	@Test(expected = DepBeanException.class)
	public void getCommittersName_nonexistingProduct() throws Exception {
		DependencyService service = getMockedService();
		Product p = DomainFactory.creator().createProduct("notexists", "0.0.0", "", "");
		service.getCommittersName(p);
	}

	@Test
	public void findReleaser_ok() throws Exception {
		DependencyService service = getMockedService();
		Product p = DomainFactory.creator().createProduct("japc", "2.22.2", "japc/japc", "/fakeroot");
		assertEquals("rgorbono", service.findReleaser(p, "2.22.2"));
	}

	@Test(expected = DepBeanException.class)
	public void findReleaser_nonexistingProduct() throws Exception {
		DependencyService service = getMockedService();
		Product p = DomainFactory.creator().createProduct("notexists", "0.0.0", "", "");
		service.findReleaser(p, "2.22.2");
	}
}
