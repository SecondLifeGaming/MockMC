package org.mockmc.mockmc.block.data;

import org.bukkit.Material;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockmc.mockmc.MockMCExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockMCExtension.class)
class HangingMossDataMockTest
{

	private final HangingMossDataMock moss = new HangingMossDataMock(Material.PALE_HANGING_MOSS);

	@Nested
	class SetTip
	{

		@Test
		void givenDefaultValue()
		{
			assertTrue(moss.isTip());
		}

		@ParameterizedTest
		@ValueSource(booleans =
		{true, false})
		void givenPossibleValues(boolean isLit)
		{
			moss.setTip(isLit);
			assertEquals(isLit, moss.isTip());
		}

	}

}
