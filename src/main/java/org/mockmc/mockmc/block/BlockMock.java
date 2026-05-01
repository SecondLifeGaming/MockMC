package org.mockmc.mockmc.block;

import com.google.common.base.Preconditions;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Tag;
import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.BlockState;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.Entity;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Unmodifiable;
import org.mockmc.mockmc.block.data.BlockDataMockFactory;
import org.mockmc.mockmc.block.state.BlockStateMock;
import org.mockmc.mockmc.block.state.BlockStateMockFactory;
import org.mockmc.mockmc.metadata.MetadataTable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Mock implementation of a {@link Block}.
 */
@SuppressWarnings(
{"deprecation", "removal", "unchecked"})
public class BlockMock implements Block, org.mockmc.mockmc.generated.server.org.bukkit.block.BlockBaseMock
{

	private final MetadataTable metadataTable = new MetadataTable();

	private final Location location;

	private BlockStateMock state;

	private Material material;

	private byte data;

	private BlockData blockData;

	private byte lightFromSky = 15;

	private byte lightFromBlocks = 0;

	@Unmodifiable
	private List<ItemStack> drops;

	/**
	 * Creates a basic block made of air.
	 */
	public BlockMock()
	{
		this(Material.AIR);
	}

	/**
	 * Creates a basic block made of air at a certain location.
	 *
	 * @param location
	 *            The location of the block.
	 */
	public BlockMock(@NotNull Location location)
	{
		this(Material.AIR, location);
		Preconditions.checkNotNull(location, "Location cannot be null");
	}

	/**
	 * Creates a basic block with a given material.
	 *
	 * @param material
	 *            The material to give the block.
	 */
	public BlockMock(@NotNull Material material)
	{
		this(material, null);
	}

	/**
	 * Creates a basic block with a given material that is also linked to a specific
	 * location.
	 *
	 * @param material
	 *            The material of the block.
	 * @param location
	 *            The location of the block. Can be {@code null} if not needed.
	 */
	public BlockMock(@NotNull Material material, @Nullable Location location)
	{
		Preconditions.checkNotNull(material, "Material cannot be null");
		Preconditions.checkArgument(material.isBlock(), "Material has to be a block");
		this.material = material;
		this.location = location;
		this.state = BlockStateMockFactory.mock(this);
		this.blockData = BlockDataMockFactory.mock(material);
		this.drops = Collections.emptyList();
	}

	@Override
	public void setMetadata(@NotNull String metadataKey, @NotNull MetadataValue newMetadataValue)
	{
		metadataTable.setMetadata(metadataKey, newMetadataValue);
	}

	@Override
	@NotNull
	public List<MetadataValue> getMetadata(String metadataKey)
	{
		return metadataTable.getMetadata(metadataKey);
	}

	@Override
	public boolean hasMetadata(@NotNull String metadataKey)
	{
		return metadataTable.hasMetadata(metadataKey);
	}

	@Override
	public void removeMetadata(@NotNull String metadataKey, @NotNull Plugin owningPlugin)
	{
		metadataTable.removeMetadata(metadataKey, owningPlugin);
	}

	@Override
	@Deprecated(since = "1.6.2")
	public byte getData()
	{
		return data;
	}

	@Override
	@NotNull
	public Block getRelative(int modX, int modY, int modZ)
	{
		int x = location.getBlockX() + modX;
		int y = location.getBlockY() + modY;
		int z = location.getBlockZ() + modZ;
		return location.getWorld().getBlockAt(x, y, z);
	}

	@Override
	@NotNull
	public Block getRelative(@NotNull BlockFace face)
	{
		return getRelative(face, 1);
	}

	@Override
	@NotNull
	public Block getRelative(@NotNull BlockFace face, int distance)
	{
		Preconditions.checkNotNull(face, "Face cannot be null");
		return getRelative(face.getModX() * distance, face.getModY() * distance, face.getModZ() * distance);
	}

	/**
	 * Asserts that the material type of the block is equal to a given type.
	 *
	 * @param material
	 *            The material type that the block should have.
	 * @throws AssertionError
	 *             Thrown if the material type of the block does not equal the given
	 *             material type.
	 */
	@Deprecated(forRemoval = true)
	public void assertType(@NotNull Material material)
	{
		if (this.material != material)
		{
			throw new AssertionError(
					String.format("Block material type is <%s>, but <%s> was expected.", this.material, material));
		}
	}

	@Override
	@NotNull
	public Material getType()
	{
		return material;
	}

	@Override
	public byte getLightLevel()
	{
		return (byte) Math.max(getLightFromSky(), getLightFromBlocks());
	}

	@Override
	public byte getLightFromSky()
	{
		return lightFromSky;
	}

	/**
	 * Sets the light level received from sky.
	 *
	 * @param lightFromSky
	 *            Value between 0 and 15.
	 */
	public void setLightFromSky(byte lightFromSky)
	{
		Preconditions.checkArgument(lightFromSky >= 0 && lightFromSky <= 15, "Light level should be between 0 and 15.");
		this.lightFromSky = lightFromSky;
	}

	@Override
	public byte getLightFromBlocks()
	{
		return lightFromBlocks;
	}

	/**
	 * Sets the light level received from other blocks.
	 *
	 * @param lightFromBlocks
	 *            Value between 0 and 15.
	 */
	public void setLightFromBlocks(byte lightFromBlocks)
	{
		Preconditions.checkArgument(lightFromBlocks >= 0 && lightFromBlocks <= 15,
				"Light level should be between 0 and 15.");
		this.lightFromBlocks = lightFromBlocks;
	}

	@Override
	@NotNull
	public World getWorld()
	{
		return location.getWorld();
	}

