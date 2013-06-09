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
package cern.devtools.deps.eclipse.ui.handlers;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.net.URL;
import java.util.EnumSet;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.ISelectionService;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;

import cern.devtools.deps.domain.ApiClass;
import cern.devtools.deps.domain.Method;
import cern.devtools.deps.eclipse.ui.LoggingUtil;
import cern.devtools.deps.eclipse.ui.tree.ApiClassTreeItem;
import cern.devtools.deps.eclipse.ui.tree.MethodTreeItem;
import cern.devtools.deps.eclipse.ui.tree.TreeItem;
import cern.devtools.deps.eclipse.ui.tree.TreeItemType;

/**
 * Opens the internal browser and shows the source code of a selected item from the "Incoming dependencies view".
 * 
 * @author Donat Csikos <dcsikos@cern.ch>
 */
public final class ShowSourceHandler extends AbstractHandler {

    /**
     * Editor id to open, when the
     */
    private static final String JAVA_SOURCE_EDITOR_ID = "org.eclipse.jdt.ui.CompilationUnitEditor";

    /**
     * The root location of the source code.
     */
    private static final String SOURCE_BASE_LOC = "http://abwww.cern.ch/ap/dist";

    /**
     * List of the folders in abwww which can contain source code.
     */
    private static final String[] SOURCE_FOLDERS = { "java", "generated" };


    private static boolean checkEditorOpened(String id) throws Exception {
        IEditorReference[] editors = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage()
                .getEditorReferences();
        for (IEditorReference editor : editors) {
            IEditorInput input = editor.getEditorInput();
            if (input instanceof ImmutableStringEditorInput) {
                ImmutableStringEditorInput imInput = (ImmutableStringEditorInput) input;
                if (imInput.getId().equals(id)) {
                    PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage()
                    .activate(editor.getEditor(true));
                    return true;
                }
            }
        }
        return false;
    }

    private static void doOpenJavaSource() throws Exception {
        TreeItem ti = getSelection();
        if (ti != null) {
            openJavaSource(PlatformUI.getWorkbench().getActiveWorkbenchWindow(), ti, null);
        }
    }

    /**
     * Download the source from the URL.
     * 
     * @param url Resource URL.
     * @return The string representation of the content.
     * @throws Exception
     */
    private static String downloadFileContent(URL url) throws Exception {

        // Get an input stream for reading
        InputStream in = url.openStream();

        // Create a buffered input stream for efficiency
        BufferedInputStream bufIn = new BufferedInputStream(in);

        StringWriter writer = new StringWriter();
        // Repeat until end of file
        while (true) {
            int data = bufIn.read();

            // Check for EOF
            if (data == -1) {
                break;
            } else {
                writer.append((char) data);
            }

        }

        // Remove the \r characters from the end of the lines, because the Eclipse Java editor throws a warning
        // message for every line of the source code, because it fails to identify the \r\n as a valid line break.
        String result = writer.toString();
        result = result.replace("\r\n", "\n");
        return result;
    }

    /**
     * @param ac
     * @return {url, source code}
     * @throws Exception
     */
    private static Pair<String, String> downloadSource(ApiClass ac, String selectedVersion) throws Exception {
        IOException exc = null;
        // Try every possible location.
        for (String loc : SOURCE_FOLDERS) {
            String result = SOURCE_BASE_LOC + "/" + ac.getProduct().getContainingFolders() + "/"
                    + selectedVersion + "/src/" + loc + "/" + ac.getSourcePath();
            try {
                // Return if a valid source code is found.
                return Pair.newInstance(result, downloadFileContent(new URL(result)));
            } catch (IOException e) {
                // Store the last exception.
                exc = e;
            }
        }

        // If none of the locations points to a valid source code, than throw an exception.
        throw new IOException(exc);
    }

    private static void downloadSourceAndShow(IWorkbenchWindow window, ApiClass ac, String selectedVersion) throws Exception {
        // download
        Pair<String, String> source = downloadSource(ac, selectedVersion);

        // show
        IWorkbenchPage page = window.getActivePage();
        String cls = ac.getSimpleName() + ".java";
        IDE.openEditor(
                page,
                new ImmutableStringEditorInput(ac.getSourcePath(), source.getSecond(), cls, source.getFirst(), source
                        .getFirst()), JAVA_SOURCE_EDITOR_ID);
    }

    private static TreeItem getSelection() {
        ISelectionService selService = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getSelectionService();
        ISelection selection = selService.getSelection();
        if (selection instanceof IStructuredSelection) {
            IStructuredSelection sSelection = (IStructuredSelection) selection;
            Object element = sSelection.getFirstElement();
            if (element != null && element instanceof TreeItem) {
                TreeItem ti = (TreeItem) element;
                if (EnumSet.of(TreeItemType.CLASS, TreeItemType.METHOD).contains(ti.getType())) {
                    return ti;
                }
            }
        }
        return null;
    }

    /**
     * Opens source file if it is available.
     * 
     * @param window A workbench window, where to display the results.
     * @param treeItem The item to open, if it contains a {@link Method} or an {@link ApiClass} instance.
     * @param version The version to query. If null, then value stored in the treenode will be used.
     * @throws Exception if the file does not exist or not accessible.
     */
    private static void openJavaSource(IWorkbenchWindow window, TreeItem treeItem, String version) throws Exception {

        // Search for the class to display.
        ApiClass ac = null;
        switch (treeItem.getType()) {
        case CLASS:
            ApiClassTreeItem acti = (ApiClassTreeItem) treeItem;
            ac = acti.getValue();
            if (version == null) {
                version = acti.getRepresentedVersion();
            }
            break;
        case METHOD:
            MethodTreeItem mti = (MethodTreeItem) treeItem;
            Method m = mti.getValue();
            ac = m.getApiClass();
            if (version == null) {
                version = mti.getRepresentedVersion();
            }
            break;
        default:
            // We would like to display just source code for classes and methods.
            // If another type should be displayed, the switch statement should be extended.
            throw new UnsupportedOperationException(
                    "There is no source code associated with the following TreeItemType: " + treeItem.getType());
        }

        // Id to identify the editor later.
        String inputId = ac.getSourcePath();
        boolean opened = checkEditorOpened(inputId);

        if (opened) {
            return;
        } else {
            downloadSourceAndShow(window, ac, version);
        }
    }

    /**
     * Open the source code if there is a proper {@link TreeItem} is in the selection. If there there is no selection,
     * it does nothing, else it calls the {@link #openJavaSource(IWorkbenchWindow, TreeItem)} method.
     * 
     * @throws Exception
     */
    public static void openSelectedJavaSource() {
        try {
            doOpenJavaSource();
        } catch (Exception e) {
            LoggingUtil.warnAndLog(e);
        }
    }

    /**
     * Open the source code if there is a proper {@link TreeItem} is in the selection. If there there is no selection,
     * it does nothing, else it calls the {@link #openJavaSource(IWorkbenchWindow, TreeItem)} method.
     * 
     * @throws Exception
     */
    public static void openSelectedJavaSource(String version) throws Exception {
        TreeItem ti = getSelection();
        if (ti != null) {
            openJavaSource(PlatformUI.getWorkbench().getActiveWorkbenchWindow(), ti, version);
        }

    }

    @Override
    public Object execute(final ExecutionEvent event) throws ExecutionException {
        openSelectedJavaSource();
        return null;
    }
}
