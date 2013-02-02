/*******************************************************************************
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
 ******************************************************************************/
package cern.devtools.depanalysis.bean.impl;

import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.KeyHolder;

public class JdbcWrapper {

	private final JdbcTemplate template;

	public JdbcTemplate getTemplate() {
		return template;
	}

	private static final Logger LOG = Logger.getLogger(JdbcWrapper.class);
	
	public JdbcWrapper(JdbcTemplate template) {
		this.template = template;
	}

	public DataSource getDataSource() {
		return template.getDataSource();
	}

	public void batchUpdate(String sql, BatchPreparedStatementSetter setter) {
		LOG.trace(sql);
		template.batchUpdate(sql, setter);
	}

	public void update(String s, Object[] o) {
		LOG.trace(s);
		for (Object ob : o) {
			LOG.trace("\t" + ob);
		}
		template.update(s, o);
	}

	public void update(String s) {
		LOG.trace(s);
		template.update(s);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List query(String s, Object[] o, RowMapper r) {
		LOG.trace(s );
		for (Object ob : o) {
			LOG.trace("\t" + ob);
		}
		return template.query(s, o, r);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List query(String s, RowMapper r) {
		LOG.trace(s);
		return template.query(s, r);
	}

	@SuppressWarnings("unchecked")
	public Object queryForObject(String s, Object[] o, @SuppressWarnings("rawtypes") RowMapper r) {
		LOG.trace(s);
		for (Object ob : o) {
			LOG.trace("\t" + ob);
		}
		return template.queryForObject(s, o, r);
	}

	public void update(PreparedStatementCreator s, KeyHolder o) {
		LOG.trace(s);
		template.update(s, o);
	}
}
