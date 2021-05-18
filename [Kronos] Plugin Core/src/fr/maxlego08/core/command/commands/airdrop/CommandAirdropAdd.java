package fr.maxlego08.core.command.commands.airdrop;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.maxlego08.core.Kronos;
import fr.maxlego08.core.command.VCommand;
import fr.maxlego08.core.command.VCommand.CommandType;

public class CommandAirdropAdd extends VCommand {

	public CommandAirdropAdd(VCommand parent, boolean noConsole) {
		super(parent, noConsole);
		this.addCommand("add");
	}

	@Override
	protected CommandType runCommand(Kronos main, CommandSender sender, String... args) {
		if (args.length != 2){
			return CommandType.SYNTAX_ERROR;
		}
	
		if (main.getA().add(((Player)sender).getLocation(), args[1])) sender.sendMessage(main.getPrefix() + " §eVous venez d'ajouter §6" + args[1] + "§e.");
		else sender.sendMessage(Kronos.k().getPrefix() + " Vous n'avez pas ajouté de airdrop");
		return CommandType.SUCCESS;
	}

	@Override
	public String getPermission() {
		return "koth.create";
	}

	@Override
	public String getSyntax() {
		return "/koth create <nom>";
	}

	@Override
	public String getDescription() {
		return "Créer un koth";
	}

}
