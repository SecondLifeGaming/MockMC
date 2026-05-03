// Auto-generated mechanical sanity test for TranslationProviderBaseMock
package org.mockmc.mockmc.generated.proxy.net.md_5.bungee.api.chat;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

public class TranslationProviderBaseMockTest extends GeneratedTestBase
{
	@Test
	public void testSafeDefaults() throws Exception
	{
		TranslationProviderBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getTranslationKey());
		assertSafeDefault(mock.asTranslatableComponent());
	}

	private static class Stub implements TranslationProviderBaseMock
	{
	}
}
