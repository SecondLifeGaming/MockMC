package org.mockmc.mockmc.entity;

import com.google.common.base.Preconditions;
import org.bukkit.Material;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Minecart;
import org.bukkit.material.MaterialData;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.mockmc.mockmc.ServerMock;
import org.mockmc.mockmc.block.data.BlockDataMock;
import org.mockmc.mockmc.block.data.BlockDataMockFactory;
import java.util.UUID;

/**
 * Mock implementation of a {@link Minecart}.
 *
 * @see VehicleMock
 */
@SuppressWarnings(
{"deprecation", "removal", "unchecked"})
public abstract class MinecartMock extends VehicleMock
		implements
			Minecart,
			org.mockmc.mockmc.generated.org.bukkit.entity.MinecartBaseMock
{

	private double damage = 0;

	private double maxSpeed = 0.4;

	private boolean slowWhenEmpty = true;

	@NotNull
	private Vector flyingVelocityMod = new Vector(0.949999988079071, 0.949999988079071, 0.949999988079071);

	@NotNull
	private Vector derailedVelocityMod = new Vector(0.5, 0.5, 0.5);

	private BlockData displayBlock;

	private int displayBlockOffset;

	/**
	 * Constructs a new {@link MinecartMock} on the provided {@link ServerMock} with
	 * a specified {@link UUID}.
	 *
	 * @param server
	 *            The server to create the entity on.
	 * @param uuid
	 *            The UUID of the entity.
	 */
	protected MinecartMock(@NotNull ServerMock server, @NotNull UUID uuid)
	{
		super(server, uuid);
	}

	@Override
	public void setDamage(double damage)
	{
		this.damage = damage;
	}

	@Override
	public double getDamage()
	{
		return this.damage;
	}

	@Override
	public double getMaxSpeed()
	{
		return this.maxSpeed;
	}

	@Override
	public void setMaxSpeed(double speed)
	{
		this.maxSpeed = speed;
	}

	@Override
	public boolean isSlowWhenEmpty()
	{
		return this.slowWhenEmpty;
	}

	@Override
	public void setSlowWhenEmpty(boolean slow)
	{
		this.slowWhenEmpty = slow;
	}

	@Override
	@NotNull
	public Vector getFlyingVelocityMod()
	{
		return flyingVelocityMod.clone();
	}

	@Override
	public void setFlyingVelocityMod(@NotNull Vector flying)
	{
		Preconditions.checkNotNull(flying, "Vector cannot be null");
		this.flyingVelocityMod = flying.clone();
	}

	@Override
	@NotNull
	public Vector getDerailedVelocityMod()
	{
		return this.derailedVelocityMod.clone();
	}

	@Override
	public void setDerailedVelocityMod(@NotNull Vector derailed)
	{
		Preconditions.checkNotNull(derailed, "Vector cannot be null");
		this.derailedVelocityMod = derailed.clone();
	}

	@Override
	@Deprecated(since = "1.18")
	public void setDisplayBlock(@Nullable MaterialData material)
	{
		this.displayBlock = material == null
				? new BlockDataMock(Material.AIR)
				: BlockDataMockFactory.mock(material.getItemType());
	}

	@Override
	@Deprecated(since = "1.18")
	@NotNull
	public MaterialData getDisplayBlock()
	{
		return new MaterialData(this.displayBlock.getMaterial());
	}

	@Override
	public void setDisplayBlockData(@Nullable BlockData blockData)
	{
		this.displayBlock = blockData == null ? new BlockDataMock(Material.AIR) : blockData;
	}

	@Override
	@NotNull
	public BlockData getDisplayBlockData()
	{
		return this.displayBlock;
	}

	@Override
	public void setDisplayBlockOffset(int offset)
	{
		this.displayBlockOffset = offset;
	}

	@Override
	public int getDisplayBlockOffset()
	{
		return this.displayBlockOffset;
	}

	@Override
	@NotNull
	public EntityType getType()
	{
		return EntityType.MINECART;
	}

}
