package fr.maxlego08.core.command.commands.stotem;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.maxlego08.core.Kronos;
import fr.maxlego08.core.command.VCommand;

public class CommandTotemSet extends VCommand {

	public CommandTotemSet(VCommand parent, boolean noConsole) {
		super(parent, noConsole);
		this.addCommand("set");
	}

	@Override
	protected CommandType runCommand(Kronos main, CommandSender sender, String... args) {
		
		main.getTs().setTotem(((Player)sender).getLocation());
		sender.sendMessage(main.getArrow() + " §eVous venez de mettre la location du totem !");
		
		return CommandType.SUCCESS;
	}

	@Override
	public String getPermission() {
	
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
