// Auto-generated mechanical sanity test for PlainMessageBodyBaseMock
package org.mockmc.mockmc.generated.proxy.net.md_5.bungee.api.dialog.body;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

public class PlainMessageBodyBaseMockTest extends GeneratedTestBase
{
	@Test
	public void testSafeDefaults()
	{
		PlainMessageBodyBaseMock mock = new PlainMessageBodyBaseMock()
		{
		};
		assertNotNull(mock);
		assertSafeDefault(mock.toString());
		assertSafeDefault(mock.width());
		assertSafeDefault(mock.contents());
	}
}
