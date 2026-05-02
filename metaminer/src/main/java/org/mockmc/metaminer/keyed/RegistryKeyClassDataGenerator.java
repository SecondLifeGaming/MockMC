package org.mockmc.metaminer.keyed;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import io.papermc.paper.registry.RegistryKey;
import org.bukkit.Keyed;
import org.mockmc.metaminer.DataGenerator;
import org.mockmc.metaminer.util.JsonUtil;

import java.io.File;
import java.util.Map;

public class RegistryKeyClassDataGenerator implements DataGenerator
{

	private final File dataFolder;

	public RegistryKeyClassDataGenerator(File pluginFolder)
	{
		this.dataFolder = new File(pluginFolder, "registries");
	}

	@Override
	public void generateData() throws Exception
	{
		File destinationFile = new File(dataFolder, "registry_key_class_relation.json");
		JsonObject rootObject = new JsonObject();

		for (Map.Entry<RegistryKey<? extends Keyed>, Class<?>> entry : KeyedClassTracker.getClassRegistryKeyRelation()
				.entrySet())
		{
			rootObject.add(entry.getKey().key().asString(), new JsonPrimitive(entry.getValue().getName()));
		}

		JsonUtil.dump(rootObject, destinationFile);
	}

}
