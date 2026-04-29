package org.mockmc.metaminer;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class StandaloneRunner
{
	public static void main(String[] args) throws IOException
	{
		File dataFolder = new File(args[0]);
		dataFolder.mkdirs();

		List<DataGenerator> generators = List.of(
				new BaseMockGenerator(dataFolder)
		);

		for (DataGenerator generator : generators)
		{
			System.out.println("Running " + generator.getClass().getSimpleName());
			generator.generateData();
		}
		System.out.println("Generation complete!");
	}
}
