// Auto-generated mechanical sanity test for CustomArgumentTypeBaseMock
package org.mockmc.mockmc.generated.server.io.papermc.paper.command.brigadier.argument;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class CustomArgumentTypeBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		CustomArgumentTypeBaseMock<?, ?> mock = new Stub<>();
		assertNotNull(mock);
		assertSafeDefault(mock.getNativeType());
		assertSafeDefault(mock.getExamples());
	}

	private static class Stub<T, N> implements CustomArgumentTypeBaseMock<T, N>
	{
	}
}
