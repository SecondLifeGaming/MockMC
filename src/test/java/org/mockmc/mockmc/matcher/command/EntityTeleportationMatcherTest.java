package org.mockmc.mockmc.matcher.command;

import org.bukkit.Location;
import org.hamcrest.Matcher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockmc.mockmc.MockMCExtension;
import org.mockmc.mockmc.MockMCInject;
import org.mockmc.mockmc.ServerMock;
import org.mockmc.mockmc.entity.PlayerMock;
import org.mockmc.testutils.matcher.AbstractMatcherTest;

import static org.mockmc.mockmc.matcher.entity.EntityTeleportationMatcher.hasTeleported;

@ExtendWith(MockMCExtension.class)
class EntityTeleportationMatcherTest extends AbstractMatcherTest
{

	@MockMCInject
	private ServerMock serverMock;
	@MockMCInject
	private PlayerMock teleporter;
	private Location location;

	@BeforeEach
	void setUp()
	{
		location = teleporter.getLocation().clone().add(20, 0, 0);
	}

	@Test
	void teleported()
	{
		teleporter.teleport(location);
		assertMatches(hasTeleported(), teleporter);
	}

	@Test
	void teleported_specifiedLocation()
	{
		teleporter.teleport(location);
		assertMatches(hasTeleported(location, 0), teleporter);
	}

	@Test
	void notTeleported()
	{
		assertDoesNotMatch(hasTeleported(), teleporter);
	}

	@Test
	void notTeleported_specifiedLocation()
	{
		assertDoesNotMatch(hasTeleported(location, 0), teleporter);
	}

	@Test
	void teleported_otherLocation()
	{
		teleporter.teleport(location.clone().add(1, 0, 0));
		assertDoesNotMatch(hasTeleported(location, 0), teleporter);
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
		return hasTeleported(location, 0);
	}

}
