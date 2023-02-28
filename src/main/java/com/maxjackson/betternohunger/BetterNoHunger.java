package com.maxjackson.betternohunger;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.plugin.java.JavaPlugin;

import com.maxjackson.betternohunger.commands.NoHunger;

import net.md_5.bungee.api.ChatColor;

public class BetterNoHunger extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        this.logColored("&6---------------------------------");
        this.logColored("&6 Better No Hunger v" + this.getDescription().getVersion() + "");
        this.logColored("&a Enabling Plugin");
        this.logColored("&6---------------------------------");
        saveDefaultConfig();
        getServer().getPluginManager().registerEvents(this, this);
        getCommand("nohunger").setExecutor(new NoHunger(this));
    }

    @Override
    public void onDisable() {
        this.logColored("&6---------------------------------");
        this.logColored("&6 Better No Hunger v" + this.getDescription().getVersion() + "");
        this.logColored("&c Disabling Plugin");
        this.logColored("&6---------------------------------");
        saveConfig();
    }

    public void logColored(String message) {
        ConsoleCommandSender sender = Bukkit.getConsoleSender();

        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
    }

    @EventHandler
    public void onHungerChange(FoodLevelChangeEvent event) {
        this.reloadConfig();
        if (event.getEntityType() != EntityType.PLAYER) return;

        if (this.getConfig().getBoolean("enabled")) {
            Player player = (Player)event.getEntity();
            List<String> worlds = this.getConfig().getStringList("worlds");
            if(worlds.contains(player.getWorld().getName())) {
                player.setFoodLevel(20);
                event.setCancelled(true);
            }
        }
    }
}