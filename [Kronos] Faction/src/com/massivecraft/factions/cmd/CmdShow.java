package com.massivecraft.factions.cmd;

import java.util.Collection;

import com.massivecraft.factions.Conf;
import com.massivecraft.factions.FPlayer;
import com.massivecraft.factions.Faction;
import com.massivecraft.factions.Factions;
import com.massivecraft.factions.struct.Permission;
import com.massivecraft.factions.struct.Role;
import com.massivecraft.factions.struct.Relation;

public class CmdShow extends FCommand
{
	
	public CmdShow()
	{
		this.aliases.add("show");
		this.aliases.add("who");
		this.aliases.add("f");
		
		//this.requiredArgs.add("");
		this.optionalArgs.put("faction tag", "yours");
		
		this.permission = Permission.SHOW.node;
		this.disableOnLock = false;
		
		senderMustBePlayer = true;
		senderMustBeMember = false;
		senderMustBeModerator = false;
		senderMustBeAdmin = false;
	}

	@Override
	public void perform()
	{
		Faction faction = myFaction;
		if (this.argIsSet(0))
		{
			faction = this.argAsFaction(0);
			if (faction == null) return;
		}

		Collection<FPlayer> admins = faction.getFPlayersWhereRole(Role.ADMIN);
		Collection<FPlayer> mods = faction.getFPlayersWhereRole(Role.MODERATOR);
		Collection<FPlayer> normals = faction.getFPlayersWhereRole(Role.NORMAL);
		
		msg("§7§m------------------§8 [§b"+faction.getTag()+"§8] §7§m----------------------");
		msg("§6» §eNom de la faction§8: §a" + faction.getTag());
		msg("§6» §eDescription§8: §7%s", faction.getDescription());
		msg("§6» §eClassement§8: §a" + faction.getClassementPosition());
		if ( ! faction.isNormal())
		{
			return;
		}
		
		String peaceStatus = "";
		if (faction.isPeaceful())
		{
			peaceStatus = "     "+Conf.colorNeutral+"This faction is Peaceful";
		}
		
		msg("§6» §eComment rejoindre§8: §7"+(faction.getOpen() ? "Sans invitation" : "Avec une invitation")+peaceStatus);

		double powerBoost = faction.getPowerBoost();
		String boost = (powerBoost == 0.0) ? "" : (powerBoost > 0.0 ? " (bonus: " : " (Pénalisé: ") + powerBoost + ")";
		msg("§6» §eClaim§8/§ePower§8/§eMaxpower§8: §7%d§8/§7%d§8/§7%d %s", faction.getLandRounded(), faction.getPowerRounded(), faction.getPowerMaxRounded(), boost);

		if (faction.isPermanent())
		{
			msg("§6» Cette faction est permamante.");
		}

		String listpart;
		
		// List relation
		String allyList = p.txt.parse("§6» §eAllié(s) §7(§d"+faction.getAllysize(faction)+"§7/§d3§7)§8: §d");
		String enemyList = p.txt.parse("§6» §eEnemies §7(§c"+faction.getEnemySize(faction)+"§7/§c∞§7)");
		for (Faction otherFaction : Factions.i.get())
		{
			if (otherFaction == faction) continue;

			Relation rel = otherFaction.getRelationTo(faction);
			if ( ! rel.isAlly() && ! rel.isEnemy()) continue;  // if not ally or enemy, drop out now so we're not wasting time on it; good performance boost

			listpart = otherFaction.getTag(fme)+p.txt.parse("<i>")+", ";
			if (rel.isAlly()){
				allyList += listpart;
			}
		}
		
		if (allyList.endsWith(", "))
			allyList = allyList.substring(0, allyList.length()-2);
		
		sendMessage(allyList);
		sendMessage(enemyList);
		
		// List the members...
		String onlineList = p.txt.parse("<a>")+"§6» §eMembre(s) connecté(s) §7(§a"+faction.getOnlinePlayers().size()+"§7/§a"+faction.getFPlayers().size()+"§7)§8: §f";
		String offlineList = p.txt.parse("<a>")+"§6» §eMembre(s) déconnecté(s) §7(§a"+(faction.getFPlayers().size()-faction.getOnlinePlayers().size())+"§7/§a"+faction.getFPlayers().size()+"§7)§8: §f";
		for (FPlayer follower : admins)
		{
			listpart = follower.getNameAndTitle(fme)+p.txt.parse("<i>")+", ";
			if (follower.isOnlineAndVisibleTo(me))
			{
				onlineList += listpart;
			}
			else
			{
				offlineList += listpart;
			}
		}
		for (FPlayer follower : mods)
		{
			listpart = follower.getNameAndTitle(fme)+p.txt.parse("<i>")+", ";
			if
			(follower.isOnlineAndVisibleTo(me))
			{
				onlineList += listpart;
			} else {
				offlineList += listpart;
			}
		}
		for (FPlayer follower : normals) {
			listpart = follower.getNameAndTitle(fme)+p.txt.parse("<i>")+", ";
			if (follower.isOnlineAndVisibleTo(me)) {
				onlineList += listpart;
			} else {
				offlineList += listpart;
			}
		}
		
		if (onlineList.endsWith(", ")) {
			onlineList = onlineList.substring(0, onlineList.length()-2);
		}
		if (offlineList.endsWith(", ")) {
			offlineList = offlineList.substring(0, offlineList.length()-2);
		}
		
		sendMessage(onlineList);
		sendMessage(offlineList);
		msg("§7§m------------------§8 [§b"+faction.getTag()+"§8] §7§m----------------------");
	}
	
}
