package org.mockmc.metaminer.util;

public final class ClassExclusions {
    
    private ClassExclusions() {}
    
    private static final String[] EXCLUDED_PREFIXES = {
        "com.destroystokyo.paper.loottable.",
        "io.papermc.paper.plugin.provider.source.",
        "io.papermc.paper.plugin.provider.type.",
        "io.papermc.paper.plugin.provider.configuration.",
        "io.papermc.paper.plugin.storage.",
        "io.papermc.paper.plugin.entrypoint."
    };
    
    private static final String[] EXCLUDED_CLASSES = {
        "io.papermc.paper.plugin.provider.ProviderStatusHolder",
        "io.papermc.paper.plugin.provider.PluginProvider",
        "com.destroystokyo.paper.profile.SharedPlayerProfile",
        "com.destroystokyo.paper.entity.CraftRangedEntity",
        "io.papermc.paper.world.flag.PaperFeatureDependent",
        "io.papermc.paper.util.Holderable",
        "io.papermc.paper.util.SafeAutoClosable",
        "io.papermc.paper.commands.PaperCommandBlockHolder"
    };
    
    private static final String[] GENERATABLE_PACKAGES = {
        "org.bukkit.",
        "org.spigotmc.",
        "co.aikar.timings.",
        "com.destroystokyo.paper.",
        "io.papermc.paper.",
        "com.velocitypowered.api.",
        "net.md_5.bungee.",
        "io.github.waterfallmc.",
        "com.mojang.brigadier."
    };

    public static boolean isExcluded(String name) {
        for (String prefix : EXCLUDED_PREFIXES) {
            if (name.startsWith(prefix)) return true;
        }
        for (String clazz : EXCLUDED_CLASSES) {
            if (name.equals(clazz)) return true;
        }
        return false;
    }
    
    public static boolean isGeneratablePackage(String name) {
        if (name.contains(".craftbukkit.")) return false;
        
        for (String pkg : GENERATABLE_PACKAGES) {
            if (name.startsWith(pkg)) return true;
        }
        return false;
    }
}
