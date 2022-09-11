package com.lusionmc.bunnymg.instance;

import com.lusionmc.bunnymg.Bunnymg;
import com.lusionmc.bunnymg.GameState;
import com.lusionmc.bunnymg.manager.ConfigManager;
import org.bukkit.ChatColor;
import org.bukkit.scheduler.BukkitRunnable;

public class Countdown extends BukkitRunnable {

    private Bunnymg bunnymg;
    private Arena arena;
    private int countdownSeconds;

    public Countdown(Bunnymg bunnymg, Arena arena) {
        this.bunnymg = bunnymg;
        this.arena = arena;
        this.countdownSeconds = ConfigManager.getCoundownSeconds();
    }

    public void start() {
        arena.setState(GameState.COUNTDOWN);
        runTaskTimer(bunnymg, 0, 20);
    }

    @Override
    public void run() {
        if (countdownSeconds == 0) {
            cancel();
            arena.start();
            return;
        }

        if(countdownSeconds <= 10 || countdownSeconds % 15 == 0) {
            arena.sendMessage(ChatColor.GREEN + "Game starting in " + countdownSeconds + " second" + (countdownSeconds == 1 ? "" : "s" + "!"));
        }

        arena.sendTitle(ChatColor.GREEN.toString() + countdownSeconds + " second" + (countdownSeconds == 1 ? "" : "s" + "!"), ChatColor.GRAY + "until game starts.");

        countdownSeconds--;
    }
}
