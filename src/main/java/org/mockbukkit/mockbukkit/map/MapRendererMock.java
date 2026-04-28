package org.mockbukkit.mockbukkit.map;

import org.bukkit.entity.Player;
import org.bukkit.map.MapCanvas;
import org.bukkit.map.MapRenderer;
import org.bukkit.map.MapView;
import org.jetbrains.annotations.NotNull;
import org.mockbukkit.mockbukkit.generated.org.bukkit.map.MapRendererBaseMock;

/**
 * Mock implementation of a {@link MapRenderer}.
 *
 * @mockbukkit.version 1.21-1.0.0
 */
public class MapRendererMock extends MapRenderer implements MapRendererBaseMock
{

	@Override
	public void render(@NotNull MapView map, @NotNull MapCanvas canvas, @NotNull Player player)
	{
		// No-op implementation
	}

}
