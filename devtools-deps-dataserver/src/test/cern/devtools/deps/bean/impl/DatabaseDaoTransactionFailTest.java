package cern.devtools.deps.bean.impl;

import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.AfterTransaction;
import org.springframework.transaction.annotation.Transactional;

import cern.devtools.deps.bean.DatabaseDao;
import cern.devtools.deps.bean.DatabaseException;
import cern.devtools.deps.domain.creation.DomainObjectCreator;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/res/ctx/ctx-test-discovery-oracledev.xml")
public class DatabaseDaoTransactionFailTest {

    @Autowired
    DomainObjectCreator creator;

    @Autowired
    private DatabaseDao db;

    @AfterTransaction
    public void checkTransactionResult() throws DatabaseException {
        assertNull(db.findProduct(creator.createProduct("project-name"), false));
    }

    @Test
    @Rollback(true)
    @Transactional
    public void test() throws DatabaseException {
        db.saveProduct(creator.createProduct("project-name"));
    }
}
