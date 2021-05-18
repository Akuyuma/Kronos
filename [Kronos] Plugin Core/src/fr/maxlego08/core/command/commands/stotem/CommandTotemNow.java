package fr.maxlego08.core.command.commands.stotem;

import org.bukkit.command.CommandSender;

import fr.maxlego08.core.Kronos;
import fr.maxlego08.core.command.VCommand;

public class CommandTotemNow extends VCommand {

	public CommandTotemNow(VCommand parent, boolean noConsole) {
		super(parent, noConsole);
		this.addCommand("now");
	}

	@Override
	protected CommandType runCommand(Kronos main, CommandSender sender, String... args) {
		
		main.getTs().spawn(sender);
		
		return CommandType.SUCCESS;
	}

	@Override
	public String getPermission() {
		// TODO Auto-generated method stub
		return "admin.totem";
	}

	@Override
	public String getSyntax() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return null;
	}

}
