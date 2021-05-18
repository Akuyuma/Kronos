package com.massivecraft.factions.cmd;

import org.bukkit.Bukkit;

import com.massivecraft.factions.Faction;
import com.massivecraft.factions.P;
import com.massivecraft.factions.FPlayer;
import com.massivecraft.factions.FPlayers;
import com.massivecraft.factions.event.FPlayerJoinEvent;
import com.massivecraft.factions.struct.Permission;
import com.massivecraft.factions.struct.Role;

public class CmdAdmin extends FCommand
{	
	public CmdAdmin()
	{
		super();
		this.aliases.add("admin");
		
		this.requiredArgs.add("player name");
		//this.optionalArgs.put("", "");
		
		this.permission = Permission.ADMIN.node;
		this.disableOnLock = true;
		
		senderMustBePlayer = false;
		senderMustBeMember = false;
		senderMustBeModerator = false;
		senderMustBeAdmin = false;
	}
	
	@Override
	public void perform()
	{
		FPlayer fyou = this.argAsBestFPlayerMatch(0);
		if (fyou == null) return;

		boolean permAny = Permission.ADMIN_ANY.has(sender, false);
		Faction targetFaction = fyou.getFaction();

		if (targetFaction != myFaction && !permAny)
		{
			msg(P.prefix +"Le joueur %s n'est pas dans votre Factions.", fyou.describeTo(fme, true));
			return;
		}

		if (fme != null && fme.getRole() != Role.ADMIN && !permAny)
		{
			msg(P.prefix +"Vous n'êtes pas l'administrateur de votre Factions.");
			return;
		}

		if (fyou == fme && !permAny)
		{
			msg("<b>The target player musn't be yourself.");
			return;
		}

		// only perform a FPlayerJoinEvent when newLeader isn't actually in the faction
		if (fyou.getFaction() != targetFaction)
		{
			FPlayerJoinEvent event = new FPlayerJoinEvent(FPlayers.i.get(me),targetFaction,FPlayerJoinEvent.PlayerJoinReason.LEADER);
			Bukkit.getServer().getPluginManager().callEvent(event);
			if (event.isCancelled()) return;
		}

		FPlayer admin = targetFaction.getFPlayerAdmin();

		// if target player is currently admin, demote and replace him
		if (fyou == admin)
		{
			targetFaction.promoteNewLeader();
			msg("<i>You have demoted %s<i> from the position of faction admin.", fyou.describeTo(fme, true));
			fyou.msg("<i>You have been demoted from the position of faction admin by %s<i>.", senderIsConsole ? "a server admin" : fme.describeTo(fyou, true));
			return;
		}

		// promote target player, and demote existing admin if one exists
		if (admin != null)
			admin.setRole(Role.MODERATOR);
		fyou.setRole(Role.ADMIN);
		msg(P.prefix +"Vous venez de donner le rôle d'administrateur de votre Factions à %s.", fyou.describeTo(fme, true));

		// Inform all players
		for (FPlayer fplayer : FPlayers.i.getOnline())
		{
			fplayer.msg(P.prefix +"Le joueur %s vient de donner me rôle d'administrateur de votre Factions à %s.", senderIsConsole ? "Console" : fme.describeTo(fplayer, true), fyou.describeTo(fplayer), targetFaction.describeTo(fplayer));
		}
	}
	
}
