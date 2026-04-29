package org.mockmc.mockmc.entity;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockmc.mockmc.MockMCExtension;
import org.mockmc.mockmc.MockMCInject;
import org.mockmc.mockmc.ServerMock;
import org.mockmc.mockmc.entity.data.EntitySubType;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockMCExtension.class)
class AgeableMockTest
{

	@MockMCInject
	private ServerMock serverMock;
	@MockMCInject
	private AgeableMock ageable;

	@Test
	void testGetAgeDefault()
	{
		assertEquals(0, ageable.getAge());
	}

	@Test
	void testSetAge()
	{
		ageable.setAge(10);
		assertEquals(10, ageable.getAge());
	}

	@Test
	void testGetAgeLock()
	{
		assertFalse(ageable.getAgeLock());
	}

	@Test
	void testSetAgeLock()
	{
		ageable.setAgeLock(true);
		assertTrue(ageable.getAgeLock());
	}

	@Test
	void testSetBaby()
	{
		ageable.setBaby();
		assertFalse(ageable.isAdult());
	}

	@Test
	void testSetAdult()
	{
		ageable.setAdult();
		assertTrue(ageable.isAdult());
	}

	@Test
	void testCanBreed()
	{
		assertTrue(ageable.canBreed());
	}

	@Test
	void testCanBreedFalse()
	{
		ageable.setAge(1);
		assertFalse(ageable.canBreed());
	}

	@Test
	void testSetBreed()
	{
		ageable.setBreed(true);
		assertTrue(ageable.isAdult());
	}

	@Test
	void testSetBreedWithAdult()
	{
		ageable.setAdult();
		assertTrue(ageable.isAdult());
		ageable.setBreed(false);
		assertEquals(6000, ageable.getAge());
	}

	@Test
	void testSetBreedFalseWithAdult()
	{
		ageable.setAge(0);
		ageable.setBreed(false);
		assertEquals(6000, ageable.getAge());
	}

	@Test
	void testToString()
	{
		assertEquals("AgeableMock", ageable.toString());
	}

	@Test
	void testSetAdultWhenBaby()
	{
		ageable.setBaby();
		ageable.setAdult();
		assertEquals(0, ageable.getAge());
	}

	@Test
	void testGetEntitySubType()
	{
		ageable.setAdult();
		assertEquals(EntitySubType.DEFAULT, ageable.getSubType());
		ageable.setBaby();
		assertEquals(EntitySubType.BABY, ageable.getSubType());
	}

}
