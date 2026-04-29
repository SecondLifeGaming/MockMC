package org.mockmc.mockmc.plugin;

import com.destroystokyo.paper.utils.PaperPluginLogger;
import io.papermc.paper.plugin.configuration.PluginMeta;
import io.papermc.paper.plugin.provider.classloader.ConfiguredPluginClassLoader;
import io.papermc.paper.plugin.provider.classloader.PluginClassLoaderGroup;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.mockmc.mockmc.ServerMock;
import org.mockmc.mockmc.exception.UnimplementedOperationException;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

public class MockMCURLClassLoader extends URLClassLoader implements ConfiguredPluginClassLoader
{

	private final File pluginFile;
	private final ServerMock server;
	private final PluginDescriptionFile description;
	private final PluginClassLoaderGroup classLoaderGroup = new MockMCPluginClassLoaderGroup();
	private final File dataFolder;

	public MockMCURLClassLoader(File file, ClassLoader parent, ServerMock server, PluginDescriptionFile description,
			File dataFolder) throws MalformedURLException
	{
		super(new URL[]
		{file.toURI().toURL()}, parent);
		this.pluginFile = file;
		this.server = server;
		this.description = description;
		this.dataFolder = dataFolder;
	}

	@Override
	public PluginMeta getConfiguration()
	{
		return description;
	}

	@Override
	public Class<?> loadClass(@NotNull String s, boolean b, boolean b1, boolean b2) throws ClassNotFoundException
	{
		return super.loadClass(s, b);
	}

	@Override
	public void init(JavaPlugin javaPlugin)
	{
		javaPlugin.init(server, description, dataFolder, pluginFile, this, getConfiguration(),
				PaperPluginLogger.getLogger(getConfiguration()));
	}

	@Override
	public @Nullable JavaPlugin getPlugin()
	{
		// TODO: Auto-generated method stub
		throw new UnimplementedOperationException();
	}

	@Override
	public @Nullable PluginClassLoaderGroup getGroup()
	{
		return classLoaderGroup;
	}

	// This method is override to make sure that the plugin's resources are loaded
	// before the resources of parent classloader
	@Nullable
	@Override
	public URL getResource(String name)
	{
		URL url = findResource(name);
		if (url == null)
		{
			return super.getResource(name);
		}
		return url;
	}

}
