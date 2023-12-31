package com.luvtox.commands;

import com.luvtox.SpawnPlugin;
import com.luvtox.utils.AccessPoint;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.Collections;
import java.util.List;

public class ReloadCommand extends AccessPoint implements CommandExecutor, TabCompleter {
    private final SpawnPlugin plugin;

    public ReloadCommand(SpawnPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        // Check if the sender is an op player
        if (sender.isOp()) {
            if (args.length == 1 && args[0].equalsIgnoreCase("reload")) {
                plugin.reloadConfig();
                plugin.getConfigManager().reload(plugin.getConfig());
                sender.sendMessage(coloredMessage(plugin.getConfigManager().prefix() + " &aEzSpawn configuration reloaded successfully!"));
                return true;
            }
        } else {
            sender.sendMessage(ChatColor.RED + "You do not have permission to use this command.");
        }

        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length == 1 && args[0].equalsIgnoreCase("reload")) {
            return Collections.singletonList("reload");
        }
        return Collections.emptyList();
    }
}
