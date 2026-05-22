// Auto-generated mechanical sanity test for GameRuleBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

@SuppressWarnings(
{"deprecation", "removal", "java:S1874"})
class GameRuleBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		GameRuleBaseMock<?> mock = new Stub<>();
		assertNotNull(mock);
		assertSafeDefault(mock.getName());
		assertSafeDefault(mock.getType());
		assertSafeDefault(mock.getDefaultValue());
		assertSafeDefault(mock.translationKey());
	}

	private static class Stub<T> implements GameRuleBaseMock<T>
	{
	}
}
