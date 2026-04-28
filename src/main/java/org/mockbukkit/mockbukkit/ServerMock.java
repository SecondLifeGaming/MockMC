package org.mockbukkit.mockbukkit;

import com.destroystokyo.paper.event.player.PlayerConnectionCloseEvent;
import com.destroystokyo.paper.profile.PlayerProfile;
import com.google.common.base.Preconditions;
import io.papermc.paper.math.Position;
import io.papermc.paper.plugin.lifecycle.event.registrar.ReloadableRegistrarEvent;
import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents;
import io.papermc.paper.registry.RegistryAccess;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import java.util.UUID;
import java.util.Date;
import java.net.InetAddress;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;
import org.bukkit.BanEntry;
import org.bukkit.BanList.Type;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.GameMode;
import org.bukkit.Keyed;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.OfflinePlayer;
import org.bukkit.Registry;
import org.bukkit.Server;
import org.bukkit.Warning.WarningState;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.block.banner.Pattern;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.spawner.SpawnRule;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.configuration.serialization.ConfigurationSerialization;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.SpawnCategory;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.server.MapInitializeEvent;
import org.bukkit.event.server.ServerLoadEvent;
import org.bukkit.event.world.WorldUnloadEvent;
import org.bukkit.generator.ChunkGenerator.ChunkData;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.StandardMessenger;
import org.bukkit.potion.PotionEffect;
import org.bukkit.scoreboard.Criteria;
import org.bukkit.structure.StructureManager;
import org.bukkit.util.BlockVector;
import org.bukkit.util.BoundingBox;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.mockbukkit.mockbukkit.block.data.BlockDataMock;
import org.mockbukkit.mockbukkit.block.data.BlockDataMockFactory;
import org.mockbukkit.mockbukkit.command.CommandMapMock;
import org.mockbukkit.mockbukkit.command.CommandResult;
import org.mockbukkit.mockbukkit.command.ConsoleCommandSenderMock;
import org.mockbukkit.mockbukkit.command.MessageTarget;
import org.mockbukkit.mockbukkit.command.brigadier.PaperCommandsMock;
import org.mockbukkit.mockbukkit.configuration.ServerConfiguration;
import org.mockbukkit.mockbukkit.entity.EntityMock;
import org.mockbukkit.mockbukkit.entity.OfflinePlayerMock;
import org.mockbukkit.mockbukkit.entity.PlayerMock;
import org.mockbukkit.mockbukkit.entity.PlayerMockFactory;
import org.mockbukkit.mockbukkit.exception.PluginIOException;
import org.mockbukkit.mockbukkit.inventory.ItemStackMock;
import org.mockbukkit.mockbukkit.inventory.RecipeType;
import org.mockbukkit.mockbukkit.inventory.SerializableMeta;
import org.mockbukkit.mockbukkit.inventory.meta.components.CustomModelDataComponentMock;
import org.mockbukkit.mockbukkit.inventory.meta.components.EquippableComponentMock;
import org.mockbukkit.mockbukkit.inventory.meta.components.FoodComponentMock;
import org.mockbukkit.mockbukkit.inventory.meta.components.JukeboxPlayableComponentMock;
import org.mockbukkit.mockbukkit.inventory.meta.components.ToolComponentMock;
import org.mockbukkit.mockbukkit.inventory.meta.components.UseCooldownComponentMock;
import org.mockbukkit.mockbukkit.map.MapViewMock;
import org.mockbukkit.mockbukkit.plugin.PluginManagerMock;
import org.mockbukkit.mockbukkit.plugin.lifecycle.event.LifecycleEventRunnerMock;
import org.mockbukkit.mockbukkit.profile.PlayerProfileMock;
import org.mockbukkit.mockbukkit.scoreboard.CriteriaMock;
import org.mockbukkit.mockbukkit.scheduler.BukkitSchedulerMock;
import org.mockbukkit.mockbukkit.structure.StructureManagerMock;
import org.mockbukkit.mockbukkit.tags.TagsMock;
import org.mockbukkit.mockbukkit.util.UnsafeValuesMock;
import org.mockbukkit.mockbukkit.world.ChunkDataMock;
import org.mockbukkit.mockbukkit.world.WorldMock;
import org.spigotmc.event.player.PlayerSpawnLocationEvent;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * Mock implementation of a {@link Server} and {@link Server.Spigot}.
 */
@SuppressWarnings(
{"deprecation", "removal", "unchecked"})
public class ServerMock extends ServerMockBase
{
	@Override
	@NotNull
	public BukkitSchedulerMock getScheduler()
	{
		return (BukkitSchedulerMock) super.getScheduler();
	}

	private Component motd = Component.text("A Minecraft Server");

	private static final Component NO_PERMISSION = Component.text(
			"I'm sorry, but you do not have permission to perform this command. Please contact the server administrators if you believe that this is in error.",
			NamedTextColor.RED);

	private final Logger logger = Logger.getLogger("ServerMock");

	private final Thread mainThread = Thread.currentThread();

	private final UnsafeValuesMock unsafe = new UnsafeValuesMock();

	private final Set<EntityMock> entities = new HashSet<>();

	private static final String PLAYER_ADD = "player add";
	private static final String COMMAND_DISPATCH = "command dispatch";

	private final List<World> worlds = new ArrayList<>();

	private final StructureManager structureManager = new StructureManagerMock();

	private final PlayerMockFactory playerFactory = new PlayerMockFactory(this);

	private final PluginManagerMock pluginManager = new PluginManagerMock(this);

	private final CommandMapMock commandMap;

	private CachedServerIconMock serverIcon = new CachedServerIconMock(null);

	private ConsoleCommandSenderMock consoleSender;

	private int spawnRadius = 16;

	@NotNull
	private WarningState warningState = WarningState.DEFAULT;

	@NotNull
	private String respawnWorldName = unsafe.getMainLevelName();

	@NotNull
	private final ServerConfiguration serverConfiguration = new ServerConfiguration();

	private int pauseWhenEmptyTime = 60;

	private GameMode defaultGameMode = GameMode.SURVIVAL;
	private boolean commandsInitialized = false;

	/**
	 * Constructs a new ServerMock and sets it up. Does <b>NOT</b> set the server
	 * returned from {@link Bukkit#getServer()}.
	 */
	public ServerMock()
	{
		this(new BukkitSchedulerMock());
	}

	private ServerMock(BukkitSchedulerMock scheduler)
	{
		super(scheduler);
		org.mockbukkit.mockbukkit.configuration.ConfigurationRegistrar.register();
		TagsMock.loadDefaultTags(this, true);
		PaperCommandsMock.INSTANCE.newDispatcher();
		this.commandMap = new CommandMapMock(this);
		try
		{
			InputStream stream = getClass().getClassLoader().getResourceAsStream("logger.properties");
			LogManager.getLogManager().readConfiguration(stream);
		} catch (IOException _)
		{
			logger.warning("Could not load file logger.properties");
		}
		logger.setLevel(Level.ALL);
	}

	/**
	 * Checks if we are on the main thread. The main thread is the thread used to
	 * create this instance of the mock server.
	 *
	 * @return {@code true} if we are on the main thread, {@code false} if we are
	 *         running on a different thread.
	 */
	public boolean isOnMainThread()
	{
		return mainThread.equals(Thread.currentThread());
	}

