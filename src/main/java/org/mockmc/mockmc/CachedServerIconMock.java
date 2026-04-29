package org.mockmc.mockmc;

import com.google.common.base.Preconditions;
import org.bukkit.util.CachedServerIcon;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Base64;

/**
 * Mock implementation of a {@link CachedServerIcon}.
 */
public class CachedServerIconMock implements org.mockmc.mockmc.generated.org.bukkit.util.CachedServerIconBaseMock
{

	/**
	 * The prefix used for base 64 images.
	 */
	public static final String PNG_BASE64_PREFIX = "data:image/png;base64,";

	@Nullable
	private final String data;

	/**
	 * Constructs a new {@link CachedServerIconMock} with the provided data.
	 *
	 * @param data
	 *            The data to be later retrieved in {@link #getData()}.
	 */
	protected CachedServerIconMock(@Nullable String data)
	{
		this.data = data;
	}

	@Override
	@Nullable
	public String getData()
	{
		return this.data;
	}

	/**
	 * Creates a {@link CachedServerIconMock} from a {@link File}.
	 *
	 * @param file
	 *            The file to load.
	 * @return The created icon.
	 * @throws IOException
	 *             If the file could not be read.
	 */
	@NotNull
	public static CachedServerIconMock fromFile(@NotNull File file) throws IOException
	{
		Preconditions.checkNotNull(file, "file cannot be null");
		Preconditions.checkArgument(file.isFile(), file + " isn't a file");
		return fromBufferedImage(ImageIO.read(file));
	}

	/**
	 * Creates a {@link CachedServerIconMock} from a {@link BufferedImage}.
	 *
	 * @param image
	 *            The image to load.
	 * @return The created icon.
	 * @throws IOException
	 *             If the image could not be written to a buffer.
	 */
	@NotNull
	public static CachedServerIconMock fromBufferedImage(@NotNull BufferedImage image) throws IOException
	{
		Preconditions.checkNotNull(image, "image cannot be null");
		Preconditions.checkArgument(image.getWidth() == 64, "Image must be 64 pixels wide");
		Preconditions.checkArgument(image.getHeight() == 64, "Image must be 64 pixels high");
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ImageIO.write(image, "PNG", out);
		String encoded = PNG_BASE64_PREFIX + Base64.getEncoder().encodeToString(out.toByteArray());
		return new CachedServerIconMock(encoded);
	}
}
