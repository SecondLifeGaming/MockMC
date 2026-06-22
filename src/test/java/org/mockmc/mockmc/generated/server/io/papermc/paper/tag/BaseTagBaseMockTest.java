// Auto-generated mechanical sanity test for BaseTagBaseMock
package org.mockmc.mockmc.generated.server.io.papermc.paper.tag;

import static org.junit.jupiter.api.Assertions.*;

import io.papermc.paper.tag.BaseTag;
import org.bukkit.Keyed;
import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

@SuppressWarnings("all")
class BaseTagBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		BaseTagBaseMock<?, ?> mock = new Stub<>();
		assertNotNull(mock);
		try
		{
			assertSafeDefault(mock.getKey());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
		try
		{
			assertSafeDefault(mock.lock());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
		try
		{
			assertSafeDefault(mock.getValues());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
	}

	private static class Stub<T extends Keyed, C extends BaseTag<T, C>> implements BaseTagBaseMock<T, C>
	{
	}
}
