// Auto-generated mechanical sanity test for NoteBlockBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.block.data.type;

import static org.junit.jupiter.api.Assertions.*;

import org.bukkit.block.data.type.NoteBlock;
import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class NoteBlockBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		NoteBlockBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getInstrument());
		assertSafeDefault(mock.getNote());
	}

	private static class Stub implements NoteBlockBaseMock
	{
		@Override
		public NoteBlock clone()
		{
			return null;
		}
	}
}
