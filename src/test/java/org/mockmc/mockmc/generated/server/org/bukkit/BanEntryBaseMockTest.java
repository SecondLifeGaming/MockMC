// Auto-generated mechanical sanity test for BanEntryBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

@SuppressWarnings(
{"deprecation", "java:S1874"})
class BanEntryBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		BanEntryBaseMock<?> mock = new Stub<>();
		assertNotNull(mock);
		assertSafeDefault(mock.getTarget());
		assertSafeDefault(mock.getReason());
		assertSafeDefault(mock.getExpiration());
		assertSafeDefault(mock.getCreated());
		assertSafeDefault(mock.getBanTarget());
		assertSafeDefault(mock.getSource());
	}

	private static class Stub<T> implements BanEntryBaseMock<T>
	{
	}
}
