package org.mockmc.mockmc.scheduler;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockmc.mockmc.MockMCExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockMCExtension.class)
class RepeatingTaskTest
{

	@Test
	void getScheduledTick_Start_IsEqualToDelay()
	{
		RepeatingTask task = new RepeatingTask(0, null, true, 10, 20, () ->
		{
		});
		assertEquals(10, task.getScheduledTick());
	}

	@Test
	void getScheduledTick_AfterUpdateScheduledTick_Changed()
	{
		RepeatingTask task = new RepeatingTask(0, null, true, 10, 20, () ->
		{
		});
		task.updateScheduledTick();
		assertEquals(30, task.getScheduledTick());
	}

	@Test
	void getPeriod_SomePeriod_ExactPeriod()
	{
		RepeatingTask task = new RepeatingTask(0, null, true, 10, 20, () ->
		{
		});
		assertEquals(20, task.getPeriod());
	}

}
