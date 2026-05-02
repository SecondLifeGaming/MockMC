// Auto-generated mechanical sanity test for DialogInputBaseMock
package org.mockmc.mockmc.generated.proxy.net.md_5.bungee.api.dialog.input;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

public class DialogInputBaseMockTest extends GeneratedTestBase
{
	@Test
	public void testSafeDefaults()
	{
		DialogInputBaseMock mock = new DialogInputBaseMock()
		{
		};
		assertNotNull(mock);
		assertSafeDefault(mock.type());
		assertSafeDefault(mock.toString());
		assertSafeDefault(mock.key());
	}
}
