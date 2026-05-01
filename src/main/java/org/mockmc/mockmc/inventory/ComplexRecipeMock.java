package org.mockmc.mockmc.inventory;

import com.google.common.base.Preconditions;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ComplexRecipe;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class ComplexRecipeMock
		implements
			ComplexRecipe,
			org.mockmc.mockmc.generated.server.org.bukkit.inventory.ComplexRecipeBaseMock
{

	private final NamespacedKey key;

	private final ItemStack result;

	public ComplexRecipeMock(@NotNull NamespacedKey key, @NotNull ItemStack result)
	{
		this.key = Preconditions.checkNotNull(key, "The key must not be null");
		this.result = Preconditions.checkNotNull(result, "The result must not be null");
	}

	@Override
	@NotNull
	public NamespacedKey getKey()
	{
		return this.key;
	}

	@Override
	@NotNull
	public ItemStack getResult()
	{
		return this.result;
	}
}
