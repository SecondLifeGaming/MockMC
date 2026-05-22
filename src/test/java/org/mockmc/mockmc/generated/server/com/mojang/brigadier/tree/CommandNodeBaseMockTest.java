// Auto-generated mechanical sanity test for CommandNodeBaseMock
package org.mockmc.mockmc.generated.server.com.mojang.brigadier.tree;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class CommandNodeBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		CommandNodeBaseMock<?> mock = new Stub<>();
		assertNotNull(mock);
		assertSafeDefault(mock.getName());
		assertSafeDefault(mock.getChildren());
		assertSafeDefault(mock.getCommand());
		assertSafeDefault(mock.getExamples());
		assertSafeDefault(mock.getRedirect());
		assertSafeDefault(mock.getRequirement());
		assertSafeDefault(mock.getRedirectModifier());
		assertSafeDefault(mock.createBuilder());
		assertSafeDefault(mock.getUsageText());
	}

	private static class Stub<S> implements CommandNodeBaseMock<S>
	{
	}
}
