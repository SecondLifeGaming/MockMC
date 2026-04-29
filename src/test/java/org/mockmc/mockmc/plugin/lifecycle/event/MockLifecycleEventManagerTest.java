package org.mockmc.mockmc.plugin.lifecycle.event;

import com.mojang.brigadier.Command;
import io.papermc.paper.command.brigadier.Commands;
import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockmc.mockmc.MockMCExtension;
import org.mockmc.mockmc.MockMCInject;
import org.mockmc.mockmc.ServerMock;
import org.mockmc.mockmc.plugin.PluginMock;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockMCExtension.class)
class MockLifecycleEventManagerTest
{

	@MockMCInject
	private ServerMock serverMock;

	@Test
	void pluginOnEnable()
	{
		AtomicBoolean atomicBoolean = new AtomicBoolean(false);
		PluginMock.builder()
				.withOnEnable(
						(pluginMock) -> pluginMock.getLifecycleManager().registerEventHandler(LifecycleEvents.COMMANDS,
								event -> event.registrar().register(Commands.literal("new_command").executes(context ->
								{
									atomicBoolean.set(true);
									return Command.SINGLE_SUCCESS;
								}).build(), "some bukkit help description string", List.of("an_alias"))))
				.build();

		serverMock.addPlayer().performCommand("new_command");
		assertTrue(atomicBoolean.get());
	}

}
