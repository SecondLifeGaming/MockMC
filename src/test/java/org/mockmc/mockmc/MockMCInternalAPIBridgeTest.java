package org.mockmc.mockmc;

import net.kyori.adventure.text.Component;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MockMCInternalAPIBridgeTest
{

	private final MockMCInternalAPIBridge bridge = new MockMCInternalAPIBridge();

	@Test
	void defaultMannequinDescription()
	{
		Component expected = Component.translatable("entity.minecraft.mannequin.label");

		Component description = bridge.defaultMannequinDescription();
		assertEquals(expected, description);
	}

}
