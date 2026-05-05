package org.mockmc.mockmc.entity;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.LlamaSpit;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockmc.mockmc.MockMCExtension;
import org.mockmc.mockmc.MockMCInject;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockMCExtension.class)
class LlamaSpitMockTest
{

	@MockMCInject
	private LlamaSpit llamaSpit;

	@Test
	void testGetType()
	{
		assertEquals(EntityType.LLAMA_SPIT, llamaSpit.getType());
	}

}
