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
class DaylightDetectorStateMockTest
{

	@MockMCInject
	private WorldMock world;
	private BlockMock block;
	private DaylightDetectorStateMock detector;

	@BeforeEach
	void setUp()
	{
		this.block = world.getBlockAt(0, 10, 0);
		this.block.setType(Material.DAYLIGHT_DETECTOR);
		this.detector = new DaylightDetectorStateMock(this.block);
	}

	@Test
	void constructor_Material()
	{
		assertDoesNotThrow(() -> new DaylightDetectorStateMock(Material.DAYLIGHT_DETECTOR));
	}

	@Test
	void constructor_Material_WrongType_ThrowsException()
	{
		assertThrowsExactly(IllegalArgumentException.class, () -> new DaylightDetectorStateMock(Material.BEDROCK));
	}

	@Test
	void constructor_Block()
	{
		assertDoesNotThrow(() -> new DaylightDetectorStateMock(new BlockMock(Material.DAYLIGHT_DETECTOR)));
	}

	@Test
	void constructor_Block_WrongType_ThrowsException()
	{
		assertThrowsExactly(IllegalArgumentException.class,
				() -> new DaylightDetectorStateMock(new BlockMock(Material.BEDROCK)));
	}

	@Test
	void getSnapshot_DifferentInstance()
	{
		assertNotSame(detector, detector.getSnapshot());
	}

	@Test
	void blockStateMock_Mock_CorrectType()
	{
		assertInstanceOf(DaylightDetectorStateMock.class, BlockStateMock.mockState(block));
	}

}
