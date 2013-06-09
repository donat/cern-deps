package cern.devtools.deps.bean.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.Arrays;
import java.util.Collection;
import java.util.EnumSet;
import java.util.Iterator;

import cern.devtools.deps.domain.ApiClass;
import cern.devtools.deps.domain.Dependency;
import cern.devtools.deps.domain.DependencyType;
import cern.devtools.deps.domain.Modifiers;
import cern.devtools.deps.domain.TransitiveDependency;
import cern.devtools.deps.domain.creation.DomainFactory;

/**
 * One class uses (initiates an instance) an another.
 * 
 * @author Donat Csikos <dcsikos@cern.ch>
 */
public class DependencyFindClassUsage extends AbstractDependencyDiscoveryTest {

    @Override
    public Source from() {
        String source = ""
                + "package cern.from;                                                                               \n"
                + "import cern.to.To;                                                                               \n"
                + "                                                                                                 \n"
                + "public class From {                                                                              \n"
                + "    public void from() { To to = new To(); to.to(); }                                            \n"
                + "}";
        return new Source(Arrays.asList("From"), Arrays.asList(source), "cern.from");
    }

    @Override
    public void test() throws Exception {
        Collection<Dependency> result = db.findClassDependencies(DomainFactory.creator().createApiClass(
                "cern.to.To", EnumSet.noneOf(Modifiers.class)));
        Iterator<Dependency> it = result.iterator();
        Dependency rh = it.next();
        assertEquals(DependencyType.CLASS_USAGE, rh.getType());
        assertEquals("cern.from.From", ((ApiClass) rh.getFrom()).getFqName());
        assertFalse(rh instanceof TransitiveDependency);
        assertFalse(it.hasNext());
    }

    @Override
    public Source to() {
        String source = ""
                + "package cern.to;                                                                                 \n"
                + "                                                                                                 \n"
                + "public class To {                                                                                \n"
                + "    public void to() { System.out.println(\"to\"); }                                             \n"
                + "}";
        return new Source(Arrays.asList("To"), Arrays.asList(source), "cern.to");
    }

    @Override
    public Source trans() {
        return null;
    }
}
