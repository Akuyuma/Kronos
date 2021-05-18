package fr.maxlego08.core.command.commands.totem;

import org.bukkit.command.CommandSender;

import fr.maxlego08.core.Kronos;
import fr.maxlego08.core.command.VCommand;

public class CommandTotem extends VCommand {

	public CommandTotem(VCommand parent, boolean noConsole) {
		super(parent, noConsole);
		this.addCommand("totem");
	}

	@Override
	protected CommandType runCommand(Kronos main, CommandSender sender, String... args) {
		sender.sendMessage(main.getArrow() + " §a/totem set");
		sender.sendMessage(main.getArrow() + " §a/totem now");
		sender.sendMessage(main.getArrow() + " §a/totem stop");
		return CommandType.SUCCESS;
	}

	@Override
	public String getPermission() {
		return "admin.totem";
	}

	@Override
	public String getSyntax() {
		return null;
	}

	@Override
	public String getDescription() {
		return null;
	}

}
