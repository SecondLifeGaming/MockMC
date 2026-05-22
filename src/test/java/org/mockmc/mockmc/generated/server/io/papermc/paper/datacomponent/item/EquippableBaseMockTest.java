// Auto-generated mechanical sanity test for EquippableBaseMock
package org.mockmc.mockmc.generated.server.io.papermc.paper.datacomponent.item;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class EquippableBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		EquippableBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.slot());
		assertSafeDefault(mock.assetId());
		assertSafeDefault(mock.equipSound());
		assertSafeDefault(mock.cameraOverlay());
		assertSafeDefault(mock.allowedEntities());
		assertSafeDefault(mock.shearSound());
	}

	private static class Stub implements EquippableBaseMock
	{
	}
}
