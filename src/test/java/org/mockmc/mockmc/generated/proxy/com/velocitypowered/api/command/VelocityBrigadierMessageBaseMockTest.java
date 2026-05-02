// Auto-generated mechanical sanity test for VelocityBrigadierMessageBaseMock
package org.mockmc.mockmc.generated.proxy.com.velocitypowered.api.command;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

public class VelocityBrigadierMessageBaseMockTest extends GeneratedTestBase
{
	@Test
	public void testSafeDefaults()
	{
		VelocityBrigadierMessageBaseMock mock = new VelocityBrigadierMessageBaseMock()
		{
		};
		assertNotNull(mock);
		assertSafeDefault(mock.getString());
		assertSafeDefault(mock.asComponent());
	}
}
