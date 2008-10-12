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
package net.hillsdon.eclipse.openshell.preferences;

import net.hillsdon.eclipse.openshell.ShellIntegrationPlugin;

import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

public class ShellIntegrationPreferencePage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {

  public ShellIntegrationPreferencePage() {
    super(GRID);
    setPreferenceStore(ShellIntegrationPlugin.getDefault().getPreferenceStore());
  }

  public void createFieldEditors() {
    addField(new StringFieldEditor(PreferenceConstants.SHELL_COMMAND, "Command:", getFieldEditorParent()));
  }

  public void init(final IWorkbench workbench) {
  }

}