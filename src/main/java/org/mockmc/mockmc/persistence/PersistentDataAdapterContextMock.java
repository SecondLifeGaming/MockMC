package org.mockmc.mockmc.persistence;

import org.bukkit.persistence.PersistentDataAdapterContext;
import org.bukkit.persistence.PersistentDataContainer;
import org.jetbrains.annotations.NotNull;

/**
 * This is about as basic of an implementation for
 * {@link PersistentDataAdapterContext} as you could imagine. But it is
 * identical with the default CraftBukkit implementation too. So mock successful
 * I would say.
 *
 * @author TheBusyBiscuit
 */
public class PersistentDataAdapterContextMock
		implements
			PersistentDataAdapterContext,
			org.mockmc.mockmc.generated.org.bukkit.persistence.PersistentDataAdapterContextBaseMock
{

	@Override
	@NotNull
	public PersistentDataContainer newPersistentDataContainer()
	{
		return new PersistentDataContainerMock();
	}
}
