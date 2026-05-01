package org.mockmc.mockmc.block.data;

import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockmc.mockmc.MockMCExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockMCExtension.class)
class BeehiveDataMockTest
{

	private final BeehiveDataMock beehive = new BeehiveDataMock(Material.BEEHIVE);

	@Nested
	class SetHoneyLevel
	{

		@Test
		void shouldHaveDefaultValue()
		{ // L23
			assertEquals(0, beehive.getHoneyLevel());
		}

		@ParameterizedTest
		@ValueSource(ints =
		{0, 1, 2, 3, 4, 5})
		void shouldAcceptPossibleHoneyLevelValues(int expected)
		{ // L28
			beehive.setHoneyLevel(expected);
			assertEquals(expected, beehive.getHoneyLevel());
		}

		@Test
		void shouldHaveDefaultMaximumValue()
		{
			assertEquals(5, beehive.getMaximumHoneyLevel());
		}
	}

	@Nested
	class SetFacing
	{

		@Test
		void shouldHaveDefaultFacingNorth()
		{
			assertEquals(BlockFace.NORTH, beehive.getFacing());
		}

		@ParameterizedTest
		@EnumSource(value = BlockFace.class, mode = EnumSource.Mode.INCLUDE, names =
		{"NORTH", "SOUTH", "EAST", "WEST"})
		void shouldAcceptValidFacingValues(BlockFace face)
		{
			beehive.setFacing(face);
			assertEquals(face, beehive.getFacing());
		}

		@ParameterizedTest
		@EnumSource(value = BlockFace.class, mode = EnumSource.Mode.EXCLUDE, names =
		{"NORTH", "SOUTH", "EAST", "WEST"})
		void shouldThrowExceptionOnInvalidFacingValues(BlockFace face)
		{ // L62
			IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> beehive.setFacing(face));
			assertEquals("Invalid face, only cartesian horizontal face are allowed for this property!", e.getMessage());
		}
	}
}
