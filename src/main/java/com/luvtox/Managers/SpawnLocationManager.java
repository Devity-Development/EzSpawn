package com.luvtox.Managers;

import org.bukkit.Bukkit;
import org.bukkit.Location;

import com.luvtox.SpawnPlugin;
import com.luvtox.Config.ConfigManager;

public class SpawnLocationManager {
    public static void saveSpawnLocation(Location location) {
        ConfigManager.getConfig().set("spawn.world", location.getWorld().getName());
        ConfigManager.getConfig().set("spawn.x", location.getX());
        ConfigManager.getConfig().set("spawn.y", location.getY());
        ConfigManager.getConfig().set("spawn.z", location.getZ());
        ConfigManager.getConfig().set("spawn.yaw", location.getYaw());
        SpawnPlugin.getInstance().saveConfig();
    }

public static Location loadSpawnLocation() {
    String worldName = ConfigManager.getSpawnWorld();
    double x = ConfigManager.getSpawnX();
    double y = ConfigManager.getSpawnY();
    double z = ConfigManager.getSpawnZ();
    float yaw = ConfigManager.getSpawnYaw();

    return new Location(Bukkit.getWorld(worldName), x, y, z, yaw, 0);
}

}

