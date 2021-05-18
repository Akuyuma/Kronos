package com.massivecraft.factions.cmd;

import com.massivecraft.factions.Board;
import com.massivecraft.factions.Conf;
import com.massivecraft.factions.FLocation;
import com.massivecraft.factions.P;
import com.massivecraft.factions.struct.Permission;


public class CmdOwnerList extends FCommand
{
	
	public CmdOwnerList()
	{
		super();
		this.aliases.add("ownerlist");
		
		//this.requiredArgs.add("");
		//this.optionalArgs.put("", "");
		
		this.permission = Permission.OWNERLIST.node;
		this.disableOnLock = false;
		
		senderMustBePlayer = true;
		senderMustBeMember = false;
		senderMustBeModerator = false;
		senderMustBeAdmin = false;
	}
	
	@Override
	public void perform()
	{
		boolean hasBypass = fme.isAdminBypassing(); 

		if ( ! hasBypass && ! assertHasFaction())
		{
			return;
		}

		if ( ! Conf.ownedAreasEnabled)
		{
			fme.msg("<b>Owned areas are disabled on this server.");
			return;
		}

		FLocation flocation = new FLocation(fme);

		if (Board.getFactionAt(flocation) != myFaction)
		{
			if (!hasBypass)
			{
				fme.msg(P.prefix +"§eCe claim n'est pas à votre Faction.");
				return;
			}

			myFaction = Board.getFactionAt(flocation);
			if (!myFaction.isNormal())
			{
				fme.msg(P.prefix +"§eCette zone n'est pas claim par vous, veuillez la claim pour effectuer cette action.");
				return;
			}
		}

		String owners = myFaction.getOwnerListString(flocation);

		if (owners == null || owners.isEmpty())
		{
			fme.msg(P.prefix +"§eAucuns membres n'est owner dans cette zone.");
			return;
		}

		fme.msg(P.prefix +"§eMembre(s) owner: %s", owners);
	}
}
