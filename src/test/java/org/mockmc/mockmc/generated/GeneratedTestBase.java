package org.mockmc.mockmc.generated;

import net.kyori.adventure.text.Component;
import org.bukkit.NamespacedKey;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockmc.mockmc.MockMCExtension;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Base class for all generated sanity tests. Provides helpers to verify "Safe
 * Defaults" as per the Engine First mission.
 */
@ExtendWith(MockMCExtension.class)
public abstract class GeneratedTestBase
{

	protected void assertSafeDefault(Object value)
	{
		assertNotNull(value, "Default return value should never be null");

		if (value instanceof Component component)
		{
			assertEquals(Component.empty(), component, "Default Component should be Component.empty()");
		} else if (value instanceof Collection<?> collection)
		{
			assertTrue(collection.isEmpty(), "Default Collection should be empty");
		} else if (value instanceof Map<?, ?> map)
		{
			assertTrue(map.isEmpty(), "Default Map should be empty");
		} else if (value instanceof Optional<?> optional)
		{
			assertTrue(optional.isEmpty(), "Default Optional should be empty");
		} else if (value instanceof NamespacedKey key)
		{
			assertEquals("mock", key.getKey(), "Default NamespacedKey should be 'mock'");
		}
	}

}
