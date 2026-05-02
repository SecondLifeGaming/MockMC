// Auto-generated mechanical sanity test for PreLoginEventBaseMock
package org.mockmc.mockmc.generated.proxy.com.velocitypowered.api.event.connection;

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
		assertSafeDefault(mock.getResult());
		assertSafeDefault(mock.getUniqueId());
		assertSafeDefault(mock.getConnection());
		assertSafeDefault(mock.getUsername());
	}
}
