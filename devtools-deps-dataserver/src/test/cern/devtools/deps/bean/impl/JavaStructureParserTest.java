package cern.devtools.deps.bean.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import java.io.IOException;
import java.util.Iterator;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import cern.devtools.deps.bean.impl.ByteCodeDescriptor;
import cern.devtools.deps.bean.impl.JavaStructureParser;
import cern.devtools.deps.domain.ApiClass;
import cern.devtools.deps.domain.Product;
import cern.devtools.deps.domain.creation.DomainFactory;
import cern.devtools.deps.memcomp.InMemoryClassLoader;
import cern.devtools.deps.memcomp.InMemoryCompiler;

/**
 * This test is intended to analyse if the proper structure is extracted from a jar file. 
 * 
 * @author Donat Csikos
 *
 */
public class JavaStructureParserTest {

	// private static final String PKG = "cern.example.";

	private static final String cls_A_name = "A";
	private static final String cls_A_source = ""
			+ "package cern.example;                                                                              \n"
			+ "                                                                                                   \n"
			+ "public class A {                                                                                   \n"
			+ "    public static String a = \"fval\";                                                             \n"
			+ "    public void a() { A.a = \"mod\"; }                                                             \n"
			+ "}";
	private static final String cls_B_name = "B";
	private static final String cls_B_source = ""
			+ "package cern.example;                                                                              \n"
			+ "import java.util.Date;                                                                             \n"
			+ "                                                                                                   \n"
			+ "public class B {                                                                                   \n"
			+ "    public String b(Date d) { return d.toString(); }                                               \n"
			+ "    public static void bs() { System.out.println(\"Static function\"); }                           \n"
			+ "}";
	private static final String cls_C_name = "C";
	private static final String cls_C_source = ""
			+ "package cern.example;                                                                              \n"
			+ "                                                                                                   \n"
			+ "public interface C {                                                                               \n"
			+ "    public void c();                                                                               \n"
			+ "}";
	private static final String cls_D_name = "D";
	private static final String cls_D_source = ""
			+ "package cern.example;                                                                              \n"
			+ "import java.util.LinkedList;                                                                       \n"
			+ "import java.util.Date;                                                                             \n"
			+ "import cern.example.B;                                                                             \n"
			+ "                                                                                                   \n"
			+ "public class D<T> extends B implements C {                                                         \n"
			+ "    @Override public String b(Date d) { System.out.println(\"Overriden method\"); return \"d\"; }  \n"
			+ "    @Override public void c() { B b = new B(); b.b(new Date()); }                                  \n"
			+ "    public LinkedList<T> d() { return new LinkedList<T>(); }                                       \n"
			+ "    public <V> LinkedList<V> dd() { return new LinkedList<V>(); }                                  \n"
			+ "                                                                                                   \n"
			+ "    public class E {                                                                               \n"
			+ "        public void e() { System.out.println(\"Inner class function\"); }                          \n"
			+ "    }                                                                                              \n"
			+ "}";
	private static final String cls_F_name = "F";
	private static final String cls_F_source = ""
			+ "package cern.example;                                                                              \n"
			+ "                                                                                                   \n"
			+ "public class F {                                                                                   \n"
			+ "    public void f() { Thread t = new Thread() { public void run() { System.out.println(A.a); } }; }\n"
			+ "    public void ff() {  System.out.println(A.a); }                                                 \n"
			+ "}";

	private Product p;

	private ApiClass A;
	// private ApiClass B;
	// private ApiClass C;
	private ApiClass D;
	private ApiClass E;
	// private ApiClass F;
	private ApiClass FAnon;

	private static InMemoryClassLoader classLoader;

	@BeforeClass
	public static void init() {
		classLoader = InMemoryCompiler.compileClasses(new String[] { cls_A_source, cls_B_source, cls_C_source,
				cls_D_source, cls_F_source },
				new String[] { cls_A_name, cls_B_name, cls_C_name, cls_D_name, cls_F_name });
	}

	@Before
	public void before() throws IOException {

		// call class parsing
		JavaStructureParser parser = JavaStructureParser.parseDescriptor(DomainFactory.creator(), new ByteCodeDescriptor("name",
				classLoader.getAllByteCodes()), true);

		// check found product
		p = parser.getProduct();

		Iterator<ApiClass> aci = p.getClasses().iterator();
		FAnon = aci.next();
		A = aci.next();
		aci.next();
		aci.next();
		// B = p.getClasses().get(2);
		// C = p.getClasses().get(3);
		D = aci.next();
		aci.next();
		// F = p.getClasses().get(5);
		E = aci.next();
	}

	@Test
	@Ignore
	public void productDetails() throws Exception {

		assertEquals("name", p.getName());
		assertEquals(1, p.getVersions().size());
		assertEquals("1.0.0", p.getVersions().get(0));
		assertEquals("", p.getContainingFolders());

		// check found apiClass
		assertEquals(7, p.getClasses().size());
	}

	@Test
	@Ignore
	public void names() {
		assertEquals("cern.example.A", A.getFqName());
		assertEquals("cern.example.D$E", E.getFqName());
		assertEquals("cern.example.F$1", FAnon.getFqName());
	}

	@Test
	@Ignore
	public void anon() {
		assertFalse((Boolean) A.isAnonymous());
		assertTrue((Boolean) FAnon.isAnonymous());
	}

	@Test
	@Ignore
	public void inheritance() {
		assertEquals("cern.example.B", D.getExtends());
		assertEquals("cern.example.C", D.getImplements());
	}
}
