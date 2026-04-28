package org.mockbukkit.mockbukkit.entity;

import com.destroystokyo.paper.Title;
import com.destroystokyo.paper.profile.PlayerProfile;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableSet;
import io.papermc.paper.chat.ChatRenderer;
import io.papermc.paper.entity.TeleportFlag;
import io.papermc.paper.event.player.AsyncChatEvent;
import io.papermc.paper.math.Position;
import net.kyori.adventure.audience.MessageType;
import net.kyori.adventure.bossbar.BossBar;
import net.kyori.adventure.bossbar.BossBarImplementation;
import net.kyori.adventure.chat.SignedMessage;
import net.kyori.adventure.identity.Identity;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.ComponentLike;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import net.kyori.adventure.text.serializer.bungeecord.BungeeComponentSerializer;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import net.kyori.adventure.util.TriState;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.BaseComponent;
import org.bukkit.BanEntry;
import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.DyeColor;
import org.bukkit.Effect;
import org.bukkit.GameMode;
import org.bukkit.GameRule;
import org.bukkit.Instrument;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Note;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.Statistic;
import org.bukkit.Tag;
import org.bukkit.WeatherType;
import org.bukkit.World;
import org.bukkit.attribute.Attribute;
import org.bukkit.ban.IpBanList;
import org.bukkit.ban.ProfileBanList;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.TileState;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.sign.Side;
import org.bukkit.conversations.Conversation;
import org.bukkit.conversations.ConversationAbandonedEvent;
import org.bukkit.conversations.ManuallyAbandonedConversationCanceller;
import org.bukkit.damage.DamageSource;
import org.bukkit.damage.DamageType;
import org.bukkit.entity.EnderPearl;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerExpCooldownChangeEvent;
import org.bukkit.event.player.PlayerGameModeChangeEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerLevelChangeEvent;
import org.bukkit.event.player.PlayerLocaleChangeEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.event.player.PlayerToggleSprintEvent;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.map.MapView;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.messaging.StandardMessenger;
import org.bukkit.scoreboard.Scoreboard;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.UnmodifiableView;
import org.jspecify.annotations.NullMarked;
import org.mockbukkit.mockbukkit.AsyncCatcher;
import org.mockbukkit.mockbukkit.MockBukkit;
import org.mockbukkit.mockbukkit.PlayerListMock;
import org.mockbukkit.mockbukkit.ServerMock;
import org.mockbukkit.mockbukkit.boss.BossBarImplementationMock;
import org.mockbukkit.mockbukkit.conversations.ConversationTracker;
import org.mockbukkit.mockbukkit.entity.data.EntityState;
import org.mockbukkit.mockbukkit.map.MapViewMock;
import org.mockbukkit.mockbukkit.simulate.entity.PlayerSimulation;
import org.mockbukkit.mockbukkit.sound.AudioExperience;
import org.mockbukkit.mockbukkit.sound.SoundReceiver;
import org.mockbukkit.mockbukkit.statistic.StatisticsMock;
import org.mockbukkit.mockbukkit.world.WorldMock;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Queue;
import java.util.Random;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Predicate;

/**
 * Mock implementation of a {@link Player}.
 *
 * @see HumanEntityMock
 */
