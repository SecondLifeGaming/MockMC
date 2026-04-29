package io.papermc.paper.plugin.lifecycle.event.types;

import io.papermc.paper.plugin.lifecycle.event.LifecycleEvent;
import io.papermc.paper.plugin.lifecycle.event.LifecycleEventOwner;
import org.mockmc.mockmc.plugin.lifecycle.event.LifecycleEventRunnerMock;
import org.mockmc.mockmc.plugin.lifecycle.event.TagEventTypeProviderMock;
import org.mockmc.mockmc.plugin.lifecycle.event.types.MonitorableLifecycleEventTypeMock;
import org.mockmc.mockmc.plugin.lifecycle.event.types.PrioritizableLifecycleEventTypeMock;

public class LifecycleEventTypeProviderMock implements LifecycleEventTypeProvider
{

	private final TagEventTypeProviderMock tagEventProvider = new TagEventTypeProviderMock();

	@Override
	public <O extends LifecycleEventOwner, E extends LifecycleEvent> LifecycleEventType.Monitorable<O, E> monitor(
			String name, Class<? extends O> ownerType)
	{
		return LifecycleEventRunnerMock.INSTANCE.addEventType(new MonitorableLifecycleEventTypeMock<>(name, ownerType));
	}

	@Override
	public <O extends LifecycleEventOwner, E extends LifecycleEvent> LifecycleEventType.Prioritizable<O, E> prioritized(
			final String name, final Class<? extends O> ownerType)
	{
		return LifecycleEventRunnerMock.INSTANCE
				.addEventType(new PrioritizableLifecycleEventTypeMock.Simple<>(name, ownerType));
	}

	@Override
	public TagEventTypeProviderMock tagProvider()
	{
		return tagEventProvider;
	}

}
