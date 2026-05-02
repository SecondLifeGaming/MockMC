package org.mockmc.metaminer.tests;

import org.mockmc.metaminer.DataGenerator;

import java.io.File;
import java.util.stream.Stream;

public class ItemStackTestDataGenerator implements DataGenerator
{

	private final File folder;

	public ItemStackTestDataGenerator(File pluginDataFolder)
	{
		this.folder = new File(pluginDataFolder, "itemstack");
	}

	@Override
	public void generateData() throws Exception
	{
		getGenerators().forEach(dataGenerator ->
		{
			try
			{
				dataGenerator.generateData();
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		});
	}

	private Stream<DataGenerator> getGenerators()
	{
		return Stream.of(new ItemStackSetTypeTestDataGenerator(folder), new ItemStackMetaDataGenerator(folder));
	}

}
