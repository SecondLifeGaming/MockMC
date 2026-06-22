package io.papermc.paper.datacomponent.item;

import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class SulfurCubeContentMock implements SulfurCubeContent
{
	private final ItemStack absorbedItem;

	public SulfurCubeContentMock(ItemStack absorbedItem)
	{
		this.absorbedItem = absorbedItem;
	}

	@Override
	public @NotNull ItemStack absorbedItem()
	{
		return this.absorbedItem;
	}
}
