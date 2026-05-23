// Auto-generated mechanical sanity test for BuildableDataComponentBaseMock
package org.mockmc.mockmc.generated.server.io.papermc.paper.datacomponent;

import static org.junit.jupiter.api.Assertions.*;

import io.papermc.paper.datacomponent.BuildableDataComponent;
import io.papermc.paper.datacomponent.DataComponentBuilder;
import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class BuildableDataComponentBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		BuildableDataComponentBaseMock<?, ?> mock = new Stub<>();
		assertNotNull(mock);
		try
		{
			assertSafeDefault(mock.toBuilder());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
	}

	private static class Stub<C extends BuildableDataComponent<C, B>, B extends DataComponentBuilder<C>>
			implements
				BuildableDataComponentBaseMock<C, B>
	{
	}
}
