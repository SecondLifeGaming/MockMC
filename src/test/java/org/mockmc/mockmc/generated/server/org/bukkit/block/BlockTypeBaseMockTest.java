// Auto-generated mechanical sanity test for BlockTypeBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.block;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

@SuppressWarnings(
{"deprecation", "removal", "java:S1874"})
class BlockTypeBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		BlockTypeBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.createBlockData());
		assertSafeDefault(mock.getItemType());
		assertSafeDefault(mock.getTranslationKey());
		assertSafeDefault(mock.asMaterial());
		assertSafeDefault(mock.typed());
		assertSafeDefault(mock.getBlockDataClass());
		assertSafeDefault(mock.createBlockDataStates());
	}

	private static class Stub implements BlockTypeBaseMock
	{
	}
}
