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
package cern.devtools.deps.bean.impl;

import static cern.devtools.deps.bean.impl.OracleUtils.Column.CLASSES_CLASS_ID;
import static cern.devtools.deps.bean.impl.OracleUtils.Column.CLASSES_INTERFACE_NAMES;
import static cern.devtools.deps.bean.impl.OracleUtils.Column.CLASSES_PACKAGE_NAME;
import static cern.devtools.deps.bean.impl.OracleUtils.Column.CLASSES_PRODUCT_ID;
import static cern.devtools.deps.bean.impl.OracleUtils.Column.CLASSES_SIMPLE_NAME;
import static cern.devtools.deps.bean.impl.OracleUtils.Column.CLASSES_SUPERCLASS_NAME;
import static cern.devtools.deps.bean.impl.OracleUtils.Column.CLASSES_VERSIONS;
import static cern.devtools.deps.bean.impl.OracleUtils.Column.DEP_CLASSDEF_FROM_CLASS_ID;
import static cern.devtools.deps.bean.impl.OracleUtils.Column.DEP_CLASSDEF_TO_CLASS_ID;
import static cern.devtools.deps.bean.impl.OracleUtils.Column.DEP_FIELDREF_FROM_METHOD_ID;
import static cern.devtools.deps.bean.impl.OracleUtils.Column.DEP_FIELDREF_TO_FIELD_ID;
import static cern.devtools.deps.bean.impl.OracleUtils.Column.DEP_INH_FROM_CLASS_ID;
import static cern.devtools.deps.bean.impl.OracleUtils.Column.DEP_INH_TO_CLASS_ID;
import static cern.devtools.deps.bean.impl.OracleUtils.Column.DEP_MCALL_FROM_METHOD_ID;
import static cern.devtools.deps.bean.impl.OracleUtils.Column.DEP_MCALL_TO_METHOD_ID;
import static cern.devtools.deps.bean.impl.OracleUtils.Column.DEP_OVERR_FROM_METHOD_ID;
import static cern.devtools.deps.bean.impl.OracleUtils.Column.DEP_OVERR_TO_METHOD_ID;
import static cern.devtools.deps.bean.impl.OracleUtils.Column.METHODS_METHOD_ID;
import static cern.devtools.deps.bean.impl.OracleUtils.Column.PRODUCTS_PRODUCT_ID;
import static cern.devtools.deps.bean.impl.OracleUtils.Table.CLASSES;
import static cern.devtools.deps.bean.impl.OracleUtils.Table.DEP_CLASSDEF;
import static cern.devtools.deps.bean.impl.OracleUtils.Table.DEP_FIELDREF;
import static cern.devtools.deps.bean.impl.OracleUtils.Table.DEP_INH;
import static cern.devtools.deps.bean.impl.OracleUtils.Table.DEP_MCALL;
import static cern.devtools.deps.bean.impl.OracleUtils.Table.DEP_OVERR;
import static cern.devtools.deps.bean.impl.OracleUtils.Table.METHODS;
import static cern.devtools.deps.bean.impl.OracleUtils.Table.PRODUCTS;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import cern.devtools.deps.domain.ApiClass;
import cern.devtools.deps.domain.CodeElement;
import cern.devtools.deps.domain.DependencyType;
import cern.devtools.deps.domain.Field;
import cern.devtools.deps.domain.Method;
import cern.devtools.deps.domain.Modifiers;
import cern.devtools.deps.domain.Product;
import cern.devtools.deps.domain.creation.DomainFactory;

/**
 * This class holds all the direct SQL operations which is used by the {@link OracleDatabaseDao}.<p>
 * 
 * The class is heavily relies on the database schema. The schema's ddl can be found in the ${project_root}/ddl.sql
 * file. It tries to map the tables, columns for the SQL operations and tries to a basic level validation over the
 * operations (e.g. in the {@link #checkProvidedColumns(Table, Column...)}).<p>
 * 
 * @author Donat Csikos <dcsikos@cern.ch>
 */
public final class OracleUtils {

	/**
	 * Enumeration, which contains all columns which present in the database.
	 * 
	 * @author Donat Csikos <dcsikos@cern.ch>
	 */
	public enum Column {
		// Columns in the Classes table.
		CLASSES_CLASS_ID("class_id", Types.INTEGER),
		CLASSES_FQ_NAME("fully_qualified_name", Types.VARCHAR),
		CLASSES_INTERFACE_NAMES("interface_names", Types.VARCHAR),
		CLASSES_PACKAGE_NAME("package_name", Types.VARCHAR),
		CLASSES_PRODUCT_ID("product_id", Types.INTEGER),
		CLASSES_SIMPLE_NAME("simple_name", Types.VARCHAR),
		CLASSES_SUPERCLASS_NAME("super_class_name", Types.VARCHAR),
		CLASSES_VERSIONS("versions", Types.VARCHAR),
		DEP_CLASSDEF_FROM_CLASS_ID("from_class_id", Types.INTEGER),
		DEP_CLASSDEF_TO_CLASS_ID("to_class_id", Types.INTEGER),
		DEP_FIELDREF_FROM_METHOD_ID("from_method_id", Types.INTEGER),
		DEP_FIELDREF_TO_FIELD_ID("to_field_id", Types.INTEGER),

		// Columns from the dependency tables
		DEP_INH_FROM_CLASS_ID("from_class_id", Types.INTEGER),
		DEP_INH_TO_CLASS_ID("to_class_id", Types.INTEGER),
		DEP_MCALL_FROM_METHOD_ID("from_method_id", Types.INTEGER),
		DEP_MCALL_TO_METHOD_ID("to_method_id", Types.INTEGER),
		DEP_OVERR_FROM_METHOD_ID("from_method_id", Types.INTEGER),
		DEP_OVERR_TO_METHOD_ID("to_method_id", Types.INTEGER),
		FIELDS_CLASS_ID("class_id", Types.INTEGER),

