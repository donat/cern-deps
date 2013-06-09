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
package cern.devtools.depanalysis.domain;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

import org.junit.Ignore;
import org.junit.Test;

import cern.devtools.deps.domain.Product;
import cern.devtools.deps.domain.creation.DomainFactory;
import cern.devtools.deps.domain.creation.impl.EmfObjectCreator;
import cern.devtools.deps.domain.impl.ProductImpl;
import cern.devtools.deps.repomodel.RProject;

public class DomainFactoryTest {

    @Test
    public void testChangeFactory() {
        DomainFactory.setDomainObjectCreator(new EmfObjectCreator());
        Product p = DomainFactory.creator().createProduct("");
        assertTrue(p instanceof RProject);
    }

    @Test
    @Ignore
    public void testDefaultFactoryRegistration() {
        Product p = DomainFactory.creator().createProduct("");
        assertEquals(ProductImpl.class, p.getClass());
    }
}
