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
		assertSafeDefault(mock.toString());
		assertSafeDefault(mock.getBuild());
		assertSafeDefault(mock.getGit());
	}
}
