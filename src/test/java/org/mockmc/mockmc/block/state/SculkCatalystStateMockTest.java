package org.mockmc.mockmc.block.state;

import org.bukkit.Material;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockmc.mockmc.MockMCExtension;
import org.mockmc.mockmc.MockMCInject;
import org.mockmc.mockmc.block.BlockMock;
import org.mockmc.mockmc.world.WorldMock;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

@SuppressWarnings(
{"deprecation", "removal", "unchecked"})
@ExtendWith(MockMCExtension.class)
class SculkCatalystStateMockTest
{

	@MockMCInject
	private WorldMock world;
	private BlockMock block;
	private SculkCatalystStateMock sculkCatalyst;

	@BeforeEach
	void setUp()
	{
		this.block = world.getBlockAt(0, 10, 0);
		this.block.setType(Material.SCULK_CATALYST);
		this.sculkCatalyst = new SculkCatalystStateMock(this.block);
	}

	@Test
	void constructor_Material()
	{
		assertDoesNotThrow(() -> new SculkCatalystStateMock(Material.SCULK_CATALYST));
	}

	@Test
	void constructor_Material_WrongType_ThrowsException()
	{
		assertThrowsExactly(IllegalArgumentException.class, () -> new SculkCatalystStateMock(Material.BEDROCK));
	}

	@Test
	void constructor_Block()
	{
		assertDoesNotThrow(() -> new SculkCatalystStateMock(new BlockMock(Material.SCULK_CATALYST)));
	}

	@Test
	void constructor_Block_WrongType_ThrowsException()
	{
		assertThrowsExactly(IllegalArgumentException.class,
				() -> new SculkCatalystStateMock(new BlockMock(Material.BEDROCK)));
	}

	@Test
	void getSnapshot_DifferentInstance()
	{
		assertNotSame(sculkCatalyst, sculkCatalyst.getSnapshot());
	}

	@Test
	void blockStateMock_Mock_CorrectType()
	{
		assertInstanceOf(SculkCatalystStateMock.class, BlockStateMock.mockState(block));
	}

}
