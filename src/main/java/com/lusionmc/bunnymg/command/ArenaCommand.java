package com.lusionmc.bunnymg.command;

import com.lusionmc.bunnymg.Bunnymg;
import com.lusionmc.bunnymg.GameState;
import com.lusionmc.bunnymg.instance.Arena;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ArenaCommand implements CommandExecutor {

    private Bunnymg bunnymg;

    public ArenaCommand(Bunnymg bunnymg) {
        this.bunnymg = bunnymg;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length == 2 && args[0].equalsIgnoreCase("join")) {
                if (bunnymg.getArenaManager().getArena(player) != null) {
                    player.sendMessage(ChatColor.RED + "You are already in an arena!");

                    return false;
                }
                int id;
                try {
                    id = Integer.parseInt(args[1]);
                } catch (NumberFormatException e){
                    player.sendMessage(ChatColor.RED + "Invalid arena id!");
                    return false;
                }

                if (id >= 0 && id < bunnymg.getArenaManager().getArenas().size()) {
                    Arena arena = bunnymg.getArenaManager().getArena(id);
                    if (arena.getState().equals(GameState.RECRUITING) || arena.getState() == GameState.COUNTDOWN) {
                        player.sendMessage(ChatColor.GREEN + "You have joined arena " + id + "!");
                        arena.addPlayer(player);
                    } else {
                        player.sendMessage(ChatColor.RED + "Game is currently in progress, please wait!");
                    }
                } else {
                    player.sendMessage(ChatColor.RED + "Invalid arena id!");
                }
            } else if (args.length == 1 && args[0].equalsIgnoreCase("leave")) {
                Arena arena = bunnymg.getArenaManager().getArena(player);
                if (arena != null) {
                    player.sendMessage(ChatColor.RED + "You have left the arena!");
                    arena.removePlayer(player);
                } else {
                    player.sendMessage(ChatColor.RED + "You are not in an arena!");
                }
            } else {
                player.sendMessage("Usage: /arena join <id> or /arena leave");
            }
        }

        return false;
    }
}
