// Auto-generated mechanical sanity test for ResolvableProfileBaseMock
package org.mockmc.mockmc.generated.server.io.papermc.paper.datacomponent.item;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class ResolvableProfileBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		ResolvableProfileBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.name());
		assertSafeDefault(mock.resolve());
		assertSafeDefault(mock.properties());
		assertSafeDefault(mock.uuid());
		assertSafeDefault(mock.skinPatch());
	}

	private static class Stub implements ResolvableProfileBaseMock
	{
	}
}
