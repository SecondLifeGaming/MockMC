// Auto-generated mechanical sanity test for StringTagBaseMock
package org.mockmc.mockmc.generated.proxy.net.md_5.bungee.nbt.type;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

public class StringTagBaseMockTest extends GeneratedTestBase
{
	@Test
	public void testSafeDefaults()
	{
		StringTagBaseMock mock = new StringTagBaseMock()
		{
		};
		assertNotNull(mock);
		assertSafeDefault(mock.toString());
		assertSafeDefault(mock.getValue());
	}
}
