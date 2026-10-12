package org.mockmc.mockmc.damage;

import org.bukkit.Sound;
import org.jetbrains.annotations.NotNull;

public class DamageEffectMock implements org.mockmc.mockmc.generated.server.org.bukkit.damage.DamageEffectBaseMock
{

	private final Sound sound;

	public DamageEffectMock(Sound sound)
	{
		this.sound = sound;
	}

	@Override
	@NotNull
	public Sound getSound()
	{
		return sound;
	}
}
