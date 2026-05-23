// Auto-generated mechanical sanity test for FluidDataBaseMock
package org.mockmc.mockmc.generated.server.io.papermc.paper.block.fluid;

import static org.junit.jupiter.api.Assertions.*;

import io.papermc.paper.block.fluid.FluidData;
import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class FluidDataBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		FluidDataBaseMock mock = new Stub();
		assertNotNull(mock);
		try
		{
			assertSafeDefault(mock.clone());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
		try
		{
			assertSafeDefault(mock.getFluidType());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
	}

	private static class Stub implements FluidDataBaseMock
	{
		@Override
		public FluidData clone()
		{
			return null;
		}
	}
}
