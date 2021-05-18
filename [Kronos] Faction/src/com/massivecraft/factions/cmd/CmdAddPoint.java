package com.massivecraft.factions.cmd;

import com.massivecraft.factions.Faction;
import com.massivecraft.factions.Factions;
import com.massivecraft.factions.P;

public class CmdAddPoint extends FCommand{

	public CmdAddPoint() {
		this.aliases.add("addpoint");
		this.aliases.add("addp");
		
		this.requiredArgs.add("nom de faction");
		this.requiredArgs.add("points");
		
		senderMustBePlayer = true;
		senderMustBeMember = false;
		senderMustBeModerator = false;
		senderMustBeAdmin = false;		
	}

	@Override
	public void perform() {
		String tag = this.argAsString(0);
		String point = this.argAsString(1);
		if (sender.hasPermission("admin.vultaria")){
			
			if (args.size() == 3){
				try{
					int points = Integer.parseInt(point);
					Faction f = Factions.i.get(tag);
					f.setClassementPoint(points);
					msg(P.prefix + "§7Vous venez d'ajouter §a"+points+" §7points à la faction §a" + f.getTag()+"§7.");
				}catch (NumberFormatException e) {
					msg(P.prefix + "§7Vous devez faire §a/f addpoint <faction> <chiffre> §7Pour ajouter des points");
				}
			}else{
				msg(P.prefix + "§7Vous devez faire §a/f addpoint <faction> <chiffre> §7Pour ajouter des points");
			}
			
		}else {
			sender.sendMessage(P.prefix + "§cVous n'avez pas la permission de faire cette commande.");
		}
		
	}
	
	
	
}
