package cern.devtools.deps.bean;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyBoolean;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.util.ReflectionTestUtils;

import cern.devtools.depanalysis.repomodel.RComponent;
import cern.devtools.depanalysis.repomodel.RDependency;
import cern.devtools.depanalysis.repomodel.RProject;
import cern.devtools.deps.domain.creation.DomainObjectCreator;

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
		when(service.getIncomingDependencies(any(RComponent.class))).thenReturn(getSampleIncomingDependencies());
		when(service.getCommittersName(any(RProject.class))).thenReturn(Arrays.asList("vbaggiol"));
		when(db.findProject(any(RProject.class), anyBoolean())).thenReturn(null);
	}

	private Collection<RDependency> getSampleIncomingDependencies() {
		return null;
//		Collection<RDependency> result = new LinkedList<RDependency>();
//		result.add(creator.createDependency(DependencyType.PRODUCT_DEPENDENCY, creator.createProject("clientA"), null));
//		result.add(creator.createDependency(DependencyType.PRODUCT_DEPENDENCY, creator.createProject("clientB"), null));
//		return result;
	}

	
	/**
	 * TODO reactivate this test!
	 *    
	 * @throws Exception
	 */
	@Test
	@Ignore
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
