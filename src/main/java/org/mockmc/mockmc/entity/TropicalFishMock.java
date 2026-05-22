package org.mockmc.mockmc.entity;

import com.google.common.base.Preconditions;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.TropicalFish;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.mockmc.mockmc.ServerMock;
import org.mockmc.mockmc.inventory.ItemStackMock;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Mock implementation of a {@link TropicalFish}.
 *
 * @see SchoolableFishMock
 */
public class TropicalFishMock extends SchoolableFishMock
		implements
			TropicalFish,
			org.mockmc.mockmc.generated.server.org.bukkit.entity.TropicalFishBaseMock
{

	@NotNull
	private DyeColor patternColor;

	@NotNull
	private DyeColor bodyColor;

	@NotNull
	private Pattern pattern;

	/**
	 * Constructs a new {@link TropicalFishMock} on the provided {@link ServerMock}
	 * with a specified {@link UUID}.
	 *
	 * @param server
	 *            The server to create the entity on.
	 * @param uuid
	 *            The UUID of the entity.
	 */
	public TropicalFishMock(@NotNull ServerMock server, @NotNull UUID uuid)
	{
		super(server, uuid);
		patternColor = DyeColor.values()[ThreadLocalRandom.current().nextInt(DyeColor.values().length)];
		bodyColor = DyeColor.values()[ThreadLocalRandom.current().nextInt(DyeColor.values().length)];
		pattern = Pattern.values()[ThreadLocalRandom.current().nextInt(Pattern.values().length)];
	}

	@Override
	@NotNull
	public ItemStack getBaseBucketItem()
	{
		return new ItemStackMock(Material.TROPICAL_FISH_BUCKET);
	}

	@Override
	@NotNull
	public DyeColor getPatternColor()
	{
		return this.patternColor;
	}

	@Override
	public void setPatternColor(@NotNull DyeColor color)
	{
		Preconditions.checkNotNull(color, "Pattern Color cannot be null");
		this.patternColor = color;
	}

	@Override
	@NotNull
	public DyeColor getBodyColor()
	{
		return this.bodyColor;
	}

	@Override
	public void setBodyColor(@NotNull DyeColor color)
	{
		Preconditions.checkNotNull(color, "Body Color cannot be null");
		this.bodyColor = color;
	}

	@Override
	@NotNull
	public Pattern getPattern()
	{
		return this.pattern;
	}

	@Override
	public void setPattern(@NotNull Pattern pattern)
	{
		Preconditions.checkNotNull(pattern, "Pattern cannot be null");
		this.pattern = pattern;
	}

	@Override
	@NotNull
	public EntityType getType()
	{
		return EntityType.TROPICAL_FISH;
	}
}
