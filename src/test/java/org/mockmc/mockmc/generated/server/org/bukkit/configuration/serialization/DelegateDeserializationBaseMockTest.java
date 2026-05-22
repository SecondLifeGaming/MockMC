// Auto-generated mechanical sanity test for DelegateDeserializationBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.configuration.serialization;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class DelegateDeserializationBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		DelegateDeserializationBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.value());
	}

	private static class Stub implements DelegateDeserializationBaseMock
	{
	}
}
