package com.luvtox.commands;

import com.luvtox.SpawnPlugin;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;

import com.luvtox.Config.ConfigManager;
import com.luvtox.Managers.SpawnLocationManager;

import net.md_5.bungee.api.ChatColor;

public class SpawnCommands {

    private final ConfigManager configManager = SpawnPlugin.getInstance().getConfigManager();

    public void teleportToSpawn(Player player) {
        Location spawnLocation = new SpawnLocationManager().loadSpawnLocation();

        if (spawnLocation != null) {
            player.teleport(spawnLocation);
            player.setGameMode(GameMode.SURVIVAL);

            if (configManager.shouldClearInventoryOnSpawn()) {
                clearPlayerInventory(player);
            }
            if (configManager.shouldClearPotionEffectsOnSpawn()) {
                clearPlayerEffects(player);
            }
            if (configManager.shouldHealOnSpawn()) {
                player.setHealth(player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
                player.setFoodLevel(20);
            }

            player.sendMessage(configManager.getSpawnMessage());
            playSpawnSoundEffect(player);
        } else {
            player.sendMessage(configManager.prefix() + "" + ChatColor.YELLOW + "Spawn location is not set.");
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

    public void playSpawnSoundEffect(Player player) {
        String soundEffect = configManager.getSpawnSoundEffect();
        if (soundEffect != null && !soundEffect.isEmpty()) {
            try {
                Sound sound = Sound.valueOf(soundEffect);
                player.playSound(player.getLocation(), sound, 1.0f, 1.0f);
            } catch (IllegalArgumentException e) {
                player.sendMessage(configManager.prefix() + " " + "Invalis sound effect : " + soundEffect);
            }
        }
    }
}