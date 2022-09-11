package com.lusionmc.bunnymg;

import com.lusionmc.bunnymg.command.ArenaCommand;
import com.lusionmc.bunnymg.listener.ConnectListener;
import com.lusionmc.bunnymg.listener.GameListener;
import com.lusionmc.bunnymg.manager.ArenaManager;
import com.lusionmc.bunnymg.manager.ConfigManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class Bunnymg extends JavaPlugin {

    private ArenaManager arenaManager;

    @Override
    public void onEnable() {
        // Plugin startup logic
        ConfigManager.setupConfig(this);
        arenaManager = new ArenaManager(this);

        Bukkit.getPluginManager().registerEvents(new GameListener(this), this);
        Bukkit.getPluginManager().registerEvents(new ConnectListener(this), this);

        getCommand("arena").setExecutor(new ArenaCommand());

    }

    public ArenaManager getArenaManager() { return arenaManager; }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
