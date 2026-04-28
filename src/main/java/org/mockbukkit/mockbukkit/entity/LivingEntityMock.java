package org.mockbukkit.mockbukkit.entity;

import com.google.common.base.Preconditions;
import io.papermc.paper.world.damagesource.CombatTracker;
import net.kyori.adventure.util.TriState;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.damage.DamageSource;
import org.bukkit.damage.DamageType;
import org.bukkit.entity.Entity;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Mob;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.Wither;
import org.bukkit.entity.memory.MemoryKey;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityPotionEffectEvent;
import org.bukkit.event.entity.EntityToggleSwimEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Range;
import org.mockbukkit.mockbukkit.AsyncCatcher;
import org.mockbukkit.mockbukkit.ServerMock;
import org.mockbukkit.mockbukkit.attribute.AttributeInstanceMock;
import org.mockbukkit.mockbukkit.attribute.AttributesMock;
import org.mockbukkit.mockbukkit.entity.ai.BrainMock;
import org.mockbukkit.mockbukkit.entity.data.EntityState;
import org.mockbukkit.mockbukkit.inventory.EntityEquipmentMock;
import org.mockbukkit.mockbukkit.potion.ActivePotionEffect;
import org.mockbukkit.mockbukkit.simulate.entity.LivingEntitySimulation;
import org.mockbukkit.mockbukkit.world.damagesource.CombatTrackerMock;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.UUID;
import java.util.function.Consumer;

/**
 * Mock implementation of a {@link LivingEntity}.
 *
 * @see EntityMock
 */
