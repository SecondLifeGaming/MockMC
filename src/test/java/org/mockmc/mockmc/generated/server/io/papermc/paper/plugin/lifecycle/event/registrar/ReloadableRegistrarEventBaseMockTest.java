// Auto-generated mechanical sanity test for ReloadableRegistrarEventBaseMock
package org.mockmc.mockmc.generated.server.io.papermc.paper.plugin.lifecycle.event.registrar;

import static org.junit.jupiter.api.Assertions.*;

import io.papermc.paper.plugin.lifecycle.event.registrar.Registrar;
import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

@SuppressWarnings("all")
class ReloadableRegistrarEventBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		ReloadableRegistrarEventBaseMock<?> mock = new Stub<>();
		assertNotNull(mock);
		try
		{
			assertSafeDefault(mock.cause());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
	}

	private static class Stub<R extends Registrar> implements ReloadableRegistrarEventBaseMock<R>
	{
	}
}
