// Auto-generated mechanical sanity test for LegacyChannelIdentifierBaseMock
package org.mockmc.mockmc.generated.proxy.com.velocitypowered.api.proxy.messages;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

public class LegacyChannelIdentifierBaseMockTest extends GeneratedTestBase
{
	@Test
	public void testSafeDefaults()
	{
		LegacyChannelIdentifierBaseMock mock = new LegacyChannelIdentifierBaseMock()
		{
		};
		assertNotNull(mock);
		assertSafeDefault(mock.getName());
		assertSafeDefault(mock.toString());
		assertSafeDefault(mock.getId());
	}
}
