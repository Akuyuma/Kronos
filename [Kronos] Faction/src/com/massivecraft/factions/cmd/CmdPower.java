package com.massivecraft.factions.cmd;

import com.massivecraft.factions.FPlayer;
import com.massivecraft.factions.P;
import com.massivecraft.factions.struct.Permission;

public class CmdPower extends FCommand
{
	
	public CmdPower()
	{
		super();
		this.aliases.add("power");
		this.aliases.add("pow");
		this.aliases.add("p");
		
		//this.requiredArgs.add("faction tag");
		this.optionalArgs.put("player name", "you");
		
		this.permission = Permission.POWER.node;
		this.disableOnLock = false;
		
		senderMustBePlayer = false;
		senderMustBeMember = false;
		senderMustBeModerator = false;
		senderMustBeAdmin = false;
	}
	
	@Override
	public void perform()
	{
		FPlayer target = this.argAsBestFPlayerMatch(0, fme);
		if (target == null) return;
		
		if (target != fme && ! Permission.POWER_ANY.has(sender, true)) return;
		
		sender.sendMessage(P.prefix + "Power§8/§eMaxpower§8: §c" + target.getPowerRounded() + "§8/§c"+ target.getPowerMaxRounded());
	}
	
}
