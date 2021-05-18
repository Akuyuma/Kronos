package org.bukkit.command.defaults;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.VanillaCommand;
import org.bukkit.entity.Player;

import java.util.Arrays;

public class PingCommand extends VanillaCommand {

    public PingCommand() {
        super("ping");
        this.description = "Get the ping of a player.";
        this.usageMessage = "/ping [player]";
        this.setAliases(Arrays.asList(new String[] { "ms" }));
    }

    @Override
    public boolean execute(CommandSender sender, String currentAlias, String[] args) {
        if (!(sender instanceof Player)) {
            return false;
        }
        if (!testPermission(sender)) return true;

        Player player = (Player) sender;
        Player target;
        if (args.length == 1) {
            if (Bukkit.getPlayerExact(args[0]) instanceof Player) {
                target = Bukkit.getPlayer(args[0]);
                if (target != null) {
                    player.sendMessage("§6» §e" + target.getName() + "'s ping§7: §6" + target.getPing() + "ms");
                } else {
                    sender.sendMessage(ChatColor.RED + "No player matching '" + args[0] + "' is connected to this server.");
                }
            } else {
                sender.sendMessage(ChatColor.RED + "No player matching '" + args[0] + "' is connected to this server.");
            }
        } else {
            player.sendMessage("§6» §eping§7: §6" + player.getPing() + "ms");
        }
        return false;
    }
}
