package com.luvtox.Managers;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

import com.luvtox.SpawnPlugin;
import com.luvtox.Config.ConfigManager;

public class SpawnLocationManager {
    private final ConfigManager configManager = SpawnPlugin.getInstance().getConfigManager();
    public void saveSpawnLocation(Location location) {
        configManager.getConfig().set("spawn.world", location.getWorld().getName());
        configManager.getConfig().set("spawn.x", location.getX());
        configManager.getConfig().set("spawn.y", location.getY());
        configManager.getConfig().set("spawn.z", location.getZ());
        configManager.getConfig().set("spawn.yaw", location.getYaw());
        configManager.getConfig().set("spawn.pitch", location.getPitch());
        SpawnPlugin.getInstance().saveConfig();
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