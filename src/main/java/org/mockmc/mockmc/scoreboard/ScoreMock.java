package org.mockmc.mockmc.scoreboard;

import org.bukkit.OfflinePlayer;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Mock implementation of a {@link Score}.
 */
public class ScoreMock implements Score, org.mockmc.mockmc.generated.org.bukkit.scoreboard.ScoreBaseMock
{

	private final ObjectiveMock objective;

	private final String entry;

	@Nullable
	private OfflinePlayer player = null;

	private int score = 0;

	private boolean set = false;

	/**
	 * Constructs a new {@link ScoreMock} for the provided objective with the
	 * specified entry.
	 *
	 * @param objective
	 *            The objective.
	 * @param entry
	 *            The entry.
	 */
	public ScoreMock(ObjectiveMock objective, String entry)
	{
		this.objective = objective;
		this.entry = entry;
	}

	/**
	 * Sets the player that this score is tracking.
	 *
	 * @param player
	 *            The player to track.
	 */
	public void setPlayer(OfflinePlayer player)
	{
		this.player = player;
	}

	@Override
	@Deprecated(since = "1.7.10")
	@NotNull
	public OfflinePlayer getPlayer()
	{
		return player;
	}

	@Override
	@NotNull
	public String getEntry()
	{
		return entry;
	}

	@Override
	@NotNull
	public ObjectiveMock getObjective()
	{
		return objective;
	}

	@Override
	public int getScore() throws IllegalStateException
	{
		if (objective.isRegistered())
		{
			return score;
		} else
		{
			throw new IllegalStateException("Objective is not registered");
		}
	}

	@Override
	public void setScore(int score) throws IllegalStateException
	{
		this.score = score;
		set = true;
	}

	@Override
	public boolean isScoreSet() throws IllegalStateException
	{
		return set;
	}

	@Override
	public Scoreboard getScoreboard()
	{
		return objective.getScoreboard();
	}

	@Override
	public void resetScore() throws IllegalStateException
	{
		score = 0;
		set = false;
	}
}
