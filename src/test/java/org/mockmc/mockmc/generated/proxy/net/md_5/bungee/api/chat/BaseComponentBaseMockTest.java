// Auto-generated mechanical sanity test for BaseComponentBaseMock
package org.mockmc.mockmc.generated.proxy.net.md_5.bungee.api.chat;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class BaseComponentBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		BaseComponentBaseMock mock = new Stub<>();
		assertNotNull(mock);
		assertSafeDefault(mock.toString());
		assertSafeDefault(mock.duplicate());
		assertSafeDefault(mock.getExtra());
		assertSafeDefault(mock.getColor());
		assertSafeDefault(mock.getStyle());
		assertSafeDefault(mock.toLegacyText());
		assertSafeDefault(mock.getClickEvent());
		assertSafeDefault(mock.getHoverEvent());
		assertSafeDefault(mock.getColorRaw());
		assertSafeDefault(mock.getShadowColorRaw());
		assertSafeDefault(mock.getFontRaw());
		assertSafeDefault(mock.isBoldRaw());
		assertSafeDefault(mock.isItalicRaw());
		assertSafeDefault(mock.isUnderlinedRaw());
		assertSafeDefault(mock.isStrikethroughRaw());
		assertSafeDefault(mock.isObfuscatedRaw());
		assertSafeDefault(mock.getInsertion());
		assertSafeDefault(mock.duplicateWithoutFormatting());
		assertSafeDefault(mock.toPlainText());
		assertSafeDefault(mock.getShadowColor());
	}

	@Test
	void testSafeDefaultsPart2()
	{
		BaseComponentBaseMock mock = new Stub<>();
		assertNotNull(mock);
		assertSafeDefault(mock.getFont());
	}

	private static class Stub implements BaseComponentBaseMock
	{
	}
}
