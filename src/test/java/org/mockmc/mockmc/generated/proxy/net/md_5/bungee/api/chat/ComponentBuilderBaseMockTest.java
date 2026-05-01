// Auto-generated mechanical sanity test for ComponentBuilderBaseMock
package org.mockmc.mockmc.generated.proxy.net.md_5.bungee.api.chat;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

public class ComponentBuilderBaseMockTest extends GeneratedTestBase
{
	@Test
	public void testSafeDefaults()
	{
		ComponentBuilderBaseMock mock = new ComponentBuilderBaseMock()
		{
		};
		assertSafeDefault(mock.reset());
		assertSafeDefault(mock.create());
		assertSafeDefault(mock.build());
		assertSafeDefault(mock.getParts());
		assertSafeDefault(mock.getCurrentComponent());
		assertSafeDefault(mock.resetCursor());
	}
}
