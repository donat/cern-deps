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

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.eclipse.core.resources.IStorage;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IPersistableElement;
import org.eclipse.ui.IStorageEditorInput;

/**
 * Arbitrary {@link IEditorInput} implementation to display java source downloaded from http source.
 * <p>
 * It retrieves a static, non-changeable view of the returned source code. The goal is to display the source code
 * natively in the eclipse source code editor.
 * 
 * @author Donat Csikos <dcsikos@cern.ch>
 */
public class ImmutableStringEditorInput implements IStorageEditorInput {
	/**
	 * The string representation of the file.
	 */
	private final String content;

	/**
	 * The name of the file with the extension.
	 */
	private final String fileName;

	/**
	 * Tooltip text;
	 */
	private final String toolTip;

	/**
	 * Dummy path for file, because Eclipse needs it to load as a resource. But, because the represented file is flagged
	 * as non-writable the path is never accessed (and thus, it does not cause any problems).
	 */
	private static final Path dummyPath = new Path("/dev/null");

	private final String url;

	private final String id;

	/**
	 * Constructor.
	 * 
	 * @param content The content of a file.
	 * @param fileName The name of the file.
	 * @param toolTip The tooltip to display on the editor's tab.
	 */
	public ImmutableStringEditorInput(String id, String content, String fileName, String toolTip, String url) {
		this.id = id;
		this.content = content;
		this.fileName = fileName;
		this.toolTip = toolTip;
		this.url = url;

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public IStorage getStorage() throws CoreException {
		return new IStorage() {

			/**
			 * Returns the InputStream view from the content field.
			 */
			@Override
			public InputStream getContents() throws CoreException {
				return new ByteArrayInputStream(content.getBytes());
			}

			/**
			 * Returns, a non-existing, but non-null path.
			 */
			@Override
			public IPath getFullPath() {
				return dummyPath;
			}

			/**
			 * Returns the name of the file.
			 */
			@Override
			public String getName() {
				return fileName;
			}

			/**
			 * The represented file is readOnly.
			 */
			@Override
			public boolean isReadOnly() {
				return true;
			}

			/**
			 * No adapter is provided for this resource.
			 */
			@Override
			public Object getAdapter(@SuppressWarnings("rawtypes") Class adapter) {
				return null;
			}
		};
	}

	/**
	 * The file always exists.
	 */
	@Override
	public boolean exists() {
		return true;
	}

	/**
	 * No specific image.
	 */
	@Override
	public ImageDescriptor getImageDescriptor() {
		return ImageDescriptor.getMissingImageDescriptor();
	}

	/**
	 * Returns the name of the file.
	 */
	@Override
	public String getName() {
		return fileName;
	}

	/**
	 * Returns <code>null</code>, because the resource is not writable.
	 */
	@Override
	public IPersistableElement getPersistable() {
		return null;
	}

	/**
	 * The tooltip is the name of the file (no valid path is available).
	 */
	@Override
	public String getToolTipText() {
		return toolTip;
	}

	/**
	 * No adapter is provided for this resource.
	 */
	@Override
	public Object getAdapter(@SuppressWarnings("rawtypes") Class adapter) {
		return null;
	}

	/**
	 * @return the Fully qualified name of the class which is displayed.
	 */
	public String getUrl() {
		return url;
	}

	public String getId() {
		return id;
	}

}
