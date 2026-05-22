// Auto-generated mechanical sanity test for BanListTypeBaseMock
package org.mockmc.mockmc.generated.server.io.papermc.paper.ban;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class BanListTypeBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		BanListTypeBaseMock<?> mock = new Stub<>();
		assertNotNull(mock);
		assertSafeDefault(mock.typeClass());
	}

	private static class Stub<T> implements BanListTypeBaseMock<T>
	{
	}
}
