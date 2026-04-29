package org.mockmc.mockmc.entity;

import org.bukkit.entity.EntityType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockmc.mockmc.MockMCExtension;
import org.mockmc.mockmc.MockMCInject;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockMCExtension.class)
class TraderLlamaMockTest
{

	@MockMCInject
	private TraderLlamaMock llama;

	@Test
	void getType()
	{
		assertEquals(EntityType.TRADER_LLAMA, llama.getType());
	}

}
