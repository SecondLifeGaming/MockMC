package org.mockmc.mockmc.matcher.entity;

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

import static org.mockmc.mockmc.matcher.entity.EntityLocationMatcher.isInLocation;

@ExtendWith(MockMCExtension.class)
class EntityLocationMatcherTest extends AbstractMatcherTest
{

	@MockMCInject
	private ServerMock serverMock;
	@MockMCInject
	private PlayerMock entity;
	private Location location;

	@BeforeEach
	void setUp()
	{
		this.location = entity.getLocation().clone();
	}

	@Test
	void inLocation()
	{
		assertMatches(isInLocation(location, 0), entity);
	}

	@Test
	void notInLocation()
	{
		assertDoesNotMatch(isInLocation(location.add(1, 0, 0), 0), entity);
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
		return isInLocation(location, 0);
	}

}
