package net.hillsdon.eclipse.openshell;

import org.eclipse.core.resources.IResource;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.IWorkbench;

/**
 * Handles IResources and IJavaElements and ITextSelections.
 * 
 * Is there a general purpose way of doing this?  Perhaps via IAdaptable...
 * 
 * @author mth
 */
public class ResourceSelection implements IResourceSelection {

  private final IWorkbench _workbench;
  
  private ISelection _selection = null;

  public ResourceSelection(final IWorkbench workbench) {
    _workbench = workbench;
  }
  
  public void setSelection(final ISelection selection) {
    _selection = selection;
  }

  public IResource getSelectedResource() {
    Object selected = null;
    if (_selection instanceof IStructuredSelection) {
      selected = ((IStructuredSelection) _selection).getFirstElement();
    }
    else if (_selection instanceof ITextSelection){
      IEditorPart editor = _workbench.getActiveWorkbenchWindow().getActivePage().getActiveEditor();
      IEditorInput editorInput = editor.getEditorInput();
      if (editorInput instanceof IFileEditorInput) {
        selected = ((IFileEditorInput) editorInput).getFile();
      }
    }
    
    if (selected instanceof IResource) {
      return (IResource) selected;
    }
    if (selected instanceof IJavaElement) {
      return ((IJavaElement) selected).getResource();
    }
    return null;
  }
  
}
