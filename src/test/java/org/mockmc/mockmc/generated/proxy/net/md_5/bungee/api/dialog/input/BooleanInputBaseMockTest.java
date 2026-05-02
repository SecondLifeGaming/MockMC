// Auto-generated mechanical sanity test for BooleanInputBaseMock
package org.mockmc.mockmc.generated.proxy.net.md_5.bungee.api.dialog.input;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

public class BooleanInputBaseMockTest extends GeneratedTestBase
{
	@Test
	public void testSafeDefaults()
	{
		BooleanInputBaseMock mock = new BooleanInputBaseMock()
		{
		};
		assertNotNull(mock);
		assertSafeDefault(mock.toString());
		assertSafeDefault(mock.label());
		assertSafeDefault(mock.initial());
		assertSafeDefault(mock.onTrue());
		assertSafeDefault(mock.onFalse());
	}
}
