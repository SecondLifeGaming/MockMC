package org.mockmc.mockmc.exception;

import org.jetbrains.annotations.NotNull;

/**
 * Thrown when MockMC fails to load internal data.
 */
public class InternalDataLoadException extends RuntimeException
{

	/**
	 * Constructs a new runtime exception with the specified detail message.
	 *
	 * @param message
	 *            The detail message.
	 */
	public InternalDataLoadException(String message)
	{
		super(message);
	}

	/**
	 * Constructs a new runtime exception with the specified cause and a detail
	 * message of (cause==null ? null : cause.toString())
	 *
	 * @param cause
	 *            The cause of the exception.
	 */
	public InternalDataLoadException(@NotNull Throwable cause)
	{
		super(cause);
	}

	/**
	 * Constructs a new runtime exception with the specified detail message and
	 * cause.
	 *
	 * @param message
	 *            The detail message.
	 * @param cause
	 *            The cause of the exception.
	 */
	public InternalDataLoadException(String message, Throwable cause)
	{
		super(message, cause);
	}

}
