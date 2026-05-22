// Auto-generated mechanical sanity test for TagBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit;

import static org.junit.jupiter.api.Assertions.*;

import org.bukkit.Keyed;
import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class TagBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		TagBaseMock<?> mock = new Stub<>();
		assertNotNull(mock);
		assertSafeDefault(mock.getValues());
	}

	private static class Stub<T extends Keyed> implements TagBaseMock<T>
	{
	}
}
