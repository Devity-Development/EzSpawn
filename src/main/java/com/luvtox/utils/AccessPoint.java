package com.luvtox.utils;

import com.luvtox.Config.ConfigManager;
import com.luvtox.Managers.SpawnLocationManager;
import com.luvtox.SpawnPlugin;
import com.luvtox.commands.SpawnCommands;
import org.bukkit.ChatColor;

public class AccessPoint {
    public final ConfigManager configManager = SpawnPlugin.getInstance().getConfigManager();
    public final SpawnCommands spawnCommands = new SpawnCommands();
    public final SpawnLocationManager spawnLocationManager = new SpawnLocationManager();


    public String coloredMessage(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

}