	/**
	 * Registers an entity so that the server can track it more easily. Should only
	 * be used internally.
	 *
	 * @param entity
	 *            The entity to register
	 */
	public void registerEntity(@NotNull EntityMock entity)
	{
		Preconditions.checkNotNull(entity, "Entity cannot be null");
		AsyncCatcher.catchOp("entity add");
		entities.add(entity);
	}

	/**
	 * Unregisters an entity from the server.
	 *
	 * @param entity
	 *            The entity to unregister.
	 */
	public void unregisterEntity(@NotNull EntityMock entity)
	{
		Preconditions.checkNotNull(entity, "Entity cannot be null");
		Preconditions.checkArgument(!entity.isValid(), "Entity is not marked for removal");
		AsyncCatcher.catchOp("entity remove");
		entities.remove(entity);
	}

	/**
	 * Returns a set of entities that exist on the server instance.
	 *
	 * @return A set of entities that exist on this server instance.
	 */
	@NotNull
	public Set<EntityMock> getEntities()
	{
		return Collections.unmodifiableSet(entities);
	}

	/**
	 * Add a specific player to the set.
	 *
	 * @param player
	 *            The player to add.
	 */
	public void addPlayer(@NotNull PlayerMock player)
	{
		AsyncCatcher.catchOp(PLAYER_ADD);
		playerList.addPlayer(player);
		CountDownLatch conditionLatch = new CountDownLatch(1);
		InetSocketAddress address = player.getAddress();
		AsyncPlayerPreLoginEvent preLoginEvent = new AsyncPlayerPreLoginEvent(player.getName(), address.getAddress(),
				player.getUniqueId());
		getPluginManager().callEventAsynchronously(preLoginEvent, _ -> conditionLatch.countDown());
		try
		{
			conditionLatch.await();
			if (preLoginEvent.getLoginResult() != AsyncPlayerPreLoginEvent.Result.ALLOWED)
			{
				PlayerConnectionCloseEvent playerConnectionCloseEvent = new PlayerConnectionCloseEvent(
						player.getUniqueId(), player.getName(), player.getAddress().getAddress(), false);
				getPluginManager().callEvent(playerConnectionCloseEvent);
				playerList.disconnectPlayer(player);
				return;
			}
		} catch (InterruptedException e)
		{
			getLogger().severe("Interrupted while waiting for AsyncPlayerPreLoginEvent! "
					+ (StringUtils.isEmpty(e.getMessage()) ? "" : e.getMessage()));
			Thread.currentThread().interrupt();
		}
		PlayerLoginEvent playerLoginEvent = new PlayerLoginEvent(player, address.getHostString(), address.getAddress());
		Bukkit.getPluginManager().callEvent(playerLoginEvent);
		if (playerLoginEvent.getResult() != PlayerLoginEvent.Result.ALLOWED)
		{
			PlayerConnectionCloseEvent playerConnectionCloseEvent = new PlayerConnectionCloseEvent(player.getUniqueId(),
					player.getName(), player.getAddress().getAddress(), false);
			getPluginManager().callEvent(playerConnectionCloseEvent);
			playerList.disconnectPlayer(player);
			return;
		}
		Component joinMessage = MiniMessage.miniMessage().deserialize("<name> has joined the Server!",
				Placeholder.component("name", player.displayName()));
		PlayerJoinEvent playerJoinEvent = new PlayerJoinEvent(player, joinMessage);
		Bukkit.getPluginManager().callEvent(playerJoinEvent);
		if (hasWhitelist() && !getWhitelistedPlayers().contains(player))
		{
			PlayerConnectionCloseEvent playerConnectionCloseEvent = new PlayerConnectionCloseEvent(player.getUniqueId(),
					player.getName(), player.getAddress().getAddress(), false);
			getPluginManager().callEvent(playerConnectionCloseEvent);
			playerList.disconnectPlayer(player);
			return;
		}
		PlayerSpawnLocationEvent playerSpawnLocationEvent = new PlayerSpawnLocationEvent(player, player.getLocation());
		getPluginManager().callEvent(playerSpawnLocationEvent);
		player.setLocation(playerSpawnLocationEvent.getSpawnLocation());
		registerEntity(player);
	}

	/**
	 * Creates a random player and adds it.
	 *
	 * @return The player that was added.
	 */
	@NotNull
	public PlayerMock addPlayer()
	{
		AsyncCatcher.catchOp(PLAYER_ADD);
		PlayerMock player = playerFactory.createRandomPlayer();
		addPlayer(player);
		return player;
	}

	/**
	 * Creates a player with a given name and adds it.
	 *
	 * @param name
	 *            The name to give to the player.
	 * @return The added player.
	 */
	@NotNull
	public PlayerMock addPlayer(@NotNull String name)
	{
		AsyncCatcher.catchOp(PLAYER_ADD);
		PlayerMock player = new PlayerMock(this, name, UUID.randomUUID());
		addPlayer(player);
		return player;
	}

	/**
	 * Set the numbers of mock players that are on this server. Note that it will
	 * remove all players that are already on this server.
	 *
	 * @param num
	 *            The number of players that are on this server.
	 */
	public void setPlayers(int num)
	{
		AsyncCatcher.catchOp("set players");
		playerList.clearOnlinePlayers();
		for (int i = 0; i < num; i++)
		{
			addPlayer();
		}
	}

	/**
	 * Set the numbers of mock offline players that are on this server. Note that
	 * even players that are online are also considered offline player because an
	 * {@link OfflinePlayer} really just refers to anyone that has at some point in
	 * time played on the server.
	 *
	 * @param num
	 *            The number of players that are on this server.
	 */
	public void setOfflinePlayers(int num)
	{
		AsyncCatcher.catchOp("set offline players");
		playerList.clearOfflinePlayers();
		for (PlayerMock player : getOnlinePlayers())
		{
			playerList.addPlayer(player);
		}
		for (int i = 0; i < num; i++)
		{
			OfflinePlayer player = playerFactory.createRandomOfflinePlayer();
			playerList.addOfflinePlayer(player);
		}
	}

	/**
	 * Get a specific mock player. A player's number will never change between
	 * invocations of {@link #setPlayers(int)}.
	 *
	 * @param num
	 *            The number of the player to retrieve.
	 * @return The chosen player.
	 */
	@NotNull
	public PlayerMock getPlayer(int num)
	{
		return playerList.getPlayer(num);
	}

	/**
	 * Returns the {@link PlayerListMock} instance that is used by this server.
	 *
	 * @return The {@link PlayerListMock} instance.
	 */
	@NotNull
	public PlayerListMock getPlayerList()
	{
		return playerList;
	}

	@Override
	@Nullable
	public UUID getPlayerUniqueId(@NotNull String playerName)
	{
		return playerList.getOfflinePlayer(playerName).getUniqueId();
	}

	/**
	 * Adds a very simple super flat world with a given name.
	 *
	 * @param name
	 *            The name to give to the world.
	 * @return The {@link WorldMock} that has been created.
	 */
	@NotNull
	public WorldMock addSimpleWorld(String name)
	{
		AsyncCatcher.catchOp("world creation");
		WorldMock world = new WorldMock();
		world.setName(name);
		worlds.add(world);
		return world;
	}

