// Auto-generated mechanical sanity test for TextDisplayBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

@SuppressWarnings(
{"deprecation", "java:S1874"})
class TextDisplayBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		TextDisplayBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.text());
		assertSafeDefault(mock.getText());
		assertSafeDefault(mock.getBackgroundColor());
		assertSafeDefault(mock.getAlignment());
	}

	private static class Stub implements TextDisplayBaseMock
	{
	}
}
