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
package cern.devtools.deps.eclipse.ui.views;

import java.util.Collection;
import java.util.LinkedList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;

import cern.devtools.deps.domain.CodeElement;
import cern.devtools.deps.domain.Dependency;

/**
 * Composite, which contains a drop-down box and a clear button. The drop-down box stores all the previously created
 * results from the dependency queries and the box empties it.
 * 
 * @author Donat Csikos <dcsikos@cern.ch>
 */
public class HistoryCombo extends Composite {

    /**
     * The items queried from the editor.
     */
    private final LinkedList<CodeElement> queriedItems = new LinkedList<CodeElement>();

    /**
     * The result of the queries. Items at the same index in the queried items points is the source of the stored result.
     */
    private final LinkedList<Collection<Dependency>> resultSets = new LinkedList<Collection<Dependency>>();

    /**
     * Combo box control.
     */
    private Combo cmbHistory;

    /**
     * Clear button control.
     */
    private Button btnClear;

    /**
     * Create the composite.
     * 
     * @param parent
     * @param style
     */
    public HistoryCombo(Composite parent, int style) {
        super(parent, style);
        GridLayout gridLayout = new GridLayout(2, false);
        gridLayout.horizontalSpacing = 0;
        gridLayout.marginHeight = 0;
        gridLayout.verticalSpacing = 0;
        gridLayout.marginWidth = 0;
        setLayout(gridLayout);

        cmbHistory = new Combo(this, SWT.NONE);
        cmbHistory.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

        btnClear = new Button(this, SWT.NONE);
        btnClear.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                cmbHistory.removeAll();
                queriedItems.clear();
                resultSets.clear();
            }
        });
        btnClear.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
        btnClear.setText("Clear");
    }

    /**
     * @return <code>true</code>, if there are results stored in this composite.
     */
    public boolean isEmpty() {
        return queriedItems.isEmpty();
    }

    public void add(CodeElement queriedItem, Collection<Dependency> resultSet) {
        queriedItems.add(queriedItem);
        resultSets.add(resultSet);
        cmbHistory.add(queriedItem.getDisplayName() + " (" + resultSet.size() + " dependencies total)");
        cmbHistory.select(cmbHistory.getItemCount() - 1);
        // FIXME: put new function into the interface to display readable name (toString() is for debugging).
    }

    public CodeElement currentQueriedItem() {
        int index = cmbHistory.getSelectionIndex();
        return queriedItems.get(index);
    }

    public Collection<Dependency> currentResultSet() {
        int index = cmbHistory.getSelectionIndex();
        return resultSets.get(index);
    }

    public void clear() {
        queriedItems.clear();
        resultSets.clear();
        cmbHistory.removeAll();
    }

    public void addSelectionListenerToCombo(SelectionListener listener) {
        cmbHistory.addSelectionListener(listener);
    }

    public void addSelectionListenerToButton(SelectionListener listener) {
        btnClear.addSelectionListener(listener);
    }
}
