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

import static org.eclipse.jdt.core.Signature.C_BOOLEAN;
import static org.eclipse.jdt.core.Signature.C_BYTE;
import static org.eclipse.jdt.core.Signature.C_CHAR;
import static org.eclipse.jdt.core.Signature.C_DOUBLE;
import static org.eclipse.jdt.core.Signature.C_FLOAT;
import static org.eclipse.jdt.core.Signature.C_INT;
import static org.eclipse.jdt.core.Signature.C_LONG;
import static org.eclipse.jdt.core.Signature.C_SHORT;
import static org.eclipse.jdt.core.Signature.C_VOID;

import java.util.Collection;
import java.util.EnumSet;
import java.util.Map;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.core.runtime.jobs.JobChangeAdapter;
import org.eclipse.jdt.core.Flags;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IField;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IMethod;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.Signature;
import org.eclipse.jdt.ui.JavaUI;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.ui.progress.UIJob;

import cern.devtools.deps.domain.ApiClass;
import cern.devtools.deps.domain.CodeElement;
import cern.devtools.deps.domain.Dependency;
import cern.devtools.deps.domain.Field;
import cern.devtools.deps.domain.Method;
import cern.devtools.deps.domain.Modifiers;
import cern.devtools.deps.domain.Product;
import cern.devtools.deps.domain.creation.DomainFactory;
import cern.devtools.deps.eclipse.ui.Activator;
import cern.devtools.deps.eclipse.ui.LoggingUtil;
import cern.devtools.deps.eclipse.ui.views.DependencyView;

/**
 * An action handler that finds a fully qualified Java element corresponding to the selected element in the workspace
 * (Projects, classes, methods and field) and executes the dependency analysis query.
 * <p>
 * 
 * This was inspired by the excellent tutorials http://www.vogella.de/articles/EclipseJDT/article.html
 * 
 * @author Lars Vogel, Vito Baggiolini, Donat Csikos
 */
public class JavaDepsHandler extends AbstractHandler {

    private static final String ON_DEMAND_IMPORTS = "on_demand_imports_exist";

    private CodeElement objectToAnalyse;

    private Collection<Dependency> dependencies;

    /**
     * executes the dependency query on a new thread using Eclipse Job API.
     * 
     * @author Donat Csikos <dcsikos@cern.ch>
     */
    final class QueryDependenciesJob extends Job {
        public QueryDependenciesJob(String name) {
            super(name);
        }

        @Override
        protected final IStatus run(IProgressMonitor monitor) {
            try {

                // Run the query in an arbitrary thread to make it cancellable.
                Thread workerThread = new Thread() {
                    @SuppressWarnings("unchecked")
                    public void run() {
                        try {
                            Object o = Activator.getDefault().getDependencyService()
                                    .getIncomingDependencies(objectToAnalyse);
                            dependencies = (Collection<Dependency>) o;
                        } catch (Exception e) {
                            throw new OperationCanceledException(e.getMessage());
                        }
                    }
                };

                // Start the thread.
                workerThread.start();

                do {
                    Thread.sleep(200);

                    // If the job was cancelled, then interrupt the worker thread.
                    if (monitor.isCanceled()) {
                        workerThread.interrupt();
                        return Status.CANCEL_STATUS;
                    }
                    // Otherwise check if the job is finished.
                    else if (!workerThread.isAlive()) {
                        return Status.OK_STATUS;
                    }
                } while (true);

            } catch (InterruptedException e) {
                dependencies = null;
                // On error display a warn message.
                LoggingUtil.warnAndLog(e);
                return Status.CANCEL_STATUS;
            }
        }
    }

