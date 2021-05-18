package fr.maxlego08.core.command.commands.koth;

import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.Main;
import org.bukkit.entity.Player;

import fr.maxlego08.core.Kronos;
import fr.maxlego08.core.command.VCommand;
import fr.maxlego08.core.command.VCommand.CommandType;

public class CommandKothCap extends VCommand {

	public CommandKothCap(VCommand c) {
		super(c, true);
		this.addCommand("setcapdelay");
	}

	@Override
	protected CommandType runCommand(Kronos main, CommandSender sender, String... args) {
		if (args.length > 3 || args.length == 1 || args.length == 2){
			return CommandType.SYNTAX_ERROR;
		}
		try{
			main.getKothManager().changeKothTime((Player)sender, args[1], Integer.valueOf(args[2]));
		}catch (NumberFormatException e) {
			return CommandType.SYNTAX_ERROR;
		}
		
		return CommandType.SUCCESS;
	}

	@Override
	public String getPermission() {
		return "koth.changecap";
	}

	@Override
	public String getSyntax() {
		return "/koth setcapdelay <nom> <temps>";
	}

	@Override
	public String getDescription() {
		return "Changer le temps de cap d'un koth";
	}

}
