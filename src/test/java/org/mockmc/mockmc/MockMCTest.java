package org.mockmc.mockmc;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.server.PluginEnableEvent;
import org.bukkit.plugin.InvalidPluginException;
import org.bukkit.plugin.Plugin;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockmc.mockmc.exception.PluginLoadException;
import org.mockmc.mockmc.plugin.PluginMock;
import org.mockmc.mockmc.plugin.SecondTestPlugin;
import org.mockmc.mockmc.plugin.TestPlugin;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockmc.mockmc.matcher.plugin.PluginManagerFiredEventFilterMatcher.hasFiredFilteredEvent;

@SuppressWarnings(
{"deprecation", "removal", "unchecked"})
@ExtendWith(MockMCExtension.class)
class MockMCTest
{

	private static final String TEST_PLUGIN_FILE_PATH = "extra/TestPlugin/build/libs/TestPlugin.jar";

	@BeforeEach
	void setUp()
	{
		MockMC.setServerInstanceToNull();
	}

	@AfterEach
	void tearDown()
	{
		if (MockMC.isMocked())
		{
			MockMC.unmock();
		}
	}

	@Test
	void paperApiFullVersion_IsReplaced()
	{
		assertNotNull(BuildParameters.PAPER_API_FULL_VERSION);
		assertNotEquals("{{ paperApiFullVersion }}", BuildParameters.PAPER_API_FULL_VERSION);
	}

	@Test
	void setServerInstanceToNull()
	{
		MockMC.mock();
		assertFalse(Bukkit.getServer() == null);
		MockMC.setServerInstanceToNull();
		assertNull(Bukkit.getServer());
	}

	@Test
	void mock_ServerMocked()
	{
		ServerMock server = MockMC.mock();
		assertNotNull(server);
		assertEquals(server, MockMC.getMock());
		assertEquals(server, Bukkit.getServer());
	}

	@Test
	void mock_ServerSafeMocked()
	{
		ServerMock server = MockMC.getOrCreateMock();
		assertNotNull(server);
		assertEquals(server, MockMC.getMock());
		assertEquals(server, MockMC.getOrCreateMock());
	}

	@Test
	void mock_CustomServerMocked()
	{
		CustomServerMock server = MockMC.mock(new CustomServerMock());
		assertNotNull(server);
		assertEquals(server, MockMC.getMock());
		assertEquals(server, Bukkit.getServer());
	}

	@Test
	void isMocked_ServerNotMocked_False()
	{
		assertFalse(MockMC.isMocked());
	}

	@Test
	void isMocked_ServerMocked_True()
	{
		MockMC.mock();
		assertTrue(MockMC.isMocked());
	}

	@Test
	void isMocked_ServerUnloaded_False()
	{
		MockMC.mock();
		MockMC.unmock();
		assertFalse(MockMC.isMocked());
	}

	@Test
	void load_MyPlugin()
	{
		ServerMock server = MockMC.mock();
		TestPlugin plugin = MockMC.load(TestPlugin.class);
		assertThat(server.getPluginManager(),
				hasFiredFilteredEvent(PluginEnableEvent.class, event -> event.getPlugin().equals(plugin)));
		assertTrue(plugin.isEnabled(), "Plugin not enabled");
		assertTrue(plugin.onEnableExecuted, "Plugin's onEnable method not executed");
	}

	@Test
	void load_TestPluginWithExtraParameter_ExtraParameterPassedOn()
	{
		MockMC.mock();
		TestPlugin plugin = MockMC.load(TestPlugin.class, Integer.valueOf(5));
		assertEquals(5, plugin.extra);
	}

	@Test
	void load_TestPluginWithExtraIncorrectParameter_ExceptionThrown()
	{
		MockMC.mock();
		assertThrows(PluginLoadException.class, () -> MockMC.load(TestPlugin.class, "Hello"));
	}

	@Test
	void loadWith_SecondTextPluginAndResourceFileAsString_PluginLoaded()
	{
		MockMC.mock();
		SecondTestPlugin plugin = MockMC.loadWith(SecondTestPlugin.class, "second_plugin.yml");
		assertEquals("SecondTestPlugin", plugin.getName(), "Name was not loaded correctly");
	}

	@Test
	void loadSimple_SecondTextPlugin_PluginLoaded()
	{
		MockMC.mock();
		SecondTestPlugin plugin = MockMC.loadSimple(SecondTestPlugin.class);
		assertEquals("SecondTestPlugin", plugin.getName(), "Name was not set correctly");
		assertEquals("1.0.0", plugin.getDescription().getVersion(), "Version was not set correctly");
	}

	@Test
	void createMockPlugin_CreatesMockPlugin()
	{
		MockMC.mock();
		PluginMock plugin = MockMC.createMockPlugin();
		assertEquals("MockPlugin", plugin.getName());
		assertEquals("1.0.0", plugin.getDescription().getVersion());
		assertTrue(plugin.isEnabled());
	}

	@Test
	void createMockPlugin_CustomName()
	{
		MockMC.mock();
		PluginMock plugin = MockMC.createMockPlugin("MyPlugin");
		assertEquals("MyPlugin", plugin.getName());
		assertTrue(plugin.isEnabled());
	}

