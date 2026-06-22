// Auto-generated mechanical sanity test for TranslationProviderBaseMock
package org.mockmc.mockmc.generated.proxy.net.md_5.bungee.api.chat;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

@SuppressWarnings("all")
class TranslationProviderBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		TranslationProviderBaseMock mock = new Stub();
		assertNotNull(mock);
		try
		{
			assertSafeDefault(mock.asTranslatableComponent());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
		try
		{
			assertSafeDefault(mock.getTranslationKey());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
	}

	private static class Stub implements TranslationProviderBaseMock
	{
	}
}
