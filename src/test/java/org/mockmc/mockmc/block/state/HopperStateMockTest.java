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
class HopperStateMockTest extends ContainerStateMockTest
{

	@MockMCInject
	private WorldMock world;
	private BlockMock block;
	private HopperStateMock hopper;

	@Override
	protected ContainerStateMock instance()
	{
		return hopper;
	}

	@BeforeEach
	void setUp()
	{
		this.block = world.getBlockAt(0, 10, 0);
		this.block.setType(Material.HOPPER);
		this.hopper = new HopperStateMock(this.block);
	}

	@Test
	void constructor_Material()
	{
		assertDoesNotThrow(() -> new HopperStateMock(Material.HOPPER));
	}

	@Test
	void constructor_Material_WrongType_ThrowsException()
	{
		assertThrowsExactly(IllegalArgumentException.class, () -> new HopperStateMock(Material.BEDROCK));
	}

	@Test
	void constructor_Block()
	{
		assertDoesNotThrow(() -> new HopperStateMock(new BlockMock(Material.HOPPER)));
	}

	@Test
	void constructor_Block_WrongType_ThrowsException()
	{
		assertThrowsExactly(IllegalArgumentException.class, () -> new HopperStateMock(new BlockMock(Material.BEDROCK)));
	}

	@Test
	void getSnapshot_DifferentInstance()
	{
		assertNotSame(hopper, hopper.getSnapshot());
	}

	@Test
	void blockStateMock_Mock_CorrectType()
	{
		assertInstanceOf(HopperStateMock.class, BlockStateMock.mockState(block));
	}

}
