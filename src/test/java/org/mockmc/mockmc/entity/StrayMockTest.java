package org.mockmc.mockmc.entity;

import org.bukkit.entity.Skeleton;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockmc.mockmc.MockMCExtension;
import org.mockmc.mockmc.MockMCInject;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SuppressWarnings(
{"deprecation", "removal", "unchecked"})
@ExtendWith(MockMCExtension.class)
class StrayMockTest
{

	@MockMCInject
	private StrayMock stray;

	@Test
	void testGetSkeletonType()
	{
		assertEquals(Skeleton.SkeletonType.STRAY, stray.getSkeletonType());
	}

}
