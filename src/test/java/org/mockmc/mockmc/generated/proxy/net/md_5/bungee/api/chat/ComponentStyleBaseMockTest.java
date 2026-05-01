// Auto-generated mechanical sanity test for ComponentStyleBaseMock
package org.mockmc.mockmc.generated.proxy.net.md_5.bungee.api.chat;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

public class ComponentStyleBaseMockTest extends GeneratedTestBase
{
	@Test
	public void testSafeDefaults()
	{
		ComponentStyleBaseMock mock = new ComponentStyleBaseMock()
		{
		};
		assertSafeDefault(mock.clone());
		assertSafeDefault(mock.getColor());
		assertSafeDefault(mock.isBoldRaw());
		assertSafeDefault(mock.isItalicRaw());
		assertSafeDefault(mock.isUnderlinedRaw());
		assertSafeDefault(mock.isStrikethroughRaw());
		assertSafeDefault(mock.isObfuscatedRaw());
		assertSafeDefault(mock.getShadowColor());
		assertSafeDefault(mock.getFont());
	}
}