	/**
	 * Adds the given mocked world to this server.
	 *
	 * @param world
	 *            The world to add.
	 */
	public void addWorld(WorldMock world)
	{
		AsyncCatcher.catchOp("world add");
		worlds.add(world);
	}

	/**
	 * Removes a mocked world from this server.
	 *
	 * @param world
	 *            The world to remove.
	 * @return true if the world was removed, otherwise false.
	 */
	public boolean removeWorld(WorldMock world)
	{
		AsyncCatcher.catchOp("world remove");
		return worlds.remove(world);
	}

	/**
	 * Executes a command as the console.
	 *
	 * @param command
	 *            The command to execute.
	 * @param args
	 *            The arguments to pass to the commands.
	 * @return The value returned by {@link Command#execute}.
	 */
	@NotNull
	public CommandResult executeConsole(@NotNull Command command, String... args)
	{
		return execute(command, getConsoleSender(), args);
	}

	/**
	 * Executes a command as the console.
	 *
	 * @param command
	 *            The command to execute.
	 * @param args
	 *            The arguments to pass to the commands.
	 * @return The value returned by {@link Command#execute}.
	 */
	@NotNull
	public CommandResult executeConsole(@NotNull String command, String... args)
	{
		return executeConsole(getCommandMap().getCommand(command), args);
	}

	/**
	 * Executes a command as a player.
	 *
	 * @param command
	 *            The command to execute.
	 * @param args
	 *            The arguments to pass to the commands.
	 * @return The value returned by {@link Command#execute}.
	 */
	@NotNull
	public CommandResult executePlayer(@NotNull Command command, String... args)
	{
		AsyncCatcher.catchOp(COMMAND_DISPATCH);
		if (playerList.isSomeoneOnline())
		{
			return execute(command, getPlayer(0), args);
		} else
		{
			throw new IllegalStateException("Need at least one player to run the command");
		}
	}

	/**
	 * Executes a command as a player.
	 *
	 * @param command
	 *            The command to execute.
	 * @param args
	 *            The arguments to pass to the commands.
	 * @return The value returned by {@link Command#execute}.
	 */
	@NotNull
	public CommandResult executePlayer(@NotNull String command, String... args)
	{
		return executePlayer(getCommandMap().getCommand(command), args);
	}

	/**
	 * Executes a command.
	 *
	 * @param command
	 *            The command to execute.
	 * @param sender
	 *            The person that executed the command.
	 * @param args
	 *            The arguments to pass to the commands.
	 * @return The value returned by {@link Command#execute}.
	 */
	@NotNull
	public CommandResult execute(@NotNull Command command, CommandSender sender, String... args)
	{
		AsyncCatcher.catchOp(COMMAND_DISPATCH);
		if (!(sender instanceof MessageTarget))
		{
			throw new IllegalArgumentException("Only a MessageTarget can be the sender of the command");
		}
		boolean status = command.execute(sender, command.getName(), args);
		return new CommandResult(status, (MessageTarget) sender);
	}

	/**
	 * Executes a command.
	 *
	 * @param command
	 *            The command to execute.
	 * @param sender
	 *            The person that executed the command.
	 * @param args
	 *            The arguments to pass to the commands.
	 * @return The value returned by {@link Command#execute}.
	 */
	@NotNull
	public CommandResult execute(@NotNull String command, CommandSender sender, String... args)
	{
		AsyncCatcher.catchOp(COMMAND_DISPATCH);
		return execute(getCommandMap().getCommand(command), sender, args);
	}

	@Override
	@NotNull
	public String getName()
	{
		return "ServerMock";
	}

	@Override
	@NotNull
	public String getVersion()
	{
		return String.format("MockBukkit (MC: %s)", getMinecraftVersion());
	}

	@Override
	@NotNull
	public String getBukkitVersion()
	{
		return "1.21.1-R0.1-SNAPSHOT";
	}

	@Override
	@NotNull
	public String getMinecraftVersion()
	{
		return "1.21.1";
	}

	@Override
	@NotNull
	public Collection<? extends PlayerMock> getOnlinePlayers()
	{
		return playerList.getOnlinePlayers();
	}

	@Override
	public OfflinePlayer @NotNull [] getOfflinePlayers()
	{
		return playerList.getOfflinePlayers();
	}

	@Override
	@Nullable
	public OfflinePlayer getOfflinePlayerIfCached(@NotNull String name)
	{
		return playerList.getOfflinePlayerIfCached(name);
	}

	@Override
	public Player getPlayer(@NotNull String name)
	{
		return playerList.getPlayer(name);
	}

	@Override
	public Player getPlayerExact(@NotNull String name)
	{
		return playerList.getPlayerExact(name);
	}

	@Override
	@NotNull
	public List<Player> matchPlayer(@NotNull String name)
	{
		return playerList.matchPlayer(name);
	}

	@Override
	public Player getPlayer(@NotNull UUID id)
	{
		return playerList.getPlayer(id);
	}

	@Override
	public @NotNull PluginManagerMock getPluginManager()
	{
		return pluginManager;
	}

	@Override
	@NotNull
	public Iterator<org.bukkit.advancement.Advancement> advancementIterator()
	{
		return Collections.emptyIterator();
	}

	@Override
	@NotNull
	public CommandMapMock getCommandMap()
	{
		return commandMap;
	}

	@Override
	public PluginCommand getPluginCommand(@NotNull String name)
	{
		Command command = getCommandMap().getCommand(name);
		return command instanceof PluginCommand pluginCommand ? pluginCommand : null;
	}

	@Override
	@NotNull
	public Logger getLogger()
	{
		return logger;
	}

	@Override
	@NotNull
	public ConsoleCommandSenderMock getConsoleSender()
	{
		if (consoleSender == null)
		{
			consoleSender = new ConsoleCommandSenderMock();
		}
		return consoleSender;
	}

	/**
	 * Creates an inventory with the provided parameters.
	 *
	 * @param owner
	 *            The holder of the inventory.
	 * @param type
	 *            The type of the inventory.
	 * @param title
	 *            The title of the inventory.
	 */
	@Override
	@NotNull
	public List<World> getWorlds()
	{
		return new ArrayList<>(worlds);
	}

	@Override
	public World getWorld(String name)
	{
		return worlds.stream().filter(world -> world.getName().equals(name)).findAny().orElse(null);
	}

	@Override
	public World getWorld(UUID uid)
	{
		return worlds.stream().filter(world -> world.getUID().equals(uid)).findAny().orElse(null);
	}

	@Override
	@Nullable
	public World getWorld(@NotNull NamespacedKey worldKey)
	{
		return worlds.stream().filter(world -> world.getName().equals(worldKey.getKey())).findAny().orElse(null);
	}

	@Override
	@Nullable
	public World getWorld(@NotNull Key key)
	{
		return worlds.stream().filter(world -> world.getName().equals(key.value())).findAny().orElse(null);
	}

	@Override
	public int getMaxPlayers()
	{
		return playerList.getMaxPlayers();
	}

	@Override
	public void setMaxPlayers(int maxPlayers)
	{
		playerList.setMaxPlayers(maxPlayers);
	}

	@Override
	public void banIP(@NotNull String address)
	{
		this.playerList.getIPBans().addBan(address, null, null, null);
	}

	@Override
	public void unbanIP(@NotNull String address)
	{
		this.playerList.getIPBans().pardon(address);
	}

