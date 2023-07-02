package com.luvtox.commands;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;

import com.luvtox.Config.ConfigManager;
import com.luvtox.Managers.SpawnLocationManager;

public class SpawnCommands {
    public static void teleportToSpawn(Player player) {
        Location spawnLocation = SpawnLocationManager.loadSpawnLocation();

        if (spawnLocation != null) {
            player.teleport(spawnLocation);
            player.setGameMode(GameMode.SURVIVAL);

            if (ConfigManager.shouldClearInventoryOnSpawn()) {
                clearPlayerInventory(player);
            }
            if (ConfigManager.shouldClearPotionEffectsOnSpawn()) {
                clearPlayerEffects(player);
            }
            if (ConfigManager.shouldHealOnSpawn()) {
                player.setHealth(player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
                player.setFoodLevel(20);
            }

            player.sendMessage(ConfigManager.getSpawnMessage());
            playSpawnSoundEffect(player);
        } else {
            player.sendMessage("Spawn location is not set.");
        }
    }

    public static void clearPlayerInventory(Player player) {
        player.getInventory().clear();
        player.getInventory().setArmorContents(null);
        player.updateInventory();
    }

    public static void clearPlayerEffects(Player player) {
        for (PotionEffect effect : player.getActivePotionEffects()) {
            player.removePotionEffect(effect.getType());
        }
    }

    public static void playSpawnSoundEffect(Player player) {
        String soundEffect = ConfigManager.getSpawnSoundEffect();
        if (soundEffect != null && !soundEffect.isEmpty()) {
            try {
                Sound sound = Sound.valueOf(soundEffect);
                player.playSound(player.getLocation(), sound, 1.0f, 1.0f);
            } catch (IllegalArgumentException e) {
                player.sendMessage("Invalid sound effect: " + soundEffect);
            }
        }
    }
}
