package com.massivecraft.factions.cmd;

import org.bukkit.Bukkit;

import com.massivecraft.factions.Conf;
import com.massivecraft.factions.event.FPlayerLeaveEvent;
import com.massivecraft.factions.event.FactionDisbandEvent;
import com.massivecraft.factions.FPlayers;
import com.massivecraft.factions.Faction;
import com.massivecraft.factions.P;
import com.massivecraft.factions.FPlayer;
import com.massivecraft.factions.integration.SpoutFeatures;
import com.massivecraft.factions.struct.Permission;
import com.massivecraft.factions.struct.Role;


public class CmdDisband extends FCommand
{
	public CmdDisband()
	{
		super();
		this.aliases.add("disband");
		
		//this.requiredArgs.add("");
		this.optionalArgs.put("faction tag", "yours");
		
		this.permission = Permission.DISBAND.node;
		this.disableOnLock = true;
		
		senderMustBePlayer = false;
		senderMustBeMember = false;
		senderMustBeModerator = false;
		senderMustBeAdmin = false;
	}
	
	@Override
	public void perform()
	{
		// The faction, default to your own.. but null if console sender.
		Faction faction = this.argAsFaction(0, fme == null ? null : myFaction);
		if (faction == null) return;
		
		boolean isMyFaction = fme == null ? false : faction == myFaction;
		
		if (isMyFaction)
		{
			if ( ! assertMinRole(Role.ADMIN)) return;
		}
		else
		{
			if ( ! Permission.DISBAND_ANY.has(sender, true))
			{
				return;
			}
		}

		if (! faction.isNormal())
		{
			msg(P.prefix +"Vous ne pouvez pas supprimer les Factions suivantes: Wilderness, SafeZone, WarZone.");
			return;
		}
		if (faction.isPermanent())
		{
			msg("<i>This faction is designated as permanent, so you cannot disband it.");
			return;
		}

		FactionDisbandEvent disbandEvent = new FactionDisbandEvent(me, faction.getId());
		Bukkit.getServer().getPluginManager().callEvent(disbandEvent);
		if(disbandEvent.isCancelled()) return;

		// Send FPlayerLeaveEvent for each player in the faction
		for ( FPlayer fplayer : faction.getFPlayers() )
		{
			Bukkit.getServer().getPluginManager().callEvent(new FPlayerLeaveEvent(fplayer, faction, FPlayerLeaveEvent.PlayerLeaveReason.DISBAND));
		}

		// Inform all players
		for (FPlayer fplayer : FPlayers.i.getOnline())
		{
			String who = senderIsConsole ? "A server admin" : fme.describeTo(fplayer);
			if (fplayer.getFaction() == faction)
			{
				fplayer.msg(P.prefix +"La Factions vient d'?tre suppimer.", who);
			}
			else
			{
				fplayer.msg(P.prefix +"?eLe joueur %s ?evient de supprim? la Factions ?a%s?e.", who, faction.getTag(fplayer));
			}
		}
		if (Conf.logFactionDisband)
			P.p.log("The faction "+faction.getTag()+" ("+faction.getId()+") was disbanded by "+(senderIsConsole ? "console command" : fme.getName())+".");	
		
		faction.detach();

		SpoutFeatures.updateAppearances();
	}
}
