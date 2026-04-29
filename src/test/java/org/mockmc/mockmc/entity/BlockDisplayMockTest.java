package org.mockmc.mockmc.entity;

import org.bukkit.Material;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.EntityType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockmc.mockmc.MockMCExtension;
import org.mockmc.mockmc.MockMCInject;
import org.mockmc.mockmc.block.data.WallSignDataMock;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockMCExtension.class)
class BlockDisplayMockTest
{

	@MockMCInject
	private BlockDisplayMock blockDisplay;

	@Test
	void getBlock_default()
	{
		assertEquals(Material.AIR.createBlockData(), blockDisplay.getBlock());
	}

	@Test
	void setBlock()
	{
		BlockData blockData = new WallSignDataMock(Material.OAK_WALL_SIGN);
		blockDisplay.setBlock(blockData);
		assertEquals(blockData, blockDisplay.getBlock());
	}

	@Test
	void getType()
	{
		assertEquals(EntityType.BLOCK_DISPLAY, blockDisplay.getType());
	}

}
