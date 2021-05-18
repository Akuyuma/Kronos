package com.massivecraft.factions.cmd;

import java.util.ArrayList;

import org.bukkit.entity.Player;

import com.massivecraft.factions.Conf;
import com.massivecraft.factions.P;
import com.massivecraft.factions.integration.Econ;
import com.massivecraft.factions.struct.Permission;


public class CmdHelp extends FCommand
{
	
	public CmdHelp()
	{
		super();
		this.aliases.add("help");
		this.aliases.add("h");
		this.aliases.add("?");
		
		//this.requiredArgs.add("");
		this.optionalArgs.put("page", "1");
		
		this.permission = Permission.HELP.node;
		this.disableOnLock = false;
		
		senderMustBePlayer = false;
		senderMustBeMember = false;
		senderMustBeModerator = false;
		senderMustBeAdmin = false;
	}	
	
	@Override
	public void perform()
	{
		if (helpPages == null) updateHelp();
		
		int page = this.argAsInt(0, 1);
		
		msg("§8§m----------------§6 Aide Faction §8§m----------------");
		page -= 1;
		
		if (page < 0 || page >= helpPages.size())
		{
			msg("§4Erreur <b>Cette page n'existe pas");
			msg("§8§m---------------------------------------------");
			return;
		}
		if (page == 8 || page == 9 || page == 10){
			if (sender instanceof Player){
				Player p = (Player)sender;
				if (!p.hasPermission("admin.faction")){					
					msg("§6» §eVous n'avez pas la permission de voir les commandes admins.");msg("§8§m---------------------------------------------");return;
				}
			}
		}
		sendMessage(helpPages.get(page));		
		msg("§6» §ePage suivante §7/f help " + (page + 2));
		msg("§8§m---------------------------------------------");
	}
	
	//----------------------------------------------//
	// Build the help pages
	//----------------------------------------------//
	
	public ArrayList<ArrayList<String>> helpPages;
	