	@Override
	public void banIP(@NotNull InetAddress address)
	{
		Preconditions.checkNotNull(address, "Address cannot be null");
		this.playerList.getIPBans().addBan(address, null, (Date) null, null);
	}

	@Override
	public void unbanIP(@NotNull InetAddress address)
	{
		Preconditions.checkNotNull(address, "Address cannot be null");
		this.playerList.getIPBans().pardon(address);
	}

	@Override
	@NotNull
	public Set<OfflinePlayer> getOperators()
	{
		return playerList.getOperators();
	}

	@Override
	@NotNull
	public GameMode getDefaultGameMode()
	{
		return this.defaultGameMode;
	}

	@Override
	public void setDefaultGameMode(GameMode mode)
	{
		this.defaultGameMode = mode;
	}

	/**
	 * @deprecated Use {@link #broadcast(Component)} instead.
	 */
	@Override
	@Deprecated(since = "1.21")
	public int broadcastMessage(@NotNull String message)
	{
		Preconditions.checkNotNull(message, "message cannot be null");
		return this.broadcast(LegacyComponentSerializer.legacySection().deserialize(message));
	}

	/**
	 * @deprecated Use {@link #broadcast(Component, String)} instead.
	 */
	@Override
	@Deprecated(since = "1.21")
	public int broadcast(@NotNull String message, @NotNull String permission)
	{
		Preconditions.checkNotNull(message, "message cannot be null");
		Preconditions.checkNotNull(permission, "permission cannot be null");
		return this.broadcast(LegacyComponentSerializer.legacySection().deserialize(message), permission);
	}

	@Override
	public int broadcast(@NotNull Component message)
	{
		Collection<? extends PlayerMock> players = getOnlinePlayers();
		for (Player player : players)
		{
			player.sendMessage(message);
		}
		return players.size();
	}

	@Override
	public int broadcast(@NotNull Component message, @NotNull String permission)
	{
		Collection<? extends PlayerMock> players = getOnlinePlayers();
		int count = 0;
		for (Player player : players)
		{
			if (player.hasPermission(permission))
			{
				player.sendMessage(message);
				count++;
			}
		}
		return count;
	}

	/**
	 * Registers any classes that are serializable with the
	 * ConfigurationSerializable system of Bukkit.
	 */
	public static void registerSerializables()
	{
		ConfigurationSerialization.registerClass(Vector.class);
		ConfigurationSerialization.registerClass(BlockVector.class);
		ConfigurationSerialization.registerClass(ItemStack.class);
		ConfigurationSerialization.registerClass(ItemStackMock.class);
		ConfigurationSerialization.registerClass(Color.class);
		ConfigurationSerialization.registerClass(PotionEffect.class);
		ConfigurationSerialization.registerClass(FireworkEffect.class);
		ConfigurationSerialization.registerClass(Pattern.class);
		ConfigurationSerialization.registerClass(BoundingBox.class);
		ConfigurationSerialization.registerClass(Location.class);
		ConfigurationSerialization.registerClass(AttributeModifier.class);
		ConfigurationSerialization.registerClass(SpawnRule.class);
		ConfigurationSerialization.registerClass(PlayerProfileMock.class);
		ConfigurationSerialization.registerClass(OfflinePlayerMock.class);
		registerItemSerializables();
	}

	/**
	 * Register the {@link ConfigurationSerialization} associated with items. For
	 * more details check the static method, in <code>CraftItemFactory.class</code>
	 * at Paper project.
	 */
	private static void registerItemSerializables()
	{
		ConfigurationSerialization.registerClass(SerializableMeta.class);
		ConfigurationSerialization.registerClass(CustomModelDataComponentMock.class);
		ConfigurationSerialization.registerClass(EquippableComponentMock.class);
		ConfigurationSerialization.registerClass(FoodComponentMock.class);
		ConfigurationSerialization.registerClass(ToolComponentMock.class);
		ConfigurationSerialization.registerClass(ToolComponentMock.ToolRuleMock.class);
		ConfigurationSerialization.registerClass(JukeboxPlayableComponentMock.class);
		ConfigurationSerialization.registerClass(UseCooldownComponentMock.class);
	}

	@Override
	public boolean addRecipe(Recipe recipe)
	{
		Preconditions.checkNotNull(recipe, "recipe cannot be null");
		return addRecipe(recipe, false);
	}

	@Override
	public boolean addRecipe(@Nullable Recipe recipe, boolean resendRecipes)
	{
		AsyncCatcher.catchOp("Recipe add");
		if (recipe == null)
		{
			return false;
		}
		// Pretend we sent the packet if resendRecipes is true
		return this.recipeManager.addRecipe(RecipeType.CRAFTING, recipe);
	}

	@Override
	@NotNull
	public List<Recipe> getRecipesFor(@NotNull ItemStack item)
	{
		Preconditions.checkNotNull(item, "item cannot be null");
		return this.recipeManager.getRecipesFor(RecipeType.CRAFTING, item);
	}

	@Nullable
	@Override
	public Recipe getRecipe(@NotNull NamespacedKey key)
	{
		Preconditions.checkNotNull(key, "key cannot be null");
		return this.recipeManager.getRecipeByKey(RecipeType.CRAFTING, key);
	}

	@Override
	@Nullable
	public Recipe getCraftingRecipe(@NotNull ItemStack[] craftingMatrix, @NotNull World world)
	{
		Preconditions.checkArgument(craftingMatrix != null, "craftingMatrix must not be null");
		Preconditions.checkArgument(craftingMatrix.length == 9, "craftingMatrix must be an array of length 9");
		Preconditions.checkArgument(world != null, "world must not be null");
		return this.recipeManager.getCraftingRecipe(craftingMatrix);
	}

	@Override
	@NotNull
	public ItemStack craftItem(@NotNull ItemStack[] craftingMatrix, @NotNull World world)
	{
		@Nullable
		Recipe recipe = getCraftingRecipe(craftingMatrix, world);
		if (recipe == null)
		{
			return ItemStack.empty();
		}
		return recipe.getResult();
	}

	@Override
	public boolean removeRecipe(@NotNull NamespacedKey key)
	{
		return removeRecipe(key, false);
	}

	@Override
	public boolean removeRecipe(@NotNull NamespacedKey key, boolean resendRecipes)
	{
		Preconditions.checkNotNull(key, "key cannot be null");
		Iterator<Recipe> iterator = recipeIterator();
		while (iterator.hasNext())
		{
			Recipe recipe = iterator.next();
			// Seriously why can't the Recipe interface itself just extend Keyed...
			if (recipe instanceof Keyed keyed && keyed.getKey().equals(key))
			{
				iterator.remove();
				return true;
			}
		}
		return false;
	}

	@Override
	@NotNull
	public Iterator<Recipe> recipeIterator()
	{
		return this.recipeManager.getRecipes(RecipeType.CRAFTING).iterator();
	}

	@Override
	public void clearRecipes()
	{
		this.recipeManager.clearRecipes(RecipeType.CRAFTING);
	}

