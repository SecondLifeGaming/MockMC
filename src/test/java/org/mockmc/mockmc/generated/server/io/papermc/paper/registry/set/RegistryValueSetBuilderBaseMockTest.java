// Auto-generated mechanical sanity test for RegistryValueSetBuilderBaseMock
package org.mockmc.mockmc.generated.server.io.papermc.paper.registry.set;

import static org.junit.jupiter.api.Assertions.*;

import io.papermc.paper.registry.RegistryBuilder;
import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class RegistryValueSetBuilderBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		RegistryValueSetBuilderBaseMock<?, ?> mock = new Stub<>();
		assertNotNull(mock);
		assertSafeDefault(mock.build());
	}

	private static class Stub<API, ENTRY_BUILDER extends RegistryBuilder<API>>
			implements
				RegistryValueSetBuilderBaseMock<API, ENTRY_BUILDER>
	{
	}
}