		// Columns in the Fields table.
		FIELDS_FIELD_ID("field_id", Types.INTEGER),
		FIELDS_SIGNATURE("signature", Types.VARCHAR),
		FIELDS_VERSIONS("versions", Types.VARCHAR),
		METHODS_CLASS_ID("class_id", Types.INTEGER),

		// Columns in the Methods table.
		METHODS_SIGNATURE("signature", Types.VARCHAR),
		METHODS_IS_STATIC("is_static", Types.BOOLEAN),
		METHODS_METHOD_ID("method_id", Types.INTEGER),
		METHODS_VERSIONS("versions", Types.VARCHAR),

		// Columns in the Products table.
		PRODUCTS_NAME("name", Types.VARCHAR),
		PRODUCTS_PATH("product_path", Types.VARCHAR),
		PRODUCTS_CONTAINING_FOLDERS("containing_folder", Types.VARCHAR),
		PRODUCTS_PRODUCT_ID("product_id", Types.INTEGER),
		PRODUCTS_VERSIONS("versions", Types.VARCHAR);

		// The name of the column.
		private final String name;

		// The type of the column.
		private final int sqlType;

		/**
		 * Constructor, which stores the name and the type of the column.
		 * 
		 * @param name The the name of the column in the database.
		 * @param sqlType The type of the column in the database.
		 */
		Column(String name, int sqlType) {
			this.name = name;
			this.sqlType = sqlType;
		}

		/**
		 * Returns the name of the column in the database.
		 * 
		 * @return The name of the column in the database.
		 */
		public String cname() {
			return name;
		}

		/**
		 * The type of the column in the database.
		 * 
		 * @return The type of the column in the database.
		 */
		public int sqlType() {
			return sqlType;
		}

		/**
		 * Returns the name of the column in the database.
		 */
		
		public String toString() {
			return name;
		}
	}

	/**
	 * <p>
	 * Enumeration which holds all the tables from the database.
	 * </p>
	 * <p>
	 * The parameters are used to maintain database naming and validation SQL query creation.
	 * </p>
	 * 
	 * @author Donat Csikos <dcsikos@cern.ch>
	 */
	public enum Table {
		/**
		 * Table holding {@link ApiClass} instances.
		 */
		CLASSES(
				"classes", ApiClass.class, Column.CLASSES_CLASS_ID, Column.CLASSES_FQ_NAME,
				Column.CLASSES_INTERFACE_NAMES, Column.CLASSES_PACKAGE_NAME, Column.CLASSES_PRODUCT_ID,
				Column.CLASSES_SIMPLE_NAME, Column.CLASSES_SUPERCLASS_NAME, Column.CLASSES_VERSIONS),

		/**
		 * Table storing class usage dependencies between {@link ApiClass} instances.
		 */
		DEP_CLASSDEF(
				"class_definition_dependencies", Void.class, Column.DEP_CLASSDEF_FROM_CLASS_ID,
				Column.DEP_CLASSDEF_TO_CLASS_ID),

		/**
		 * Table storing field references dependencies between {@link Method} and {@link Field} and instances.
		 */
		DEP_FIELDREF(
				"field_reference_dependencies", Void.class, Column.DEP_FIELDREF_FROM_METHOD_ID,
				Column.DEP_FIELDREF_TO_FIELD_ID),

		/**
		 * Table storing inheritance dependencies between {@link ApiClass}instances.
		 */
		DEP_INH("inheritance_dependencies", Void.class, Column.DEP_INH_FROM_CLASS_ID, Column.DEP_INH_TO_CLASS_ID),

		/**
		 * Table storing inheritance dependencies between {@link Method} instances.
		 */
		DEP_MCALL(
				"method_call_dependencies", Void.class, Column.DEP_MCALL_FROM_METHOD_ID, Column.DEP_MCALL_TO_METHOD_ID),

		/**
		 * Table storing override dependencies between {@link Method} instances.
		 */
		DEP_OVERR("override_dependencies", Void.class, Column.DEP_OVERR_FROM_METHOD_ID, Column.DEP_OVERR_TO_METHOD_ID),

		/**
		 * Table holding {@link Field} instances.
		 */
		FIELDS(
				"fields", Field.class, Column.FIELDS_FIELD_ID, Column.FIELDS_SIGNATURE, Column.FIELDS_VERSIONS,
				Column.FIELDS_CLASS_ID),

		/**
		 * Table holding {@link Method} instances.
		 */
		METHODS(
				"methods", Method.class, Column.METHODS_METHOD_ID, Column.METHODS_SIGNATURE, Column.METHODS_IS_STATIC,
				Column.METHODS_VERSIONS, Column.METHODS_CLASS_ID),

		/**
		 * Table holding {@link Product} instances.
		 */
		PRODUCTS(
				"products", Product.class, Column.PRODUCTS_PRODUCT_ID, Column.PRODUCTS_NAME, Column.PRODUCTS_VERSIONS,
				Column.PRODUCTS_CONTAINING_FOLDERS, Column.PRODUCTS_PATH);

		/**
		 * The columns, which form the table itself.
		 */
		private final EnumSet<Column> columns;

		/**
		 * The string representation of the columns divided by commas. E.g.: id, name, version. Used for querying all
		 * attributes from the database.
		 */
		private final String columnsCSList;

		/**
		 * The class, which is stored in the table.
		 */
		private final Class<?> handledClass;

		/**
		 * The name of the table.
		 */
		private final String name;

		/**
		 * Constructor.
		 * 
		 * @param name The name of the table.
		 * @param handledClass The handled class, which is stored in the table. Of no one, then the {@link Void} class
		 *        should be sufficient.
		 * @param columns The list of the contained columns which forms the table.
		 */
		Table(String name, Class<?> handledClass, Column... columns) {
			// save name
			this.name = name;

			// the class which should be stored in the represented table
			this.handledClass = handledClass;

			// save columns
			this.columns = EnumSet.copyOf(Arrays.asList(columns));

			BeanUtils.listToString(Arrays.asList(columns), ",");

			// create comma separated list from the columns
			columnsCSList = BeanUtils.listToString(Arrays.asList(columns), ", ");
		}

