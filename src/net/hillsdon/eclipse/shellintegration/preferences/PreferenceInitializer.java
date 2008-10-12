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
package net.hillsdon.eclipse.shellintegration.preferences;

import net.hillsdon.eclipse.shellintegration.ShellIntegrationPlugin;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;

public class PreferenceInitializer extends AbstractPreferenceInitializer {

	public void initializeDefaultPreferences() {
		IPreferenceStore store = ShellIntegrationPlugin.getDefault().getPreferenceStore();
		// We could switch on OS / window-manager here, or availability in path or similar.
		store.setDefault(PreferenceConstants.SHELL_COMMAND, "gnome-terminal");
	}

}
