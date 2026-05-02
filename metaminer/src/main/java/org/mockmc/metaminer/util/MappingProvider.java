package org.mockmc.metaminer.util;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MappingProvider {
    private static final Logger LOGGER = Logger.getLogger(MappingProvider.class.getName());
    private static final String MANIFEST_URL = "https://launchermeta.mojang.com/mc/game/version_manifest_v2.json";

    private MappingProvider() {
        throw new UnsupportedOperationException("Utility class");
    }

    public static File getVanillaServer(String mcVersion, File cacheDir) throws IOException {
        File vanillaFile = new File(cacheDir, "vanilla-" + mcVersion + ".jar");
        if (vanillaFile.exists()) return vanillaFile;

        String versionUrl = getVersionUrl(mcVersion);
        String serverUrl = getDownloadUrl(versionUrl, "server");

        LOGGER.log(Level.INFO, "Downloading Vanilla Server {0}...", mcVersion);
        FileUtils.copyURLToFile(URI.create(serverUrl).toURL(), vanillaFile);
        return vanillaFile;
    }

    public static File getMappings(String mcVersion, File cacheDir) throws IOException {
        File mappingFile = new File(cacheDir, "mappings-" + mcVersion + ".txt");
        if (mappingFile.exists()) return mappingFile;

        String versionUrl = getVersionUrl(mcVersion);
        String mappingsUrl = getDownloadUrl(versionUrl, "server_mappings");

        LOGGER.log(Level.INFO, "Downloading Mojang Mappings {0}...", mcVersion);
        FileUtils.copyURLToFile(URI.create(mappingsUrl).toURL(), mappingFile);
        return mappingFile;
    }

    private static String getVersionUrl(String mcVersion) throws IOException {
        String manifestContent = IOUtils.toString(URI.create(MANIFEST_URL), StandardCharsets.UTF_8);
        JsonObject manifest = JsonParser.parseString(manifestContent).getAsJsonObject();
        JsonArray versions = manifest.getAsJsonArray("versions");
        for (JsonElement v : versions) {
            JsonObject version = v.getAsJsonObject();
            if (version.get("id").getAsString().equals(mcVersion)) {
                return version.get("url").getAsString();
            }
        }
        throw new IOException("Could not find Mojang metadata for version: " + mcVersion);
    }

    private static String getDownloadUrl(String versionUrl, String type) throws IOException {
        String versionContent = IOUtils.toString(URI.create(versionUrl), StandardCharsets.UTF_8);
        JsonObject versionData = JsonParser.parseString(versionContent).getAsJsonObject();
        return versionData.getAsJsonObject("downloads").getAsJsonObject(type).get("url").getAsString();
    }
}
