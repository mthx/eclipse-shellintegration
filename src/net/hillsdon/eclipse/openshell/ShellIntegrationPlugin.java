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
package net.hillsdon.eclipse.openshell;

import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

public class ShellIntegrationPlugin extends AbstractUIPlugin {

	private static ShellIntegrationPlugin _plugin;
	
	public ShellIntegrationPlugin() {
		_plugin = this;
	}

	public void start(final BundleContext context) throws Exception {
		super.start(context);
	}

	public void stop(final BundleContext context) throws Exception {
		super.stop(context);
		_plugin = null;
	}

	public static ShellIntegrationPlugin getDefault() {
		return _plugin;
	}

}