	@Override
	public boolean dispatchCommand(@NotNull CommandSender sender, @NotNull String commandLine)
	{
		AsyncCatcher.catchOp(COMMAND_DISPATCH);
		if (!commandsInitialized)
		{
			LifecycleEventRunnerMock.INSTANCE.callReloadableRegistrarEvent(LifecycleEvents.COMMANDS,
					PaperCommandsMock.INSTANCE, Plugin.class, ReloadableRegistrarEvent.Cause.INITIAL);
			this.commandsInitialized = true;
		}
		if (sender instanceof Player player)
		{
			PlayerCommandPreprocessEvent event = new PlayerCommandPreprocessEvent(player, commandLine);
			pluginManager.callEvent(event);
			if (event.isCancelled())
			{
				return true;
			}
			commandLine = event.getMessage();
		}
		String[] commands = commandLine.split(" ");
		String commandLabel = commands[0];
		String[] args = Arrays.copyOfRange(commands, 1, commands.length);
		Command command = getCommandMap().getCommand(commandLabel);
		if (command != null)
		{
			return command.execute(sender, commandLabel, args);
		} else
		{
			return false;
		}
	}

	/**
	 * Gets the tab completion result for a command.
	 *
	 * @param sender
	 *            The command sender.
	 * @param commandLine
	 *            The command string, without a leading slash.
	 * @return The tab completion result, or an empty list.
	 */
	@NotNull
	public List<String> getCommandTabComplete(@NotNull CommandSender sender, @NotNull String commandLine)
	{
		AsyncCatcher.catchOp("command tabcomplete");
		int idx = commandLine.indexOf(' ');
		String commandLabel = commandLine.substring(0, idx);
		String[] args = commandLine.substring(idx + 1).split(" ", -1);
		Command command = getCommandMap().getCommand(commandLabel);
		if (command != null)
		{
			return command.tabComplete(sender, commandLabel, args);
		} else
		{
			return Collections.emptyList();
		}
	}

	@Override
	public void sendPluginMessage(@NotNull Plugin source, @NotNull String channel, byte[] message)
	{
		StandardMessenger.validatePluginMessage(this.getMessenger(), source, channel, message);
		for (Player player : this.getOnlinePlayers())
		{
			player.sendPluginMessage(source, channel, message);
		}
	}

	@Override
	@NotNull
	public Set<String> getListeningPluginChannels()
	{
		Set<String> result = new HashSet<>();
		for (Player player : this.getOnlinePlayers())
		{
			result.addAll(player.getListeningPluginChannels());
		}
		return result;
	}

	@Override
	public int getPort()
	{
		return this.serverConfiguration.getServerPort();
	}

	/**
	 * Sets the server listen port.
	 *
	 * @param port
	 *            The server listen port.
	 * @see ServerMock#getPort()
	 */
	public void setPort(int port)
	{
		this.serverConfiguration.setServerPort(port);
	}

	@Override
	public int getViewDistance()
	{
		return this.serverConfiguration.getViewDistance();
	}

	/**
	 * Sets the global view distance for all players.
	 *
	 * @param viewDistance
	 *            The new view distance.
	 * @see ServerMock#getViewDistance()
	 */
	public void setViewDistance(int viewDistance)
	{
		this.serverConfiguration.setViewDistance(viewDistance);
	}

	@Override
	@NotNull
	public String getIp()
	{
		return this.serverConfiguration.getServerIp();
	}

	/**
	 * Sets the server listen IP.
	 *
	 * @param serverIp
	 *            The server listen IP.
	 * @see ServerMock#getIp()
	 */
	public void setIp(@NotNull String serverIp)
	{
		this.serverConfiguration.setServerIp(serverIp);
	}

	@Override
	@NotNull
	public String getWorldType()
	{
		return this.serverConfiguration.getLevelType().getKey();
	}

	/**
	 * Sets the global default World Type
	 *
	 * @param worldType
	 *            The new {@link ServerConfiguration.LevelType}
	 * @see ServerMock#getWorldType()
	 */
	public void setWorldType(@NotNull ServerConfiguration.LevelType worldType)
	{
		this.serverConfiguration.setLevelType(worldType);
	}

	@Override
	public boolean getGenerateStructures()
	{
		return this.serverConfiguration.isGenerateStructures();
	}

	/**
	 * Sets whether structures should be generated.
	 *
	 * @param generateStructures
	 *            Whether structures should be generated.
	 * @see ServerMock#getGenerateStructures()
	 */
	public void setGenerateStructures(boolean generateStructures)
	{
		this.serverConfiguration.setGenerateStructures(generateStructures);
	}

	@Override
	public boolean getAllowEnd()
	{
		return this.serverConfiguration.isAllowEnd();
	}

	/**
	 * Sets whether the End should be allowed.
	 *
	 * @param allowEnd
	 *            Whether the End should be allowed.
	 * @see ServerMock#getAllowEnd()
	 */
	public void setAllowEnd(boolean allowEnd)
	{
		this.serverConfiguration.setAllowEnd(allowEnd);
	}

	@Override
	public boolean getAllowNether()
	{
		return this.serverConfiguration.isAllowNether();
	}

	@Override
	public boolean isLoggingIPs()
	{
		return this.serverConfiguration.isLoggingIPs();
	}

	/**
	 * Sets whether the Nether should be allowed.
	 *
	 * @param allowNether
	 *            Whether the Nether should be allowed.
	 * @see ServerMock#getAllowNether()
	 */
	public void setAllowNether(boolean allowNether)
	{
		this.serverConfiguration.setAllowNether(allowNether);
	}

	@Override
	@NotNull
	public String getUpdateFolder()
	{
		return this.serverConfiguration.getUpdateFolder();
	}

	/**
	 * Sets the global update folder.
	 *
	 * @param updateFolder
	 *            The new update folder.
	 * @see ServerConfiguration#setUpdateFolder(String)
	 */
	public void setUpdateFolder(@NotNull String updateFolder)
	{
		this.serverConfiguration.setUpdateFolder(updateFolder);
	}

	@Override
	@NotNull
	public File getUpdateFolderFile()
	{
		return new File(this.getPluginsFolder(), this.getUpdateFolder());
	}

	/**
	 * @deprecated Use {@link #getTicksPerSpawns(SpawnCategory)} instead.
	 */
	@Override
	@Deprecated(since = "1.21")
	public int getTicksPerAnimalSpawns()
	{
		return this.getTicksPerSpawns(SpawnCategory.ANIMAL);
	}

	/**
	 * @deprecated Use {@link #getTicksPerSpawns(SpawnCategory)} instead.
	 */
	@Override
	@Deprecated(since = "1.21")
	public int getTicksPerMonsterSpawns()
	{
		return this.getTicksPerSpawns(SpawnCategory.MONSTER);
	}

	@Override
	public World createWorld(@NotNull WorldCreator creator)
	{
		WorldMock world = new WorldMock(creator);
		addWorld(world);
		return world;
	}

	@Override
	public boolean unloadWorld(String name, boolean save)
	{
		return unloadWorld(getWorld(name), save);
	}

	@Override
	public boolean unloadWorld(World world, boolean save)
	{
		if (save)
		{
			world.save();
		}
		if (!(world instanceof WorldMock worldMock))
		{
			return false;
		}
		if (!worldMock.getPlayers().isEmpty())
		{
			return false;
		}
		if (!new WorldUnloadEvent(worldMock).callEvent())
		{
			return false;
		}
		return removeWorld(worldMock);
	}

	@Override
	@NotNull
	public World getRespawnWorld()
	{
		World world = getWorld(this.respawnWorldName);
		Preconditions.checkState(world != null, "No world registered with name %s", this.respawnWorldName);
		return world;
	}

