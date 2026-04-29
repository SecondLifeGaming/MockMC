package org.mockmc.mockmc.persistence;

import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataHolder;
import org.jetbrains.annotations.NotNull;

/**
 * This is a mock of a {@link PersistentDataHolder}. This implementation uses
 * the {@link PersistentDataContainerMock} for its
 * {@link PersistentDataContainer} implementation.
 *
 * @author md5sha256
 */
public class PersistentDataHolderMock
		implements
			PersistentDataHolder,
			org.mockmc.mockmc.generated.org.bukkit.persistence.PersistentDataHolderBaseMock
{

	@NotNull
	private final PersistentDataContainer container;

	/**
	 * Constructs a new {@link PersistentDataHolderMock}.
	 */
	public PersistentDataHolderMock()
	{
		this.container = new PersistentDataContainerMock();
	}

	@Override
	@NotNull
	public PersistentDataContainer getPersistentDataContainer()
	{
		return container;
	}
}
