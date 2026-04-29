package org.mockmc.mockmc.boss;

import org.bukkit.NamespacedKey;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.boss.KeyedBossBar;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Mock implementation of a boss bar manager.
 */
public class BossBarManagerMock
{

	private final Map<NamespacedKey, KeyedBossBarMock> bossBars = new HashMap<>();

	/**
	 * Creates a new boss bar.
	 *
	 * @param title
	 *            The title of the boss bar.
	 * @param color
	 *            The color of the boss bar.
	 * @param style
	 *            The style of the boss bar.
	 * @param flags
	 *            The flags of the boss bar.
	 * @return The created boss bar.
	 */
	@NotNull
	public BossBar createBossBar(@Nullable String title, @NotNull BarColor color, @NotNull BarStyle style,
			@NotNull BarFlag... flags)
	{
		return new BossBarMock(title, color, style, flags);
	}

	/**
	 * Creates a new keyed boss bar.
	 *
	 * @param key
	 *            The key of the boss bar.
	 * @param title
	 *            The title of the boss bar.
	 * @param color
	 *            The color of the boss bar.
	 * @param style
	 *            The style of the boss bar.
	 * @param flags
	 *            The flags of the boss bar.
	 * @return The created boss bar.
	 */
	@NotNull
	public KeyedBossBar createBossBar(@NotNull NamespacedKey key, @Nullable String title, @NotNull BarColor color,
			@NotNull BarStyle style, @NotNull BarFlag... flags)

	{
		KeyedBossBarMock bar = new KeyedBossBarMock(key, title, color, style, flags);
		this.bossBars.put(key, bar);
		return bar;
	}

	/**
	 * @return An iterator over all keyed boss bars.
	 */
	@NotNull
	public Iterator<KeyedBossBar> getBossBars()
	{
		return this.bossBars.values().stream().map(KeyedBossBar.class::cast).iterator();
	}

	/**
	 * Gets a keyed boss bar by key.
	 *
	 * @param key
	 *            The key of the boss bar.
	 * @return The boss bar, or {@code null} if not found.
	 */
	@Nullable
	public KeyedBossBar getBossBar(@NotNull NamespacedKey key)
	{
		return this.bossBars.get(key);
	}

	/**
	 * Removes a keyed boss bar.
	 *
	 * @param key
	 *            The key of the boss bar.
	 * @return {@code true} if the boss bar was removed, {@code false} otherwise.
	 */
	public boolean removeBossBar(@NotNull NamespacedKey key)
	{
		return this.bossBars.remove(key) != null;
	}

}
