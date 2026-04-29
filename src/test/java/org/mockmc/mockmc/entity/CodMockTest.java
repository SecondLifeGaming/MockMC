package org.mockmc.mockmc.entity;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockmc.mockmc.MockMCExtension;
import org.mockmc.mockmc.MockMCInject;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockMCExtension.class)
class CodMockTest
{

	@MockMCInject
	private CodMock cod;

	@Test
	void testGetBaseBucketItem()
	{
		assertEquals(Material.COD_BUCKET, cod.getBaseBucketItem().getType());
	}

	@Test
	void testGetType()
	{
		assertEquals(EntityType.COD, cod.getType());
	}

}
