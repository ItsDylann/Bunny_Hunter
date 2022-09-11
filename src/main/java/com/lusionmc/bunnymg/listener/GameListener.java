package com.lusionmc.bunnymg.listener;

import com.lusionmc.bunnymg.Bunnymg;
import com.lusionmc.bunnymg.GameState;
import com.lusionmc.bunnymg.instance.Arena;
import org.bukkit.entity.Rabbit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

public class GameListener implements Listener {

    private Bunnymg bunnymg;

    public GameListener(Bunnymg bunnymg) {
        this.bunnymg = bunnymg;
    }

    @EventHandler
    public void onBunnyKill(EntityDeathEvent e) {

        Arena arena = bunnymg.getArenaManager().getArena(e.getEntity().getKiller());
        if (arena != null && arena.getState().equals(GameState.LIVE)) {
            if (e.getEntity() instanceof Rabbit) {
                arena.getGame().addKill(e.getEntity().getKiller());
            }
        }
    }
}
