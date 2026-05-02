// Auto-generated mechanical sanity test for ModInfoBaseMock
package org.mockmc.mockmc.generated.proxy.com.velocitypowered.api.util;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

public class ModInfoBaseMockTest extends GeneratedTestBase
{
	@Test
	public void testSafeDefaults()
	{
		ModInfoBaseMock mock = new ModInfoBaseMock()
		{
		};
		assertNotNull(mock);
		assertSafeDefault(mock.toString());
		assertSafeDefault(mock.getType());
		assertSafeDefault(mock.getMods());
	}
}
