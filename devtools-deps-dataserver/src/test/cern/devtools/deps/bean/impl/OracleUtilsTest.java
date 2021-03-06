package cern.devtools.deps.bean.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.EnumSet;
import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import cern.devtools.deps.bean.impl.OracleUtils;
import cern.devtools.deps.bean.impl.OracleUtils.Column;
import cern.devtools.deps.bean.impl.OracleUtils.Table;
import cern.devtools.deps.domain.Field;
import cern.devtools.deps.domain.Modifiers;
import cern.devtools.deps.domain.creation.DomainFactory;

@Ignore
public class OracleUtilsTest {

	OracleUtils utils;

	@Before
	public void before() throws SecurityException, NoSuchFieldException, IllegalArgumentException,
			IllegalAccessException {
		utils = new OracleUtils(getMockedJdbc());
	}

	private static JdbcTemplate getMockedJdbc() {
		JdbcTemplate jdbc = mock(JdbcTemplate.class);
		when(jdbc.batchUpdate(any(String[].class))).thenReturn(null);
		return jdbc;
	}

	@Test(expected = IllegalArgumentException.class)
	public void testConstructor() {
		new OracleUtils(null);
	}

	@Test
	public void testSqlGeneration() {
		String sql = OracleUtils.sqlDeleteByColumn(Table.CLASSES, Column.CLASSES_CLASS_ID);
		System.out.println(sql);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testIncorrectParams() {
		OracleUtils.checkProvidedColumns(Table.CLASSES, Column.PRODUCTS_NAME);
	}

	@Test
	public void sqlInsertRow() {
		String sql = OracleUtils.sqlInsertRow(Table.CLASSES, Column.CLASSES_CLASS_ID, Column.CLASSES_SIMPLE_NAME);
		assertEquals("insert into classes(class_id,simple_name) values (?,?)", sql);
	}

	@Test(expected = NullPointerException.class)
	public void sqlSelectByCandidates() {
		List<String> cands = null;
		OracleUtils.sqlSelectByCandidates(Table.CLASSES, Column.CLASSES_CLASS_ID, cands);
	}

	@Test(expected = IllegalArgumentException.class)
	public void sqlSelectByCandidates2() {
		List<String> cands = Collections.emptyList();
		OracleUtils.sqlSelectByCandidates(Table.CLASSES, Column.CLASSES_CLASS_ID, cands);
	}

	@Test
	public void sqlSelectByCandidates3() {
		List<String> cands = new LinkedList<String>();
		cands.add("a"); cands.add("b");
		String sql = OracleUtils.sqlSelectByCandidates(Table.CLASSES, Column.CLASSES_CLASS_ID, cands);
		assertEquals("select class_id, fully_qualified_name, interface_names, package_name, product_id, " +
				"simple_name, super_class_name, versions from classes where class_id in ('a','b')", sql);
	}
	
	@Test
	public void sqlSelectByColumn() {
		String sql = OracleUtils.sqlSelectByColumn(Table.FIELDS, Column.FIELDS_SIGNATURE);
		assertEquals("select field_id, signature, versions, class_id from fields where signature = ?", sql);
	}
	
	@Test
	public void sqlUpdate() {
		String sql = OracleUtils.sqlUpdate(Table.METHODS, Column.METHODS_METHOD_ID, Column.METHODS_SIGNATURE);
		assertEquals("update methods set signature = ? where method_id = ?", sql);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void sqlUpdate2() {
		OracleUtils.sqlUpdate(Table.METHODS, Column.METHODS_METHOD_ID);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void batchInsertFields() {
		utils.batchInsertFields("1.0.0", 1l, null);
	}
	
	@Test
	public void batchInsertFields2() {
		List<Field> fields = new LinkedList<Field>();
		fields.add(DomainFactory.creator().createField("MyClass.FIELD", EnumSet.noneOf(Modifiers.class)));
		utils.batchInsertFields("1.0.0", 1l, fields);
	}
}
