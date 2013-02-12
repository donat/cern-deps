/*
 * File DependencyDetailsComposite.java.
 * Created by Donat Csikos<dcsikos@cern.ch> at 15 Mar 2012.
 *
 * Copyright CERN 2012, All Rights Reserved.
 */
package cern.devtools.deps.eclipse.ui.views;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.program.Program;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Link;
import org.eclipse.swt.widgets.Text;

import cern.devtools.deps.domain.ApiClass;
import cern.devtools.deps.domain.Method;
import cern.devtools.deps.eclipse.ui.Activator;
import cern.devtools.deps.eclipse.ui.handlers.ShowSourceHandler;
import cern.devtools.deps.eclipse.ui.tree.ApiClassTreeItem;
import cern.devtools.deps.eclipse.ui.tree.MethodTreeItem;
import cern.devtools.deps.eclipse.ui.tree.ProductTreeItem;
import cern.devtools.deps.eclipse.ui.tree.TreeItem;
import cern.devtools.deps.eclipse.ui.tree.TreeItemType;
import cern.devtools.deps.eclipse.ui.tree.VersionTreeItem;

/**
 * Composite SWT widget for displaying the preferences for a selected element in the {@link DependencyView} view.
 * 
 * @author Donat Csikos <dcsikos@cern.ch>
 */
public final class DependencyDetailsComposite extends Composite {

	/**
	 * <p>
	 * Listener implementation for responding selection change in the treeviewer.
	 * </p>
	 * <p>
	 * It enables the visibility if there is at least one selection. The details are always about the first selected
	 * item.
	 * </p>
	 * 
	 * @author Donat Csikos <dcsikos@cern.ch>
	 */
	public static class DetailsChangeListener implements ISelectionChangedListener {

		/**
		 * The listener holds reference for the operated widget.
		 */
		private final DependencyDetailsComposite details;

		public DetailsChangeListener(DependencyDetailsComposite details) {
			this.details = details;
		}

		@Override
		public void selectionChanged(SelectionChangedEvent event) {
			// By default the details is not visible.
			boolean visible = false;
			ISelection is = event.getSelection();
			if (is instanceof TreeSelection) {
				TreeSelection ts = (TreeSelection) is;
				Object o = ts.getFirstElement();
				if (o instanceof TreeItem) {
					// Make it visible if the selection is on a treeitem.
					visible = details.displayTreeItem((TreeItem) o);
				}
			}

			details.setVisible(visible);
		}

	}

	/**
	 * Listener object to add to external widgets through the getChangeListener method.
	 */
	private DetailsChangeListener listener = null;

	/**
	 * Stacklayout to create multiple layers in the widget. Based on the type of the argument of the
	 * {@link DetailsChangeListener#selectionChanged(SelectionChangedEvent)} the internal logic will decide, which panel
	 * should be visible: the product details, the method details, the class details or none of them.
	 */
	private StackLayout layout;

	/**
	 * Selected class' name widget.
	 */
	private Text txtClassName;

	/**
	 * Selected method's name widget.
	 */
	private Text txtMethodName;

	/**
	 * Widget for displaying method details. Should set the referencen to the {@link StackLayout#topControl} field.
	 */
	private Composite cmpMethodDetails;

	private Composite cmpClassDetails;

	/**
	 * Widget to display if a method is static.
	 */
	private Button chkMethodIsStatic;

	/**
	 * Widget holds the versions of a method.
	 */
	private Composite cmpMethodVersions;

	/**
	 * Widget holds the versions of a class.
	 */
	private Composite cmpClassVersions;

	/**
	 * Widget displaying the details of a product node.
	 */
	private Composite cmpProductDetails;

	/**
	 * Product name input field.
	 */
	private Text txtProductName;
	
	/**
	 * Product version input field
	 */
	private Text txtProductVersions;

	/**
	 * Container of the product version
	 */
	private Composite cmpVersionDetails;
	
	/**
	 * Version number input field for the version node.
	 */
	private Text txtVersion;
	