	public void updateHelp()
	{
		helpPages = new ArrayList<ArrayList<String>>();
		ArrayList<String> pageLines;

		pageLines = new ArrayList<String>();
		pageLines.add( "§6» §e/f help <page> §7Voir toutes les commandes" );
		pageLines.add( "§6» §e/f list §7Voir la liste de toutes les factions" );
		pageLines.add( "§6» §e/f f §7Voir les informations de votre factions" );
		pageLines.add( "§6» §e/f p §7Voir votre power" );
		pageLines.add( "§6» §e/f join <faction> §7Rejoindre une faction" );
		pageLines.add( "§6» §e/f leave §7Quitter votre faction" );
		pageLines.add( "§6» §e/f c §7Change le mode de tchat " );
		pageLines.add( "§6» §e/f home §7Aller à l'home de votre faction" );
		helpPages.add(pageLines);
		
		pageLines = new ArrayList<String>();
		pageLines.add( "§6» §e/f create <faction> §7Créer une faction" );
		pageLines.add( "§6» §e/f desc <description> §7Changer de description" );
		pageLines.add( "§6» §e/f tag <nom> §7Changer le nom de la faction" );
		pageLines.add( "§6» §e/f open §7Ouvrir/Fermer la faction" );
		pageLines.add( "§6» §e/f inv <joueur> §7Inviter un joueur" );
		pageLines.add( "§6» §e/f deinv <joueur> §7Retirer l'invitation d'un joueur");
		pageLines.add( "§6» §e/f sethome§7 Mettre le home de faction" );
		helpPages.add(pageLines);
		
		if (Econ.isSetup() && Conf.econEnabled && Conf.bankEnabled)
		{
			pageLines = new ArrayList<String>();
			pageLines.add( "" );
			pageLines.add( p.txt.parse("<i>Your faction has a bank which is used to pay for certain" ));
			pageLines.add( p.txt.parse("<i>things, so it will need to have money deposited into it." ));
			pageLines.add( p.txt.parse("<i>To learn more, use the money command." ));
			pageLines.add( "" );
			pageLines.add( p.cmdBase.cmdMoney.getUseageTemplate(true) );
			pageLines.add( "" );
			pageLines.add( "" );
			pageLines.add( "" );
			helpPages.add(pageLines);
		}
		
		pageLines = new ArrayList<String>();
		pageLines.add( "§6» §e/f claim §7Revendiquer un territoire" );
		pageLines.add( "§6» §e/f autoclaim §7Revendiquer automatiquement un territoire" );
		pageLines.add( "§6» §e/f unclaim §7Retirer un de vos territoire" );
		pageLines.add( "§6» §e/f unclaimall §7Retirer tous vos territoire" );
		pageLines.add( "§6» §e/f kick <joueur> §7Retirer un joueur de votre faction" );
		pageLines.add( "§6» §e/f mod <joueur> §7Mettre un joueur modérateur de faction" );
		pageLines.add( "§6» §e/f admin <joueur> §7Mettre un joueur administrateur de faction" );
		pageLines.add( "§6» §e/f title <joueur> <nom> §7Changer le nom d'un joueur" );
		helpPages.add(pageLines);
		
		pageLines = new ArrayList<String>();
		pageLines.add( "§6» §e/f map §7Voir les territoires sur la map" );
		pageLines.add( "§6» §e/f owner <joueur> §7définir la propriété des terres revendiquées" );
		pageLines.add( "§6» §e/f ownerlist §7liste des propriétaires de ce terrain revendiqué" );
		pageLines.add(p.txt.parse("§6» <i>Terrain revendiqué avec la propriété ensemble est en outre protégé de sorte"));
		pageLines.add(p.txt.parse("§6» <i>que seul le propriétaire (s), admin de faction, et éventuellement le"));
		pageLines.add(p.txt.parse("§6» <i>Les modérateurs de faction ont un accès complet."));
		helpPages.add(pageLines);
		
		pageLines = new ArrayList<String>();
		pageLines.add( "§6» §e/f disband §7Supprimer votre faction" );
		pageLines.add("");
		pageLines.add( "§6» §e/f ally <faction> §7Mettre une faction en aliance, §b3 §7maximum" );
		pageLines.add( "§6» §e/f neutral <faction> §7Mettre une faction en neutre" );
		pageLines.add( "§6» §e/f enemy <faction> §7Mettre une faction en enemy" );
		pageLines.add(p.txt.parse("§6» §eDéfinissez la relation que vous SOUHAITEZ avoir avec une autre faction."));
		pageLines.add(p.txt.parse("§6» <i>Votre relation par défaut avec les autres factions sera neutre."));
		pageLines.add(p.txt.parse("§6» <i>Si les deux factions choisissent \"allié\", vous serez des alliés."));
		pageLines.add(p.txt.parse("§6» <i>Si une faction choisit \"ennemi\", vous serez ennemis."));
		helpPages.add(pageLines);
		
		pageLines = new ArrayList<String>();
		pageLines.add(p.txt.parse("§6» <i>Vous ne pouvez jamais blesser des membres ou des alliés."));
		pageLines.add(p.txt.parse("§6» <i>Vous ne pouvez pas blesser les neutres sur leur propre territoire."));
		pageLines.add(p.txt.parse("§6» <i>ous pouvez toujours blesser les ennemis et les joueurs sans faction."));
		pageLines.add("");
		pageLines.add(p.txt.parse("§6» <i>Les dégâts des ennemis sont réduits dans votre propre territoire."));
		pageLines.add(p.txt.parse("§6» <i>Quand tu meurs, tu perds le pouvoir. Il est restauré au fil du temps."));
		pageLines.add(p.txt.parse("§6» <i>Le power d'une faction est la somme de tous les pouvoirs des membres."));
		pageLines.add(p.txt.parse("§6» <i>Le power d'une faction détermine combien de terres elle peut contenir."));
		pageLines.add(p.txt.parse("§6» <i>Vous pouvez revendiquer des terres auprès de factions avec trop peu de power."));
		helpPages.add(pageLines);
		
		pageLines = new ArrayList<String>();
		pageLines.add(p.txt.parse("§6» <i>Seuls les membres de la faction peuvent construire et détruire dans leur propre"));
		pageLines.add(p.txt.parse("§6» <i>territoire. L'utilisation des éléments suivants est également limitée§7:"));
		pageLines.add(p.txt.parse("§6» <i>Porte, coffre, four, distributeur."));
		pageLines.add("");
		pageLines.add(p.txt.parse("§6» <i>Assurez-vous de mettre des plaques de pression devant les portes pour votre"));
		pageLines.add(p.txt.parse("§6» <i>visiteurs invités. Sinon, ils ne peuvent pas passer à travers. Vous pouvez"));
		pageLines.add(p.txt.parse("§6» <i>utilisez aussi ceci pour créer des zones réservées aux membres."));
		pageLines.add(p.txt.parse("§6» <i>Comme les distributeurs sont protégés, vous pouvez créer des pièges sans"));
		pageLines.add(p.txt.parse("§6» <i>s'inquiéter de ces flèches se faire voler ^^"));
		helpPages.add(pageLines);
		
		pageLines = new ArrayList<String>();
		pageLines.add("§6» Commande uniquement pour les administrateurs§8:");
		pageLines.add( p.cmdBase.cmdBypass.getUseageTemplate(true) );
		pageLines.add(p.txt.parse("§6» <c>/f claim safezone <i>claim land for the Safe Zone"));
		pageLines.add(p.txt.parse("§6» <c>/f claim warzone <i>claim land for the War Zone"));
		pageLines.add(p.txt.parse("§6» <c>/f autoclaim [safezone|warzone] <i>take a guess"));
		pageLines.add( p.cmdBase.cmdSafeunclaimall.getUseageTemplate(true) );
		pageLines.add( p.cmdBase.cmdWarunclaimall.getUseageTemplate(true) );
		pageLines.add(p.txt.parse("§6» <i>Note: " + p.cmdBase.cmdUnclaim.getUseageTemplate(false) + P.p.txt.parse("<i>") + " works on safe/war zones as well."));
		pageLines.add( p.cmdBase.cmdPeaceful.getUseageTemplate(true) );
		helpPages.add(pageLines);
		
		pageLines = new ArrayList<String>();
		pageLines.add(p.txt.parse("§6» <i>Encore des commandes pour les admins§7:"));
		pageLines.add( p.cmdBase.cmdChatSpy.getUseageTemplate(true) );
		pageLines.add( p.cmdBase.cmdPermanent.getUseageTemplate(true) );
		pageLines.add( p.cmdBase.cmdPermanentPower.getUseageTemplate(true) );
		pageLines.add( p.cmdBase.cmdPowerBoost.getUseageTemplate(true) );
		pageLines.add( p.cmdBase.cmdConfig.getUseageTemplate(true) );
		helpPages.add(pageLines);
		
		pageLines = new ArrayList<String>();
		pageLines.add(p.txt.parse("§6» <i>Toujours plus de commandes pour les admins§7:"));
		pageLines.add( p.cmdBase.cmdLock.getUseageTemplate(true) );
		pageLines.add( p.cmdBase.cmdReload.getUseageTemplate(true) );
		pageLines.add( p.cmdBase.cmdSaveAll.getUseageTemplate(true) );
		pageLines.add( p.cmdBase.cmdVersion.getUseageTemplate(true) );
		helpPages.add(pageLines);
	}
}

