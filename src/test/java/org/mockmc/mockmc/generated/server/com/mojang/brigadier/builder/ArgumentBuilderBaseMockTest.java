// Auto-generated mechanical sanity test for ArgumentBuilderBaseMock
package org.mockmc.mockmc.generated.server.com.mojang.brigadier.builder;

import static org.junit.jupiter.api.Assertions.*;

import com.mojang.brigadier.builder.ArgumentBuilder;
import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class ArgumentBuilderBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		ArgumentBuilderBaseMock<?, ?> mock = new Stub<>();
		assertNotNull(mock);
		assertSafeDefault(mock.build());
		assertSafeDefault(mock.getArguments());
		assertSafeDefault(mock.getCommand());
		assertSafeDefault(mock.getRedirect());
		assertSafeDefault(mock.getRequirement());
		assertSafeDefault(mock.getRedirectModifier());
	}

	private static class Stub<S, T extends ArgumentBuilder<S, T>> implements ArgumentBuilderBaseMock<S, T>
	{
	}
}
