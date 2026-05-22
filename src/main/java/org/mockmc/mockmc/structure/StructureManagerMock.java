package org.mockmc.mockmc.structure;

import com.google.common.base.Preconditions;
import org.bukkit.NamespacedKey;
import org.bukkit.structure.Structure;
import org.bukkit.structure.StructureManager;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.mockmc.mockmc.exception.UnimplementedOperationException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class StructureManagerMock
		implements
			StructureManager,
			org.mockmc.mockmc.generated.server.org.bukkit.structure.StructureManagerBaseMock
{

	private static final String KEY_CANNOT_BE_NULL = "NamespacedKey structureKey cannot be null";
	private final Map<NamespacedKey, Structure> structures = new HashMap<>();

	@Override
	@NotNull
	public Map<NamespacedKey, Structure> getStructures()
	{
		return Collections.unmodifiableMap(structures);
	}

	@Override
	@Nullable
	public Structure getStructure(@NotNull NamespacedKey structureKey)
	{
		Preconditions.checkArgument(structureKey != null, KEY_CANNOT_BE_NULL);
		return structures.get(structureKey);
	}

	@Override
	@Nullable
	public Structure registerStructure(@NotNull NamespacedKey structureKey, @NotNull Structure structure)
	{
		Preconditions.checkArgument(structureKey != null, KEY_CANNOT_BE_NULL);
		Preconditions.checkArgument(structure != null, "Structure structure cannot be null");
		return structures.put(structureKey, structure);
	}

	@Override
	@Nullable
	public Structure unregisterStructure(@NotNull NamespacedKey structureKey)
	{
		Preconditions.checkArgument(structureKey != null, KEY_CANNOT_BE_NULL);
		return structures.remove(structureKey);
	}

	@Override
	@Nullable
	public Structure loadStructure(@NotNull NamespacedKey structureKey, boolean register)
	{
		Preconditions.checkArgument(structureKey != null, KEY_CANNOT_BE_NULL);
		throw new UnimplementedOperationException("Load structure was not implemented yet.");
	}

	@Override
	@Nullable
	public Structure loadStructure(@NotNull NamespacedKey structureKey)
	{
		return this.loadStructure(structureKey, true);
	}

	@Override
	public void saveStructure(@NotNull NamespacedKey structureKey)
	{
		Preconditions.checkArgument(structureKey != null, KEY_CANNOT_BE_NULL);
		throw new UnimplementedOperationException("Save structure was not implemented yet.");
	}

	@Override
	public void saveStructure(@NotNull NamespacedKey structureKey, @NotNull Structure structure) throws IOException
	{
		Preconditions.checkArgument(structureKey != null, "NamespacedKey structure cannot be null");
		Preconditions.checkArgument(structure != null, "Structure cannot be null");
		File structureFile = this.getStructureFile(structureKey);
		Files.createDirectories(structureFile.toPath().getParent());
		this.saveStructure(structureFile, structure);
	}

	@Override
	public void deleteStructure(@NotNull NamespacedKey structureKey)
	{
		this.deleteStructure(structureKey, true);
	}

	@Override
	public void deleteStructure(@NotNull NamespacedKey structureKey, boolean unregister)
	{
		Preconditions.checkArgument(structureKey != null, KEY_CANNOT_BE_NULL);
		throw new UnimplementedOperationException("Delete structure was not implemented yet.");
	}

	@Override
	@NotNull
	public File getStructureFile(@NotNull NamespacedKey structureKey)
	{
		Preconditions.checkArgument(structureKey != null, KEY_CANNOT_BE_NULL);
		throw new UnimplementedOperationException("Get structure file was not implemented yet.");
	}

	@Override
	@NotNull
	public Structure loadStructure(@NotNull File file) throws IOException
	{
		Preconditions.checkArgument(file != null, "File cannot be null");
		FileInputStream fileinputstream = new FileInputStream(file);
		return this.loadStructure(fileinputstream);
	}

	@Override
	@NotNull
	public Structure loadStructure(@NotNull InputStream inputStream)
	{
		Preconditions.checkArgument(inputStream != null, "inputStream cannot be null");
		throw new UnimplementedOperationException("Load structure was not implemented yet.");
	}

	@Override
	public void saveStructure(@NotNull File file, @NotNull Structure structure) throws IOException
	{
		Preconditions.checkArgument(file != null, "file cannot be null");
		Preconditions.checkArgument(structure != null, "structure cannot be null");
		FileOutputStream fileoutputstream = new FileOutputStream(file);
		this.saveStructure(fileoutputstream, structure);
	}

	@Override
	public void saveStructure(@NotNull OutputStream outputStream, @NotNull Structure structure)
	{
		Preconditions.checkArgument(outputStream != null, "outputStream cannot be null");
		Preconditions.checkArgument(structure != null, "structure cannot be null");
		throw new UnimplementedOperationException("Save structure was not implemented yet.");
	}

	@Override
	@NotNull
	public Structure createStructure()
	{
		return new StructureMock();
	}

	@Override
	@NotNull
	public Structure copy(@NotNull Structure structure)
	{
		Preconditions.checkArgument(structure != null, "Structure cannot be null");
		return new StructureMock(structure);
	}
}
