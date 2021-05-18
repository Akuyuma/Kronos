package fr.maxlego08.core.command.commands.koth;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.maxlego08.core.Kronos;
import fr.maxlego08.core.command.VCommand;
import fr.maxlego08.core.command.VCommand.CommandType;

public class CommandKothEditLoot extends VCommand {

	public CommandKothEditLoot(VCommand c) {
		super(c, true);
		this.addCommand("editloot");
	}

	@Override
	protected CommandType runCommand(Kronos main, CommandSender sender, String... args) {
		if (args.length != 2){
			return CommandType.SYNTAX_ERROR;
		}
	
		main.getKothManager().editLoot((Player)sender, args[1]);
		
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
