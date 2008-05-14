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
