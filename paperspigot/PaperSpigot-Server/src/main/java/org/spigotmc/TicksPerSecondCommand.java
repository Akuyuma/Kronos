package org.spigotmc;

import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;

import java.util.Arrays;

public class TicksPerSecondCommand extends Command
{

    public TicksPerSecondCommand(String name)
    {
        super( name );
        this.description = "Gets the current ticks per second for the server";
        this.usageMessage = "/tps";
        this.setPermission( "bukkit.command.tps" );
        this.setAliases(Arrays.asList(new String[] { "lag" }));
    }

    @Override
    public boolean execute(CommandSender sender, String currentAlias, String[] args) {
        if ( !testPermission( sender ) ) {
            return true;
        }

        // PaperSpigot start - Further improve tick handling
        double[] tps = Bukkit.spigot().getTPS();
        String[] tpsAvg = new String[tps.length];

        for ( int i = 0; i < tps.length; i++) {
            tpsAvg[i] = format( tps[i] );
        }

        sender.sendMessage( ChatColor.GOLD + "» §eTPS depuis 5s, 1m, 5m, 15m: " + StringUtils.join(tpsAvg, ", "));
        // PaperSpigot end

        return true;
    }

    private static String format(double tps) {
        return ( ( tps > 18.0 ) ? ChatColor.GRAY : ( tps > 16.0 ) ? ChatColor.YELLOW : ChatColor.DARK_RED ).toString() + ( ( tps > 20.0 ) ? "*" : "" ) + Math.min( Math.round( tps * 100.0 ) / 100.0, 20.0 );
    }
}
