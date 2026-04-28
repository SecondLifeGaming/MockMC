package org.mockbukkit.mockbukkit.command;

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
	 */
	@Deprecated(forRemoval = true)
	default void assertSaid(@NotNull String expected)
	{
		assertSaid(LegacyComponentSerializer.legacySection().deserialize(expected));
	}

	/**
	 * Asserts that more messages were received by the message target.
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
