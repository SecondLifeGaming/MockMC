package org.mockbukkit.mockbukkit.registry;

import io.papermc.paper.registry.RegistryKey;
import io.papermc.paper.registry.TypedKey;
import io.papermc.paper.registry.set.RegistryKeySet;
import org.bukkit.Keyed;
import org.bukkit.NamespacedKey;
import org.bukkit.Registry;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Unmodifiable;

import java.util.Collection;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Mock implementation of {@link RegistryKeySet}.
 *
 * @param <T>
 *            The type of the keyed objects.
 */
public class RegistryKeySetMock<T extends Keyed> implements RegistryKeySet<T>
{

	private final RegistryKey<T> registryKey;
	private final Set<NamespacedKey> keys;

	public RegistryKeySetMock(RegistryKey<T> registryKey, Collection<NamespacedKey> keys)
	{
		this.registryKey = registryKey;
		this.keys = Set.copyOf(keys);
	}

	@Override
	public RegistryKey<T> registryKey()
	{
		return this.registryKey;
	}

	@Override
	public @Unmodifiable Collection<TypedKey<T>> values()
	{
		return this.keys.stream().map(key -> TypedKey.create(registryKey, key)).collect(Collectors.toUnmodifiableSet());
	}

	@Override
	public @Unmodifiable Collection<T> resolve(Registry<T> registry)
	{
		return this.keys.stream().map(registry::get).filter(Objects::nonNull).collect(Collectors.toUnmodifiableSet());
	}

	@Override
	public boolean contains(TypedKey<T> valueKey)
	{
		return this.keys.contains(valueKey.key());
	}

	@Override
	public int size()
	{
		return this.keys.size();
	}

	@Override
	public @NotNull Iterator<TypedKey<T>> iterator()
	{
		return values().iterator();
	}

}
