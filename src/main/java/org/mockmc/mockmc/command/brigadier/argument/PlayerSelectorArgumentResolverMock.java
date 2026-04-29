package org.mockmc.mockmc.command.brigadier.argument;

import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.argument.resolvers.selector.PlayerSelectorArgumentResolver;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.mockmc.mockmc.adventure.Languages;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@ApiStatus.Internal
public class PlayerSelectorArgumentResolverMock implements PlayerSelectorArgumentResolver
{

	private final String input;
	private final boolean single;

	public PlayerSelectorArgumentResolverMock(String input, boolean single)
	{
		this.input = input;
		this.single = single;
	}

	@Override
	public @NotNull List<Player> resolve(@NotNull CommandSourceStack source)
	{
		if (single && input.equals("@a"))
		{
			String message = Languages.getInstance().getOrDefault("command.expected.a_single_player",
					"Only one player is allowed, but the provided selector allows more than one");
			source.getSender().sendMessage(message);
			return Collections.emptyList();
		}

		List<Player> resolved;
		if (input.startsWith("@"))
		{
			resolved = resolveSelector(input, source);
		} else
		{
			Player player = Bukkit.getPlayer(input);
			resolved = player != null ? List.of(player) : Collections.emptyList();
		}
		if (resolved.isEmpty() && single)
		{
			String message = Languages.getInstance().getOrDefault("argument.entity.notfound.player",
					"No player was found");
			source.getSender().sendMessage(message);
		}
		return resolved;
	}

	private List<Player> resolveSelector(String selector, CommandSourceStack source)
	{
		switch (selector)
		{
			case "@a" :
				return new ArrayList<>(Bukkit.getOnlinePlayers());
			case "@p" :
			{
				org.bukkit.Location sourceLocation = source.getLocation();
				org.bukkit.World sourceWorld = sourceLocation.getWorld();
				if (sourceWorld == null)
				{
					return Collections.emptyList();
				}
				Player nearest = null;
				double nearestDistance = Double.MAX_VALUE;
				for (Player player : Bukkit.getOnlinePlayers())
				{
					if (!player.getWorld().equals(sourceWorld))
					{
						continue;
					}
					double distance = player.getLocation().distanceSquared(sourceLocation);
					if (distance < nearestDistance)
					{
						nearestDistance = distance;
						nearest = player;
					}
				}
				return nearest != null ? List.of(nearest) : Collections.emptyList();
			}
			case "@r" :
			{
				List<Player> players = new ArrayList<>(Bukkit.getOnlinePlayers());
				if (players.isEmpty())
				{
					return Collections.emptyList();
				}
				Collections.shuffle(players);
				return List.of(players.getFirst());
			}
			case "@s" :
				return (source.getExecutor() instanceof Player player) ? List.of(player) : Collections.emptyList();
			default :
				return Collections.emptyList();
		}
	}

}
