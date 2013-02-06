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

import cern.devtools.depanalysis.domain.creation.DomainFactory;

/**
 * Interface serves as top level controller component. This involves configuring the framework-wide options, such as the
 * type of the used domain objects, and the scheduling options.
 * <p>
 * The interface serves as a marker interface, and provides runtime information for a possible monitoring bean.
 * <p>
 * TODO: future work: implement JMX bean on top of this interface for monitoring the application
 * 
 * @author Donat Csikos
 */
public interface Controller {

	/**
	 * A simple listener interface for reacting the controller's event. Currently it only supports the event when a
	 * dependency database is being updated.
	 */
	public interface DependencyUpdateListener {
		/**
		 * Callback function when for the database update event. 
		 */
		public void onUpdate();
	}

	/**
	 * Flags, if the module initiates dependency discovery in specified intervals.
	 * 
	 * @return The module is scheduled.
	 */
	public boolean isScheduled();

	/**
	 * The time interval which the module waits between the startup time and the first dependency discovery. The value
	 * is negative, if there is no schedule at all.
	 * 
	 * @return Time in milliseconds.
	 */
	public long getScheduleDelayMilis();

	/**
	 * The time interval between discoveries. The value is negative, if there is no schedule at all.
	 * 
	 * @return Time in milliseconds.
	 */
	public long getScheduleIntervalMilis();

	/**
	 * Returns the String representation of the used domain objects in the framework.
	 * 
	 * @see DomainFactory
	 * @return The type of the used domain objects.
	 */
	public String getDomainType();

	/**
	 * Add listener to the controller instance.
	 * 
	 * @param listener
	 */
	public void addDependencyUpdateListener(DependencyUpdateListener listener);

	/**
	 * Remove listener from the list.
	 * 
	 * @param listener
	 */
	public void removeDependencyUpdateListener(DependencyUpdateListener listener);
}
