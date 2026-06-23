package org.mockmc.mockmc.command;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

/**
 * Represents an object that can receive messages.
 */
@FunctionalInterface
public interface MessageTarget
{

	/**
	 * Returns the next message that was sent to the target.
	 *
	 * @return The next message sent to the target.
	 */
	@Nullable
	Component nextComponentMessage();

	/**
	 * Returns the next message that was sent to the target.
	 *
	 * @return The next message sent to the target.
	 */
	default @Nullable String nextMessage()
	{
		Component comp = nextComponentMessage();
		if (comp == null)
		{
			return null;
		}
		return LegacyComponentSerializer.legacySection().serialize(comp);
	}

	/**
	 * Asserts that a specific message was not received next by the message target.
	 *
	 * @param expected
	 *            The message that should have been received by the target.
	 * @deprecated Scheduled for removal.
	 */
	@Deprecated(forRemoval = true)
	default void assertSaid(@NotNull Component expected)
	{
		Component comp = nextComponentMessage();
		if (comp == null)
		{
			throw new AssertionError("No more messages were sent");
		} else
		{
			if (!Objects.equals(expected, comp))
			{
				throw new AssertionError(String.format("Expected: %s but was: %s", expected, comp));
			}
		}
	}

	/**
	 * Asserts that a specific message was not received next by the message target.
	 *
	 * @param expected
	 *            The message that should have been received by the target.
	 * @deprecated Scheduled for removal.
	 */
	@Deprecated(forRemoval = true)
	default void assertSaid(@NotNull String expected)
	{
		String actual = nextMessage();
		if (actual == null)
		{
			throw new AssertionError("No more messages were sent");
		}
		if (!Objects.equals(expected, actual))
		{
			throw new AssertionError(String.format("Expected: %s but was: %s", expected, actual));
		}
	}

	/**
	 * Asserts that more messages were received by the message target.
	 *
	 * @deprecated Scheduled for removal.
	 */
	@Deprecated(forRemoval = true)
	default void assertNoMoreSaid()
	{
		if (nextComponentMessage() != null)
		{
			throw new AssertionError("More messages were available");
		}
	}

}
