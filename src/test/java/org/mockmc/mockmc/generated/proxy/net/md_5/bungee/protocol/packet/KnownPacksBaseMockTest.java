// Auto-generated mechanical sanity test for KnownPacksBaseMock
package org.mockmc.mockmc.generated.proxy.net.md_5.bungee.protocol.packet;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

public class KnownPacksBaseMockTest extends GeneratedTestBase
{
	@Test
	public void testSafeDefaults()
	{
		KnownPacksBaseMock mock = new KnownPacksBaseMock()
		{
		};
		assertNotNull(mock);
		assertSafeDefault(mock.toString());
		assertSafeDefault(mock.getKnownPacks());
	}
}
