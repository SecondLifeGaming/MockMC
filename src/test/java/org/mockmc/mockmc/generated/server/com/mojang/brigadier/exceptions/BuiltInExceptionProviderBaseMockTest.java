// Auto-generated mechanical sanity test for BuiltInExceptionProviderBaseMock
package org.mockmc.mockmc.generated.server.com.mojang.brigadier.exceptions;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class BuiltInExceptionProviderBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		BuiltInExceptionProviderBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.literalIncorrect());
		assertSafeDefault(mock.longTooLow());
		assertSafeDefault(mock.doubleTooLow());
		assertSafeDefault(mock.doubleTooHigh());
		assertSafeDefault(mock.floatTooLow());
		assertSafeDefault(mock.floatTooHigh());
		assertSafeDefault(mock.integerTooLow());
		assertSafeDefault(mock.integerTooHigh());
		assertSafeDefault(mock.longTooHigh());
		assertSafeDefault(mock.readerExpectedStartOfQuote());
		assertSafeDefault(mock.readerExpectedEndOfQuote());
		assertSafeDefault(mock.readerInvalidEscape());
		assertSafeDefault(mock.readerInvalidBool());
		assertSafeDefault(mock.readerInvalidInt());
		assertSafeDefault(mock.readerExpectedInt());
		assertSafeDefault(mock.readerInvalidLong());
		assertSafeDefault(mock.readerExpectedLong());
		assertSafeDefault(mock.readerInvalidDouble());
		assertSafeDefault(mock.readerExpectedDouble());
		assertSafeDefault(mock.readerInvalidFloat());
	}

	@Test
	void testSafeDefaultsPart2()
	{
		BuiltInExceptionProviderBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.readerExpectedFloat());
		assertSafeDefault(mock.readerExpectedBool());
		assertSafeDefault(mock.readerExpectedSymbol());
		assertSafeDefault(mock.dispatcherUnknownCommand());
		assertSafeDefault(mock.dispatcherUnknownArgument());
		assertSafeDefault(mock.dispatcherExpectedArgumentSeparator());
		assertSafeDefault(mock.dispatcherParseException());
	}

	private static class Stub implements BuiltInExceptionProviderBaseMock
	{
	}
}
