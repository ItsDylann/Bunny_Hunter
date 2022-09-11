package com.lusionmc.bunnymg.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ArenaCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length == 2 && args[0].equalsIgnoreCase("join")) {

            } else if (args.length == 1 && args[0].equalsIgnoreCase("leave")) {

            } else {
                player.sendMessage("Usage: /arena join <id> or /arena leave");
            }
        }

        return false;
    }
}
