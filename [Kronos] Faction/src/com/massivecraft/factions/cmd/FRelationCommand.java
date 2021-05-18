package com.massivecraft.factions.cmd;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import com.massivecraft.factions.Conf;
import com.massivecraft.factions.Faction;
import com.massivecraft.factions.event.FactionRelationEvent;
import com.massivecraft.factions.integration.SpoutFeatures;
import com.massivecraft.factions.struct.Permission;
import com.massivecraft.factions.struct.Relation;

public abstract class FRelationCommand extends FCommand
{
	public Relation targetRelation;
	
	public FRelationCommand()
	{
		super();
		this.requiredArgs.add("faction tag");
		//this.optionalArgs.put("player name", "you");
		
		this.permission = Permission.RELATION.node;
		this.disableOnLock = true;
		
		senderMustBePlayer = true;
		senderMustBeMember = false;
		senderMustBeModerator = true;
		senderMustBeAdmin = false;
	}
	
	@Override
	public void perform()
	{
		Faction them = this.argAsFaction(0);
		if (them == null) return;
		
		if ( ! them.isNormal())
		{
			msg("§6» <b>Nan! Tu ne peux pas.");
			return;
		}
		
		if (them == myFaction)
		{
			msg("§6» <b>Nan! Vous ne pouvez pas déclarer une relation à vous-même :)");
			return;
		}

		if (myFaction.getRelationWish(them) == targetRelation)
		{
			msg("§6» <b>Vous avez déjà cette relation avec %s.", them.getTag());
			return;
		}
		
		if (myFaction.getAllysize(myFaction) == 3 && targetRelation == Relation.ALLY){
			sender.sendMessage("§6» §cVous ne pouvez pas avoir plus de §a3 §cfaction en alliance !");
			return;
		}

		// if economy is enabled, they're not on the bypass list, and this command has a cost set, make 'em pay
		if ( ! payForCommand(targetRelation.getRelationCost(), "to change a relation wish", "for changing a relation wish")) return;

		// try to set the new relation
		Relation oldRelation = myFaction.getRelationTo(them, true);
		myFaction.setRelationWish(them, targetRelation);
		Relation currentRelation = myFaction.getRelationTo(them, true);
		ChatColor currentRelationColor = currentRelation.getColor();

		// if the relation change was successful
		if (targetRelation.value == currentRelation.value)
		{
			// trigger the faction relation event
			FactionRelationEvent relationEvent = new FactionRelationEvent(myFaction, them, oldRelation, currentRelation);
			Bukkit.getServer().getPluginManager().callEvent(relationEvent);

			them.msg("§6» <i>Votre faction est maintenant "+currentRelationColor+targetRelation.toString()+"<i> pour "+currentRelationColor+myFaction.getTag());
			myFaction.msg("§6» <i>Votre faction est maintenant "+currentRelationColor+targetRelation.toString()+"<i> pour "+currentRelationColor+them.getTag());
		}
		// inform the other faction of your request
		else
		{
			them.msg("§6» §e"+currentRelationColor+myFaction.getTag()+"<i> souhaite être votre "+targetRelation.getColor()+targetRelation.toString());
			them.msg("§6» <i>Faite <c>/"+Conf.baseCommandAliases.get(0)+" "+targetRelation+" "+myFaction.getTag()+"<i> pour accepter.");
			myFaction.msg("§6» §e"+currentRelationColor+them.getTag()+"<i> ont été informés que vous souhaitez être "+targetRelation.getColor()+targetRelation);
		}
		
		if ( ! targetRelation.isNeutral() && them.isPeaceful())
		{
			them.msg("§6» <i>Cela n'aura aucun effet tant que votre faction est pacifique.");
			myFaction.msg("§6» <i>Cela n'aura aucun effet pendant que leur faction est pacifique.");
		}
		
		if ( ! targetRelation.isNeutral() && myFaction.isPeaceful())
		{
			them.msg("§6» <i>Cela n'aura aucun effet pendant que leur faction est pacifique.");
			myFaction.msg(" §6» <i>Cela n'aura aucun effet pendant que leur faction est pacifique.");
		}

		SpoutFeatures.updateAppearances(myFaction, them);
		SpoutFeatures.updateTerritoryDisplayLoc(null);
	}
}
