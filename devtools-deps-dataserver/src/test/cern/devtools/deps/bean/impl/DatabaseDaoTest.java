package cern.devtools.deps.bean.impl;

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

import cern.devtools.deps.bean.DatabaseDao;
import cern.devtools.deps.bean.DatabaseException;
import cern.devtools.deps.domain.ApiClass;
import cern.devtools.deps.domain.Field;
import cern.devtools.deps.domain.Method;
import cern.devtools.deps.domain.Modifiers;
import cern.devtools.deps.domain.Product;
import cern.devtools.deps.domain.creation.DomainObjectCreator;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/ctx-test.xml")
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
