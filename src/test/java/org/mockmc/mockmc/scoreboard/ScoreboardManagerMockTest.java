package org.mockmc.mockmc.scoreboard;

import org.bukkit.scoreboard.Scoreboard;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockmc.mockmc.MockMCExtension;
import org.mockmc.mockmc.MockMCInject;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertSame;

@ExtendWith(MockMCExtension.class)
class ScoreboardManagerMockTest
{

	@MockMCInject
	private ScoreboardManagerMock manager;

	@Test
	void getMainScoreboard_NotNull()
	{
		assertNotNull(manager.getMainScoreboard());
	}

	@Test
	void getMainScoreboard_MultipleCalls_ReturnsSame()
	{
		Scoreboard scoreboardA = manager.getMainScoreboard();
		Scoreboard scoreboardB = manager.getMainScoreboard();
		assertSame(scoreboardA, scoreboardB);
	}

	@Test
	void getNewScoreboard_NotNull()
	{
		assertNotNull(manager.getNewScoreboard());
	}

	@Test
	void getNewScoreboard_MultipleCalls_ReturnsDifferentInstances()
	{
		Scoreboard scoreboardA = manager.getNewScoreboard();
		Scoreboard scoreboardB = manager.getNewScoreboard();
		assertNotSame(scoreboardA, scoreboardB);
	}

}
