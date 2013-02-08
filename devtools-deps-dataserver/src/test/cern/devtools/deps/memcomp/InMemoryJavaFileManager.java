package cern.devtools.deps.memcomp;

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
