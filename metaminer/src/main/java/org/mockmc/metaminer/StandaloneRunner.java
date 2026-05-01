package org.mockmc.metaminer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class StandaloneRunner
{
	private static final Logger LOGGER = LoggerFactory.getLogger(StandaloneRunner.class);

	public static void main(String[] args) throws IOException
	{
		File dataFolder = new File(args[0]);
		dataFolder.mkdirs();

		List<DataGenerator> generators = List.of(
				new BaseMockGenerator(dataFolder),
				new BaseTestGenerator(dataFolder),
				new CoverageReportGenerator(dataFolder)
		);

		for (DataGenerator generator : generators)
		{
			LOGGER.info("Running {}", generator.getClass().getSimpleName());
			generator.generateData();
		}
		LOGGER.info("Generation complete!");
	}
}
