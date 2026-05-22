// Auto-generated mechanical sanity test for SculkSensorBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.block.data.type;

import static org.junit.jupiter.api.Assertions.*;

import org.bukkit.block.data.type.SculkSensor;
import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

@SuppressWarnings(
{"deprecation", "java:S1874"})
class SculkSensorBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		SculkSensorBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getPhase());
		assertSafeDefault(mock.getSculkSensorPhase());
	}

	private static class Stub implements SculkSensorBaseMock
	{
		@Override
		public SculkSensor clone()
		{
			return null;
		}
	}
}
