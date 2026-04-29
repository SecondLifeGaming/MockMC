package org.mockmc.mockmc.dialog;

import io.papermc.paper.dialog.Dialog;
import io.papermc.paper.registry.RegistryAccess;
import io.papermc.paper.registry.RegistryKey;
import org.bukkit.NamespacedKey;
import org.bukkit.Registry;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockmc.mockmc.MockMCExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SuppressWarnings(
{"deprecation", "removal", "unchecked"})
@ExtendWith(MockMCExtension.class)
class DialogMockTest
{

	@Test
	void getKey()
	{
		Registry<@NotNull Dialog> registry = RegistryAccess.registryAccess().getRegistry(RegistryKey.DIALOG);

		Dialog serverLinks = registry.get(NamespacedKey.minecraft("server_links"));

		assertNotNull(serverLinks);
		assertEquals("minecraft:server_links", serverLinks.getKey().asString());
	}

}
