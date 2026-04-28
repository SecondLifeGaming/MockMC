package org.mockbukkit.metaminer.keyed;

import io.leangen.geantyref.GenericTypeReflector;
import io.papermc.paper.registry.RegistryKey;
import org.bukkit.Keyed;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class KeyedClassTracker
{

	private KeyedClassTracker()
	{
		throw new UnsupportedOperationException("Utility class");
	}

	private static final Map<RegistryKey<? extends Keyed>, Class<?>> CLASS_REGISTRY_KEY_RELATION = loadClassRegistryKeyRelation();

	/**
	 * Returns the relation between registry keys and their classes.
	 *
	 * @return The relation.
	 */
	public static Map<RegistryKey<? extends Keyed>, Class<?>> getClassRegistryKeyRelation()
	{
		return CLASS_REGISTRY_KEY_RELATION;
	}

	private static Map<RegistryKey<? extends Keyed>, Class<?>> loadClassRegistryKeyRelation()
	{
		Map<RegistryKey<? extends Keyed>, Class<?>> output = new HashMap<>();
		for (final Field field : RegistryKey.class.getFields())
		{
			if (field.getType() == RegistryKey.class)
			{
				// get the legacy type from the RegistryKey generic parameter on the field
				final Class<?> legacyType = GenericTypeReflector.erase(((ParameterizedType) field.getGenericType()).getActualTypeArguments()[0]);
				try
				{
					@SuppressWarnings("unchecked")
					RegistryKey<? extends Keyed> registryKey = (RegistryKey<? extends Keyed>) field.get(null);
					output.put(registryKey, legacyType);
				}
				catch (IllegalAccessException e)
				{
					throw new IllegalStateException(e);
				}
			}
		}
		return Collections.unmodifiableMap(output);
	}

}
