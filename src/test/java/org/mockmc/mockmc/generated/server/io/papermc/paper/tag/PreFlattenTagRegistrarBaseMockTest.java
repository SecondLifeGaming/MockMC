// Auto-generated mechanical sanity test for PreFlattenTagRegistrarBaseMock
package org.mockmc.mockmc.generated.server.io.papermc.paper.tag;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class PreFlattenTagRegistrarBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		PreFlattenTagRegistrarBaseMock<?> mock = new Stub<>();
		assertNotNull(mock);
		assertSafeDefault(mock.registryKey());
		assertSafeDefault(mock.getAllTags());
	}

	private static class Stub<T> implements PreFlattenTagRegistrarBaseMock<T>
	{
	}
}
