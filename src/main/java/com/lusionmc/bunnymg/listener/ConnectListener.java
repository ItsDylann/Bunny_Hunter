package com.lusionmc.bunnymg.listener;

import com.lusionmc.bunnymg.Bunnymg;
import com.lusionmc.bunnymg.instance.Arena;
import com.lusionmc.bunnymg.manager.ConfigManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class ConnectListener implements Listener {

    private Bunnymg bunnymg;

    public ConnectListener(Bunnymg bunnymg) {
        this.bunnymg = bunnymg;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        e.getPlayer().teleport(ConfigManager.getLobbySpawn());
    }

    @EventHandler
    public void onQuit(PlayerJoinEvent e) {
        Arena arena = bunnymg.getArenaManager().getArena(e.getPlayer());
        if (arena != null) {
            arena.removePlayer(e.getPlayer());
        }

    }

}
