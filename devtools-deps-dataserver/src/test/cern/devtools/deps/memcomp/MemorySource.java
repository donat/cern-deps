package cern.devtools.deps.memcomp;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;

import javax.tools.SimpleJavaFileObject;

class MemorySource extends SimpleJavaFileObject {

	private String src;

	public MemorySource(String name, String src) {
		super(URI.create("file:///" + name + ".java"), Kind.SOURCE);
		this.src = src;
	}

	@Override
	public CharSequence getCharContent(boolean ignoreEncodingErrors) {
		return src;
	}

	@Override
	public OutputStream openOutputStream() {
		throw new IllegalStateException();
	}

	@Override
	public InputStream openInputStream() {
		return new ByteArrayInputStream(src.getBytes());
	}
}
