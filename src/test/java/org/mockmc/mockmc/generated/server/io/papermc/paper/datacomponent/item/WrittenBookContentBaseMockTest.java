// Auto-generated mechanical sanity test for WrittenBookContentBaseMock
package org.mockmc.mockmc.generated.server.io.papermc.paper.datacomponent.item;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class WrittenBookContentBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		WrittenBookContentBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.title());
		assertSafeDefault(mock.author());
		assertSafeDefault(mock.pages());
	}

	private static class Stub implements WrittenBookContentBaseMock
	{
	}
}
