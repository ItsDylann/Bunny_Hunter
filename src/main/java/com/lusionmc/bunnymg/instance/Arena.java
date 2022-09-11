package com.lusionmc.bunnymg.instance;

import com.lusionmc.bunnymg.Bunnymg;
import com.lusionmc.bunnymg.GameState;
import com.lusionmc.bunnymg.manager.ConfigManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Arena {

    private Bunnymg bunnymg;

    //Came from config - set in stone and wont change
    private int id;
    private Location spawn;

    //State will always change & players will change
    private GameState state;
    private List<UUID> players;
    private Countdown countdown;
    private Game game;

    public Arena(Bunnymg bunnymg, int id, Location spawn) {
        this.bunnymg = bunnymg;

        this.id = id;
        this.spawn = spawn;

        this.state = GameState.RECRUITING;
        this.players = new ArrayList<>();
        this.countdown = new Countdown(bunnymg, this);
        this.game = new Game(this);
    }

    //GAME

    public void start() {
        game.start();
    }

    public void reset(boolean kickPlayers) {
        if (kickPlayers) {
            Location loc = ConfigManager.getLobbySpawn();
            for (UUID uuid  : players) {
                Bukkit.getPlayer(uuid).teleport(loc);
            }
            players.clear();
        }
        sendTitle("", "");
        state = GameState.RECRUITING;
        countdown.cancel();
        countdown = new Countdown(bunnymg, this);
        game = new Game(this);
    }

    //TOOLS

    public void sendMessage(String message) {
        for (UUID uuid : players) {
            Bukkit.getPlayer(uuid).sendMessage(message);
        }
    }

    public void sendTitle(String title, String subtitle) {
        for (UUID uuid : players) {
            Bukkit.getPlayer(uuid).sendTitle(title, subtitle);
        }
    }

    //PLAYERS
    public void addPlayer(Player player) {
        players.add(player.getUniqueId());
        player.teleport(spawn);

        if (state.equals(GameState.RECRUITING) && players.size() >= ConfigManager.getRequiredPlayers()) {
            countdown.start();
        }
    }

    public void removePlayer(Player player) {
        players.remove(player.getUniqueId());
        player.teleport(ConfigManager.getLobbySpawn());
        player.sendTitle("Countdown Stopped", "You have left the game");

        if (state.equals(GameState.COUNTDOWN) && players.size() < ConfigManager.getRequiredPlayers()) {
            sendMessage(ChatColor.RED + "Not enough players to start the game! Countdown stopped.");
            reset(false);
            return;
        }

        if (state.equals(GameState.LIVE) && players.size() < ConfigManager.getRequiredPlayers()) {
            sendMessage(ChatColor.RED + "Not enough players to continue the game! Too many players have left! Game stopped.");
            reset(false);
        }

    }

    //INFO
    public int getId() { return id; }

    public GameState getState() { return state; }
    public List<UUID> getPlayers() { return players; }

    public Game getGame() { return game; }

    public void setState(GameState state) { this.state = state; }

}
