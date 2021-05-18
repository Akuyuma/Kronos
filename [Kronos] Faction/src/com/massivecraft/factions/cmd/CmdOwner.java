package com.massivecraft.factions.cmd;

import com.massivecraft.factions.Board;
import com.massivecraft.factions.Conf;
import com.massivecraft.factions.FLocation;
import com.massivecraft.factions.Faction;
import com.massivecraft.factions.P;
import com.massivecraft.factions.FPlayer;
import com.massivecraft.factions.integration.SpoutFeatures;
import com.massivecraft.factions.struct.Permission;
import com.massivecraft.factions.struct.Role;


public class CmdOwner extends FCommand
{
	
	public CmdOwner()
	{
		super();
		this.aliases.add("owner");
		
		//this.requiredArgs.add("");
		this.optionalArgs.put("player name", "you");
		
		this.permission = Permission.OWNER.node;
		this.disableOnLock = true;
		
		senderMustBePlayer = true;
		senderMustBeMember = false;
		senderMustBeModerator = false;
		senderMustBeAdmin = false;
	}
	
	// TODO: Fix colors!
	
	@Override
	public void perform()
	{
		boolean hasBypass = fme.isAdminBypassing();
		
		if ( ! hasBypass && ! assertHasFaction()) {
			return;
		}

		if ( ! Conf.ownedAreasEnabled)
		{
			fme.msg("<b>Sorry, but owned areas are disabled on this server.");
			return;
		}

		if ( ! hasBypass && Conf.ownedAreasLimitPerFaction > 0 && myFaction.getCountOfClaimsWithOwners() >= Conf.ownedAreasLimitPerFaction)
		{
			fme.msg("<b>Sorry, but you have reached the server's <h>limit of %d <b>owned areas per faction.", Conf.ownedAreasLimitPerFaction);
			return;
		}

		if ( ! hasBypass && !assertMinRole(Conf.ownedAreasModeratorsCanSet ? Role.MODERATOR : Role.ADMIN))
		{
			return;
		}

		FLocation flocation = new FLocation(fme);

		Faction factionHere = Board.getFactionAt(flocation);
		if (factionHere != myFaction)
		{
			if ( ! hasBypass)
			{
				fme.msg(P.prefix +"§eCet zone n'est pas à vous, veuillez claim pour effectuer cette action.");
				return;
			}

			if ( ! factionHere.isNormal())
			{
				fme.msg(P.prefix +"§eCet zone n'est pas à vous, veuillez claim pour effectuer cette action.");
				return;
			}
		}

		FPlayer target = this.argAsBestFPlayerMatch(0, fme);
		if (target == null) return;

		String playerName = target.getName();

		if (target.getFaction() != myFaction)
		{
			fme.msg(P.prefix +"§e%s n'est pas membre de votre Faction.", playerName);
			return;
		}

		// if no player name was passed, and this claim does already have owners set, clear them
		if (args.isEmpty() && myFaction.doesLocationHaveOwnersSet(flocation))
		{
			myFaction.clearClaimOwnership(flocation);
			SpoutFeatures.updateOwnerListLoc(flocation);
			fme.msg(P.prefix +"§eVous venez d'enlever tout les membres owner de ce claim.");
			return;
		}

		if (myFaction.isPlayerInOwnerList(playerName, flocation))
		{
			myFaction.removePlayerAsOwner(playerName, flocation);
			SpoutFeatures.updateOwnerListLoc(flocation);
			fme.msg("<i>You have removed ownership of this claimed land from %s<i>.", playerName);
			return;
		}

		myFaction.setPlayerAsOwner(playerName, flocation);
		SpoutFeatures.updateOwnerListLoc(flocation);

		fme.msg(P.prefix +"§eVous venez d'ajouter le membre %s dans ce claim.", playerName);
	}
}
