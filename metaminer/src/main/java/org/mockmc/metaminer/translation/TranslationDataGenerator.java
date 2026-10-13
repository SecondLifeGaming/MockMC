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
	private final String resourcePath;

	public TranslationDataGenerator(File dataFolder)
	{
		this(dataFolder, "assets/minecraft/lang/en_us.json");
	}

	public TranslationDataGenerator(File dataFolder, String resourcePath)
	{
		this.dataFolder = new File(dataFolder, "translations");
		this.resourcePath = resourcePath;
	}

	@Override
	public void generateData() throws java.io.IOException
	{
		InputStream inputStream = findTranslationStream();

		if (inputStream == null)
		{
			throw new java.io.IOException("Could not locate " + resourcePath + " in classloaders or jars/cache");
		}

		try (InputStream is = inputStream)
		{
			JsonElement jsonElement = JsonParser
					.parseReader(new InputStreamReader(is, StandardCharsets.UTF_8));
			JsonObject jsonObject = new JsonObject();
			jsonObject.add("rightToLeft", new JsonPrimitive(false));
			jsonObject.add("translations", jsonElement);
			JsonUtil.dump(jsonObject, new File(dataFolder, "en-us.json"));
		}
	}

	private InputStream findTranslationStream() throws java.io.IOException
	{
		InputStream stream = this.getClass().getResourceAsStream("/" + resourcePath);
		if (stream == null)
		{
			stream = org.bukkit.Bukkit.class.getResourceAsStream("/" + resourcePath);
		}
		if (stream == null)
		{
			stream = ClassLoader.getSystemResourceAsStream(resourcePath);
		}
		if (stream == null)
		{
			stream = ClassLoader.getSystemResourceAsStream("/" + resourcePath);
		}
		if (stream == null)
		{
			stream = findStreamInJarCache();
		}
		return stream;
	}

	private InputStream findStreamInJarCache() throws java.io.IOException
	{
		File jarsDir = new File("../jars/cache");
		if (!jarsDir.exists())
		{
			jarsDir = new File("jars/cache");
		}
		if (!jarsDir.exists())
		{
			return null;
		}
		File[] files = jarsDir.listFiles((dir, name) -> name.startsWith("remapped-paper-") && name.endsWith(".jar"));
		if (files == null || files.length == 0)
		{
			return null;
		}

		String primaryPrefix = resolvePrimaryPaperPrefix();
		java.util.Arrays.sort(files, (a, b) -> {
			if (primaryPrefix != null)
			{
				boolean aIsPrimary = a.getName().contains(primaryPrefix);
				boolean bIsPrimary = b.getName().contains(primaryPrefix);
				if (aIsPrimary != bIsPrimary)
				{
					return aIsPrimary ? -1 : 1;
				}
			}
			return b.getName().compareTo(a.getName());
		});

		for (File file : files)
		{
			try (java.util.jar.JarFile jarFile = new java.util.jar.JarFile(file))
			{
				java.util.jar.JarEntry entry = jarFile.getJarEntry(resourcePath);
				if (entry != null)
				{
					try (InputStream entryStream = jarFile.getInputStream(entry))
					{
						return new java.io.ByteArrayInputStream(entryStream.readAllBytes());
					}
				}
			}
		}
		return null;
	}

	private String resolvePrimaryPaperPrefix()
	{
		File manifestFile = new File("../jars/manifests/versions.json");
		if (!manifestFile.exists())
		{
			manifestFile = new File("jars/manifests/versions.json");
		}
		if (manifestFile.exists())
		{
			try (java.io.Reader reader = new java.io.FileReader(manifestFile, StandardCharsets.UTF_8))
			{
				JsonObject root = JsonParser.parseReader(reader).getAsJsonObject();
				JsonObject platforms = root.getAsJsonObject("platforms");
				if (platforms != null && platforms.has("paper"))
				{
					JsonObject paper = platforms.getAsJsonObject("paper");
					com.google.gson.JsonArray versions = paper.getAsJsonArray("versions");
					if (versions != null)
					{
						for (JsonElement el : versions)
						{
							JsonObject v = el.getAsJsonObject();
							if (v.has("primary") && v.get("primary").getAsBoolean())
							{
								String ver = v.get("version").getAsString();
								String build = v.has("build") ? v.get("build").getAsString() : "";
								return "remapped-paper-" + ver + "-" + build;
							}
						}
					}
				}
			}
			catch (Exception _)
			{
				// Fall back to default version ordering
			}
		}
		return null;
	}

}
