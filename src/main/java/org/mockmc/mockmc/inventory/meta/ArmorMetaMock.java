package org.mockmc.mockmc.inventory.meta;

import org.bukkit.NamespacedKey;
import org.bukkit.configuration.serialization.DelegateDeserialization;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.trim.ArmorTrim;
import org.bukkit.inventory.meta.trim.TrimMaterial;
import org.bukkit.inventory.meta.trim.TrimPattern;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.mockmc.mockmc.inventory.SerializableMeta;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@DelegateDeserialization(SerializableMeta.class)
public class ArmorMetaMock extends ItemMetaMock
		implements
			org.mockmc.mockmc.generated.server.org.bukkit.inventory.meta.ArmorMetaBaseMock
{

	private ArmorTrim trim = null;

	public ArmorMetaMock()
	{
		super();
	}

	public ArmorMetaMock(ItemMeta meta)
	{
		super(meta);
		if (meta instanceof ArmorMetaMock armorMeta)
		{
			this.trim = armorMeta.getTrim();
		}
	}

	@Override
	public boolean hasTrim()
	{
		return trim != null;
	}

	@Override
	public void setTrim(@Nullable ArmorTrim trim)
	{
		this.trim = trim;
	}

	@Override
	@Nullable
	public ArmorTrim getTrim()
	{
		return this.trim;
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
		ArmorMetaMock meta = (ArmorMetaMock) obj;
		return super.equals(obj) && Objects.equals(this.trim, meta.trim);
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(super.hashCode(), trim);
	}

	@Override
	@SuppressWarnings(
	{"MethodDoesntCallSuperMethod", "java:S2975", "java:S1182"})
	@NotNull
	public ArmorMetaMock clone()
	{
		return new ArmorMetaMock(this);
	}

	@Override
	@NotNull
	public Map<String, Object> serialize()
	{
		Map<String, Object> serialized = super.serialize();
		if (hasTrim())
		{
			Map<String, Object> trimData = new HashMap<>();
			trimData.put("material", this.trim.getMaterial().getKey().toString());
			trimData.put("pattern", this.trim.getPattern().getKey().toString());
			serialized.put("trim", trimData);
		}
		return serialized;
	}

	@Override
	protected void deserializeInternal(@NotNull Map<String, Object> args)
	{
		super.deserializeInternal(args);
		if (args.get("trim") instanceof Map<?, ?> rawTrimData)
		{
			Object rawMaterial = rawTrimData.get("material");
			Object rawPattern = rawTrimData.get("pattern");
			if (rawMaterial != null && rawPattern != null)
			{
				NamespacedKey materialKey = NamespacedKey.fromString(rawMaterial.toString());
				NamespacedKey patternKey = NamespacedKey.fromString(rawPattern.toString());
				io.papermc.paper.registry.RegistryAccess registryAccess = io.papermc.paper.registry.RegistryAccess
						.registryAccess();
				TrimMaterial trimMaterial = materialKey != null
						? registryAccess.getRegistry(io.papermc.paper.registry.RegistryKey.TRIM_MATERIAL)
								.get(materialKey)
						: null;
				TrimPattern trimPattern = patternKey != null
						? registryAccess.getRegistry(io.papermc.paper.registry.RegistryKey.TRIM_PATTERN).get(patternKey)
						: null;
				if (trimMaterial != null && trimPattern != null)
				{
					this.setTrim(new ArmorTrim(trimMaterial, trimPattern));
				}
			}
		}
	}

	@Override
	protected String getTypeName()
	{
		return "ARMOR";
	}

	/**
	 * This method is used to deserialize the armor meta.
	 *
	 * @param args
	 *            The data to be deserialized.
	 *
	 * @return The armor deserialized.
	 *
	 * @see org.bukkit.configuration.serialization.ConfigurationSerializable
	 */
	@NotNull
	@SuppressWarnings("java:S2176")
	public static ArmorMetaMock deserialize(@NotNull Map<String, Object> args)
	{
		ArmorMetaMock armorMetaMock = new ArmorMetaMock();
		armorMetaMock.deserializeInternal(args);
		return armorMetaMock;
	}
}
