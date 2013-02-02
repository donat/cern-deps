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

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cern.devtools.depanalysis.bean.ArtifactDescriptor;
import cern.devtools.depanalysis.bean.DatabaseDao;
import cern.devtools.depanalysis.domain.Product;
import cern.devtools.depanalysis.domain.creation.DomainObjectCreator;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ JavaStructureParser.class })
public abstract class AbstractDependencyExtractorTest {

	protected DatabaseDao db;
	private DependencyExtractorImpl extractor;
	protected ClassPathXmlApplicationContext ctx;
	private Product pFrom;
	private Product pTo;
	private Product pTrans;
	private DomainObjectCreator creator;

	@Before
	public void before() throws Exception {

		PowerMockito.mockStatic(JavaStructureParser.class);

		ctx = new ClassPathXmlApplicationContext("classpath:/config/ctx-test.xml");

		db = (DatabaseDao) ctx.getBean("database_dao");

		creator = (DomainObjectCreator) ctx.getBean("domain_creator");

		pFrom = getFrom();
		pTo = getTo();
		pTrans = getTrans();

		final Map<String, Product> products = new HashMap<String, Product>();
		if (pFrom != null)
			products.put(pFrom.getName(), pFrom);
		if (pTo != null)
			products.put(pTo.getName(), pTo);
		if (pTrans != null)
			products.put(pTrans.getName(), pTrans);

		when(JavaStructureParser.parseDescriptor(creator, any(ArtifactDescriptor.class), any(boolean.class)))
				.thenAnswer(new Answer<JavaStructureParser>() {
					public JavaStructureParser answer(InvocationOnMock invocation) throws Throwable {
						ArtifactDescriptor ad = (ArtifactDescriptor) invocation.getArguments()[0];
						JavaStructureParser result = null;
						if (ad.getName().equals("from")) {
							result = mock(JavaStructureParser.class);
							when(result.getProduct()).thenReturn(pFrom);
						}
						if (ad.getName().equals("to")) {
							result = mock(JavaStructureParser.class);
							when(result.getProduct()).thenReturn(pTo);
						}
						if (ad.getName().equals("trans")) {
							result = mock(JavaStructureParser.class);
							when(result.getProduct()).thenReturn(pTrans);
						}

						return result;
					}
				});

		extractor = new DependencyExtractorImpl();
		extractor.executeAnalysis(mockedArtifactDescriptors());
	}

	@After
	public void after() throws Exception {
		db.deleteProduct(pFrom);
		db.deleteProduct(pTo);
		db.deleteProduct(pTrans);
	}

	public abstract Product getFrom();

	public abstract Product getTo();

	public abstract Product getTrans();

	private List<ArtifactDescriptor> mockedArtifactDescriptors() {
		List<ArtifactDescriptor> result = new LinkedList<ArtifactDescriptor>();
		result.add(new FileDescriptor("from", "1.0.0", "", ""));
		result.add(new FileDescriptor("to", "1.0.0", "", ""));
		result.add(new FileDescriptor("trans", "1.0.0", "", ""));
		return result;
	}
}
