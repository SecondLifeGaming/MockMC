package org.mockmc.mockmc.exception;

import org.jetbrains.annotations.NotNull;

/**
 * Thrown when MockMC fails to initialize ItemMeta.
 */
public class ItemMetaInitException extends RuntimeException
{

	/**
	 * Constructs a new runtime exception with the specified cause and a detail
	 * message.
	 *
	 * @param message
	 *            The detail message.
	 * @param cause
	 *            The cause of the exception.
	 */
	public ItemMetaInitException(String message, Throwable cause)
	{
		super(message, cause);
	}

	/**
	 * Constructs a new runtime exception with the specified cause and a detail
	 * message of (cause==null ? null : cause.toString())
	 *
	 * @param cause
	 *            The cause of the exception.
	 */
	public ItemMetaInitException(@NotNull Throwable cause)
	{
		super(cause);
	}

}
