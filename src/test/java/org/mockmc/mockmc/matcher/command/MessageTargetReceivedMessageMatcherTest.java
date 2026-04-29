package org.mockmc.mockmc.matcher.command;

import org.hamcrest.Matcher;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockmc.mockmc.MockMCExtension;
import org.mockmc.mockmc.MockMCInject;
import org.mockmc.mockmc.ServerMock;
import org.mockmc.mockmc.entity.PlayerMock;
import org.mockmc.testutils.matcher.AbstractMatcherTest;

import static org.mockmc.mockmc.matcher.command.MessageTargetReceivedMessageMatcher.hasReceived;

@ExtendWith(MockMCExtension.class)
class MessageTargetReceivedMessageMatcherTest extends AbstractMatcherTest
{

	@MockMCInject
	private ServerMock serverMock;
	@MockMCInject
	private PlayerMock messageTarget;
	private static final String MESSAGE = "Hello world!";

	@Test
	void receivedMessage()
	{
		messageTarget.sendMessage(MESSAGE);
		assertMatches(hasReceived(MESSAGE), messageTarget);
	}

	@Test
	void notReceivedMessage_noMessage()
	{
		assertDoesNotMatch(hasReceived(MESSAGE), messageTarget);
	}

	@Test
	void notReceivedMessage_wrongMessage()
	{
		messageTarget.sendMessage("HELLO WORLD!!");
		assertDoesNotMatch(hasReceived(MESSAGE), messageTarget);
	}

	@Test
	void nullCompatible()
	{
		testIsNullSafe();
	}

	@Test
	void unknownTypeSafe()
	{
		testCopesWithUnknownTypes();
	}

	@Override
	protected Matcher<?> createMatcher()
	{
		return hasReceived(MESSAGE);
	}

}
