// Auto-generated mechanical sanity test for NumberRangeInputBaseMock
package org.mockmc.mockmc.generated.proxy.net.md_5.bungee.api.dialog.input;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

public class NumberRangeInputBaseMockTest extends GeneratedTestBase
{
	@Test
	public void testSafeDefaults()
	{
		NumberRangeInputBaseMock mock = new NumberRangeInputBaseMock()
		{
		};
		assertNotNull(mock);
		assertSafeDefault(mock.toString());
		assertSafeDefault(mock.step());
		assertSafeDefault(mock.width());
		assertSafeDefault(mock.label());
		assertSafeDefault(mock.initial());
		assertSafeDefault(mock.labelFormat());
	}
}
