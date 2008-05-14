package net.hillsdon.eclipse.openshell.preferences;

import net.hillsdon.eclipse.openshell.ShellIntegrationPlugin;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;

public class PreferenceInitializer extends AbstractPreferenceInitializer {

	public void initializeDefaultPreferences() {
		IPreferenceStore store = ShellIntegrationPlugin.getDefault().getPreferenceStore();
		// We could switch on OS / window-manager here, or availability in path or similar.
		store.setDefault(PreferenceConstants.SHELL_COMMAND, "gnome-terminal");
	}

}
