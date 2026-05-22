// Auto-generated mechanical sanity test for MusicInstrumentMetaBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.inventory.meta;

import static org.junit.jupiter.api.Assertions.*;

import org.bukkit.inventory.meta.MusicInstrumentMeta;
import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class MusicInstrumentMetaBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		MusicInstrumentMetaBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.clone());
		assertSafeDefault(mock.getInstrument());
	}

	private static class Stub implements MusicInstrumentMetaBaseMock
	{
		@Override
		public MusicInstrumentMeta clone()
		{
			return null;
		}
	}
}
