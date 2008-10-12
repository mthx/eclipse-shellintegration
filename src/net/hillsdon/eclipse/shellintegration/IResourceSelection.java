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
  void setWorkbenchSelection(ISelection selection);

  /**
   * @return The workbench selection as a resource if possible, otherwise null.
   */
  IResource getSelectedResource();

}