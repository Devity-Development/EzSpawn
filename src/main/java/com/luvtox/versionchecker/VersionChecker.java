package com.luvtox.versionchecker;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class VersionChecker {
    private final JavaPlugin plugin;

    public VersionChecker(JavaPlugin plugin) {
        this.plugin = plugin;
    }
    public void run() {
        String serverVersion = plugin.getServer().getVersion();
        String[] versionParts = serverVersion.split("\\.");

        if (versionParts.length > 1) {
            int majorVersion = Integer.parseInt(versionParts[1].replaceAll("[^\\d.]", ""));

            if (majorVersion < 12) {
                plugin.getLogger().warning("The plugin is not tested for versions below 1.12");
            } else {
                plugin.getLogger().info("You are running a minecraft version compatible with EzSpawn");
            }
        }
    }
}
