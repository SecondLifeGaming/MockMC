// Auto-generated mechanical sanity test for ClientSettingsBaseMock
package org.mockmc.mockmc.generated.proxy.net.md_5.bungee.protocol.packet;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

public class ClientSettingsBaseMockTest extends GeneratedTestBase
{
	@Test
	public void testSafeDefaults()
	{
		ClientSettingsBaseMock mock = new ClientSettingsBaseMock()
		{
		};
		assertSafeDefault(mock.toString());
		assertSafeDefault(mock.getLocale());
		assertSafeDefault(mock.getParticleStatus());
	}
}
