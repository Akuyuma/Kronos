package fr.maxlego08.core.command.commands.koth;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.maxlego08.core.Kronos;
import fr.maxlego08.core.command.VCommand;
import fr.maxlego08.core.command.VCommand.CommandType;

public class CommandKothRemove extends VCommand {

	public CommandKothRemove(VCommand c) {
		super(c, true);
		this.addCommand("remove");
	}

	@Override
	protected CommandType runCommand(Kronos main, CommandSender sender, String... args) {
		if (args.length > 2){
			return CommandType.SYNTAX_ERROR;
		}
	
		main.getKothManager().deleteKoth((Player)sender, args[1]);
		
		return CommandType.SUCCESS;
	}

	@Override
	public String getPermission() {
		return "koth.delete";
	}

	@Override
	public String getSyntax() {
		return "/koth remove <nom>";
	}

	@Override
	public String getDescription() {
		return "Supprimer un koth";
	}

}
