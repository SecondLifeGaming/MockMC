package org.mockmc.mockmc;

import com.destroystokyo.paper.event.server.WhitelistToggleEvent;
import com.google.common.base.Preconditions;
import io.papermc.paper.threadedregions.scheduler.AsyncScheduler;
import io.papermc.paper.threadedregions.scheduler.GlobalRegionScheduler;
import io.papermc.paper.threadedregions.scheduler.RegionScheduler;
import net.kyori.adventure.text.Component;
import net.md_5.bungee.api.chat.BaseComponent;
import org.bukkit.BanList;
import org.bukkit.Keyed;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.OfflinePlayer;
import org.bukkit.Server;
import org.bukkit.Tag;
import org.bukkit.World;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.boss.KeyedBossBar;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemFactory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.map.MapView;
import org.bukkit.plugin.messaging.Messenger;
import org.bukkit.plugin.messaging.StandardMessenger;
import org.bukkit.scoreboard.Criteria;
import org.bukkit.tag.DamageTypeTags;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.mockmc.mockmc.boss.BossBarMock;
import org.mockmc.mockmc.boss.KeyedBossBarMock;
import org.mockmc.mockmc.help.HelpMapMock;
import org.mockmc.mockmc.inventory.InventoryFactoryMock;
import org.mockmc.mockmc.inventory.InventoryMock;
import org.mockmc.mockmc.inventory.ItemFactoryMock;
import org.mockmc.mockmc.inventory.RecipeManager;
import org.mockmc.mockmc.inventory.RecipeType;
import org.mockmc.mockmc.map.MapViewMock;
import org.mockmc.mockmc.scheduler.BukkitSchedulerMock;
import org.mockmc.mockmc.scoreboard.ScoreboardManagerMock;
import org.mockmc.mockmc.services.ServicesManagerMock;
import org.mockmc.mockmc.tags.MaterialTagMock;
import org.mockmc.mockmc.tags.TagRegistry;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

/**
 * Base class for {@link ServerMock} to reduce its dependency count and
 * complexity.
 */
