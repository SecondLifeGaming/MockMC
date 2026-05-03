// Auto-generated mechanical sanity test for DependencyBaseMock
package org.mockmc.mockmc.generated.proxy.com.velocitypowered.api.plugin;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

public class DependencyBaseMockTest extends GeneratedTestBase
{
	@Test
	public void testSafeDefaults() throws Exception
	{
		DependencyBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.id());
	}

	private static class Stub implements DependencyBaseMock
	{
	}
}
