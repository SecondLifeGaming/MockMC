// Auto-generated mechanical sanity test for HelpTopicFactoryBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.help;

import static org.junit.jupiter.api.Assertions.*;

import org.bukkit.command.Command;
import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class HelpTopicFactoryBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		HelpTopicFactoryBaseMock<?> mock = new Stub<>();
		assertNotNull(mock);
	}

	private static class Stub<TCommand extends Command> implements HelpTopicFactoryBaseMock<TCommand>
	{
	}
}
