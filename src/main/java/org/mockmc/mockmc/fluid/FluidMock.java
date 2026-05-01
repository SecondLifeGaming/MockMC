package org.mockmc.mockmc.fluid;

import com.google.gson.JsonObject;
import org.bukkit.Fluid;
import org.bukkit.NamespacedKey;
import org.mockmc.mockmc.util.OldKeyedEnumMock;

@SuppressWarnings("removal")
public class FluidMock extends OldKeyedEnumMock<Fluid>
		implements
			Fluid,
			org.mockmc.mockmc.generated.server.org.bukkit.FluidBaseMock
{

	public FluidMock(String name, int ordinal, NamespacedKey key)
	{
		super(name, ordinal, key);
	}

	public static FluidMock from(JsonObject object)
	{
		String name = object.get("name").getAsString();
		NamespacedKey key = NamespacedKey.fromString(object.get("key").getAsString());
		int ordinal = object.get("ordinal").getAsInt();
		return new FluidMock(name, ordinal, key);
	}
}
