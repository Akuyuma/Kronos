package org.bukkit.command.defaults;

import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.VanillaCommand;
import org.bukkit.entity.Player;

import java.util.Arrays;

public class NightCommand extends VanillaCommand {
    public NightCommand() {
        super("night");
        this.description = "Changes the time of a player";
    }

    @Override
    public boolean execute(CommandSender sender, String currentAlias, String[] args) {
        if (sender instanceof Player) {
            final Player player = (Player) sender;
            int value = 18000;

            player.setPlayerTime(value, false);
            player.sendMessage("§7Time mode for §c" + player.getName() + " §7set to night.");
        }

        return true;
    }
}