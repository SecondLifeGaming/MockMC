// Auto-generated mechanical sanity test for DataComponentBuilderBaseMock
package org.mockmc.mockmc.generated.server.io.papermc.paper.datacomponent;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class DataComponentBuilderBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		DataComponentBuilderBaseMock<?> mock = new Stub<>();
		assertNotNull(mock);
		assertSafeDefault(mock.build());
	}

	private static class Stub<C> implements DataComponentBuilderBaseMock<C>
	{
	}
}
