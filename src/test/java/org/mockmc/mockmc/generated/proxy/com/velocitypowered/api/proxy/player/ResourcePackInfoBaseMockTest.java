// Auto-generated mechanical sanity test for ResourcePackInfoBaseMock
package org.mockmc.mockmc.generated.proxy.com.velocitypowered.api.proxy.player;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class ResourcePackInfoBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults() throws Exception
	{
		ResourcePackInfoBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getId());
		assertSafeDefault(mock.getUrl());
		assertSafeDefault(mock.getOrigin());
		assertSafeDefault(mock.getHash());
		assertSafeDefault(mock.getPrompt());
		assertSafeDefault(mock.asBuilder());
		assertSafeDefault(mock.getOriginalOrigin());
	}

	private static class Stub implements ResourcePackInfoBaseMock
	{
	}
}
