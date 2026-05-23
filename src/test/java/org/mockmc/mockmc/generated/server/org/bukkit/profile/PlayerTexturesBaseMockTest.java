// Auto-generated mechanical sanity test for PlayerTexturesBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.profile;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class PlayerTexturesBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		PlayerTexturesBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getSkin());
		assertSafeDefault(mock.getSkinModel());
		assertSafeDefault(mock.getCape());
	}

	private static class Stub implements PlayerTexturesBaseMock
	{
	}
}
