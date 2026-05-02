// Auto-generated mechanical sanity test for DialogBaseBaseMock
package org.mockmc.mockmc.generated.proxy.net.md_5.bungee.api.dialog;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

public class DialogBaseBaseMockTest extends GeneratedTestBase
{
	@Test
	public void testSafeDefaults()
	{
		DialogBaseBaseMock mock = new DialogBaseBaseMock()
		{
		};
		assertNotNull(mock);
		assertSafeDefault(mock.toString());
		assertSafeDefault(mock.body());
		assertSafeDefault(mock.title());
		assertSafeDefault(mock.inputs());
		assertSafeDefault(mock.pause());
		assertSafeDefault(mock.externalTitle());
		assertSafeDefault(mock.canCloseWithEscape());
		assertSafeDefault(mock.afterAction());
	}
}
