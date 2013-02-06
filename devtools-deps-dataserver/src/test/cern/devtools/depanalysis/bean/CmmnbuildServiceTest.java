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
package cern.devtools.depanalysis.bean;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyBoolean;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.util.ReflectionTestUtils;

import cern.devtools.depanalysis.domain.CodeElement;
import cern.devtools.depanalysis.domain.Dependency;
import cern.devtools.depanalysis.domain.DependencyType;
import cern.devtools.depanalysis.domain.Product;
import cern.devtools.depanalysis.domain.creation.DomainObjectCreator;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/config/ctx-test-service-oracledev.xml")
public class CmmnbuildServiceTest {

	@Autowired
	private CmmnbuildService cmmnbuildService;
	
	@Autowired
	private DomainObjectCreator creator;

	@Before
	public void before() throws Exception { 
		
		// Mock the external beans from our bean and add them to the tested commonbuild service.
		//MailSender mailSender = mock(MailSender.class);
		DatabaseDao db = mock (DatabaseDao.class);
		DependencyService service = mock(DependencyService.class);
		//ReflectionTestUtils.setField(cmmnbuildService, "mailSender", mailSender);
		ReflectionTestUtils.setField(cmmnbuildService, "db", db);
		ReflectionTestUtils.setField(cmmnbuildService, "service", service);
		
		// Specify the results returned by the mocks.
		when(service.getIncomingDependencies(any(CodeElement.class))).thenReturn(getSampleIncomingDependencies());
		when(service.getCommittersName(any(Product.class))).thenReturn(Arrays.asList("vbaggiol"));
		when(db.findProduct(any(Product.class), anyBoolean())).thenReturn(creator.createProduct("", "", "japc/japc", ""));
	}

	private Collection<Dependency> getSampleIncomingDependencies() {
		Collection<Dependency> result = new LinkedList<Dependency>();
		result.add(creator.createDependency(DependencyType.PRODUCT_DEPENDENCY, creator.createProduct("clientA"), null));
		result.add(creator.createDependency(DependencyType.PRODUCT_DEPENDENCY, creator.createProduct("clientB"), null));
		return result;
	}

	
	/**
	 * TODO reactivate this test!
	 *    
	 * @throws Exception
	 */
	@Test
	//@Ignore
	public void test() throws Exception {
		// Call the function under test
		cmmnbuildService.notifyIncomingJarDependencies("dcsikos", "ApiProduct", "1.0.0", "2.0.0", "PRO");
		
		// Setup object to catch the arguments passed to the MailSender.send(..) function.
//		ArgumentCaptor<String[]> mailToCapturer = ArgumentCaptor.forClass(String[].class);
//		ArgumentCaptor<String> mailSubjectCapturer = ArgumentCaptor.forClass(String.class);
//		ArgumentCaptor<String> mailBodyCapturer = ArgumentCaptor.forClass(String.class);
//
//		// Gather the details of the sent mails. 
//		verify(mailSender).send(mailToCapturer.capture(), mailSubjectCapturer.capture(), mailBodyCapturer.capture());
//		
//		// Check the validity of them.
//		assertEquals(1, mailToCapturer.getValue().length);
//		assertEquals("dcsikos@cern.ch", mailToCapturer.getValue()[0]);
//		assertEquals("[REPOSITORY_CHANGE] 2.0.0 version of the ApiProduct is released!", mailSubjectCapturer.getValue());
//		String expectedSubject = "Dear Donat Csikos!\n\n"
//				+ "This message is sent because the 2.0.0 version is released from the product ApiProduct. "
//				+ "You are a committer of the following project(s),"
//				+ " which most likely contain a dependency to this fresh release:\n"
//				+ "\tDependantProduct\nPlease check if you are affected by this change!";
//		assertEquals(expectedSubject, mailBodyCapturer.getValue());

	}
}
