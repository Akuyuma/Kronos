package com.massivecraft.factions.cmd;

import com.massivecraft.factions.Faction;
import com.massivecraft.factions.P;
import com.massivecraft.factions.FPlayer;
import com.massivecraft.factions.struct.Permission;
import com.massivecraft.factions.struct.Role;

public class CmdMod extends FCommand
{
	
	public CmdMod()
	{
		super();
		this.aliases.add("mod");
		
		this.requiredArgs.add("player name");
		//this.optionalArgs.put("", "");
		
		this.permission = Permission.MOD.node;
		this.disableOnLock = true;
		
		senderMustBePlayer = false;
		senderMustBeMember = false;
		senderMustBeModerator = false;
		senderMustBeAdmin = false;
	}
	
	@Override
	public void perform()
	{
		FPlayer you = this.argAsBestFPlayerMatch(0);
		if (you == null) return;

		boolean permAny = Permission.MOD_ANY.has(sender, false);
		Faction targetFaction = you.getFaction();

		if (targetFaction != myFaction && !permAny)
		{
			msg(P.prefix +"§e%s n'est pas dans votre Faction.", you.describeTo(fme, true));
			return;
		}

		if (fme != null && fme.getRole() != Role.ADMIN && !permAny)
		{
			msg(P.prefix +"§eVous devez être administrateur de votre Factions pour effectuer cette action.");
			return;
		}

		if (you == fme && !permAny)
		{
			msg(P.prefix +"§eLe membre viser ne peux pas être vous-même");
			return;
		}

		if (you.getRole() == Role.ADMIN)
		{
			msg(P.prefix +"§eLe membre viser est déjà administrateur de la Factions.");
			return;
		}

		if (you.getRole() == Role.MODERATOR)
		{
			// Revoke
			you.setRole(Role.NORMAL);
			targetFaction.msg(P.prefix +"§e%s n'est plus modérateur de la Faction.", you.describeTo(targetFaction, true));
			msg(P.prefix +"§eVous venez d'enlever le status de modérateur à %s.", you.describeTo(fme, true));
		}
		else
		{
			// Give
			you.setRole(Role.MODERATOR);
			targetFaction.msg(P.prefix +"§e%s est désormais modérateur de la Faction.", you.describeTo(targetFaction, true));
			msg(P.prefix +"§eVous venez de mettre le status de modérateur à %s.", you.describeTo(fme, true));
		}
	}
	
}
