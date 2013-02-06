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
package cern.devtools.depanalysis.memcomp;

import javax.tools.FileObject;
import javax.tools.ForwardingJavaFileManager;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;

/**
 * A JavaFilemanager that injects a special class loader in which the produced
 * JavaFileObject is stored
 */
class InMemoryJavaFileManager extends ForwardingJavaFileManager<StandardJavaFileManager> {
	private InMemoryClassLoader xcl;

	public InMemoryJavaFileManager(StandardJavaFileManager sjfm, InMemoryClassLoader xcl) {
		super(sjfm);
		this.xcl = xcl;
	}

	/**
	 * Provides a {@link MemoryByteCode} object as storage for the produced
	 * byte code
	 */
	@Override
	public JavaFileObject getJavaFileForOutput(Location location, String name, JavaFileObject.Kind kind,
			FileObject sibling) 
	{
		MemoryByteCode mbc = new MemoryByteCode(name);
		xcl.addClass(name, mbc);
		return mbc;
	}

	@Override
	public ClassLoader getClassLoader(Location location) {
		return xcl;
	}
}
