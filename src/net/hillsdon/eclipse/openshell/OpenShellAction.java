package net.hillsdon.eclipse.openshell;

import java.io.File;
import java.io.IOException;

import net.hillsdon.eclipse.openshell.preferences.PreferenceConstants;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IPathEditorInput;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;
import org.eclipse.ui.PlatformUI;

/**
 * Opens a shell with a working directory based on the selected resource.
 * 
 * Handles IResources and IJavaElements.  Is there a general purpose way
 * of doing this?  Perhaps via IAdaptable...
 *
 * @author mth
 */
public class OpenShellAction extends Action implements IWorkbenchWindowActionDelegate {

  private ISelection _selection;
  private IWorkbenchWindow _window;

  public void run(final IAction action) {
    // IResource or IJavaElement.
    Object selected = null;
    if (_selection instanceof IStructuredSelection) {
      selected = ((IStructuredSelection) _selection).getFirstElement();
    }
    if (_selection instanceof ITextSelection){
      IEditorPart editor = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
      IEditorInput editorInput = editor.getEditorInput();
      if (editorInput instanceof IPathEditorInput) {
        selected = ((IPathEditorInput) editorInput).getPath();
      }
    }
    openShell(getCorrespondingFullPath(selected));
  }

  private IPath getCorrespondingFullPath(final Object selected) {
    if (selected instanceof IResource) {
      return ((IResource) selected).getLocation();
    }
    if (selected instanceof IJavaElement) {
      return getCorrespondingFullPath(((IJavaElement) selected).getResource());
    }
    return null;
  }

  private void openShell(final IPath path) {
    try {
      File file = path == null ? null : path.toFile();
      while (file != null && !file.isDirectory()) {
        file = file.getParentFile();
      }
      String command = ShellIntegrationPlugin.getDefault().getPreferenceStore().getString(PreferenceConstants.SHELL_COMMAND);
      Runtime.getRuntime().exec(command, null, file);
    }
    catch (IOException ex) {
      MessageDialog.openError(_window.getShell(), "Failed to launch shell", "Failed to launch the shell.  Please check your preferences.\nThe error was:\n\n" + ex.getLocalizedMessage());
    }
  }

  public void dispose() {
  }

  public void init(final IWorkbenchWindow window) {
    _window = window;
  }

  public void selectionChanged(final IAction action, final ISelection selection) {
    _selection = selection;
  }
  
}
