// Auto-generated mechanical sanity test for WarningBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class WarningBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		WarningBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.reason());
	}

	private static class Stub implements WarningBaseMock
	{
	}
}
