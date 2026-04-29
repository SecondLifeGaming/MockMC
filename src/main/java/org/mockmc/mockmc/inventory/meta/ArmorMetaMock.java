package org.mockmc.mockmc.inventory.meta;

import org.bukkit.NamespacedKey;
import org.bukkit.Registry;
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
@SuppressWarnings(
{"deprecation", "removal", "unchecked"})
public class ArmorMetaMock extends ItemMetaMock
		implements
			org.mockmc.mockmc.generated.org.bukkit.inventory.meta.ArmorMetaBaseMock
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
			trimData.put("material", this.trim.getMaterial().key().toString());
			trimData.put("pattern", this.trim.getPattern().key().toString());
			serialized.put("trim", trimData);
		}
		return serialized;
	}

	@Override
	protected void deserializeInternal(@NotNull Map<String, Object> args)
	{
		super.deserializeInternal(args);
		if (args.containsKey("trim"))
		{
			Map<String, Object> trimData = (Map<String, Object>) args.get("trim");
			String materialKey = trimData.get("material").toString();
			String patternKey = trimData.get("pattern").toString();
			TrimMaterial trimMaterial = Registry.TRIM_MATERIAL.get(NamespacedKey.fromString(materialKey));
			TrimPattern trimPattern = Registry.TRIM_PATTERN.get(NamespacedKey.fromString(patternKey));
			if (trimMaterial != null && trimPattern != null)
			{
				this.setTrim(new ArmorTrim(trimMaterial, trimPattern));
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
	public static ArmorMetaMock deserialize(@NotNull Map<String, Object> args)
	{
		ArmorMetaMock armorMetaMock = new ArmorMetaMock();
		armorMetaMock.deserializeInternal(args);
		return armorMetaMock;
	}
}
