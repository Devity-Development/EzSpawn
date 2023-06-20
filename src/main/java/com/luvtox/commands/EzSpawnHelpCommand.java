package com.luvtox.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class EzSpawnHelpCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            sendPluginHelp(sender);
            return true;
        }
        return false;
    }

    private void sendPluginHelp(CommandSender sender) {
        sender.sendMessage(ChatColor.GOLD + "===== EzSpawn Help =====");
        sender.sendMessage(ChatColor.YELLOW + "/ezspawn help" + ChatColor.WHITE + " - Display this help message");
        sender.sendMessage(ChatColor.YELLOW + "/setspawn" + ChatColor.WHITE + " - Set the destination for player teleportation on /spawn");
        sender.sendMessage(ChatColor.YELLOW + "/ezspawn reload" + ChatColor.WHITE + " - Reload the configuration for EzSpawn");
        sender.sendMessage(ChatColor.GOLD + "======================");

    }
}



