package org.mockbukkit.mockbukkit.plugin;

import org.bukkit.event.Event;
import org.bukkit.event.Listener;
import org.bukkit.event.server.PluginDisableEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginLoader;
import org.bukkit.plugin.RegisteredListener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.java.JavaPluginLoader;
import org.jetbrains.annotations.NotNull;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

@SuppressWarnings(
{"deprecation", "removal", "unchecked"})
public class MockBukkitPluginLoader
		implements
			PluginLoader,
			org.mockbukkit.mockbukkit.generated.org.bukkit.plugin.PluginLoaderBaseMock
{
	@Override
	public Pattern[] getPluginFileFilters()
	{
		return new Pattern[]
		{Pattern.compile("\\.jar$")};
	}

	@Override
	@NotNull
	public Map<Class<? extends Event>, Set<RegisteredListener>> createRegisteredListeners(@NotNull Listener listener,
			@NotNull Plugin plugin)
	{
		return new JavaPluginLoader(plugin.getServer()).createRegisteredListeners(listener, plugin);
	}

	@Override
	public void disablePlugin(@NotNull Plugin plugin)
	{
		((JavaPlugin) plugin).setEnabled(false);
		plugin.getServer().getPluginManager().callEvent(new PluginDisableEvent(plugin));
	}

	@Override
	public void enablePlugin(@NotNull Plugin plugin)
	{
		((JavaPlugin) plugin).setEnabled(true);
	}
}
