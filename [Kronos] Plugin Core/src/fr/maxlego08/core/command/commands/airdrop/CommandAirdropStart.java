package fr.maxlego08.core.command.commands.airdrop;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.maxlego08.core.Kronos;
import fr.maxlego08.core.command.VCommand;
import fr.maxlego08.core.command.VCommand.CommandType;

public class CommandAirdropStart extends VCommand {

	public CommandAirdropStart(VCommand parent, boolean noConsole) {
		super(parent, noConsole);
		this.addCommand("start");
	}

	@Override
	protected CommandType runCommand(Kronos main, CommandSender sender, String... args) {
	
		main.getA().spawn(sender);
		
		return CommandType.SUCCESS;
	}

	@Override
	public String getPermission() {
		return "koth.start";
	}

	@Override
	public String getSyntax() {
		return "/koth start <nom>";
	}

	@Override
	public String getDescription() {
		return "Commencer un koth";
	}

}
