package com.massivecraft.factions.cmd;

import java.util.ArrayList;

import org.bukkit.Bukkit;

import com.massivecraft.factions.Conf;
import com.massivecraft.factions.Faction;
import com.massivecraft.factions.Factions;
import com.massivecraft.factions.P;
import com.massivecraft.factions.event.FactionRenameEvent;
import com.massivecraft.factions.integration.SpoutFeatures;
import com.massivecraft.factions.struct.Permission;
import com.massivecraft.factions.util.MiscUtil;

public class CmdTag extends FCommand
{
	
	public CmdTag()
	{
		this.aliases.add("tag");
		
		this.requiredArgs.add("faction tag");
		//this.optionalArgs.put("", "");
		
		this.permission = Permission.TAG.node;
		this.disableOnLock = true;
		
		senderMustBePlayer = true;
		senderMustBeMember = false;
		senderMustBeModerator = true;
		senderMustBeAdmin = false;
	}
	
	@Override
	public void perform()
	{
		String tag = this.argAsString(0);
		
		// TODO does not first test cover selfcase?
		if (Factions.i.isTagTaken(tag) && ! MiscUtil.getComparisonString(tag).equals(myFaction.getComparisonTag()))
		{
			msg(P.prefix +"§eCe nom de Factions est déjà pris.");
			return;
		}
		
		ArrayList<String> errors = new ArrayList<String>();
		errors.addAll(Factions.validateTag(tag));
		if (errors.size() > 0)
		{
			sendMessage(errors);
			return;
		}

		// trigger the faction rename event (cancellable)
		FactionRenameEvent renameEvent = new FactionRenameEvent(fme, tag);
		Bukkit.getServer().getPluginManager().callEvent(renameEvent);
		if(renameEvent.isCancelled()) return;

		String oldtag = myFaction.getTag();
		myFaction.setTag(tag);
		
		// Inform
		myFaction.msg(P.prefix +"§e%s change le nom de faction par %s.", fme.describeTo(myFaction, true), myFaction.getTag(myFaction));
		for (Faction faction : Factions.i.get())
		{
			if (faction == myFaction)
			{
				continue;
			}
			faction.msg(P.prefix +"§eLa Faction %s change de nom pour %s.", fme.getColorTo(faction)+oldtag, myFaction.getTag(faction));
		}

		if (Conf.spoutFactionTagsOverNames)
		{
			SpoutFeatures.updateAppearances(myFaction);
		}
	}
	
}
