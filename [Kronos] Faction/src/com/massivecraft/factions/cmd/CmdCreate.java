package com.massivecraft.factions.cmd;

import java.util.ArrayList;

import org.bukkit.Bukkit;

import com.massivecraft.factions.Conf;
import com.massivecraft.factions.FPlayer;
import com.massivecraft.factions.FPlayers;
import com.massivecraft.factions.Faction;
import com.massivecraft.factions.Factions;
import com.massivecraft.factions.P;
import com.massivecraft.factions.event.FPlayerJoinEvent;
import com.massivecraft.factions.event.FactionCreateEvent;
import com.massivecraft.factions.struct.Permission;
import com.massivecraft.factions.struct.Role;


public class CmdCreate extends FCommand
{
	public CmdCreate()
	{
		super();
		this.aliases.add("create");
		
		this.requiredArgs.add("nom de faction");
		//this.optionalArgs.put("", "");
		
		this.permission = Permission.CREATE.node;
		this.disableOnLock = true;
		
		senderMustBePlayer = true;
		senderMustBeMember = false;
		senderMustBeModerator = false;
		senderMustBeAdmin = false;
	}
	
	@Override
	public void perform()
	{
		String tag = this.argAsString(0);
		
		if (fme.hasFaction())
		{
			msg(P.prefix +"Vous avez déjà une Factions, veuillez la quitter pour effectuer cette action.");
			return;
		}
		
		if (Factions.i.isTagTaken(tag))
		{
			msg(P.prefix +"Ce nom de Factions est déjà pris.");
			return;
		}
		
		ArrayList<String> tagValidationErrors = Factions.validateTag(tag);
		if (tagValidationErrors.size() > 0)
		{
			sendMessage(tagValidationErrors);
			return;
		}

		// trigger the faction creation event (cancellable)
		FactionCreateEvent createEvent = new FactionCreateEvent(me, tag);
		Bukkit.getServer().getPluginManager().callEvent(createEvent);
		if(createEvent.isCancelled()) return;

		Faction faction = Factions.i.create();

		// TODO: Why would this even happen??? Auto increment clash??
		if (faction == null)
		{
			msg("<b>There was an internal error while trying to create your faction. Please try again.");
			return;
		}

		// finish setting up the Faction
		faction.setTag(tag);

		// trigger the faction join event for the creator
		FPlayerJoinEvent joinEvent = new FPlayerJoinEvent(FPlayers.i.get(me),faction,FPlayerJoinEvent.PlayerJoinReason.CREATE);
		Bukkit.getServer().getPluginManager().callEvent(joinEvent);
		// join event cannot be cancelled or you'll have an empty faction

		// finish setting up the FPlayer
		fme.setRole(Role.ADMIN);
		fme.setFaction(faction);

		for (FPlayer follower : FPlayers.i.getOnline())
		{
			follower.msg(P.prefix +"Le joueur §a%s §evient de créer la Factions §a%s.", fme.describeTo(follower, true), faction.getTag(follower));
		}

		if (Conf.logFactionCreate)
			P.p.log(fme.getName()+" created a new faction: "+tag);
	}
	
}
