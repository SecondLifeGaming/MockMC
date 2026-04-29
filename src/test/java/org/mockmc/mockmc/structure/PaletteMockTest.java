package org.mockmc.mockmc.structure;

import org.bukkit.Material;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockmc.mockmc.MockMCExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockMCExtension.class)
class PaletteMockTest
{

	private final PaletteMock palette = PaletteMock.of(Material.STONE);

	@Test
	void getBlocks()
	{
		assertEquals(1, palette.getBlocks().size());
	}

	@Test
	void getBlockCount()
	{
		assertEquals(1, palette.getBlockCount());
	}

}
