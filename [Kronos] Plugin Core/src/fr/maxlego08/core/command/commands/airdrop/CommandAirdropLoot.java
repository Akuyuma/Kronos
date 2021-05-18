package fr.maxlego08.core.command.commands.airdrop;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.maxlego08.core.Kronos;
import fr.maxlego08.core.command.VCommand;
import fr.maxlego08.core.command.VCommand.CommandType;

public class CommandAirdropLoot extends VCommand {

	public CommandAirdropLoot(VCommand parent, boolean noConsole) {
		super(parent, noConsole);
		this.addCommand("loot");
	}

	@Override
	protected CommandType runCommand(Kronos main, CommandSender sender, String... args) {
	
		main.getA().viewLoot((Player)sender);
		
		return CommandType.SUCCESS;
	}

	@Override
	public String getPermission() {
		return "koth.loot";
	}

	@Override
	public String getSyntax() {
		return "/koth loot <nom>";
	}

	@Override
	public String getDescription() {
		return "Voir le loot d'un koth";
	}

}
