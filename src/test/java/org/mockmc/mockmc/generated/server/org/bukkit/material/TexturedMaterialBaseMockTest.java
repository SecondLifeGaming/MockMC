// Auto-generated mechanical sanity test for TexturedMaterialBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.material;

import static org.junit.jupiter.api.Assertions.*;

import org.bukkit.material.TexturedMaterial;
import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

@SuppressWarnings(
{"deprecation", "removal", "java:S1874"})
class TexturedMaterialBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults() throws Exception
	{
		TexturedMaterialBaseMock mock = new Stub();
		assertNotNull(mock);
		try
		{
			assertSafeDefault(mock.toString());
		} catch (Exception | LinkageError e)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
		try
		{
			assertSafeDefault(mock.clone());
		} catch (Exception | LinkageError e)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
		try
		{
			assertSafeDefault(mock.getMaterial());
		} catch (Exception | LinkageError e)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
		try
		{
			assertSafeDefault(mock.getTextures());
		} catch (Exception | LinkageError e)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
	}

	private static class Stub implements TexturedMaterialBaseMock
	{
		@Override
		public TexturedMaterial clone()
		{
			return null;
		}
	}
}
