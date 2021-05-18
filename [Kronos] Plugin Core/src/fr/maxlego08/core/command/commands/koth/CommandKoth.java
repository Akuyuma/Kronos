package fr.maxlego08.core.command.commands.koth;

import org.bukkit.command.CommandSender;

import fr.maxlego08.core.Kronos;
import fr.maxlego08.core.command.VCommand;

public class CommandKoth extends VCommand {

	public CommandKoth() {
		super(null, false);
		this.addCommand("koth");
	}

	@Override
	protected CommandType runCommand(Kronos main, CommandSender sender, String... args) {
		
		sender.sendMessage("§6» §a/koth create <nom> §7Créer un Koth");
		sender.sendMessage("§6» §a/koth delete <nom> §7Supprimer un Koth");
		sender.sendMessage("§6» §a/koth start <nom> §7Lancer un Koth");
		sender.sendMessage("§6» §a/koth stop <nom> §7Arrêter un Koth");
		sender.sendMessage("§6» §a/koth setcapdelay <nom> <temps> §7Mettre le temps d'un koht");
		sender.sendMessage("§6» §a/koth editloot §7Edider les loots");
		sender.sendMessage("§6» §a/koth loot §7Voir les loots");
		sender.sendMessage("§6» §a/koth list §7Voir les koth");
		
		return CommandType.SUCCESS;
	}

	@Override
	public String getPermission() {
		return "koth.syntaxe";
	}

	@Override
	public String getSyntax() {
		return null;
	}

	@Override
	public String getDescription() {
		return null;
	}

}
