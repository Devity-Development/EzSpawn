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
        if (SpawnLocationManager.isSpawnSet()) {
            SpawnCommands.teleportToSpawn(player);
        }
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();
        event.setKeepInventory(true);

        Bukkit.getScheduler().runTaskLater(SpawnPlugin.getInstance(), () -> {
            player.spigot().respawn();

            Location respawnLocation = SpawnLocationManager.loadSpawnLocation().clone();
            if (respawnLocation != null) {
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
            }
        }, 25);
    }
}
