package fr.maxlego08.core.command.commands.airdrop;

import org.bukkit.command.CommandSender;

import fr.maxlego08.core.Kronos;
import fr.maxlego08.core.command.VCommand;
import fr.maxlego08.core.command.VCommand.CommandType;

public class CommandAirdrop extends VCommand {

	public CommandAirdrop(VCommand parent, boolean noConsole) {
		super(parent, noConsole);
		this.addCommand("airdrop");
	}

	@Override
	protected CommandType runCommand(Kronos main, CommandSender sender, String... args) {
		
		sender.sendMessage("§6» §a/airdrop add <nom> §7Créer un airdrop");
		sender.sendMessage("§6» §a/airdrop delete <nom> §7Supprimer un airdrop");
		sender.sendMessage("§6» §a/airdrop start §7Lancer un airdrop");
		sender.sendMessage("§6» §a/airdrop stop §7Arrêter un airdrop");
		sender.sendMessage("§6» §a/airdrop editloot §7Edider les loots");
		sender.sendMessage("§6» §a/airdrop loot §7Voir les loots");
		sender.sendMessage("§6» §a/airdrop list §7Voir les airdrop");
		
		return CommandType.SUCCESS;
	}

	@Override
	public String getPermission() {
		return "airdrop.syntaxe";
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