    /**
     * Helper class for listening {@link QueryDependenciesJob} execution to finish. If finished, it displays the result
     * of the query on the UI thread.
     * 
     * @author Donat Csikos <dcsikos@cern.ch>
     */
    final class ShowQueryResults extends JobChangeAdapter {
        @Override
        public void done(IJobChangeEvent ce) {
            final IStatus status = ce.getResult();
            UIJob showResults = new UIJob("Query dependencies") {

                @Override
                public IStatus runInUIThread(IProgressMonitor monitor) {
                    // If the graph finished successfully, then display the results.
                    if (status.equals(Status.OK_STATUS)) {
                        try {
                            // DependencyView.updateDependecies(objectToAnalyse, dependencies);
                            IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
                            DependencyView view = (DependencyView) window.getActivePage().showView(DependencyView.ID);
                            view.displayNewDependency(objectToAnalyse, dependencies);
                            PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage()
                                    .showView(DependencyView.ID);
                        } catch (PartInitException e) {
                            LoggingUtil.warnAndLog("The plugin could not open the 'Incoming dependencies' view.", e);
                        }
                    }
                    return Status.OK_STATUS;
                }
            };
            showResults.schedule();
        }
    }

    /**
     * Helper method, joins the strings putting the specified character between the elements
     * <p>
     * 
     * @param arguments the string elements to concatenate, e.g {@code "aa",
     *            "bb", "cc"}
     * @param joinChar the character to us as separation, e.g. {@code ';'}
     * @return the joined string e.g. {@code "aa;bb;cc" }
     */
    // non-static for testing
    static String join(String[] arguments, char joinChar) {
        if (arguments.length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arguments.length - 1; i++) {
            sb.append(arguments[i]).append(joinChar);
        }
        sb.append(arguments[arguments.length - 1]);
        return sb.toString();
    }

    /**
     * Helper method, returns the source type for the specified binary signature character (e.g. B, I, J)
     * 
     * @param sig the binary signature character
     * @return the primitive source type
     */
    private static String bin2Source(char sig) {
        switch (sig) {
        case C_BYTE:
            return "byte";
        case C_CHAR:
            return "char";
        case C_DOUBLE:
            return "double";
        case C_FLOAT:
            return "float";
        case C_INT:
            return "int";
        case C_LONG:
            return "long";
        case C_SHORT:
            return "short";
        case C_VOID:
            return "void";
        case C_BOOLEAN:
            return "boolean";
        }
        throw new IllegalArgumentException("unknown primitive signature type '" + sig + "'");
    }

    @Override
    public Object execute(ExecutionEvent event) throws ExecutionException {

        // if proper attribute is selected, then run the dependency analysis
        ISelection sel = getSelection(event);
        try {
            // Handle the situation when the action is executed from the package explorer.
            if (sel instanceof TreeSelection) {
                objectToAnalyse = getSelectedProject((TreeSelection) sel);
            } else {
                ICompilationUnit comp = findCompilationUnit(event);
                objectToAnalyse = fullyQualify(comp, (ITextSelection) sel);
            }
            startQueryAndDisplayResult();
        } catch (Exception e) {
            // unexpected event: display warning
            MessageDialog.openError(HandlerUtil.getActiveShell(event), "Dependency Analysis Error",
                    "Query dependencies failed. More information available in the error log.");
            Status log = new Status(IStatus.WARNING, Activator.PLUGIN_ID, e.getMessage());
            Activator.getDefault().getLog().log(log);
            e.printStackTrace();
        }

        return null;
    }

    private ICompilationUnit findCompilationUnit(ExecutionEvent event) throws ExecutionException {
        ICompilationUnit comp = getCompilationUnit(event);
        if (comp == null) {
            throw new ExecutionException("Unable to find Java elements for the selected element");
        }
        try {
            if (!comp.isStructureKnown()) {
                throw new ExecutionException(
                        "Structure is not well-known. Make sure your source file compiles before invoking this command");
            }
        } catch (JavaModelException e) {
            throw new ExecutionException(
                    "Java model exception. Make sure your source file compiles before invoking this command");
        }
        return comp;

    }

    private CodeElement getSelectedProject(TreeSelection sel) {
        Object selectedItem = sel.getFirstElement();
        if (selectedItem == null || !(selectedItem instanceof IJavaProject)) {
            return null;
        }
        IJavaProject project = (IJavaProject) selectedItem;
        String name = project.getProject().getName();
        return DomainFactory.creator().createProduct(name);
    }

