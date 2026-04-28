package org.mockbukkit.mockbukkit.entity;

import com.google.common.base.Preconditions;
import net.kyori.adventure.sound.Sound;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Cow;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.entity.MushroomCow;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDropItemEvent;
import org.bukkit.event.entity.EntityTransformEvent;
import org.jetbrains.annotations.NotNull;
import org.mockbukkit.mockbukkit.ServerMock;
import org.mockbukkit.mockbukkit.inventory.ItemStackMock;
import org.mockbukkit.mockbukkit.util.AdventureConverters;
import org.mockbukkit.mockbukkit.world.WorldMock;
import java.util.List;
import java.util.UUID;

/**
 * Mock implementation of a {@link MushroomCow}.
 *
 * @see CowMock
 */
@SuppressWarnings(
{"deprecation", "removal", "unchecked"})
public class MushroomCowMock extends AbstractCowMock
		implements
			MushroomCow,
			org.mockbukkit.mockbukkit.generated.org.bukkit.entity.MushroomCowBaseMock
{

	@NotNull
	private MushroomCow.Variant variant = MushroomCow.Variant.RED;

	/**
	 * Constructs a new {@link MushroomCowMock} on the provided {@link ServerMock}
	 * with a specified {@link UUID}.
	 *
	 * @param server
	 *            The server to create the entity on.
	 * @param uuid
	 *            The UUID of the entity.
	 */
	public MushroomCowMock(@NotNull ServerMock server, @NotNull UUID uuid)
	{
		super(server, uuid);
	}

	@Override
	@NotNull
	public MushroomCow.Variant getVariant()
	{
		return this.variant;
	}

	@Override
	public void setVariant(@NotNull MushroomCow.Variant variant)
	{
		Preconditions.checkNotNull(variant, "Variant cannot be null");
		this.variant = variant;
	}

	@Override
	@NotNull
	public EntityType getType()
	{
		return EntityType.MOOSHROOM;
	}

	@Override
	public void shear(Sound.@NotNull Source source)
	{
		WorldMock world = this.getWorld();
		Location location = this.getLocation();
		world.playSound(this, org.bukkit.Sound.ENTITY_MOOSHROOM_SHEAR,
				AdventureConverters.soundSourceToCategory(source), 1.0F, 1.0F);
		Cow cow = world.spawn(this.getLocation(), Cow.class, null, CreatureSpawnEvent.SpawnReason.CUSTOM, true, false);
		// world.spawnParticle(Particle.EXPLOSION_NORMAL, location.getX(),
		// location.getY() + 0.5, location.getZ(), 1); // Not implemented
		cow.teleport(this);
		cow.setHealth(this.getHealth());
		if (this.customName() != null)
		{
			cow.customName(this.customName());
			cow.setCustomNameVisible(this.isCustomNameVisible());
		}
		cow.setPersistent(this.isPersistent());
		cow.setInvulnerable(this.isInvulnerable());
		if (!new EntityTransformEvent(this, List.of(cow), EntityTransformEvent.TransformReason.SHEARED).callEvent())
		{
			cow.remove();
			return;
		}
		for (int i = 0; i < 5; ++i)
		{
			Item item = world.dropItem(location,
					new ItemStackMock(Material.valueOf(this.getVariant().name() + "_MUSHROOM")));
			if (!new EntityDropItemEvent(this, item).callEvent())
			{
				item.remove();
			}
		}
		this.remove();
	}

	@Override
	public boolean readyToBeSheared()
	{
		return !this.isDead() && this.isAdult();
	}
}
