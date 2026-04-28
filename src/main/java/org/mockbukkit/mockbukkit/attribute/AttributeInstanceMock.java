package org.mockbukkit.mockbukkit.attribute;

import com.google.common.base.Preconditions;
import net.kyori.adventure.key.Key;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.attribute.AttributeModifier;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import static org.bukkit.attribute.AttributeModifier.Operation.ADD_NUMBER;
import static org.bukkit.attribute.AttributeModifier.Operation.ADD_SCALAR;
import static org.bukkit.attribute.AttributeModifier.Operation.MULTIPLY_SCALAR_1;

/**
 * Mock implementation of {@link AttributeInstance}.
 */
@SuppressWarnings(
{"deprecation", "removal", "unchecked"})
public class AttributeInstanceMock
		implements
			AttributeInstance,
			org.mockbukkit.mockbukkit.generated.org.bukkit.attribute.AttributeInstanceBaseMock
{

	@NotNull
	private final Attribute attribute;

	private final double defaultValue;

	private double baseValue;

	@NotNull
	private final List<AttributeModifier> modifiers = new ArrayList<>();

	@NotNull
	private final Map<Key, AttributeModifier> modifiersByKey = new HashMap<>();

	@NotNull
	private final Map<UUID, AttributeModifier> modifiersByUuid = new HashMap<>();

	/**
	 * Constructs a new {@link AttributeInstanceMock} for the provided
	 * {@link Attribute} and with the specified value.
	 *
	 * @param attribute
	 *            The Attribute this is an instance of.
	 * @param defaultValue
	 *            The default value of the attribute.
	 */
	public AttributeInstanceMock(@NotNull Attribute attribute, double defaultValue)
	{
		Preconditions.checkNotNull(attribute, "Attribute cannot be null");
		this.attribute = attribute;
		this.defaultValue = defaultValue;
		this.baseValue = defaultValue;
	}

	@Override
	@NotNull
	public Attribute getAttribute()
	{
		return this.attribute;
	}

	@Override
	public double getDefaultValue()
	{
		return this.defaultValue;
	}

	@Override
	public double getBaseValue()
	{
		return this.baseValue;
	}

	@Override
	public void setBaseValue(double baseValue)
	{
		this.baseValue = baseValue;
	}

	@Override
	@NotNull
	public Collection<AttributeModifier> getModifiers()
	{
		return new ArrayList<>(modifiers);
	}

	@Override
	@Nullable
	public AttributeModifier getModifier(@NotNull Key key)
	{
		Preconditions.checkArgument(key != null, "Key cannot be null");
		return modifiersByKey.get(key);
	}

	@Override
	public void removeModifier(@NotNull Key key)
	{
		Preconditions.checkNotNull(key, "Key cannot be null");
		AttributeModifier modifier = modifiersByKey.remove(key);
		if (modifier != null)
		{
			modifiers.remove(modifier);
			removeModifierByUuid(modifier);
		}
	}

	@SuppressWarnings(
	{"removal"})
	private void removeModifierByUuid(@NotNull AttributeModifier modifier)
	{
		modifiersByUuid.remove(modifier.getUniqueId());
	}

	@Override
	@Deprecated(forRemoval = true, since = "1.21")
	@Nullable
	public AttributeModifier getModifier(@NotNull UUID uuid)
	{
		Preconditions.checkNotNull(uuid, "UUID cannot be null");
		return modifiersByUuid.get(uuid);
	}

	@Override
	@Deprecated(forRemoval = true, since = "1.21")
	public void removeModifier(@NotNull UUID uuid)
	{
		Preconditions.checkNotNull(uuid, "UUID cannot be null");
		AttributeModifier modifier = modifiersByUuid.remove(uuid);
		if (modifier != null)
		{
			modifiers.remove(modifier);
			modifiersByKey.remove(modifier.getKey());
		}
	}

	@Override
	@SuppressWarnings(
	{"removal"})
	public void addModifier(@NotNull AttributeModifier modifier)
	{
		Preconditions.checkNotNull(modifier, "Modifier shouldn't be null");
		// Remove existing modifier with same key or UUID if present
		removeModifier(modifier.getKey());
		removeModifier(modifier.getUniqueId());
		modifiers.add(modifier);
		modifiersByKey.put(modifier.getKey(), modifier);
		modifiersByUuid.put(modifier.getUniqueId(), modifier);
	}

	@Override
	public void addTransientModifier(@NotNull AttributeModifier modifier)
	{
		// Thorinwasher: We don't even need to differentiate between these two types, as
		// there is no world storage
		// solution for MockBukkit; This does not matter, as nothing is persistent
		addModifier(modifier);
	}

	@Override
	public void removeModifier(@NotNull AttributeModifier modifier)
	{
		Preconditions.checkNotNull(modifier, "Modifier cannot be null");
		// Remove from permanent modifiers
		if (modifiers.remove(modifier))
		{
			modifiersByKey.remove(modifier.getKey());
			removeModifierByUuid(modifier);
		}
	}

	@Override
	public double getValue()
	{
		double base = this.getBaseValue();
		for (AttributeModifier modifier : modifiers)
		{
			if (modifier.getOperation() == ADD_NUMBER)
			{
				base += modifier.getAmount();
			}
		}
		double d = base;
		for (AttributeModifier modifier : modifiers)
		{
			if (modifier.getOperation() == ADD_SCALAR)
			{
				d += base * modifier.getAmount();
			}
		}
		for (AttributeModifier modifier : modifiers)
		{
			if (modifier.getOperation() == MULTIPLY_SCALAR_1)
			{
				d *= 1.0 + modifier.getAmount();
			}
		}
		return d;
	}
}
