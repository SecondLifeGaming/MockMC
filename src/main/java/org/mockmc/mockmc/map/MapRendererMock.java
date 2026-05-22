package org.mockmc.mockmc.map;

import org.bukkit.entity.Player;
import org.bukkit.map.MapCanvas;
import org.bukkit.map.MapRenderer;
import org.bukkit.map.MapView;
import org.jetbrains.annotations.NotNull;
import org.mockmc.mockmc.generated.server.org.bukkit.map.MapRendererBaseMock;

/**
 * Mock implementation of a {@link MapRenderer}.
 *
 * @mockmc.version 1.21-1.0.0
 */
public class MapRendererMock extends MapRenderer implements MapRendererBaseMock
{

	@Override
	public void render(@NotNull MapView map, @NotNull MapCanvas canvas, @NotNull Player player)
	{
		// No-op implementation
	}

}