		/**
		 * Returns the contained columns.
		 * 
		 * @return The contained columns.
		 */
		Set<Column> columns() {
			return this.columns;
		}

		/**
		 * The string representation of the columns divided by commas. E.g.: id, name, version. Used for querying all
		 * attributes from the database.
		 * 
		 * @return The comma separated list of the columns
		 */
		String columnsCSList() {
			return this.columnsCSList;
		}

		/**
		 * Returns the class, which is stored in the table.
		 * 
		 * @return The class, which is stored in the table.
		 */
		Class<?> handledClass() {
			return this.handledClass;
		}

		/**
		 * Returns the name of the table;
		 * 
		 * @return The name of the table;
		 */
		String tname() {
			return this.name;
		}

		
		public String toString() {
			return this.name;
		}
	}

	/**
	 * Separator character for hyphen string lists in the database.
	 */
	private static final char SEPARATOR = ' ';

	/**
	 * Verifies that all of the provided column is in the same table
	 * 
	 * @param table The table where the columns should be present.
	 * @param columns The list of columns.
	 */
	protected static void checkProvidedColumns(Table table, Column... columns) {
		for (Column c : columns) {
			if (!table.columns.contains(c)) {
				throw new IllegalArgumentException("The specied column is not part of the table");
			}
		}
	}

	/**
	 * Creates an SQL delete string. Provides the string like <code>"delete from MyTable where columName = ?"</code>.
	 * 
	 * @param from Where to delete.
	 * @param critColumn column for the where clause
	 * @return The SQL delete command.
	 */
	protected static String sqlDeleteByColumn(Table from, Column critColumn) {
		checkProvidedColumns(from, critColumn);
		return "delete from " + from.tname() + " where " + critColumn.cname() + " = ? ";
	}

	/**
	 * Creates an SQL insert string. Provides a string like
	 * <code>"insert into Mytable(colum1, column2) values (?, ?)"</code>.
	 * 
	 * @param into The table name to make the insert.
	 * @param columns The list of columns which should be addded. The equal number of '?' will appear in the values
	 *        clause.
	 * @return The SQL insert command.
	 */
	protected static String sqlInsertRow(Table into, Column... columns) {
		checkProvidedColumns(into, columns);
		String sql = "insert into " + into.tname() + "(";
		sql += BeanUtils.listToString(Arrays.asList(columns), ',') + ") values (";
		List<String> qMarks = new LinkedList<String>();
		for (@SuppressWarnings("unused")
		Column c : columns) {
			qMarks.add("?");
		}
		sql += BeanUtils.listToString(qMarks, ',') + ")";
		return sql;
	}

	/**
	 * Creates an SQL select string with an "in" parameter after the where keyword. Provides a string like
	 * <code>"select col_1, col_2 col_n from Mytable where col2 in ('a', 'b', 'c', 'd')"</code>.
	 * 
	 * @param from The table name to perform the select operation on.
	 * @param critColumn The column where the criteria is applied. Must be a String format, or else the result will
	 *        cause SQL error.
	 * @param candidates The items for the in clause.
	 * @return The SQL select command.
	 */
	protected static String sqlSelectByCandidates(Table from, Column critColumn, List<String> candidates) {
		checkProvidedColumns(from, critColumn);
		if (candidates.size() == 0) {
			throw new IllegalArgumentException("Parameter can't be an empty list");
		}
		String sql = "select " + from.columnsCSList() + " from " + from.tname() + " where ";
		// + critColumn.cname() + " in ('" + BeanUtils.listToString(candidates, "','") + "')";
		for (String c : candidates) {
			sql += critColumn.cname() + "='" + c + "' or ";
		}
		sql = sql.substring(0, sql.length() - 4);
		return sql;
	}

	/**
	 * Creates an SQL select string with a simple where clause. Provides a string like
	 * <code>"select col_1, col_2 col_n from Mytable where col2 = ?"</code>.
	 * 
	 * @param from The table name to perform the select operation on.
	 * @param critColumn The criteria column.
	 * @return The SQL select command.
	 */
	protected static String sqlSelectByColumn(Table from, Column critColumn) {
		checkProvidedColumns(from, critColumn);
		return "select " + from.columnsCSList() + " from " + from.tname() + " where " + critColumn.cname() + " = ?";
	}

	/**
	 * Creates an SQL update string. Provides a string like
	 * <code>"update Mytable set col_1 = ?, col_2 = ? where id_col = ?"</code>.
	 * 
	 * @param table The name of the table to update.
	 * @param idColumn The column which identifies all rows to update.
	 * @param columns The columns to update.
	 * @return The SQL update command.
	 */
	protected static String sqlUpdate(Table table, Column idColumn, Column... columns) {
		checkProvidedColumns(table, columns);
		if (columns.length == 0) {
			throw new IllegalArgumentException("At least one column should be provided");
		}
		String sql = "update " + table.tname() + " set ";
		List<String> list = new LinkedList<String>();
		for (Column c : columns) {
			list.add(c + " = ?");
		}
		sql += BeanUtils.listToString(list, ',');
		sql += " where " + idColumn.cname() + " = ?";
		return sql;
	}

