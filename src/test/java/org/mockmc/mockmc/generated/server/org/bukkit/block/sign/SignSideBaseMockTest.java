// Auto-generated mechanical sanity test for SignSideBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.block.sign;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

@SuppressWarnings(
{"deprecation", "java:S1874"})
class SignSideBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		SignSideBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.lines());
		assertSafeDefault(mock.getLines());
	}

	private static class Stub implements SignSideBaseMock
	{
	}
}
