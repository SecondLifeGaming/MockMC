// Auto-generated mechanical sanity test for PreTransferEventBaseMock
package org.mockmc.mockmc.generated.proxy.com.velocitypowered.api.event.connection;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

public class PreTransferEventBaseMockTest extends GeneratedTestBase
{
	@Test
	public void testSafeDefaults()
	{
		PreTransferEventBaseMock mock = new PreTransferEventBaseMock()
		{
		};
		assertNotNull(mock);
		assertSafeDefault(mock.getResult());
		assertSafeDefault(mock.player());
		assertSafeDefault(mock.originalAddress());
	}
}
