package com.lusionmc.bunnymg.instance;

import com.lusionmc.bunnymg.GameState;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class Game {

    private Arena arena;
    private HashMap<UUID, Integer> kills;

    public Game(Arena arena) {
        this.arena = arena;
        kills = new HashMap<>();
    }

    public void start() {
        arena.setState(GameState.LIVE);
        arena.sendMessage(ChatColor.GREEN + "Game started! Kill 100 Bunnies to win!");
        //0 kills to start with
        for (UUID uuid : arena.getPlayers()) {
            kills.put(uuid, 0);
        }
    }

    public void addKill(Player player) {
        int playerKills = kills.get(player.getUniqueId()) + 1;
        if (playerKills == 100) {
            arena.sendMessage(ChatColor.GOLD + player.getName() + " has won the game!");
            arena.reset(true);

            return;
        }
        player.sendMessage(ChatColor.GREEN + "You have killed " + playerKills + " bunnies!");
        kills.replace(player.getUniqueId(), playerKills);
    }

}