// Auto-generated mechanical sanity test for ScoreboardObjectiveBaseMock
package org.mockmc.mockmc.generated.proxy.net.md_5.bungee.protocol.packet;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

public class ScoreboardObjectiveBaseMockTest extends GeneratedTestBase
{
	@Test
	public void testSafeDefaults()
	{
		ScoreboardObjectiveBaseMock mock = new ScoreboardObjectiveBaseMock()
		{
		};
		assertNotNull(mock);
		assertSafeDefault(mock.getName());
		assertSafeDefault(mock.toString());
		assertSafeDefault(mock.getValue());
		assertSafeDefault(mock.getType());
		assertSafeDefault(mock.getNumberFormat());
	}
}
