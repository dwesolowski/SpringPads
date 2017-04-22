package com.github.dwesolowski.springpads.listeners;

import com.github.dwesolowski.springpads.SpringPads;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.util.Vector;

public class PlayerMoveEvent implements Listener {

    private final SpringPads plugin;

    public PlayerMoveEvent(SpringPads plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerMove(org.bukkit.event.player.PlayerMoveEvent event) {
        Player player = event.getPlayer();
        Location Loc = player.getLocation();
        if (!this.plugin.getConfig().getStringList("DisabledWorlds").contains(player.getWorld().getName())) {
            if ((Loc.getBlock().getType() == Material.valueOf(this.plugin.getConfig().getString("Settings.Pad_Type"))) && (Loc.getWorld().getBlockAt(Loc).getRelative(0, -1, 0).getType() == Material.valueOf(this.plugin.getConfig().getString("Settings.Pad_Block")))) {
                player.setVelocity(Loc.getDirection().multiply(this.plugin.getConfig().getInt("Settings.Power")));
                player.setVelocity(new Vector(player.getVelocity().getX(), 1.0D, player.getVelocity().getZ()));
                player.getWorld().playEffect(Loc, Effect.valueOf(this.plugin.getConfig().getString("Settings.Effect")), 3);
                player.getWorld().playSound(Loc, Sound.valueOf(this.plugin.getConfig().getString("Settings.Sound")), 10.0F, 1.0F);
                if (!this.plugin.preventDamage.contains(player)) {
                    this.plugin.preventDamage.add(player);
                }
            }
        }
    }

}
