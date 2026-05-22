// Auto-generated mechanical sanity test for ImmutableStringReaderBaseMock
package org.mockmc.mockmc.generated.server.com.mojang.brigadier;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class ImmutableStringReaderBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		ImmutableStringReaderBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getString());
		assertSafeDefault(mock.getRemaining());
		assertSafeDefault(mock.getRead());
	}

	private static class Stub implements ImmutableStringReaderBaseMock
	{
	}
}
