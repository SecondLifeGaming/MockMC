package org.mockmc.mockmc.art;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.gson.GsonComponentSerializer;
import org.bukkit.Art;
import org.bukkit.NamespacedKey;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.mockmc.mockmc.util.OldKeyedEnumMock;
import java.util.Objects;

/**
 * Custom Art implementation for Paper 1.21.
 *
 * @mockmc.version 1.21-1.0.0
 */
@SuppressWarnings(
{"deprecation", "removal"})
public class ArtMock extends OldKeyedEnumMock<Art>
		implements
			Art,
			org.mockmc.mockmc.generated.server.org.bukkit.ArtBaseMock
{

	private final int blockHeight;
	private final int blockWidth;
	private final int id;
	private final NamespacedKey key;
	private final Key assetId;

	@Nullable
	private final Component author;

	@Nullable
	private final Component title;

	public ArtMock(String name, int ordinal, NamespacedKey key, int blockHeight, int blockWidth, int id, Key assetId,
			@Nullable Component author, @Nullable Component title)
	{
		super(name, ordinal, key);
		this.blockHeight = blockHeight;
		this.blockWidth = blockWidth;
		this.id = id;
		this.key = Objects.requireNonNull(key, "key cannot be null");
		this.assetId = Objects.requireNonNull(assetId, "assetId cannot be null");
		this.author = author;
		this.title = title;
	}

	/**
	 * Static factory to create an ArtMock from a JsonObject.
	 */
	public static ArtMock from(JsonObject jsonObject)
	{
		String name = jsonObject.get("name").getAsString();
		NamespacedKey key = NamespacedKey.fromString(jsonObject.get("key").getAsString());
		int ordinal = jsonObject.get("ordinal").getAsInt();
		int blockHeight = jsonObject.get("blockHeight").getAsInt();
		int blockWidth = jsonObject.get("blockWidth").getAsInt();
		int id = jsonObject.get("id").getAsInt();

		NamespacedKey assetIdString = NamespacedKey.fromString(jsonObject.get("assetId").getAsString());

		JsonElement authorElement = jsonObject.get("author");
		Component author = (authorElement != null && !authorElement.isJsonNull()
				? GsonComponentSerializer.gson().deserializeFromTree(authorElement)
				: Component.empty());

		JsonElement titleElement = jsonObject.get("title");
		Component title = (titleElement != null && !titleElement.isJsonNull()
				? GsonComponentSerializer.gson().deserializeFromTree(titleElement)
				: Component.empty());

		return new ArtMock(name, ordinal, key, blockHeight, blockWidth, id, assetIdString, author, title);
	}

	@Override
	public int getBlockWidth()
	{
		return this.blockWidth;
	}

	@Override
	public int getBlockHeight()
	{
		return this.blockHeight;
	}

	@Override
	public int getId()
	{
		return this.id;
	}

	@Override
	@Nullable
	public Component title()
	{
		return this.title;
	}

	@Override
	@Nullable
	public Component author()
	{
		return this.author;
	}

	@Override
	@NotNull
	public Key assetId()
	{
		return this.assetId;
	}

	/**
	 * @deprecated Use {@link #assetId()} instead as per the 1.21 API changes.
	 */
	@Override
	@Deprecated(since = "1.21", forRemoval = true)
	@NotNull
	@SuppressWarnings("removal")
	public Key key()
	{
		return this.key;
	}
}
