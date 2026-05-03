package org.mockmc.metaminer.tests;

import com.google.gson.JsonArray;
import org.bukkit.inventory.ItemType;
import org.mockmc.metaminer.DataGenerator;
import org.mockmc.metaminer.util.JsonUtil;

import java.io.File;

public class ItemStackMetaDataGenerator implements DataGenerator
{

	private final File folder;

	public ItemStackMetaDataGenerator(File folder)
	{
		this.folder = folder;
	}

	@Override
	public void generateData() throws java.io.IOException
	{
		try
		{
			JsonArray jsonArray = new JsonArray();
			for (ItemType itemType : ItemMetaClassFinder.getInduvidualMetaItemTypes())
			{
				jsonArray.add(itemType.getKey().asString());
			}
			File file = new File(folder, "metaItemTypes.json");
			JsonUtil.dump(jsonArray, file);
		}
		catch (Exception | LinkageError _)
		{
			// Skip if registry is not available
		}
	}

}
