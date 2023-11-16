package com.luvtox.Listener;

import com.luvtox.SpawnPlugin;
import com.luvtox.utils.AccessPoint;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import com.luvtox.commands.SpawnCommands;

public class SpawnEventListener extends AccessPoint implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (spawnLocationManager.isSpawnSet()) {
            if (configManager.teleportOnJoin()) {
                spawnCommands.teleportToSpawn(player);
            }
        }
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();
        event.setKeepInventory(true);

        Bukkit.getScheduler().runTaskLater(SpawnPlugin.getInstance(), () -> {
            player.spigot().respawn();

            Location respawnLocation = spawnLocationManager.loadSpawnLocation().clone();

            if(respawnLocation == null) return;

            if (configManager.respawnAtBed()) {
                if (player.getBedSpawnLocation() != null) {
                    Location bedSpawnLocation = player.getBedSpawnLocation();
                    player.sendMessage("Respawning at your bed spawn location.");
                    player.teleport(bedSpawnLocation);
                } else  {
                    player.teleport(respawnLocation);
                }
            } else {
                player.teleport(respawnLocation);
            }

            player.setGameMode(GameMode.SURVIVAL);

            if (configManager.shouldClearInventoryOnSpawn()) {
                SpawnCommands.clearPlayerInventory(player);
            }
            if (configManager.shouldClearPotionEffectsOnSpawn()) {
                SpawnCommands.clearPlayerEffects(player);
            }
            if (configManager.shouldHealOnSpawn()) {
                player.setHealth(player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
                player.setFoodLevel(20);
            }

            player.sendMessage(configManager.getSpawnMessage());
        }, 25);
    }
}