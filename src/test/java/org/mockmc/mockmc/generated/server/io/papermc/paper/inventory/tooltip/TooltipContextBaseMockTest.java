// Auto-generated mechanical sanity test for TooltipContextBaseMock
package org.mockmc.mockmc.generated.server.io.papermc.paper.inventory.tooltip;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class TooltipContextBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		TooltipContextBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.asAdvanced());
		assertSafeDefault(mock.asCreative());
	}

	private static class Stub implements TooltipContextBaseMock
	{
	}
}
