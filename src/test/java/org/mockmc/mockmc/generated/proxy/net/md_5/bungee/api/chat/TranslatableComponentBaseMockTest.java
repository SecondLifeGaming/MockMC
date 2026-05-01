// Auto-generated mechanical sanity test for TranslatableComponentBaseMock
package org.mockmc.mockmc.generated.proxy.net.md_5.bungee.api.chat;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

public class TranslatableComponentBaseMockTest extends GeneratedTestBase
{
	@Test
	public void testSafeDefaults()
	{
		TranslatableComponentBaseMock mock = new TranslatableComponentBaseMock()
		{
		};
		assertSafeDefault(mock.toString());
		assertSafeDefault(mock.duplicate());
		assertSafeDefault(mock.getTranslate());
		assertSafeDefault(mock.getFallback());
		assertSafeDefault(mock.getWith());
	}
}
