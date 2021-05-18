package fr.maxlego08.core.command.commands.airdrop;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.maxlego08.core.Kronos;
import fr.maxlego08.core.command.VCommand;
import fr.maxlego08.core.command.VCommand.CommandType;

public class CommandAirdropEditLoot extends VCommand {

	public CommandAirdropEditLoot(VCommand parent, boolean noConsole) {
		super(parent, noConsole);
		this.addCommand("editloot");
	}

	@Override
	protected CommandType runCommand(Kronos main, CommandSender sender, String... args) {
	
		main.getA().editLoot((Player)sender);
		
		return CommandType.SUCCESS;
	}

	@Override
	public String getPermission() {
		return "koth.editloot";
	}

	@Override
	public String getSyntax() {
		return "/koth editloot <nom>";
	}

	@Override
	public String getDescription() {
		return "Changer le loot d'un koth";
	}

}