	@Override
	public void setRespawnWorld(@NotNull World world)
	{
		String worldName = world.getName();
		Preconditions.checkArgument(getWorld(worldName) != null, "World %s is not registered in this server",
				worldName);
		this.respawnWorldName = worldName;
	}

	@Override
	@NotNull
	public MapViewMock createMap(@NotNull World world)
	{
		MapViewMock mapView = new MapViewMock(world, nextMapId++);
		mapViews.put(mapView.getId(), mapView);
		new MapInitializeEvent(mapView).callEvent();
		return mapView;
	}

	@Override
	public void reload()
	{
		Plugin[] pluginsClone = pluginManager.getPlugins().clone();
		this.pluginManager.clearPlugins();
		this.commandMap.clearCommands();
		for (Plugin plugin : pluginsClone)
		{
			getPluginManager().disablePlugin(plugin);
			getWorlds().stream().map(WorldMock.class::cast).forEach(w -> w.clearMetadata(plugin));
			getEntities().forEach(e -> e.clearMetadata(plugin));
			getOnlinePlayers().forEach(p -> p.clearMetadata(plugin));
		}
		// reloadData(); Not implemented.
		// Wait up to 2.5 seconds for plugins to finish async tasks.
		try
		{
			((BukkitSchedulerMock) getScheduler()).waitActiveWorkersFinished(2500, TimeUnit.MILLISECONDS);
		} catch (InterruptedException _)
		{
			Thread.currentThread().interrupt();
		}
		((BukkitSchedulerMock) getScheduler()).saveOverdueTasks();
		List<Plugin> newPlugins = new ArrayList<>(pluginsClone.length);
		for (Plugin oldPlugin : pluginsClone)
		{
			if (!(oldPlugin instanceof JavaPlugin oldJavaPlugin))
			{
				continue;
			}
			// This is a little sketchy, but we have to do it since when initializing
			// plugins we create a subclass of the main class.
			// If we try to then load that subclass as the plugin, it doesn't work, so we
			// need to get the original class to subclass from again.
			Class<? extends JavaPlugin> originalClass = (Class<? extends JavaPlugin>) oldJavaPlugin.getClass()
					.getSuperclass();
			// Don't use MockBukkit#load here since we enable later.
			Plugin plugin = getPluginManager().loadPlugin(originalClass, oldJavaPlugin.getDescription(), new Object[0]);
			newPlugins.add(plugin);
		}
		newPlugins.stream().sorted(Comparator.comparing(p -> p.getDescription().getLoad()))
				.forEach(plugin -> getPluginManager().enablePlugin(plugin));
		new ServerLoadEvent(ServerLoadEvent.LoadType.RELOAD).callEvent();
	}

	@Override
	public void updateResources()
	{
		// This only sends packets to players in Paper.
	}

	@Override
	public void updateRecipes()
	{
		// This only sends packets to players in Paper.
	}

	@Override
	public void resetRecipes()
	{
		this.recipeManager.reset(RecipeType.CRAFTING);
	}

	@Override
	public int getSpawnRadius()
	{
		return spawnRadius;
	}

	@Override
	public void setSpawnRadius(int spawnRadius)
	{
		this.spawnRadius = spawnRadius;
	}

	@Override
	public boolean isEnforcingSecureProfiles()
	{
		return this.serverConfiguration.isEnforceSecureProfiles() && this.getOnlineMode();
	}

	/**
	 * Sets whether the server should enforce secure profiles.
	 *
	 * @param enforcingSecureProfiles
	 *            Whether the server should enforce secure profiles.
	 * @see ServerMock#isEnforcingSecureProfiles()
	 */
	public void setEnforcingSecureProfiles(boolean enforcingSecureProfiles)
	{
		this.serverConfiguration.setEnforceSecureProfiles(enforcingSecureProfiles);
	}

	@Override
	public boolean getOnlineMode()
	{
		return this.serverConfiguration.isOnlineMode();
	}

	/**
	 * Sets whether the server should be in online mode.
	 *
	 * @param onlineMode
	 *            Whether the server should be in online mode.
	 * @see ServerMock#getOnlineMode()
	 */
	public void setOnlineMode(boolean onlineMode)
	{
		this.serverConfiguration.setOnlineMode(onlineMode);
	}

	@Override
	public boolean getAllowFlight()
	{
		return this.serverConfiguration.isAllowFlight();
	}

	/**
	 * Sets whether the server should allow flight.
	 *
	 * @param allowFlight
	 *            Whether the server should allow flight.
	 * @see ServerMock#getAllowFlight()
	 */
	public void setAllowFlight(boolean allowFlight)
	{
		this.serverConfiguration.setAllowFlight(allowFlight);
	}

	@Override
	public boolean isHardcore()
	{
		return this.serverConfiguration.isHardcore();
	}

	/**
	 * Sets whether the server should be in hardcore mode.
	 *
	 * @param hardcore
	 *            Whether the server should be in hardcore mode.
	 * @see ServerMock#isHardcore()
	 */
	public void setHardcore(boolean hardcore)
	{
		this.serverConfiguration.setHardcore(hardcore);
	}

	/**
	 * @deprecated Use {@link #getOfflinePlayer(UUID)} instead.
	 */
	@Override
	@Deprecated(since = "1.21")
	@NotNull
	public OfflinePlayer getOfflinePlayer(@NotNull String name)
	{
		return playerList.getOfflinePlayer(name);
	}

	@Override
	@NotNull
	public OfflinePlayer getOfflinePlayer(@NotNull UUID id)
	{
		OfflinePlayer player = playerList.getOfflinePlayer(id);
		if (player != null)
		{
			return player;
		} else
		{
			return playerFactory.createOfflinePlayer(id);
		}
	}

	@Override
	@NotNull
	public Set<OfflinePlayer> getBannedPlayers()
	{
		return this.getBanList(Type.PROFILE).getEntries().stream().map(banEntry -> (BanEntry<PlayerProfile>) banEntry)
				.map(banEntry -> this.getOfflinePlayer(banEntry.getBanTarget().getId())).collect(Collectors.toSet());
	}

	@Override
	public int getMaxChainedNeighborUpdates()
	{
		return this.serverConfiguration.getMaxChainedNeighbourUpdates();
	}

	/**
	 * Sets the maximum number of chained neighbour updates before skipping
	 * additional ones. Negative values remove the limit.
	 *
	 * @param maxChainedNeighborUpdates
	 *            The maximum number of chained neighbour updates.
	 * @see ServerMock#getMaxChainedNeighborUpdates()
	 */
	public void setMaxChainedNeighborUpdates(int maxChainedNeighborUpdates)
	{
		this.serverConfiguration.setMaxChainedNeighbourUpdates(maxChainedNeighborUpdates);
	}

	/**
	 * @deprecated Use {@link #getSpawnLimit(SpawnCategory)} instead.
	 */
	@Override
	@Deprecated(since = "1.21")
	public int getMonsterSpawnLimit()
	{
		return this.getSpawnLimit(SpawnCategory.MONSTER);
	}

	/**
	 * @deprecated Use {@link #getSpawnLimit(SpawnCategory)} instead.
	 */
	@Override
	@Deprecated(since = "1.21")
	public int getAnimalSpawnLimit()
	{
		return this.getSpawnLimit(SpawnCategory.ANIMAL);
	}