    /**
     * Return the signature part (arguments and return type) with fully qualified types, e.g.
     * <p>
     * {@code (java.util.Date,java.lang.String[],long):int}
     * 
     * @param packageName
     * @param importMap
     * @param returnType
     * @param parameterTypes
     * @return the signature
     */
    String fullyQualifySignature(String packageName, Map<String, String> importMap, String returnType,
            String... parameterTypes) {
        String[] arguments = fullyQualifyTypes(packageName, importMap, parameterTypes);
        String args = join(arguments, ',');
        String retType = fullyQualifyTypes(packageName, importMap, returnType)[0];
        return "(" + args + ")" + ":" + retType;
    }

    /**
     * Fully qualify the source type (e.g. return "java.util.Date" for "Date", "int[]" for "[I", etc.)
     * 
     * @param packageName the name of the current package, e.g. "my.own.package"
     * @param imports a Map<SimpleName => FullyQualifiedName>, e.g. { Date => java.util.Date }
     * @param shortType type, e.g. QDate, QString, I, [J, etc.
     * @return the fully qualified source type
     */
    // non-private for testing
    public final String fullyQualifyType(String packageName, Map<String, String> imports, String shortType) {
        // arrays:
        if (shortType.startsWith("[")) {
            return fullyQualifyType(packageName, imports, shortType.substring(1)) + "[]";
        }
        // primitive types coded e.g. as I, J, :
        if (shortType.length() == 1) {
            return bin2Source(shortType.charAt(0));
        }
        if (!shortType.startsWith("Q")) {
            return shortType;
        }
        // remove the trailing Q
        String type = shortType.substring(1, shortType.lastIndexOf(';'));

        // FIXME this is a weak heuristics, based on the naming convention that
        // package names are lowercase
        if (type.contains(".") && Character.isLowerCase(type.charAt(0))) {
            // looks like a fully qualified type already
            return type;
        }

        String fqname = imports.get(type);
        if (fqname != null) {
            return fqname;
        }
        try {
            String javaLangType = "java.lang." + type;
            Class.forName(javaLangType);
            return javaLangType;
        } catch (ClassNotFoundException ex) {
            // it was not a java.lang type
        }

        if (!imports.containsKey(ON_DEMAND_IMPORTS)) {
            // must be the current package
            return packageName + "." + type;
        }
        return shortType;
    }

    /**
     * Fully qualify the source types.
     */
    String[] fullyQualifyTypes(String packageName, Map<String, String> imports, String... sourceTypes) {
        String[] fqTypes = new String[sourceTypes.length];
        for (int i = 0; i < sourceTypes.length; i++) {
            fqTypes[i] = fullyQualifyType(packageName, imports, sourceTypes[i]);
        }
        return fqTypes;
    }

    /**
     * Fully qualify an item which was selected in the editor.
     */
    private CodeElement fullyQualify(ICompilationUnit comp, ITextSelection sel) throws JavaModelException {
        String packageName = comp.getPackageDeclarations()[0].getElementName();
        IJavaElement el = comp.getElementAt(sel.getOffset());
        int elType = el.getElementType();
        CodeElement result = null;
        switch (elType) {
        case IJavaElement.METHOD:
            IMethod method = (IMethod) el;
            method.getFlags();
            EnumSet<Modifiers> mods = EnumSet.noneOf(Modifiers.class);
            if (Flags.isStatic(method.getFlags())) {
                mods.add(Modifiers.STATIC);
            }
            result = DomainFactory.creator().createMethod(fullyQualify(method, packageName), mods);

            // create container object hierarchy
            IType type = method.getDeclaringType();
            IJavaProject javaProject = method.getJavaProject();
            ApiClass ac = DomainFactory.creator().createApiClass(fullyQualify(type), EnumSet.noneOf(Modifiers.class));
            Product p = DomainFactory.creator().createProduct(javaProject.getElementName());
            p.getClasses().add(ac);
            ac.setProduct(p);
            ((Method) result).setApiClass(ac);
            ac.getMethods().add((Method) result);

            break;
        case IJavaElement.FIELD:
            result = DomainFactory.creator().createField(fullyQualify((IField) el), EnumSet.noneOf(Modifiers.class));
            type = ((IField) el).getDeclaringType();
            javaProject = ((IField) el).getJavaProject();
            ac = DomainFactory.creator().createApiClass(fullyQualify(type), EnumSet.noneOf(Modifiers.class));
            p = DomainFactory.creator().createProduct(javaProject.getElementName());
            p.getClasses().add(ac);
            ac.setProduct(p);
            ((Field) result).setApiClass(ac);
            ac.getFields().add((Field) result);
            break;
        case IJavaElement.TYPE:
            result = DomainFactory.creator().createApiClass(fullyQualify((IType) el), EnumSet.noneOf(Modifiers.class));
            javaProject = ((IType) el).getJavaProject();
            p = DomainFactory.creator().createProduct(javaProject.getElementName());
            ((ApiClass) result).setProduct(p);
            p.getClasses().add((ApiClass) result);
            break;
        default:
            System.err.println("unknown element: " + el);
            return null;
        }
        return result;
    }

