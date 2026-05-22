// Auto-generated mechanical sanity test for BaseTagBaseMock
package org.mockmc.mockmc.generated.server.io.papermc.paper.tag;

import static org.junit.jupiter.api.Assertions.*;

import io.papermc.paper.tag.BaseTag;
import org.bukkit.Keyed;
import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class BaseTagBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		BaseTagBaseMock<?, ?> mock = new Stub<>();
		assertNotNull(mock);
		assertSafeDefault(mock.getKey());
		assertSafeDefault(mock.lock());
		assertSafeDefault(mock.getValues());
	}

	private static class Stub<T extends Keyed, C extends BaseTag<T, C>> implements BaseTagBaseMock<T, C>
	{
	}
}
