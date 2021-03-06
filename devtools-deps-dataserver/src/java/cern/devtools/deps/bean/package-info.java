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
/**
 * Contains interface which describes the main activities for running the server side of the dependency analysis
 * framework.<p>
 *
 * The interfaces are implemented in the {@link cern.devtools.deps.bean.impl} package. The instantiation, the
 * dependency injection and the execution are performed by the Spring framework. The Spring configuration is in the
 * <code>src/java/config/ctx-server.xml</code> file, which wires the interface implementation together. There is an
 * another configuration file in the <code>src/test/java/config/ctx-test.xml</code> which obviously serves as a
 * configuration for the unit tests.<p>
 * 
 * This package is distributed into two projects: the devtools-deps-common and the devtools-deps-dataserver. It is
 * necessary, because we don't want to expose the entire API towards the clients, the only thing they have to be aware
 * is the service interface.<p>
 * 
 * @author Donat Csikos <dcsikos@cern.ch>
 */
package cern.devtools.deps.bean;

