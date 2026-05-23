// Auto-generated mechanical sanity test for ContentBaseMock
package org.mockmc.mockmc.generated.proxy.net.md_5.bungee.api.chat.hover.content;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

@SuppressWarnings(
{"deprecation", "java:S1874"})
class ContentBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		ContentBaseMock mock = new Stub();
		assertNotNull(mock);
		try
		{
			assertSafeDefault(mock.toString());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
		try
		{
			assertSafeDefault(mock.requiredAction());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
	}

	private static class Stub implements ContentBaseMock
	{
	}
}
