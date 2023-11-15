package com.luvtox.Config;

import com.luvtox.utils.AccessPoint;
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
        return config.getString("location.world", "world");
    }

    public double getSpawnX() {
        return config.getDouble("location.x");
    }

    public double getSpawnY() {
        return config.getDouble("location.y");
    }

    public double getSpawnZ() {
        return config.getDouble("location.z");
    }

    public float getSpawnYaw() {
        return (float) config.getDouble("location.yaw");
    }

    public float getSpawnPitch() {
        return (float) config.getDouble("location.pitch");
    }

    public String prefix() {
        return coloredMessage(config.getString("spawn.prefix", "&f[&6EzSpawn&f]"));
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
        return coloredMessage(config.getString("spawn.message", "You have been teleported to spawn."));
    }

    public String getSpawnSoundEffect() {
        return config.getString("spawn.soundEffect", "ENTITY_PLAYER_LEVELUP");
    }

    private String coloredMessage(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

}