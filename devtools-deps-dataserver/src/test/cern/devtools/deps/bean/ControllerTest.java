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
