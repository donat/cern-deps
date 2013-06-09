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

import java.util.LinkedList;
import java.util.List;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.ui.model.WorkbenchContentProvider;
import org.eclipse.ui.model.WorkbenchLabelProvider;

import cern.devtools.deps.domain.ApiClass;
import cern.devtools.deps.domain.CodeElement;
import cern.devtools.deps.domain.DependencyType;
import cern.devtools.deps.domain.Field;
import cern.devtools.deps.domain.Method;
import cern.devtools.deps.domain.Product;
import cern.devtools.deps.eclipse.ui.views.DependencyView;

/**
 * Single item used to display items in the {@link TreeViewer}.
 * <p>
 * To integrate this object into the JFace viewer, it implements the {@link IAdaptable} interface, but using the Eclipse
 * workbench capabilities, adapting the content of the item is executed via the {@link TreeItemAdapterFactory}. The is
 * factory is responsible for providing label and content provider for the {@link TreeItem} instances.
 * <p>
 * The {@link TreeItemAdapterFactory} is registered in the {@link DependencyView#init(org.eclipse.ui.IViewSite)} and
 * {@link DependencyView#dispose()} method. Also to provide TreeItem instances as an input for a view, it is required to
 * specify the proper label and content provider for it, which is the {@link WorkbenchLabelProvider} and the
 * {@link WorkbenchContentProvider}. It is done in the
 * {@link DependencyView#createPartControl(org.eclipse.swt.widgets.Composite)};
 * <p>
 * The implementation is based on the book: Eclipse Rich Client Platform, second edition, Chapter 10, page 150.
 * <p>
 * The concrete implementations provides a necessary data for displaying a represented items.
 * 
 * @author Donat Csikos <dcsikos@cern.ch>
 */
public abstract class TreeItem implements IAdaptable {

    /**
     * Creates a new node.
     * 
     * @param type The Type of the created node.
     * @param parent The parent node. only the root node could have a <code>null</code> parent.
     * @param data The supplementary object which is is used to represent the node.
     * @return The created child node.
     */
    public static TreeItem createNewNode(TreeItemType type, TreeItem parent, final Object... data) {
        switch (type) {
        case ROOT:
            return data.length == 0 ? new RootTreeItem(parent, null) : new RootTreeItem(parent, (CodeElement) data[0]);
        case NO_DEPENDENCIES:
            return new NoDependenciesTreeItem(parent, (CodeElement) data[0]);
        case DEPENDENCY_TYPE:
            return new DependencyTypeTreeItem(parent, (DependencyType) data[0]);
        case PRODUCT:
            return new ProductTreeItem(parent, (Product) data[0]);
        case VERSION:
            return new VersionTreeItem(parent, (String) data[0]);
        case METHOD:
            return new MethodTreeItem(parent, (Method) data[0], (String) data[1]);
        case CLASS:
            return new ApiClassTreeItem(parent, (ApiClass) data[0], (String) data[1]);
        case FIELD:
            return new FieldTreeItem(parent, (Field) data[0]);
        case PACKAGE:
            return new PackageTreeItem(parent, (String) data[0]);
        default:
            throw new RuntimeException("TreeItemType is not supported for creation: " + type);
        }
    }

    /**
     * List of the children's node.
     */
    private List<TreeItem> children;

    /**
     * Parent tree node, <code>null</code>, if root node.
     */
    private TreeItem parent = null;

    /**
     * Node type. Used to determine how to display the current node.
     */
    private final TreeItemType type;

    /**
     * Constructor
     * 
     * @param type Node type.
     * @param parent The parent {@link TreeItem}. Can be <code>null</code> only if it is a root node.
     */
    protected TreeItem(TreeItemType type, TreeItem parent) {
        // Parameter checking.
        if (type == null) {
            throw new RuntimeException("The type must be specified");
        }
        if (type != TreeItemType.ROOT && parent == null) {
            throw new RuntimeException("Parent cannot be null (only for a root node).");
        }

        // setting relationships
        // Setting the parent and the children references.
        this.setParent(parent);
        if (parent != null) {
            parent.getChildren().add(this);
        }
        this.type = type;
    }

    @Override
    @SuppressWarnings("rawtypes")
    public Object getAdapter(Class adapter) {
        return null;
    }

    /**
     * Returns all the nodes below this one.
     * 
     * @return All the nodes below this one.
     */
    public List<TreeItem> getChildren() {
        return children == null ? (children = new LinkedList<TreeItem>()) : children;
    }

    /**
     * Returns parent node.
     * 
     * @return Parent node.
     */
    public TreeItem getParent() {
        return parent;
    }

    /**
     * Returns the type of the node.
     * 
     * @return The type of the node.
     */
    public TreeItemType getType() {
        return type;
    }

    /**
     * Return <code>true</code>, if does not have a child node.
     * 
     * @return
     */
    public boolean isLeaf() {
        return getChildren().isEmpty();
    }

    /**
     * Counts the number of items which does not have a child.
     */
    public int leafNumber() {
        int sum = 0;
        for (TreeItem child : getChildren()) {
            if (child.isLeaf()) {
                sum++;
            } else {
                sum += child.leafNumber();
            }
        }
        return sum;
    }

    /**
     * @param parent parent
     */
    public void setParent(TreeItem parent) {
        this.parent = parent;
    }
}
