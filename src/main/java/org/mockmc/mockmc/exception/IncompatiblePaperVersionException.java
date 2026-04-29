package org.mockmc.mockmc.exception;

import org.mockmc.mockmc.BuildParameters;

/**
 * This exception provides a detailed diagnostic message to help developers
 * identify and resolve compatibility issues during test execution.
 * <p>
 * The message includes:
 * <ul>
 * <li>The version of MockMC currently in use</li>
 * <li>The Paper API version MockMC was built against</li>
 * <li>A recommendation to align the plugin's Paper API version with MockMC</li>
 * </ul>
 *
 * @see BuildParameters#MOCK_MC_VERSION
 * @see BuildParameters#PAPER_API_FULL_VERSION
 */
public class IncompatiblePaperVersionException extends RuntimeException
{

	private static final String TEMPLATE_MESSAGE = """
			Version Mismatch!

			################### MockMC Version Mismatch ###################

			This may indicate a version mismatch between your project's Paper API
			and the version supported by this MockMC build.

			🔍 MockMC %s (%s) was built against Paper API version %s
			✅ Please ensure your plugin is compiled against the same version to maintain compatibility.

			###################################################################
			""";

	/**
	 * Constructs a new {@code IncompatiblePaperVersionException} with a formatted
	 * message indicating the detected version mismatch, and includes the original
	 * cause of failure.
	 *
	 */
	public IncompatiblePaperVersionException(Throwable cause)
	{
		super(String.format(TEMPLATE_MESSAGE, BuildParameters.MOCK_MC_VERSION, BuildParameters.BUILD_NUMBER_STRING,
				BuildParameters.PAPER_API_FULL_VERSION), cause);
	}

}
