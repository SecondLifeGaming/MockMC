package org.mockmc.metaminer.translation;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import org.mockmc.metaminer.DataGenerator;
import org.mockmc.metaminer.util.JsonUtil;

import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class TranslationDataGenerator implements DataGenerator
{

	private final File dataFolder;

	public TranslationDataGenerator(File dataFolder)
	{
		this.dataFolder = new File(dataFolder, "translations");
	}

	@Override
	public void generateData() throws Exception
	{
		try (InputStream inputStream = this.getClass().getResourceAsStream("/assets/minecraft/lang/en_us.json"))
		{
			if (inputStream == null)
			{
				return;
			}
			JsonElement jsonElement = JsonParser
					.parseReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
			JsonObject jsonObject = new JsonObject();
			jsonObject.add("rightToLeft", new JsonPrimitive(false));
			jsonObject.add("translations", jsonElement);
			JsonUtil.dump(jsonObject, new File(dataFolder, "en-us.json"));
		}
	}

}
