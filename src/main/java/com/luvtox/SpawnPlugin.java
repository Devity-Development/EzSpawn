package com.luvtox;

import com.luvtox.versionchecker.VersionChecker;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import com.luvtox.Config.ConfigManager;
import com.luvtox.Listener.SpawnEventListener;
import com.luvtox.Managers.SpawnLocationManager;
import com.luvtox.commands.ReloadCommand;
import com.luvtox.commands.SpawnCommands;

public class SpawnPlugin extends JavaPlugin {
    private static SpawnPlugin instance;

    public static SpawnPlugin getInstance() {
        return instance;
    }

    private ConfigManager configManager;

    @Override
    public void onEnable() {

        new VersionChecker(this).run(); //Version checker

        instance = this;
        if (!getDataFolder().exists()) {
            getDataFolder().mkdirs();
        }

        this.getConfig().options().copyDefaults(true);
        this.saveConfig(); //Config loader
        this.configManager = new ConfigManager(this.getConfig());

        getLogger().info("\u001B[32mGenerating config\u001B[0m");
        getLogger().info("\u001B[32mLoading config\u001B[0m");
        getLogger().info("\u001B[32mConfig has been loaded\u001B[0m");
        getLogger().info("\u001B[32mLoading commands\u001B[0m");
        getCommand("ezspawn").setExecutor(new ReloadCommand(this));
        getServer().getPluginManager().registerEvents(new SpawnEventListener(), this);
        getLogger().info("\u001B[32mCommands have been loaded\u001B[0m");
        getLogger().info("\u001B[32mEzSpawn version v0.0.7 by Devity has been enabled\u001B[0m");

    }

    @Override
    public void onDisable() {
        getLogger().warning("\u001B[31mShutting down\u001B[0m");
        getLogger().warning("Shutting down");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("setspawn")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                new SpawnLocationManager().saveSpawnLocation(player.getLocation());
                sender.sendMessage(this.configManager.prefix() + " " + ChatColor.GREEN + "Spawn location set successfully!");
            } else {
                sender.sendMessage(this.configManager.prefix() + " " + ChatColor.RED + "This command can only be run by a player.");
            }
            return true;
        }

        if (command.getName().equalsIgnoreCase("spawn")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                new SpawnCommands().teleportToSpawn(player);
            } else {
                sender.sendMessage(this.configManager.prefix() + "" + ChatColor.RED + "This command can only be run by a player.");
            }
            return true;
        }

        return false;
    }

    public ConfigManager getConfigManager() {
        return configManager;
    }
}

