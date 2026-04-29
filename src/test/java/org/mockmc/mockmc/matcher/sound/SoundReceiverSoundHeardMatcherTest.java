package org.mockmc.mockmc.matcher.sound;

import net.kyori.adventure.key.Key;
import net.kyori.adventure.sound.Sound;
import org.bukkit.Location;
import org.hamcrest.Matcher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockmc.mockmc.MockMCExtension;
import org.mockmc.mockmc.MockMCInject;
import org.mockmc.mockmc.ServerMock;
import org.mockmc.mockmc.sound.AudioExperience;
import org.mockmc.mockmc.sound.SoundReceiver;
import org.mockmc.testutils.matcher.AbstractMatcherTest;

@ExtendWith(MockMCExtension.class)
class SoundReceiverSoundHeardMatcherTest extends AbstractMatcherTest
{

	@MockMCInject
	private ServerMock serverMock;
	@MockMCInject
	private SoundReceiver soundReceiver;
	@MockMCInject
	private Location location1;
	private Sound sound1;
	private Sound sound2;

	@BeforeEach
	void setUp()
	{
		this.sound1 = Sound.sound().type(Key.key("music_disc.13")).source(Sound.Source.MUSIC).build();
		this.sound2 = Sound.sound().type(Key.key("music_disc.12")).source(Sound.Source.MUSIC).build();
	}

	@Test
	void hasHeard_matches()
	{
		soundReceiver.addHeardSound(new AudioExperience(sound1, location1));
		assertMatches(SoundReceiverSoundHeardMatcher.hasHeard(sound1, ignored -> true), soundReceiver);
	}

	@Test
	void hasHeard_filterMismatch()
	{
		soundReceiver.addHeardSound(new AudioExperience(sound1, location1));
		assertDoesNotMatch(SoundReceiverSoundHeardMatcher.hasHeard(sound1, ignored -> false), soundReceiver);
	}

	@Test
	void hasHeard_differentSound()
	{
		soundReceiver.addHeardSound(new AudioExperience(sound1, location1));
		assertDoesNotMatch(SoundReceiverSoundHeardMatcher.hasHeard(sound2, ignored -> true), soundReceiver);
	}

	@Test
	void hasHeard_noSound()
	{
		assertDoesNotMatch(SoundReceiverSoundHeardMatcher.hasHeard(sound1, ignored -> true), soundReceiver);
	}

	@Test
	void nullSafe()
	{
		testIsNullSafe();
	}

	@Override
	protected Matcher<?> createMatcher()
	{
		return SoundReceiverSoundHeardMatcher.hasHeard(sound1, ignored -> true);
	}

}
