// Auto-generated mechanical sanity test for TextInputBaseMock
package org.mockmc.mockmc.generated.proxy.net.md_5.bungee.api.dialog.input;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

public class TextInputBaseMockTest extends GeneratedTestBase
{
	@Test
	public void testSafeDefaults()
	{
		TextInputBaseMock mock = new TextInputBaseMock()
		{
		};
		assertNotNull(mock);
		assertSafeDefault(mock.toString());
		assertSafeDefault(mock.width());
		assertSafeDefault(mock.label());
		assertSafeDefault(mock.maxLength());
		assertSafeDefault(mock.multiline());
		assertSafeDefault(mock.labelVisible());
		assertSafeDefault(mock.initial());
	}
}
