package org.mockmc.mockmc;

import org.bukkit.Location;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockmc.mockmc.entity.ZombieMock;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockMCExtension.class)
class BenchmarkTest
{

	@MockMCInject
	private ServerMock server;

	@Test
	void benchmark_10000_Entities_100_Ticks()
	{
		long start = System.currentTimeMillis();
		for (int i = 0; i < 10000; i++)
		{
			server.addSimpleWorld("world").spawn(new Location(null, 0, 0, 0), ZombieMock.class);
		}
		long spawnEnd = System.currentTimeMillis();
		System.out.println("Spawned 10,000 zombies in " + (spawnEnd - start) + "ms");

		long tickStart = System.currentTimeMillis();
		server.getScheduler().performTicks(100);
		long tickEnd = System.currentTimeMillis();
		System.out.println("Performed 100 ticks (1M entity-ticks total) in " + (tickEnd - tickStart) + "ms");

		long total = tickEnd - tickStart;
		assertTrue(total < 5000, "Should handle 1M entity-ticks in under 5 seconds (current: " + total + "ms)");
	}

}
