// Auto-generated mechanical sanity test for CommandBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.command;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

@SuppressWarnings(
{"deprecation", "removal", "java:S1874"})
class CommandBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		CommandBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getName());
		assertSafeDefault(mock.toString());
		assertSafeDefault(mock.getLabel());
		assertSafeDefault(mock.getPermissionMessage());
		assertSafeDefault(mock.permissionMessage());
		assertSafeDefault(mock.getDescription());
		assertSafeDefault(mock.getTimingName());
		assertSafeDefault(mock.getAliases());
		assertSafeDefault(mock.getUsage());
		assertSafeDefault(mock.getPermission());
	}

	private static class Stub implements CommandBaseMock
	{
	}
}
