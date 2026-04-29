package org.mockmc.mockmc.entity;

import org.bukkit.entity.AnimalTamer;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Parrot;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockmc.mockmc.MockMCExtension;
import org.mockmc.mockmc.MockMCInject;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockMCExtension.class)
class ParrotMockTest
{

	@MockMCInject
	private ParrotMock parrot;

	@Test
	void testGetType()
	{
		assertEquals(EntityType.PARROT, parrot.getType());
	}

	@Test
	void testSetVariant()
	{
		Parrot.Variant newVariant = Parrot.Variant.BLUE;
		parrot.setVariant(newVariant);
		assertEquals(newVariant, parrot.getVariant());
	}

	@Test
	void testIsTamed()
	{
		assertFalse(parrot.isTamed());
	}

	@Test
	void testSetTamed()
	{
		parrot.setTamed(true);
		assertTrue(parrot.isTamed());
	}

	@Test
	void testIsSitting()
	{
		assertFalse(parrot.isSitting());
	}

	@Test
	void testSetSitting()
	{
		parrot.setSitting(true);
		assertTrue(parrot.isSitting());
	}

	@Test
	void testSetOwner(@MockMCInject AnimalTamer owner)
	{
		parrot.setOwner(owner);
		assertEquals(owner, parrot.getOwner());
	}

	@Test
	void testGetOwnerUniqueId(@MockMCInject AnimalTamer owner)
	{
		parrot.setOwner(owner);
		assertEquals(owner.getUniqueId(), parrot.getOwnerUniqueId());
	}

}
