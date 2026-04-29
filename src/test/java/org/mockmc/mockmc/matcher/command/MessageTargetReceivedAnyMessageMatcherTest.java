package org.mockmc.mockmc.matcher.command;

import org.hamcrest.Matcher;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockmc.mockmc.MockMCExtension;
import org.mockmc.mockmc.MockMCInject;
import org.mockmc.mockmc.ServerMock;
import org.mockmc.mockmc.entity.PlayerMock;
import org.mockmc.testutils.matcher.AbstractMatcherTest;

import static org.mockmc.mockmc.matcher.command.MessageTargetReceivedAnyMessageMatcher.hasReceivedAny;

@ExtendWith(MockMCExtension.class)
class MessageTargetReceivedAnyMessageMatcherTest extends AbstractMatcherTest
{

	@MockMCInject
	private ServerMock serverMock;
	@MockMCInject
	private PlayerMock messageTarget;
	private static final String MESSAGE = "Hello world!";

	@Test
	void receivedAny()
	{
		messageTarget.sendMessage(MESSAGE);
		assertMatches(hasReceivedAny(), messageTarget);
	}

	@Test
	void notReceivedAny()
	{
		assertDoesNotMatch(hasReceivedAny(), messageTarget);
	}

	@Test
	void description()
	{
		assertDescription("to receive any message", hasReceivedAny());
	}

	@Test
	void nullSafe()
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
		return hasReceivedAny();
	}

}
