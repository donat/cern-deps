package cern.devtools.deps.bean;

import org.junit.After;
import org.junit.Before;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Abstract unit test class for defining common environment for the tests.
 * 
 * @author dcsikos
 * 
 */
public abstract class AbstractBeanTest {

	/**
	 * Spring context
	 */
	protected ClassPathXmlApplicationContext context;

	@Before
	public void setup() {
		context = new ClassPathXmlApplicationContext("classpath:/config/ctx-test-discovery-oracledev.xml");
	}

	@After
	public void tearDown() {
		context.close();
	}
}
