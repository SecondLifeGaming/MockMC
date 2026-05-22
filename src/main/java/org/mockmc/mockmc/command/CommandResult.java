package org.mockmc.mockmc.command;

import com.google.common.base.Preconditions;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * Represents the result of a command invocation.
 *
 * @mockmc.version 1.21-1.0.0
 */
public class CommandResult
{

	private final boolean success;
	private final @NotNull MessageTarget sender;

	/**
	 * Constructs a new {@link CommandResult} with the provided parameters.
	 *
	 * @param success
	 *            Whether the command succeeded (returned true).
	 * @param sender
	 *            The message target who executed the command.
	 */
	@ApiStatus.Internal
	public CommandResult(boolean success, @NotNull MessageTarget sender)
	{
		Preconditions.checkNotNull(sender, "Sender cannot be null");
		this.success = success;
		this.sender = sender;
	}

	/**
	 * Check if the command executed successfully.
	 *
	 * @return {@code true} if the command executed successfully, {@code false} if a
	 *         problem occurred.
	 */
	public boolean hasSucceeded()
	{
		return success;
	}

	/**
	 * Asserts if the returned code of the executed command is not {@code true}.
	 */
	@Deprecated(forRemoval = true)
	public void assertSucceeded()
	{
		if (!success)
		{
			throw new AssertionError();
		}
	}

	/**
	 * Asserts if the returned code of the executed command is not {@code false}.
	 */
	@Deprecated(forRemoval = true)
	public void assertFailed()
	{
		if (success)
		{
			throw new AssertionError();
		}
	}

	/**
	 * Assets if the given message was not the next message send to the command
	 * sender.
	 *
	 * @param message
	 *            The message to check for.
	 * @see MessageTarget#nextMessage()
	 */
	@Deprecated(forRemoval = true)
	public void assertResponse(String message)
	{
		String received = sender.nextMessage();
		if (received != null)
		{
			if (!Objects.equals(message, received))
			{
				throw new AssertionError(String.format("Expected: %s but was: %s", message, received));
			}
		} else
		{
			throw new AssertionError("No more messages");
		}
	}

	/**
	 * Asserts if a given formatted message was not the next message sent to the
	 * command sender.
	 *
	 * @param format
	 *            The formatted message to check for.
	 * @param objects
	 *            The objects to place into the formatted message.
	 * @see #assertResponse(String)
	 * @see MessageTarget#nextMessage()
	 */
	@Deprecated(forRemoval = true)
	public void assertResponse(@NotNull String format, Object... objects)
	{
		assertResponse(String.format(format, objects));
	}

	/**
	 * Asserts if more messages have been sent to the command sender.
	 *
	 * @see MessageTarget#nextMessage()
	 */
	@Deprecated(forRemoval = true)
	public void assertNoResponse()
	{
		if (sender.nextMessage() != null)
		{
			throw new AssertionError("More messages");
		}
	}

	/**
	 * @return The sender of this command
	 */
	public @NotNull MessageTarget getSender()
	{
		return sender;
	}

}
