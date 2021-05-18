package com.massivecraft.factions.cmd;

import com.massivecraft.factions.Board;
import com.massivecraft.factions.Conf;
import com.massivecraft.factions.FLocation;
import com.massivecraft.factions.Faction;
import com.massivecraft.factions.P;
import com.massivecraft.factions.struct.Permission;
import com.massivecraft.factions.struct.Role;

public class CmdSethome extends FCommand
{
	public CmdSethome()
	{
		this.aliases.add("sethome");
		
		//this.requiredArgs.add("");
		this.optionalArgs.put("faction tag", "mine");
		
		this.permission = Permission.SETHOME.node;
		this.disableOnLock = true;
		
		senderMustBePlayer = true;
		senderMustBeMember = false;
		senderMustBeModerator = false;
		senderMustBeAdmin = false;
	}
	
	@Override
	public void perform()
	{
		if ( ! Conf.homesEnabled)
		{
			fme.msg("§6» <b>Vous ne pouvez pas mettre de home pour votre faction sur le serveur !");
			return;
		}
		
		Faction faction = this.argAsFaction(0, myFaction);
		if (faction == null) return;
		
		// Can the player set the home for this faction?
		if (faction == myFaction)
		{
			if ( ! Permission.SETHOME_ANY.has(sender) && ! assertMinRole(Role.MODERATOR)) return;
		}
		else
		{
			if ( ! Permission.SETHOME_ANY.has(sender, true)) return;
		}
		
		// Can the player set the faction home HERE?
		if
		(
			! Permission.BYPASS.has(me)
			&&
			Conf.homesMustBeInClaimedTerritory
			&& 
			Board.getFactionAt(new FLocation(me)) != faction
		)
		{
			fme.msg(P.prefix +"§eCette zone n'est pas claim par votre Faction, veuillez claim pour effectuer cette action.");
			return;
		}

		faction.setHome(me.getLocation());
		
		faction.msg(P.prefix +"§e%s vient de poser le home de Faction: ", fme.describeTo(myFaction, true));
		faction.sendMessage(p.cmdBase.cmdHome.getUseageTemplate());
		if (faction != myFaction)
		{
			fme.msg(P.prefix +"§eVous venez de poser le home de Faction.");
		}
	}
	
}
