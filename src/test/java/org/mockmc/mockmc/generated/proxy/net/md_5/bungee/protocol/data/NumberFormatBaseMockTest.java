// Auto-generated mechanical sanity test for NumberFormatBaseMock
package org.mockmc.mockmc.generated.proxy.net.md_5.bungee.protocol.data;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

public class NumberFormatBaseMockTest extends GeneratedTestBase
{
	@Test
	public void testSafeDefaults()
	{
		NumberFormatBaseMock mock = new NumberFormatBaseMock()
		{
		};
		assertNotNull(mock);
		assertSafeDefault(mock.toString());
		assertSafeDefault(mock.getValue());
		assertSafeDefault(mock.getType());
	}
}
