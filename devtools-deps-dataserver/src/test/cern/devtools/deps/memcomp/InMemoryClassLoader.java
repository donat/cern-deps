package cern.devtools.deps.memcomp;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Class loader that keeps the produced class and byte code in memory
 */
public class InMemoryClassLoader extends ClassLoader {
	private static Map<String, MemoryByteCode> m = new HashMap<String, MemoryByteCode>();

	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		MemoryByteCode mbc = m.get(name);
		if (mbc == null) {
			mbc = m.get(name.replace(".", "/"));
			if (mbc == null) {
				return super.findClass(name);
			}
		}
		return defineClass(name, mbc.getBytes(), 0, mbc.getBytes().length);
	}

	public void addClass(String name, MemoryByteCode mbc) {
		m.put(name, mbc);
	}

	/**
	 * Returns the compiled byte code of the specified class
	 * 
	 * @param className
	 * @return the compiled byte code
	 */
	public byte[] getByteCode(String className) {
		return m.get(className).getBytes();
	}
	
	public byte[][] getAllByteCodes() {
		byte[][] result = new byte[m.keySet().size()][];
		int i = 0;
		for(String name : m.keySet()) {
			byte[] bytecode = m.get(name).getBytes();
			result[i++] = bytecode;
		}
		return result;
	}
	
	public byte[][] getPackageByteCode(String packageName) {
		int numOfClasses = 0;
		for(String name : m.keySet()) {
			if (name.startsWith(packageName)) {
				numOfClasses++;
			}
		}
		byte[][] result = new byte[numOfClasses][];
		int act = 0;
		for(String name : m.keySet()) {
			if (name.startsWith(packageName)) {
				result[act++] = m.get(name).getBytes();
			}
		}
		return result;
	}

	public InputStream getByteCodeAsStream(String name) {
		return new ByteArrayInputStream(m.get(name).getBytes());
	}
}
