// Auto-generated mechanical sanity test for InputOptionBaseMock
package org.mockmc.mockmc.generated.proxy.net.md_5.bungee.api.dialog.input;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

public class InputOptionBaseMockTest extends GeneratedTestBase
{
	@Test
	public void testSafeDefaults()
	{
		InputOptionBaseMock mock = new InputOptionBaseMock()
		{
		};
		assertNotNull(mock);
		assertSafeDefault(mock.toString());
		assertSafeDefault(mock.id());
		assertSafeDefault(mock.display());
		assertSafeDefault(mock.initial());
	}
}
