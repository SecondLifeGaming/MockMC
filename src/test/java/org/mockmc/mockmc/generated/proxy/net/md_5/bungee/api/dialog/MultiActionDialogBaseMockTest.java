// Auto-generated mechanical sanity test for MultiActionDialogBaseMock
package org.mockmc.mockmc.generated.proxy.net.md_5.bungee.api.dialog;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

public class MultiActionDialogBaseMockTest extends GeneratedTestBase
{
	@Test
	public void testSafeDefaults()
	{
		MultiActionDialogBaseMock mock = new MultiActionDialogBaseMock()
		{
		};
		assertNotNull(mock);
		assertSafeDefault(mock.toString());
		assertSafeDefault(mock.getBase());
		assertSafeDefault(mock.exitAction());
		assertSafeDefault(mock.columns());
		assertSafeDefault(mock.actions());
	}
}
