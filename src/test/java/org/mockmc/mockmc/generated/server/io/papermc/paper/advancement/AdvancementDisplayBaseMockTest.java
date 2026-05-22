// Auto-generated mechanical sanity test for AdvancementDisplayBaseMock
package org.mockmc.mockmc.generated.server.io.papermc.paper.advancement;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class AdvancementDisplayBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		AdvancementDisplayBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.displayName());
		assertSafeDefault(mock.frame());
		assertSafeDefault(mock.description());
		assertSafeDefault(mock.icon());
		assertSafeDefault(mock.title());
		assertSafeDefault(mock.backgroundPath());
	}

	private static class Stub implements AdvancementDisplayBaseMock
	{
	}
}
