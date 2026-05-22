// Auto-generated mechanical sanity test for ReferenceBaseMock
package org.mockmc.mockmc.generated.server.io.papermc.paper.registry;

import static org.junit.jupiter.api.Assertions.*;

import org.bukkit.Keyed;
import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

@SuppressWarnings(
{"deprecation", "removal", "java:S1874"})
class ReferenceBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		ReferenceBaseMock<?> mock = new Stub<>();
		assertNotNull(mock);
		assertSafeDefault(mock.value());
		assertSafeDefault(mock.valueOrNull());
	}

	private static class Stub<T extends Keyed> implements ReferenceBaseMock<T>
	{
	}
}
