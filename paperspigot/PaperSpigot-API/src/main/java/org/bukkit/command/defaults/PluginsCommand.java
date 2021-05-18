package org.bukkit.command.defaults;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;

public class PluginsCommand extends BukkitCommand {
    public PluginsCommand(String name) {
        super(name);
        this.description = "Gets a list of plugins running on the server";
        this.usageMessage = "/plugins";
        this.setPermission("bukkit.command.plugins");
        this.setAliases(Arrays.asList("pl"));
    }

    @Override
    public boolean execute(CommandSender sender, String currentAlias, String[] args) {
        Plugin[] plugins = Bukkit.getPluginManager().getPlugins();

        if (plugins.length == 0) {
            sender.sendMessage("§cAucun plugin n'a pu être trouvé !.");
            return true;
        }

        
        Plugin[] arrayOfPlugin1;
        int j = (arrayOfPlugin1 = plugins).length;
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < j; i++){
            Plugin plugin = arrayOfPlugin1[i];
            ChatColor chatColor = plugin.isEnabled() ? ChatColor.GREEN : ChatColor.RED;
            list.add(chatColor+"" + plugin.getName() +"§7");
        }
        sender.sendMessage("§6» §ePlugins §7(§a" + plugins.length+"§7) §8: " + list.toString().replace("[", "").replace("]", ""));
        return true;
    }

    // Spigot Start
    @Override
    public java.util.List<String> tabComplete(CommandSender sender, String alias, String[] args) throws IllegalArgumentException
    {
        return java.util.Collections.emptyList();
    }
    // Spigot End
}
