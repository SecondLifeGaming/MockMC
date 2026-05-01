package org.mockmc.mockmc.inventory.meta;

import com.destroystokyo.paper.MaterialTags;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Registry;
import org.bukkit.block.BlockState;
import org.bukkit.block.Container;
import org.bukkit.configuration.serialization.DelegateDeserialization;
import java.util.Objects;
import org.bukkit.block.Banner;
import org.bukkit.block.Sign;
import org.bukkit.block.Structure;
import org.bukkit.block.TileState;
import org.bukkit.block.banner.Pattern;
import org.bukkit.block.sign.Side;
import org.bukkit.block.sign.SignSide;
import org.bukkit.block.structure.Mirror;
import org.bukkit.block.structure.StructureRotation;
import org.bukkit.block.structure.UsageMode;
import org.bukkit.configuration.serialization.ConfigurationSerialization;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BlockStateMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.BlockVector;
import org.jetbrains.annotations.NotNull;
import org.mockmc.mockmc.block.state.BarrelStateMock;
import org.mockmc.mockmc.block.state.BeaconStateMock;
import org.mockmc.mockmc.block.state.BedStateMock;
import org.mockmc.mockmc.block.state.BeehiveStateMock;
import org.mockmc.mockmc.block.state.BellStateMock;
import org.mockmc.mockmc.block.state.BlastFurnaceStateMock;
import org.mockmc.mockmc.block.state.BlockStateMockFactory;
import org.mockmc.mockmc.block.state.BrewingStandStateMock;
import org.mockmc.mockmc.block.state.CalibratedSculkSensorStateMock;
import org.mockmc.mockmc.block.state.CampfireStateMock;
import org.mockmc.mockmc.block.state.ChestStateMock;
import org.mockmc.mockmc.block.state.CommandBlockStateMock;
import org.mockmc.mockmc.block.state.ComparatorStateMock;
import org.mockmc.mockmc.block.state.ContainerStateMock;
import org.mockmc.mockmc.block.state.CreatureSpawnerStateMock;
import org.mockmc.mockmc.block.state.DaylightDetectorStateMock;
import org.mockmc.mockmc.block.state.DispenserStateMock;
import org.mockmc.mockmc.block.state.DropperStateMock;
import org.mockmc.mockmc.block.state.EnchantingTableStateMock;
import org.mockmc.mockmc.block.state.EnderChestStateMock;
import org.mockmc.mockmc.block.state.FurnaceStateMock;
import org.mockmc.mockmc.block.state.HopperStateMock;
import org.mockmc.mockmc.block.state.JigsawStateMock;
import org.mockmc.mockmc.block.state.JukeboxStateMock;
import org.mockmc.mockmc.block.state.LecternStateMock;
import org.mockmc.mockmc.block.state.SculkCatalystStateMock;
import org.mockmc.mockmc.block.state.SculkSensorStateMock;
import org.mockmc.mockmc.block.state.SculkShriekerStateMock;
import org.mockmc.mockmc.block.state.ShulkerBoxStateMock;
import org.mockmc.mockmc.block.state.SignStateMock;
import org.mockmc.mockmc.block.state.SmokerStateMock;
import org.mockmc.mockmc.block.state.StructureStateMock;
import org.mockmc.mockmc.block.state.TestBlockStateMock;
import org.mockmc.mockmc.block.state.TestInstanceBlockStateMock;
import org.mockmc.mockmc.block.state.TileStateMock;
import org.mockmc.mockmc.inventory.SerializableMeta;
import org.mockmc.mockmc.inventory.serializer.SerializationUtils;
import org.mockmc.mockmc.persistence.PersistentDataContainerMock;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Mock implementation of a {@link BlockStateMeta}. Used for everything except
 * {@link ShieldMetaMock} because ShieldMeta is a special case that implements
 * both {@link BlockStateMeta} and {@link org.bukkit.inventory.meta.ShieldMeta}.
 *
 * @see ShieldMetaMock
 * @see ItemMetaMock
 */
