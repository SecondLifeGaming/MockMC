package org.mockmc.mockmc.matcher.entity;

import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Mob;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * Advanced matcher for checking the state of an {@link Entity}.
 *
 * @mockmc.version 1.21-1.0.0
 */
public class EntityStateMatcher<T extends Entity> extends TypeSafeMatcher<T>
{

	private final List<Predicate<T>> predicates = new ArrayList<>();
	private final List<String> descriptions = new ArrayList<>();

	public EntityStateMatcher()
	{
	}

	public EntityStateMatcher<T> withTarget(@NotNull Matcher<LivingEntity> targetMatcher)
	{
		predicates.add(entity ->
		{
			if (entity instanceof Mob mob)
			{
				return targetMatcher.matches(mob.getTarget());
			}
			return false;
		});
		descriptions.add("targeting " + targetMatcher);
		return this;
	}

	public EntityStateMatcher<T> withFilter(@NotNull Predicate<T> filter, @NotNull String description)
	{
		predicates.add(filter);
		descriptions.add(description);
		return this;
	}

	public EntityStateMatcher<T> isAware(boolean aware)
	{
		predicates.add(entity ->
		{
			if (entity instanceof Mob mob)
			{
				return mob.isAware() == aware;
			}
			return false;
		});
		descriptions.add(aware ? "is aware" : "is not aware");
		return this;
	}

	@Override
	protected boolean matchesSafely(T entity)
	{
		for (Predicate<T> predicate : predicates)
		{
			if (!predicate.test(entity))
			{
				return false;
			}
		}
		return true;
	}

	@Override
	public void describeTo(Description description)
	{
		description.appendText("Entity ");
		for (int i = 0; i < descriptions.size(); i++)
		{
			if (i > 0)
				description.appendText(" and ");
			description.appendText(descriptions.get(i));
		}
	}

	public static <T extends Entity> EntityStateMatcher<T> hasState()
	{
		return new EntityStateMatcher<>();
	}
}
