// Auto-generated mechanical sanity test for CommandInvocationBaseMock
package org.mockmc.mockmc.generated.proxy.com.velocitypowered.api.command;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class CommandInvocationBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults() throws Exception
	{
		CommandInvocationBaseMock mock = new Stub<>();
		assertNotNull(mock);
		assertSafeDefault(mock.source());
		assertSafeDefault(mock.arguments());
	}

	private static class Stub<T> implements CommandInvocationBaseMock<T>
	{
	}
}
