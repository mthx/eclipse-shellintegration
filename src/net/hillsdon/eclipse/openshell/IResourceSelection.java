package net.hillsdon.eclipse.openshell;

import org.eclipse.core.resources.IResource;
import org.eclipse.jface.viewers.ISelection;

/**
 * Converts from generic workbench selection to the corresponding resource
 * when possible.
 * 
 * @author mth
 */
public interface IResourceSelection {

  /**
   * @param selection The new workbench selection, maybe null.
   */
  void setSelection(ISelection selection);

  /**
   * @return The workbench selection as a resource if possible, otherwise null.
   */
  IResource getSelectedResource();

}