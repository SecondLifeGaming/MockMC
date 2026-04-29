package org.mockmc.mockmc.inventory;

import net.kyori.adventure.text.Component;
import org.bukkit.entity.HumanEntity;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.view.builder.MerchantInventoryViewBuilder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Mock implementation of {@link MerchantInventoryViewBuilder}.
 *
 * @param <V>
 *            The type of {@link InventoryView} this builder creates.
 */
public class MerchantInventoryViewBuilderMock<V extends InventoryView>
		implements
			MerchantInventoryViewBuilder<V>,
			org.mockmc.mockmc.generated.org.bukkit.inventory.view.builder.MerchantInventoryViewBuilderBaseMock<V>
{

	private final MenuTypeMock<V, ?> menuType;

	@Nullable
	private Component title;

	MerchantInventoryViewBuilderMock(@NotNull MenuTypeMock<V, ?> menuType)
	{
		this.menuType = menuType;
	}

	private MerchantInventoryViewBuilderMock(@NotNull MenuTypeMock<V, ?> menuType, @Nullable Component title)
	{
		this.menuType = menuType;
		this.title = title;
	}

	@Override
	@NotNull
	public MerchantInventoryViewBuilder<V> copy()
	{
		return new MerchantInventoryViewBuilderMock<>(menuType, title);
	}

	@Override
	@NotNull
	public MerchantInventoryViewBuilder<V> title(@Nullable Component title)
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