@DelegateDeserialization(SerializableMeta.class)
@SuppressWarnings(
{"deprecation", "unchecked"})
public class BlockStateMetaMock extends ItemMetaMock
		implements
			org.mockmc.mockmc.generated.server.org.bukkit.inventory.meta.BlockStateMetaBaseMock
{

	private static final String BLOCK_MATERIAL = "blockMaterial";
	private static final String CONTAINER = "container";
	private static final String PUBLIC_BUCKETS = "publicBuckets";
	private static final String PATTERNS = "patterns";
	private static final String FRONT_OWNER = "front-owner";
	private static final String BACK_OWNER = "back-owner";
	private static final String STRUCTURE_NAME = "structureName";
	private static final String AUTHOR = "author";
	private static final String POS_X = "posX";
	private static final String POS_Y = "posY";
	private static final String POS_Z = "posZ";
	private static final String SIZE_X = "sizeX";
	private static final String SIZE_Y = "sizeY";
	private static final String SIZE_Z = "sizeZ";
	private static final String MIRROR = "mirror";
	private static final String ROTATION = "rotation";
	private static final String USAGE_MODE = "usageMode";
	private static final String IGNORE_ENTITIES = "ignoreEntities";
	private static final String SHOW_AIR = "showAir";
	private static final String SHOW_BOUNDING_BOX = "showBoundingBox";
	private static final String INTEGRITY = "integrity";
	private static final String SEED = "seed";
	private static final String METADATA = "metadata";
	private static final String COLOR = "color";
	private static final String LINES = "lines";
	private static final String GLOWING = "glowing";

	/**
	 * Contains the set of materials that are appropriate for BlockStateMeta
	 * ItemMeta type. Using EnumMap for O(1) lookup performance using an internal
	 * array.
	 */
	static final Map<Material, Class<? extends TileStateMock>> BLOCK_STATE_MATERIALS;

	static
	{
		// To update this list check Paper class "CraftItemMetas"
		Map<Material, Class<? extends TileStateMock>> map = new java.util.EnumMap<>(Material.class);
		MaterialTags.BEDS.getValues().forEach(m -> map.put(m, BedStateMock.class));
		MaterialTags.SHULKER_BOXES.getValues().forEach(m -> map.put(m, ShulkerBoxStateMock.class));
		MaterialTags.SIGNS.getValues().forEach(m -> map.put(m, SignStateMock.class));
		map.put(Material.BARREL, BarrelStateMock.class);
		map.put(Material.BEACON, BeaconStateMock.class);
		map.put(Material.BEE_NEST, BeehiveStateMock.class);
		map.put(Material.BEEHIVE, BeehiveStateMock.class);
		map.put(Material.BELL, BellStateMock.class);
		map.put(Material.BLAST_FURNACE, BlastFurnaceStateMock.class);
		map.put(Material.BREWING_STAND, BrewingStandStateMock.class);
		map.put(Material.CALIBRATED_SCULK_SENSOR, CalibratedSculkSensorStateMock.class);
		map.put(Material.CAMPFIRE, CampfireStateMock.class);
		map.put(Material.CHEST, ChestStateMock.class);
		map.put(Material.CHISELED_BOOKSHELF, null);
		map.put(Material.COMMAND_BLOCK, CommandBlockStateMock.class);
		map.put(Material.CREAKING_HEART, null);
		map.put(Material.CHAIN_COMMAND_BLOCK, CommandBlockStateMock.class);
		map.put(Material.REPEATING_COMMAND_BLOCK, CommandBlockStateMock.class);
		map.put(Material.COMPARATOR, ComparatorStateMock.class);
		map.put(Material.CONDUIT, null);
		map.put(Material.CRAFTER, null);
		map.put(Material.DAYLIGHT_DETECTOR, DaylightDetectorStateMock.class);
		map.put(Material.DECORATED_POT, null);
		map.put(Material.DISPENSER, DispenserStateMock.class);
		map.put(Material.DROPPER, DropperStateMock.class);
		map.put(Material.ENCHANTING_TABLE, EnchantingTableStateMock.class);
		map.put(Material.ENDER_CHEST, EnderChestStateMock.class);
		map.put(Material.FURNACE, FurnaceStateMock.class);
		map.put(Material.HOPPER, HopperStateMock.class);
		map.put(Material.JIGSAW, JigsawStateMock.class);
		map.put(Material.JUKEBOX, JukeboxStateMock.class);
		map.put(Material.LECTERN, LecternStateMock.class);
		map.put(Material.SCULK_CATALYST, SculkCatalystStateMock.class);
		map.put(Material.SCULK_SENSOR, SculkSensorStateMock.class);
		map.put(Material.SCULK_SHRIEKER, SculkShriekerStateMock.class);
		map.put(Material.SMOKER, SmokerStateMock.class);
		map.put(Material.SOUL_CAMPFIRE, CampfireStateMock.class);
		map.put(Material.SPAWNER, CreatureSpawnerStateMock.class);
		map.put(Material.STRUCTURE_BLOCK, StructureStateMock.class);
		map.put(Material.SUSPICIOUS_GRAVEL, null);
		map.put(Material.SUSPICIOUS_SAND, null);
		map.put(Material.TEST_BLOCK, TestBlockStateMock.class);
		map.put(Material.TEST_INSTANCE_BLOCK, TestInstanceBlockStateMock.class);
		map.put(Material.TRAPPED_CHEST, ChestStateMock.class);
		map.put(Material.TRIAL_SPAWNER, null);
		map.put(Material.VAULT, null);
		BLOCK_STATE_MATERIALS = Collections.unmodifiableMap(map);
	}

	private BlockState blockState;

	protected Material material;

	private BlockStateMetaMock()
	{
	}

	/**
	 * Constructs a new {@link BlockStateMetaMock}, for the given material.
	 *
	 * @param material
	 *            indicates which type of {@link BlockState} to hold.
	 */
	public BlockStateMetaMock(Material material)
	{
		this.material = material;
	}

	/**
	 * Constructs a new {@link BlockStateMetaMock}, cloning the data from another.
	 *
	 * @param meta
	 *            The meta to clone.
	 */
	public BlockStateMetaMock(@NotNull ItemMeta meta)
	{
		super(meta);
		if (meta instanceof BlockStateMeta state)
		{
			if (state.hasBlockState())
			{
				this.blockState = state.getBlockState();
				this.material = this.blockState.getType();
			} else if (meta instanceof BlockStateMetaMock state2)
			{
				this.material = state2.material;
			}
		}
	}

	@Override
	public boolean hasBlockState()
	{
		return blockState != null;
	}

	@Override
	public void clearBlockState()
	{
		blockState = null;
	}

	@Override
	@NotNull
	public BlockState getBlockState()
	{
		if (blockState != null)
		{
			return blockState.copy();
		}
		return BlockStateMockFactory.mock(material);
	}

	@Override
	public void setBlockState(@NotNull BlockState blockState)
	{
		this.blockState = blockState;
		if (this.material == null)
		{
			this.material = blockState.getType();
		}
	}

	@Override
	protected String getTypeName()
	{
		return "TILE_ENTITY";
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
		{
			return true;
		}
		if (obj == null || obj.getClass() != this.getClass())
		{
			return false;
		}
		BlockStateMetaMock that = (BlockStateMetaMock) obj;
		if (!super.equals(obj))
		{
			return false;
		}
		return Objects.equals(blockState, that.blockState) && material == that.material;
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(super.hashCode(), blockState, material);
	}

	@Override
	@SuppressWarnings(
	{"MethodDoesntCallSuperMethod", "java:S2975", "java:S1182"})
	@NotNull
	public BlockStateMetaMock clone()
	{
		return new BlockStateMetaMock(this);
	}

	@Override
	@NotNull
	public Map<String, Object> serialize()
	{
		Map<String, Object> serialized = super.serialize();
		if (material != null)
		{
			serialized.put(BLOCK_MATERIAL, material.name());
		}
		if (blockState instanceof Container container)
		{
			ItemStack[] contents = container.getSnapshotInventory().getContents();
			List<Map<String, Object>> containerData = new ArrayList<>(contents.length);
			for (int i = 0; i < contents.length; i++)
			{
				ItemStack item = contents[i];
				if (item != null && item.getType() != Material.AIR)
				{
					containerData.add(Map.of("slot", i, "item", item.serialize()));
				}
			}
			serialized.put(CONTAINER, containerData);
		}
		if (blockState instanceof TileState tileState)
		{
			PersistentDataContainerMock pdc = (PersistentDataContainerMock) tileState.getPersistentDataContainer();
			if (!pdc.isEmpty())
			{
				serialized.put(PUBLIC_BUCKETS, pdc.serialize());
			}
		}
		if (blockState instanceof Banner banner)
		{
			serialized.put(PATTERNS, SerializationUtils.serialize(banner.getPatterns()));
		}
		if (blockState instanceof Sign sign)
		{
			serialized.put(FRONT_OWNER, serializeSignSide(sign.getSide(Side.FRONT)));
			serialized.put(BACK_OWNER, serializeSignSide(sign.getSide(Side.BACK)));
		}
		if (blockState instanceof Structure structure)
		{
			serialized.put(STRUCTURE_NAME, structure.getStructureName());
			serialized.put(AUTHOR, structure.getAuthor());
			serialized.put(POS_X, structure.getRelativePosition().getBlockX());
			serialized.put(POS_Y, structure.getRelativePosition().getBlockY());
			serialized.put(POS_Z, structure.getRelativePosition().getBlockZ());
			serialized.put(SIZE_X, structure.getStructureSize().getBlockX());
			serialized.put(SIZE_Y, structure.getStructureSize().getBlockY());
			serialized.put(SIZE_Z, structure.getStructureSize().getBlockZ());
			serialized.put(MIRROR, structure.getMirror().name());
			serialized.put(ROTATION, structure.getRotation().name());
			serialized.put(USAGE_MODE, structure.getUsageMode().name());
			serialized.put(IGNORE_ENTITIES, structure.isIgnoreEntities());
			serialized.put(SHOW_AIR, structure.isShowAir());
			serialized.put(SHOW_BOUNDING_BOX, structure.isBoundingBoxVisible());
			serialized.put(INTEGRITY, structure.getIntegrity());
			serialized.put(SEED, structure.getSeed());
			serialized.put(METADATA, structure.getMetadata());
		}
		return serialized;
	}

	private Map<String, Object> serializeSignSide(SignSide side)
	{
		return Map.of(LINES, List.of(side.getLines()), GLOWING, side.isGlowingText(), COLOR, side.getColor().name());
	}

	@Override
	protected void deserializeInternal(@NotNull Map<String, Object> args)
	{
		clearBlockState();
		super.deserializeInternal(args);
		if (args.containsKey(BLOCK_MATERIAL))
		{
			material = Registry.MATERIAL
					.get(NamespacedKey.fromString(((String) args.get(BLOCK_MATERIAL)).toLowerCase(Locale.ENGLISH)));
		}
		if (material != null && (args.containsKey(CONTAINER) || args.containsKey(PUBLIC_BUCKETS)
				|| args.containsKey(PATTERNS) || args.containsKey(FRONT_OWNER) || args.containsKey(BACK_OWNER)
				|| args.containsKey(STRUCTURE_NAME)))
		{
			blockState = getBlockState();
		}

		deserializeContainer(args);
		deserializeTileState(args);
		deserializeBanner(args);
		deserializeSign(args);
		deserializeStructure(args);
	}

	private void deserializeContainer(@NotNull Map<String, Object> args)
	{
		if (args.containsKey(CONTAINER) && blockState instanceof ContainerStateMock container)
		{
			Inventory inventory = container.getSnapshotInventory();
			List<Map<String, Object>> containerData = (List<Map<String, Object>>) args.get(CONTAINER);
			for (Map<String, Object> slotData : containerData)
			{
				int slot = (int) slotData.getOrDefault("slot", -1);
				if (slot >= 0)
				{
					inventory.setItem(slot,
							Bukkit.getUnsafe().deserializeStack((Map<String, Object>) slotData.get("item")));
				}
			}
		}
	}

	private void deserializeTileState(@NotNull Map<String, Object> args)
	{
		if (args.containsKey(PUBLIC_BUCKETS) && blockState instanceof TileState tileState)
		{
			PersistentDataContainerMock pdc = (PersistentDataContainerMock) tileState.getPersistentDataContainer();
			PersistentDataContainerMock deserializedPdc = PersistentDataContainerMock
					.deserialize((Map<String, Object>) args.get(PUBLIC_BUCKETS));
			deserializedPdc.copyTo(pdc, true);
		}
	}

	private void deserializeBanner(@NotNull Map<String, Object> args)
	{
		if (args.containsKey(PATTERNS) && blockState instanceof Banner banner)
		{
			List<Map<String, Object>> patternsData = (List<Map<String, Object>>) args.get(PATTERNS);
			List<Pattern> patterns = new ArrayList<>();
			for (Map<String, Object> patternData : patternsData)
			{
				patterns.add((Pattern) ConfigurationSerialization.deserializeObject(patternData));
			}
			banner.setPatterns(patterns);
		}
	}

	private void deserializeSign(@NotNull Map<String, Object> args)
	{
		if (blockState instanceof Sign sign)
		{
			if (args.containsKey(FRONT_OWNER))
			{
				deserializeSignSide(sign.getSide(Side.FRONT), (Map<String, Object>) args.get(FRONT_OWNER));
			}
			if (args.containsKey(BACK_OWNER))
			{
				deserializeSignSide(sign.getSide(Side.BACK), (Map<String, Object>) args.get(BACK_OWNER));
			}
		}
	}

	private void deserializeStructure(@NotNull Map<String, Object> args)
	{
		if (blockState instanceof Structure structure)
		{
			deserializeStructureIdentity(structure, args);
			deserializeStructurePosition(structure, args);
			deserializeStructureConfiguration(structure, args);
		}
	}

	private void deserializeStructureIdentity(Structure structure, Map<String, Object> args)
	{
		if (args.containsKey(STRUCTURE_NAME))
		{
			structure.setStructureName((String) args.get(STRUCTURE_NAME));
		}
		if (args.containsKey(AUTHOR))
		{
			structure.setAuthor((String) args.get(AUTHOR));
		}
	}

	private void deserializeStructurePosition(Structure structure, Map<String, Object> args)
	{
		if (args.containsKey(POS_X) && args.containsKey(POS_Y) && args.containsKey(POS_Z))
		{
			structure.setRelativePosition(new BlockVector(((Number) args.get(POS_X)).intValue(),
					((Number) args.get(POS_Y)).intValue(), ((Number) args.get(POS_Z)).intValue()));
		}
		if (args.containsKey(SIZE_X) && args.containsKey(SIZE_Y) && args.containsKey(SIZE_Z))
		{
			structure.setStructureSize(new BlockVector(((Number) args.get(SIZE_X)).intValue(),
					((Number) args.get(SIZE_Y)).intValue(), ((Number) args.get(SIZE_Z)).intValue()));
		}
	}

	private void deserializeStructureConfiguration(Structure structure, Map<String, Object> args)
	{
		if (args.containsKey(MIRROR))
		{
			structure.setMirror(Mirror.valueOf((String) args.get(MIRROR)));
		}
		if (args.containsKey(ROTATION))
		{
			structure.setRotation(StructureRotation.valueOf((String) args.get(ROTATION)));
		}
		if (args.containsKey(USAGE_MODE))
		{
			structure.setUsageMode(UsageMode.valueOf((String) args.get(USAGE_MODE)));
		}
		if (args.containsKey(IGNORE_ENTITIES))
		{
			structure.setIgnoreEntities((boolean) args.get(IGNORE_ENTITIES));
		}
		if (args.containsKey(SHOW_AIR))
		{
			structure.setShowAir((boolean) args.get(SHOW_AIR));
		}
		if (args.containsKey(SHOW_BOUNDING_BOX))
		{
			structure.setBoundingBoxVisible((boolean) args.get(SHOW_BOUNDING_BOX));
		}
		if (args.containsKey(INTEGRITY))
		{
			structure.setIntegrity(((Number) args.get(INTEGRITY)).floatValue());
		}
		if (args.containsKey(SEED))
		{
			structure.setSeed(((Number) args.get(SEED)).longValue());
		}
		if (args.containsKey(METADATA))
		{
			structure.setMetadata((String) args.get(METADATA));
		}
	}

	private void deserializeSignSide(SignSide side, Map<String, Object> data)
	{
		List<String> lines = (List<String>) data.get(LINES);
		for (int i = 0; i < lines.size(); i++)
		{
			side.setLine(i, lines.get(i));
		}
		side.setGlowingText((boolean) data.getOrDefault(GLOWING, false));
		if (data.containsKey(COLOR))
		{
			side.setColor(org.bukkit.DyeColor.valueOf((String) data.get(COLOR)));
		}
	}

	@NotNull
	public static BlockStateMetaMock deserialize(@NotNull Map<String, Object> args)
	{
		BlockStateMetaMock mock = new BlockStateMetaMock();
		mock.deserializeInternal(args);
		return mock;
	}
}
