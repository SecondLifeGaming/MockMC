// Auto-generated mechanical sanity test for SimpleAttachableMaterialDataBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.material;

import static org.junit.jupiter.api.Assertions.*;

import org.bukkit.material.SimpleAttachableMaterialData;
import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

@SuppressWarnings(
{"deprecation", "removal", "java:S1874"})
class SimpleAttachableMaterialDataBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults() throws Exception
	{
		SimpleAttachableMaterialDataBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.toString());
		assertSafeDefault(mock.clone());
		assertSafeDefault(mock.getFacing());
	}

	private static class Stub implements SimpleAttachableMaterialDataBaseMock
	{
		@Override
		public SimpleAttachableMaterialData clone()
		{
			return null;
		}
	}
}
