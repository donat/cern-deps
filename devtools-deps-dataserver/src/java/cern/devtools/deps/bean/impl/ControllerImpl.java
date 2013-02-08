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
package cern.devtools.deps.bean.impl;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.log4j.Logger;

import cern.devtools.deps.bean.ArtifactDescriptor;
import cern.devtools.deps.bean.ArtifactFinder;
import cern.devtools.deps.bean.Controller;
import cern.devtools.deps.bean.DependencyExtractor;
import cern.devtools.deps.domain.creation.DomainFactory;
import cern.devtools.deps.domain.creation.DomainObjectCreator;
import cern.devtools.deps.domain.creation.impl.EmfObjectCreator;
import cern.devtools.deps.domain.creation.impl.JavaBeanObjectCreator;

/**
 * Implementation of the {@link Controller} interface.
 * 
 * @author Donat Csikos
 */
public class ControllerImpl implements Controller {

	/**
	 * Discovery is scheduled.
	 */
	private boolean isScheduled;

	/**
	 * Threshold time after first discovery will start on schedule (in milliseconds).
	 */
	private Long scheduleDelayMilis;

	/**
	 * Time to wait between scheduled discoveries (in milliseconds).
	 */
	private Long scheduleIntervalMilis;

	/**
	 * Artifact finder bean
	 */
	private final ArtifactFinder finder;

	/**
	 * Dependency extractor bean.
	 */
	private final DependencyExtractor extractor;

	/**
	 * @see DomainObjectCreator
	 */
	private String domainType;

	/**
	 * 
	 */
	private List<DependencyUpdateListener> listeners = new LinkedList<Controller.DependencyUpdateListener>();

	/**
	 * Logger.
	 */
	private static final Logger LOG = Logger.getLogger(ControllerImpl.class);

	/**
	 * <p>
	 * Constructor for injecting dependencies for this component.
	 * </p>
	 * <p>
	 * The first two parameters should be responsible for finding the jar files and extracting the dependency
	 * informations out of it. The properties parameter waits for the following settings:
	 * <ul>
	 * <li><b>schedule</b>: <code>true</code>, if the framework should schedule the discovery. The properties must
	 * contain this key, or else {@link RuntimeException} will be thrown. If schedule is on, the schedule_delay_ms and
	 * the schedule_interval_ms is obligatory to define, or else the same exception will happen</li>
	 * <li><b>schedule_delay_ms</b>: specifies, how much time will the component wait before the first discovery.
	 * Measured in milliseconds.</li>
	 * <li><b>schedule_interval_ms</b>: specifies, how much time will the component wait between two discoveries.
	 * Measured in milliseconds.</li>
	 * <li><b>domain_type</b>: The used domain by the framework. By default the domain objects are implemented as simple
	 * POJO objects, but through the {@link DomainFactory} interface, the implementation could be changed.</li>
	 * </ul>
	 * </p>
	 * 
	 * @param finder Finder bean.
	 * @param extractor Extractor bean.
	 * @param properties Configuration parameters.
	 */
	public ControllerImpl(ArtifactFinder finder, DependencyExtractor extractor,  Map<String, Object> properties) {
		// store final members
		this.finder = finder;
		this.extractor = extractor;

		// check and set properties
		configureObligatoryProps(properties);
		configureOptionalProps(properties);

		// start the schedule, if needed.
		if (isScheduled) {
			schedule();
		}
	}

	/**
	 * Configures the scheduling properties.
	 * 
	 * @param properties The read properties.
	 */
	private void configureObligatoryProps(Map<String, Object> properties) {
		Object schedule = properties.get("schedule");
		Object delay = null;
		Object interval = null;
		if (schedule == null) {
			throw new RuntimeException("Controller not initialized properly: schedule parameter is missing");
		} else {
			delay = properties.get("schedule_delay_ms");
			interval = properties.get("schedule_interval_ms");
		}

		this.isScheduled = Boolean.valueOf((String) schedule);
		if (this.isScheduled) {
			if (delay == null || interval == null) {
				throw new RuntimeException(
						"Controller not initialized properly: schedule requires schedule_delay_ms and schedule_interval_ms parameters to set.");
			}
			this.scheduleDelayMilis = Long.valueOf((String) delay);
			this.scheduleIntervalMilis = Long.valueOf((String) interval);
		}
	}

	/**
	 * Configures the domain properties.
	 * 
	 * @param properties The read properties.
	 */
	private void configureOptionalProps(Map<String, Object> properties) {
		// configure domain factory
		Object domainType = properties.get("domain_type");
		DomainObjectCreator creator = null;
		if (domainType != null && "EMF".equals(domainType)) {
			creator = new EmfObjectCreator();
		} else {
			creator = new JavaBeanObjectCreator();
		}
		DomainFactory.setDomainObjectCreator(creator);
		this.domainType = creator.creatorName();
	}

	/**
	 * Starts periodical discovery.
	 */
	private void schedule() {
		final Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				try {
					LOG.info("Discovery Started at " + new Date());
					List<? extends ArtifactDescriptor> artifacts = finder.findArtifacts();
					extractor.executeAnalysis(artifacts);
					notifyListers();
					LOG.info("Discovery finished at " + new Date());
				} catch (Throwable e) {
					LOG.warn("Exception has happened during the discovery. Reason is: " + e.getMessage() + ".");
				}
			}
		}, scheduleDelayMilis, scheduleIntervalMilis);
	}


	public boolean isScheduled() {
		return isScheduled;
	}

	public long getScheduleDelayMilis() {
		return scheduleDelayMilis;
	}

	public long getScheduleIntervalMilis() {
		return scheduleIntervalMilis;
	}

	public String getDomainType() {
		return domainType;
	}

	public void addDependencyUpdateListener(DependencyUpdateListener listener) {
		listeners.add(listener);

	}

	public void removeDependencyUpdateListener(DependencyUpdateListener listener) {
		listeners.remove(listener);
	}

	private void notifyListers() {
		for (DependencyUpdateListener listener : listeners) {
			listener.onUpdate();
		}
	}
}
