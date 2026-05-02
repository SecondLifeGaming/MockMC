// Auto-generated mechanical sanity test for LocationBaseMock
package org.mockmc.mockmc.generated.proxy.net.md_5.bungee.protocol.data;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

public class LocationBaseMockTest extends GeneratedTestBase
{
	@Test
	public void testSafeDefaults()
	{
		LocationBaseMock mock = new LocationBaseMock()
		{
		};
		assertNotNull(mock);
		assertSafeDefault(mock.toString());
		assertSafeDefault(mock.getDimension());
	}
}
