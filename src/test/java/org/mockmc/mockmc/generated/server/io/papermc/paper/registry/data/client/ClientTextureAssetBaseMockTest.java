// Auto-generated mechanical sanity test for ClientTextureAssetBaseMock
package org.mockmc.mockmc.generated.server.io.papermc.paper.registry.data.client;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

@SuppressWarnings("all")
class ClientTextureAssetBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		ClientTextureAssetBaseMock mock = new Stub();
		assertNotNull(mock);
		try
		{
			assertSafeDefault(mock.texturePath());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
		try
		{
			assertSafeDefault(mock.identifier());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
	}

	private static class Stub implements ClientTextureAssetBaseMock
	{
	}
}
