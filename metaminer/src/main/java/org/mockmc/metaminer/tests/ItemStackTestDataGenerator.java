package org.mockmc.metaminer.tests;

import org.mockmc.metaminer.DataGenerator;

import java.io.File;
import java.util.stream.Stream;

public class ItemStackTestDataGenerator implements DataGenerator
{

	private static final java.util.logging.Logger LOGGER = java.util.logging.Logger.getLogger(ItemStackTestDataGenerator.class.getName());

	private final File folder;

	public ItemStackTestDataGenerator(File pluginDataFolder)
	{
		this.folder = new File(pluginDataFolder, "itemstack");
	}

	@Override
	public void generateData() throws java.io.IOException
	{
		getGenerators().forEach(dataGenerator ->
		{
			try
			{
				dataGenerator.generateData();
			}
			catch (Exception e)
			{
				LOGGER.log(java.util.logging.Level.SEVERE, e, () -> "Failed to generate data with " + dataGenerator.getClass().getSimpleName());
			}
		});
	}

	private Stream<DataGenerator> getGenerators()
	{
		return Stream.of(new ItemStackSetTypeTestDataGenerator(folder), new ItemStackMetaDataGenerator(folder));
	}

}