	/**
	 * @deprecated Use {@link #getSpawnLimit(SpawnCategory)} instead.
	 */
	@Override
	@Deprecated(since = "1.21")
	public int getWaterAnimalSpawnLimit()
	{
		return this.getSpawnLimit(SpawnCategory.WATER_ANIMAL);
	}

	/**
	 * @deprecated Use {@link #getSpawnLimit(SpawnCategory)} instead.
	 */
	@Override
	@Deprecated(since = "1.21")
	public int getAmbientSpawnLimit()
	{
		return this.getSpawnLimit(SpawnCategory.AMBIENT);
	}

	@Override
	public boolean isPrimaryThread()
	{
		return this.isOnMainThread();
	}

	@Override
	@NotNull
	public Component motd()
	{
		return this.motd;
	}

	@Override
	public void motd(@NotNull Component motd)
	{
		Preconditions.checkNotNull(motd, "motd cannot be null");
		this.motd = motd;
	}

	/**
	 * @deprecated Use {@link #motd()} instead.
	 */
	@Override
	@Deprecated(since = "1.21")
	@NotNull
	public String getMotd()
	{
		return LegacyComponentSerializer.legacySection().serialize(this.motd);
	}

	@Override
	public void setMotd(@NotNull String motd)
	{
		Preconditions.checkNotNull(motd, "motd cannot be null");
		this.motd = LegacyComponentSerializer.legacySection().deserialize(motd);
	}

	@Override
	@Nullable
	public Component shutdownMessage()
	{
		return this.serverConfiguration.getShutdownMessage();
	}

	/**
	 * Sets the shutdown message.
	 *
	 * @param shutdownMessage
	 *            The shutdown message.
	 * @see ServerMock#shutdownMessage()
	 */
	public void setShutdownMessage(@NotNull Component shutdownMessage)
	{
		this.serverConfiguration.setShutdownMessage(shutdownMessage);
	}

	/**
	 * @deprecated Use modern API instead.
	 */
	@Override
	@Deprecated(since = "1.13")
	public String getShutdownMessage()
	{
		return LegacyComponentSerializer.legacySection().serialize(this.serverConfiguration.getShutdownMessage());
	}

	/**
	 * Sets the return value of {@link #getWarningState}.
	 *
	 * @param warningState
	 *            The {@link WarningState} to set.
	 */
	public void setWarningState(@NotNull WarningState warningState)
	{
		Preconditions.checkNotNull(warningState, "warningState cannot be null");
		this.warningState = warningState;
	}

	@Override
	@NotNull
	public WarningState getWarningState()
	{
		return this.warningState;
	}

	@Override
	@NotNull
	public Criteria getScoreboardCriteria(@NotNull String name)
	{
		Preconditions.checkNotNull(name, "Scoreboard criteria name cannot be null");
		return this.criteria.computeIfAbsent(name, CriteriaMock::new);
	}

	/**
	 * Sets the return value of {@link #getServerIcon()}.
	 *
	 * @param serverIcon
	 *            The icon to set.
	 */
	public void setServerIcon(CachedServerIconMock serverIcon)
	{
		this.serverIcon = serverIcon;
	}

	@Override
	public CachedServerIconMock getServerIcon()
	{
		return this.serverIcon;
	}

	@Override
	@NotNull
	public CachedServerIconMock loadServerIcon(@NotNull File file) throws IOException
	{
		Preconditions.checkNotNull(file, "File cannot be null");
		Preconditions.checkArgument(file.isFile(), file + " isn't a file");
		return loadServerIcon(ImageIO.read(file));
	}

	@Override
	@NotNull
	public CachedServerIconMock loadServerIcon(@NotNull BufferedImage image) throws IOException
	{
		Preconditions.checkNotNull(image, "Image cannot be null");
		Preconditions.checkArgument(image.getWidth() == 64, "Image must be 64 pixels wide");
		Preconditions.checkArgument(image.getHeight() == 64, "Image must be 64 pixels high");
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ImageIO.write(image, "PNG", out);
		String encoded = Base64.getEncoder().encodeToString(out.toByteArray());
		return new CachedServerIconMock(CachedServerIconMock.PNG_BASE64_PREFIX + encoded);
	}

	@Override
	public int getPauseWhenEmptyTime()
	{
		return this.pauseWhenEmptyTime;
	}

	@Override
	public void setPauseWhenEmptyTime(int seconds)
	{
		this.pauseWhenEmptyTime = seconds;
	}

	@Override
	@NotNull
	public ChunkData createChunkData(@NotNull World world)
	{
		Preconditions.checkNotNull(world, "World cannot be null");
		return new ChunkDataMock(world);
	}

	@Override
	@Nullable
	public Entity getEntity(@NotNull UUID uuid)
	{
		Preconditions.checkNotNull(uuid, "uuid cannot be null");
		for (EntityMock entity : entities)
		{
			if (entity.getUniqueId().equals(uuid))
			{
				return entity;
			}
		}
		return null;
	}

	/**
	 * @deprecated Use modern API instead.
	 */
	@Override
	@Deprecated(since = "1.13")
	@NotNull
	public UnsafeValuesMock getUnsafe()
	{
		return unsafe;
	}

	@Override
	@NotNull
	public BlockData createBlockData(@NotNull Material material)
	{
		Preconditions.checkNotNull(material, "Must provide material");
		return BlockDataMockFactory.mock(material);
	}

	@Override
	@NotNull
	public BlockData createBlockData(@NotNull Material material, @Nullable Consumer<? super BlockData> consumer)
	{
		BlockData blockData = createBlockData(material);
		if (consumer != null)
		{
			consumer.accept(blockData);
		}
		return blockData;
	}

	@Override
	@NotNull
	public BlockData createBlockData(@NotNull String data)
	{
		return this.createBlockData(null, data);
	}

	@Override
	@NotNull
	public BlockData createBlockData(Material material, String data)
	{
		Preconditions.checkArgument(material != null || data != null, "Must provide one of material or data");
		return BlockDataMock.newData((material != null) ? material.asBlockType() : null, data);
	}

	@Override
	@NotNull
	public StructureManager getStructureManager()
	{
		return this.structureManager;
	}

	@Override
	@Nullable
	public <T extends Keyed> Registry<T> getRegistry(@NotNull Class<T> tClass)
	{
		return RegistryAccess.registryAccess().getRegistry(tClass);
	}

	/**
	 * @deprecated Use modern API instead.
	 */
	@Override
	@Deprecated(since = "1.13")
	public int getTicksPerWaterSpawns()
	{
		return this.getTicksPerSpawns(SpawnCategory.WATER_ANIMAL);
	}

	/**
	 * @deprecated Use modern API instead.
	 */
	@Override
	@Deprecated(since = "1.13")
	public int getTicksPerAmbientSpawns()
	{
		return this.getTicksPerSpawns(SpawnCategory.AMBIENT);
	}

	/**
	 * This returns the current time of the {@link Server} in milliseconds
	 *
	 * @return The current {@link Server} time
	 */
	protected long getCurrentServerTime()
	{
		return System.currentTimeMillis();
	}

	@Override
	public int getTicksPerWaterAmbientSpawns()
	{
		return this.getTicksPerSpawns(SpawnCategory.WATER_AMBIENT);
	}