	/**
	 * Container for the releaser's name.
	 */
	private Composite cmbReleasedBy;

	/**
	 * Container for the possible releaser of one project.
	 */
	private Composite cmbCommitters;

	/**
	 * Creates the composite. Designed with WindowBuilder 1.2.
	 * 
	 * @param parent Parent Widget.
	 * @param style Stype forwarded to the {@link Composite#Composite(Composite, int)};
	 */
	public DependencyDetailsComposite(Composite parent, int style) {
		super(parent, style);
		layout = new StackLayout();
		setLayout(layout);

		cmpProductDetails = new Composite(this, SWT.NONE);

		cmpClassDetails = new Composite(this, SWT.NONE);
		cmpClassDetails.setLayout(new GridLayout(1, false));

		Group grpClassDetails = new Group(cmpClassDetails, SWT.NONE);
		grpClassDetails.setLayout(new GridLayout(2, false));
		grpClassDetails.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		grpClassDetails.setText("Class details");

		Label lblQualifiedName = new Label(grpClassDetails, SWT.NONE);
		lblQualifiedName.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblQualifiedName.setText("Qualified name");

		txtClassName = new Text(grpClassDetails, SWT.BORDER);
		txtClassName.setEditable(false);
		txtClassName.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label lblVersions_1 = new Label(grpClassDetails, SWT.NONE);
		lblVersions_1.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblVersions_1.setText("Versions");

		cmpClassVersions = new Composite(grpClassDetails, SWT.BORDER);
		RowLayout rl_cmpClassVersions = new RowLayout(SWT.HORIZONTAL);
		cmpClassVersions.setLayout(rl_cmpClassVersions);
		cmpClassVersions.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));

		Link link_1 = new Link(cmpClassVersions, 0);
		link_1.setText("<a>1.0.0</a>");
		cmpProductDetails.setLayout(new GridLayout(1, false));

		Group grpProduct = new Group(cmpProductDetails, SWT.NONE);
		GridData gd_grpProduct = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
		gd_grpProduct.heightHint = 175;
		grpProduct.setLayoutData(gd_grpProduct);
		grpProduct.setText("Product");
		grpProduct.setLayout(new GridLayout(2, false));

		Label lblName = new Label(grpProduct, SWT.NONE);
		lblName.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblName.setBounds(0, 0, 55, 15);
		lblName.setText("Name");

		txtProductName = new Text(grpProduct, SWT.BORDER);
		txtProductName.setEditable(false);
		txtProductName.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label lblVersions = new Label(grpProduct, SWT.NONE);
		lblVersions.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblVersions.setText("Versions");

		txtProductVersions = new Text(grpProduct, SWT.BORDER);
		txtProductVersions.setEditable(false);
		txtProductVersions.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label lblCommitters = new Label(grpProduct, SWT.NONE);
		lblCommitters.setText("Committers");

		cmbCommitters = new Composite(grpProduct, SWT.BORDER);
		cmbCommitters.setLayout(new RowLayout(SWT.HORIZONTAL));
		GridData gd_cmbCommitters = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_cmbCommitters.heightHint = 18;
		cmbCommitters.setLayoutData(gd_cmbCommitters);

		Link link_2 = new Link(cmbCommitters, SWT.NONE);
		link_2.setText("<a>New Link</a>");

		Link link_4 = new Link(cmbCommitters, SWT.NONE);
		link_4.setText("<a>New Link</a>");
		
		Link link_5 = new Link(cmbCommitters, SWT.NONE);
		link_5.setText("<a>New Link</a>");
		
		Link link_6 = new Link(cmbCommitters, SWT.NONE);
		link_6.setText("<a>New Link</a>");
		
		Link link_7 = new Link(cmbCommitters, SWT.NONE);
		link_7.setText("<a>New Link</a>");
		
		Link link_8 = new Link(cmbCommitters, SWT.NONE);
		link_8.setText("<a>New Link</a>");

		cmpMethodDetails = new Composite(this, SWT.NONE);
		cmpMethodDetails.setLayout(new GridLayout(1, false));

		Group grpMethodDetails = new Group(cmpMethodDetails, SWT.NONE);
		grpMethodDetails.setLayout(new GridLayout(2, false));
		grpMethodDetails.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		grpMethodDetails.setText("Method details");

		Label lblQualifiedName_1 = new Label(grpMethodDetails, SWT.NONE);
		lblQualifiedName_1.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblQualifiedName_1.setText("Qualified name");

		txtMethodName = new Text(grpMethodDetails, SWT.BORDER);
		txtMethodName.setEditable(false);
		txtMethodName.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label lblStatic = new Label(grpMethodDetails, SWT.NONE);
		lblStatic.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblStatic.setText("Static");

		chkMethodIsStatic = new Button(grpMethodDetails, SWT.CHECK);
		chkMethodIsStatic.setEnabled(false);
		chkMethodIsStatic.setSelection(true);

		Label lblVersions_2 = new Label(grpMethodDetails, SWT.NONE);
		lblVersions_2.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblVersions_2.setText("Versions");

		cmpMethodVersions = new Composite(grpMethodDetails, SWT.BORDER);
		cmpMethodVersions.setLayout(new RowLayout(SWT.HORIZONTAL));
		GridData gd_cmpMethodVersions = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_cmpMethodVersions.heightHint = 18;
		cmpMethodVersions.setLayoutData(gd_cmpMethodVersions);

		Link link_3 = new Link(cmpMethodVersions, 0);
		link_3.setText("<a>1.0.0</a>");

		cmpVersionDetails = new Composite(this, SWT.NONE);
		cmpVersionDetails.setLayout(new GridLayout(1, false));

		Group grpVersionDetails = new Group(cmpVersionDetails, SWT.NONE);
		grpVersionDetails.setText("Version");
		grpVersionDetails.setLayout(new GridLayout(2, false));
		grpVersionDetails.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

		Label lblVersionNumber = new Label(grpVersionDetails, SWT.NONE);
		lblVersionNumber.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblVersionNumber.setText("Version number");

		txtVersion = new Text(grpVersionDetails, SWT.BORDER);
		txtVersion.setEditable(false);
		txtVersion.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label lblReleasedBy = new Label(grpVersionDetails, SWT.NONE);
		lblReleasedBy.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblReleasedBy.setText("Released by");

		cmbReleasedBy = new Composite(grpVersionDetails, SWT.BORDER);
		cmbReleasedBy.setLayout(new RowLayout(SWT.HORIZONTAL));
		GridData gd_cmbReleasedBy = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gd_cmbReleasedBy.heightHint = 18;
		cmbReleasedBy.setLayoutData(gd_cmbReleasedBy);

		Link link = new Link(cmbReleasedBy, SWT.NONE);
		link.setText("<a>dcsikos</a>");
		initProps();
	}

	/**
	 * Sets default runtime propeties for this component.
	 */
	private void initProps() {
		setVisible(false);
	}

	/**
	 * Returns a listener object associated with the {@link DependencyDetailsComposite} instance. Calling this function
	 * on a same object will return the same listener.
	 * 
	 * @return
	 */
	public ISelectionChangedListener getChangeListener() {
		return listener == null ? listener = new DetailsChangeListener(this) : listener;
	}

	/**
	 * <p>
	 * Decides, which details composite should be visible.
	 * </p>
	 * <p>
	 * This is the entry point of the class, but can be private, because the it is called through the listener function.
	 * </p>
	 * 
	 * @param ti The selected treeItem to visualise.
	 * @return <code>true</code>, if a displayable item is passed as an argument.
	 */
	private boolean displayTreeItem(TreeItem ti) {
		if (ti.getType() == TreeItemType.VERSION) {
			showVersion((VersionTreeItem) ti);
			return true;
		}

		if (ti instanceof MethodTreeItem) {
			showMethod((MethodTreeItem) ti);
			return true;
		} else if (ti instanceof ApiClassTreeItem) {
			showApiClass((ApiClassTreeItem) ti);
			return true;
		} else if (ti instanceof ProductTreeItem) {
			showProduct((ProductTreeItem) ti);
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Displays method details.
	 * 
	 * @param ti The selected TreeItem holding the method.
	 */
	private void showMethod(final MethodTreeItem ti) {
		// Sets up the widget data.
		final Method m = ti.getValue();
		layout.topControl = cmpMethodDetails;
		txtMethodName.setText(m.getSignature());
		chkMethodIsStatic.setSelection(m.isStatic());
		
		// Add the version links to the container.
		cmpMethodVersions = putVersionLinksIntoComposite(m.getVersions(), cmpMethodVersions);
	}

	private void showApiClass(final ApiClassTreeItem ti) {
		// Sets up the widget data.
		final ApiClass ac = ti.getValue();
		layout.topControl = cmpClassDetails;
		txtClassName.setText(ac.getFqName());

		// Add the version links to the container.
		cmpClassVersions = putVersionLinksIntoComposite(ac.getVersions(), cmpClassVersions);
	}

	private void showProduct(final ProductTreeItem ti) {
		// Sets up the widget data.
		layout.topControl = cmpProductDetails;
		txtProductName.setText(ti.getValue().getName());
		StringBuilder versions = new StringBuilder();
		for (String ver : ti.getValue().getVersions()) {
			versions.append(ver);
			versions.append(' ');
		}
		txtProductVersions.setText(versions.toString());

		Collection<String> committers;
		committers = ti.getCommitters();
		
		// Add the mail links to the container.
		cmbCommitters = putMailtoLinksIntoComposite(committers, cmbCommitters);
	}

	private void showVersion(final VersionTreeItem ti) {
		layout.topControl = cmpVersionDetails;

		String version = ti.getValue();
		String releaser = ti.getReleaser();

		txtVersion.setText(version);
		
		// Add the mail links to the container.
		if ("".equals(releaser)) {
			cmbReleasedBy = putMailtoLinksIntoComposite(Collections.<String> emptyList(), cmbReleasedBy);
		} else {
			cmbReleasedBy = putMailtoLinksIntoComposite(Arrays.asList(releaser), cmbReleasedBy);
		}
		layout.topControl.getParent().layout();
	}

	private Composite putMailtoLinksIntoComposite(Collection<String> names, Composite container) {
		// Dispose the version information.
		Composite containerParent = container.getParent();
		container.dispose();

		// Rebuild widget tree with the new versions.
		container = new Composite(containerParent, SWT.BORDER);

		// setup layout
		RowLayout layout = new RowLayout(SWT.HORIZONTAL);
		layout.fill = true;
		container.setLayout(layout);
		container.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));

		for (final String n : names) {
			Link link = new Link(container, 0);
			link.setText("<a>" + n + "</a>");
			link.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					Program.launch("mailto:" + n + "@cern.ch");
				}
			});
		}

		// Refresh UI to apply changes.
		this.layout.topControl.getParent().layout();
		containerParent.layout();

		return container;
	}

		// Dispose the version information.
	private Composite putVersionLinksIntoComposite(List<String> versions, Composite container) {
		Composite containerParent = container.getParent();
		container.dispose();

		// Rebuild widget tree with the new versions.
		container = new Composite(containerParent, SWT.BORDER);

		// setup layout
		RowLayout layout = new RowLayout(SWT.HORIZONTAL);
		layout.fill = true;
		container.setLayout(layout);
		container.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));

		// add links
		for (final String version : versions) {
			Link link = new Link(container, 0);
			link.setText("<a>" + version + "</a>");
			link.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					try {
						ShowSourceHandler.openSelectedJavaSource(version);
					} catch (Exception e1) {
						Activator
								.getDefault()
								.getLog()
								.log(new Status(IStatus.WARNING, Activator.PLUGIN_ID,
										"Create version links failed. Reason is: " + e1.getMessage() + "."));
					}
				}
			});
		}
		

		// Refresh UI to apply changes.
		this.layout.topControl.getParent().layout();
		containerParent.layout();
		
		return container;
	}
}
