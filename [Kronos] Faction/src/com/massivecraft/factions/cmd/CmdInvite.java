package com.massivecraft.factions.cmd;

import com.massivecraft.factions.FPlayer;
import com.massivecraft.factions.P;
import com.massivecraft.factions.struct.Permission;

public class CmdInvite extends FCommand
{
	public CmdInvite()
	{
		super();
		this.aliases.add("invite");
		this.aliases.add("inv");
		
		this.requiredArgs.add("player name");
		//this.optionalArgs.put("", "");
		
		this.permission = Permission.INVITE.node;
		this.disableOnLock = true;
		
		senderMustBePlayer = true;
		senderMustBeMember = false;
		senderMustBeModerator = true;
		senderMustBeAdmin = false;
	}
	
	@Override
	public void perform()
	{
		FPlayer you = this.argAsBestFPlayerMatch(0);
		if (you == null) return;
		
		if (you.getFaction() == myFaction)
		{
			msg(P.prefix +"§eLe joueur %s §eest déjà inviter dans votre factions.", you.getName());
			return;
		}

		myFaction.invite(you);
		
		you.msg(P.prefix +"§e%s §evient de vous invité dans sa factions.", fme.describeTo(you, true));
		myFaction.msg(P.prefix +"§e%s §evient d'inviter %s dans la faction.", fme.describeTo(myFaction, true), you.describeTo(myFaction));
	}
	
}
