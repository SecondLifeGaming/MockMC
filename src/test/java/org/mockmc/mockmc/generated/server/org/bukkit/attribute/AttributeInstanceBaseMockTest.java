// Auto-generated mechanical sanity test for AttributeInstanceBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.attribute;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class AttributeInstanceBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		AttributeInstanceBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getModifiers());
		assertSafeDefault(mock.getAttribute());
	}

	private static class Stub implements AttributeInstanceBaseMock
	{
	}
}
