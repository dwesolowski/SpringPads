package com.github.dwesolowski.springpads;

import com.github.dwesolowski.springpads.commands.SpringPadsCMD;
import com.github.dwesolowski.springpads.listeners.EntityDamageEvent;
import com.github.dwesolowski.springpads.listeners.PlayerMoveEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public final class SpringPads extends JavaPlugin {

    public final List<Player> preventDamage = new ArrayList<>();

    @Override
    public void onEnable() {
        getConfig().options().copyDefaults(true);
        registerCommands();
        registerListeners();
        saveDefaultConfig();
        registerMetrics();
    }

    @Override
    public void onDisable() {
        Bukkit.getPluginManager().disablePlugin(this);
    }

    private void registerCommands() {
        this.getCommand("springpads").setExecutor(new SpringPadsCMD(this));
    }

    private void registerListeners() {
        getServer().getPluginManager().registerEvents(new PlayerMoveEvent(this), this);
        getServer().getPluginManager().registerEvents(new EntityDamageEvent(this), this);
    }

    private void registerMetrics() {
        final MetricsLite metrics = new MetricsLite(this);
    }
}
