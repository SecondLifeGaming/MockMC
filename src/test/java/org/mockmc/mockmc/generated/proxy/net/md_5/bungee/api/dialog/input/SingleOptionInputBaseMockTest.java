// Auto-generated mechanical sanity test for SingleOptionInputBaseMock
package org.mockmc.mockmc.generated.proxy.net.md_5.bungee.api.dialog.input;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

public class SingleOptionInputBaseMockTest extends GeneratedTestBase
{
	@Test
	public void testSafeDefaults()
	{
		SingleOptionInputBaseMock mock = new SingleOptionInputBaseMock()
		{
		};
		assertNotNull(mock);
		assertSafeDefault(mock.toString());
		assertSafeDefault(mock.options());
		assertSafeDefault(mock.width());
		assertSafeDefault(mock.label());
		assertSafeDefault(mock.labelVisible());
	}
}
