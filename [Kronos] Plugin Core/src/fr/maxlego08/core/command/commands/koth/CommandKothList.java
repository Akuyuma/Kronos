package fr.maxlego08.core.command.commands.koth;

import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.Main;

import fr.maxlego08.core.Kronos;
import fr.maxlego08.core.command.VCommand;
import fr.maxlego08.core.command.VCommand.CommandType;
import fr.maxlego08.core.command.commands.koth.data.KothData;

public class CommandKothList extends VCommand {

	public CommandKothList(VCommand parent) {
		super(parent, false);
		this.addCommand("list");
	}

	@Override
	protected CommandType runCommand(Kronos main, CommandSender sender, String... args) {
		
		if (main.getKothManager().getKoths().size() > 0){
			StringBuilder b = new StringBuilder();
			for(KothData d : main.getKothManager().getKoths()){
				b.append(d.getName() + "§7, §a");
			}
			sender.sendMessage(main.getPrefix() + " §7Liste des koth§8: §a"+ b.toString());
		}else{
			sender.sendMessage(main.getPrefix() + " §7Liste des koth§8: §aAucun");
		}
		
		return CommandType.SUCCESS;
	}

	@Override
	public String getPermission() {
		return "koth.list";
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
