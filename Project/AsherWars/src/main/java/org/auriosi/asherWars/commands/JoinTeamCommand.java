package org.auriosi.asherWars.commands;

import org.auriosi.asherWars.GameManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class JoinTeamCommand implements CommandExecutor {
    private final GameManager gameManager;

    public JoinTeamCommand(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (commandSender instanceof Player player) {
            gameManager.removePlayerFromBase(player);
            gameManager.addPlayerToBase(player, strings[0]);
            player.sendMessage("You have joined the team " + strings[0]);
        }

        return true;
    }
}
