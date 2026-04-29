package org.mockmc.mockmc.entity;

import com.google.common.base.Preconditions;
import org.bukkit.Material;
import org.bukkit.entity.Chicken;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.mockmc.mockmc.ServerMock;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Mock implementation of a {@link Chicken}.
 *
 * @see AnimalsMock
 */
public class ChickenMock extends AnimalsMock
		implements
			Chicken,
			org.mockmc.mockmc.generated.org.bukkit.entity.ChickenBaseMock
{

	@NotNull
	private SoundVariant soundVariant = SoundVariant.CLASSIC;

	@NotNull
	private Variant variant = Variant.TEMPERATE;

	private boolean isChickenJockey = false;

	private int eggTime;

	/**
	 * Constructs a new {@link ChickenMock} on the provided {@link ServerMock} with
	 * a specified {@link UUID}.
	 *
	 * @param server
	 *            The server to create the entity on.
	 * @param uuid
	 *            The UUID of the entity.
	 */
	public ChickenMock(@NotNull ServerMock server, @NotNull UUID uuid)
	{
		super(server, uuid);
		this.eggTime = ThreadLocalRandom.current().nextInt(6000) + 6000;
	}

	@Override
	@NotNull
	public EntityType getType()
	{
		return EntityType.CHICKEN;
	}

	@Override
	public boolean isBreedItem(@NotNull ItemStack stack)
	{
		Preconditions.checkNotNull(stack, "ItemStack cannot be null");
		return stack.getType() == Material.WHEAT_SEEDS;
	}

	@Override
	@NotNull
	public Variant getVariant()
	{
		return this.variant;
	}

	@Override
	public void setVariant(@NotNull Variant variant)
	{
		Preconditions.checkArgument(variant != null, "Variant cannot be null");
		this.variant = variant;
	}

	@Override
	public SoundVariant getSoundVariant()
	{
		return this.soundVariant;
	}

	@Override
	public void setSoundVariant(SoundVariant variant)
	{
		Preconditions.checkArgument(variant != null, "variant cannot be null");
		this.soundVariant = variant;
	}

	@Override
	public boolean isChickenJockey()
	{
		return this.isChickenJockey;
	}

	@Override
	public void setIsChickenJockey(boolean isChickenJockey)
	{
		this.isChickenJockey = isChickenJockey;
	}

	@Override
	public int getEggLayTime()
	{
		return this.eggTime;
	}

	@Override
	public void setEggLayTime(int eggLayTime)
	{
		this.eggTime = eggLayTime;
	}
}
