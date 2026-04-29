package io.papermc.paper.datacomponent.item;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockmc.mockmc.MockMCExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SuppressWarnings("UnstableApiUsage")
@ExtendWith(MockMCExtension.class)
class SwingAnimationMockTest
{

	@Test
	void givenBasicCreation()
	{
		SwingAnimation component = SwingAnimation.swingAnimation().duration(10).type(SwingAnimation.Animation.STAB)
				.build();

		assertEquals(10, component.duration());
		assertEquals(SwingAnimation.Animation.STAB, component.type());
	}

}
