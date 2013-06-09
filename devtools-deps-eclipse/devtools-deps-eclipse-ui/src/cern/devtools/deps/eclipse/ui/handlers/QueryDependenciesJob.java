/**
 * Copyright (c) 2013 European Organisation for Nuclear Research (CERN), All Rights Reserved.
 */

package cern.devtools.deps.eclipse.ui.handlers;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Collection;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.eclipse.core.runtime.jobs.IJobChangeListener;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.progress.UIJob;

import cern.devtools.deps.bean.DependencyService;
import cern.devtools.deps.domain.Dependency;
import cern.devtools.deps.eclipse.prefs.PreferenceStore;
import cern.devtools.deps.eclipse.ui.LoggingUtil;
import cern.devtools.deps.eclipse.ui.views.DependencyView;

/**
 * executes the dependency query on a new thread using Eclipse Job API.
 * 
 * @author Donat Csikos <dcsikos@cern.ch>
 */
final class QueryDependenciesJob extends Job implements IJobChangeListener {

    /**
     * Run the query in an arbitrary thread to make it cancellable.
     */
    private class CancellableQueryThread extends Thread {

        private Exception exception = null;
        Collection<Dependency> result;
        private final DependencyService service;

        public CancellableQueryThread(DependencyService service) {
            this.service = service;
        }

        public Exception getExceptionIfHappened() {
            return exception;
        }

        public Collection<Dependency> getResult() {
            return result;
        }

        @Override
        public void run() {
            try {
                result = service.getIncomingDependencies(javaDepsHandler.objectToAnalyse);
            } catch (Exception e) {
                this.exception = e;
            }
        }
    };

    private static DependencyService getDependencyRMIService() throws Exception {
        String rmiConnectionString = null;
        try {
            rmiConnectionString = PreferenceStore.getRmiConnectionString();
            Remote r = Naming.lookup(rmiConnectionString);
            return (DependencyService) r;
        } catch (MalformedURLException e) {
            throw new Exception("Remote location url is not well-formed: " + rmiConnectionString, e);
        } catch (RemoteException e) {
            throw new Exception("Remote service registry is not found under the following address: "
                    + rmiConnectionString, e);
        } catch (NotBoundException e) {
            throw new Exception("Service name is not bound with the address: " + rmiConnectionString, e);
        }
    }


    /**
     * Comment for <code>javaDepsHandler</code>
     */
    private final JavaDepsHandler javaDepsHandler;

    public QueryDependenciesJob(JavaDepsHandler javaDepsHandler, String name) {
        super(name);
        this.javaDepsHandler = javaDepsHandler;
    }

    @Override
    public void aboutToRun(IJobChangeEvent event) {
        // do nothing
    }

    @Override
    public void awake(IJobChangeEvent event) {
        // do nothing

    }

    @Override
    public void done(IJobChangeEvent event) {
        final IStatus status = event.getResult();
        UIJob showResults = new UIJob("Query dependencies") {

            @Override
            public IStatus runInUIThread(IProgressMonitor monitor) {
                // If the graph finished successfully, then display the results.
                if (status.equals(Status.OK_STATUS)) {
                    try {
                        // DependencyView.updateDependecies(objectToAnalyse, dependencies);
                        IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
                        DependencyView view = (DependencyView) window.getActivePage().showView(DependencyView.ID);
                        view.displayNewDependency(javaDepsHandler.objectToAnalyse, javaDepsHandler.dependencies);
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

    @Override
    protected final IStatus run(IProgressMonitor monitor) {

        // Try to connect to the server process
        DependencyService dependencyService = null;
        try {
            dependencyService = getDependencyRMIService();
        } catch (Exception e) {
            LoggingUtil.warnAndLog(e.getMessage(), e);
            return Status.CANCEL_STATUS;
        }

        // Start the query
        CancellableQueryThread queryThread = new CancellableQueryThread(dependencyService);
        queryThread.start();

        // Wait till the query ends or the process gets cancelled
        boolean stop = false;
        while (!stop) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
            }
            if (monitor.isCanceled()) {
                queryThread.interrupt();
                stop = true;
            }
            if (!queryThread.isAlive()) {
                stop = true;
            }
        }

        // Flag the result of the execution
        if (queryThread.getExceptionIfHappened() != null) {
            LoggingUtil.warnAndLog(queryThread.getExceptionIfHappened());
            return Status.CANCEL_STATUS;
        }
        if (queryThread.getResult() != null) {
            javaDepsHandler.dependencies = queryThread.getResult();
            return Status.OK_STATUS;
        } else {
            javaDepsHandler.dependencies = null;
            LoggingUtil.warnAndLog("Thead interrupted during execution", new RuntimeException());
            return Status.CANCEL_STATUS;
        }
    }

    @Override
    public void running(IJobChangeEvent event) {
        // do nothing
    }

    @Override
    public void scheduled(IJobChangeEvent event) {
        // do nothing
    }

    @Override
    public void sleeping(IJobChangeEvent event) {
        // do nothing
    }
}