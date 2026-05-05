package org.mockmc.mockmc.util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.Getter;
import lombok.ToString;

import java.nio.charset.StandardCharsets;
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

	private static final Gson GSON = new Gson();

	@Getter
	private final Map<String, Object> data = new HashMap<>();

	/**
	 * Decodes a Base64 string into NBT state. In this mock implementation, we
	 * expect the Base64 to encode a JSON representation of the NBT data.
	 */
	public static NbtStateMock fromBase64(String base64)
	{
		NbtStateMock mock = new NbtStateMock();
		if (base64 == null || base64.isEmpty())
		{
			return mock;
		}
		byte[] bytes = Base64.getDecoder().decode(base64);
		String json = new String(bytes, StandardCharsets.UTF_8);
		Map<String, Object> decoded = GSON.fromJson(json, new TypeToken<Map<String, Object>>()
		{
		}.getType());
		if (decoded != null)
		{
			mock.data.putAll(decoded);
		}
		return mock;
	}

	/**
	 * Encodes the current state back to a Base64 NBT string (as JSON).
	 */
	public String toBase64()
	{
		String json = GSON.toJson(data);
		return Base64.getEncoder().encodeToString(json.getBytes(StandardCharsets.UTF_8));
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
