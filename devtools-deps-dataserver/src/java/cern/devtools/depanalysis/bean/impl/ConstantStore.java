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
package cern.devtools.depanalysis.bean.impl;

/**
 * Stores constants which is used by the system.
 * 
 * @author Donat Csikos
 */
public interface ConstantStore {
	/**
	 * Linux-style path of the commonbuild repository location. Used when the jvm is running on Linux.
	 */
	public static String PCROPS_WINDOWS_LOC = "\\\\cs-ccr-nfsdev\\pcrops\\dist\\";

	/**
	 * Windows-style path of the commonbuild repository location. Used when the jvm is running on Windows machines.
	 */
	public static String PCROPS_LINUX_LOC = "/user/pcrops/dist/";

	/**
	 * The URL of the Commonbuild release log resource.
	 */
	public static String CMMNBUILD_RELEASELOG_URL = "http://abwww/ap/dist/release_prefs/releaselog";
}
