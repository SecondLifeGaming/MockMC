// Auto-generated mechanical sanity test for RegistrarEventBaseMock
package org.mockmc.mockmc.generated.server.io.papermc.paper.plugin.lifecycle.event.registrar;

import static org.junit.jupiter.api.Assertions.*;

import io.papermc.paper.plugin.lifecycle.event.registrar.Registrar;
import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class RegistrarEventBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		RegistrarEventBaseMock<?> mock = new Stub<>();
		assertNotNull(mock);
		assertSafeDefault(mock.registrar());
	}

	private static class Stub<R extends Registrar> implements RegistrarEventBaseMock<R>
	{
	}
}
