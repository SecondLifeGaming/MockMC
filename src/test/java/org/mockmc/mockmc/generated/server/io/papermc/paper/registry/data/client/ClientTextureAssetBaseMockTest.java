// Auto-generated mechanical sanity test for ClientTextureAssetBaseMock
package org.mockmc.mockmc.generated.server.io.papermc.paper.registry.data.client;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class ClientTextureAssetBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		ClientTextureAssetBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.identifier());
		assertSafeDefault(mock.texturePath());
	}

	private static class Stub implements ClientTextureAssetBaseMock
	{
	}
}
