package org.mockmc.mockmc.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockmc.mockmc.MockMCExtension;
import org.mockmc.mockmc.MockMCInject;
import org.mockmc.mockmc.ServerMock;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockmc.mockmc.matcher.command.CommandResultAnyResponseMatcher.hasAnyResponse;
import static org.mockmc.mockmc.matcher.command.CommandResultAnyResponseMatcher.hasNoResponse;
import static org.mockmc.mockmc.matcher.command.CommandResultResponseMatcher.doesNotHaveResponse;
import static org.mockmc.mockmc.matcher.command.CommandResultResponseMatcher.hasResponse;
import static org.mockmc.mockmc.matcher.command.CommandResultSucceedMatcher.hasFailed;
import static org.mockmc.mockmc.matcher.command.CommandResultSucceedMatcher.hasSucceeded;

@SuppressWarnings(
{"deprecation", "removal", "unchecked"})
@ExtendWith(MockMCExtension.class)
class CommandResultTest
{

	@MockMCInject
	private ServerMock serverMock;
	private MessageTarget target;

	@BeforeEach
	void setUp()
	{
		this.target = serverMock.getConsoleSender();
	}

	@Test
	void hasSucceeded_Succeeded_True()
	{
		CommandResult result = new CommandResult(true, target);
		assertTrue(result.hasSucceeded());
	}

	@Test
	void hasSucceeded_Failed_False()
	{
		CommandResult result = new CommandResult(false, target);
		assertFalse(result.hasSucceeded());
	}

	@Test
	void assertSucceed_Succeeded_DoesNotAssert()
	{
		CommandResult result = new CommandResult(true, target);
		assertThat(result, hasSucceeded());
	}

	@Test
	void assertSucceed_Failed_Asserts()
	{
		CommandResult result = new CommandResult(false, target);
		assertThat(result, hasFailed());
	}

	@Test
	void assertFailed_Succeeded_Asserts()
	{
		CommandResult result = new CommandResult(true, target);
		assertThrows(AssertionError.class, result::assertFailed);
	}

	@Test
	void assertFailed_Failed_DoesNotAssert()
	{
		CommandResult result = new CommandResult(false, target);
		result.assertFailed();
	}

	@Test
	void assertResponse_CorrectResponse_DoesNotAssert()
	{
		ConsoleCommandSenderMock sender = new ConsoleCommandSenderMock();
		sender.sendMessage("Hello world");
		CommandResult result = new CommandResult(true, sender);
		assertThat(result, hasResponse("Hello world"));
	}

	@Test
	void assertResponse_WrongResponse_Asserts()
	{
		ConsoleCommandSenderMock sender = new ConsoleCommandSenderMock();
		sender.sendMessage("Hello world");
		CommandResult result = new CommandResult(true, sender);
		assertThat(result, doesNotHaveResponse("world Hello"));
	}

	@Test
	void assertResponse_WrongFormattedResponse_Asserts()
	{
		ConsoleCommandSenderMock sender = new ConsoleCommandSenderMock();
		sender.sendMessage("Hello 5 world");
		CommandResult result = new CommandResult(true, sender);
		assertThat(result, doesNotHaveResponse(String.format("Hello %d world", 6)));
	}

	@Test
	void assertResponse_NoMessages_Asserts()
	{
		ConsoleCommandSenderMock sender = new ConsoleCommandSenderMock();
		CommandResult result = new CommandResult(true, sender);
		assertThat(result, doesNotHaveResponse("Hello world"));
	}

	@Test
	void assertNoResponse_NoMoreMessage_DoesNotAssert()
	{
		ConsoleCommandSenderMock sender = new ConsoleCommandSenderMock();
		CommandResult result = new CommandResult(true, sender);
		assertThat(result, hasNoResponse());
	}

	@Test
	void assertNoResponse_MoreMessage_Asserts()
	{
		ConsoleCommandSenderMock sender = new ConsoleCommandSenderMock();
		sender.sendMessage("More hello world");
		CommandResult result = new CommandResult(true, sender);
		assertThat(result, hasAnyResponse());
	}

}