	@Override
	public int getX()
	{
		return location.getBlockX();
	}

	@Override
	public int getY()
	{
		return location.getBlockY();
	}

	@Override
	public int getZ()
	{
		return location.getBlockZ();
	}

	@Override
	@NotNull
	public Location getLocation()
	{
		return location;
	}

	@Override
	public Location getLocation(@Nullable Location loc)
	{
		if (loc != null)
		{
			loc.setWorld(this.getWorld());
			loc.setX(location.getX());
			loc.setY(location.getY());
			loc.setZ(location.getZ());
			loc.setYaw(0);
			loc.setPitch(0);
		}
		return loc;
	}

	@Override
	@NotNull
	public Chunk getChunk()
	{
		return location.getWorld().getChunkAt(this);
	}

	@Override
	public void setType(@NotNull Material type)
	{
		Preconditions.checkNotNull(type, "Type cannot be null");
		material = type;
		state = BlockStateMockFactory.mock(this);
		blockData = BlockDataMockFactory.mock(type);
	}

	@Override
	public void setType(@NotNull Material type, boolean applyPhysics)
	{
		setType(type);
	}

	@Override
	public BlockFace getFace(@NotNull Block block)
	{
		Preconditions.checkNotNull(block, "Block cannot be null");
		for (BlockFace face : BlockFace.values())
		{
			if ((this.getX() + face.getModX() == block.getX()) && (this.getY() + face.getModY() == block.getY())
					&& (this.getZ() + face.getModZ() == block.getZ()))
			{
				return face;
			}
		}
		return null;
	}

	@Override
	@NotNull
	public BlockState getState()
	{
		return this.state;
	}

	@Override
	@NotNull
	public Biome getBiome()
	{
		return getWorld().getBiome(getLocation());
	}

	@Override
	@NotNull
	public BlockState getState(boolean useSnapshot)
	{
		return state.getSnapshot();
	}

	@Override
	public void setBiome(@NotNull Biome bio)
	{
		Preconditions.checkNotNull(bio, "Biome cannot be null");
		getWorld().setBiome(getLocation(), bio);
	}

	@Override
	public int getBlockPower()
	{
		return this.getBlockPower(BlockFace.SELF);
	}

	@Override
	public boolean isEmpty()
	{
		return material.isAir();
	}

	@Override
	public boolean isBurnable()
	{
		return material.isBurnable();
	}

	@Override
	public boolean isSolid()
	{
		return material.isSolid();
	}

	@Override
	public boolean isCollidable()
	{
		return material.isCollidable();
	}

	@Override
	public boolean isReplaceable()
	{
		return Tag.REPLACEABLE.isTagged(material);
	}

	@Override
	public boolean isLiquid()
	{
		return material == Material.LAVA || material == Material.WATER || material == Material.BUBBLE_COLUMN;
	}

	@Override
	public boolean isSuffocating()
	{
		return state.isSuffocating();
	}

	@Override
	public boolean breakNaturally()
	{
		return this.breakNaturally(null);
	}

	@Override
	public boolean breakNaturally(@Nullable ItemStack tool)
	{
		ItemStack item = Optional.ofNullable(tool).orElse(ItemStack.empty());
		return this.breakNaturally(item, false);
	}

	@Override
	public boolean breakNaturally(boolean triggerEffect, boolean dropExperience)
	{
		return this.breakNaturally(ItemStack.empty(), triggerEffect, dropExperience);
	}

	@Override
	public boolean breakNaturally(@NotNull ItemStack tool, boolean triggerEffect, boolean dropExperience)
	{
		return this.breakNaturally(tool, triggerEffect, dropExperience, false);
	}

	@Override
	public boolean breakNaturally(@NotNull ItemStack tool, boolean triggerEffect, boolean dropExperience,
			boolean forceEffect)
	{
		if (this.isEmpty())
		{
			return false;
		}
		this.setType(Material.AIR);
		return true;
	}

	@Override
	@NotNull
	public Collection<ItemStack> getDrops()
	{
		return this.getDrops(null);
	}

	@Override
	@NotNull
	public Collection<ItemStack> getDrops(@Nullable ItemStack tool)
	{
		return this.getDrops(tool, null);
	}

	@Override
	@NotNull
	public Collection<ItemStack> getDrops(@Nullable ItemStack tool, @Nullable Entity entity)
	{
		return new ArrayList<>(drops);
	}

	/**
	 * Sets the drops of this block.
	 *
	 * @param items
	 *            the items for this block to drop.
	 */
	public void setDrops(@Nullable Collection<ItemStack> items)
	{
		if (items == null)
		{
			this.drops = Collections.emptyList();
		} else
		{
			this.drops = items.stream().map(ItemStack::clone).toList();
		}
	}

	@Override
	@NotNull
	public BlockData getBlockData()
	{
		return blockData;
	}

	@Override
	public void setBlockData(@NotNull BlockData data)
	{
		this.material = data.getMaterial();
		this.blockData = data.clone();
	}

	@Override
	public void setBlockData(@NotNull BlockData data, boolean applyPhysics)
	{
		this.material = data.getMaterial();
		this.blockData = data.clone();
	}

	/**
	 * This method sets the current {@link BlockState} to the provided
	 * {@link BlockStateMock}. <strong>Do not call this method directly, use
	 * {@link BlockState#update()} instead.</strong>
	 *
	 * @param state
	 *            The {@link BlockState} that should be set.
	 */
	@ApiStatus.Internal
	public void setState(@NotNull BlockStateMock state)
	{
		Preconditions.checkNotNull(state, "The BlockState cannot be null");
		this.state = state;
	}
}
