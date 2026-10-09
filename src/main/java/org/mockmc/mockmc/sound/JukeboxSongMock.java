package org.mockmc.mockmc.sound;

import com.google.gson.JsonObject;
import org.bukkit.JukeboxSong;
import org.bukkit.NamespacedKey;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings(
{"deprecation", "removal", "unchecked"})
public class JukeboxSongMock implements JukeboxSong, org.mockmc.mockmc.generated.server.org.bukkit.JukeboxSongBaseMock
{

	private final NamespacedKey key;

	private final String translationKey;

	public JukeboxSongMock(NamespacedKey key, String translationKey)
	{
		this.translationKey = translationKey;
		this.key = key;
	}

	@ApiStatus.Internal
	public static JukeboxSongMock from(JsonObject jsonObject)
	{
		NamespacedKey key = NamespacedKey.fromString(jsonObject.get("key").getAsString());
		String translationKey = null;
		if (jsonObject.has("translationKey"))
		{
			translationKey = jsonObject.get("translationKey").getAsString();
		} else if (jsonObject.has("description"))
		{
			if (jsonObject.get("description").isJsonObject()
					&& jsonObject.getAsJsonObject("description").has("translate"))
			{
				translationKey = jsonObject.getAsJsonObject("description").get("translate").getAsString();
			} else if (jsonObject.get("description").isJsonPrimitive())
			{
				translationKey = jsonObject.get("description").getAsString();
			}
		}
		if (translationKey == null)
		{
			translationKey = "jukebox_song." + key.getNamespace() + "." + key.getKey();
		}
		return new JukeboxSongMock(key, translationKey);
	}

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
