package org.mockmc.mockmc.attribute;

import com.google.gson.JsonObject;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.jetbrains.annotations.NotNull;
import org.mockmc.mockmc.util.OldKeyedEnumMock;

public class AttributeMock extends OldKeyedEnumMock<Attribute>
		implements
			org.mockmc.mockmc.generated.org.bukkit.attribute.AttributeBaseMock
{

	private final String translationKey;

	private final Sentiment sentiment;

	public AttributeMock(String name, int ordinal, NamespacedKey key, String translationKey, Sentiment sentiment)
	{
		super(name, ordinal, key);
		this.translationKey = translationKey;
		this.sentiment = sentiment;
	}

	public static AttributeMock from(JsonObject jsonObject)
	{
		String name = jsonObject.get("name").getAsString();
		NamespacedKey key = NamespacedKey.fromString(jsonObject.get("key").getAsString());
		int ordinal = jsonObject.get("ordinal").getAsInt();
		String translationKey = jsonObject.get("translationKey").getAsString();
		Sentiment sentiment = Sentiment.valueOf(jsonObject.get("sentiment").getAsString());
		return new AttributeMock(name, ordinal, key, translationKey, sentiment);
	}

	@Override
	@NotNull
	public String translationKey()
	{
		return this.translationKey;
	}

	/**
	 * @deprecated Use {@link #translationKey()} instead.
	 */
	@Override
	@Deprecated(forRemoval = true, since = "1.21.3")
	@NotNull
	public String getTranslationKey()
	{
		return this.translationKey;
	}

	@Override
	@NotNull
	public net.kyori.adventure.key.Key key()
	{
		return this.getKey();
	}

	@Override
	@NotNull
	public Sentiment getSentiment()
	{
		return sentiment;
	}
}
