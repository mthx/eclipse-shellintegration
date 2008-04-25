package net.hillsdon.eclipse.openterminal;

import java.io.File;
import java.io.IOException;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

/**
 * Opens a terminal.  Works with directory-like selections in the package and
 * navigation view. 
 * 
 * @author mth
 */
public class OpenTerminalAction implements IObjectActionDelegate {

  private ISelection _selection;

  public void selectionChanged(final IAction action, final ISelection selection) {
    _selection = selection;
  }

	public void setActivePart(final IAction action, final IWorkbenchPart targetPart) {
	}

	public void run(final IAction action) {
    if (_selection instanceof IStructuredSelection) {
      Object selected = ((IStructuredSelection) _selection).getFirstElement();
      openTerminal(getCorrespondingFullPath(selected));
    }
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

  private void openTerminal(final IPath path) {
    try {
      File file = path.toFile();
      while (file != null && !file.isDirectory()) {
        file = file.getParentFile();
      }
      Runtime.getRuntime().exec(new String[] {"gnome-terminal", "--working-directory", file.toString()});
    }
    catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

}
