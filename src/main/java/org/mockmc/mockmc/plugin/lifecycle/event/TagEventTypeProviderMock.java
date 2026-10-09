package org.mockmc.mockmc.plugin.lifecycle.event;

public abstract class TagEventTypeProviderMock
		implements
			io.papermc.paper.plugin.lifecycle.event.types.TagEventTypeProvider
{
	@Override
	public <T> io.papermc.paper.plugin.lifecycle.event.types.LifecycleEventType.Prioritizable<io.papermc.paper.plugin.bootstrap.BootstrapContext, io.papermc.paper.plugin.lifecycle.event.registrar.ReloadableRegistrarEvent<io.papermc.paper.tag.PreFlattenTagRegistrar<T>>> preFlatten(
			io.papermc.paper.registry.RegistryKey<T> registryKey)
	{
		return null;
	}

	@Override
	public <T> io.papermc.paper.plugin.lifecycle.event.types.LifecycleEventType.Prioritizable<io.papermc.paper.plugin.bootstrap.BootstrapContext, io.papermc.paper.plugin.lifecycle.event.registrar.ReloadableRegistrarEvent<io.papermc.paper.tag.PostFlattenTagRegistrar<T>>> postFlatten(
			io.papermc.paper.registry.RegistryKey<T> registryKey)
	{
		return null;
	}
}
