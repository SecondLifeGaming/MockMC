package org.mockbukkit.mockbukkit.art;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.gson.GsonComponentSerializer;
import org.bukkit.Art;
import org.bukkit.NamespacedKey;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.mockbukkit.mockbukkit.util.OldKeyedEnumMock;
import java.util.Objects;

@SuppressWarnings(
{"deprecation", "removal", "unchecked"})
public class ArtMock extends OldKeyedEnumMock<Art>
		implements
			Art,
			org.mockbukkit.mockbukkit.generated.org.bukkit.ArtBaseMock
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

	public static ArtMock from(JsonObject jsonObject)
	{
		String name = jsonObject.get("name").getAsString();
		NamespacedKey key = NamespacedKey.fromString(jsonObject.get("key").getAsString());
		int ordinal = jsonObject.get("ordinal").getAsInt();
		int blockHeight = jsonObject.get("blockHeight").getAsInt();
		int blockWidth = jsonObject.get("blockWidth").getAsInt();
		int id = jsonObject.get("id").getAsInt();
		@Nullable
		NamespacedKey assetIdString = NamespacedKey.fromString(jsonObject.get("assetId").getAsString());
		@Nullable
		JsonElement authorElement = jsonObject.get("author");
		@Nullable
		Component author = (authorElement != null
				? GsonComponentSerializer.gson().deserializeFromTree(authorElement)
				: Component.empty());
		@Nullable
		JsonElement titleElement = jsonObject.get("title");
		@Nullable
		Component title = (titleElement != null
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

	@Override
	@Deprecated(since = "1.21", forRemoval = true)
	@NotNull
	public Key key()
	{
		return this.key;
	}
}
