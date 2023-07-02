package com.luvtox.Managers;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

import com.luvtox.SpawnPlugin;
import com.luvtox.Config.ConfigManager;

public class SpawnLocationManager {
    public static void saveSpawnLocation(Location location) {
        ConfigManager.getConfig().set("spawn.world", location.getWorld().getName());
        ConfigManager.getConfig().set("spawn.x", location.getX());
        ConfigManager.getConfig().set("spawn.y", location.getY());
        ConfigManager.getConfig().set("spawn.z", location.getZ());
        ConfigManager.getConfig().set("spawn.yaw", location.getYaw());
        ConfigManager.getConfig().set("spawn.pitch", location.getPitch());
        SpawnPlugin.getInstance().saveConfig();
    }

    public static Location loadSpawnLocation() {
        String worldName = ConfigManager.getSpawnWorld();
        double x = ConfigManager.getSpawnX();
        double y = ConfigManager.getSpawnY();
        double z = ConfigManager.getSpawnZ();
        float yaw = ConfigManager.getSpawnYaw();
        float pitch = ConfigManager.getSpawnPitch();

        World world = Bukkit.getWorld(worldName);
        if (world != null) {
            return new Location(world, x, y, z, yaw, pitch);
        }

        return null; // Return null if the world is not found
    }

    public static boolean isSpawnSet() {
        String worldName = ConfigManager.getSpawnWorld();
        return Bukkit.getWorld(worldName) != null;
    }
}