	@Test
	void createMockPlugin_CustomName_CustomVersion()
	{
		MockMC.mock();
		PluginMock plugin = MockMC.createMockPlugin("MyPlugin", "6.9.0");
		assertEquals("MyPlugin", plugin.getName());
		assertEquals("6.9.0", plugin.getDescription().getVersion());
		assertTrue(plugin.isEnabled());
	}

	@Test
	void unload_PluginLoaded_PluginDisabled()
	{
		MockMC.mock();
		TestPlugin plugin = MockMC.load(TestPlugin.class);
		assertFalse(plugin.onDisableExecuted);
		MockMC.unmock();
		assertFalse(plugin.isEnabled());
		assertTrue(plugin.onDisableExecuted);
	}

	@Test
	void load_CanLoadPluginFromExternalSource_PluginLoaded()
	{
		MockMC.mock();
		MockMC.loadJar("extra/TestPlugin/build/libs/TestPlugin.jar");
		Plugin[] plugins = MockMC.getMock().getPluginManager().getPlugins();
		assertEquals(1, plugins.length);
		assertEquals("TestPlugin", plugins[0].getName());
	}

	@Test
	void load_PluginWithConfigFile_ConfigFileParsed()
	{
		MockMC.mock();
		TestPlugin plugin = MockMC.load(TestPlugin.class);
		FileConfiguration config = plugin.getConfig();
		String value = config.getString("foo");
		assertEquals("bar", value);
	}

	@Test
	void ensureMocking_Mocking_DoesNothing()
	{
		MockMC.mock();
		assertDoesNotThrow(MockMC::ensureMocking);
	}

	@Test
	void ensureMocking_NotMocking_ThrowsException()
	{
		assertThrows(IllegalStateException.class, MockMC::ensureMocking);
	}

	@Test
	void loadJar_file() throws InvalidPluginException
	{
		MockMC.mock();
		Plugin javaPlugin = MockMC.loadJar(new File(TEST_PLUGIN_FILE_PATH));
		assertNotNull(javaPlugin.getServer());
		assertNotNull(javaPlugin.getDataFolder());
		assertEquals("TestPlugin", javaPlugin.getName());
		FileConfiguration config = javaPlugin.getConfig();
		assertEquals("goo", config.getString("foo"));
		assertEquals("soo", config.getString("boo"));
		assertEquals("bas", config.getList("baa").getLast());
	}

	@Test
	void loadJar_fileName()
	{
		MockMC.mock();
		Plugin javaPlugin = MockMC.loadJar(TEST_PLUGIN_FILE_PATH);
		assertNotNull(javaPlugin.getServer());
		assertNotNull(javaPlugin.getDataFolder());
		assertEquals("TestPlugin", javaPlugin.getName());
		FileConfiguration config = javaPlugin.getConfig();
		assertEquals("goo", config.getString("foo"));
		assertEquals("soo", config.getString("boo"));
		assertEquals("bas", config.getList("baa").getLast());
	}

	private static class CustomServerMock extends ServerMock
	{

	}

	@Test
	void load_WithConfig_FileConfiguration()
	{
		FileConfiguration configuration = new YamlConfiguration();
		configuration.set("foo", "notbar");

		MockMC.mock();
		TestPlugin plugin = MockMC.loadWithConfig(TestPlugin.class, configuration);
		assertNotNull(plugin);

		FileConfiguration config = plugin.getConfig();
		String value = config.getString("foo");
		assertEquals("notbar", value);
	}

	@Test
	void load_WithConfig_File()
	{
		URL resource = this.getClass().getClassLoader().getResource("loadWithConfig/config_test.yml");
		if (resource == null)
		{
			fail();
		}
		File file = new File(resource.getFile());
		MockMC.mock();
		TestPlugin plugin = MockMC.loadWithConfig(TestPlugin.class, file);
		assertNotNull(plugin);

		FileConfiguration config = plugin.getConfig();
		String value = config.getString("foo");
		assertEquals("notbar", value);
	}

	@Test
	void load_WithConfig_InputStream()
	{
		URL resource = this.getClass().getClassLoader().getResource("loadWithConfig/config_test.yml");
		if (resource == null)
		{
			fail();
		}
		File file = new File(resource.getFile());
		try (InputStream inputStream = new FileInputStream(file))
		{
			MockMC.mock();
			TestPlugin plugin = MockMC.loadWithConfig(TestPlugin.class, inputStream);
			assertNotNull(plugin);

			FileConfiguration config = plugin.getConfig();
			String value = config.getString("foo");
			assertEquals("notbar", value);
		} catch (IOException e)
		{
			fail("Couldn't read config input stream", e);
		}

	}

	@Test
	void load_WithConfig_InputStream_FileNotExists()
	{
		try (InputStream inputStream = new ByteArrayInputStream("test data".getBytes()))
		{
			MockMC.mock();
			PluginLoadException runtimeException = assertThrows(PluginLoadException.class,
					() -> MockMC.loadWithConfig(TestPlugin.class, inputStream));
			assertEquals("Couldn't read config input stream", runtimeException.getMessage());
		} catch (IOException e)
		{
			fail("Couldn't read config input stream", e);
		}

	}

}
