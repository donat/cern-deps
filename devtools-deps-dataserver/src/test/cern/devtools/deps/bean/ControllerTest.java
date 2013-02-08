package cern.devtools.deps.bean;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import cern.devtools.deps.bean.ArtifactFinder;
import cern.devtools.deps.bean.Controller;
import cern.devtools.deps.bean.DependencyExtractor;
import cern.devtools.deps.bean.Controller.DependencyUpdateListener;
import cern.devtools.deps.bean.impl.ControllerImpl;

public class ControllerTest extends AbstractBeanTest {
	
	Controller controller;
	
	@Before
	public void createSimpleController() {
		ArtifactFinder af = mock(ArtifactFinder.class);
		DependencyExtractor de = mock(DependencyExtractor.class);
		Map<String, Object> props = new HashMap<String, Object>();
		props.put("schedule", "true");
		props.put("schedule_delay_ms", "10");
		props.put("schedule_interval_ms", "500");
		controller = new ControllerImpl(af, de, props);
	}
	
	@Test
	public void testAccessors() {
		assertEquals(10l, controller.getScheduleDelayMilis());
		assertEquals(500l, controller.getScheduleIntervalMilis());
		assertEquals("javabean", controller.getDomainType());
		assertEquals(true, controller.isScheduled());
	}
	
	@Test
	public void testNotification() throws Exception {
		final boolean[] called = {false};
		controller.addDependencyUpdateListener(new DependencyUpdateListener() {
			public void onUpdate() {
				called[0] = true;
			}
		});
		
		Thread.sleep(600);
		assertTrue(called[0]);
	}
}
