// Auto-generated mechanical sanity test for TitleBaseMock
package org.mockmc.mockmc.generated.proxy.net.md_5.bungee.api;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

public class TitleBaseMockTest extends GeneratedTestBase
{
	@Test
	public void testSafeDefaults() throws Exception
	{
		TitleBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.reset());
		assertSafeDefault(mock.clear());
	}

	private static class Stub implements TitleBaseMock
	{
	}
}
