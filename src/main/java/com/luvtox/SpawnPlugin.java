package com.luvtox;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import com.luvtox.Config.ConfigManager;
import com.luvtox.Listener.SpawnEventListener;
import com.luvtox.Managers.SpawnLocationManager;
import com.luvtox.commands.EzSpawnHelpCommand;
import com.luvtox.commands.ReloadCommand;
import com.luvtox.commands.SpawnCommands;

import java.io.File;

public class SpawnPlugin extends JavaPlugin {
    private static SpawnPlugin instance;

    public static SpawnPlugin getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;
        getLogger().info("Enabling EzSpawn by luvtox");

        if (!getDataFolder().exists()) {
            getDataFolder().mkdirs();
        }
        getLogger().info("Generating config");
        File configFile = new File(getDataFolder(), "config.yml");
        if (!configFile.exists()) {
            saveDefaultConfig();
        }
        getLogger().info("Loading config");
        ConfigManager.loadConfig();
        getLogger().info("Config has been loaded");
        getLogger().info("Loading commands");
        getCommand("ezspawn").setExecutor(new EzSpawnHelpCommand());
        getCommand("ezspawn").setExecutor(new ReloadCommand(this));
        getServer().getPluginManager().registerEvents(new SpawnEventListener(), this);
        getLogger().info("Commands have been loaded");
        getLogger().info("EzSpawn version  v0.0.2 by Devity has been enabled");
    }

    @Override
    public void onDisable() {
        getLogger().info(Color.RED + "Saving Data");
        getLogger().info(Color.RED + "Shutting down");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("setspawn")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                SpawnLocationManager.saveSpawnLocation(player.getLocation());
                sender.sendMessage(ChatColor.GREEN + "Spawn location set successfully!");
            } else {
                sender.sendMessage(ChatColor.RED + "This command can only be run by a player.");
            }
            return true;
        }

        if (command.getName().equalsIgnoreCase("spawn")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                SpawnCommands.teleportToSpawn(player);
            } else {
                sender.sendMessage(ChatColor.RED + "This command can only be run by a player.");
            }
            return true;
        }

        return false;
    }
}


