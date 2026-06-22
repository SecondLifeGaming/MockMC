// Auto-generated mechanical sanity test for PaperLeashableBaseMock
package org.mockmc.mockmc.generated.server.io.papermc.paper.entity;

import static org.junit.jupiter.api.Assertions.*;

import net.minecraft.world.entity.Leashable;
import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

@SuppressWarnings("all")
class PaperLeashableBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		PaperLeashableBaseMock mock = new Stub();
		assertNotNull(mock);
		try
		{
			assertSafeDefault(mock.getHandle());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
		try
		{
			assertSafeDefault(mock.getLeashHolder());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
	}

	private static class Stub implements PaperLeashableBaseMock
	{
		@Override
		public Leashable getHandle()
		{
			return null;
		}
	}
}