@SuppressWarnings(
{"deprecation", "removal", "unchecked", "java:S110"})
public class PlayerMock extends HumanEntityMock
		implements
			SoundReceiver,
			org.mockbukkit.mockbukkit.generated.org.bukkit.entity.PlayerBaseMock
{

	private static final Component DEFAULT_KICK_COMPONENT = Component.text("You are not whitelisted on this server!");
	private static final String LOCATION_CANNOT_BE_NULL = "Location cannot be null";
	private static final String MESSAGE_CANNOT_BE_NULL = "Message cannot be null";
	private static final String MESSAGE_CANNOT_BE_NULL_LOWER = "message cannot be null";
	private static final String PLUGIN_CANNOT_BE_NULL = "Plugin cannot be null";
	private static final String SOUND_CANNOT_BE_NULL = "Sound cannot be null";
	private static final String CATEGORY_CANNOT_BE_NULL = "Category cannot be null";
	private static final String ENTITY_CANNOT_BE_NULL = "Entity cannot be null";
	private static final String PLAYER_CANNOT_BE_NULL = "Player cannot be null";
	private static final String HEADER_CANNOT_BE_NULL = "header cannot be null";
	private static final String FOOTER_CANNOT_BE_NULL = "footer cannot be null";

	@NotNull
	private GameMode previousGamemode = super.getGameMode();

	@Nullable
	private WeatherType playerWeather = null;

	@Nullable
	private Entity spectatorTarget = null;

	@NotNull
	private TriState flyingFallDamage = TriState.NOT_SET;

	private boolean online;

	@Nullable
	private Component displayName = null;

	@Nullable
	private Component playerListName = null;

	@Nullable
	private Component playerListHeader = null;

	@Nullable
	private Component playerListFooter = null;

	private int expTotal = 0;

	private float exp = 0;

	private float flySpeed = 0.1F;

	private int expCooldown = 0;

	private int deathScreenScore = 0;

	private int playerListOrder = 0;

	private boolean sprinting = false;

	private boolean allowFlight = false;

	private boolean flying = false;

	private boolean scaledHealth = false;

	private boolean allowServerListings = true;

	private boolean sleepingIgnored = false;

	private boolean seenWinScreen = false;

	private boolean relativeTime = true;

	private boolean affectsSpawning = true;

	private long timeOffset = 0;

	private double healthScale = 20;

	private Location compassTarget;

	@Nullable
	private Location respawnLocation;

	@Nullable
	private InetSocketAddress address;

	@Nullable
	private InetSocketAddress haProxyAddress;

	private final PlayerSpigotMock playerSpigotMock = new PlayerSpigotMock();

	private final List<AudioExperience> heardSounds = new LinkedList<>();

	private final Map<UUID, Set<Plugin>> hiddenEntities = new HashMap<>();

	private final Set<UUID> hiddenPlayersDeprecated = new HashSet<>();

	private final ConversationTracker conversationTracker = new ConversationTracker();

	private final Queue<Component> messages = new LinkedTransferQueue<>();

	private final Queue<Component> titles = new LinkedTransferQueue<>();

	private final Queue<Component> subtitles = new LinkedTransferQueue<>();

	private final Queue<Component> actionBar = new LinkedTransferQueue<>();

	private final Set<BossBar> bossBars = new HashSet<>();

	private Scoreboard scoreboard;

	private final StatisticsMock statistics = new StatisticsMock();

	private final Set<String> channels = new HashSet<>();

	private final List<ItemStack> consumedItems = new LinkedList<>();

	private Locale locale;

	@NotNull
	private PlayerProfile playerProfile;

	/**
	 * Constructs a new {@link PlayerMock} for the provided server with the
	 * specified name. The players UUID will be generated from the name.
	 *
	 * @param server
	 *            The player's server.
	 * @param name
	 *            The player's name.
	 * @see ServerMock#addPlayer
	 */
	public PlayerMock(@NotNull ServerMock server, @NotNull String name)
	{
		this(server, name, UUID.randomUUID());
	}

	/**
	 * Constructs a new {@link PlayerMock} for the provided server with the
	 * specified name and {@link UUID}. Does NOT add the player to the server.
	 *
	 * @param server
	 *            The player's server.
	 * @param name
	 *            The player's name.
	 * @param uuid
	 *            The player's {@link UUID}.
	 * @see ServerMock#addPlayer
	 */
	public PlayerMock(@NotNull ServerMock server, @NotNull String name, @NotNull UUID uuid)
	{
		Preconditions.checkNotNull(name, "Name cannot be null");
		super(server, uuid);
		setName(name);
		setDisplayName(name);
		this.online = true;
		if (Bukkit.getWorlds().isEmpty())
		{
			MockBukkit.getMock().addSimpleWorld("world");
		}
		setLocation(Bukkit.getWorlds().get(0).getSpawnLocation().clone());
		setCompassTarget(getLocation());
		closeInventory();
		// NMS Player#createAttributes
		attributes.get(Attribute.MOVEMENT_SPEED).setBaseValue(0.10000000149011612D);
		Random random = ThreadLocalRandom.current();
		address = new InetSocketAddress("192.0.2." + random.nextInt(255), random.nextInt(32768, 65535));
		scoreboard = server.getScoreboardManager().getMainScoreboard();
		locale = Locale.ENGLISH;
		playerProfile = Bukkit.createProfile(uuid, name);
	}

	/**
	 * Simulates a disconnection from the server.
	 *
	 * @return True if the player was disconnected, false if they were already
	 *         offline.
	 */
	public boolean disconnect()
	{
		if (!online)
		{
			return false;
		}
		this.online = false;
		this.conversationTracker.abandonAllConversations();
		this.perms.clearPermissions();
		Component message = MiniMessage.miniMessage().deserialize("<name> has left the Server!",
				Placeholder.component("name", this.displayName()));
		PlayerQuitEvent playerQuitEvent = new PlayerQuitEvent(this, message, PlayerQuitEvent.QuitReason.DISCONNECTED);
		Bukkit.getPluginManager().callEvent(playerQuitEvent);
		this.server.getPlayerList().disconnectPlayer(this);
		return true;
	}

	/**
	 * Simulates a connection to the server.
	 *
	 * @return True if the player was connected, false if they were already online.
	 */
	public boolean reconnect()
	{
		if (Arrays.stream(server.getPlayerList().getOfflinePlayers())
				.noneMatch(it -> it.getUniqueId().equals(this.getUniqueId())))
		{
			throw new IllegalStateException("Player was never online");
		}
		if (server.hasWhitelist() && !server.getWhitelistedPlayers().contains(this))
		{
			return false;
		}
		if (online)
		{
			return false;
		}
		this.online = true;
		server.addPlayer(this);
		return true;
	}

	/**
	 * Simulates a Player consuming an Edible Item. Some edibles inflict status
	 * effects on the consumer with a certain probability.
	 *
	 * @param consumable
	 *            The Item to consume
	 * @see PlayerMock#simulateConsumeItem(ItemStack)
	 * @deprecated Use {@link PlayerSimulation#simulateConsumeItem(ItemStack)}
	 *             instead.
	 */
	@Deprecated(forRemoval = true, since = "4.0")
	public void simulateConsumeItem(@NotNull ItemStack consumable)
	{
		consumedItems.add(new PlayerSimulation(this).simulateConsumeItem(consumable));
	}

	/**
	 * Whether the player has consumed the specified item
	 *
	 * @param consumable
	 *            The item that the player should have consumed
	 * @return True if the specified item has been consumed
	 */
	public boolean hasConsumed(@NotNull ItemStack consumable)
	{
		Preconditions.checkNotNull(consumable, "Consumed Item can't be null");
		return consumedItems.contains(consumable);
	}

	/**
	 * Asserts a Player has consumed the given Item
	 *
	 * @param consumable
	 *            The Item to assert has been consumed
	 * @deprecated Use {@link PlayerSimulation#assertItemConsumed(ItemStack)}
	 *             instead.
	 */
	@Deprecated(forRemoval = true, since = "4.0")
	public void assertItemConsumed(@NotNull ItemStack consumable)
	{
		Preconditions.checkNotNull(consumable, "Consumed Item can't be null");
		if (!consumedItems.contains(consumable))
		{
			throw new AssertionError();
		}
	}

	@Override
	@NotNull
	public EntityType getType()
	{
		return EntityType.PLAYER;
	}

	/**
	 * Simulates the player damaging a block. Note that this method does not do
	 * anything unless the player is in survival mode. If {@code InstaBreak} is set
	 * to true by an event handler, a {@link BlockBreakEvent} is immediately fired.
	 * The result will then still be whether or not the {@link BlockDamageEvent} was
	 * cancelled or not, not the later {@link BlockBreakEvent}.
	 *
	 * @param block
	 *            The block to damage.
	 * @return the event that was fired, {@code null} if the player was not in
	 *         survival gamemode.
	 * @deprecated Use {@link PlayerSimulation#simulateBlockDamage(Block)} instead.
	 */
	@Deprecated(forRemoval = true)
	@Nullable
	public BlockDamageEvent simulateBlockDamage(@NotNull Block block)
	{
		return new PlayerSimulation(this).simulateBlockDamage(block);
	}

	/**
	 * Simulates the player breaking a block. This method will not break the block
	 * if the player is in adventure or spectator mode. If the player is in survival
	 * mode, the player will first damage the block.
	 *
	 * @param block
	 *            The block to break.
	 * @return The event that was fired, {@code null} if it wasn't or if the player
	 *         was in adventure mode or in spectator mode.
	 * @deprecated Use {@link PlayerSimulation#simulateBlockBreak(Block)} instead.
	 */
	@Deprecated(forRemoval = true, since = "4.0")
	@Nullable
	public BlockBreakEvent simulateBlockBreak(@NotNull Block block)
	{
		return new PlayerSimulation(this).simulateBlockBreak(block);
	}

	/**
	 * Simulates the player placing a block. This method will not place the block if
	 * the player is in adventure or spectator mode.
	 *
	 * @param material
	 *            The material of the location to set to
	 * @param location
	 *            The location of the material to set to
	 * @return The event that was fired. {@code null} if it wasn't or the player was
	 *         in adventure mode.
	 * @deprecated Use
	 *             {@link PlayerSimulation#simulateBlockPlace(Material, Location)}
	 *             instead.
	 */
	@Deprecated(forRemoval = true, since = "4.0")
	@Nullable
	public BlockPlaceEvent simulateBlockPlace(@NotNull Material material, @NotNull Location location)
	{
		return new PlayerSimulation(this).simulateBlockPlace(material, location);
	}

	/**
	 * Simulates the player clicking an Inventory.
	 *
	 * @param slot
	 *            The slot in the player's open inventory
	 * @return The event that was fired.
	 * @deprecated Use {@link PlayerSimulation#simulateInventoryClick(int)} instead.
	 */
	@Deprecated(forRemoval = true, since = "4.0")
	@NotNull
	public InventoryClickEvent simulateInventoryClick(int slot)
	{
		return new PlayerSimulation(this).simulateInventoryClick(slot);
	}

	/**
	 * Simulates the player clicking an Inventory.
	 *
	 * @param inventoryView
	 *            The inventory view we want to click
	 * @param slot
	 *            The slot in the provided Inventory
	 * @return The event that was fired.
	 * @deprecated Use
	 *             {@link PlayerSimulation#simulateInventoryClick(InventoryView, int)}
	 *             instead.
	 */
	@Deprecated(forRemoval = true, since = "4.0")
	@NotNull
	public InventoryClickEvent simulateInventoryClick(@NotNull InventoryView inventoryView, int slot)
	{
		return new PlayerSimulation(this).simulateInventoryClick(inventoryView, slot);
	}

	/**
	 * Simulates the player clicking an Inventory.
	 *
	 * @param inventoryView
	 *            The inventory view we want to click
	 * @param clickType
	 *            The click type we want to fire
	 * @param slot
	 *            The slot in the provided Inventory
	 * @return The event that was fired.
	 * @deprecated Use
	 *             {@link PlayerSimulation#simulateInventoryClick(InventoryView, ClickType, int)}
	 *             instead.
	 */
	@Deprecated(forRemoval = true, since = "4.0")
	@NotNull
	public InventoryClickEvent simulateInventoryClick(@NotNull InventoryView inventoryView,
			@NotNull ClickType clickType, int slot)
	{
		return new PlayerSimulation(this).simulateInventoryClick(inventoryView, clickType, slot);
	}

	/**
	 * This method simulates the {@link Player} respawning and also calls a
	 * {@link PlayerRespawnEvent}. Should the {@link Player} not be dead (when
	 * {@link #isDead()} returns false) then this will throw an
	 * {@link UnsupportedOperationException}. Otherwise, the {@link Location} will
	 * be set to {@link Player#getBedSpawnLocation()} or
	 * {@link World#getSpawnLocation()}. Lastly the health of this {@link Player}
	 * will be restored and set to the max health.
	 */
	public PlayerRespawnEvent respawn()
	{
		Location location = getRespawnLocation();
		boolean isBedSpawn = location != null;
		// Respawn Anchors are not yet supported.
		boolean isAnchorSpawn = false;
		if (!isBedSpawn)
		{
			location = getLocation().getWorld().getSpawnLocation();
		}
		PlayerRespawnEvent event = new PlayerRespawnEvent(this, location, isBedSpawn, isAnchorSpawn);
		Bukkit.getPluginManager().callEvent(event);
		// Reset location and health
		this.setHealth(this.getAttribute(Attribute.MAX_HEALTH).getValue());
		setLocation(event.getRespawnLocation().clone());
		alive = true;
		return event;
	}

	/**
	 * This method moves player instantly with respect to PlayerMoveEvent
	 *
	 * @param moveLocation
	 *            Location to move player to
	 * @return The event that is fired
	 * @deprecated Use {@link PlayerSimulation#simulatePlayerMove(Location)}
	 *             instead.
	 */
	@Deprecated(forRemoval = true, since = "4.0")
	@NotNull
	public PlayerMoveEvent simulatePlayerMove(@NotNull Location moveLocation)
	{
		return new PlayerSimulation(this).simulatePlayerMove(moveLocation);
	}

	@Override
	public void setGameMode(@NotNull GameMode mode)
	{
		Preconditions.checkNotNull(mode, "GameMode cannot be null");
		if (super.getGameMode() == mode)
		{
			return;
		}
		PlayerGameModeChangeEvent event = new PlayerGameModeChangeEvent(this, mode,
				PlayerGameModeChangeEvent.Cause.UNKNOWN, null);
		if (!event.callEvent())
		{
			return;
		}
		this.previousGamemode = super.getGameMode();
		super.setGameMode(mode);
	}

	@Override
	public boolean isWhitelisted()
	{
		return server.getWhitelistedPlayers().contains(this);
	}

	@Override
	public void setWhitelisted(boolean value)
	{
		if (value)
		{
			server.getWhitelistedPlayers().add(this);
		} else
		{
			server.getWhitelistedPlayers().remove(this);
		}
	}

	@Override
	public Player getPlayer()
	{
		return (isOnline()) ? this : null;
	}

	@Override
	public boolean isOnline()
	{
		return getServer().getPlayer(getUniqueId()) != null;
	}

	@Override
	public boolean isBanned()
	{
		return MockBukkit.getMock().getBanList(BanList.Type.NAME).isBanned(getName());
	}

	@Override
	@Nullable
	public <E extends BanEntry<? super PlayerProfile>> E ban(@Nullable String reason, @Nullable Date expires,
			@Nullable String source)
	{
		return this.ban(reason, expires, source, true);
	}

	@Override
	@Nullable
	public <E extends BanEntry<? super PlayerProfile>> E ban(@Nullable String reason, @Nullable Instant expires,
			@Nullable String source)
	{
		return this.ban(reason, expires != null ? Date.from(expires) : null, source);
	}

	@Override
	@Nullable
	public <E extends BanEntry<? super PlayerProfile>> E ban(@Nullable String reason, @Nullable Duration duration,
			@Nullable String source)
	{
		return this.ban(reason, duration != null ? Instant.now().plus(duration) : null, source);
	}

	@Override
	@Nullable
	public <E extends BanEntry<? super PlayerProfile>> E ban(@Nullable String reason, @Nullable Date expires,
			@Nullable String source, boolean kickPlayer)
	{
		E banEntry = (E) ((ProfileBanList) getServer().getBanList(BanList.Type.PROFILE)).addBan(this.getPlayerProfile(),
				reason, expires, source);
		if (kickPlayer)
		{
			this.kickPlayer(reason);
		}
		return banEntry;
	}

	@Override
	@Nullable
	public <E extends BanEntry<? super PlayerProfile>> E ban(@Nullable String reason, @Nullable Instant instant,
			@Nullable String source, boolean kickPlayer)
	{
		return this.ban(reason, instant != null ? Date.from(instant) : null, source, kickPlayer);
	}

	@Override
	@Nullable
	public <E extends BanEntry<? super PlayerProfile>> E ban(@Nullable String reason, @Nullable Duration duration,
			@Nullable String source, boolean kickPlayer)
	{
		return this.ban(reason, duration != null ? Instant.now().plus(duration) : null, source, kickPlayer);
	}

	/**
	 * This method is an assertion for the currently open {@link InventoryView} for
	 * this {@link Player}. The {@link Predicate} refers to the top inventory, not
	 * the {@link PlayerInventory}. It uses the method
	 * {@link InventoryView#getTopInventory()}.
	 *
	 * @param message
	 *            The message to display upon failure
	 * @param type
	 *            The {@link InventoryType} you are expecting
	 * @param predicate
	 *            A custom {@link Predicate} to check the opened {@link Inventory}.
	 * @deprecated Use manual assertions with {@link #getOpenInventory()} instead.
	 */
	@Deprecated(forRemoval = true, since = "4.0")
	public void assertInventoryView(String message, InventoryType type, @NotNull Predicate<Inventory> predicate)
	{
		InventoryView view = getOpenInventory();
		if (view.getType() == type && predicate.test(view.getTopInventory()))
		{
			return;
		}
		throw new AssertionError(message);
	}

	/**
	 * This method is an assertion for the currently open {@link InventoryView} for
	 * this {@link Player}. The {@link Predicate} refers to the top inventory, not
	 * the {@link PlayerInventory}. It uses the method
	 * {@link InventoryView#getTopInventory()}.
	 *
	 * @param type
	 *            The {@link InventoryType} you are expecting
	 * @param predicate
	 *            A custom {@link Predicate} to check the opened {@link Inventory}.
	 * @deprecated Use manual assertions with {@link #getOpenInventory()} instead.
	 */
	@Deprecated(forRemoval = true, since = "4.0")
	public void assertInventoryView(InventoryType type, @NotNull Predicate<Inventory> predicate)
	{
		assertInventoryView("The InventoryView Assertion has failed", type, predicate);
	}

	/**
	 * This method is an assertion for the currently open {@link InventoryView} for
	 * this {@link Player}.
	 *
	 * @param type
	 *            The {@link InventoryType} you are expecting
	 * @deprecated Use manual assertions with {@link #getOpenInventory()} instead.
	 */
	@Deprecated(forRemoval = true, since = "4.0")
	public void assertInventoryView(InventoryType type)
	{
		assertInventoryView("The InventoryView Assertion has failed", type, inv -> true);
	}

	/**
	 * This method is an assertion for the currently open {@link InventoryView} for
	 * this {@link Player}.
	 *
	 * @param message
	 *            The message to display upon failure
	 * @param type
	 *            The {@link InventoryType} you are expecting
	 * @deprecated Use manual assertions with {@link #getOpenInventory()} instead.
	 */
	@Deprecated(forRemoval = true, since = "4.0")
	public void assertInventoryView(String message, InventoryType type)
	{
		assertInventoryView(message, type, inv -> true);
	}

	@Override
	public void updateInventory()
	{
		// Normally a packet would be sent here to update the player's inventory.
		// We just pretend that this happened!
	}

	@Override
	public boolean performCommand(@NotNull String command)
	{
		Preconditions.checkNotNull(command, "Command cannot be null");
		return Bukkit.dispatchCommand(this, command);
	}

	@Override
	public boolean isAllowingServerListings()
	{
		return this.allowServerListings;
	}

	/**
	 * Sets whether the player has the "Allow Server Listings" setting enabled.
	 *
	 * @param allowServerListings
	 *            whether the player allows server listings
	 */
	public void setAllowServerListings(boolean allowServerListings)
	{
		this.allowServerListings = allowServerListings;
	}

	@Override
	public EntityEquipment getEquipment()
	{
		return (EntityEquipment) getInventory();
	}

	@Override
	public boolean isConversing()
	{
		return this.conversationTracker.isConversing();
	}

	@Override
	public void acceptConversationInput(@NotNull String input)
	{
		this.conversationTracker.acceptConversationInput(input);
	}

	@Override
	public boolean beginConversation(@NotNull Conversation conversation)
	{
		return this.conversationTracker.beginConversation(conversation);
	}

	@Override
	public void abandonConversation(@NotNull Conversation conversation)
	{
		this.conversationTracker.abandonConversation(conversation,
				new ConversationAbandonedEvent(conversation, new ManuallyAbandonedConversationCanceller()));
	}

	@Override
	public void abandonConversation(@NotNull Conversation conversation, @NotNull ConversationAbandonedEvent details)
	{
		this.conversationTracker.abandonConversation(conversation, details);
	}

	@Override
	public long getFirstPlayed()
	{
		return this.server.getPlayerList().getFirstPlayed(getUniqueId());
	}

	/**
	 * @deprecated Use {@link #getLastSeen()} instead.
	 */
	@Override
	@Deprecated(since = "1.13")
	public long getLastPlayed()
	{
		return getLastSeen();
	}

	/**
	 * No longer used.
	 *
	 * @param time
	 *            N/A.
	 * @see PlayerListMock#setLastSeen(UUID, long)
	 * @deprecated Moved to {@link PlayerListMock}.
	 */
	@Deprecated(forRemoval = true)
	public void setLastPlayed(long time)
	{
		throw new UnsupportedOperationException("Deprecated; Does not do anything");
	}

	@Override
	public boolean hasPlayedBefore()
	{
		return server.getPlayerList().hasPlayedBefore(getUniqueId());
	}

	@Override
	@NotNull
	public Map<String, Object> serialize()
	{
		Map<String, Object> result = new LinkedHashMap<>();
		result.put("name", getName());
		return result;
	}

	@Override
	public void sendPluginMessage(@NotNull Plugin source, @NotNull String channel, byte[] message)
	{
		Preconditions.checkNotNull(source, "Source cannot be null");
		Preconditions.checkNotNull(channel, "Channel cannot be null");
		StandardMessenger.validatePluginMessage(getServer().getMessenger(), source, channel, message);
	}

	@Override
	@NotNull
	public Set<String> getListeningPluginChannels()
	{
		return ImmutableSet.copyOf(channels);
	}

	@Override
	@NotNull
	public Component displayName()
	{
		return this.displayName;
	}

	@Override
	public void displayName(@Nullable Component displayName)
	{
		this.displayName = displayName;
	}

	@Override
	/**
	 * @deprecated Use modern API instead.
	 */
	@Deprecated(since = "1.21")
	@NotNull
	public String getDisplayName()
	{
		return LegacyComponentSerializer.legacySection().serialize(this.displayName);
	}

	@Override
	/**
	 * @deprecated Use modern API instead.
	 */
	@Deprecated(since = "1.21.3")
	/**
	 * @deprecated Use {@link #displayName(Component)} instead.
	 */
	public void setDisplayName(@Nullable String name)
	{
		this.displayName = name == null ? null : LegacyComponentSerializer.legacySection().deserialize(name);
	}

	@Override
	@NotNull
	public String getScoreboardEntryName()
	{
		return this.getName();
	}

	@Override
	public void playerListName(@Nullable Component name)
	{
		this.playerListName = name;
	}

	@Override
	@NotNull
	public Component playerListName()
	{
		return this.playerListName == null ? name() : this.playerListName;
	}

	@Override
	@Nullable
	public Component playerListHeader()
	{
		return this.playerListHeader;
	}

	@Override
	@Nullable
	public Component playerListFooter()
	{
		return this.playerListFooter;
	}

	@Override
	/**
	 * @deprecated Use modern API instead.
	 */
	@Deprecated(since = "1.21.3")
	/**
	 * @deprecated Use {@link #playerListName(Component)} instead.
	 */
	@NotNull
	public String getPlayerListName()
	{
		return this.playerListName == null
				? getName()
				: LegacyComponentSerializer.legacySection().serialize(this.playerListName);
	}

	@Override
	/**
	 * @deprecated Use modern API instead.
	 */
	@Deprecated
	public void setPlayerListName(@Nullable String name)
	{
		this.playerListName = name == null ? null : LegacyComponentSerializer.legacySection().deserialize(name);
	}

	@Override
	public int getPlayerListOrder()
	{
		return this.playerListOrder;
	}

	@Override
	public void setPlayerListOrder(int order)
	{
		Preconditions.checkArgument(order >= 0, "order cannot be negative");
		this.playerListOrder = order;
	}

	@Override
	public void setCompassTarget(@NotNull Location loc)
	{
		Preconditions.checkNotNull(loc, LOCATION_CANNOT_BE_NULL);
		this.compassTarget = loc;
	}

	@NotNull
	@Override
	public Location getCompassTarget()
	{
		return this.compassTarget;
	}

	/**
	 * Sets the {@link InetSocketAddress} returned by {@link #getAddress}.
	 *
	 * @param address
	 *            The address to set.
	 */
	public void setAddress(@Nullable InetSocketAddress address)
	{
		this.address = address;
	}

	@Override
	@Nullable
	public InetSocketAddress getAddress()
	{
		return (isOnline()) ? address : null;
	}

	/**
	 * Sets the socket address of this player's proxy
	 *
	 * @param haProxyAddress
	 *            the player's proxy address, null if the server doesn't have Proxy
	 *            Protocol enabled, or the player didn't connect to an HAProxy
	 *            instance.
	 */
	public void setHaProxyAddress(@Nullable InetSocketAddress haProxyAddress)
	{
		this.haProxyAddress = haProxyAddress;
	}

	@Override
	@Nullable
	public InetSocketAddress getHAProxyAddress()
	{
		return (isOnline()) ? haProxyAddress : null;
	}

	@Override
	public void sendRawMessage(@NotNull String message)
	{
		this.sendRawMessage(null, message);
	}

	@Override
	public void sendRawMessage(@Nullable UUID sender, @NotNull String message)
	{
		Preconditions.checkArgument(message != null, MESSAGE_CANNOT_BE_NULL_LOWER);
		this.messages.add(LegacyComponentSerializer.legacySection().deserialize(message));
	}

	@Override
	public void sendMessage(String message)
	{
		if (!this.conversationTracker.isConversingModaly())
		{
			this.sendRawMessage(message);
		}
	}

	@Override
	public void sendMessage(String... messages)
	{
		for (String message : messages)
		{
			this.sendMessage(message);
		}
	}

	@Override
	public void sendMessage(UUID sender, String message)
	{
		if (!this.conversationTracker.isConversingModaly())
		{
			this.sendRawMessage(sender, message);
		}
	}

	@Override
	public void sendMessage(UUID sender, String... messages)
	{
		for (String message : messages)
		{
			this.sendMessage(sender, message);
		}
	}

	@Override
	public void sendMessage(@NotNull Component message)
	{
		Preconditions.checkNotNull(message, MESSAGE_CANNOT_BE_NULL_LOWER);
		this.messages.add(message);
	}

	@Override
	public void sendMessage(@NotNull ComponentLike message)
	{
		Preconditions.checkNotNull(message, MESSAGE_CANNOT_BE_NULL_LOWER);
		this.sendMessage(message.asComponent());
	}

	@Override
	public void sendMessage(@NotNull final Identity source, @NotNull final Component message,
			@NotNull final MessageType type)
	{
		Preconditions.checkNotNull(message, "input");
		this.sendMessage(message);
	}

	/**
	 * @deprecated Use {@link #sendMessage(Component)} instead.
	 */
	@Override
	@Deprecated(since = "1.21")
	public void sendMessage(@NotNull Component message, @NotNull MessageType type)
	{
		this.sendMessage(message);
	}

	/**
	 * @deprecated Use {@link #sendMessage(Component)} instead.
	 */
	@Override
	@Deprecated(since = "1.21")
	public void sendMessage(@NotNull ComponentLike message, @NotNull MessageType type)
	{
		this.sendMessage(message);
	}

	@Override
	public void sendActionBar(@NotNull ComponentLike message)
	{
		Preconditions.checkNotNull(message, MESSAGE_CANNOT_BE_NULL_LOWER);
		this.sendActionBar(message.asComponent());
	}

	@Override
	@Nullable
	public Component nextComponentMessage()
	{
		return messages.poll();
	}

	/**
	 * @deprecated Use {@link #kick(Component)} instead.
	 */
	@Override
	@Deprecated(since = "1.21")
	public void kickPlayer(String message)
	{
		kick(Component.text(message));
	}

	@Override
	public void kick()
	{
		kick(DEFAULT_KICK_COMPONENT);
	}

	@Override
	public void kick(@Nullable Component message)
	{
		kick(message, PlayerKickEvent.Cause.PLUGIN);
	}

	@Override
	public void kick(@Nullable Component message, PlayerKickEvent.@NotNull Cause cause)
	{
		AsyncCatcher.catchOp("player kick");
		if (!isOnline())
		{
			return;
		}
		PlayerKickEvent event = new PlayerKickEvent(this, Component.text("Plugin"),
				message == null ? Component.empty() : message, cause);
		Bukkit.getPluginManager().callEvent(event);
		server.getPlayerList().disconnectPlayer(this);
	}

	@Override
	public void chat(@NotNull String msg)
	{
		Preconditions.checkNotNull(msg, MESSAGE_CANNOT_BE_NULL);
		// Fire AsyncChatEvent (Modern)
		AsyncChatEvent asyncChatEvent = new AsyncChatEvent(true, this, new HashSet<>(Bukkit.getOnlinePlayers()),
				ChatRenderer.defaultRenderer(), Component.text(msg), Component.text(msg),
				SignedMessage.system(msg, Component.text(msg)));
		// Fire AsyncPlayerChatEvent (Legacy)
		AsyncPlayerChatEvent asyncPlayerChatEvent = new AsyncPlayerChatEvent(true, this, msg,
				new HashSet<>(Bukkit.getOnlinePlayers()));
		// Fire PlayerChatEvent (Legacy Sync)
		PlayerChatEvent syncEvent = new PlayerChatEvent(this, msg);
		server.getScheduler().executeAsyncEvent(asyncChatEvent);
		server.getScheduler().executeAsyncEvent(asyncPlayerChatEvent);
		server.getPluginManager().callEvent(syncEvent);
		// Implement broadcasting based on event results (renderers, viewers,
		// cancellation)
	}

	@Override
	@Nullable
	public BanEntry<InetAddress> banIp(@Nullable String reason, @Nullable Date expires, @Nullable String source,
			boolean kickPlayer)
	{
		Preconditions.checkArgument(this.getAddress() != null, "The Address of this Player is null");
		BanEntry<InetAddress> banEntry = ((IpBanList) this.server.getBanList(BanList.Type.IP))
				.addBan(this.getAddress().getAddress(), reason, expires, source);
		if (kickPlayer)
		{
			this.kickPlayer(reason);
		}
		return banEntry;
	}

	@Override
	@Nullable
	public BanEntry<InetAddress> banIp(@Nullable String reason, @Nullable Instant instant, @Nullable String source,
			boolean kickPlayer)
	{
		return this.banIp(reason, instant != null ? Date.from(instant) : null, source, kickPlayer);
	}

	@Override
	@Nullable
	public BanEntry<InetAddress> banIp(@Nullable String reason, @Nullable Duration duration, @Nullable String source,
			boolean kickPlayer)
	{
		return this.banIp(reason, duration != null ? Instant.now().plus(duration) : null, source, kickPlayer);
	}

	/**
	 * Simulates sneaking.
	 *
	 * @param sneak
	 *            Whether the player is beginning to sneak.
	 * @return The event.
	 */
	@Deprecated(forRemoval = true, since = "4.0")
	@NotNull
	public PlayerToggleSneakEvent simulateSneak(boolean sneak)
	{
		return new PlayerSimulation(this).simulateSneak(sneak);
	}

	@Override
	public boolean isSprinting()
	{
		return sprinting;
	}

	@Override
	public void setSprinting(boolean sprinting)
	{
		this.sprinting = sprinting;
	}

	/**
	 * Simulates sprinting.
	 *
	 * @param sprint
	 *            Whether the player is beginning to sprint.
	 * @return The event.
	 */
	@Deprecated(forRemoval = true, since = "4.0")
	@NotNull
	public PlayerToggleSprintEvent simulateSprint(boolean sprint)
	{
		return new PlayerSimulation(this).simulateSprint(sprint);
	}

	@Override
	public boolean isSleepingIgnored()
	{
		return this.sleepingIgnored;
	}

	@Override
	public void setSleepingIgnored(boolean isSleeping)
	{
		this.sleepingIgnored = isSleeping;
	}

	@Override
	/**
	 * @deprecated Use modern API instead.
	 */
	@Deprecated(since = "1.6.2")
	public void playNote(@NotNull Location loc, byte instrument, byte note)
	{
		playNote(loc, Instrument.getByType(instrument), note);
	}

	@Override
	public void playNote(@NotNull Location loc, @NotNull Instrument instrument, @NotNull Note note)
	{
		playNote(loc, instrument, note.getId());
	}

	private void playNote(@NotNull Location loc, @NotNull Instrument instrument, byte note)
	{
		Preconditions.checkNotNull(loc, LOCATION_CANNOT_BE_NULL);
		Preconditions.checkNotNull(instrument, "Instrument cannot be null");
		Sound sound = switch (instrument)
		{
			case BANJO -> Sound.BLOCK_NOTE_BLOCK_BANJO;
			case BASS_DRUM -> Sound.BLOCK_NOTE_BLOCK_BASEDRUM;
			case BASS_GUITAR -> Sound.BLOCK_NOTE_BLOCK_BASS;
			case BELL -> Sound.BLOCK_NOTE_BLOCK_BELL;
			case BIT -> Sound.BLOCK_NOTE_BLOCK_BIT;
			case CHIME -> Sound.BLOCK_NOTE_BLOCK_CHIME;
			case COW_BELL -> Sound.BLOCK_NOTE_BLOCK_COW_BELL;
			case DIDGERIDOO -> Sound.BLOCK_NOTE_BLOCK_DIDGERIDOO;
			case FLUTE -> Sound.BLOCK_NOTE_BLOCK_FLUTE;
			case GUITAR -> Sound.BLOCK_NOTE_BLOCK_GUITAR;
			case IRON_XYLOPHONE -> Sound.BLOCK_NOTE_BLOCK_IRON_XYLOPHONE;
			case PIANO -> Sound.BLOCK_NOTE_BLOCK_HARP;
			case PLING -> Sound.BLOCK_NOTE_BLOCK_PLING;
			case SNARE_DRUM -> Sound.BLOCK_NOTE_BLOCK_SNARE;
			case STICKS -> Sound.BLOCK_NOTE_BLOCK_HAT;
			case XYLOPHONE -> Sound.BLOCK_NOTE_BLOCK_XYLOPHONE;
			case TRUMPET -> Sound.BLOCK_NOTE_BLOCK_TRUMPET;
			case TRUMPET_EXPOSED -> Sound.BLOCK_NOTE_BLOCK_TRUMPET_EXPOSED;
			case TRUMPET_OXIDIZED -> Sound.BLOCK_NOTE_BLOCK_TRUMPET_OXIDIZED;
			case TRUMPET_WEATHERED -> Sound.BLOCK_NOTE_BLOCK_TRUMPET_WEATHERED;
			case ZOMBIE -> Sound.BLOCK_NOTE_BLOCK_IMITATE_ZOMBIE;
			case SKELETON -> Sound.BLOCK_NOTE_BLOCK_IMITATE_SKELETON;
			case CREEPER -> Sound.BLOCK_NOTE_BLOCK_IMITATE_CREEPER;
			case DRAGON -> Sound.BLOCK_NOTE_BLOCK_IMITATE_ENDER_DRAGON;
			case WITHER_SKELETON -> Sound.BLOCK_NOTE_BLOCK_IMITATE_WITHER_SKELETON;
			case PIGLIN -> Sound.BLOCK_NOTE_BLOCK_IMITATE_PIGLIN;
			// What the Fuck Mojang?
			case CUSTOM_HEAD -> Sound.UI_BUTTON_CLICK;
		};
		float pitch = (float) Math.pow(2.0D, (note - 12.0D) / 12.0D);
		playSound(loc, sound, SoundCategory.RECORDS, 3, pitch);
	}

	@Override
	public void playSound(@NotNull Location location, @NotNull String sound, float volume, float pitch)
	{
		Preconditions.checkNotNull(location, LOCATION_CANNOT_BE_NULL);
		Preconditions.checkNotNull(sound, SOUND_CANNOT_BE_NULL);
		heardSounds.add(new AudioExperience(sound, SoundCategory.MASTER, location, volume, pitch));
	}

	@Override
	public void playSound(@NotNull Location location, @NotNull Sound sound, float volume, float pitch)
	{
		playSound(location, sound, SoundCategory.MASTER, volume, pitch);
	}

	@Override
	public void playSound(@NotNull Entity entity, @NotNull Sound sound, float volume, float pitch)
	{
		playSound(entity, sound, SoundCategory.MASTER, volume, pitch);
	}

	@Override
	public void playSound(@NotNull Location location, @NotNull String sound, @NotNull SoundCategory category,
			float volume, float pitch)
	{
		Preconditions.checkNotNull(location, LOCATION_CANNOT_BE_NULL);
		Preconditions.checkNotNull(sound, SOUND_CANNOT_BE_NULL);
		Preconditions.checkNotNull(category, CATEGORY_CANNOT_BE_NULL);
		heardSounds.add(new AudioExperience(sound, category, location, volume, pitch));
	}

	@Override
	public void playSound(@NotNull Location location, @NotNull Sound sound, @NotNull SoundCategory category,
			float volume, float pitch)
	{
		Preconditions.checkNotNull(location, LOCATION_CANNOT_BE_NULL);
		Preconditions.checkNotNull(sound, SOUND_CANNOT_BE_NULL);
		Preconditions.checkNotNull(category, CATEGORY_CANNOT_BE_NULL);
		heardSounds.add(new AudioExperience(sound, category, location, volume, pitch));
	}

	@Override
	public void playSound(@NotNull Entity entity, @NotNull Sound sound, @NotNull SoundCategory category, float volume,
			float pitch)
	{
		Preconditions.checkNotNull(entity, ENTITY_CANNOT_BE_NULL);
		Preconditions.checkNotNull(sound, SOUND_CANNOT_BE_NULL);
		Preconditions.checkNotNull(category, CATEGORY_CANNOT_BE_NULL);
		heardSounds.add(new AudioExperience(sound, category, entity.getLocation(), volume, pitch));
	}

	@Override
	@NotNull
	public TriState hasFlyingFallDamage()
	{
		return this.flyingFallDamage;
	}

	@Override
	public void setFlyingFallDamage(@NotNull TriState flyingFallDamage)
	{
		Preconditions.checkArgument(flyingFallDamage != null, "flyingFallDamage cannot be null");
		this.flyingFallDamage = flyingFallDamage;
	}

	@Override
	public void setHasSeenWinScreen(boolean hasSeenWinScreen)
	{
		this.seenWinScreen = hasSeenWinScreen;
	}

	@Override
	public boolean hasSeenWinScreen()
	{
		return this.seenWinScreen;
	}

	@Override
	public void playSound(net.kyori.adventure.sound.@NotNull Sound sound)
	{
		// Not strictly equivalent since normally the sound does not follow the entity.
		playSound(sound, net.kyori.adventure.sound.Sound.Emitter.self());
	}

	@Override
	public void playSound(net.kyori.adventure.sound.@NotNull Sound sound, double x, double y, double z)
	{
		Preconditions.checkNotNull(sound, SOUND_CANNOT_BE_NULL);
		heardSounds.add(new AudioExperience(sound, new Location(getWorld(), x, y, z)));
	}

	@Override
	public void playSound(net.kyori.adventure.sound.@NotNull Sound sound,
			net.kyori.adventure.sound.Sound.@NotNull Emitter emitter)
	{
		Preconditions.checkNotNull(emitter, "Emitter cannot be null");
		Preconditions.checkNotNull(sound, SOUND_CANNOT_BE_NULL);
		if (emitter == net.kyori.adventure.sound.Sound.Emitter.self())
		{
			emitter = this;
		}
		Preconditions.checkArgument(emitter instanceof Entity, "Sound emitter must be an Entity or self()");
		heardSounds.add(new AudioExperience(sound, ((Entity) emitter).getLocation()));
	}

	@Override
	@NotNull
	public List<AudioExperience> getHeardSounds()
	{
		return heardSounds;
	}

	@Override
	public void addHeardSound(@NotNull AudioExperience audioExperience)
	{
		Preconditions.checkNotNull(audioExperience, "AudioExperience cannot be null");
		SoundReceiver.super.addHeardSound(audioExperience);
	}

	@Override
	public void stopSound(@NotNull Sound sound)
	{
		stopSound(sound, SoundCategory.MASTER);
	}

	@Override
	public void stopSound(@NotNull String sound)
	{
		stopSound(sound, SoundCategory.MASTER);
	}

	@Override
	public void stopSound(@NotNull Sound sound, @Nullable SoundCategory category)
	{
		Preconditions.checkNotNull(sound, SOUND_CANNOT_BE_NULL);
		// We will just pretend the Sound has stopped.
	}

	@Override
	public void stopSound(@NotNull String sound, @Nullable SoundCategory category)
	{
		Preconditions.checkNotNull(sound, SOUND_CANNOT_BE_NULL);
		// We will just pretend the Sound has stopped.
	}

	@Override
	public void stopSound(@NotNull SoundCategory category)
	{
		// We will just pretend the Sound has stopped.
	}

	@Override
	public void stopAllSounds()
	{
		// We will just pretend all Sounds have stopped.
	}

	@Override
	/**
	 * @deprecated Use modern API instead.
	 */
	@Deprecated(since = "1.6.2")
	public void playEffect(@NotNull Location loc, @NotNull Effect effect, int data)
	{
		Preconditions.checkNotNull(loc, LOCATION_CANNOT_BE_NULL);
		Preconditions.checkNotNull(effect, "Effect cannot be null");
		// Pretend packet gets sent.
	}

	@Override
	public <T> void playEffect(@NotNull Location loc, @NotNull Effect effect, @Nullable T data)
	{
		Preconditions.checkNotNull(loc, LOCATION_CANNOT_BE_NULL);
		Preconditions.checkNotNull(effect, "Effect cannot be null");
		if (data != null)
		{
			Preconditions.checkArgument(effect.getData() != null && effect.getData().isAssignableFrom(data.getClass()),
					"Wrong kind of data for this effect! (Expected " + effect.getData() + ", got " + data.getClass());
		} else
		{
			// The axis is optional for ELECTRIC_SPARK
			Preconditions.checkArgument(effect.getData() == null || effect == Effect.ELECTRIC_SPARK,
					"Wrong kind of data for this effect!");
		}
	}

	@Override
	public boolean breakBlock(@NotNull Block block)
	{
		Preconditions.checkNotNull(block, "Block cannot be null");
		Preconditions.checkArgument(block.getWorld().equals(getWorld()), "Cannot break blocks across worlds");
		BlockBreakEvent event = new BlockBreakEvent(block, this);
		boolean swordNoBreak = getGameMode() == GameMode.CREATIVE
				&& getEquipment().getItemInMainHand().getType().name().contains("SWORD");
		event.setCancelled(swordNoBreak);
		Bukkit.getPluginManager().callEvent(event);
		if (!event.isCancelled())
		{
			block.setType(Material.AIR);
			// todo: BlockDropItemEvent when BlockMock#getDrops is implemented.
		}
		return !event.isCancelled();
	}

	@Override
	/**
	 * @deprecated Use modern API instead.
	 */
	@Deprecated(since = "1.6.2")
	public void sendBlockChange(@NotNull Location loc, @NotNull Material material, byte data)
	{
		Preconditions.checkNotNull(loc, LOCATION_CANNOT_BE_NULL);
		Preconditions.checkNotNull(material, "Material cannot be null");
		// Pretend we sent the block change.
	}

	@Override
	public void sendBlockChange(@NotNull Location loc, @NotNull BlockData block)
	{
		Preconditions.checkNotNull(loc, LOCATION_CANNOT_BE_NULL);
		Preconditions.checkNotNull(block, "Block cannot be null");
		// Pretend we sent the block change.
	}

	@Override
	public void sendBlockChanges(@NotNull Collection<BlockState> blocks, boolean suppressLightUpdates)
	{
		// Pretend we sent the block change.
	}

	@Override
	public void sendSignChange(@NotNull Location loc, @Nullable List<? extends Component> lines,
			@NotNull DyeColor dyeColor, boolean hasGlowingText) throws IllegalArgumentException
	{
		Preconditions.checkNotNull(loc, LOCATION_CANNOT_BE_NULL);
		Preconditions.checkNotNull(dyeColor, "DyeColor cannot be null");
		if (lines == null)
		{
			lines = new ArrayList<>(4);
		}
		if (lines.size() < 4)
		{
			throw new IllegalArgumentException("Must have at least 4 lines");
		}
	}

	@Override
	/**
	 * @deprecated Use modern API instead.
	 */
	@Deprecated
	public void sendSignChange(@NotNull Location loc, String[] lines)
	{
		this.sendSignChange(loc, lines, DyeColor.BLACK);
	}

	@Override
	public void sendSignChange(@NotNull Location loc, String[] lines, @NotNull DyeColor dyeColor)
			throws IllegalArgumentException
	{
		this.sendSignChange(loc, lines, dyeColor, false);
	}

	@Override
	public void sendSignChange(@NotNull Location loc, @Nullable String @Nullable [] lines, @NotNull DyeColor dyeColor,
			boolean hasGlowingText) throws IllegalArgumentException
	{
		Preconditions.checkNotNull(loc, LOCATION_CANNOT_BE_NULL);
		Preconditions.checkNotNull(dyeColor, "DyeColor cannot be null");
		if (lines == null)
		{
			lines = new String[4];
		}
		if (lines.length < 4)
		{
			throw new IllegalArgumentException("Must have at least 4 lines");
		}
	}

	@Override
	public void sendBlockUpdate(@NotNull Location loc, @NotNull TileState tileState) throws IllegalArgumentException
	{
		Preconditions.checkNotNull(loc);
		Preconditions.checkNotNull(tileState);
		// Pretend we sent block update
	}

	@Override
	public void sendMap(@NotNull MapView map)
	{
		Preconditions.checkNotNull(map, "Map cannot be null");
		if (!(map instanceof MapViewMock mapView))
		{
			return;
		}
		mapView.render(this);
		// Pretend the map packet gets sent.
	}

	@Override
	public void showWinScreen()
	{
		// You won!
	}

	@Override
	public void sendActionBar(@NotNull Component component)
	{
		actionBar.add(component);
	}

	@Override
	/**
	 * @deprecated Use modern API instead.
	 */
	@Deprecated
	public void sendActionBar(@NotNull String message)
	{
		Preconditions.checkNotNull(message, MESSAGE_CANNOT_BE_NULL);
		// Pretend we sent the action bar.
	}

	@Override
	/**
	 * @deprecated Use modern API instead.
	 */
	@Deprecated
	public void sendActionBar(char alternateChar, @NotNull String message)
	{
		Preconditions.checkNotNull(message, MESSAGE_CANNOT_BE_NULL);
		// Pretend we sent the action bar.
	}

	@Override
	/**
	 * @deprecated Use modern API instead.
	 */
	@Deprecated
	public void sendActionBar(@NotNull BaseComponent... message)
	{
		Preconditions.checkNotNull(message, MESSAGE_CANNOT_BE_NULL);
		// Pretend we sent the action bar.
	}

	/**
	 * @return The next action bar sent to the player.
	 */
	public Component nextActionBar()
	{
		return actionBar.poll();
	}

	@Override
	/**
	 * @deprecated Use modern API instead.
	 */
	@Deprecated
	public void setPlayerListHeaderFooter(BaseComponent @NotNull [] header, BaseComponent @NotNull [] footer)
	{
		this.playerListHeader = BungeeComponentSerializer.get()
				.deserialize(Arrays.stream(header).filter(Objects::nonNull).toArray(BaseComponent[]::new));
		this.playerListFooter = BungeeComponentSerializer.get()
				.deserialize(Arrays.stream(footer).filter(Objects::nonNull).toArray(BaseComponent[]::new));
	}

	@Override
	public void sendPlayerListHeader(@NotNull Component header)
	{
		Preconditions.checkNotNull(header, HEADER_CANNOT_BE_NULL);
		this.playerListHeader = header;
	}

	@Override
	public void sendPlayerListFooter(@NotNull Component footer)
	{
		Preconditions.checkNotNull(footer, FOOTER_CANNOT_BE_NULL);
		this.playerListFooter = footer;
	}

	@Override
	public void sendPlayerListHeaderAndFooter(@NotNull Component header, @NotNull Component footer)
	{
		Preconditions.checkNotNull(header, HEADER_CANNOT_BE_NULL);
		Preconditions.checkNotNull(footer, FOOTER_CANNOT_BE_NULL);
		this.playerListHeader = header;
		this.playerListFooter = footer;
	}

	@Override
	public void sendPlayerListHeaderAndFooter(@NotNull ComponentLike header, @NotNull ComponentLike footer)
	{
		Preconditions.checkNotNull(header, HEADER_CANNOT_BE_NULL);
		Preconditions.checkNotNull(footer, FOOTER_CANNOT_BE_NULL);
		this.playerListHeader = header.asComponent();
		this.playerListFooter = footer.asComponent();
	}

	@Override
	/**
	 * @deprecated Use modern API instead.
	 */
	@Deprecated
	public void setPlayerListHeaderFooter(@Nullable BaseComponent header, @Nullable BaseComponent footer)
	{
		this.playerListHeader = BungeeComponentSerializer.get().deserialize(new BaseComponent[]
		{header});
		this.playerListFooter = BungeeComponentSerializer.get().deserialize(new BaseComponent[]
		{footer});
	}

	@Override
	/**
	 * @deprecated Use modern API instead.
	 */
	@Deprecated(since = "1.21")
	public void setTitleTimes(int fadeInTicks, int stayTicks, int fadeOutTicks)
	{
		// No-op
	}

	@Override
	/**
	 * @deprecated Use modern API instead.
	 */
	@Deprecated
	public void setSubtitle(BaseComponent[] subtitle)
	{
		this.subtitles.add(BungeeComponentSerializer.get().deserialize(subtitle));
	}

	@Override
	/**
	 * @deprecated Use modern API instead.
	 */
	@Deprecated
	public void setSubtitle(BaseComponent subtitle)
	{
		if (subtitle != null)
		{
			this.subtitles.add(BungeeComponentSerializer.get().deserialize(new BaseComponent[]
			{subtitle}));
		}
	}

	@Override
	/**
	 * @deprecated Use modern API instead.
	 */
	@Deprecated
	public void showTitle(@Nullable BaseComponent[] title)
	{
		if (title != null)
		{
			this.titles.add(BungeeComponentSerializer.get().deserialize(title));
		}
	}

	@Override
	/**
	 * @deprecated Use modern API instead.
	 */
	@Deprecated
	public void showTitle(@Nullable BaseComponent title)
	{
		if (title != null)
		{
			this.titles.add(BungeeComponentSerializer.get().deserialize(new BaseComponent[]
			{title}));
		}
	}

	@Override
	/**
	 * @deprecated Use modern API instead.
	 */
	@Deprecated
	public void showTitle(@Nullable BaseComponent[] title, @Nullable BaseComponent[] subtitle, int fadeInTicks,
			int stayTicks, int fadeOutTicks)
	{
		this.setTitleTimes(fadeInTicks, stayTicks, fadeOutTicks);
		this.showTitle(title);
		if (subtitle != null)
		{
			this.setSubtitle(subtitle);
		}
	}

	@Override
	/**
	 * @deprecated Use modern API instead.
	 */
	@Deprecated
	public void showTitle(@Nullable BaseComponent title, @Nullable BaseComponent subtitle, int fadeInTicks,
			int stayTicks, int fadeOutTicks)
	{
		this.setTitleTimes(fadeInTicks, stayTicks, fadeOutTicks);
		this.showTitle(title);
		if (subtitle != null)
		{
			this.setSubtitle(subtitle);
		}
	}

	@Override
	public void showTitle(@NotNull net.kyori.adventure.title.Title title)
	{
		Preconditions.checkNotNull(title, "title cannot be null");
		this.titles.add(title.title());
		this.subtitles.add(title.subtitle());
	}

	@Override
	public void clearTitle()
	{
		this.titles.clear();
		this.subtitles.clear();
	}

	@Override
	public void resetTitle()
	{
		this.clearTitle();
	}

	@Override
	/**
	 * @deprecated Use modern API instead.
	 */
	@Deprecated
	public void sendTitle(@NotNull Title title)
	{
		Preconditions.checkNotNull(title, "Title is null");
	}

	@Override
	@Nullable
	public GameMode getPreviousGameMode()
	{
		return previousGamemode;
	}

	@Override
	public void incrementStatistic(@NotNull Statistic statistic)
	{
		statistics.incrementStatistic(statistic, 1);
	}

	@Override
	public void decrementStatistic(@NotNull Statistic statistic)
	{
		statistics.decrementStatistic(statistic, 1);
	}

	@Override
	public void incrementStatistic(@NotNull Statistic statistic, int amount)
	{
		statistics.incrementStatistic(statistic, amount);
	}

	@Override
	public void decrementStatistic(@NotNull Statistic statistic, int amount)
	{
		statistics.decrementStatistic(statistic, amount);
	}

	@Override
	public void setStatistic(@NotNull Statistic statistic, int newValue)
	{
		statistics.setStatistic(statistic, newValue);
	}

	@Override
	public int getStatistic(@NotNull Statistic statistic)
	{
		return statistics.getStatistic(statistic);
	}

	@Override
	public void incrementStatistic(@NotNull Statistic statistic, @NotNull Material material)
	{
		statistics.incrementStatistic(statistic, material, 1);
	}

	@Override
	public void decrementStatistic(@NotNull Statistic statistic, @NotNull Material material)
	{
		statistics.decrementStatistic(statistic, material, 1);
	}

	@Override
	public int getStatistic(@NotNull Statistic statistic, @NotNull Material material)
	{
		return statistics.getStatistic(statistic, material);
	}

	@Override
	public void incrementStatistic(@NotNull Statistic statistic, @NotNull Material material, int amount)
	{
		statistics.incrementStatistic(statistic, material, amount);
	}

	@Override
	public void decrementStatistic(@NotNull Statistic statistic, @NotNull Material material, int amount)
	{
		statistics.decrementStatistic(statistic, material, amount);
	}

	@Override
	public void setStatistic(@NotNull Statistic statistic, @NotNull Material material, int newValue)
	{
		statistics.setStatistic(statistic, material, newValue);
	}

	@Override
	public void incrementStatistic(@NotNull Statistic statistic, @NotNull EntityType entityType)
	{
		statistics.incrementStatistic(statistic, entityType, 1);
	}

	@Override
	public void decrementStatistic(@NotNull Statistic statistic, @NotNull EntityType entityType)
	{
		statistics.decrementStatistic(statistic, entityType, 1);
	}

	@Override
	public int getStatistic(@NotNull Statistic statistic, @NotNull EntityType entityType)
	{
		return statistics.getStatistic(statistic, entityType);
	}

	@Override
	public void incrementStatistic(@NotNull Statistic statistic, @NotNull EntityType entityType, int amount)
	{
		statistics.incrementStatistic(statistic, entityType, amount);
	}

	@Override
	public void decrementStatistic(@NotNull Statistic statistic, @NotNull EntityType entityType, int amount)
	{
		statistics.decrementStatistic(statistic, entityType, amount);
	}

	@Override
	public void setStatistic(@NotNull Statistic statistic, @NotNull EntityType entityType, int newValue)
	{
		statistics.setStatistic(statistic, entityType, newValue);
	}

	@Override
	public void setPlayerTime(long time, boolean relative)
	{
		this.timeOffset = time;
		this.relativeTime = relative;
	}

	@Override
	public long getPlayerTime()
	{
		return this.timeOffset;
	}

	@Override
	public long getPlayerTimeOffset()
	{
		Preconditions.checkArgument(isInWorld(), "Player is not in world.");
		WorldMock world = getWorld();
		long dayTime = world.getFullTime();
		if (isPlayerTimeRelative())
		{
			// Adds timeOffset to the current server time.
			return dayTime + this.timeOffset;
		} else
		{
			// Adds timeOffset to the beginning of this day.
			return dayTime - (dayTime % 24000) + this.timeOffset;
		}
	}

	@Override
	public boolean isPlayerTimeRelative()
	{
		return this.relativeTime;
	}

	@Override
	public void resetPlayerTime()
	{
		this.setPlayerTime(0, true);
	}

	@Override
	@Nullable
	public WeatherType getPlayerWeather()
	{
		return this.playerWeather;
	}

	@Override
	public void setPlayerWeather(@NotNull WeatherType type)
	{
		Preconditions.checkArgument(type != null, "weather type cannot be null");
		this.playerWeather = type;
	}

	@Override
	public void resetPlayerWeather()
	{
		this.playerWeather = null;
	}

	@Override
	public void giveExp(int amount)
	{
		this.exp += (float) amount / (float) this.getExpToLevel();
		setTotalExperience(this.expTotal + amount);
		while (this.exp < 0.0F)
		{
			float total = this.exp * this.getExpToLevel();
			boolean shouldContinue = this.expLevel > 0;
			this.giveExpLevels(-1);
			if (shouldContinue)
			{
				this.exp = 1.0F + (total / this.getExpToLevel());
			}
		}
		while (this.exp >= 1.0F)
		{
			this.exp = (this.exp - 1.0F) * this.getExpToLevel();
			this.giveExpLevels(1);
			this.exp /= this.getExpToLevel();
		}
	}

	@Override
	public int getExpCooldown()
	{
		return this.expCooldown;
	}

	@Override
	public void setExpCooldown(int ticks)
	{
		Preconditions.checkArgument(ticks >= 0, "Cooldown ticks must be greater than or equal to 0");
		this.expCooldown = ticks;
		PlayerExpCooldownChangeEvent event = new PlayerExpCooldownChangeEvent(this, ticks,
				PlayerExpCooldownChangeEvent.ChangeReason.PLUGIN);
		Bukkit.getPluginManager().callEvent(event);
	}

	@Override
	public void giveExpLevels(int amount)
	{
		int oldLevel = this.expLevel;
		this.expLevel += amount;
		if (this.expLevel < 0)
		{
			this.expLevel = 0;
			this.exp = 0.0F;
		}
		if (oldLevel != this.expLevel)
		{
			PlayerLevelChangeEvent event = new PlayerLevelChangeEvent(this, oldLevel, this.expLevel);
			Bukkit.getPluginManager().callEvent(event);
		}
	}

	@Override
	public float getExp()
	{
		return exp;
	}

	@Override
	public void setExp(float exp)
	{
		Preconditions.checkArgument(exp >= 0.0 && exp <= 1.0, "Experience progress must be between 0.0 and 1.0 (%s)",
				exp);
		this.exp = exp;
	}

	@Override
	public int getLevel()
	{
		return expLevel;
	}

	@Override
	public void setLevel(int level)
	{
		Preconditions.checkArgument(level >= 0, "Experience level must not be negative (%s)", level);
		this.expLevel = level;
	}

	@Override
	public int getTotalExperience()
	{
		return expTotal;
	}

	@Override
	public void setTotalExperience(int exp)
	{
		Preconditions.checkArgument(exp >= 0, "Total experience points must not be negative (%s)", exp);
		this.expTotal = exp;
	}

	@Override
	@Nullable
	public Location getRespawnLocation()
	{
		return this.respawnLocation;
	}

	@Override
	@org.jspecify.annotations.Nullable
	public Location getRespawnLocation(boolean loadLocationAndValidate)
	{
		return null;
	}

	@Override
	@Nullable
	public Location getBedSpawnLocation()
	{
		return getRespawnLocation();
	}

	@Override
	public long getLastLogin()
	{
		return this.server.getPlayerList().getLastLogin(getUniqueId());
	}

	@Override
	public long getLastSeen()
	{
		return this.server.getPlayerList().getLastSeen(getUniqueId());
	}

	@Override
	public void setRespawnLocation(@Nullable Location loc)
	{
		setRespawnLocation(loc, false);
	}

	@Override
	public void setBedSpawnLocation(@Nullable Location loc)
	{
		setBedSpawnLocation(loc, false);
	}

	@Override
	public void setBedSpawnLocation(@Nullable Location loc, boolean override)
	{
		setRespawnLocation(loc, override);
	}

	@Override
	public void setRespawnLocation(@Nullable Location loc, boolean override)
	{
		if (override || loc == null || Tag.BEDS.isTagged(loc.getBlock().getType()))
		{
			this.respawnLocation = loc;
		}
	}

	@Override
	@NotNull
	public Collection<EnderPearl> getEnderPearls()
	{
		return Collections.emptyList();
	}

	@Override
	public boolean getAllowFlight()
	{
		return allowFlight;
	}

	@Override
	public void setAllowFlight(boolean flight)
	{
		if (this.isFlying() && !flight)
		{
			flying = false;
		}
		this.allowFlight = flight;
	}

	@Override
	/**
	 * @deprecated Use modern API instead.
	 */
	@Deprecated(since = "1.12.2")
	public void hidePlayer(@NotNull Player player)
	{
		Preconditions.checkNotNull(player, PLAYER_CANNOT_BE_NULL);
		hiddenPlayersDeprecated.add(player.getUniqueId());
	}

	@Override
	public void hidePlayer(@NotNull Plugin plugin, @NotNull Player player)
	{
		Preconditions.checkNotNull(plugin, PLUGIN_CANNOT_BE_NULL);
		Preconditions.checkNotNull(player, PLAYER_CANNOT_BE_NULL);
		hiddenEntities.putIfAbsent(player.getUniqueId(), new HashSet<>());
		Set<Plugin> blockingPlugins = hiddenEntities.get(player.getUniqueId());
		blockingPlugins.add(plugin);
	}

	@Override
	/**
	 * @deprecated Use modern API instead.
	 */
	@Deprecated(since = "1.12.2")
	public void showPlayer(@NotNull Player player)
	{
		Preconditions.checkNotNull(player, PLAYER_CANNOT_BE_NULL);
		hiddenPlayersDeprecated.remove(player.getUniqueId());
	}

	@Override
	public void showPlayer(@NotNull Plugin plugin, @NotNull Player player)
	{
		Preconditions.checkNotNull(plugin, PLUGIN_CANNOT_BE_NULL);
		Preconditions.checkNotNull(player, PLAYER_CANNOT_BE_NULL);
		if (hiddenEntities.containsKey(player.getUniqueId()))
		{
			Set<Plugin> blockingPlugins = hiddenEntities.get(player.getUniqueId());
			blockingPlugins.remove(plugin);
			if (blockingPlugins.isEmpty())
			{
				hiddenEntities.remove(player.getUniqueId());
			}
		}
	}

	@Override
	public boolean canSee(@NotNull Player player)
	{
		Preconditions.checkNotNull(player, PLAYER_CANNOT_BE_NULL);
		return !hiddenEntities.containsKey(player.getUniqueId())
				&& !hiddenPlayersDeprecated.contains(player.getUniqueId());
	}

	@Override
	public void hideEntity(@NotNull Plugin plugin, @NotNull Entity entity)
	{
		Preconditions.checkNotNull(plugin, PLUGIN_CANNOT_BE_NULL);
		Preconditions.checkNotNull(entity, ENTITY_CANNOT_BE_NULL);
		hiddenEntities.putIfAbsent(entity.getUniqueId(), new HashSet<>());
		Set<Plugin> blockingPlugins = hiddenEntities.get(entity.getUniqueId());
		blockingPlugins.add(plugin);
	}

	@Override
	public void showEntity(@NotNull Plugin plugin, @NotNull Entity entity)
	{
		Preconditions.checkNotNull(plugin, PLUGIN_CANNOT_BE_NULL);
		Preconditions.checkNotNull(entity, ENTITY_CANNOT_BE_NULL);
		if (hiddenEntities.containsKey(entity.getUniqueId()))
		{
			Set<Plugin> blockingPlugins = hiddenEntities.get(entity.getUniqueId());
			blockingPlugins.remove(plugin);
			if (blockingPlugins.isEmpty())
			{
				hiddenEntities.remove(entity.getUniqueId());
			}
		}
	}

	@Override
	public boolean canSee(@NotNull Entity entity)
	{
		Preconditions.checkNotNull(entity, ENTITY_CANNOT_BE_NULL);
		return !hiddenEntities.containsKey(entity.getUniqueId());
	}

	@Override
	public boolean isFlying()
	{
		return flying;
	}

	@Override
	public void setFlying(boolean value)
	{
		if (!this.getAllowFlight() && value)
		{
			throw new IllegalArgumentException("Cannot make player fly if getAllowFlight() is false");
		}
		this.flying = value;
	}

	/**
	 * Simulates toggling flight.
	 *
	 * @param fly
	 *            Whether the player is starting to fly.
	 * @return The event.
	 */
	@Deprecated(forRemoval = true, since = "4.0")
	@NotNull
	public PlayerToggleFlightEvent simulateToggleFlight(boolean fly)
	{
		return new PlayerSimulation(this).simulateToggleFlight(fly);
	}

	@Override
	public float getFlySpeed()
	{
		return this.flySpeed;
	}

	@Override
	public void setFlySpeed(float value)
	{
		Preconditions.checkArgument(value <= 1f && value >= -1f, "Speed value (%s) need to be between -1f and 1f",
				value);
		this.flySpeed = value;
	}

	@Override
	public float getWalkSpeed()
	{
		return (float) (this.attributes.get(Attribute.MOVEMENT_SPEED).getValue() * 2);
	}

	@Override
	public void setWalkSpeed(float value)
	{
		Preconditions.checkArgument(value > -1, value + " is too low");
		Preconditions.checkArgument(value < 1, value + " is too high");
		this.attributes.get(Attribute.MOVEMENT_SPEED).setBaseValue(value / 2);
	}

	@Override
	@NotNull
	public Scoreboard getScoreboard()
	{
		return this.scoreboard;
	}

	@Override
	public void setScoreboard(@NotNull Scoreboard scoreboard)
	{
		Preconditions.checkNotNull(scoreboard, "Scoreboard cannot be null");
		this.scoreboard = scoreboard;
	}

	@Override
	public void setHealth(double health)
	{
		if (health > 0)
		{
			this.health = Math.min(health, getMaxHealth());
			return;
		}
		DamageSource dmg;
		String msg;
		if (getLastDamageCause() != null
				&& getLastDamageCause().getDamageSource().getDirectEntity() instanceof Player player)
		{
			dmg = DamageSource.builder(DamageType.PLAYER_ATTACK).build();
			setKiller(player);
			msg = getName() + " was slain by " + player.getName();
		} else
		{
			dmg = DamageSource.builder(DamageType.GENERIC).build();
			msg = getName() + " got killed";
		}
		this.health = 0;
		List<ItemStack> drops = new ArrayList<>(Arrays.asList(getInventory().getContents()));
		PlayerDeathEvent event = new PlayerDeathEvent(this, dmg, drops, 0, msg);
		if (!event.callEvent())
		{
			setKiller(null);
			this.health = event.getReviveHealth();
			this.alive = true;
		} else
		{
			// Terminate any InventoryView and the cursor item
			closeInventory();
			// Clear the Inventory if keep-inventory is not enabled
			if (Boolean.FALSE.equals(getWorld().getGameRuleValue(GameRule.KEEP_INVENTORY)))
			{
				getInventory().clear();
				// Should someone try to provoke a RespawnEvent, they will now find the
				// Inventory to be empty
			}
			setLevel(0);
			setExp(0);
			setFoodLevel(0);
			setBlocking(false);
			alive = false;
		}
	}

	@Override
	public boolean isHealthScaled()
	{
		return this.scaledHealth;
	}

	@Override
	public void setHealthScaled(boolean scale)
	{
		this.scaledHealth = scale;
	}

	@Override
	public double getHealthScale()
	{
		return this.healthScale;
	}

	@Override
	public void setHealthScale(double scale)
	{
		Preconditions.checkArgument(scale >= 0, "Must be greater than 0");
		Preconditions.checkArgument(scale != Double.NaN, scale + " is not a number!");
		// There is also too high but... what constitutes too high?
		this.healthScale = scale;
		this.scaledHealth = true;
	}

	@Override
	public void sendHealthUpdate(double health, int foodLevel, float saturationLevel)
	{
		// Pretend we sent the health update.
	}

	@Override
	public void sendHealthUpdate()
	{
		// Pretend we sent the health update.
	}

	@Override
	@Nullable
	public Entity getSpectatorTarget()
	{
		return this.spectatorTarget;
	}

	@Override
	public void setSpectatorTarget(Entity entity)
	{
		Preconditions.checkArgument(this.getGameMode() == GameMode.SPECTATOR, "Player must be in spectator mode");
		this.spectatorTarget = entity;
	}

	@Override
	/**
	 * @deprecated Use modern API instead.
	 */
	@Deprecated(since = "1.8.7")
	public void sendTitle(String title, String subtitle)
	{
		this.titles.add(LegacyComponentSerializer.legacySection().deserialize(title));
		this.subtitles.add(LegacyComponentSerializer.legacySection().deserialize(subtitle));
	}

	@Override
	/**
	 * @deprecated Use modern API instead.
	 */
	@Deprecated
	public void sendTitle(String title, String subtitle, int fadeIn, int stay, int fadeOut)
	{
		sendTitle(title, subtitle);
	}

	/**
	 * @return The next title sent to the player.
	 */
	@Nullable
	public String nextTitle()
	{
		Component next = this.titles.poll();
		return next == null ? null : LegacyComponentSerializer.legacySection().serialize(next);
	}

	/**
	 * @return The next title sent to the player as a {@link Component}.
	 */
	@Nullable
	public Component nextComponentTitle()
	{
		return this.titles.poll();
	}

	/**
	 * @return The next subtitle sent to the player.
	 */
	@Nullable
	public String nextSubTitle()
	{
		Component next = this.subtitles.poll();
		return next == null ? null : LegacyComponentSerializer.legacySection().serialize(next);
	}

	/**
	 * @return The next subtitle sent to the player as a {@link Component}.
	 */
	@Nullable
	public Component nextComponentSubTitle()
	{
		return this.subtitles.poll();
	}

	@Override
	public void showBossBar(@NotNull BossBar bar)
	{
		Preconditions.checkNotNull(bar, "Bossbar cannot be null");
		BossBarImplementation.get(bar, BossBarImplementationMock.class).playerShow(this);
		this.bossBars.add(bar);
	}

	@Override
	public void hideBossBar(@NotNull BossBar bar)
	{
		Preconditions.checkNotNull(bar, "Bossbar cannot be null");
		BossBarImplementation.get(bar, BossBarImplementationMock.class).playerHide(this);
		this.bossBars.remove(bar);
	}

	/**
	 * Gets an unmodifiable set of all active boss bars currently shown to this
	 * player. Helper method to {@link PlayerMock#activeBossBars()}.
	 *
	 * @see #activeBossBars()
	 */
	@UnmodifiableView
	@NotNull
	public Set<BossBar> getBossBars()
	{
		return Collections.unmodifiableSet(this.bossBars);
	}

	@Override
	@UnmodifiableView
	@NotNull
	public Iterable<? extends BossBar> activeBossBars()
	{
		return getBossBars();
	}

	@Override
	public void spawnParticle(@NotNull Particle particle, @NotNull Location location, int count)
	{
		this.spawnParticle(particle, location.getX(), location.getY(), location.getZ(), count);
	}

	@Override
	public void spawnParticle(@NotNull Particle particle, double x, double y, double z, int count)
	{
		this.spawnParticle(particle, x, y, z, count, null);
	}

	@Override
	public <T> void spawnParticle(@NotNull Particle particle, @NotNull Location location, int count, T data)
	{
		this.spawnParticle(particle, location.getX(), location.getY(), location.getZ(), count, data);
	}

	@Override
	public <T> void spawnParticle(@NotNull Particle particle, double x, double y, double z, int count, T data)
	{
		this.spawnParticle(particle, x, y, z, count, 0, 0, 0, data);
	}

	@Override
	public void spawnParticle(@NotNull Particle particle, @NotNull Location location, int count, double offsetX,
			double offsetY, double offsetZ)
	{
		this.spawnParticle(particle, location.getX(), location.getY(), location.getZ(), count, offsetX, offsetY,
				offsetZ);
	}

	@Override
	public void spawnParticle(@NotNull Particle particle, double x, double y, double z, int count, double offsetX,
			double offsetY, double offsetZ)
	{
		this.spawnParticle(particle, x, y, z, count, offsetX, offsetY, offsetZ, null);
	}

	@Override
	public <T> void spawnParticle(@NotNull Particle particle, @NotNull Location location, int count, double offsetX,
			double offsetY, double offsetZ, T data)
	{
		this.spawnParticle(particle, location.getX(), location.getY(), location.getZ(), count, offsetX, offsetY,
				offsetZ, data);
	}

	@Override
	public <T> void spawnParticle(@NotNull Particle particle, double x, double y, double z, int count, double offsetX,
			double offsetY, double offsetZ, T data)
	{
		this.spawnParticle(particle, x, y, z, count, offsetX, offsetY, offsetZ, 1, data);
	}

	@Override
	public void spawnParticle(@NotNull Particle particle, @NotNull Location location, int count, double offsetX,
			double offsetY, double offsetZ, double extra)
	{
		this.spawnParticle(particle, location.getX(), location.getY(), location.getZ(), count, offsetX, offsetY,
				offsetZ, extra);
	}

	@Override
	public void spawnParticle(@NotNull Particle particle, double x, double y, double z, int count, double offsetX,
			double offsetY, double offsetZ, double extra)
	{
		this.spawnParticle(particle, x, y, z, count, offsetX, offsetY, offsetZ, extra, null);
	}

	@Override
	public <T> void spawnParticle(@NotNull Particle particle, @NotNull Location location, int count, double offsetX,
			double offsetY, double offsetZ, double extra, T data)
	{
		this.spawnParticle(particle, location.getX(), location.getY(), location.getZ(), count, offsetX, offsetY,
				offsetZ, data);
	}

	@Override
	public <T> void spawnParticle(@NotNull Particle particle, double x, double y, double z, int count, double offsetX,
			double offsetY, double offsetZ, double extra, @Nullable T data)
	{
		Preconditions.checkNotNull(particle, "Particle cannot be null");
		if (data != null && !particle.getDataType().isInstance(data))
		{
			throw new IllegalArgumentException("data should be " + particle.getDataType() + " got " + data.getClass());
		}
	}

	@Override
	public <T> void spawnParticle(@NotNull Particle particle, @NotNull Location location, int count, double offsetX,
			double offsetY, double offsetZ, double extra, @Nullable T data, boolean force)
	{
		// We currently have no way of properly spawning particles, therefore the force
		// parameter is unused
		this.spawnParticle(particle, location.getX(), location.getY(), location.getZ(), count, offsetX, offsetY,
				offsetZ, data);
	}

	@Override
	public <T> void spawnParticle(@NotNull Particle particle, double x, double y, double z, int count, double offsetX,
			double offsetY, double offsetZ, double extra, @Nullable T data, boolean force)
	{
		// We currently have no way of properly spawning particles, therefore the force
		// parameter is unused
		this.spawnParticle(particle, x, y, z, count, offsetX, offsetY, offsetZ, data);
	}

	@Override
	@NotNull
	public String getLocale()
	{
		return locale().toLanguageTag();
	}

	@Override
	public boolean getAffectsSpawning()
	{
		return this.affectsSpawning;
	}

	@Override
	public void setAffectsSpawning(boolean affects)
	{
		this.affectsSpawning = affects;
	}

	@Override
	public String getPlayerListHeader()
	{
		return LegacyComponentSerializer.legacySection().serialize(this.playerListHeader);
	}

	@Override
	public void setPlayerListHeader(@Nullable String header)
	{
		this.playerListHeader = header == null ? null : LegacyComponentSerializer.legacySection().deserialize(header);
	}

	@Override
	public String getPlayerListFooter()
	{
		return LegacyComponentSerializer.legacySection().serialize(this.playerListFooter);
	}

	@Override
	public void setPlayerListFooter(@Nullable String footer)
	{
		this.playerListFooter = footer == null ? null : LegacyComponentSerializer.legacySection().deserialize(footer);
	}

	@Override
	public void setPlayerListHeaderFooter(@Nullable String header, @Nullable String footer)
	{
		this.playerListHeader = header == null ? null : LegacyComponentSerializer.legacySection().deserialize(header);
		this.playerListFooter = footer == null ? null : LegacyComponentSerializer.legacySection().deserialize(footer);
	}

	@Override
	public void updateCommands()
	{
		// NO OP, only updates commands client side, MockBukkit is only serverside
	}

	@Override
	@NotNull
	public Locale locale()
	{
		return this.locale;
	}

	@Override
	@NullMarked
	@ApiStatus.Experimental
	public void openVirtualSign(Position position, Side side)
	{
		// NO OP, only updates commands client side, MockBukkit is only serverside
	}

	@Override
	@NotNull
	public PlayerProfile getPlayerProfile()
	{
		return this.playerProfile;
	}

	@Override
	public void setPlayerProfile(@NotNull PlayerProfile profile)
	{
		Preconditions.checkNotNull(profile, "Profile cannot be null");
		this.playerProfile = profile;
	}

	@Override
	public void sendExperienceChange(float progress)
	{
		this.sendExperienceChange(progress, this.getLevel());
	}

	@Override
	public void sendExperienceChange(float progress, int level)
	{
		Preconditions.checkArgument(progress >= 0.0 && progress <= 1.0,
				"Experience progress must be between 0.0 and 1.0 (%s)", progress);
		Preconditions.checkArgument(level >= 0, "Experience level must not be negative (%s)", level);
	}

	@Override
	public void sendBlockDamage(@NotNull Location loc, float progress)
	{
		Preconditions.checkNotNull(loc, LOCATION_CANNOT_BE_NULL);
		Preconditions.checkArgument(progress >= 0.0 && progress <= 1.0,
				"progress must be between 0.0 and 1.0 (inclusive)");
	}

	@Override
	public void sendMultiBlockChange(@NotNull Map<? extends Position, BlockData> blockChanges)
	{
		Preconditions.checkNotNull(blockChanges, "Block changes cannot be null");
		// Pretend to send the packet
	}

	@Override
	public void sendMultiBlockChange(@NotNull Map<? extends Position, BlockData> blockChanges,
			boolean suppressLightUpdates)
	{
		Preconditions.checkNotNull(blockChanges, "Block changes cannot be null");
		// Pretend to send the packet
	}

	@Override
	public int getPing()
	{
		/*
		 * This PlayerMock and the ServerMock exist within the same machine, therefore
		 * there would most likely be a ping of 0ms.
		 */
		return 0;
	}

	@Override
	@SuppressWarnings("UnstableApiUsage")
	public boolean teleport(@NotNull Location location, @NotNull PlayerTeleportEvent.TeleportCause cause,
			TeleportFlag[] flags)
	{
		Preconditions.checkNotNull(location, LOCATION_CANNOT_BE_NULL);
		Preconditions.checkNotNull(location.getWorld(), "World cannot be null");
		Preconditions.checkNotNull(cause, "Cause cannot be null");
		Set<TeleportFlag.Relative> relativeArguments = EnumSet.noneOf(TeleportFlag.Relative.class);
		Set<TeleportFlag> allFlags = new HashSet<>();
		for (TeleportFlag flag : flags)
		{
			if (flag instanceof TeleportFlag.Relative relativeTeleportFlag)
			{
				relativeArguments.add(relativeTeleportFlag);
			}
			allFlags.add(flag);
		}
		boolean dismount = !allFlags.contains(TeleportFlag.EntityState.RETAIN_VEHICLE);
		boolean ignorePassengers = allFlags.contains(TeleportFlag.EntityState.RETAIN_PASSENGERS);
		location.checkFinite();
		if (isDead() || (!ignorePassengers && hasPassengers()))
		{
			return false;
		}
		if (location.getWorld() != getWorld()
				&& ((ignorePassengers && hasPassengers()) || (!dismount && isInsideVehicle())))
		{
			return false;
		}
		PlayerTeleportEvent event = new PlayerTeleportEvent(this, getLocation(), location, cause);
		if (!event.callEvent())
		{
			return false;
		}
		// Close any foreign inventory
		if (getOpenInventory().getType() != InventoryType.CRAFTING)
		{
			closeInventory(InventoryCloseEvent.Reason.TELEPORT);
		}
		World previousWorld = getWorld();
		teleportWithoutEvent(event.getTo(), cause);
		// Detect player dimension change
		if (!location.getWorld().equals(previousWorld))
		{
			new PlayerChangedWorldEvent(this, previousWorld).callEvent();
		}
		return true;
	}

	@Override
	public void sendEquipmentChange(@NotNull LivingEntity entity, @NotNull EquipmentSlot slot, @NotNull ItemStack item)
	{
		Preconditions.checkNotNull(entity, "entity must not be null");
		Preconditions.checkNotNull(slot, "slot must not be null");
		Preconditions.checkNotNull(item, "item must not be null");
		// Pretend the packet gets sent.
	}

	@Override
	public void sendEquipmentChange(@NotNull LivingEntity entity,
			@NotNull Map<EquipmentSlot, ItemStack> equipmentChanges)
	{
		equipmentChanges.forEach((slot, stack) -> sendEquipmentChange(entity, slot, stack));
	}

	@Override
	public boolean isOp()
	{
		return server.getPlayerList().getOperators().stream().anyMatch(op -> op.getPlayer() == this);
	}

	@Override
	public void setOp(boolean isOperator)
	{
		if (isOperator)
		{
			server.getPlayerList().addOperator(this.getUniqueId());
		} else
		{
			server.getPlayerList().removeOperator(this.getUniqueId());
		}
		recalculatePermissions();
	}

	@Override
	protected EntityState getEntityState()
	{
		if (this.isSneaking())
		{
			return EntityState.SNEAKING;
		}
		if (this.isGliding())
		{
			return EntityState.GLIDING;
		}
		if (this.isSwimming())
		{
			return EntityState.SWIMMING;
		}
		if (this.isSleeping())
		{
			return EntityState.SLEEPING;
		}
		return super.getEntityState();
	}

	@Override
	public void remove()
	{
		throw new UnsupportedOperationException("Players can't be removed with this method, use kick() instead");
	}

	@Override
	public Player.@NotNull Spigot spigot()
	{
		return playerSpigotMock;
	}

	/**
	 * Mock implementation of a {@link Player.Spigot}.
	 */
	public class PlayerSpigotMock extends Player.Spigot
	{

		@Override
		/**
		 * @deprecated Use modern API instead.
		 */
		@Deprecated(forRemoval = true, since = "4.0")
		public void sendMessage(@NotNull BaseComponent component)
		{
			this.sendMessage(ChatMessageType.SYSTEM, component);
		}

		@Override
		/**
		 * @deprecated Use modern API instead.
		 */
		@Deprecated(forRemoval = true, since = "4.0")
		public void sendMessage(@NotNull BaseComponent... components)
		{
			this.sendMessage(ChatMessageType.SYSTEM, components);
		}

		@Override
		/**
		 * @deprecated Use modern API instead.
		 */
		@Deprecated(forRemoval = true, since = "4.0")
		public void sendMessage(@Nullable UUID sender, @NotNull BaseComponent component)
		{
			this.sendMessage(ChatMessageType.CHAT, sender, component);
		}

		@Override
		/**
		 * @deprecated Use modern API instead.
		 */
		@Deprecated(forRemoval = true, since = "4.0")
		public void sendMessage(@Nullable UUID sender, @NotNull BaseComponent... components)
		{
			this.sendMessage(ChatMessageType.CHAT, sender, components);
		}

		@Override
		/**
		 * @deprecated Use modern API instead.
		 */
		@Deprecated(forRemoval = true, since = "4.0")
		public void sendMessage(@NotNull ChatMessageType position, @NotNull BaseComponent component)
		{
			this.sendMessage(position, component);
		}

		@Override
		/**
		 * @deprecated Use modern API instead.
		 */
		@Deprecated(forRemoval = true, since = "4.0")
		public void sendMessage(@NotNull ChatMessageType position, @NotNull BaseComponent... components)
		{
			this.sendMessage(position, null, components);
		}

		@Override
		/**
		 * @deprecated Use modern API instead.
		 */
		@Deprecated(forRemoval = true, since = "4.0")
		public void sendMessage(@NotNull ChatMessageType position, @Nullable UUID sender,
				@NotNull BaseComponent component)
		{
			this.sendMessage(position, sender, component);
		}

		@Override
		/**
		 * @deprecated Use modern API instead.
		 */
		@Deprecated(forRemoval = true, since = "4.0")
		public void sendMessage(@NotNull ChatMessageType position, @Nullable UUID sender,
				@NotNull BaseComponent... components)
		{
			Preconditions.checkNotNull(position, "Position must not be null");
			Preconditions.checkNotNull(components, "Component must not be null");
			Component comp = BungeeComponentSerializer.get().deserialize(components);
			String serialized = LegacyComponentSerializer.legacySection().serialize(comp);
			comp = LegacyComponentSerializer.legacySection().deserialize(serialized);
			PlayerMock.this.sendMessage(sender == null ? Identity.nil() : Identity.identity(sender), comp);
		}
	}

	/**
	 * Sets player locale
	 *
	 * @param locale
	 *            the locale
	 */
	public void setLocale(@NotNull Locale locale)
	{
		Preconditions.checkNotNull(locale, "locale cannot be null");
		if (locale.equals(this.locale))
		{
			return;
		}
		PlayerLocaleChangeEvent event = new PlayerLocaleChangeEvent(this, locale.toLanguageTag());
		Bukkit.getPluginManager().callEvent(event);
		this.locale = locale;
	}

	@Override
	public int getDeathScreenScore()
	{
		return this.deathScreenScore;
	}

	@Override
	public void setDeathScreenScore(int deathScreenScore)
	{
		this.deathScreenScore = deathScreenScore;
	}

}