	/**
	 * @deprecated Use modern API instead.
	 */
	@Override
	@Deprecated(since = "1.13")
	public int getTicksPerWaterUndergroundCreatureSpawns()
	{
		return this.getTicksPerSpawns(SpawnCategory.WATER_UNDERGROUND_CREATURE);
	}

	/**
	 * @deprecated Use {@link #getTicksPerSpawns(SpawnCategory)} instead.
	 */
	@Override
	@Deprecated(since = "1.21")
	public int getWaterAmbientSpawnLimit()
	{
		return this.getSpawnLimit(SpawnCategory.WATER_AMBIENT);
	}

	/**
	 * @deprecated Use {@link #getTicksPerSpawns(SpawnCategory)} instead.
	 */
	@Override
	@Deprecated(since = "1.21")
	public int getWaterUndergroundCreatureSpawnLimit()
	{
		return this.getSpawnLimit(SpawnCategory.WATER_UNDERGROUND_CREATURE);
	}

	@Override
	public int getMaxWorldSize()
	{
		return this.serverConfiguration.getMaxWorldSize();
	}

	/**
	 * Set the maximum world size
	 *
	 * @param maxWorldSize
	 *            The maximum world size
	 * @see ServerMock#getMaxWorldSize()
	 */
	public void setMaxWorldSize(int maxWorldSize)
	{
		this.serverConfiguration.setMaxWorldSize(maxWorldSize);
	}

	@Override
	public int getSimulationDistance()
	{
		return this.serverConfiguration.getSimulationDistance();
	}

	/**
	 * Set the simulation distance
	 *
	 * @param simulationDistance
	 *            The simulation distance
	 * @see ServerMock#getSimulationDistance()
	 */
	public void setSimulationDistance(int simulationDistance)
	{
		this.serverConfiguration.setSimulationDistance(simulationDistance);
	}

	@Override
	public boolean getHideOnlinePlayers()
	{
		return this.serverConfiguration.isHideOnlinePlayers();
	}

	/**
	 * Set whether to hide online players
	 *
	 * @param hideOnlinePlayers
	 *            Whether to hide online players
	 * @see ServerMock#getHideOnlinePlayers()
	 */
	public void setHideOnlinePlayers(boolean hideOnlinePlayers)
	{
		this.serverConfiguration.setHideOnlinePlayers(hideOnlinePlayers);
	}

	@Override
	public Server.@NotNull Spigot spigot()
	{
		return this;
	}

	@Override
	@NotNull
	public String getPermissionMessage()
	{
		return unsafe.legacyComponentSerializer().serialize(NO_PERMISSION);
	}

	@Override
	@NotNull
	public Component permissionMessage()
	{
		return NO_PERMISSION;
	}

	@Override
	@NotNull
	public PlayerProfileMock createProfile(@NotNull UUID uuid)
	{
		return createProfile(uuid, null);
	}

	@Override
	@NotNull
	public PlayerProfileMock createProfile(@NotNull String name)
	{
		return createProfile(null, name);
	}

	@Override
	@NotNull
	public PlayerProfileMock createProfile(@Nullable UUID uuid, @Nullable String name)
	{
		return new PlayerProfileMock(name, uuid);
	}

	@Override
	@NotNull
	public PlayerProfileMock createProfileExact(@Nullable UUID uuid, @Nullable String name)
	{
		return new PlayerProfileMock(name, uuid);
	}

	@Override
	public int getCurrentTick()
	{
		return (int) ((BukkitSchedulerMock) getScheduler()).getCurrentTick();
	}

	@Override
	public void restart()
	{
		throw new UnsupportedOperationException("Not supported.");
	}

	@Override
	public int getTicksPerSpawns(@NotNull SpawnCategory spawnCategory)
	{
		Preconditions.checkArgument(spawnCategory != null, "SpawnCategory cannot be null");
		Preconditions.checkArgument(spawnCategory != SpawnCategory.MISC, "SpawnCategory.%s are not supported",
				spawnCategory);
		return (int) this.serverConfiguration.getTicksPerSpawn().getLong(spawnCategory);
	}

	/**
	 * @deprecated Use modern API instead.
	 */
	@Override
	@Deprecated(since = "1.13")
	@NotNull
	public PlayerProfileMock createPlayerProfile(@Nullable UUID uniqueId, @Nullable String name)
	{
		return new PlayerProfileMock(name, uniqueId);
	}

	/**
	 * @deprecated Use modern API instead.
	 */
	@Override
	@Deprecated(since = "1.13")
	@NotNull
	public PlayerProfileMock createPlayerProfile(@NotNull UUID uniqueId)
	{
		return createPlayerProfile(uniqueId, null);
	}

	/**
	 * @deprecated Use modern API instead.
	 */
	@Override
	@Deprecated(since = "1.13")
	@NotNull
	public PlayerProfileMock createPlayerProfile(@NotNull String name)
	{
		return createPlayerProfile(null, name);
	}

	@Override
	public int getSpawnLimit(@NotNull SpawnCategory spawnCategory)
	{
		Preconditions.checkArgument(spawnCategory != null, "SpawnCategory cannot be null");
		Preconditions.checkArgument(spawnCategory != SpawnCategory.MISC, "SpawnCategory.%s are not supported",
				spawnCategory);
		return this.serverConfiguration.getSpawnLimits().getOrDefault(spawnCategory, -1);
	}

	@Override
	public boolean isOwnedByCurrentRegion(@NotNull World world, @NotNull Position position)
	{
		return this.isPrimaryThread();
	}

	@Override
	public boolean isOwnedByCurrentRegion(@NotNull World world, @NotNull Position position, int squareRadiusChunks)
	{
		return this.isPrimaryThread();
	}

	@Override
	public boolean isOwnedByCurrentRegion(@NotNull Location location)
	{
		return this.isPrimaryThread();
	}

	@Override
	public boolean isOwnedByCurrentRegion(@NotNull Location location, int squareRadiusChunks)
	{
		return this.isPrimaryThread();
	}

	@Override
	public boolean isOwnedByCurrentRegion(@NotNull World world, int chunkX, int chunkZ)
	{
		return this.isPrimaryThread();
	}

	@Override
	public boolean isOwnedByCurrentRegion(@NotNull World world, int chunkX, int chunkZ, int squareRadiusChunks)
	{
		return this.isPrimaryThread();
	}

	@Override
	public boolean isOwnedByCurrentRegion(@NotNull Entity entity)
	{
		return this.isPrimaryThread();
	}

	@Override
	public boolean isOwnedByCurrentRegion(@NotNull World world, int i, int i1, int i2, int i3)
	{
		return this.isPrimaryThread();
	}

	@Override
	public boolean isGlobalTickThread()
	{
		return this.isPrimaryThread();
	}

	@Override
	@NotNull
	public File getPluginsFolder()
	{
		try
		{
			return getPluginManager().getParentTemporaryDirectory();
		} catch (IOException e)
		{
			throw new PluginIOException(e);
		}
	}

	/**
	 * Exposes the {@link ServerConfiguration} of this {@link ServerMock}.
	 *
	 * @return The {@link ServerConfiguration} of this {@link ServerMock}.
	 */
	@NotNull
	public ServerConfiguration getServerConfiguration()
	{
		return this.serverConfiguration;
	}
}
