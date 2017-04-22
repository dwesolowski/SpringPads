package com.github.dwesolowski.springpads.commands;

import com.github.dwesolowski.springpads.SpringPads;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpringPadsCMD implements CommandExecutor {

    private final SpringPads plugin;

    public SpringPadsCMD(SpringPads plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Command not allowed in console, must be used in-game only!");
            return true;
        }
        Player player = (Player) sender;
        Location Loc = player.getLocation();
        if ((cmd.getName().equalsIgnoreCase("springpads"))) {
            if (player.hasPermission("springpads.admin")) {
                if (args.length == 1) {

                    if (args[0].equalsIgnoreCase("create")) {
                        Loc.getWorld().getBlockAt(Loc).getRelative(0, -1, 0).setType(Material.getMaterial(this.plugin.getConfig().getString("Settings.Pad_Block")));
                        Loc.getWorld().getBlockAt(Loc).setType(Material.getMaterial(this.plugin.getConfig().getString("Settings.Pad_Type")));
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin.getConfig().getString("Messages.PadCreated")));
                        return true;
                    }

                    if (args[0].equalsIgnoreCase("reload")) {
                        this.plugin.reloadConfig();
                        sender.sendMessage(ChatColor.RED + "SpringPads configuration has been reloaded!");
                        return true;
                    }
                }
            } else {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin.getConfig().getString("Messages.NoPermissions")));
                return true;
            }
        }
        return false;
    }
}
