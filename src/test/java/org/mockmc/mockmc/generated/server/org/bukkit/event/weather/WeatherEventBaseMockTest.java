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
		assertSafeDefault(mock.getWorld());
	}

	private static class Stub implements WeatherEventBaseMock
	{
	}
}