@SuppressWarnings(
{"removal"})
public abstract class LivingEntityMock extends EntityMock
		implements
			org.mockbukkit.mockbukkit.generated.org.bukkit.entity.LivingEntityBaseMock
{

	private final BrainMock brain = new BrainMock();

	private final CombatTracker combatTracker = new CombatTrackerMock(this);

	/**
	 * How much health the entity has.
	 */
	protected double health;

	private int maxAirTicks = 300;

	private int remainingAirTicks = 300;

	/**
	 * NoDamage ticks
	 */
	private int noDamageTicks = 0;

	private int maximumNoDamageTicks = 20;

	/**
	 * Whether the entity is alive.
	 */
	protected boolean alive = true;

	private boolean gliding = false;

	private boolean jumping = false;

	private boolean riptiding = false;

	// Active item fields
	@Nullable
	private ItemStack activeItem = null;

	@Nullable
	private EquipmentSlot activeItemHand = null;

	private int activeItemUsedTicks = 0;

	private int activeItemMaxDuration = 0;

	/**
	 * The attributes this entity has.
	 */
	protected final Map<Attribute, AttributeInstanceMock> attributes;

	private final EntityEquipment equipment = new EntityEquipmentMock(this);

	private final Set<UUID> collidableExemptions = new HashSet<>();

	private boolean collidable = true;

	private boolean ai = true;

	private boolean swimming;

	/**
	 * Set whether this entity is slumbering.
	 */
	private boolean sleeping;

	/**
	 * Set whether this entity is climbing.
	 */
	private boolean climbing;

	private double absorptionAmount;

	private int arrowCooldown;

	private int arrowsInBody;

	@Nullable
	private Player killer;

	private final Map<PotionEffectType, PriorityQueue<ActivePotionEffect>> activeEffects = new HashMap<>();

	private TriState frictionState = TriState.NOT_SET;

	private Entity leashHolder;

	@Nullable
	private Location lastClimbableLocation;

	/**
	 * Constructs a new {@link LivingEntityMock} on the provided {@link ServerMock}
	 * with a specified {@link UUID}.
	 *
	 * @param server
	 *            The server to create the entity on.
	 * @param uuid
	 *            The UUID of the entity.
	 */
	protected LivingEntityMock(@NotNull ServerMock server, @NotNull UUID uuid)
	{
		super(server, uuid);
		attributes = new HashMap<>();
		double maxHealth = AttributesMock.getDefaultValue(Attribute.MAX_HEALTH);
		attributes.put(Attribute.MAX_HEALTH, new AttributeInstanceMock(Attribute.MAX_HEALTH, maxHealth));
		double movementSpeed = AttributesMock.getDefaultValue(Attribute.MOVEMENT_SPEED);
		attributes.put(Attribute.MOVEMENT_SPEED, new AttributeInstanceMock(Attribute.MOVEMENT_SPEED, movementSpeed));
		resetMaxHealth();
		setHealth(maxHealth);
	}

	@Override
	public double getHealth()
	{
		return health;
	}

	@Override
	public void remove()
	{
		this.health = 0;
		this.alive = false;
		super.remove();
	}

	@Override
	public boolean isDead()
	{
		return !this.alive || !super.isValid();
	}

	@Override
	public boolean isValid()
	{
		return !isDead();
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
		if (getLastDamageCause() != null
				&& getLastDamageCause().getDamageSource().getDirectEntity() instanceof Player player)
		{
			dmg = DamageSource.builder(DamageType.PLAYER_ATTACK).build();
			setKiller(player);
		} else
		{
			dmg = DamageSource.builder(DamageType.GENERIC).build();
		}
		this.health = 0;
		EntityDeathEvent event = new EntityDeathEvent(this, dmg, new ArrayList<>());
		if (!event.callEvent())
		{
			setKiller(null);
			this.health = event.getReviveHealth();
			this.alive = true;
		} else
		{
			this.alive = false;
		}
	}

	@Override
	public double getAbsorptionAmount()
	{
		return absorptionAmount;
	}

	@Override
	public void setAbsorptionAmount(double amount)
	{
		Preconditions.checkArgument(amount >= 0 && Double.isFinite(amount), "amount < 0 or non-finite");
		this.absorptionAmount = amount;
	}

	@Override
	public double getMaxHealth()
	{
		return getAttribute(Attribute.MAX_HEALTH).getValue();
	}

	@Override
	public void setMaxHealth(double health)
	{
		getAttribute(Attribute.MAX_HEALTH).setBaseValue(health);
		if (this.health > health)
		{
			this.health = health;
		}
	}

	@Override
	public void resetMaxHealth()
	{
		setMaxHealth(AttributesMock.getDefaultValue(Attribute.MAX_HEALTH));
	}

	@Override
	public void damage(double amount)
	{
		damage(amount, (Entity) null);
	}

	@Override
	public void damage(double amount, @Nullable Entity source)
	{
		if (isInvulnerable())
		{
			if (source instanceof HumanEntity)
			{
				if (((Player) source).getGameMode() != GameMode.CREATIVE)
				{
					return;
				}
			} else
			{
				return;
			}
		}
		setHealth(health - amount);
	}

	/**
	 * Simulate damage to this entity and throw an event.
	 *
	 * @param amount
	 *            <p>
	 *            The amount of damage to be done
	 *            </p>
	 * @param source
	 *            <p>
	 *            The damager
	 *            </p>
	 * @return
	 *         <p>
	 *         The EntityDamageEvent that got thrown
	 *         </p>
	 */
	public EntityDamageEvent simulateDamage(double amount, @NotNull DamageSource source)
	{
		return new LivingEntitySimulation(this).simulateDamage(amount, source);
	}

	/**
	 * Simulate damage to this entity and throw an event
	 *
	 * @param amount
	 *            <p>
	 *            The amount of damage to be done
	 *            </p>
	 * @param source
	 *            <p>
	 *            The damager
	 *            </p>
	 * @return
	 *         <p>
	 *         The event that got thrown
	 *         </p>
	 */
	public EntityDamageEvent simulateDamage(double amount, @Nullable Entity source)
	{
		return new LivingEntitySimulation(this).simulateDamage(amount, source);
	}

	@Override
	public AttributeInstance getAttribute(@NotNull Attribute attribute)
	{
		return attributes.getOrDefault(attribute, null);
	}

	@Override
	public void registerAttribute(@NotNull Attribute attribute)
	{
		Preconditions.checkNotNull(attribute, "Attribute cannot be null");
		this.attributes.put(attribute, new AttributeInstanceMock(attribute, AttributesMock.getDefaultValue(attribute)));
	}

	@Override
	@NotNull
	public <T extends Projectile> T launchProjectile(@NotNull Class<? extends T> projectile)
	{
		return this.launchProjectile(projectile, null);
	}

	@Override
	@NotNull
	public <T extends Projectile> T launchProjectile(@NotNull Class<? extends T> projectile, @Nullable Vector velocity)
	{
		return this.launchProjectile(projectile, velocity, null);
	}

	@Override
	@NotNull
	public <T extends Projectile> T launchProjectile(@NotNull Class<? extends T> projectile, @Nullable Vector velocity,
			@Nullable Consumer<? super T> function)
	{
		Preconditions.checkNotNull(projectile, "Projectile cannot be null");
		World world = getWorld();
		Preconditions.checkNotNull(world, "World cannot be null when launching a projectile");
		// The throwable spawn location is slightly lower than the eye level (See:
		// ThrowableProjectile constructor)
		Location spawnLocation = getEyeLocation().subtract(0, 0.10000000149011612D, 0);
		T entity = world.createEntity(spawnLocation, projectile);
		Preconditions.checkNotNull(entity, "Projectile (%s) not supported", projectile.getName());
		if (velocity != null)
		{
			entity.setVelocity(velocity);
		}
		if (function != null)
		{
			function.accept(entity);
		}
		return entity;
	}

	@Override
	public void startUsingItem(@NotNull EquipmentSlot hand)
	{
		Preconditions.checkNotNull(hand, "Hand cannot be null");
		Preconditions.checkArgument(hand == EquipmentSlot.HAND || hand == EquipmentSlot.OFF_HAND,
				"Hand must be HAND or OFF_HAND");
		ItemStack item = hand == EquipmentSlot.HAND ? equipment.getItemInMainHand() : equipment.getItemInOffHand();
		if (item.getType().isAir())
		{
			// Can't use air/null items
			return;
		}
		this.activeItem = item.clone();
		this.activeItemHand = hand;
		this.activeItemUsedTicks = 0;
		// Default max duration for most consumable items (food, potions) is 32 ticks
		// Bows can be held indefinitely, but we'll use a reasonable default
		this.activeItemMaxDuration = 32;
	}

	@Override
	@Nullable
	@SuppressWarnings("removal")
	public ItemStack getItemInUse()
	{
		return this.activeItem;
	}

	@Override
	@SuppressWarnings("removal")
	public int getItemInUseTicks()
	{
		return this.activeItemUsedTicks;
	}

	@Override
	@SuppressWarnings("removal")
	public void setItemInUseTicks(int ticks)
	{
		Preconditions.checkArgument(ticks >= 0, "Ticks must be >= 0");
		this.activeItemUsedTicks = ticks;
	}

	@Override
	public void completeUsingActiveItem()
	{
		// Clear the active item (item usage is complete)
		this.activeItem = null;
		this.activeItemHand = null;
		this.activeItemUsedTicks = 0;
		this.activeItemMaxDuration = 0;
	}

	@Override
	public int getActiveItemRemainingTime()
	{
		if (this.activeItem == null)
		{
			return 0;
		}
		return Math.max(0, this.activeItemMaxDuration - this.activeItemUsedTicks);
	}

	protected double getEyeHeight(EntityState pose)
	{
		return entityData.getEyeHeight(getSubType(), pose);
	}

	@Override
	public double getEyeHeight(boolean ignorePose)
	{
		if (ignorePose)
		{
			return getEyeHeight(EntityState.DEFAULT);
		}
		return getEyeHeight(getEntityState());
	}

	@Override
	public double getEyeHeight()
	{
		return getEyeHeight(false);
	}

	@Override
	protected EntityState getEntityState()
	{
		if (isSleeping())
		{
			return EntityState.SLEEPING;
		}
		if (isSneaking())
		{
			return EntityState.SNEAKING;
		}
		if (isSwimming())
		{
			return EntityState.SWIMMING;
		}
		return super.getEntityState();
	}

	@Override
	public void setActiveItemRemainingTime(@Range(from = 0L, to = 2147483647L) int ticks)
	{
		Preconditions.checkArgument(ticks >= 0, "Ticks must be >= 0");
		if (this.activeItem == null)
		{
			// No active item, nothing to set
			return;
		}
		this.activeItemUsedTicks = Math.max(0, this.activeItemMaxDuration - ticks);
	}

	@Override
	public boolean hasActiveItem()
	{
		return this.activeItem != null;
	}

	@Override
	public int getActiveItemUsedTime()
	{
		return this.activeItemUsedTicks;
	}

	@Override
	@NotNull
	public EquipmentSlot getActiveItemHand()
	{
		if (this.activeItemHand == null)
		{
			throw new IllegalStateException("No active item");
		}
		return this.activeItemHand;
	}

	@Override
	@NotNull
	public Location getEyeLocation()
	{
		return getLocation().add(0, getEyeHeight(), 0);
	}

	@Override
	public int getRemainingAir()
	{
		return remainingAirTicks;
	}

	@Override
	public void setRemainingAir(int ticks)
	{
		this.remainingAirTicks = ticks;
	}

	@Override
	public int getMaximumAir()
	{
		return maxAirTicks;
	}

	@Override
	public void setMaximumAir(int ticks)
	{
		this.maxAirTicks = ticks;
	}

	@Override
	public boolean addPotionEffect(@NotNull PotionEffect effect)
	{
		// force is ignored
		return addPotionEffect(effect, true);
	}

	/**
	 * @deprecated Use {@link #addPotionEffect(PotionEffect, boolean)} instead.
	 */
	@Override
	@Deprecated(since = "1.15")
	public boolean addPotionEffect(@NotNull PotionEffect effect, boolean force)
	{
		// Bukkit now allows multiple effects of the same type,
		// the force/success attributes are now obsolete
		addPotionEffect(effect, EntityPotionEffectEvent.Cause.PLUGIN);
		// legacy behaviour always returned true
		return true;
	}

	/**
	 * Adds a potion effect. If the event is canceled, no effect will be added.
	 *
	 * @param effect
	 *            The Potion Effect to add.
	 * @param cause
	 *            The cause.
	 * @return The event containing details about adding the potion effect.
	 */
	public EntityPotionEffectEvent addPotionEffect(@NotNull PotionEffect effect, EntityPotionEffectEvent.Cause cause)
	{
		AsyncCatcher.catchOp("effect add");
		Preconditions.checkNotNull(effect, "PotionEffect cannot be null");
		PotionEffectType type = effect.getType();
		PotionEffect oldEffect = getPotionEffect(type);
		EntityPotionEffectEvent.Action action = oldEffect == null
				? EntityPotionEffectEvent.Action.ADDED
				: EntityPotionEffectEvent.Action.CHANGED;
		boolean override = oldEffect != null;
		EntityPotionEffectEvent event = new EntityPotionEffectEvent(this, oldEffect, effect, cause, action, override);
		Bukkit.getPluginManager().callEvent(event);
		if (!event.isCancelled())
		{
			var pq = activeEffects.computeIfAbsent(type, k -> new PriorityQueue<>());
			if (oldEffect == null || oldEffect.getAmplifier() < effect.getAmplifier()
					|| (oldEffect.getAmplifier() == effect.getAmplifier() && !oldEffect.isInfinite()
							&& (effect.isInfinite() || oldEffect.getDuration() < effect.getDuration())))
			{
				pq.add(new ActivePotionEffect(effect));
			}
		}
		return event;
	}

	/**
	 * Adds multiple potion effects. If one event is canceled, the effect from that
	 * event won't be added.
	 *
	 * @param effects
	 *            The Potion Effects to add.
	 * @param cause
	 *            The cause.
	 * @return A list of events containing details about adding the potion effects.
	 */
	public List<EntityPotionEffectEvent> addPotionEffects(@NotNull Collection<PotionEffect> effects,
			EntityPotionEffectEvent.Cause cause)
	{
		Preconditions.checkNotNull(effects, "PotionEffect cannot be null");
		return effects.stream().map(potionEffect -> addPotionEffect(potionEffect, cause)).toList();
	}

	@Override
	public boolean addPotionEffects(@NotNull Collection<PotionEffect> effects)
	{
		addPotionEffects(effects, EntityPotionEffectEvent.Cause.PLUGIN);
		// legacy behaviour always returned true
		return true;
	}

	@Override
	public boolean hasPotionEffect(@NotNull PotionEffectType type)
	{
		return activeEffects.containsKey(type);
	}

	@Contract(value = "_ -> new", pure = true)
	@NotNull
	private PotionEffect mapToPotionEffect(@NotNull ActivePotionEffect activeEffect)
	{
		var effect = activeEffect.getPotionEffect();
		return new PotionEffect(effect.getType(), activeEffect.getDuration(), effect.getAmplifier(), effect.isAmbient(),
				effect.hasParticles(), effect.hasIcon());
	}

	@Override
	@Nullable
	public PotionEffect getPotionEffect(@NotNull PotionEffectType type)
	{
		Preconditions.checkNotNull(type, "Potion type cannot be null");
		var queue = activeEffects.get(type);
		if (queue == null)
		{
			return null;
		}
		return mapToPotionEffect(queue.peek());
	}

	private void removeExpiredEffects()
	{
		List<PotionEffectType> effectTypes = new ArrayList<>(activeEffects.keySet());
		for (PotionEffectType type : effectTypes)
		{
			var queue = activeEffects.get(type);
			long expiredCount = queue == null ? 0 : queue.stream().filter(ActivePotionEffect::hasExpired).count();
			if (expiredCount == 0)
			{
				continue;
			}
			boolean allExpired = queue.size() == expiredCount;
			if (allExpired)
			{
				var event = new EntityPotionEffectEvent(this, mapToPotionEffect(queue.peek()), null,
						EntityPotionEffectEvent.Cause.EXPIRATION, EntityPotionEffectEvent.Action.REMOVED, true);
				Bukkit.getPluginManager().callEvent(event);
				if (!event.isCancelled())
				{
					removeExpiredFromQueue(type);
				}
			} else
			{
				queue.removeIf(ActivePotionEffect::hasExpired);
			}
		}
	}

	private void removeExpiredFromQueue(PotionEffectType type)
	{
		var queue = activeEffects.get(type);
		if (queue == null)
		{
			return;
		}
		queue.removeIf(ActivePotionEffect::hasExpired);
		if (queue.isEmpty())
		{
			activeEffects.remove(type);
		}
	}

	@Override
	public void removePotionEffect(@NotNull PotionEffectType type)
	{
		removePotionEffect(type, EntityPotionEffectEvent.Cause.PLUGIN, EntityPotionEffectEvent.Action.REMOVED);
	}

	public void removePotionEffect(@NotNull PotionEffectType type, EntityPotionEffectEvent.Cause cause,
			EntityPotionEffectEvent.Action action)
	{
		Preconditions.checkNotNull(type, "Potion type cannot be null");
		var queue = activeEffects.get(type);
		if (queue == null)
		{
			return;
		}
		var activeEffect = queue.peek();
		var changeEvent = new EntityPotionEffectEvent(this, mapToPotionEffect(activeEffect), null, cause, action, true);
		Bukkit.getPluginManager().callEvent(changeEvent);
		if (!changeEvent.isCancelled())
		{
			activeEffects.remove(type);
		}
	}

	@Override
	@NotNull
	public Collection<PotionEffect> getActivePotionEffects()
	{
		return activeEffects.values().stream().map(queue -> mapToPotionEffect(queue.peek())).toList();
	}

	@Override
	public boolean clearActivePotionEffects()
	{
		if (activeEffects.isEmpty())
		{
			return false;
		}
		// Fire removal events for all active effects
		activeEffects.forEach((type, queue) -> queue.forEach(activeEffect ->
		{
			var event = new EntityPotionEffectEvent(this, activeEffect.getPotionEffect(), null,
					EntityPotionEffectEvent.Cause.PLUGIN, EntityPotionEffectEvent.Action.CLEARED, true);
			Bukkit.getPluginManager().callEvent(event);
		}));
		activeEffects.clear();
		return true;
	}

	@Nullable
	@Override
	public EntityEquipment getEquipment()
	{
		return this.equipment;
	}

	@Override
	public boolean teleport(@NotNull Location location, PlayerTeleportEvent.@NotNull TeleportCause cause)
	{
		if (isDead())
		{
			return false;
		}
		return super.teleport(location, cause);
	}

	@Override
	public boolean isLeashed()
	{
		return this.leashHolder instanceof Mob;
	}

	@Override
	@NotNull
	public Entity getLeashHolder() throws IllegalStateException
	{
		if (!this.isLeashed())
		{
			throw new IllegalStateException("Entity is currently not leashed");
		}
		return this.leashHolder;
	}

	@Override
	public boolean setLeashHolder(Entity holder)
	{
		if (this instanceof Wither || !(this instanceof Mob))
		{
			return false;
		}
		if (holder != null && holder.isDead())
		{
			return false;
		}
		this.leashHolder = holder;
		return true;
	}

	@Override
	public boolean isSwimming()
	{
		return this.swimming;
	}

	@Override
	public void setSwimming(boolean swimming)
	{
		if (this.isValid() && this.isSwimming() != swimming)
		{
			EntityToggleSwimEvent event = new EntityToggleSwimEvent(this, swimming);
			Bukkit.getPluginManager().callEvent(event);
			if (event.isCancelled())
			{
				return;
			}
		}
		this.swimming = swimming;
	}

	@Override
	public boolean isRiptiding()
	{
		return this.riptiding;
	}

	@Override
	public void setRiptiding(boolean isRiptiding)
	{
		this.riptiding = isRiptiding;
	}

	@Override
	public void setAI(boolean ai)
	{
		if (this instanceof Mob)
		{
			this.ai = ai;
		}
	}

	@Override
	public boolean hasAI()
	{
		return this instanceof Mob && this.ai;
	}

	@Override
	public void swingMainHand()
	{
		// Pretend packet gets sent.
	}

	@Override
	public void swingOffHand()
	{
		// Pretend packet gets sent.
	}

	@Override
	public void setCollidable(boolean collidable)
	{
		this.collidable = collidable;
	}

	@Override
	public boolean isCollidable()
	{
		return this.collidable;
	}

	@NotNull
	@Override
	public Set<UUID> getCollidableExemptions()
	{
		return this.collidableExemptions;
	}

	@Nullable
	@Override
	public <T> T getMemory(@NotNull MemoryKey<T> memoryKey)
	{
		return brain.getMemory(memoryKey).orElse(null);
	}

	@Override
	public <T> void setMemory(@NotNull MemoryKey<T> memoryKey, @Nullable T memoryValue)
	{
		brain.setMemory(memoryKey, memoryValue);
	}

	@Override
	public void setArrowsInBody(int count)
	{
		Preconditions.checkArgument(count >= 0, "New arrow amount must be >= 0");
		this.arrowsInBody = count;
	}

	@Override
	public int getArrowsInBody()
	{
		return this.arrowsInBody;
	}

	@Override
	public void setArrowCooldown(int ticks)
	{
		this.arrowCooldown = ticks;
	}

	@Override
	public int getArrowCooldown()
	{
		return arrowCooldown;
	}

	@Override
	@NotNull
	public ItemStack getActiveItem()
	{
		if (this.activeItem == null)
		{
			return new ItemStack(Material.AIR);
		}
		return this.activeItem;
	}

	@Override
	public void clearActiveItem()
	{
		this.completeUsingActiveItem();
	}

	@Override
	public boolean isJumping()
	{
		return this.jumping;
	}

	@Override
	public void setJumping(boolean jumping)
	{
		this.jumping = jumping;
	}

	@Override
	public boolean isHandRaised()
	{
		return hasActiveItem();
	}

	@Override
	@NotNull
	public EquipmentSlot getHandRaised()
	{
		if (!isHandRaised())
		{
			throw new IllegalStateException("No hand raised");
		}
		return getActiveItemHand();
	}

	@Override
	@NotNull
	public TriState getFrictionState()
	{
		return this.frictionState;
	}

	@Override
	public void setFrictionState(@NotNull TriState state)
	{
		Preconditions.checkNotNull(state, "State cannot be null");
		this.frictionState = state;
	}

	@Override
	@NotNull
	public CombatTracker getCombatTracker()
	{
		return this.combatTracker;
	}

	@Override
	public void tick()
	{
		super.tick();
		removeExpiredEffects();
	}

	/**
	 * Retrieves the last known climbable location.
	 *
	 * @return the last climbable Location, or null if no such location exists
	 */
	@Nullable
	public Location getLastClimbableLocation()
	{
		return this.lastClimbableLocation;
	}

	/**
	 * Updates the location of the last climbable spot.
	 *
	 * @param lastClimbableLocation
	 *            the new location to set as the last climbable location, or null if
	 *            no location is available
	 */
	public void setLastClimbableLocation(@Nullable Location lastClimbableLocation)
	{
		this.lastClimbableLocation = lastClimbableLocation;
	}

	@Override
	public int getNoDamageTicks()
	{
		return this.noDamageTicks;
	}

	@Override
	public void setNoDamageTicks(int ticks)
	{
		this.noDamageTicks = ticks;
	}

	@Override
	public int getMaximumNoDamageTicks()
	{
		return this.maximumNoDamageTicks;
	}

	@Override
	public void setMaximumNoDamageTicks(int ticks)
	{
		this.maximumNoDamageTicks = ticks;
	}

	@Override
	public boolean isGliding()
	{
		return this.gliding;
	}

	@Override
	public void setGliding(boolean gliding)
	{
		this.gliding = gliding;
	}

	@Override
	public boolean isSleeping()
	{
		return this.sleeping;
	}

	public void setSleeping(boolean sleeping)
	{
		this.sleeping = sleeping;
	}

	@Override
	public boolean isClimbing()
	{
		return this.climbing;
	}

	public void setClimbing(boolean climbing)
	{
		this.climbing = climbing;
	}

	@Override
	@Nullable
	public Player getKiller()
	{
		return this.killer;
	}

	@Override
	public void setKiller(@Nullable Player killer)
	{
		this.killer = killer;
	}
}
