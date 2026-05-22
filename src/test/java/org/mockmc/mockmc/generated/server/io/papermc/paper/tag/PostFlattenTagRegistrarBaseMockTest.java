// Auto-generated mechanical sanity test for PostFlattenTagRegistrarBaseMock
package org.mockmc.mockmc.generated.server.io.papermc.paper.tag;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class PostFlattenTagRegistrarBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		PostFlattenTagRegistrarBaseMock<?> mock = new Stub<>();
		assertNotNull(mock);
		assertSafeDefault(mock.registryKey());
		assertSafeDefault(mock.getAllTags());
	}

	private static class Stub<T> implements PostFlattenTagRegistrarBaseMock<T>
	{
	}
}
