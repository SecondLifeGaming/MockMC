// Auto-generated mechanical sanity test for DisconnectReportDetailsBaseMock
package org.mockmc.mockmc.generated.proxy.net.md_5.bungee.protocol.packet;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

public class DisconnectReportDetailsBaseMockTest extends GeneratedTestBase
{
	@Test
	public void testSafeDefaults()
	{
		DisconnectReportDetailsBaseMock mock = new DisconnectReportDetailsBaseMock()
		{
		};
		assertNotNull(mock);
		assertSafeDefault(mock.toString());
		assertSafeDefault(mock.getDetails());
	}
}
