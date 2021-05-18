package com.massivecraft.factions.cmd;

import org.bukkit.Bukkit;

import com.massivecraft.factions.Conf;
import com.massivecraft.factions.FPlayers;
import com.massivecraft.factions.Faction;
import com.massivecraft.factions.FPlayer;
import com.massivecraft.factions.P;
import com.massivecraft.factions.event.FPlayerJoinEvent;
import com.massivecraft.factions.struct.Permission;

public class CmdJoin extends FCommand
{
	public CmdJoin()
	{
		super();
		this.aliases.add("join");
		
		this.requiredArgs.add("faction name");
		this.optionalArgs.put("player", "you");
		
		this.permission = Permission.JOIN.node;
		this.disableOnLock = true;
		
		senderMustBePlayer = true;
		senderMustBeMember = false;
		senderMustBeModerator = false;
		senderMustBeAdmin = false;
	}
	
	@Override
	public void perform()
	{
		Faction faction = this.argAsFaction(0);
		if (faction == null) return;

		FPlayer fplayer = this.argAsBestFPlayerMatch(1, fme, false);
		boolean samePlayer = fplayer == fme;

		if (!samePlayer  && ! Permission.JOIN_OTHERS.has(sender, false))
		{
			msg("§6» <b>Vous n'avez pas la permission de rejoindre une faction !");
			return;
		}

		if ( ! faction.isNormal())
		{
			msg("§6» <b>Vous ne pouvez pas rejoindre cette faction.");
			return;
		}

		if (faction == fplayer.getFaction())
		{
			msg(P.prefix +"§e%s est déjà dans une faction.", fplayer.describeTo(fme, true));
			return;
		}

		if (fplayer.hasFaction())
		{
			msg(P.prefix +"§e%s §edoit quitter sa Faction actuelle pour rejoindre votre faction.", fplayer.describeTo(fme, true));
			return;
		}

		if (!Conf.canLeaveWithNegativePower && fplayer.getPower() < 0)
		{
			msg(P.prefix +"<b>%s §evous ne pouvez pas rejoindre une faction avec un power negatif, Vous devez avoir un power supérieur à 10", fplayer.describeTo(fme, true));
			return;
		}

		if( ! (faction.getOpen() || faction.isInvited(fplayer) || fme.isAdminBypassing() || Permission.JOIN_ANY.has(sender, false)))
		{
			msg(P.prefix +"§ePour rejoindre cet faction, veuillez y être inviter.");
			if (samePlayer)
				faction.msg(P.prefix +"§e%s §eas tenter de rejoindre votre faction.", fplayer.describeTo(faction, true));
			return;
		}

		// trigger the join event (cancellable)
		FPlayerJoinEvent joinEvent = new FPlayerJoinEvent(FPlayers.i.get(me),faction,FPlayerJoinEvent.PlayerJoinReason.COMMAND);
		Bukkit.getServer().getPluginManager().callEvent(joinEvent);
		if (joinEvent.isCancelled()) return;

		fme.msg(P.prefix +"§e%s §eà rejoins avec succès la faction §a%s§e.", fplayer.describeTo(fme, true), faction.getTag(fme));

		if (!samePlayer)
			fplayer.msg("<i>%s moved you into the faction %s.", fme.describeTo(fplayer, true), faction.getTag(fplayer));
		faction.msg(P.prefix +"§e%s §evient de rejoindre votre factions.", fplayer.describeTo(faction, true));

		fplayer.resetFactionData();
		fplayer.setFaction(faction);
		faction.deinvite(fplayer);

		if (Conf.logFactionJoin)
		{
			if (samePlayer)
				P.p.log("%s joined the faction %s.", fplayer.getName(), faction.getTag());
			else
				P.p.log("%s moved the player %s into the faction %s.", fme.getName(), fplayer.getName(), faction.getTag());
		}
	}
}
