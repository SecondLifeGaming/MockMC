// Auto-generated mechanical sanity test for JukeboxPlayableComponentBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.inventory.meta.components;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

@SuppressWarnings("all")
class JukeboxPlayableComponentBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		JukeboxPlayableComponentBaseMock mock = new Stub();
		assertNotNull(mock);
		try
		{
			assertSafeDefault(mock.getSong());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
		try
		{
			assertSafeDefault(mock.getSongKey());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
	}

	private static class Stub implements JukeboxPlayableComponentBaseMock
	{
	}
}
