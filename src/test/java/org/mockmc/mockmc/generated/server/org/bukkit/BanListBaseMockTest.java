// Auto-generated mechanical sanity test for BanListBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

@SuppressWarnings(
{"deprecation", "java:S1874"})
class BanListBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		BanListBaseMock<?> mock = new Stub<>();
		assertNotNull(mock);
		assertSafeDefault(mock.getEntries());
		assertSafeDefault(mock.getBanEntries());
	}

	private static class Stub<T> implements BanListBaseMock<T>
	{
	}
}
