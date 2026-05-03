package org.mockmc.mockmc.entity;

import com.google.common.base.Preconditions;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Mannequin;
import org.bukkit.inventory.MainHand;
import org.jetbrains.annotations.NotNull;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import org.mockmc.mockmc.ServerMock;
import java.util.UUID;

/**
 * Mock implementation of a {@link Mannequin}.
 *
 * @see LivingEntityMock
 */
public class MannequinMock extends LivingEntityMock
		implements
			Mannequin,
			org.mockmc.mockmc.generated.server.org.bukkit.entity.MannequinBaseMock
{

	private boolean immovable = false;

	private MainHand mainHand = MainHand.RIGHT;

	@Nullable
	private Component description = null;

	/**
	 * Constructs a new {@link LivingEntityMock} on the provided {@link ServerMock}
	 * with a specified {@link UUID}.
	 *
	 * @param server
	 *            The server to create the entity on.
	 * @param uuid
	 *            The UUID of the entity.
	 */
	protected MannequinMock(@NotNull ServerMock server, @NotNull UUID uuid)
	{
		super(server, uuid);
	}

	@Override
	public boolean isImmovable()
	{
		return this.immovable;
	}

	@Override
	public void setImmovable(boolean immovable)
	{
		this.immovable = immovable;
	}

	@Override
	@Nullable
	public Component getDescription()
	{
		return this.description;
	}

	@Override
	public void setDescription(@Nullable Component description)
	{
		this.description = description;
	}

	@Override
	@NonNull
	public MainHand getMainHand()
	{
		return this.mainHand;
	}

	@Override
	public void setMainHand(@NonNull MainHand hand)
	{
		Preconditions.checkArgument(hand != null, "hand cannot be null");
		this.mainHand = hand;
	}

	@Override
	@NotNull
	public EntityType getType()
	{
		return EntityType.MANNEQUIN;
	}
}
