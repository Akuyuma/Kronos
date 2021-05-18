package fr.maxlego08.core.command.commands.airdrop;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.maxlego08.core.Kronos;
import fr.maxlego08.core.command.VCommand;
import fr.maxlego08.core.command.VCommand.CommandType;

public class CommandAirdropRemove extends VCommand {

	public CommandAirdropRemove(VCommand parent, boolean noConsole) {
		super(parent, noConsole);
		this.addCommand("remove");
	}

	@Override
	protected CommandType runCommand(Kronos main, CommandSender sender, String... args) {
		if (args.length > 2){
			return CommandType.SYNTAX_ERROR;
		}
	
		main.getA().remove(args[1]);
		sender.sendMessage(main.getPrefix() + " Vous venez de retiré un airdrop !");
		
		return CommandType.SUCCESS;
	}

	@Override
	public String getPermission() {
		return "koth.delete";
	}

	@Override
	public String getSyntax() {
		return "/airdrop remove <nom>";
	}

	@Override
	public String getDescription() {
		return "Supprimer un koth";
	}

}
