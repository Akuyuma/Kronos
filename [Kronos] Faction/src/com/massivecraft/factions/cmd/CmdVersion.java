package com.massivecraft.factions.cmd;

import com.massivecraft.factions.P;
import com.massivecraft.factions.struct.Permission;


public class CmdVersion extends FCommand
{
	public CmdVersion()
	{
		this.aliases.add("version");
		
		//this.requiredArgs.add("");
		//this.optionalArgs.put("", "");
		
		this.permission = Permission.VERSION.node;
		this.disableOnLock = false;
		
		senderMustBePlayer = false;
		senderMustBeMember = false;
		senderMustBeModerator = false;
		senderMustBeAdmin = false;
	}

	@Override
	public void perform()
	{
		msg("<i>Verison du plugin faction§7: §b"+P.p.getDescription().getFullName());
		msg("<i>Version custom par Maxlego08");
	}
}
