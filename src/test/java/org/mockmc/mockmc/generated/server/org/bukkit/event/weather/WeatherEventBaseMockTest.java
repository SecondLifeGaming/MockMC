// Auto-generated mechanical sanity test for WeatherEventBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.event.weather;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class WeatherEventBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		WeatherEventBaseMock mock = new Stub();
		assertNotNull(mock);
		try
		{
			assertSafeDefault(mock.getWorld());
		} catch (Exception | LinkageError e)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
	}

	private static class Stub implements WeatherEventBaseMock
	{
	}
}
