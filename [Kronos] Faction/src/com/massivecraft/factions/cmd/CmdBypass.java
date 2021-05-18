package com.massivecraft.factions.cmd;

import com.massivecraft.factions.P;
import com.massivecraft.factions.struct.Permission;

public class CmdBypass extends FCommand
{
	public CmdBypass()
	{
		super();
		this.aliases.add("bypass");
		
		//this.requiredArgs.add("");
		this.optionalArgs.put("on/off", "flip");
		
		this.permission = Permission.BYPASS.node;
		this.disableOnLock = false;
		
		senderMustBePlayer = true;
		senderMustBeMember = false;
		senderMustBeModerator = false;
		senderMustBeAdmin = false;
	}
	
	@Override
	public void perform()
	{
		fme.setIsAdminBypassing(this.argAsBool(0, ! fme.isAdminBypassing()));
		
		// TODO: Move this to a transient field in the model??
		if ( fme.isAdminBypassing())
		{
			fme.msg(P.prefix +"Mode Bypass est �aActiv�e.");
			P.p.log(fme.getName() + " has ENABLED admin bypass mode.");
		}
		else
		{
			fme.msg(P.prefix +"Mode Bypass est �cD�sactiv�e.");
			P.p.log(fme.getName() + " DISABLED admin bypass mode.");
		}
	}
}
