package org.mockmc.mockmc.util;

import lombok.Getter;
import lombok.ToString;

import java.io.*;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

/**
 * Handles Base64 encoded NBT state for complex entities. Aiming for 1-1
 * byte-perfect cloning of Vanilla/Paper state.
 */
@ToString
public class NbtStateMock
{

	@Getter
	private final Map<String, Object> data = new HashMap<>();

	/**
	 * Decodes a Base64 string into NBT state. Note: This is a simplified
	 * implementation for mocking purposes. In a full implementation, this would use
	 * a real NBT decoder.
	 */
	public static NbtStateMock fromBase64(String base64) throws IOException
	{
		NbtStateMock mock = new NbtStateMock();
		byte[] bytes = Base64.getDecoder().decode(base64);

		try (DataInputStream in = new DataInputStream(new ByteArrayInputStream(bytes)))
		{
			// Placeholder for NBT decoding logic
			// For now, we just log that we received data.
			// Full implementation would traverse the NBT tree.
		}
		return mock;
	}

	/**
	 * Encodes the current state back to a Base64 NBT string.
	 */
	public String toBase64() throws IOException
	{
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		try (DataOutputStream out = new DataOutputStream(bos))
		{
			// Placeholder for NBT encoding logic
		}
		return Base64.getEncoder().encodeToString(bos.toByteArray());
	}

	public void set(String key, Object value)
	{
		data.put(key, value);
	}

	public Object get(String key)
	{
		return data.get(key);
	}

	public boolean has(String key)
	{
		return data.containsKey(key);
	}
}
