package com.massivecraft.factions.cmd;

import com.massivecraft.factions.Board;
import com.massivecraft.factions.FLocation;
import com.massivecraft.factions.P;
import com.massivecraft.factions.struct.Permission;


public class CmdMap extends FCommand
{
	public CmdMap()
	{
		super();
		this.aliases.add("map");
		
		//this.requiredArgs.add("");
		this.optionalArgs.put("on/off", "once");
		
		this.permission = Permission.MAP.node;
		this.disableOnLock = false;
		
		senderMustBePlayer = true;
		senderMustBeMember = false;
		senderMustBeModerator = false;
		senderMustBeAdmin = false;
	}
	
	@Override
	public void perform()
	{
		if (this.argIsSet(0))
		{
			if (this.argAsBool(0, ! fme.isMapAutoUpdating()))
			{
				// Turn on

				fme.setMapAutoUpdating(true);
				msg(P.prefix +"§eMode Map automatique §aActivé§e.");
				
				// And show the map once
				showMap();
			}
			else
			{
				// Turn off
				fme.setMapAutoUpdating(false);
				msg(P.prefix +"§eMode Map automatique §cDésactivé§e.");
			}
		}
		else
		{

			showMap();
		}
	}
	
	public void showMap()
	{
		sendMessage(Board.getMap(myFaction, new FLocation(fme), fme.getPlayer().getLocation().getYaw()));
	}
	
}
