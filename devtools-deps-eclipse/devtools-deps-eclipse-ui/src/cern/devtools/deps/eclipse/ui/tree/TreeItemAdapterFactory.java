/*
 * File TreeItem.java.
 * Created by Donat Csikos<dcsikos@cern.ch> at 20 Mar 2012.
 *
 * Copyright CERN 2012, All Rights Reserved.
 */
package cern.devtools.deps.eclipse.ui.tree;

import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.model.IWorkbenchAdapter;

import cern.devtools.deps.domain.ApiClass;
import cern.devtools.deps.domain.CodeElement;
import cern.devtools.deps.domain.Field;
import cern.devtools.deps.domain.Method;
import cern.devtools.deps.domain.Product;
import cern.devtools.deps.eclipse.ui.Images;

/**
 * <p>
 * Eclipse integration for providing custom LabelProvider and ContentProvider for JFace viewers.
 * </p>
 * <p>
 * More information about the working mechanism is described in the {@link TreeItem}
 * </p>
 * 
 * @author Donat Csikos
 */
public class TreeItemAdapterFactory implements IAdapterFactory {
	private IWorkbenchAdapter adapter = new IWorkbenchAdapter() {

		@Override
		public Object getParent(Object o) {
			if (o instanceof TreeItem) {
				return ((TreeItem) o).getParent();
			} else {
				return null;
			}
		}

		@SuppressWarnings("unchecked")
		@Override
		public String getLabel(Object o) {
			String label = "NOLABEL";
			if (o instanceof TreeItem) {
				TreeItem ti = ((TreeItem) o);
				switch (ti.getType()) {
				case ROOT:
					label = "Dependencies for " + (((RootTreeItem) ti).getValue()).getDisplayName();
					//label += " (" + ti.leafNumber() + ")";
					break;
				case DEPENDENCY_TYPE:
					label = displayTextFor((DependencyTypeTreeItem) ti);
					break;
				case PRODUCT:
					Product pti = ((ProductTreeItem) ti).getValue();
					label = pti.getDisplayName();
					//label += " (" + ti.leafNumber() + ")";
					break;
				case CLASS:
					label = ((ApiClassTreeItem) ti).getValue().getSimpleName();
					break;
				case METHOD:
					Method m = ((MethodTreeItem) ti).getValue();
					label = m.getDisplayName();
					break;
				case FIELD:
					label = ((FieldTreeItem) ti).getValue().getSignature();
					break;
				case VERSION:
					label = ((GenericTreeItem<String>) ti).getValue();
					break;
				case PACKAGE:
					label = ((GenericTreeItem<String>) ti).getValue();
					if ("".equals(label)) {
						label = "<default>";
					}
					break;
				case NO_DEPENDENCIES:
					label = "No incoming dependencies for " + ((NoDependenciesTreeItem) ti).getValue().getDisplayName()
							+ ".";
					break;
				default:
					break;
				}
				
				if (!ti.isLeaf()) {
					label += " (" + ti.leafNumber() + ")";
				}
			}
			
			
			return label;
		}

		/**
		 * Labels for the different type of dependencies.
		 * 
		 * @param type The type to display.
		 * @param ti The treeItem to display.
		 * @return The label.
		 */
		private String displayTextFor(DependencyTypeTreeItem ti) {
//			int num = ti.leafNumber();
			String text;
			switch (ti.getValue()) {
			case CLASS_USAGE:
				text =  "Used by";
				break;
			case FIELD_REFERENCE:
				text = "Referenced in";
				break;
			case CLASS_INHERITANCE:
				text = "Inherited by";
				break;
			case METHOD_CALL:
				text = "Method call";
				break;
			case METHOD_OVERRIDE:
				text = "Overridden by";
				break;
			case PRODUCT_DEPENDENCY:
				text ="Dependent jars";
				break;
			default:
				text =  "";
			}
			
//			text += " (" + num + ")";
			return text;
		}

		@Override
		public ImageDescriptor getImageDescriptor(Object o) {
			if (o instanceof TreeItem) {
				TreeItem ti = (TreeItem) o;
				switch (ti.getType()) {
				case NO_DEPENDENCIES:
				case ROOT:
					@SuppressWarnings("unchecked")
					CodeElement ce = (((GenericTreeItem<CodeElement>) ti).getValue());
					if (ce instanceof Field) {
						return Images.ICON_FIELD.descriptor();
					} else if (ce instanceof ApiClass) {
						return Images.ICON_CLASS.descriptor();
					} else if (ce instanceof Method) {
						return Images.ICON_METHOD.descriptor();
					} else if (ce instanceof Product) {
						return Images.ICON_PRODUCT.descriptor();
					}
					break;
				case DEPENDENCY_TYPE:
					return Images.ICON_DEPENDENCY.descriptor();
				case CLASS:
					return Images.ICON_CLASS.descriptor();
				case METHOD:
					return Images.ICON_METHOD.descriptor();
				case PRODUCT:
					return Images.ICON_PRODUCT.descriptor();
				case VERSION:
					return Images.ICON_VERSION.descriptor();
				case PACKAGE:
					return Images.ICON_PACKAGE.descriptor();
				}
			}
			return null;
		}

		@Override
		public Object[] getChildren(Object o) {
			return ((TreeItem) o).getChildren().toArray();
		}
	};

	@Override
	@SuppressWarnings("rawtypes")
	public Object getAdapter(Object adaptableObject, Class adapterType) {
		if (adapterType == IWorkbenchAdapter.class && adaptableObject instanceof TreeItem) {
			return adapter;
		} else {
			return null;
		}
	}

	@Override
	@SuppressWarnings("rawtypes")
	public Class[] getAdapterList() {
		return new Class[] { IWorkbenchAdapter.class };
	}
}