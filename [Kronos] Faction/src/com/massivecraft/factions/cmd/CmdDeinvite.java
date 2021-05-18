package com.massivecraft.factions.cmd;

import com.massivecraft.factions.FPlayer;
import com.massivecraft.factions.P;
import com.massivecraft.factions.struct.Permission;

public class CmdDeinvite extends FCommand
{
	
	public CmdDeinvite()
	{
		super();
		this.aliases.add("deinvite");
		this.aliases.add("deinv");
		
		this.requiredArgs.add("player name");
		//this.optionalArgs.put("", "");
		
		this.permission = Permission.DEINVITE.node;
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
			msg(P.prefix +"Le joueur %s est déjà dans votre Factions.", you.getName());
			return;
		}
		
		myFaction.deinvite(you);
		
		you.msg(P.prefix +"Le joueur %s vient de supprimé votre invitation pour rejoindre la Factions %s.", fme.describeTo(you), myFaction.describeTo(you));
		
		myFaction.msg(P.prefix +"Le joueur %s vient de supprimé l'invitation de %s.", fme.describeTo(myFaction), you.describeTo(myFaction));
	}
	
}
