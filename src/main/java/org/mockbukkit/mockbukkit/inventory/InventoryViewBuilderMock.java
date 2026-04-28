package org.mockbukkit.mockbukkit.inventory;

import net.kyori.adventure.text.Component;
import org.bukkit.entity.HumanEntity;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.view.builder.InventoryViewBuilder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Mock implementation of {@link InventoryViewBuilder}.
 *
 * @param <V>
 *            The type of {@link InventoryView} this builder creates.
 */
public class InventoryViewBuilderMock<V extends InventoryView>
		implements
			InventoryViewBuilder<V>,
			org.mockbukkit.mockbukkit.generated.org.bukkit.inventory.view.builder.InventoryViewBuilderBaseMock<V>
{

	private final MenuTypeMock<V, ?> menuType;

	@Nullable
	private Component title;

	InventoryViewBuilderMock(@NotNull MenuTypeMock<V, ?> menuType)
	{
		this.menuType = menuType;
	}

	private InventoryViewBuilderMock(@NotNull MenuTypeMock<V, ?> menuType, @Nullable Component title)
	{
		this.menuType = menuType;
		this.title = title;
	}

	@Override
	@NotNull
	public InventoryViewBuilder<V> copy()
	{
		return new InventoryViewBuilderMock<>(menuType, title);
	}

	@Override
	@NotNull
	public InventoryViewBuilder<V> title(@Nullable Component title)
	{
		this.title = title;
		return this;
	}

	@Override
	@NotNull
	public V build(@NotNull HumanEntity player)
	{
		Component effectiveTitle = title != null ? title : menuType.getMenuTypeData().inventoryType().defaultTitle();
		return menuType.create(player, effectiveTitle);
	}
}
