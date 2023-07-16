package com.luvtox.Config;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;

public class ConfigManager {
    private FileConfiguration config;

    public ConfigManager(FileConfiguration config) {
        this.config = config;
    }

    public void reload(FileConfiguration config) {
        this.config = config;
    }

    public FileConfiguration getConfig() {
        return config;
    }

    public String getSpawnWorld() {
        return config.getString("spawn.world", "world");
    }

    public double getSpawnX() {
        return config.getDouble("spawn.x");
    }

    public double getSpawnY() {
        return config.getDouble("spawn.y");
    }

    public double getSpawnZ() {
        return config.getDouble("spawn.z");
    }

    public float getSpawnYaw() {
        return (float) config.getDouble("spawn.yaw");
    }

    public float getSpawnPitch() {
        return (float) config.getDouble("spawn.pitch");
    }

    public String prefix() {
        return ChatColor.translateAlternateColorCodes('&', config.getString("spawn.prefix", "&f[&6EzSpawn&f]"));
    }

    public boolean shouldClearInventoryOnSpawn() {
        return config.getBoolean("spawn.clearInventory", false);
    }

    public boolean shouldClearPotionEffectsOnSpawn() {
        return config.getBoolean("spawn.clearPotionEffects", false);
    }

    public boolean shouldHealOnSpawn() {
        return config.getBoolean("spawn.heal", false);
    }

    public boolean teleportOnJoin() {
        return config.getBoolean("spawn.teleportOnJoin", true);
    }

    public String getSpawnMessage() {
        return ChatColor.translateAlternateColorCodes('&', config.getString("spawn.message", "You have been teleported to spawn."));
    }

    public String getSpawnSoundEffect() {
        return config.getString("spawn.soundEffect", "ENTITY_PLAYER_LEVELUP");
    }

}