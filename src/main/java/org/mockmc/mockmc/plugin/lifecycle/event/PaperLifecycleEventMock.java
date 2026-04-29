package org.mockmc.mockmc.plugin.lifecycle.event;

import io.papermc.paper.plugin.lifecycle.event.LifecycleEvent;

public interface PaperLifecycleEventMock extends LifecycleEvent
{

	default void invalidate()
	{
	}

}
