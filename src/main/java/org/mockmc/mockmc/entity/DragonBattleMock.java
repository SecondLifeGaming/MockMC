package org.mockmc.mockmc.entity;

import org.bukkit.Location;
import org.bukkit.boss.BossBar;
import org.bukkit.boss.DragonBattle;
import org.bukkit.entity.EnderCrystal;
import org.bukkit.entity.EnderDragon;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Unmodifiable;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class DragonBattleMock implements DragonBattle, org.mockmc.mockmc.generated.org.bukkit.boss.DragonBattleBaseMock
{

	public static final int GATEWAY_COUNT = 20;

	// looks like this is used as some kind of for cycle to store/spawn portals
	public int gateways = 20;

	private final EnderDragonMock enderDragonMock;

	// this is the exit portal, default not spawned
	private Location portalLocation = null;

	private boolean previouslyKilled;

	private List<EnderCrystal> respawnCrystals;

	private DragonBattle.RespawnPhase respawnPhase;

	public DragonBattleMock(EnderDragonMock enderDragonMock)
	{
		this.enderDragonMock = enderDragonMock;
		// normally obtained through save data, assume false
		previouslyKilled = false;
		// assume it is spawning just now
		respawnPhase = RespawnPhase.START;
		// assume naturally spawned so no respawn crystals
		respawnCrystals = List.of();
	}

	@Override
	@Nullable
	public EnderDragon getEnderDragon()
	{
		return enderDragonMock;
	}

	@Override
	@NotNull
	public BossBar getBossBar()
	{
		return Objects.requireNonNull(enderDragonMock.getBossBar());
	}

	@Override
	@Nullable
	public Location getEndPortalLocation()
	{
		return portalLocation;
	}

	@Override
	public boolean hasBeenPreviouslyKilled()
	{
		return previouslyKilled;
	}

	@Override
	public void setPreviouslyKilled(boolean previouslyKilled)
	{
		this.previouslyKilled = previouslyKilled;
	}

	@Override
	public void initiateRespawn()
	{
		respawnPhase = RespawnPhase.START;
	}

	@Override
	public boolean initiateRespawn(@Nullable Collection<EnderCrystal> enderCrystalCollection)
	{
		if (this.hasBeenPreviouslyKilled() && this.getRespawnPhase() == RespawnPhase.NONE)
		{
			if (portalLocation == null)
			{
				portalLocation = new Location(enderDragonMock.getWorld(), 0, 0, 0);
			}
			if (enderCrystalCollection != null)
			{
				respawnCrystals = enderCrystalCollection.stream().filter(Objects::nonNull)
						.filter(it -> it.getWorld().equals(enderDragonMock.getWorld())).toList();
			} else
			{
				respawnCrystals = List.of();
			}
			respawnPhase = RespawnPhase.START;
			return true;
		}
		return false;
	}

	@NotNull
	@Override
	public DragonBattle.RespawnPhase getRespawnPhase()
	{
		return respawnPhase;
	}

	@Override
	public boolean setRespawnPhase(@NotNull DragonBattle.RespawnPhase respawnPhase)
	{
		this.respawnPhase = respawnPhase;
		return true;
	}

	@Override
	public void resetCrystals()
	{
		respawnCrystals = List.of();
	}

	@Override
	public int getGatewayCount()
	{
		return GATEWAY_COUNT - this.gateways;
	}

	@Override
	public boolean spawnNewGateway()
	{
		if (this.gateways <= 0)
		{
			return false;
		}
		gateways--;
		return true;
	}

	@Override
	@NotNull
	@Unmodifiable
	public List<EnderCrystal> getRespawnCrystals()
	{
		return Collections.unmodifiableList(respawnCrystals);
	}

}
