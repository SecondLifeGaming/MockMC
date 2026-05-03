// Auto-generated mechanical sanity test for TargetedEventBaseMock
package org.mockmc.mockmc.generated.proxy.net.md_5.bungee.api.event;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

public class TargetedEventBaseMockTest extends GeneratedTestBase
{
	@Test
	public void testSafeDefaults() throws Exception
	{
		TargetedEventBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.toString());
		assertSafeDefault(mock.getSender());
		assertSafeDefault(mock.getReceiver());
	}

	private static class Stub implements TargetedEventBaseMock
	{
	}
}
