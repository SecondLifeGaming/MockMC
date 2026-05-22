package org.mockmc.mockmc.inventory.meta.components;

import lombok.EqualsAndHashCode;
import com.google.common.base.Preconditions;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.bukkit.Color;
import org.bukkit.configuration.serialization.SerializableAs;
import org.bukkit.inventory.meta.components.CustomModelDataComponent;
import org.jetbrains.annotations.NotNullByDefault;
import org.mockmc.mockmc.inventory.SerializableMeta;
import org.mockmc.mockmc.util.NbtParser;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Mock implementation of {@link CustomModelDataComponent}.
 *
 * @mockmc.version 1.21-1.0.0
 */
@Builder
@AllArgsConstructor(access = lombok.AccessLevel.PRIVATE)
@NotNullByDefault
@EqualsAndHashCode
@SerializableAs("CustomModelData")
@SuppressWarnings("unchecked")
public class CustomModelDataComponentMock
		implements
			org.mockmc.mockmc.generated.server.org.bukkit.inventory.meta.components.CustomModelDataComponentBaseMock
{

	@Builder.Default
	private List<Float> floats = List.of();

	@Builder.Default
	private List<Boolean> flags = List.of();

	@Builder.Default
	private List<String> strings = List.of();

	@Builder.Default
	private List<Color> colors = List.of();

	@Override
	public List<Float> getFloats()
	{
		return this.floats;
	}

	@Override
	public void setFloats(List<Float> floats)
	{
		this.floats = List.copyOf(floats);
	}

	@Override
	public List<Boolean> getFlags()
	{
		return this.flags;
	}

	@Override
	public void setFlags(List<Boolean> flags)
	{
		this.flags = List.copyOf(flags);
	}

	@Override
	public List<String> getStrings()
	{
		return this.strings;
	}

	@Override
	public void setStrings(List<String> strings)
	{
		this.strings = List.copyOf(strings);
	}

	@Override
	public List<Color> getColors()
	{
		return this.colors;
	}

	@Override
	public void setColors(List<Color> colors)
	{
		this.colors = List.copyOf(colors);
	}

	@Override
	public Map<String, Object> serialize()
	{
		Map<String, Object> result = new LinkedHashMap<>();
		result.put("floats", this.getFloats());
		result.put("flags", this.getFlags());
		result.put("strings", this.getStrings());
		result.put("colors", this.getColors().stream().map(Color::serialize).toList());
		return result;
	}

	public static CustomModelDataComponentMock deserialize(Map<String, Object> map)
	{
		var floats = SerializableMeta.getList(Float.class, map, "floats");
		var flags = SerializableMeta.getList(Boolean.class, map, "flags");
		var strings = SerializableMeta.getList(String.class, map, "strings");
		var colorsRaw = SerializableMeta.getList(Object.class, map, "colors");
		var colors = NbtParser.parseList(colorsRaw, o ->
		{
			Preconditions.checkArgument(o instanceof Map<?, ?>, "Expected Map<?, ?> but got %s", o.getClass());
			return Color.deserialize((Map<String, Object>) o);
		});
		return CustomModelDataComponentMock.builder().floats(floats).flags(flags).strings(strings).colors(colors)
				.build();
	}

	public static CustomModelDataComponent useDefault()
	{
		return builder().build();
	}
}
