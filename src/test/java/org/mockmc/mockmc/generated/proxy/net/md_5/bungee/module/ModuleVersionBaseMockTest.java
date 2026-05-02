// Auto-generated mechanical sanity test for ModuleVersionBaseMock
package org.mockmc.mockmc.generated.proxy.net.md_5.bungee.module;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

public class ModuleVersionBaseMockTest extends GeneratedTestBase
{
	@Test
	public void testSafeDefaults()
	{
		ModuleVersionBaseMock mock = new ModuleVersionBaseMock()
		{
		};
		assertNotNull(mock);
		assertSafeDefault(mock.toString());
		assertSafeDefault(mock.getGit());
		assertSafeDefault(mock.getBuild());
	}
}
