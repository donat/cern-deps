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
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import cern.devtools.deps.bean.impl.ByteCodeAnalyser;
import cern.devtools.deps.domain.Modifiers;
import cern.devtools.deps.domain.creation.DomainFactory;
import cern.devtools.deps.memcomp.InMemoryClassLoader;
import cern.devtools.deps.memcomp.InMemoryCompiler;

/**
 * JUnit test for class {@link ByteCodeAnalyzer}
 * 
 * @author vbaggiol
 */
public class ByteCodeAnalyserTest {

	private static final String PKG = "example.";

	private static final String if1_name = "if1";
	private static final String if1_source = "package example;\n public interface if1 {}";

	private static final String if2_name = "if2";
	private static final String if2_source = "package example;\n public interface if2 {}";

	private static final String supercls_name = "supercls";
	private static final String supercls_source = "package example;\n public class supercls {}";

	private static final String cls_name = "cls";
	private static final String cls_source = "package example;\n public class cls extends supercls implements if1, if2 {"
			+ "public static final Object samplefield = null;" + "public void foo(String input){new java.util.Date();}" + "}";

	private ByteCodeAnalyser bca;

	private static InMemoryClassLoader classes;

	@BeforeClass
	public static void init() {
		classes = InMemoryCompiler.compileClasses(new String[] { if1_source, if2_source,
				supercls_source, cls_source }, new String[] { if1_name, if2_name, supercls_name, cls_name });
		
	}

	@Before
	public void before() throws Exception {
		bca = ByteCodeAnalyser.analyseClassStream(DomainFactory.creator(), classes.getByteCodeAsStream(PKG + cls_name), true);
	}

	@Test
	public void getSimpleName() {
		assertEquals("cls", bca.getSimpleName());
	}

	@Test
	public void getPackageName() {
		assertEquals("example", bca.getPackageName());
	}

	@Test
	public void isAnonymous() {
		assertFalse(bca.getModifiers().contains(Modifiers.ANONYMOUS));
	}

	@Test
	public void getExtends() {
		assertEquals("example.supercls", bca.getExtends());
	}

	@Test
	public void getImplements() {
		assertEquals(2, bca.getImplements().size());
		assertTrue(bca.getImplements().contains("example.if1"));
		assertTrue(bca.getImplements().contains("example.if2"));
	}

	@Test
	public void getFields() {
		assertEquals("example.cls.samplefield", bca.getFields().get(0).getSignature());
		assertFalse((Boolean) bca.getFields().get(0).isPrivate());
	}

	@Test
	public void getMethods() {
		assertEquals(3, bca.getMethods().size());
		assertEquals("example/cls.<init>:()V", bca.getMethods().get(0).getSignature());
		assertEquals("example/cls.foo:(Ljava/lang/String;)V", bca.getMethods().get(1).getSignature());
		assertEquals("example/cls.<clinit>:()V", bca.getMethods().get(2).getSignature());
	}

	@Test
	public void getUsedClassNames() {
		assertTrue(bca.getUsedClassNames().contains("java/util/Date"));
	}

}
