package com.luvtox.Config;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;

import com.luvtox.SpawnPlugin;

public class ConfigManager {
    private static FileConfiguration config;

    public static void loadConfig() {
        SpawnPlugin.getInstance().reloadConfig();
        config = SpawnPlugin.getInstance().getConfig();
    }

    public static FileConfiguration getConfig() {
        return config;
    }

    public static String getSpawnWorld() {
        return config.getString("spawn.world", "world");
    }

    public static double getSpawnX() {
        return config.getDouble("spawn.x");
    }

    public static double getSpawnY() {
        return config.getDouble("spawn.y");
    }

    public static double getSpawnZ() {
        return config.getDouble("spawn.z");
    }

    public static float getSpawnYaw() {
        return (float) config.getDouble("spawn.yaw");
    }

    public static float getSpawnPitch() {
        return (float) config.getDouble("spawn.pitch");
    }

    public static String prefix() {
        return config.getString("spawn.prefix", "&f[&6EzSpawn&f]");
    }

    public static boolean shouldClearInventoryOnSpawn() {
        return config.getBoolean("spawn.clearInventory", false);
    }

    public static boolean shouldClearPotionEffectsOnSpawn() {
        return config.getBoolean("spawn.clearPotionEffects", false);
    }

    public static boolean shouldHealOnSpawn() {
        return config.getBoolean("spawn.heal", false);
    }

    public static boolean teleportOnJoin() {
        return config.getBoolean("spawn.teleportOnJoin", true);
    }

    public static String getSpawnMessage() {
        return ChatColor.translateAlternateColorCodes('&', config.getString("spawn.message", "You have been teleported to spawn."));
    }

    public static String getSpawnSoundEffect() {
        return config.getString("spawn.soundEffect", "ENTITY_PLAYER_LEVELUP");
    }

}