package com.massivecraft.factions.cmd;

import com.massivecraft.factions.Conf;
import com.massivecraft.factions.FPlayer;
import com.massivecraft.factions.FPlayers;
import com.massivecraft.factions.P;
import com.massivecraft.factions.struct.Permission;
import com.massivecraft.factions.zcore.util.TextUtil;

public class CmdDescription extends FCommand
{
	public CmdDescription()
	{
		super();
		this.aliases.add("desc");
		
		this.requiredArgs.add("desc");
		this.errorOnToManyArgs = false;
		//this.optionalArgs
		
		this.permission = Permission.DESCRIPTION.node;
		this.disableOnLock = true;
		
		senderMustBePlayer = true;
		senderMustBeMember = false;
		senderMustBeModerator = true;
		senderMustBeAdmin = false;
	}
	
	@Override
	public void perform()
	{

		myFaction.setDescription(TextUtil.implode(args, " ").replaceAll("(&([a-f0-9]))", "& $2"));  // since "&" color tags seem to work even through plain old FPlayer.sendMessage() for some reason, we need to break those up

		if ( ! Conf.broadcastDescriptionChanges)
		{
			fme.msg(P.prefix +"Vous venez de modifier la description de votre Factions:", myFaction.describeTo(fme));
			fme.sendMessage(P.prefix + myFaction.getDescription());
			return;
		}

		// Broadcast the description to everyone
		for (FPlayer fplayer : FPlayers.i.getOnline())
		{
			fplayer.msg("<i>The faction %s<i> changed their description to:", myFaction.describeTo(fplayer));
			fplayer.sendMessage(myFaction.getDescription());  // players can inject "&" or "`" or "<i>" or whatever in their description; &k is particularly interesting looking
		}
	}
	
}
