package org.mockmc.mockmc.block.data.deserializer;

import org.bukkit.Instrument;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockmc.mockmc.MockMCExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockMCExtension.class)
class InstrumentDeserializerTest
{

	@ParameterizedTest
	@CsvSource(value =
	{"harp, PIANO", "basedrum, BASS_DRUM", "snare, SNARE_DRUM", "hat, STICKS", "bass, BASS_GUITAR", "flute, FLUTE",
			"bell, BELL", "guitar, GUITAR", "chime, CHIME", "xylophone, XYLOPHONE", "iron_xylophone, IRON_XYLOPHONE",
			"cow_bell, COW_BELL", "didgeridoo, DIDGERIDOO", "bit, BIT", "banjo, BANJO", "pling, PLING",
			"zombie, ZOMBIE", "skeleton, SKELETON", "creeper, CREEPER", "ender_dragon, DRAGON",
			"wither_skeleton, WITHER_SKELETON", "piglin, PIGLIN", "null, CUSTOM_HEAD",}, nullValues = "null")
	void givenValidInstruments(String name, Instrument expectedInstrument)
	{
		var actual = InstrumentDeserializer.INSTANCE.apply(name);
		assertEquals(expectedInstrument, actual);
	}

}
