package fr.maxlego08.core.command.commands.koth;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.maxlego08.core.Kronos;
import fr.maxlego08.core.command.VCommand;

public class CommandKothEnd extends VCommand {

	public CommandKothEnd(VCommand c) {
		super(c, true);
		this.addCommand("end");
	}

	@Override
	protected CommandType runCommand(Kronos main, CommandSender sender, String... args) {
		if (args.length > 2){
			return CommandType.SYNTAX_ERROR;
		}
	
		main.getKothManager().endKoth((Player)sender, args[1]);
		
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
