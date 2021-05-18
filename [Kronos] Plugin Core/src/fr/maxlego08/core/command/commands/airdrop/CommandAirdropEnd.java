package fr.maxlego08.core.command.commands.airdrop;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.maxlego08.core.Kronos;
import fr.maxlego08.core.command.VCommand;
import fr.maxlego08.core.command.VCommand.CommandType;

public class CommandAirdropEnd extends VCommand {

	public CommandAirdropEnd(VCommand parent, boolean noConsole) {
		super(parent, noConsole);
		this.addCommand("end");
	}

	@Override
	protected CommandType runCommand(Kronos main, CommandSender sender, String... args) {
		
		main.getA().stop(sender);
		
		return CommandType.SUCCESS;
	}

	@Override
	public String getPermission() {
		return "koth.end";
	}

	@Override
	public String getSyntax() {
		return "/koth end <nom>";
	}

	@Override
	public String getDescription() {
		return "Arrêter un koth";
	}

}
