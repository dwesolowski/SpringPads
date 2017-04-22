package com.github.dwesolowski.springpads.listeners;

import com.github.dwesolowski.springpads.SpringPads;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class EntityDamageEvent implements Listener {

    private final SpringPads plugin;

    public EntityDamageEvent(SpringPads plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onFallDamage(org.bukkit.event.entity.EntityDamageEvent event) {
        if (((event.getEntity() instanceof Player)) && (event.getCause() == org.bukkit.event.entity.EntityDamageEvent.DamageCause.FALL)) {
            Player player = (Player) event.getEntity();
            if (this.plugin.preventDamage.contains(player)) {
                event.setCancelled(true);
                this.plugin.preventDamage.remove(player);
            }
        }
    }
}
