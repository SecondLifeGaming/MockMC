// Auto-generated mechanical sanity test for VersionedComponentSerializerBaseMock
package org.mockmc.mockmc.generated.proxy.net.md_5.bungee.chat;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

public class VersionedComponentSerializerBaseMockTest extends GeneratedTestBase
{
	@Test
	public void testSafeDefaults()
	{
		VersionedComponentSerializerBaseMock mock = new VersionedComponentSerializerBaseMock()
		{
		};
		assertNotNull(mock);
		assertSafeDefault(mock.getVersion());
		assertSafeDefault(mock.getGson());
	}
}
