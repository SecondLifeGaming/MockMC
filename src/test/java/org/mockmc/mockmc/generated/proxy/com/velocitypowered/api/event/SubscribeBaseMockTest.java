// Auto-generated mechanical sanity test for SubscribeBaseMock
package org.mockmc.mockmc.generated.proxy.com.velocitypowered.api.event;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class SubscribeBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults() throws Exception
	{
		SubscribeBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.order());
	}

	private static class Stub implements SubscribeBaseMock
	{
	}
}
