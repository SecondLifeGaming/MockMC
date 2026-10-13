package org.mockmc.mockmc.sound;

import com.google.gson.JsonObject;
import org.bukkit.NamespacedKey;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

public class JukeboxSongMock implements org.mockmc.mockmc.generated.server.org.bukkit.JukeboxSongBaseMock
{

	private final NamespacedKey key;

	private final String translationKey;

	public JukeboxSongMock(NamespacedKey key, String translationKey)
	{
		this.translationKey = translationKey;
		this.key = key;
	}

	private static final String DESCRIPTION = "description";

	@ApiStatus.Internal
	public static JukeboxSongMock from(JsonObject jsonObject)
	{
		NamespacedKey key = NamespacedKey.fromString(jsonObject.get("key").getAsString());
		String translationKey = null;
		if (jsonObject.has("translationKey"))
		{
			translationKey = jsonObject.get("translationKey").getAsString();
		} else if (jsonObject.has(DESCRIPTION))
		{
			if (jsonObject.get(DESCRIPTION).isJsonObject() && jsonObject.getAsJsonObject(DESCRIPTION).has("translate"))
			{
				translationKey = jsonObject.getAsJsonObject(DESCRIPTION).get("translate").getAsString();
			} else if (jsonObject.get(DESCRIPTION).isJsonPrimitive())
			{
				translationKey = jsonObject.get(DESCRIPTION).getAsString();
			}
		}
		if (translationKey == null)
		{
			translationKey = "jukebox_song." + key.getNamespace() + "." + key.getKey();
		}
		return new JukeboxSongMock(key, translationKey);
	}

	/**
	 * @deprecated Use modern JukeboxSong API.
	 * @mockmc.version 1.21
	 */
	@Override
	@Deprecated(forRemoval = true, since = "1.21")
	@NotNull
	public String getTranslationKey()
	{
		return this.translationKey;
	}

	@Override
	@NotNull
	public NamespacedKey getKey()
	{
		return this.key;
	}
}
