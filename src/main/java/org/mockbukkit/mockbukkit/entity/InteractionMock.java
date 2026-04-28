package org.mockbukkit.mockbukkit.entity;

import com.google.common.base.Preconditions;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Interaction;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.mockbukkit.mockbukkit.ServerMock;
import java.util.UUID;
import org.mockbukkit.mockbukkit.generated.org.bukkit.entity.InteractionBaseMock;
import org.mockbukkit.mockbukkit.generated.org.bukkit.entity.Interaction_PreviousInteractionBaseMock;

/**
 * Mock implementation of a {@link Interaction}.
 *
 * @see EntityMock
 */
public class InteractionMock extends EntityMock implements Interaction, InteractionBaseMock
{

	private boolean responsive;

	private float height;

	private float width;

	private PreviousInteraction lastAttack;

	private PreviousInteraction lastInteraction;

	/**
	 * Constructs a new {@link InteractionMock} on the provided {@link ServerMock}
	 * with a specified {@link UUID}.
	 *
	 * @param server
	 *            The server to create the entity on.
	 * @param uuid
	 *            The UUID of the entity.
	 */
	public InteractionMock(@NotNull ServerMock server, @NotNull UUID uuid)
	{
		super(server, uuid);
	}

	@Override
	public float getInteractionWidth()
	{
		return this.width;
	}

	@Override
	public void setInteractionWidth(float width)
	{
		this.width = width;
	}

	@Override
	public float getInteractionHeight()
	{
		return this.height;
	}

	@Override
	public void setInteractionHeight(float height)
	{
		this.height = height;
	}

	@Override
	public boolean isResponsive()
	{
		return this.responsive;
	}

	@Override
	public void setResponsive(boolean response)
	{
		this.responsive = response;
	}

	@Override
	@Nullable
	public PreviousInteraction getLastAttack()
	{
		return this.lastAttack;
	}

	/**
	 * Set the last attack on this interaction entity.
	 *
	 * @param lastAttack
	 *            Last attack data, if present
	 */
	public void setLastAttack(@Nullable PreviousInteraction lastAttack)
	{
		this.lastAttack = lastAttack;
	}

	@Override
	@Nullable
	public PreviousInteraction getLastInteraction()
	{
		return this.lastInteraction;
	}

	/**
	 * Sets the last interaction on this entity.
	 *
	 * @param lastInteraction
	 *            Interaction data, if present
	 */
	public void setLastInteraction(@Nullable PreviousInteraction lastInteraction)
	{
		this.lastInteraction = lastInteraction;
	}

	@Override
	@NotNull
	public EntityType getType()
	{
		return EntityType.INTERACTION;
	}

	public static class PreviousInteractionMock implements PreviousInteraction, Interaction_PreviousInteractionBaseMock
	{

		private final OfflinePlayer player;

		private final long timestamp;

		/**
		 * Create a previous interaction mock.
		 *
		 * @param player
		 *            The last player that interacted.
		 * @param timestamp
		 *            The interaction timestamp.
		 */
		public PreviousInteractionMock(OfflinePlayer player, long timestamp)
		{
			Preconditions.checkNotNull(player, "The player cannot be null");
			this.player = player;
			this.timestamp = timestamp;
		}

		@Override
		@NotNull
		public OfflinePlayer getPlayer()
		{
			return this.player;
		}

		@Override
		public long getTimestamp()
		{
			return this.timestamp;
		}
	}
}
