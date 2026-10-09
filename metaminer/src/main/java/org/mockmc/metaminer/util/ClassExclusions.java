package org.mockmc.metaminer.util;

public final class ClassExclusions {
    
    private ClassExclusions() {}
    
    private static final String[] EXCLUDED_PREFIXES = {
        "io.papermc.paper.plugin.provider.source.",
        "io.papermc.paper.plugin.provider.type.",
        "io.papermc.paper.plugin.provider.configuration.",
        "io.papermc.paper.plugin.storage.",
        "io.papermc.paper.plugin.entrypoint.",
        "io.papermc.paper.configuration.",
        "io.papermc.paper.plugin.lifecycle.event.",
        "io.papermc.paper.tag.",
        "io.papermc.paper.registry.data.",
        "io.papermc.paper.registry.event.",
        "io.papermc.paper.registry.set."
    };
    
    private static final String[] EXCLUDED_CLASSES = {
        "io.papermc.paper.plugin.provider.ProviderStatusHolder",
        "io.papermc.paper.plugin.provider.PluginProvider",
        "com.destroystokyo.paper.profile.SharedPlayerProfile",
        "com.destroystokyo.paper.entity.CraftRangedEntity",
        "io.papermc.paper.world.flag.PaperFeatureDependent",
        "io.papermc.paper.util.Holderable",
        "io.papermc.paper.util.SafeAutoClosable",
        "io.papermc.paper.commands.PaperCommandBlockHolder",
        "io.papermc.paper.entity.PaperBucketable",
        "org.bukkit.inventory.RecipeChoice$PredicateChoice",
        "io.papermc.paper.util.OldEnum",
        "io.papermc.paper.registry.HolderableBase",
        "io.papermc.paper.datacomponent.BuildableDataComponent",
        "io.papermc.paper.command.brigadier.BasicCommand",
        "io.papermc.paper.registry.PaperRegistryBuilderFiller",
        "io.papermc.paper.registry.RegistryBuilderFactory",
        "org.bukkit.Warning"
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
        String canonicalName = name.replace('$', '.');
        for (String prefix : EXCLUDED_PREFIXES) {
            if (canonicalName.startsWith(prefix) || name.startsWith(prefix)) return true;
        }
        for (String clazz : EXCLUDED_CLASSES) {
            if (canonicalName.equals(clazz) || name.equals(clazz)) return true;
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
