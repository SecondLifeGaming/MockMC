// Auto-generated mechanical sanity test for ObjectiveBaseMock
package org.mockmc.mockmc.generated.proxy.net.md_5.bungee.api.score;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

public class ObjectiveBaseMockTest extends GeneratedTestBase
{
	@Test
	public void testSafeDefaults()
	{
		ObjectiveBaseMock mock = new ObjectiveBaseMock()
		{
		};
		assertNotNull(mock);
		assertSafeDefault(mock.getName());
		assertSafeDefault(mock.toString());
		assertSafeDefault(mock.getValue());
		assertSafeDefault(mock.getType());
	}
}
