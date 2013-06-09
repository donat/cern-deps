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
package cern.devtools.deps.eclipse.ui.tree;

import java.util.Collection;
import java.util.Collections;

import cern.devtools.deps.domain.Product;
import cern.devtools.deps.eclipse.ui.Activator;
import cern.devtools.deps.eclipse.ui.LoggingUtil;

/**
 * TreeItem represents an {@link Product}.
 * 
 * @author Donat Csikos <dcsikos@cern.ch>
 */
public class ProductTreeItem extends GenericTreeItem<Product> {

    private Collection<String> committers = null;

    public ProductTreeItem(TreeItem parent, Product p) {
        super(TreeItemType.PRODUCT, parent, p);
    }

    /**
     * Lazily loads and returns the committers of the represented project. E.g. {"lmestre", "vbaggiol", "eroux", "arey", "rgorbono", "vlezhebo"}.
     * @return
     */
    public Collection<String> getCommitters() {
        if (committers == null) {
            try {
                committers = Activator.getDefault().getDependencyRMIService().getCommittersName(super.value);
            } catch (Exception e) {
                LoggingUtil.logWarn(e.getMessage());
                committers = null;
                return Collections.emptyList();
            }
        }
        return committers;
    }
}