    /**
     * Get fully qualified signature for the selected method.
     */
    private String fullyQualify(IMethod method, String packageName) throws JavaModelException {

        // appends the fully qualified class name
        IType type = (IType) method.getParent();
        String result = fullyQualify(type) + "#";
        result += method.getElementName();

        // process parameter list
        result += "(";
        String[] params = method.getParameterTypes();
        for (int i = 0; i < params.length; ++i) {
            result += decodeJdtSource(params[i], type);

            if (i != (params.length - 1)) {
                result += ',';
            }
        }

        // end parameter list and add return type
        result += "):";
        result += decodeJdtSource(method.getReturnType(), type);

        System.out.println(result);
        return result;
    }

    private static String decodeJdtSource(String jdtType, IType container) throws JavaModelException {
        String result = Signature.toString(jdtType);
        boolean isArray = false;
        int origLength = result.length();
        if (result.contains("[]")) {
            result = result.replace("[]", "");
            isArray = true;
        }
        int newLength = result.length();
        String[][] retTypeArray = container.resolveType(result);

        if (retTypeArray == null) {
            // TODO: this is an assumption, is the only one that can cause resolveType() to return null.
            return Signature.toString(jdtType);
        }
        result = "";
        for (String pkg : retTypeArray[0]) {
            result += pkg + ".";
        }
        result = result.substring(0, result.length() - 1);

        if (isArray) {
            while (newLength < origLength) {
                result += "[]";
                newLength += 2;
            }

        }
        return result;
    }

    /**
     * Return a fully qualify name for a Field
     * 
     * @param el the field
     * @return the FQN
     */
    private String fullyQualify(IField el) {
        return fullyQualify((IType) el.getParent()) + "." + el.getElementName();
    }

    private String fullyQualify(IType el) {
        return el.getFullyQualifiedName().replace('$', '.');
    }

    /**
     * get the compilation unit open in the active Java editor
     * 
     * @param event the Execution Event coming from {@link #execute(ExecutionEvent)}
     * @return the CompilationUnit or {@code null} if none could be found
     */
    ICompilationUnit getCompilationUnit(ExecutionEvent event) {
        IEditorPart editor = HandlerUtil.getActiveEditor(event);
        if (editor == null) {
            return null;
        }
        IEditorInput editorInput = editor.getEditorInput();
        IJavaElement javaEl = JavaUI.getEditorInputJavaElement(editorInput);
        if (javaEl == null) {
            return null;
        }
        if (javaEl instanceof ICompilationUnit) {
            return (ICompilationUnit) javaEl;
        }
        return null;
    }

    /**
     * Get the element selected in the active window
     * 
     * @param event the Execution Event coming from {@link #execute(ExecutionEvent)}
     * @return the selection
     */
    private ISelection getSelection(ExecutionEvent event) {
        IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindow(event);
        IWorkbenchPage page = window.getActivePage();
        return page.getSelection();
    }

    /**
     * Executes the query and displays the result.
     */
    private void startQueryAndDisplayResult() {
        // create long-running job for displaying dependencies
        Job showDepsJob = new QueryDependenciesJob("Query dependencies");
        // when job is done, display results on the UI thread
        showDepsJob.addJobChangeListener(new ShowQueryResults());
        // set job as long running and make it execute
        showDepsJob.setPriority(Job.LONG);
        showDepsJob.schedule();
    }
}
