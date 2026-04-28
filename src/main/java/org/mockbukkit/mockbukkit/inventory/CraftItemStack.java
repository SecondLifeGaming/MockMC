package org.mockbukkit.mockbukkit.inventory;

/**
 * Dummy class to satisfy NBTAPI reflection.
 */
public class CraftItemStack
{
	public static Object asNMSCopy(org.bukkit.inventory.ItemStack item)
	{
		return item;
	}
}
