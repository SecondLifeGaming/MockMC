package org.mockbukkit.mockbukkit.inventory.meta;

import org.bukkit.block.banner.Pattern;
import org.bukkit.configuration.serialization.DelegateDeserialization;
import org.bukkit.inventory.meta.BannerMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;
import org.mockbukkit.mockbukkit.inventory.SerializableMeta;
import org.mockbukkit.mockbukkit.util.NbtParser;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Mock implementation of an {@link BannerMeta}.
 *
 * @see ItemMetaMock
 */
@DelegateDeserialization(SerializableMeta.class)
@SuppressWarnings("unchecked")
public class BannerMetaMock extends ItemMetaMock
		implements
			org.mockbukkit.mockbukkit.generated.org.bukkit.inventory.meta.BannerMetaBaseMock
{
	private static final String PATTERNS = "patterns";

	private List<Pattern> patternList;

	/**
	 * Constructs a new {@link BannerMetaMock}.
	 */
	public BannerMetaMock()
	{
		super();
		this.patternList = new ArrayList<>();
	}

	/**
	 * Constructs a new {@link BannerMetaMock}, cloning the data from another.
	 *
	 * @param meta
	 *            The meta to clone.
	 */
	public BannerMetaMock(@NotNull ItemMeta meta)
	{
		super(meta);
		if (meta instanceof BannerMeta bannerMeta)
		{
			this.patternList = new ArrayList<>(bannerMeta.getPatterns());
		} else
		{
			this.patternList = new ArrayList<>();
		}
	}

	@Override
	@NotNull
	public List<Pattern> getPatterns()
	{
		return new ArrayList<>(this.patternList);
	}

	@Override
	public void setPatterns(@NotNull List<Pattern> patternList)
	{
		this.patternList = new ArrayList<>(patternList);
	}

	@Override
	public void addPattern(@NotNull Pattern pattern)
	{
		this.patternList.add(pattern);
	}

	@Override
	@NotNull
	public Pattern getPattern(int i)
	{
		return this.patternList.get(i);
	}

	@Override
	@NotNull
	public Pattern removePattern(int i)
	{
		return this.patternList.remove(i);
	}

	@Override
	public void setPattern(int i, @NotNull Pattern pattern)
	{
		this.patternList.set(i, pattern);
	}

	@Override
	public int numberOfPatterns()
	{
		return this.patternList.size();
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(super.hashCode(), this.patternList);
	}

	@Override
	public boolean equals(Object obj)
	{
		if (obj == null || obj.getClass() != this.getClass())
		{
			return false;
		}
		BannerMetaMock other = (BannerMetaMock) obj;
		return super.equals(obj) && Objects.equals(this.patternList, other.patternList);
	}

	@Override
	@SuppressWarnings(
	{"MethodDoesntCallSuperMethod", "java:S2975", "java:S1182"})
	@NotNull
	public BannerMetaMock clone()
	{
		return new BannerMetaMock(this);
	}

	@Override
	protected void deserializeInternal(Map<String, Object> serialized)
	{
		super.deserializeInternal(serialized);
		if (serialized.containsKey(PATTERNS))
		{
			this.setPatterns(
					((List<Map<String, Object>>) serialized.get(PATTERNS)).stream().map(Pattern::new).toList());
		}
	}

	/**
	 * Required method for Bukkit deserialization.
	 *
	 * @param args
	 *            A serialized BannerMetaMock object in a Map&lt;String, Object&gt;
	 *            format.
	 * @return A new instance of the BannerMetaMock class.
	 */
	@NotNull
	public static BannerMetaMock deserialize(@NotNull Map<String, Object> args)
	{
		BannerMetaMock serialMock = new BannerMetaMock();
		serialMock.deserializeInternal(args);
		serialMock.patternList = NbtParser.parseList(args.get(PATTERNS), o -> new Pattern((Map<String, Object>) o));
		if (serialMock.patternList == null)
		{
			serialMock.patternList = new ArrayList<>();
		}
		return serialMock;
	}

	/**
	 * Serializes the properties of an BannerMetaMock to a HashMap. Unimplemented
	 * properties are not present in the map.
	 *
	 * @return A HashMap of String, Object pairs representing the BannerMetaMock.
	 */
	@Override
	@NotNull
	public Map<String, Object> serialize()
	{
		final Map<String, Object> serialized = super.serialize();
		serialized.put(PATTERNS, this.patternList.stream().map(Pattern::serialize).toList());
		return serialized;
	}

	@Override
	protected String getTypeName()
	{
		return "BANNER";
	}
}
