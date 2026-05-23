// Base class for mechanical sanity tests.
package org.mockmc.mockmc.generated;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockmc.mockmc.MockMCExtension;

@ExtendWith(MockMCExtension.class)
public abstract class GeneratedTestBase
{
	protected void assertSafeDefault(Object value)
	{
		// Sanity check for default values. For stubs, we mostly just care that they
		// don't throw.
	}
}
