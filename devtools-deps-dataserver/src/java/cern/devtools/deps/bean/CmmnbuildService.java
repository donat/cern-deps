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

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Simple RMI service interface which is called from the commonbuild build process.
 * 
 * @author Donat Csikos <dcsikos@cern.ch>
 */
public interface CmmnbuildService extends Remote {
	/**
	 * Sends a notification for all the projects' maintainer, who depends on the caller product. The function has to
	 * return within 10 seconds.
	 * 
	 * @param releaserName name of the releaser
	 * @param productName name of the released product
	 * @param oldVersion version number in the commonbuild repo
	 * @param newVersion released version number
	 * @param releaseType PRO, DEV, NEXT, PREV, Qfix
	 * @throws RemoteException
	 */
	public void notifyIncomingJarDependencies(String releaserName, String productName, String oldVersion,
			String newVersion, String releaseType) throws RemoteException;
}
