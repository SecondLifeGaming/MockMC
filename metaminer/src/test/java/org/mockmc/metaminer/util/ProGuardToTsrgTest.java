package org.mockmc.metaminer.util;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ProGuardToTsrgTest
{

	@Test
	void testConversion(@TempDir Path tempDir) throws Exception
	{
		Path proguard = tempDir.resolve("test.proguard");
		Files.write(proguard, List.of("com.example.Class -> a:", "    int field -> b",
				"    void method(int,java.lang.String) -> c", "    10:20:void method2() -> d"));

		Path tsrg = tempDir.resolve("test.tsrg");
		ProGuardToTsrg.convert(proguard.toFile(), tsrg.toFile());

		assertTrue(Files.exists(tsrg));
		List<String> lines = Files.readAllLines(tsrg);

		List<String> expected = List.of("a com/example/Class", "\tb field", "\tc (ILjava/lang/String;)V method",
				"\td ()V method2");

		assertEquals(expected, lines);
	}
}
