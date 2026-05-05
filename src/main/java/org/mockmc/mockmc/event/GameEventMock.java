package org.mockmc.mockmc.event;

import com.google.common.base.Preconditions;
import com.google.gson.JsonObject;
import org.bukkit.GameEvent;
import org.bukkit.NamespacedKey;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

public class GameEventMock extends GameEvent implements org.mockmc.mockmc.generated.server.org.bukkit.GameEventBaseMock
{

	@Override
	public int getRange()
	{
		return 0;
	}

	@Override
	public int getVibrationLevel()
	{
		return 0;
	}

	private final NamespacedKey key;

	/**
	 * @param key
	 *            A namespaced key
	 */
	public GameEventMock(NamespacedKey key)
	{
		this.key = key;
	}

	/**
	 * @param data
	 *            Json data
	 * @deprecated Use {@link #GameEventMock(NamespacedKey)} instead.
	 */
	@Deprecated(forRemoval = true)
	public GameEventMock(JsonObject data)
	{
		this.key = NamespacedKey.fromString(data.get("key").getAsString());
	}

	@Override
	@NotNull
	public NamespacedKey getKey()
	{
		return key;
	}

	@ApiStatus.Internal
	public static GameEventMock from(JsonObject data)
	{
		Preconditions.checkNotNull(data);
		Preconditions.checkArgument(data.has("key"), "Missing json key");
		NamespacedKey key = NamespacedKey.fromString(data.get("key").getAsString());
		return new GameEventMock(key);
	}
}
