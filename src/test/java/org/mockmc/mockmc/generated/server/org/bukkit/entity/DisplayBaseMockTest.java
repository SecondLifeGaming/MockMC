// Auto-generated mechanical sanity test for DisplayBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class DisplayBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		DisplayBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getTransformation());
		assertSafeDefault(mock.getBillboard());
		assertSafeDefault(mock.getGlowColorOverride());
		assertSafeDefault(mock.getBrightness());
	}

	private static class Stub implements DisplayBaseMock
	{
	}
}
