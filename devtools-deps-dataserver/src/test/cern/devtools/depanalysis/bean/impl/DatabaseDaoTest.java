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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.EnumSet;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import cern.devtools.depanalysis.bean.DatabaseDao;
import cern.devtools.depanalysis.bean.DatabaseException;
import cern.devtools.depanalysis.domain.ApiClass;
import cern.devtools.depanalysis.domain.Field;
import cern.devtools.depanalysis.domain.Method;
import cern.devtools.depanalysis.domain.Modifiers;
import cern.devtools.depanalysis.domain.Product;
import cern.devtools.depanalysis.domain.creation.DomainObjectCreator;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/config/ctx-test-dbdao-oracledev.xml")
@Transactional
public class DatabaseDaoTest {

    @Autowired
    private DatabaseDao db;

    @Autowired
    DomainObjectCreator creator;

    private static final String PRODUCT_PATH = "/tmp/testproduct.jar";

    @Before
    public void init() throws DatabaseException {
        Product p = getProduct();
        db.saveProduct(p);
    }

    public Product getProduct() {
        Product p = creator.createProduct("name", "1.0.0", "test/testproduct", PRODUCT_PATH);
        ApiClass ac = creator.createApiClass("MyClass", EnumSet.noneOf(Modifiers.class));
        Method m = creator.createMethod("cern.example.MyClass#foo()", EnumSet.of(Modifiers.STATIC));
        Field f = creator.createField("cern.example.MyClass.FIELD", EnumSet.noneOf(Modifiers.class));
        ac.getMethods().add(m);
        m.setApiClass(ac);
        ac.getFields().add(f);
        f.setApiClass(ac);
        p.getClasses().add(ac);
        ac.setProduct(p);
        return p;
    }

    @Test
    public void findProduct() throws DatabaseException {
        // check existing data without internals
        Product product = db.findProduct(getProduct(), false);
        assertNotNull(product);

        // check existing data with internals
        product = db.findProduct(creator.createProduct("name", "1.0.0", "test/testproduct", PRODUCT_PATH), true);
        assertNotNull(product);
        assertEquals(1, product.getClasses().size());
        assertEquals(1, product.getClasses().iterator().next().getMethods().size());
        assertEquals(1, product.getClasses().iterator().next().getFields().size());

        // find non-existing product
        product = db.findProduct(creator.createProduct(""), true);
        assertNull(product);
    }
}
