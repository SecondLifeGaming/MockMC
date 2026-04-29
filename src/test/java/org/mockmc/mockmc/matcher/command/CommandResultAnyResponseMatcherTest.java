package org.mockmc.mockmc.matcher.command;

import org.hamcrest.Matcher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockmc.mockmc.MockMCExtension;
import org.mockmc.mockmc.MockMCInject;
import org.mockmc.mockmc.ServerMock;
import org.mockmc.mockmc.command.CommandResult;
import org.mockmc.mockmc.entity.PlayerMock;
import org.mockmc.testutils.matcher.AbstractMatcherTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockmc.mockmc.matcher.command.CommandResultAnyResponseMatcher.hasAnyResponse;
import static org.mockmc.mockmc.matcher.command.CommandResultAnyResponseMatcher.hasNoResponse;

@ExtendWith(MockMCExtension.class)
class CommandResultAnyResponseMatcherTest extends AbstractMatcherTest
{

	@MockMCInject
	private ServerMock serverMock;
	@MockMCInject
	private PlayerMock sender;
	private CommandResult commandResult;
	private static final String MESSAGE = "Hello world!";

	@BeforeEach
	void setUp()
	{
		commandResult = new CommandResult(true, sender);
	}

	@Test
	void anyResponse()
	{
		sender.sendMessage(MESSAGE);
		assertThat(commandResult, hasAnyResponse());
	}

	@Test
	void notAnyResponse()
	{
		assertThat(commandResult, hasNoResponse());
	}

	@Test
	void description()
	{
		assertDescription("to have any messages sent to command sender", hasAnyResponse());
	}

	@Test
	void nullSafe()
	{
		assertNullSafe(hasAnyResponse());
	}

	@Test
	void unknownTypeSafe()
	{
		testCopesWithUnknownTypes();
	}

	@Override
	protected Matcher<?> createMatcher()
	{
		return hasAnyResponse();
	}

}
