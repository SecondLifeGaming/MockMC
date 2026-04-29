package org.mockmc.mockmc.block.data;

import org.bukkit.Material;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockmc.mockmc.MockMCExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@ExtendWith(MockMCExtension.class)
class PowerableDataMockTest
{
	private PowerableDataMock powerable;

	@BeforeEach
	void setUp()
	{
		this.powerable = new PowerableDataMock(Material.OAK_FENCE_GATE);
	}

	@Nested
	class SetPowered
	{

		@Test
		void givenDefaultValue()
		{
			assertFalse(powerable.isPowered());
		}

		@ParameterizedTest
		@ValueSource(booleans =
		{true, false})
		void givenPossibleValues(boolean inWall)
		{
			powerable.setPowered(inWall);
			assertEquals(inWall, powerable.isPowered());
		}

	}

}
