// Auto-generated mechanical sanity test for ModuleSpecBaseMock
package org.mockmc.mockmc.generated.proxy.net.md_5.bungee.module;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

public class ModuleSpecBaseMockTest extends GeneratedTestBase
{
	@Test
	public void testSafeDefaults()
	{
		ModuleSpecBaseMock mock = new ModuleSpecBaseMock()
		{
		};
		assertNotNull(mock);
		assertSafeDefault(mock.getName());
		assertSafeDefault(mock.toString());
		assertSafeDefault(mock.getFile());
		assertSafeDefault(mock.getProvider());
	}
}
