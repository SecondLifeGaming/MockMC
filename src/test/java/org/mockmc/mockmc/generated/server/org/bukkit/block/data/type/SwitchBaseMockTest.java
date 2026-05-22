// Auto-generated mechanical sanity test for SwitchBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.block.data.type;

import static org.junit.jupiter.api.Assertions.*;

import org.bukkit.block.data.type.Switch;
import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

@SuppressWarnings(
{"deprecation", "java:S1874"})
class SwitchBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		SwitchBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getFace());
		assertSafeDefault(mock.getAttachedFace());
	}

	private static class Stub implements SwitchBaseMock
	{
		@Override
		public Switch clone()
		{
			return null;
		}
	}
}
