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
package cern.devtools.depanalysis.bean.impl;

import static junit.framework.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.AfterTransaction;
import org.springframework.transaction.annotation.Transactional;

import cern.devtools.depanalysis.bean.DatabaseDao;
import cern.devtools.depanalysis.bean.DatabaseException;
import cern.devtools.depanalysis.domain.creation.DomainObjectCreator;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/config/ctx-test-dbdao-oracledev.xml")
public class DatabaseDaoTransactionSuccessTest {

	@Autowired
	private DatabaseDao db;

	@Autowired
	DomainObjectCreator creator;

	@Test
	@Rollback(false)
	@Transactional
	public void test() throws DatabaseException {
		db.saveProduct(creator.createProduct("project-name"));
	}

	@AfterTransaction
	public void checkTransactionResult() throws DatabaseException {
		assertNotNull(db.findProduct(creator.createProduct("project-name"), false));
		db.deleteProduct(creator.createProduct("project-name"));
	}
}
