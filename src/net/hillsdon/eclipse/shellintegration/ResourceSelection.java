/*******************************************************************************
 * Copyright (c) 2008 Matthew Hillsdon.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Matthew Hillsdon <matt@hillsdon.net>
 *******************************************************************************/
package net.hillsdon.eclipse.shellintegration;

import org.eclipse.core.resources.IResource;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.team.ui.synchronize.ISynchronizeModelElement;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.IWorkbench;

/**
 * Adapts various common IDE selections to resources.
 * 
 * @author mth
 */
public class ResourceSelection implements IResourceSelection {

  private final IWorkbench _workbench;
  
  private ISelection _selection = null;

  public ResourceSelection(final IWorkbench workbench) {
    _workbench = workbench;
  }
  
  public void setWorkbenchSelection(final ISelection selection) {
    _selection = selection;
  }

  public IResource getSelectedResource() {
    Object selected = null;
    if (_selection instanceof IStructuredSelection) {
      selected = ((IStructuredSelection) _selection).getFirstElement();
    }
    else if (_selection instanceof ITextSelection){
      IEditorPart editor = _workbench.getActiveWorkbenchWindow().getActivePage().getActiveEditor();
      if (editor != null) {
        IEditorInput editorInput = editor.getEditorInput();
        if (editorInput instanceof IFileEditorInput) {
          selected = ((IFileEditorInput) editorInput).getFile();
        }
      }
    }
    
    // Beautiful... oh for IHasCorrespondingResource.
    if (selected instanceof IResource) {
      return (IResource) selected;
    }
    if (selected instanceof IJavaElement) {
      return ((IJavaElement) selected).getResource();
    }
    if (selected instanceof ISynchronizeModelElement) {
      return ((ISynchronizeModelElement) selected).getResource();
    }
    return null;
  }
  
}
