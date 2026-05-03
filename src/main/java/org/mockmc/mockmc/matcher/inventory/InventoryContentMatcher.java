package org.mockmc.mockmc.matcher.inventory;

import io.papermc.paper.datacomponent.DataComponentType;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

/**
 * Advanced matcher for checking the contents of an {@link Inventory}.
 *
 * @mockmc.version 1.21-1.0.0
 */
public class InventoryContentMatcher extends TypeSafeMatcher<Inventory>
{

	private Material material;
	private Matcher<Integer> amountMatcher;
	private final List<Predicate<ItemStack>> predicates = new ArrayList<>();
	private final List<String> descriptions = new ArrayList<>();

	public InventoryContentMatcher()
	{
		// This constructor is intentionally empty as the matcher is configured using
		// fluent methods.
	}

	public InventoryContentMatcher withMaterial(@NotNull Material material)
	{
		this.material = material;
		return this;
	}

	public InventoryContentMatcher withAmount(@NotNull Matcher<Integer> amountMatcher)
	{
		this.amountMatcher = amountMatcher;
		return this;
	}

	public <T> InventoryContentMatcher withData(@NotNull DataComponentType.Valued<T> type,
			@NotNull Matcher<? super T> valueMatcher)
	{
		predicates.add(item ->
		{
			T data = item.getData(type);
			return data != null && valueMatcher.matches(data);
		});
		descriptions.add("with data " + type.getKey() + " matching " + valueMatcher);
		return this;
	}

	public InventoryContentMatcher withData(@NotNull DataComponentType.NonValued type)
	{
		predicates.add(item -> item.hasData(type));
		descriptions.add("with data " + type.getKey());
		return this;
	}

	public InventoryContentMatcher withFilter(@NotNull Predicate<ItemStack> filter, @NotNull String description)
	{
		predicates.add(filter);
		descriptions.add(description);
		return this;
	}

	@Override
	protected boolean matchesSafely(Inventory inventory)
	{
		int totalMatchAmount = Arrays.stream(inventory.getContents()).filter(Objects::nonNull)
				.filter(item -> item.getType() != Material.AIR)
				.filter(item -> material == null || item.getType() == material)
				.filter(item -> predicates.stream().allMatch(p -> p.test(item))).mapToInt(ItemStack::getAmount).sum();

		if (amountMatcher != null)
		{
			return amountMatcher.matches(totalMatchAmount);
		}

		return totalMatchAmount > 0;
	}

	@Override
	public void describeTo(Description description)
	{
		description.appendText("Inventory containing ");
		if (amountMatcher != null)
		{
			description.appendDescriptionOf(amountMatcher).appendText(" of ");
		} else
		{
			description.appendText("at least one of ");
		}

		if (material != null)
		{
			description.appendValue(material);
		} else
		{
			description.appendText("any item");
		}

		for (String desc : descriptions)
		{
			description.appendText(" ").appendText(desc);
		}
	}

	@Override
	protected void describeMismatchSafely(Inventory item, Description mismatchDescription)
	{
		mismatchDescription.appendText("found items: ");
		boolean first = true;
		for (ItemStack stack : item.getContents())
		{
			if (stack != null && stack.getType() != Material.AIR)
			{
				if (!first)
					mismatchDescription.appendText(", ");
				mismatchDescription.appendValue(stack);
				first = false;
			}
		}
	}

	public static InventoryContentMatcher hasItem()
	{
		return new InventoryContentMatcher();
	}
}
