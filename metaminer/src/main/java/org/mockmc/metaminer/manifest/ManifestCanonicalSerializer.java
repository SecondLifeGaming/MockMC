package org.mockmc.metaminer.manifest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HexFormat;
import java.util.List;
import java.util.Map;

/**
 * Deterministic serializer for API manifests and overlays.
 * Enforces:
 * 1. Strict UTF-8 without BOM.
 * 2. Strict UNIX LF (\n) line endings.
 * 3. Exactly one trailing newline.
 * 4. Exactly 2-space indentation.
 * 5. Lexicographical sorting of JSON object keys at all nesting depths.
 * 6. Canonical SHA-256 digest computation over emitted bytes.
 */
public final class ManifestCanonicalSerializer {

	private static final Gson GSON = new GsonBuilder().serializeNulls().create();

	private ManifestCanonicalSerializer() {}

	/**
	 * Serializes an ApiManifest into canonical JSON bytes.
	 */
	public static byte[] serializeToCanonicalBytes(ApiManifest manifest) {
		JsonElement rawTree = GSON.toJsonTree(manifest);
		JsonElement canonicalTree = sortJsonElement(rawTree);
		String formatted = formatCanonicalJson(canonicalTree, 0);
		String withTerminalLf = formatted.endsWith("\n") ? formatted : formatted + "\n";
		return withTerminalLf.getBytes(StandardCharsets.UTF_8);
	}

	/**
	 * Serializes a MockmcOverlay into canonical JSON bytes.
	 */
	public static byte[] serializeToCanonicalBytes(MockmcOverlay overlay) {
		JsonElement rawTree = GSON.toJsonTree(overlay);
		JsonElement canonicalTree = sortJsonElement(rawTree);
		String formatted = formatCanonicalJson(canonicalTree, 0);
		String withTerminalLf = formatted.endsWith("\n") ? formatted : formatted + "\n";
		return withTerminalLf.getBytes(StandardCharsets.UTF_8);
	}

	/**
	 * Computes the canonical SHA-256 hex digest of the serialized bytes.
	 */
	public static String computeSha256(byte[] canonicalBytes) {
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] hash = digest.digest(canonicalBytes);
			return HexFormat.of().formatHex(hash);
		} catch (NoSuchAlgorithmException e) {
			throw new IllegalStateException("SHA-256 algorithm not available", e);
		}
	}

	private static JsonElement sortJsonElement(JsonElement element) {
		if (element == null || element.isJsonNull() || element.isJsonPrimitive()) {
			return element;
		}
		if (element.isJsonArray()) {
			JsonArray array = element.getAsJsonArray();
			JsonArray sortedArray = new JsonArray(array.size());
			for (JsonElement child : array) {
				sortedArray.add(sortJsonElement(child));
			}
			return sortedArray;
		}
		if (element.isJsonObject()) {
			JsonObject obj = element.getAsJsonObject();
			List<String> keys = new ArrayList<>(obj.keySet());
			Collections.sort(keys);
			JsonObject sortedObj = new JsonObject();
			for (String key : keys) {
				sortedObj.add(key, sortJsonElement(obj.get(key)));
			}
			return sortedObj;
		}
		return element;
	}

	private static String formatCanonicalJson(JsonElement element, int indentLevel) {
		if (element == null || element.isJsonNull()) {
			return "null";
		}
		if (element.isJsonPrimitive()) {
			JsonPrimitive prim = element.getAsJsonPrimitive();
			if (prim.isString()) {
				return escapeJsonString(prim.getAsString());
			}
			return prim.toString();
		}
		String indent = "  ".repeat(indentLevel);
		String childIndent = "  ".repeat(indentLevel + 1);

		if (element.isJsonArray()) {
			JsonArray arr = element.getAsJsonArray();
			if (arr.isEmpty()) {
				return "[]";
			}
			StringBuilder sb = new StringBuilder("[\n");
			for (int i = 0; i < arr.size(); i++) {
				sb.append(childIndent);
				sb.append(formatCanonicalJson(arr.get(i), indentLevel + 1));
				if (i < arr.size() - 1) {
					sb.append(",");
				}
				sb.append("\n");
			}
			sb.append(indent).append("]");
			return sb.toString();
		}

		if (element.isJsonObject()) {
			JsonObject obj = element.getAsJsonObject();
			if (obj.isEmpty()) {
				return "{}";
			}
			StringBuilder sb = new StringBuilder("{\n");
			List<Map.Entry<String, JsonElement>> entries = new ArrayList<>(obj.entrySet());
			for (int i = 0; i < entries.size(); i++) {
				Map.Entry<String, JsonElement> entry = entries.get(i);
				sb.append(childIndent);
				sb.append(escapeJsonString(entry.getKey())).append(": ");
				sb.append(formatCanonicalJson(entry.getValue(), indentLevel + 1));
				if (i < entries.size() - 1) {
					sb.append(",");
				}
				sb.append("\n");
			}
			sb.append(indent).append("}");
			return sb.toString();
		}

		return element.toString();
	}

	private static String escapeJsonString(String value) {
		StringBuilder sb = new StringBuilder("\"");
		for (int i = 0; i < value.length(); i++) {
			char c = value.charAt(i);
			switch (c) {
				case '"' -> sb.append("\\\"");
				case '\\' -> sb.append("\\\\");
				case '\b' -> sb.append("\\b");
				case '\f' -> sb.append("\\f");
				case '\n' -> sb.append("\\n");
				case '\r' -> sb.append("\\r");
				case '\t' -> sb.append("\\t");
				default -> {
					if (c < 0x20) {
						sb.append(String.format("\\u%04x", (int) c));
					} else {
						sb.append(c);
					}
				}
			}
		}
		sb.append("\"");
		return sb.toString();
	}
}
