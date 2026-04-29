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

import static org.mockmc.mockmc.matcher.command.CommandResultResponseMatcher.hasResponse;

@ExtendWith(MockMCExtension.class)
class CommandResultResponseMatcherTest extends AbstractMatcherTest
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
		this.commandResult = new CommandResult(true, sender);
	}

	@Test
	void matches()
	{
		sender.sendMessage(MESSAGE);
		assertMatches(hasResponse(MESSAGE), commandResult);
	}

	@Test
	void doesNotMatch_null()
	{
		assertDoesNotMatch(hasResponse(MESSAGE), commandResult);
	}

	@Test
	void doesNotMatch_differentMessage()
	{
		sender.sendMessage("Hewwo wowd!");
		assertDoesNotMatch(hasResponse(MESSAGE), commandResult);
	}

	@Test
	void nullSafe()
	{
		assertNullSafe(hasResponse(MESSAGE));
	}

	@Test
	void unknownTypeSafe()
	{
		testCopesWithUnknownTypes();
	}

	@Override
	protected Matcher<?> createMatcher()
	{
		return hasResponse(MESSAGE);
	}

}
