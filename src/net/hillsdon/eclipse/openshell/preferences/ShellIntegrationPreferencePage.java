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