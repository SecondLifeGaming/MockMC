// Auto-generated mechanical sanity test for DialogBodyBaseMock
package org.mockmc.mockmc.generated.proxy.net.md_5.bungee.api.dialog.body;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

public class DialogBodyBaseMockTest extends GeneratedTestBase
{
	@Test
	public void testSafeDefaults() throws Exception
	{
		DialogBodyBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.toString());
		assertSafeDefault(mock.getType());
	}

	private static class Stub implements DialogBodyBaseMock
	{
	}
}