	/**
	 * Spring JdbcTemplate object for mapping a row from the {@link Table#CLASSES} table to a {@link ApiClass} instance.
	 */
	@SuppressWarnings("rawtypes")
	private final RowMapper apiClassRowMapper = new RowMapper() {
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			// Determines fully qualified name
			String packageName = rs.getString(CLASSES_PACKAGE_NAME.cname());
			String simpleName = rs.getString(CLASSES_SIMPLE_NAME.cname());
			String fqName = null;
			if (packageName == null || "".equals(packageName)) {
				fqName = simpleName;
			} else {
				fqName = packageName + "." + simpleName;
			}

			// Creates new class instance.
			ApiClass result = DomainFactory.creator().createApiClass(fqName,
					rs.getString(CLASSES_SUPERCLASS_NAME.cname()), rs.getString(CLASSES_INTERFACE_NAMES.cname()),
					EnumSet.noneOf(Modifiers.class));

			// Parses versions from the stored flat format (where the versions stored in a single string separated by
			// the SEPARATOR character).
			result.setId(rs.getLong(CLASSES_CLASS_ID.cname()));
			String versionString = rs.getString(CLASSES_VERSIONS.cname());
			if (versionString != null && versionString.length() > 0) {
				result.getVersions().addAll(BeanUtils.stringToList(versionString, SEPARATOR));
			}

			// Create dummy product container, which only contains it's id.
			Product container = DomainFactory.creator().createProduct("");
			container.setId(rs.getLong(CLASSES_PRODUCT_ID.cname()));
			result.setProduct(container);
			container.getClasses().add(result);

			return result;
		}
	};

	/**
	 * Spring JdbcTemplate object for mapping a row from the {@link Table#FIELDS} table to an {@link Field} instance.
	 */
	@SuppressWarnings("rawtypes")
	private final RowMapper fieldRowMapper = new RowMapper() {
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			// Create new Field instanca
			Field result = DomainFactory.creator().createField(rs.getString(Column.FIELDS_SIGNATURE.cname()),
					EnumSet.noneOf(Modifiers.class));

			// Setup id and versions
			result.setId(rs.getLong(Column.FIELDS_FIELD_ID.cname()));
			result.getVersions()
					.addAll(BeanUtils.stringToList(rs.getString(Column.FIELDS_VERSIONS.cname()), SEPARATOR));

			return result;
		}
	};

	/**
	 * Spring object for execution jdbc operations.
	 */
	private final JdbcWrapper jdbc;

	/**
	 * Spring JdbcTemplate object for mapping a row from the {@link Table#METHODS} table to a {@link Method} instance.
	 */
	@SuppressWarnings("rawtypes")
	private final RowMapper methodRowMapper = new RowMapper() {
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			// Setup modifiers for the instance we are about to create.
			boolean isStatic = rs.getBoolean(Column.METHODS_IS_STATIC.cname());
			EnumSet<Modifiers> modifiers = EnumSet.noneOf(Modifiers.class);

			if (isStatic) {
				modifiers.add(Modifiers.STATIC);
			}

			// Create instance.
			Method result = DomainFactory.creator().createMethod(rs.getString(Column.METHODS_SIGNATURE.cname()),
					modifiers);

			// Setup id and version from the database.
			result.setId(rs.getLong(Column.METHODS_METHOD_ID.cname()));
			result.getVersions().addAll(
					BeanUtils.stringToList(rs.getString(Column.METHODS_VERSIONS.cname()), SEPARATOR));

			// Create dummy ApiClass container which contains only the id attribute.
			long acid = rs.getLong(Column.METHODS_CLASS_ID.cname());
			if (acid > 0) {
				ApiClass ac = DomainFactory.creator().createApiClass("", EnumSet.noneOf(Modifiers.class));
				ac.setId(acid);
				result.setApiClass(ac);
			}

			return result;
		}
	};

	/**
	 * Spring JdbcTemplate object for mapping a row from the {@link Table#PRODUCTS} table to a {@link Product} instance.
	 */
	@SuppressWarnings("rawtypes")
	private final RowMapper productRowMapper = new RowMapper() {
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			// Create instance.
			Product result = DomainFactory.creator().createProduct(rs.getString(Column.PRODUCTS_NAME.cname()), "",
					rs.getString(Column.PRODUCTS_CONTAINING_FOLDERS.cname()),
					rs.getString(Column.PRODUCTS_PATH.cname()));

			// Setup id and versions attributes.
			result.setId(rs.getLong(Column.PRODUCTS_PRODUCT_ID.cname()));
			String versionString = rs.getString(Column.PRODUCTS_VERSIONS.cname());
			result.getVersions().clear();
			if (versionString != null && versionString.length() > 0) {
				result.getVersions().addAll(BeanUtils.stringToList(versionString, SEPARATOR));
			}

			return result;
		}
	};

	/**
	 * Constructor, which initialises the JDBC template to access the database.
	 * 
	 * @param jdbc Spring JDBC template to perform the SQL operations.
	 */
	public OracleUtils(JdbcTemplate jdbc) {
		if (jdbc == null) {
			throw new IllegalArgumentException("The datasource cannot be null.");
		}
		this.jdbc = new JdbcWrapper(jdbc);
		try {
			this.jdbc.getDataSource().getConnection().setAutoCommit(false);
			this.jdbc.getDataSource().getConnection().setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Performs a batch insert on the {@link Table#FIELDS} table.
	 * 
	 * @param version The only one and default version, which the method should contain.
	 * @param apiClassId The container ApiClass' id.
	 * @param fields The List of field instances, which should be stored.
	 */
	public void batchInsertFields(final String version, final long apiClassId, final Collection<Field> fields) {
		// Parameter inspection.
		if (version == null || apiClassId == 0l || fields == null) {
			throw new IllegalArgumentException("Wrong parameters supported.");
		}

		final List<Field> fieldList = new LinkedList<Field>(fields);
		
		if (fieldList != null && fieldList.size() > 0) {
			// Create an insert operation and perform batch insert for every items in the fields list.
			String sql = sqlInsertRow(Table.FIELDS, Column.FIELDS_SIGNATURE, Column.FIELDS_VERSIONS,
					Column.FIELDS_CLASS_ID);
			jdbc.batchUpdate(sql, new BatchPreparedStatementSetter() {

				public int getBatchSize() {
					// Insert all the fields.
					return fieldList.size();
				}

				
				public void setValues(PreparedStatement ps, int i) throws SQLException {
					// The parameter i goes from 0 to fields.size()-1.
					Field f = fieldList.get(i);

					// Set the parameters for the prepared statemenet.
					ps.setString(1, f.getSignature());
					ps.setString(2, version);
					ps.setLong(3, apiClassId);
				}
			});
		}
	}

	/**
	 * Performs a batch insert on the {@link Table#METHODS} table.
	 * 
	 * @param version The only one and default version, which the field should contain.
	 * @param apiClassId The container ApiClass' id.
	 * @param methods The List of method instances, which should be stored.
	 */
	public void batchInsertMethods(final String version, final long apiClassId, final Collection<Method> methods) {
		// Parameter inspection.
		if (version == null || apiClassId == 0l || methods == null) {
			throw new IllegalArgumentException("Wrong parameters supported.");
		}
		
		final List<Method> methodList = new LinkedList<Method>(methods);

		if (methodList != null && methodList.size() > 0) {
			// Create an insert operation and perform batch insert for every items in the methods list.
			String query = sqlInsertRow(Table.METHODS, Column.METHODS_SIGNATURE, Column.METHODS_IS_STATIC,
					Column.METHODS_VERSIONS, Column.METHODS_CLASS_ID);
			jdbc.batchUpdate(query, new BatchPreparedStatementSetter() {
				
				public int getBatchSize() {
					// Insert all the fields.
					return methodList.size();
				}

				
				public void setValues(PreparedStatement ps, int i) throws SQLException {
					// The parameter i goes from 0 to fields.size()-1.
					Method m = methodList.get(i);

					// Set the parameters for the prepared statemenet.
					ps.setString(1, m.getSignature());
					ps.setBoolean(2, m.isStatic());
					ps.setString(3, version);
					ps.setLong(4, apiClassId);
				}
			});
		}
	}

	/**
	 * Deletes the specified product. If the argument contains a valid (> 0) id, then the delete is performed on the row
	 * with this id. If there is no id, then the delete will be based on the product's name.
	 * 
	 * @param product The product to delete.
	 */
	public void deleteProduct(Product product) {
		// Parameter check.
		if (product == null) {
			throw new IllegalArgumentException("Product can't be null");
		}

		// If the parameter has a database id, then we can use this attribute for deleting the proper row.
		if (product.getId() != 0) {
			String sql = sqlDeleteByColumn(Table.PRODUCTS, Column.PRODUCTS_PRODUCT_ID);
			jdbc.update(sql, new Object[] { product.getId() });
		}
		// If the parameter does not contain a database id, than it means that the instance is created programmatically
		// and we have to find it by it's name and delete it.
		else if (!"".equals(product.getName())) {
			String sql = sqlDeleteByColumn(Table.PRODUCTS, Column.PRODUCTS_NAME);
			jdbc.update(sql, new Object[] { product.getName() });
		}
		// If there is no name nor id available, than calling this operation is invalid.
		else {
			throw new IllegalArgumentException("Product delete failed because no id nor path was specified.");
		}
	}

	/**
	 * Returns all element based on it's container's primary key.
	 * 
	 * @param clazz The searched type.
	 * @param id The id of the container.
	 * @return The list of entities which satisfies the query.
	 */
	@SuppressWarnings("unchecked")
	public <T extends CodeElement> List<T> findByForeignKey(Class<? extends CodeElement> clazz, long id) {
		if (id == 0l) {
			return Collections.emptyList();
		}

		if (ApiClass.class.isAssignableFrom(clazz)) {
			String query = sqlSelectByColumn(Table.CLASSES, Column.CLASSES_PRODUCT_ID);
			return jdbc.query(query, new Object[] { id }, apiClassRowMapper);
		} else if (Method.class.isAssignableFrom(clazz)) {
			String query = sqlSelectByColumn(Table.METHODS, Column.METHODS_CLASS_ID);
			return jdbc.query(query, new Object[] { id }, methodRowMapper);
		} else if (Field.class.isAssignableFrom(clazz)) {
			String query = sqlSelectByColumn(Table.FIELDS, Column.FIELDS_CLASS_ID);
			return jdbc.query(query, new Object[] { id }, fieldRowMapper);
		} else {
			throw new IllegalArgumentException("The class " + clazz.getCanonicalName()
					+ "is not queriable by foreign key.");
		}
	}

	/**
	 * Returns all element which has an equal signature as one of the argument signatures.
	 * 
	 * @param clazz The desired type for the list to return..
	 * @param candidates The list of signatures, which we are looking for.
	 * @return The result list.
	 */
	public <T extends CodeElement> List<T> findBySignature(Class<T> clazz, List<String> candidates) {
		// Parameter check.
		if (candidates == null || candidates.size() == 0) {
			return Collections.emptyList();
		}

		// Choose query and rowMapper instance according the type information.
		String sql = null;
		@SuppressWarnings("rawtypes")
		RowMapper rm = null;
		if (Field.class.isAssignableFrom(clazz)) {
			sql = sqlSelectByCandidates(Table.FIELDS, Column.FIELDS_SIGNATURE, candidates);
			rm = fieldRowMapper;
		} else if (Method.class.isAssignableFrom(clazz)) {
			sql = sqlSelectByCandidates(Table.METHODS, Column.METHODS_SIGNATURE, candidates);
			rm = methodRowMapper;
		} else if (ApiClass.class.isAssignableFrom(clazz)) {
			sql = sqlSelectByCandidates(Table.CLASSES, Column.CLASSES_FQ_NAME, candidates);
			rm = apiClassRowMapper;
		} else {
			throw new IllegalArgumentException("This type is not supported: " + clazz);
		}

		// Run the query. Because the rowMapper returns the proper type, the type checking warning can be suppressed.
		@SuppressWarnings("unchecked")
		List<T> result = jdbc.query(sql, rm);
		return result;
	}

	/**
	 * Find product based on it's path.
	 * 
	 * @param path the name of the product.
	 * @return The product reference.
	 */
	public Product findProductByPath(String path) {
		// Parameter check.
		if (path == null) {
			throw new IllegalArgumentException("Argument should be null");
		}

		// create a SQL command like "select * from Product where name = ?", than execute and return the result.
		String query = sqlSelectByColumn(Table.PRODUCTS, Column.PRODUCTS_PATH);
		return (Product) jdbc.queryForObject(query, new Object[] { path }, productRowMapper);
	}
	
	/**
	 * Find product based on it's name.
	 * 
	 * @param name the name of the product.
	 * @return The product reference.
	 */
	public Product findProductByName(String name) {
		// Parameter check.
		if (name == null) {
			throw new IllegalArgumentException("Argument should be null");
		}

		// create a SQL command like "select * from Product where name = ?", than execute and return the result.
		String query = sqlSelectByColumn(Table.PRODUCTS, Column.PRODUCTS_NAME);
		return (Product) jdbc.queryForObject(query, new Object[] { name }, productRowMapper);
	}

	/**
	 * Find {@link ApiClass} and {@link Product} instance based on their id's.
	 * 
	 * @param clazz The type of items we are looking for
	 * @param id The instance's database id.
	 * @return The instance.
	 */
	@SuppressWarnings("unchecked")
	public <T extends CodeElement> T find(Class<T> clazz, long id) {
		if (Product.class.isAssignableFrom(clazz)) {
			String sql = sqlSelectByColumn(PRODUCTS, PRODUCTS_PRODUCT_ID);
			return (T) jdbc.queryForObject(sql, new Object[] { id }, productRowMapper);
		} else if (ApiClass.class.isAssignableFrom(clazz)) {
			String sql = sqlSelectByColumn(CLASSES, CLASSES_CLASS_ID);
			return (T) jdbc.queryForObject(sql, new Object[] { id }, apiClassRowMapper);
		} else {
			throw new IllegalArgumentException("find() not implemented yet for the class:" + clazz + ".");
		}
	}

	/**
	 * Store {@link ApiClass} instance.
	 * 
	 * @param ac The instance to store.
	 * @return The database id of the stored instance.
	 */
	public long persistApiclass(final ApiClass ac) {
		// Parameter check.
		if (ac == null) {
			throw new IllegalArgumentException("Argument should be null");
		}

		// Insert items and retrieve generated key.
		// Idea: http://static.springsource.org/spring/docs/2.5.6/reference/jdbc.html#jdbc-auto-genereted-keys

		// The SQL insert contains all columns.
		final String sql = sqlInsertRow(Table.CLASSES, Column.CLASSES_PACKAGE_NAME, Column.CLASSES_SIMPLE_NAME,
				Column.CLASSES_PRODUCT_ID, Column.CLASSES_SUPERCLASS_NAME, Column.CLASSES_INTERFACE_NAMES,
				Column.CLASSES_VERSIONS);

		// ID holder object holds the @@IDENTITY value after the insert. Which is specified in the PreparedStatement
		// instantiation in the second parameter.
		KeyHolder keyHolder = new GeneratedKeyHolder();
		PreparedStatementCreator creator = new PreparedStatementCreator() {
			
			public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
				// @@IDENTITY is the CLASSES_CLASS_ID
				PreparedStatement ps = conn.prepareStatement(sql, new String[] { Column.CLASSES_CLASS_ID.cname() });

				// Sets class attributes to the proper fields.
				ps.setString(1, ac.getPackageName() == null ? "" : ac.getPackageName());
				ps.setString(2, ac.getSimpleName());
				ps.setLong(3, ac.getProduct().getId());
				ps.setString(4, ac.getExtends());
				ps.setString(5, ac.getImplements() == null ? "" : ac.getImplements());
				ps.setString(6, BeanUtils.listToString(ac.getVersions(), SEPARATOR));
				return ps;
			}
		};

		// Execute the insert and return the generated id.
		jdbc.update(creator, keyHolder);
		return keyHolder.getKey().longValue();
	}

	/**
	 * Find an elements in the dependency tables and return the correspondents which depend on these items. The proper
	 * dependency table is calculated from the type of the specified dependency type.
	 * 
	 * @param resultType Return type.
	 * @param candidates The instances we want to know the incoming pairs.
	 * @param type The type of the dependency.
	 * @return List of the items which has a dependency on the argument.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public <T extends CodeElement> Set<T> findIncomingPairs(Class<T> resultType,
			List<? extends CodeElement> candidates, DependencyType type) {
		// Parameter check.
		if (candidates.size() == 0) {
			return Collections.emptySet();
		}

		// Construct a string representation of the IDs as string list separated by the ',' character.
		// It will be the parameter of the in clause in the sql strings.
		List<String> ids = new LinkedList<String>();
		for (int i = 0; i < candidates.size(); ++i) {
			ids.add(Long.toString(candidates.get(i).getId()));
		}
		String idList = BeanUtils.listToString(ids, ", ");

		// Construct the SQL queries and execute it based on the type of the dependency.
		// The reason, why it does not use the sqlXX() function to assembe the query is it contains a join operation
		// between the dependency and the CodeElement holder table.
		switch (type) {
		case CLASS_USAGE:
			String sql = "select * from " + CLASSES.tname() + ", " + DEP_CLASSDEF.tname() + " where "
					+ CLASSES_CLASS_ID.cname() + " = " + DEP_CLASSDEF_FROM_CLASS_ID.cname() + " and "
					+ DEP_CLASSDEF_TO_CLASS_ID.cname() + " in (" + idList + ")";
			return new HashSet(jdbc.query(sql, apiClassRowMapper));

		case FIELD_REFERENCE:
			sql = "select * from " + METHODS.tname() + ", " + DEP_FIELDREF.tname() + " where "
					+ METHODS_METHOD_ID.cname() + " = " + DEP_FIELDREF_FROM_METHOD_ID.cname() + " and "
					+ DEP_FIELDREF_TO_FIELD_ID.cname() + " in (" + idList + ")";
			return new HashSet(jdbc.query(sql, methodRowMapper));
		case METHOD_CALL:
			sql = "select * from " + METHODS.tname() + ", " + DEP_MCALL.tname() + " where " + METHODS_METHOD_ID.cname()
					+ " = " + DEP_MCALL_FROM_METHOD_ID.cname() + " and " + DEP_MCALL_TO_METHOD_ID.cname() + " in ("
					+ idList + ")";
			return new HashSet(jdbc.query(sql, methodRowMapper));
		case METHOD_OVERRIDE:
			sql = "select * from " + METHODS.tname() + ", " + DEP_OVERR.tname() + " where " + METHODS_METHOD_ID.cname()
					+ " = " + DEP_OVERR_FROM_METHOD_ID.cname() + " and " + DEP_OVERR_TO_METHOD_ID.cname() + " in ("
					+ idList + ")";
			return new HashSet(jdbc.query(sql, methodRowMapper));
		case CLASS_INHERITANCE:
			sql = "select * from " + CLASSES.tname() + ", " + DEP_INH.tname() + " where " + CLASSES_CLASS_ID.cname()
					+ " = " + DEP_INH_FROM_CLASS_ID.cname() + " and " + DEP_INH_TO_CLASS_ID.cname() + " in (" + idList
					+ ")";
			return new HashSet(jdbc.query(sql, apiClassRowMapper));
		default:
			throw new IllegalArgumentException("The dependency type should not be queried: " + type);
		}
	}

	/**
	 * Stores dependencies in the database. All stored dependency will have the same source part and a different
	 * destination (specified in the "to" argument).
	 * 
	 * @param from The source part of the dependencies.
	 * @param to The destination part of the dependencies.
	 * @param type The type of the dependencies.
	 */
	public void persistDependencies(final CodeElement from, final List<? extends CodeElement> to,
			final DependencyType type) {
		if (to.size() == 0) {
			return;
		}

		// The SQL insert determined by the type of the dependency.
		String sql = null;
		switch (type) {
		case CLASS_INHERITANCE:
			sql = sqlInsertRow(Table.DEP_INH, Column.DEP_INH_FROM_CLASS_ID, Column.DEP_INH_TO_CLASS_ID);
			break;
		case CLASS_USAGE:
			sql = sqlInsertRow(Table.DEP_CLASSDEF, Column.DEP_CLASSDEF_FROM_CLASS_ID, Column.DEP_CLASSDEF_TO_CLASS_ID);
			break;
		case FIELD_REFERENCE:
			sql = sqlInsertRow(Table.DEP_FIELDREF, Column.DEP_FIELDREF_FROM_METHOD_ID, Column.DEP_FIELDREF_TO_FIELD_ID);
			break;
		case METHOD_CALL:
			sql = sqlInsertRow(Table.DEP_MCALL, Column.DEP_MCALL_FROM_METHOD_ID, Column.DEP_MCALL_TO_METHOD_ID);
			break;
		case METHOD_OVERRIDE:
			sql = sqlInsertRow(Table.DEP_OVERR, Column.DEP_OVERR_FROM_METHOD_ID, Column.DEP_OVERR_TO_METHOD_ID);
			break;
		default:
			throw new IllegalArgumentException("The dependency type should not be stored: " + type);
		}

		// Store as a batch update.
		jdbc.batchUpdate(sql, new BatchPreparedStatementSetter() {

			
			public int getBatchSize() {
				return to.size();
			}

			
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				ps.setLong(1, from.getId());
				ps.setLong(2, to.get(i).getId());
			}
		});
	}

	/**
	 * Stores the product instance.
	 * 
	 * @param p The instance to store.
	 * @return The database id of the stored instance.
	 */
	public long persistProduct(final Product p) {
		// Parameter check.
		if (p == null) {
			throw new IllegalArgumentException("Argument should be null");
		}

		// prepare insert with the ability to retreive the generated primary key.
		// The solution is the same as in the persistApiclass() method above.
		final String sql = sqlInsertRow(Table.PRODUCTS, Column.PRODUCTS_NAME, Column.PRODUCTS_VERSIONS,
				Column.PRODUCTS_CONTAINING_FOLDERS);
		KeyHolder keyHolder = new GeneratedKeyHolder();
		PreparedStatementCreator creator = new PreparedStatementCreator() {
			
			public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
				// The id column is the product_id
				PreparedStatement ps = conn.prepareStatement(sql, new String[] { Column.PRODUCTS_PRODUCT_ID.cname() });

				// Set the parameters according the parameter's attributes
				ps.setString(1, p.getName());
				ps.setString(2, BeanUtils.listToString(p.getVersions(), SEPARATOR));
				ps.setString(3, p.getContainingFolders() == null ? "" : p.getContainingFolders());
				return ps;
			}
		};

		// Execute the insert and return the generated id.
		jdbc.update(creator, keyHolder);
		return keyHolder.getKey().longValue();
	}

	/**
	 * Updates the persisted object in the database.
	 * 
	 * @param instance
	 */
	public <T extends CodeElement> void update(T instance) {
		if (instance == null || instance.getId() == 0l) {
			throw new IllegalArgumentException("Argument cannot be null, and shoud have a valid database id.");
		}

		if (instance instanceof ApiClass) {
			updateApiClass((ApiClass) instance);
		} else if (instance instanceof Product) {
			updateProduct((Product) instance);
		} else if (instance instanceof Method) {
			updateMethod((Method) instance);
		} else if (instance instanceof Field) {
			updateField((Field) instance);
		}
	}

	/**
	 * Creates apiclass SQL update command and runs it.
	 * 
	 * @param instance The instance to update.
	 */
	private void updateApiClass(ApiClass instance) {
		String sql = sqlUpdate(Table.CLASSES, Column.CLASSES_CLASS_ID, Column.CLASSES_SIMPLE_NAME,
				Column.CLASSES_PACKAGE_NAME, Column.CLASSES_SUPERCLASS_NAME, Column.CLASSES_INTERFACE_NAMES,
				Column.CLASSES_VERSIONS);
		jdbc.update(sql, new Object[] { instance.getSimpleName(), instance.getPackageName(), instance.getExtends(),
				instance.getImplements(), BeanUtils.listToString(instance.getVersions(), SEPARATOR), instance.getId() });
	}

	/**
	 * Creates field SQL update command and runs it.
	 * 
	 * @param instance The instance to update.
	 */
	private void updateField(Field instance) {
		String sql = sqlUpdate(Table.FIELDS, Column.FIELDS_FIELD_ID, Column.FIELDS_SIGNATURE, Column.FIELDS_VERSIONS,
				Column.FIELDS_CLASS_ID);
		jdbc.update(sql,
				new Object[] { instance.getSignature(), BeanUtils.listToString(instance.getVersions(), SEPARATOR),
						instance.getApiClass().getId(), instance.getId() });
	}

	/**
	 * Creates method SQL update command and runs it.
	 * 
	 * @param instance The instance to update.
	 */
	private void updateMethod(Method m) {
		String sql = sqlUpdate(Table.METHODS, Column.METHODS_METHOD_ID, Column.METHODS_SIGNATURE,
				Column.METHODS_VERSIONS, Column.METHODS_CLASS_ID);
		jdbc.update(sql, new Object[] { m.getSignature(), BeanUtils.listToString(m.getVersions(), SEPARATOR),
				m.getApiClass().getId(), m.getId() });
	}

	/**
	 * Creates product SQL update command and runs it.
	 * 
	 * @param instance The instance to update.
	 */
	private void updateProduct(Product p) {
		String sql = sqlUpdate(Table.PRODUCTS, Column.PRODUCTS_PRODUCT_ID, Column.PRODUCTS_NAME,
				Column.PRODUCTS_VERSIONS, Column.PRODUCTS_CONTAINING_FOLDERS);
		
		
		
		jdbc.update(
				sql,
				new Object[] { p.getName(), BeanUtils.listToString(p.getVersions(), SEPARATOR),
						p.getContainingFolders(), p.getId() });
	}
	
	@SuppressWarnings("unchecked")
	public List<Product> findIncomingProducts(Product p) {
		// Select all the products through the method call dependencies.
		String sql = "select distinct p1.product_id, p1.name, p1.versions, p1.containing_folder, p1.product_path ";
		sql += "from methods m1, classes c1, products p1, method_call_dependencies d, methods m2, classes c2, products p2 ";
		sql += "where c1.product_id = p1.product_id and m1.class_id = c1.class_id and d.from_method_id = m1.method_id and " +
				"d.to_method_id = m2.method_id and m2.class_id=c2.class_id and c2.product_id = p2.product_id and p2.name= ? ";
		// Union with override dependencies.
		sql += "union ";
		sql += "select distinct p1.product_id, p1.name, p1.versions, p1.containing_folder, p1.product_path ";
		sql += "from methods m1, classes c1, products p1, override_dependencies d, methods m2, classes c2, products p2 ";
		sql += "where c1.product_id = p1.product_id and m1.class_id = c1.class_id and d.from_method_id = m1.method_id and " +
				"d.to_method_id = m2.method_id and m2.class_id=c2.class_id and c2.product_id = p2.product_id and p2.name= ? ";
		// Union with field reference dependencies.
		sql += "union ";
		sql += "select distinct p1.product_id, p1.name, p1.versions, p1.containing_folder, p1.product_path ";
		sql += "from methods m1, classes c1, products p1, field_reference_dependencies d, fields f2, classes c2, products p2 ";
		sql += "where c1.product_id = p1.product_id and  m1.class_id=c1.class_id and d.from_method_id=m1.method_id and " +
				"d.to_field_id = f2.field_id and f2.class_id=c2.class_id and c2.product_id=p2.product_id and p2.name = ? ";
		sql += "union ";
		// Union with class usage dependencies.
		sql += "select distinct p1.product_id, p1.name, p1.versions, p1.containing_folder, p1.product_path ";
		sql += "from  classes c1, products p1, class_definition_dependencies d, classes c2, products p2 ";
		sql += "where c1.product_id = p1.product_id and d.from_class_id = c1.class_id and d.to_class_id = c2.class_id " +
				"and c2.class_id = p2.product_id and p2.name= ? ";
		sql += "union ";
		// Union with inheritance dependencies.
		sql += "select distinct p1.product_id, p1.name, p1.versions, p1.containing_folder, p1.product_path ";
		sql += "from  classes c1, products p1, inheritance_dependencies d, classes c2, products p2 ";
		sql += "where c1.product_id = p1.product_id and d.from_class_id = c1.class_id and d.to_class_id = c2.class_id " +
				"and c2.class_id = p2.product_id and p2.name= ? ";
		
		// Execute result and return the products.
		return (List<Product>) jdbc.query(sql, new Object[] { p.getName(), p.getName(), p.getName(), p.getName(), p.getName() }, productRowMapper);
	}
	
	public long getDepsNumber( ) {
		return jdbc.getTemplate().queryForInt("SELECT SUM(Cnt) from(  SELECT Count(*) As Cnt FROM class_definition_dependencies  UNION all  SELECT Count(*) as Cnt FROM field_reference_dependencies  UNION all  SELECT Count(*) as Cnt FROM inheritance_dependencies  UNION all  SELECT Count(*) as Cnt FROM method_call_dependencies  UNION all  SELECT Count(*) as Cnt FROM override_dependencies)");
	}

	public void deleteAll() {
		jdbc.update("delete from products");
	}
}
