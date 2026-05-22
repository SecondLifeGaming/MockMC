// Auto-generated mechanical sanity test for SignBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.block;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

@SuppressWarnings(
{"deprecation", "java:S1874"})
class SignBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		SignBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.lines());
		assertSafeDefault(mock.getColor());
		assertSafeDefault(mock.getLines());
		assertSafeDefault(mock.getAllowedEditor());
		assertSafeDefault(mock.getAllowedEditorUniqueId());
	}

	private static class Stub implements SignBaseMock
	{
	}
}
