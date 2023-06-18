package com.luvtox.Listener;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import com.luvtox.SpawnPlugin;
import com.luvtox.Config.ConfigManager;
import com.luvtox.Managers.SpawnLocationManager;
import com.luvtox.commands.SpawnCommands;

public class SpawnEventListener implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
            if (ConfigManager.teleportOnJoin()) {
                SpawnCommands.teleportToSpawn(player);
        }

    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();
        event.setKeepInventory(true); // Keep inventory on death

        Bukkit.getScheduler().runTaskLater(SpawnPlugin.getInstance(), () -> {
            player.spigot().respawn(); // Respawn the player

            Location respawnLocation = SpawnLocationManager.loadSpawnLocation().clone();
            respawnLocation.setWorld(Bukkit.getWorld(ConfigManager.getSpawnWorld()));

            player.teleport(respawnLocation);
            player.setGameMode(GameMode.SURVIVAL);

            if (ConfigManager.shouldClearInventoryOnSpawn()) {
                SpawnCommands.clearPlayerInventory(player);
            }
            if (ConfigManager.shouldClearPotionEffectsOnSpawn()) {
                SpawnCommands.clearPlayerEffects(player);
            }
            if (ConfigManager.shouldHealOnSpawn()) {
                player.setHealth(player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
                player.setFoodLevel(20);
            }

            player.sendMessage(ConfigManager.getSpawnMessage());
        }, 25); // Delay of 25 ticks (1.25 seconds)
    }
}

