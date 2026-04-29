package org.mockmc.mockmc.entity;

import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Turtle;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockmc.mockmc.MockMCExtension;
import org.mockmc.mockmc.MockMCInject;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@ExtendWith(MockMCExtension.class)
class TurtleMockTest
{

	@MockMCInject
	private Turtle turtle;

	@Test
	void testGetType()
	{
		assertEquals(EntityType.TURTLE, turtle.getType());
	}

	@Test
	void testHasEgg()
	{
		assertFalse(turtle.hasEgg());
	}

	@Test
	void testGetHomeDefault()
	{
		assertEquals(new Location(null, 0, 0, 0), turtle.getHome());
	}

	@Test
	void testSetHome()
	{
		Location location = new Location(null, 1, 2, 3);
		turtle.setHome(location);
		assertEquals(location, turtle.getHome());
	}

	@Test
	void getEyeHeight_GivenDefaultTurtle()
	{
		assertEquals(0.34D, turtle.getEyeHeight());
	}

	@Test
	void getEyeHeight_GivenBabyTurtle()
	{
		turtle.setBaby();
		assertEquals(0.102D, turtle.getEyeHeight());
	}

}
