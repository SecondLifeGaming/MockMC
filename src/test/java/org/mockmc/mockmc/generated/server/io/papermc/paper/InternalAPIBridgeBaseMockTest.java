// Auto-generated mechanical sanity test for InternalAPIBridgeBaseMock
package org.mockmc.mockmc.generated.server.io.papermc.paper;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

@SuppressWarnings(
{"deprecation", "removal", "java:S1874"})
class InternalAPIBridgeBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		InternalAPIBridgeBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.constructLegacyCustomBiome());
		assertSafeDefault(mock.defaultMannequinProfile());
		assertSafeDefault(mock.validMannequinPoses());
		assertSafeDefault(mock.defaultMannequinDescription());
		assertSafeDefault(mock.allSkinParts());
	}

	private static class Stub implements InternalAPIBridgeBaseMock
	{
	}
}
