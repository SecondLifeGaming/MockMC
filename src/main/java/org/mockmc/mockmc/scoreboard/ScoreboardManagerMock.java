package org.mockmc.mockmc.scoreboard;

import org.bukkit.scoreboard.ScoreboardManager;
import org.jetbrains.annotations.NotNull;
import org.mockmc.mockmc.AsyncCatcher;

/**
 * Mock implementation of a {@link ScoreboardManager}.
 */
public class ScoreboardManagerMock
		implements
			ScoreboardManager,
			org.mockmc.mockmc.generated.server.org.bukkit.scoreboard.ScoreboardManagerBaseMock
{

	private final ScoreboardMock mainScoreboard = new ScoreboardMock();

	@Override
	@NotNull
	public ScoreboardMock getMainScoreboard()
	{
		return mainScoreboard;
	}

	@Override
	@NotNull
	public ScoreboardMock getNewScoreboard()
	{
		AsyncCatcher.catchOp("scoreboard registration");
		return new ScoreboardMock();
	}
}
