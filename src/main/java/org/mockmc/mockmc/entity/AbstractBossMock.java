package org.mockmc.mockmc.entity;

import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Boss;
import org.jetbrains.annotations.NotNull;
import org.mockmc.mockmc.ServerMock;
import org.mockmc.mockmc.boss.BossBarMock;
import java.util.UUID;

abstract class AbstractBossMock extends MonsterMock
		implements
			Boss,
			org.mockmc.mockmc.generated.org.bukkit.entity.BossBaseMock
{

	protected final BossBarMock bossBarMock;

	/**
	 * Constructs a new {@link MonsterMock} on the provided {@link ServerMock} with
	 * a specified {@link UUID}.
	 *
	 * @param server
	 *            The server to create the entity on.
	 * @param uuid
	 *            The UUID of the entity.
	 */
	protected AbstractBossMock(@NotNull ServerMock server, @NotNull UUID uuid, String title)
	{
		super(server, uuid);
		bossBarMock = new BossBarMock(title, BarColor.PURPLE, BarStyle.SOLID);
	}

	@Override
	public BossBar getBossBar()
	{
		return bossBarMock;
	}

	@Override
	public @NotNull net.kyori.adventure.text.event.HoverEvent<net.kyori.adventure.text.event.HoverEvent.ShowEntity> asHoverEvent(
			@NotNull java.util.function.UnaryOperator<net.kyori.adventure.text.event.HoverEvent.ShowEntity> op)
	{
		return org.mockmc.mockmc.generated.org.bukkit.entity.BossBaseMock.super.asHoverEvent(op);
	}
}
