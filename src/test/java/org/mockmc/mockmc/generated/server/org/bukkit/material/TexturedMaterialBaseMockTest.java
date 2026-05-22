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
		assertSafeDefault(mock.toString());
		assertSafeDefault(mock.clone());
		assertSafeDefault(mock.getMaterial());
		assertSafeDefault(mock.getTextures());
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
