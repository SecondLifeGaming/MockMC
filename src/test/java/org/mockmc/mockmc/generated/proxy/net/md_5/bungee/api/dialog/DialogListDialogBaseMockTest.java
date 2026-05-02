// Auto-generated mechanical sanity test for DialogListDialogBaseMock
package org.mockmc.mockmc.generated.proxy.net.md_5.bungee.api.dialog;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

public class DialogListDialogBaseMockTest extends GeneratedTestBase
{
	@Test
	public void testSafeDefaults()
	{
		DialogListDialogBaseMock mock = new DialogListDialogBaseMock()
		{
		};
		assertNotNull(mock);
		assertSafeDefault(mock.toString());
		assertSafeDefault(mock.getBase());
		assertSafeDefault(mock.exitAction());
		assertSafeDefault(mock.columns());
		assertSafeDefault(mock.buttonWidth());
		assertSafeDefault(mock.dialogs());
	}
}
