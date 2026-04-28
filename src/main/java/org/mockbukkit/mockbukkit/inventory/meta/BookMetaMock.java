package org.mockbukkit.mockbukkit.inventory.meta;
import java.util.Objects;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import org.bukkit.configuration.serialization.DelegateDeserialization;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.mockbukkit.mockbukkit.inventory.SerializableMeta;
import org.mockbukkit.mockbukkit.util.NbtParser;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Mock implementation of a {@link BookMeta}.
 *
 * @see ItemMetaMock
 */
@DelegateDeserialization(SerializableMeta.class)
@SuppressWarnings("deprecation")
public class BookMetaMock extends ItemMetaMock
		implements
			org.mockbukkit.mockbukkit.generated.org.bukkit.inventory.meta.KnowledgeBookMetaBaseMock,
			org.mockbukkit.mockbukkit.generated.org.bukkit.inventory.meta.BookMetaBaseMock
{

	@Nullable
	private String title;

	@NotNull
	private List<String> pages = new ArrayList<>();

	@Nullable
	private String author;

	@Nullable
	private Generation generation = null;

	/**
	 * Constructs a new {@link BookMetaMock}.
	 */
	public BookMetaMock()
	{
		super();
	}

	/**
	 * Constructs a new {@link BookMetaMock}, cloning the data from another.
	 *
	 * @param meta
	 *            The meta to clone.
	 */
	public BookMetaMock(@NotNull ItemMeta meta)
	{
		super(meta);
		if (meta instanceof BookMeta bookMeta)
		{
			this.title = bookMeta.getTitle();
			this.author = bookMeta.getAuthor();
			this.pages = new ArrayList<>(bookMeta.getPages());
			this.generation = bookMeta.getGeneration();
		}
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(author, pages, title, generation);
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
		{
			return true;
		}
		if (obj == null || obj.getClass() != this.getClass())
		{
			return false;
		}
		if (!super.equals(obj))
		{
			return false;
		}
		BookMetaMock other = (BookMetaMock) obj;
		return Objects.equals(this.title, other.title) && Objects.equals(this.author, other.author)
				&& Objects.equals(this.pages, other.pages) && Objects.equals(this.generation, other.generation);
	}

	@Override
	public boolean hasTitle()
	{
		return !Strings.isNullOrEmpty(this.title);
	}

	@Override
	public boolean hasPages()
	{
		return !this.pages.isEmpty();
	}

	@Override
	@Nullable
	public String getTitle()
	{
		return this.title;
	}

	@Override
	public boolean setTitle(@Nullable String title)
	{
		if (title == null)
		{
			this.title = null;
			return true;
		} else if (title.length() > 65535)
		{
			return false;
		} else
		{
			this.title = title;
			return true;
		}
	}

	@Override
	public boolean hasAuthor()
	{
		return !Strings.isNullOrEmpty(this.author);
	}

	@Override
	@Nullable
	public String getAuthor()
	{
		return author;
	}

	@Override
	public void setAuthor(@Nullable String author)
	{
		this.author = author;
	}

	/**
	 * @deprecated Use adventure API instead.
	 */
	@Override
	@Deprecated(since = "1.16")
	@NotNull
	public String getPage(int page)
	{
		Preconditions.checkArgument(this.isValidPage(page), "Invalid page number");
		return this.pages.get(page - 1);
	}

	private boolean isValidPage(int page)
	{
		return page > 0 && page <= this.pages.size();
	}

	/**
	 * @deprecated Use adventure API instead.
	 */
	@Override
	@Deprecated(since = "1.16")
	public void setPage(int page, @Nullable String text)
	{
		if (!this.isValidPage(page))
		{
			throw new IllegalArgumentException("Invalid page number " + page + "/" + this.pages.size());
		} else
		{
			String newText;
			if (text != null)
			{
				newText = text.length() > 32767 ? text.substring(0, 32767) : text;
			} else
			{
				newText = "";
			}
			this.pages.set(page - 1, newText);
		}
	}

	/**
	 * @deprecated Use adventure API instead.
	 */
	@Override
	@Deprecated(since = "1.16")
	@NotNull
	public List<String> getPages()
	{
		return this.pages;
	}

	/**
	 * @deprecated Use adventure API instead.
	 */
	@Override
	@Deprecated(since = "1.16")
	public void setPages(@NotNull List<String> pages)
	{
		this.pages.clear();
		for (String page : pages)
		{
			this.addPage(page == null ? "" : page);
		}
	}

	/**
	 * @deprecated Use adventure API instead.
	 */
	@Override
	@Deprecated(since = "1.16")
	public void setPages(String @NotNull [] pages)
	{
		this.pages.clear();
		this.addPage(pages);
	}

	/**
	 * @deprecated Use adventure API instead.
	 */
	@Deprecated(since = "1.16")
	public void addPage(@NotNull String page)
	{
		this.pages.add(page.length() > 32767 ? page.substring(0, 32767) : page);
	}

	/**
	 * @deprecated Use adventure API instead.
	 */
	@Override
	@Deprecated(since = "1.16")
	public void addPage(String @NotNull [] pages)
	{
		for (String page : pages)
		{
			this.addPage(page == null ? "" : page);
		}
	}

	@Override
	public int getPageCount()
	{
		return this.pages.size();
	}

	@Override
	@SuppressWarnings(
	{"MethodDoesntCallSuperMethod", "java:S2975", "java:S1182"})
	@NotNull
	public BookMetaMock clone()
	{
		return new BookMetaMock(this);
	}

	@Override
	public boolean hasGeneration()
	{
		return generation != null;
	}

	@Override
	@Nullable
	public Generation getGeneration()
	{
		return generation;
	}

	@Override
	public void setGeneration(@Nullable Generation generation)
	{
		this.generation = generation;
	}

	/**
	 * Required method for Bukkit deserialization.
	 *
	 * @param args
	 *            A serialized BookMetaMock object in a Map&lt;String, Object&gt;
	 *            format.
	 * @return A new instance of the BookMetaMock class.
	 */
	@NotNull
	public static BookMetaMock deserialize(@NotNull Map<String, Object> args)
	{
		BookMetaMock serialMock = new BookMetaMock();
		serialMock.deserializeInternal(args);
		serialMock.title = NbtParser.parseString(args.get("title"));
		serialMock.author = NbtParser.parseString(args.get("author"));
		serialMock.pages = NbtParser.parseList(args.get("pages"), NbtParser::parseString);
		serialMock.generation = NbtParser.parseEnum(args.get("generation"), BookMeta.Generation.class);
		return serialMock;
	}

	/**
	 * Serializes the properties of an BookMetaMock to a HashMap. Unimplemented
	 * properties are not present in the map.
	 *
	 * @return A HashMap of String, Object pairs representing the BookMetaMock.
	 */
	@Override
	@NotNull
	public Map<String, Object> serialize()
	{
		final Map<String, Object> serialized = super.serialize();
		if (this.title != null)
		{
			serialized.put("title", this.title);
		}
		if (this.author != null)
		{
			serialized.put("author", this.author);
		}
		serialized.put("pages", this.pages);
		if (this.generation != null)
		{
			serialized.put("generation", this.generation);
		}
		return serialized;
	}

	@Override
	protected String getTypeName()
	{
		return "BOOK";
	}
}
