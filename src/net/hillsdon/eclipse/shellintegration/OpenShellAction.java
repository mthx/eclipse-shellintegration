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

import java.io.File;
import java.io.IOException;

import net.hillsdon.eclipse.shellintegration.preferences.PreferenceConstants;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;
import org.eclipse.ui.PlatformUI;

/**
 * Opens a shell with a working directory based on the selected resource.
 *
 * @author mth
 */
public class OpenShellAction extends Action implements IWorkbenchWindowActionDelegate {

  private final IResourceSelection _selection;
  private final IPreferenceStore _preferences;
  
  private IWorkbenchWindow _window;

  public OpenShellAction() {
    this(new ResourceSelection(PlatformUI.getWorkbench()), ShellIntegrationPlugin.getDefault().getPreferenceStore());
  }
  
  public OpenShellAction(final IResourceSelection selection, final IPreferenceStore preferences) {
    _selection = selection;
    _preferences = preferences;
  }
  
  public void selectionChanged(final IAction action, final ISelection selection) {
    _selection.setWorkbenchSelection(selection);
    // We're always enabled - we can always open a shell even if it isn't at the ideal location.
  }

  public void run(final IAction action) {
    final IResource resource = _selection.getSelectedResource();
    final IPath path = resource == null ? null : resource.getLocation();
    File file = path == null ? null : path.toFile();
    while (file != null && !file.isDirectory()) {
      file = file.getParentFile();
    }
    try {
      Runtime.getRuntime().exec(_preferences.getString(PreferenceConstants.SHELL_COMMAND), null, file);
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
  
}
