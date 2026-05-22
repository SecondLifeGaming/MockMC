// Auto-generated mechanical sanity test for ArgumentTypeBaseMock
package org.mockmc.mockmc.generated.server.com.mojang.brigadier.arguments;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class ArgumentTypeBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		ArgumentTypeBaseMock<?> mock = new Stub<>();
		assertNotNull(mock);
		assertSafeDefault(mock.getExamples());
	}

	private static class Stub<T> implements ArgumentTypeBaseMock<T>
	{
	}
}
