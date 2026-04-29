package org.mockmc.mockmc.matcher.help;

import org.bukkit.command.defaults.VersionCommand;
import org.bukkit.help.HelpTopicFactory;
import org.bukkit.help.IndexHelpTopic;
import org.hamcrest.Matcher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockmc.mockmc.MockMCExtension;
import org.mockmc.mockmc.MockMCInject;
import org.mockmc.mockmc.ServerMock;
import org.mockmc.mockmc.help.HelpMapMock;
import org.mockmc.testutils.matcher.AbstractMatcherTest;

import java.util.Collections;

import static org.mockmc.mockmc.matcher.help.HelpMapFactoryRegisteredMatcher.hasFactoryRegistered;

@SuppressWarnings(
{"deprecation", "removal", "unchecked"})
@ExtendWith(MockMCExtension.class)
class HelpMapFactoryRegisteredMatcherTest extends AbstractMatcherTest
{

	@MockMCInject
	private ServerMock serverMock;
	private HelpMapMock helpMap;
	private HelpTopicFactory<VersionCommand> helpTopicFactory;

	@BeforeEach
	void setUp()
	{
		this.helpMap = serverMock.getHelpMap();
		this.helpTopicFactory = command -> new IndexHelpTopic("", "short text", "perm", Collections.emptyList());
	}

	@Test
	void registered_matches()
	{
		helpMap.registerHelpTopicFactory(VersionCommand.class, helpTopicFactory);
		assertMatches(hasFactoryRegistered(helpTopicFactory), helpMap);
	}

	@Test
	void notRegistered()
	{
		assertDoesNotMatch(hasFactoryRegistered(helpTopicFactory), helpMap);
	}

	@Test
	void nullSafe()
	{
		testIsNullSafe();
	}

	@Override
	protected Matcher<?> createMatcher()
	{
		return hasFactoryRegistered(helpTopicFactory);
	}

}
