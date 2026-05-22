// Auto-generated mechanical sanity test for OldEnumBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.util;

import static org.junit.jupiter.api.Assertions.*;

import org.bukkit.util.OldEnum;
import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

@SuppressWarnings(
{"deprecation", "removal", "java:S1874"})
class OldEnumBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		OldEnumBaseMock<?> mock = new Stub<>();
		assertNotNull(mock);
		assertSafeDefault(mock.name());
	}

	private static class Stub<T extends OldEnum<T>> implements OldEnumBaseMock<T>
	{
	}
}
