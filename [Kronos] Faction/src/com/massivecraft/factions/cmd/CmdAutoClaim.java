package com.massivecraft.factions.cmd;

import com.massivecraft.factions.Faction;
import com.massivecraft.factions.P;
import com.massivecraft.factions.struct.Permission;
import com.massivecraft.factions.struct.Role;

public class CmdAutoClaim extends FCommand
{
	public CmdAutoClaim()
	{
		super();
		this.aliases.add("autoclaim");
		
		//this.requiredArgs.add("");
		this.optionalArgs.put("faction", "your");
		
		this.permission = Permission.AUTOCLAIM.node;
		this.disableOnLock = true;
		
		senderMustBePlayer = true;
		senderMustBeMember = false;
		senderMustBeModerator = false;
		senderMustBeAdmin = false;
	}

	@Override
	public void perform()
	{
		Faction forFaction = this.argAsFaction(0, myFaction);
		if (forFaction == null || forFaction == fme.getAutoClaimFor())
		{
			fme.setAutoClaimFor(null);
			msg(P.prefix +"Mode de claim automatique �cD�sactiv�e.");
			return;
		}

		if (! fme.canClaimForFaction(forFaction))
		{
			if (myFaction == forFaction)
				msg("<b>You must be <h>%s<b> to claim land.", Role.MODERATOR.toString());
			else
				msg("<b>You can't claim land for <h>%s<b>.", forFaction.describeTo(fme));

			return;
		}
		
		fme.setAutoClaimFor(forFaction);
		
		msg(P.prefix +"Mode de claim automatique �cD�sactiv�e.");
		fme.attemptClaim(forFaction, me.getLocation(), true);
	}
	
}