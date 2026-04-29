package org.mockmc.mockmc.matcher.command;

import org.hamcrest.Matcher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockmc.mockmc.MockMCExtension;
import org.mockmc.mockmc.MockMCInject;
import org.mockmc.mockmc.ServerMock;
import org.mockmc.mockmc.command.CommandResult;
import org.mockmc.testutils.matcher.AbstractMatcherTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockmc.mockmc.matcher.command.CommandResultSucceedMatcher.hasFailed;
import static org.mockmc.mockmc.matcher.command.CommandResultSucceedMatcher.hasSucceeded;

@ExtendWith(MockMCExtension.class)
class CommandResultSucceedMatcherTest extends AbstractMatcherTest
{

	@MockMCInject
	private ServerMock serverMock;
	private CommandResult commandResult;

	@BeforeEach
	void setUp()
	{
		this.commandResult = new CommandResult(true, serverMock.addPlayer());
	}

	@Test
	void succeeded()
	{
		assertThat(commandResult, hasSucceeded());
	}

	@Test
	void notSucceeded()
	{
		CommandResult commandResultFail = new CommandResult(false, serverMock.addPlayer());
		assertThat(commandResultFail, hasFailed());
	}

	@Test
	void description()
	{
		assertDescription("to have a success code", hasSucceeded());
	}

	@Test
	void nullSafe()
	{
		assertNullSafe(hasSucceeded());
	}

	@Test
	void unknownTypeSafe()
	{
		testCopesWithUnknownTypes();
	}

	@Override
	protected Matcher<?> createMatcher()
	{
		return hasSucceeded();
	}

}
