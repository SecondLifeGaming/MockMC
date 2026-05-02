// Auto-generated mechanical sanity test for NamedTagBaseMock
package org.mockmc.mockmc.generated.proxy.net.md_5.bungee.nbt;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

public class NamedTagBaseMockTest extends GeneratedTestBase
{
	@Test
	public void testSafeDefaults()
	{
		NamedTagBaseMock mock = new NamedTagBaseMock()
		{
		};
		assertNotNull(mock);
		assertSafeDefault(mock.getName());
		assertSafeDefault(mock.toString());
		assertSafeDefault(mock.getTag());
	}
}