public abstract class ServerMockBase extends Server.Spigot
		implements
			org.mockmc.mockmc.generated.server.org.bukkit.ServerBaseMock
{

	private static final String NAMESPACED_KEY_NULL = "A NamespacedKey must never be null";
	private static final String INVENTORY_TYPE_NULL = "InventoryType cannot be null";

	protected final Map<String, TagRegistry> materialTags = new HashMap<>();
	protected final ScoreboardManagerMock scoreboardManager = new ScoreboardManagerMock();
	protected final Map<String, Criteria> criteria = new HashMap<>();
	protected final ServicesManagerMock servicesManager = new ServicesManagerMock();
	protected final FoliaSchedulerManager foliaSchedulers;
	protected final BukkitSchedulerMock scheduler;
	protected final HelpMapMock helpMap = new HelpMapMock();
	protected final StandardMessenger messenger = new StandardMessenger();
	protected final Map<Integer, MapViewMock> mapViews = new HashMap<>();
	protected final PlayerListMock playerList = new PlayerListMock();
	protected final Map<NamespacedKey, KeyedBossBarMock> bossBars = new HashMap<>();
	protected final ItemFactoryMock factory = new ItemFactoryMock();
	protected final InventoryFactoryMock inventoryFactory = new InventoryFactoryMock();
	protected final RecipeManager recipeManager = new RecipeManager();
	protected int nextMapId = 1;

	protected ServerMockBase(BukkitSchedulerMock scheduler)
	{
		this.scheduler = scheduler;
		this.foliaSchedulers = new FoliaSchedulerManager(scheduler);
	}

	@Override
	@SuppressWarnings(
	{"deprecation", "unchecked"})
	public <T extends BanList<?>> T getBanList(@NotNull BanList.Type type)
	{
		Preconditions.checkNotNull(type, INVENTORY_TYPE_NULL);
		switch (type)
		{
			case IP :
				return (T) this.playerList.getIPBans();
			case PROFILE, NAME :
				return (T) this.playerList.getProfileBans();
			default :
				throw new IllegalStateException("Unknown BanList Type: " + type);
		}
	}

	@Override
	public <B extends BanList<E>, E> B getBanList(@NotNull io.papermc.paper.ban.BanListType<B> type)
	{
		return this.playerList.getBanList(type);
	}

	@Override
	public @NotNull Set<String> getIPBans()
	{
		return this.playerList.getIPBans().getEntries().stream().map(entry -> entry.getBanTarget().getHostAddress())
				.collect(java.util.stream.Collectors.toSet());
	}

	@Override
	public @NotNull Messenger getMessenger()
	{
		return this.messenger;
	}

	@Override
	public @NotNull HelpMapMock getHelpMap()
	{
		return this.helpMap;
	}

	@Override
	public @NotNull ScoreboardManagerMock getScoreboardManager()
	{
		return this.scoreboardManager;
	}

	@Override
	public @NotNull BukkitSchedulerMock getScheduler()
	{
		return this.scheduler;
	}

	@Override
	public @NotNull GlobalRegionScheduler getGlobalRegionScheduler()
	{
		return this.foliaSchedulers.getGlobalRegionScheduler();
	}

	@Override
	public @NotNull RegionScheduler getRegionScheduler()
	{
		return this.foliaSchedulers.getRegionScheduler();
	}

	@Override
	public @NotNull AsyncScheduler getAsyncScheduler()
	{
		return this.foliaSchedulers.getAsyncScheduler();
	}

	@Override
	public @NotNull ServicesManagerMock getServicesManager()
	{
		return this.servicesManager;
	}

	/**
	 * @deprecated Use modern API instead.
	 */
	@Override
	@Deprecated(since = "1.21")
	public void broadcast(@NotNull BaseComponent component)
	{
		for (org.bukkit.entity.Player player : getOnlinePlayers())
		{
			player.spigot().sendMessage(component);
		}
	}

	/**
	 * @deprecated Use modern API instead.
	 */
	@Override
	@Deprecated(since = "1.21")
	public void broadcast(@NotNull BaseComponent... components)
	{
		for (org.bukkit.entity.Player player : getOnlinePlayers())
		{
			player.spigot().sendMessage(components);
		}
	}

	@Override
	public MapView getMap(int id)
	{
		return this.mapViews.get(id);
	}

	@Override
	public boolean hasWhitelist()
	{
		return this.playerList.isWhitelistEnabled();
	}

	@Override
	public void setWhitelist(boolean value)
	{
		if (this.playerList.isWhitelistEnabled() != value)
		{
			this.playerList.setWhitelist(value);
			new WhitelistToggleEvent(value).callEvent();
		}
	}

	@Override
	public boolean isWhitelistEnforced()
	{
		return this.playerList.isWhitelistEnforced();
	}

	@Override
	public void setWhitelistEnforced(boolean value)
	{
		this.playerList.setWhitelistEnforced(value);
	}

	@Override
	@NotNull
	public Set<OfflinePlayer> getWhitelistedPlayers()
	{
		return this.playerList.getWhitelistedPlayers();
	}

	@Override
	public void reloadWhitelist()
	{
		this.playerList.reloadWhitelist();
	}

	@Override
	@NotNull
	public BossBar createBossBar(@NotNull String title, @NotNull BarColor color, @NotNull BarStyle style,
			BarFlag... flags)
	{
		return new BossBarMock(title, color, style, flags);
	}

	@Override
	@NotNull
	public KeyedBossBar createBossBar(@NotNull NamespacedKey key, @NotNull String title, @NotNull BarColor color,
			@NotNull BarStyle style, BarFlag... flags)
	{
		Preconditions.checkNotNull(key, NAMESPACED_KEY_NULL);
		KeyedBossBarMock bar = new KeyedBossBarMock(key, title, color, style, flags);
		bossBars.put(key, bar);
		return bar;
	}

	@Override
	@NotNull
	public Iterator<KeyedBossBar> getBossBars()
	{
		return bossBars.values().stream().map(KeyedBossBar.class::cast).iterator();
	}

	@Override
	public KeyedBossBar getBossBar(NamespacedKey key)
	{
		Preconditions.checkNotNull(key, NAMESPACED_KEY_NULL);
		return bossBars.get(key);
	}

	@Override
	public boolean removeBossBar(NamespacedKey key)
	{
		Preconditions.checkNotNull(key, NAMESPACED_KEY_NULL);
		return bossBars.remove(key, bossBars.get(key));
	}

	@Override
	@NotNull
	public ItemFactory getItemFactory()
	{
		return factory;
	}

	@Override
	@NotNull
	public InventoryMock createInventory(@Nullable InventoryHolder owner, @NotNull InventoryType type)
	{
		Preconditions.checkArgument(type != null, INVENTORY_TYPE_NULL);
		Preconditions.checkArgument(type.isCreatable(), "InventoryType.%s cannot be used to create a inventory", type);
		return createInventory(owner, type, type.defaultTitle());
	}

	@Override
	@NotNull
	public InventoryMock createInventory(@Nullable InventoryHolder owner, @NotNull InventoryType type,
			@NotNull Component title)
	{
		Preconditions.checkArgument(type.isCreatable(), "Cannot open an inventory of type ", type);
		return createInventoryInternal(owner, type, title, type.getDefaultSize());
	}

	/**
	 * @deprecated Use modern API instead.
	 */
	@Override
	@Deprecated(since = "1.21")
	@NotNull
	public InventoryMock createInventory(@Nullable InventoryHolder owner, @NotNull InventoryType type,
			@NotNull String title)
	{
		Preconditions.checkArgument(type != null, INVENTORY_TYPE_NULL);
		Preconditions.checkArgument(type.isCreatable(), "InventoryType.%s cannot be used to create a inventory", type);
		Preconditions.checkArgument(title != null, "title cannot be null");
		return createInventory(owner, type, Component.text(title));
	}

	@Override
	@NotNull
	public InventoryMock createInventory(@Nullable InventoryHolder owner, int size)
	{
		Preconditions.checkArgument(9 <= size && size <= 54 && size % 9 == 0,
				"Size for custom inventory must be a multiple of 9 between 9 and 54 slots (got %s)", size);
		return createInventoryInternal(owner, InventoryType.CHEST, null, size);
	}

	@Override
	@NotNull
	public InventoryMock createInventory(@Nullable InventoryHolder owner, int size, @NotNull Component title)
			throws IllegalArgumentException
	{
		Preconditions.checkArgument(9 <= size && size <= 54 && size % 9 == 0,
				"Size for custom inventory must be a multiple of 9 between 9 and 54 slots (got " + size + ")");
		return createInventoryInternal(owner, InventoryType.CHEST, title, size);
	}

	/**
	 * @deprecated Use modern API instead.
	 */
	@Deprecated(since = "1.21")
	@NotNull
	public InventoryMock createInventory(@Nullable InventoryHolder owner, @NotNull InventoryType type,
			@NotNull String title, int size)
	{
		Preconditions.checkArgument(type != null, INVENTORY_TYPE_NULL);
		Preconditions.checkArgument(title != null, "title cannot be null");
		return createInventoryInternal(owner, type, Component.text(title), size);
	}

	@Override
	public @NotNull InventoryMock createInventory(@Nullable InventoryHolder owner, int size, @NotNull String title)
	{
		return createInventory(owner, size, Component.text(title));
	}

	@NotNull
	@ApiStatus.Internal
	private InventoryMock createInventoryInternal(@Nullable InventoryHolder owner, @NotNull InventoryType type,
			@Nullable Component title, int size)
	{
		return this.inventoryFactory.createInventoryInternal(owner, type, title, size);
	}

	@Override
	public boolean addRecipe(Recipe recipe)
	{
		return addRecipe(recipe, false);
	}

	@Override
	public boolean addRecipe(@Nullable Recipe recipe, boolean resendRecipes)
	{
		if (recipe == null)
		{
			return false;
		}
		AsyncCatcher.catchOp("Recipe add");
		// Pretend we sent the packet if resendRecipes is true
		return this.recipeManager.addRecipe(RecipeType.CRAFTING, recipe);
	}

	@Override
	@NotNull
	public List<Recipe> getRecipesFor(@NotNull ItemStack item)
	{
		return this.recipeManager.getRecipesFor(RecipeType.CRAFTING, item);
	}

	@Override
	@Nullable
	public Recipe getRecipe(@NotNull NamespacedKey key)
	{
		return this.recipeManager.getRecipeByKey(RecipeType.CRAFTING, key);
	}

	@Override
	@Nullable
	public Recipe getCraftingRecipe(@NotNull ItemStack[] craftingMatrix, @NotNull World world)
	{
		return this.recipeManager.getCraftingRecipe(craftingMatrix);
	}

	@Override
	@NotNull
	public ItemStack craftItem(@NotNull ItemStack[] craftingMatrix, @NotNull World world,
			@NotNull org.bukkit.entity.Player player)
	{
		Recipe recipe = getCraftingRecipe(craftingMatrix, world);
		if (recipe == null)
		{
			return ItemStack.empty();
		}
		return recipe.getResult();
	}

	@Override
	public Iterator<Recipe> recipeIterator()
	{
		return this.recipeManager.getRecipes(RecipeType.CRAFTING).iterator();
	}

	@Override
	public void clearRecipes()
	{
		this.recipeManager.reset(RecipeType.CRAFTING);
	}

	@Override
	public void resetRecipes()
	{
		this.recipeManager.reset(RecipeType.CRAFTING);
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
			if (recipe instanceof Keyed keyed && keyed.getKey().equals(key))
			{
				iterator.remove();
				return true;
			}
		}
		return false;
	}

	/**
	 * This creates a new Mock {@link Tag} for the {@link Material} class.<br>
	 * Call this in advance before you are gonna access
	 * {@link #getTag(String, NamespacedKey, Class)} or any of the constants defined
	 * in {@link Tag}.
	 *
	 * @param key
	 *            The {@link NamespacedKey} for this {@link Tag}
	 * @param registryKey
	 *            The name of the {@link TagRegistry}.
	 * @param materials
	 *            {@link Material Materials} which should be covered by this
	 *            {@link Tag}
	 * @return The newly created {@link Tag}
	 */
	@NotNull
	public Tag<Material> createMaterialTag(@NotNull NamespacedKey key, @NotNull String registryKey,
			@NotNull Material... materials)
	{
		Preconditions.checkNotNull(key);
		TagRegistry registry = materialTags.get(registryKey);
		MaterialTagMock tag = new MaterialTagMock(key, materials);
		registry.getTags().put(key, tag);
		return tag;
	}

	/**
	 * Adds a tag registry.
	 *
	 * @param registry
	 *            The registry to add.
	 */
	public void addTagRegistry(@NotNull TagRegistry registry)
	{
		materialTags.put(registry.getRegistry(), registry);
	}

	@Override
	public <T extends Keyed> Tag<T> getTag(@NotNull String registryKey, @NotNull NamespacedKey key,
			@NotNull Class<T> clazz)
	{
		Preconditions.checkArgument(registryKey != null, "registryKey cannot be null");
		Preconditions.checkArgument(key != null, "NamespacedKey key cannot be null");
		Preconditions.checkArgument(clazz != null, "Class clazz cannot be null");
		switch (registryKey)
		{
			case Tag.REGISTRY_BLOCKS :
			{
				Preconditions.checkArgument(clazz == org.bukkit.Material.class,
						"Block namespace (%s) must have material type", clazz.getName());
				Optional<Tag<T>> optionalTag = getTag(registryKey, key);
				if (optionalTag.isPresent())
				{
					return optionalTag.get();
				}
				break;
			}
			case Tag.REGISTRY_ITEMS :
			{
				Preconditions.checkArgument(clazz == org.bukkit.Material.class,
						"Item namespace (%s) must have material type", clazz.getName());
				Optional<Tag<T>> optionalTag = getTag(registryKey, key);
				if (optionalTag.isPresent())
				{
					return optionalTag.get();
				}
				break;
			}
			case Tag.REGISTRY_FLUIDS :
			{
				Preconditions.checkArgument(clazz == org.bukkit.Fluid.class,
						"Fluid namespace (%s) must have fluid type", clazz.getName());
				Optional<Tag<T>> optionalTag = getTag(registryKey, key);
				if (optionalTag.isPresent())
				{
					return optionalTag.get();
				}
				break;
			}
			case Tag.REGISTRY_ENTITY_TYPES :
			{
				Preconditions.checkArgument(clazz == org.bukkit.entity.EntityType.class,
						"Entity type namespace (%s) must have entity type", clazz.getName());
				Optional<Tag<T>> optionalTag = getTag(registryKey, key);
				if (optionalTag.isPresent())
				{
					return optionalTag.get();
				}
				break;
			}
			case Tag.REGISTRY_GAME_EVENTS :
			{
				Preconditions.checkArgument(clazz == org.bukkit.GameEvent.class,
						"Game Event namespace must have GameEvent type");
				Optional<Tag<T>> optionalTag = getTag(registryKey, key);
				if (optionalTag.isPresent())
				{
					return optionalTag.get();
				}
				break;
			}
			case DamageTypeTags.REGISTRY_DAMAGE_TYPES :
			{
				Preconditions.checkArgument(clazz == org.bukkit.damage.DamageType.class,
						"Damage type namespace must have DamageType type");
				Optional<Tag<T>> optionalTag = getTag(registryKey, key);
				if (optionalTag.isPresent())
				{
					return optionalTag.get();
				}
				break;
			}
			default :
				throw new IllegalArgumentException("Unknown registry key: " + registryKey);
		}
		// Per definition this method should return null if the given tag does not
		// exist.
		return null;
	}

	@Override
	@NotNull
	public <T extends Keyed> Iterable<Tag<T>> getTags(@NotNull String registry, @NotNull Class<T> clazz)
	{
		Preconditions.checkArgument(registry != null, "registry cannot be null");
		Preconditions.checkArgument(clazz != null, "Class clazz cannot be null");
		switch (registry)
		{
			case Tag.REGISTRY_BLOCKS :
			{
				Preconditions.checkArgument(clazz == org.bukkit.Material.class,
						"Block namespace (%s) must have material type", clazz.getName());
				return getTags(registry);
			}
			case Tag.REGISTRY_ITEMS :
			{
				Preconditions.checkArgument(clazz == org.bukkit.Material.class,
						"Item namespace (%s) must have material type", clazz.getName());
				return getTags(registry);
			}
			case Tag.REGISTRY_FLUIDS :
			{
				Preconditions.checkArgument(clazz == org.bukkit.Fluid.class,
						"Fluid namespace (%s) must have fluid type", clazz.getName());
				return getTags(registry);
			}
			case Tag.REGISTRY_ENTITY_TYPES :
			{
				Preconditions.checkArgument(clazz == org.bukkit.entity.EntityType.class,
						"Entity type namespace (%s) must have entity type", clazz.getName());
				return getTags(registry);
			}
			case Tag.REGISTRY_GAME_EVENTS :
			{
				Preconditions.checkArgument(clazz == org.bukkit.GameEvent.class,
						"Game Event namespace must have GameEvent type");
				return getTags(registry);
			}
			default :
				throw new IllegalArgumentException("Unknown registry key: " + registry);
		}
	}

	@SuppressWarnings("unchecked")
	@NotNull
	private <T extends Keyed> Collection<Tag<T>> getTags(@NotNull String registryKey)
	{
		TagRegistry registry = materialTags.get(registryKey);
		if (registry == null)
		{
			return Collections.emptyList();
		}
		return registry.getTags().values().stream().map(tag -> (Tag<T>) tag).toList();
	}

	@SuppressWarnings("unchecked")
	@NotNull
	protected <T extends Keyed> Optional<Tag<T>> getTag(@NotNull String registryKey, @NotNull NamespacedKey key)
	{
		TagRegistry registry = materialTags.get(registryKey);
		if (registry == null)
		{
			return Optional.empty();
		}
		Tag<?> tag = registry.getTags().get(key);
		if (tag == null)
		{
			return Optional.empty();
		}
		return Optional.of((Tag<T>) tag);
	}

}
