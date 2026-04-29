package org.mockmc.mockmc.block.state;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.BlockState;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.mockmc.mockmc.MockMCExtension;
import org.mockmc.mockmc.MockMCInject;
import org.mockmc.mockmc.ServerMock;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;

@ExtendWith(MockMCExtension.class)
class BlockStateMockFactoryTest
{

	@MockMCInject
	private ServerMock server;

	@ParameterizedTest
	@CsvFileSource(resources = "/blocks/block_states.csv")
	void validatePossibleStates_GivenMaterial(Material material, Class<? extends BlockState> blockStateClass)
	{
		@NotNull
		BlockState block = BlockStateMockFactory.mock(material);
		assertInstanceOf(blockStateClass, block);
	}

	@ParameterizedTest
	@CsvFileSource(resources = "/blocks/block_states.csv")
	void validatePossibleStates_GivenBlock(Material material, Class<? extends BlockState> blockStateClass)
	{
		World world = server.addSimpleWorld("test");
		world.setType(0, 63, 0, Material.BEDROCK);
		world.setType(0, 64, 0, material);

		@NotNull
		BlockState block = world.getBlockAt(0, 64, 0).getState();
		assertInstanceOf(blockStateClass, block);
	}

}
