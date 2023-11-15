package com.luvtox.Managers;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

import com.luvtox.SpawnPlugin;
import com.luvtox.Config.ConfigManager;

public class SpawnLocationManager {
    private final ConfigManager configManager = SpawnPlugin.getInstance().getConfigManager();
    private final SpawnPlugin plugin = SpawnPlugin.getInstance();
    public void saveSpawnLocation(Location location) {
        configManager.getConfig().set("location.world", location.getWorld().getName());
        configManager.getConfig().set("location.x", location.getX());
        configManager.getConfig().set("location.y", location.getY());
        configManager.getConfig().set("location.z", location.getZ());
        configManager.getConfig().set("location.yaw", location.getYaw());
        configManager.getConfig().set("location.pitch", location.getPitch());

        plugin.saveConfig();
        plugin.reloadConfig();
        plugin.getConfigManager().reload(plugin.getConfig());
    }

    public Location loadSpawnLocation() {
        String worldName = configManager.getSpawnWorld();
        double x = configManager.getSpawnX();
        double y = configManager.getSpawnY();
        double z = configManager.getSpawnZ();
        float yaw = configManager.getSpawnYaw();
        float pitch = configManager.getSpawnPitch();

        World world = Bukkit.getWorld(worldName);
        if (world != null) {
            return new Location(world, x, y, z, yaw, pitch);
        }

        return null; // Return null if the world is not found
    }

    public boolean isSpawnSet() {
        String worldName = configManager.getSpawnWorld();
        return Bukkit.getWorld(worldName) != null;
    }
}