// Auto-generated mechanical sanity test for EntityBaseMock
package org.mockmc.mockmc.generated.proxy.net.md_5.bungee.api.chat.hover.content;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

public class EntityBaseMockTest extends GeneratedTestBase
{
	@Test
	public void testSafeDefaults()
	{
		EntityBaseMock mock = new EntityBaseMock()
		{
		};
		assertNotNull(mock);
		assertSafeDefault(mock.getName());
		assertSafeDefault(mock.toString());
		assertSafeDefault(mock.getId());
		assertSafeDefault(mock.getType());
		assertSafeDefault(mock.requiredAction());
	}
}
