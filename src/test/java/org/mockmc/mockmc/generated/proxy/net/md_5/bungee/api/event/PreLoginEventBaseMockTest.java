// Auto-generated mechanical sanity test for PreLoginEventBaseMock
package org.mockmc.mockmc.generated.proxy.net.md_5.bungee.api.event;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

public class PreLoginEventBaseMockTest extends GeneratedTestBase
{
	@Test
	public void testSafeDefaults()
	{
		PreLoginEventBaseMock mock = new PreLoginEventBaseMock()
		{
		};
		assertNotNull(mock);
		assertSafeDefault(mock.toString());
		assertSafeDefault(mock.getReason());
		assertSafeDefault(mock.getConnection());
		assertSafeDefault(mock.getCancelReason());
		assertSafeDefault(mock.getCancelReasonComponents());
	}
}
