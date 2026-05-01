// Auto-generated mechanical sanity test for ResourcePackInfoBaseMock
package org.mockmc.mockmc.generated.proxy.com.velocitypowered.api.proxy.player;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

public class ResourcePackInfoBaseMockTest extends GeneratedTestBase
{
	@Test
	public void testSafeDefaults()
	{
		ResourcePackInfoBaseMock mock = new ResourcePackInfoBaseMock()
		{
		};
		assertSafeDefault(mock.getId());
		assertSafeDefault(mock.getUrl());
		assertSafeDefault(mock.getOrigin());
		assertSafeDefault(mock.getHash());
		assertSafeDefault(mock.getPrompt());
		assertSafeDefault(mock.getOriginalOrigin());
	}
}
