package com.maxjackson.betternohunger.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.maxjackson.betternohunger.BetterNoHunger;

public class NoHunger implements CommandExecutor {

    private BetterNoHunger plugin;

    public NoHunger(BetterNoHunger plugin) {
        this.plugin = plugin;
    }

    public void send(CommandSender sender, String message) {
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length <= 0) {
            this.send(sender, "&c/nohunger <command> [...options]");
            this.send(sender, "&c/nohunger help - Displays commands.");
            this.send(sender, "&c/nohunger reload - Reloads configuration.");
            this.send(sender, "&c/nohunger enable - Enable or disables the no hunger.");
            return false;
        }

        String cmd = args[0];

        if (cmd.equals("help")) {
            this.send(sender, "&c/nohunger <command> [...options]");
            this.send(sender, "&c/nohunger help - Displays commands.");
            this.send(sender, "&c/nohunger reload - Reloads configuration.");
            this.send(sender, "&c/nohunger enable - Enable or disables the no hunger.");
        }

        if (cmd.equals("reload")) {
            this.plugin.reloadConfig();
            this.send(sender, "&cReloaded Config.");
        }

        if (cmd.equals("enable")) {
            this.plugin.reloadConfig();
            boolean enabled = this.plugin.getConfig().getBoolean("enabled");
            boolean e2 = !enabled;
            this.plugin.getConfig().set("enabled", e2);
            this.plugin.saveConfig();
            if (e2) {
                this.send(sender, "&aEnabled No hunger");
            } else {
                this.send(sender, "&cDisabled No hunger");
            }
        }

        return true;
    }
    

    
}
