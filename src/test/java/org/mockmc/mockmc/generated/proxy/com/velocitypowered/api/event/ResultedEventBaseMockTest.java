// Auto-generated mechanical sanity test for ResultedEventBaseMock
package org.mockmc.mockmc.generated.proxy.com.velocitypowered.api.event;

import static org.junit.jupiter.api.Assertions.*;

import com.velocitypowered.api.event.ResultedEvent;
import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class ResultedEventBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults() throws Exception
	{
		ResultedEventBaseMock mock = new Stub<>();
		assertNotNull(mock);
		assertSafeDefault(mock.getResult());
	}

	private static class Stub<R extends ResultedEvent.Result> implements ResultedEventBaseMock<R>
	{
	}
}
