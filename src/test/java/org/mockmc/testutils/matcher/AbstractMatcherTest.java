package org.mockmc.testutils.matcher;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.StringDescription;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Base class for matcher tests.
 */
public abstract class AbstractMatcherTest
{

	/**
	 * Creates the matcher to test.
	 *
	 * @return The matcher.
	 */
	protected abstract Matcher<?> createMatcher();

	/**
	 * Asserts that the matcher matches the actual value.
	 *
	 * @param matcher
	 *            The matcher.
	 * @param actual
	 *            The actual value.
	 */
	protected void assertMatches(Matcher<?> matcher, Object actual)
	{
		assertTrue(matcher.matches(actual), () ->
		{
			Description description = new StringDescription();
			matcher.describeTo(description);
			Description mismatchDescription = new StringDescription();
			matcher.describeMismatch(actual, mismatchDescription);
			return "\nExpected: " + description + "\n     but: " + mismatchDescription;
		});
	}

	/**
	 * Asserts that the matcher does not match the actual value.
	 *
	 * @param matcher
	 *            The matcher.
	 * @param actual
	 *            The actual value.
	 */
	protected void assertDoesNotMatch(Matcher<?> matcher, Object actual)
	{
		assertFalse(matcher.matches(actual), () ->
		{
			Description description = new StringDescription();
			matcher.describeTo(description);
			return "\nExpected: not " + description + "\n     but: matched " + actual;
		});
	}

	/**
	 * Asserts that the matcher is null-safe.
	 */
	protected void testIsNullSafe()
	{
		assertNullSafe(createMatcher());
	}

	/**
	 * Asserts that the matcher is null-safe.
	 *
	 * @param matcher
	 *            The matcher.
	 */
	protected void assertNullSafe(Matcher<?> matcher)
	{
		assertDoesNotMatch(matcher, null);
	}

	/**
	 * Tests that the matcher copes with unknown types.
	 */
	protected void testCopesWithUnknownTypes()
	{
		assertDoesNotMatch(createMatcher(), new Object());
	}

	/**
	 * Asserts that the matcher has the expected description.
	 *
	 * @param expected
	 *            The expected description.
	 * @param matcher
	 *            The matcher.
	 */
	protected void assertDescription(String expected, Matcher<?> matcher)
	{
		Description description = new StringDescription();
		matcher.describeTo(description);
		assertTrue(description.toString().contains(expected),
				() -> "Expected description to contain: " + expected + "\n     but: was " + description);
	}

}
