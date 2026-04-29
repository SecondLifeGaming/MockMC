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
class BubbleColumnDataMockTest
{

	private final BubbleColumnDataMock bubbleColumn = new BubbleColumnDataMock(Material.BUBBLE_COLUMN);

	@Nested
	class SetDrag
	{

		@Test
		void givenDefaultValue()
		{
			assertTrue(bubbleColumn.isDrag());
		}

		@ParameterizedTest
		@ValueSource(booleans =
		{true, false})
		void givenPossibleValues(boolean inWall)
		{
			bubbleColumn.setDrag(inWall);
			assertEquals(inWall, bubbleColumn.isDrag());
		}

	}

}
