package org.mockmc.mockmc.block.state;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Vault;
import org.jetbrains.annotations.NotNull;

import org.bukkit.inventory.ItemStack;
import org.bukkit.loot.LootTable;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * Mock implementation of a {@link Vault}.
 *
 * @see TileStateMock
 * @mockmc.version 1.21-1.0.0
 */
public class VaultStateMock extends TileStateMock
		implements
			Vault,
			org.mockmc.mockmc.generated.server.org.bukkit.block.VaultBaseMock
{

	private final Set<UUID> rewardedPlayers = new HashSet<>();
	private final Set<UUID> connectedPlayers = new HashSet<>();
	private LootTable lootTable;
	private LootTable displayedLootTable;
	private ItemStack keyItem = new ItemStack(Material.AIR);
	private ItemStack displayedItem = new ItemStack(Material.AIR);
	private double activationRange = 4.0;
	private double deactivationRange = 4.5;
	private long nextStateUpdateTime = 0;

	protected VaultStateMock(@NotNull Block block)
	{
		super(block);
	}

	public VaultStateMock(@NotNull Material material)
	{
		super(material);
	}

	protected VaultStateMock(@NotNull VaultStateMock state)
	{
		super(state);
		this.rewardedPlayers.addAll(state.rewardedPlayers);
		this.connectedPlayers.addAll(state.connectedPlayers);
		this.lootTable = state.lootTable;
		this.displayedLootTable = state.displayedLootTable;
		this.keyItem = state.keyItem.clone();
		this.displayedItem = state.displayedItem.clone();
		this.activationRange = state.activationRange;
		this.deactivationRange = state.deactivationRange;
		this.nextStateUpdateTime = state.nextStateUpdateTime;
	}

	@Override
	public boolean hasRewardedPlayer(@NotNull UUID uuid)
	{
		return this.rewardedPlayers.contains(uuid);
	}

	@Override
	public boolean addRewardedPlayer(@NotNull UUID uuid)
	{
		return this.rewardedPlayers.add(uuid);
	}

	@Override
	public boolean removeRewardedPlayer(@NotNull UUID uuid)
	{
		return this.rewardedPlayers.remove(uuid);
	}

	@Override
	@NotNull
	public Collection<UUID> getRewardedPlayers()
	{
		return Collections.unmodifiableCollection(rewardedPlayers);
	}

	@Override
	public boolean hasConnectedPlayer(@NotNull UUID uuid)
	{
		return this.connectedPlayers.contains(uuid);
	}

	@Override
	@NotNull
	public Set<UUID> getConnectedPlayers()
	{
		return Collections.unmodifiableSet(connectedPlayers);
	}

	@Override
	public LootTable getLootTable()
	{
		return this.lootTable;
	}

	@Override
	public void setLootTable(LootTable lootTable)
	{
		this.lootTable = lootTable;
	}

	@Override
	public LootTable getDisplayedLootTable()
	{
		return this.displayedLootTable;
	}

	@Override
	public void setDisplayedLootTable(LootTable lootTable)
	{
		this.displayedLootTable = lootTable;
	}

	@Override
	@NotNull
	public ItemStack getKeyItem()
	{
		return this.keyItem;
	}

	@Override
	public void setKeyItem(@NotNull ItemStack itemStack)
	{
		this.keyItem = itemStack;
	}

	@Override
	@NotNull
	public ItemStack getDisplayedItem()
	{
		return this.displayedItem;
	}

	@Override
	public void setDisplayedItem(@NotNull ItemStack itemStack)
	{
		this.displayedItem = itemStack;
	}

	@Override
	public double getActivationRange()
	{
		return this.activationRange;
	}

	@Override
	public void setActivationRange(double v)
	{
		this.activationRange = v;
	}

	@Override
	public double getDeactivationRange()
	{
		return this.deactivationRange;
	}

	@Override
	public void setDeactivationRange(double v)
	{
		this.deactivationRange = v;
	}

	@Override
	public long getNextStateUpdateTime()
	{
		return this.nextStateUpdateTime;
	}

	@Override
	public void setNextStateUpdateTime(long l)
	{
		this.nextStateUpdateTime = l;
	}

	@Override
	@NotNull
	public VaultStateMock getSnapshot()
	{
		return new VaultStateMock(this);
	}

	@Override
	@NotNull
	public VaultStateMock copy()
	{
		return new VaultStateMock(this);
	}
}
