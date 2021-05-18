package fr.maxlego08.core.command.commands.airdrop;

import org.bukkit.command.CommandSender;

import fr.maxlego08.core.Kronos;
import fr.maxlego08.core.command.VCommand;
import fr.maxlego08.core.command.VCommand.CommandType;
import fr.maxlego08.core.command.commands.airdrop.data.AirDrop;
import fr.maxlego08.core.command.commands.koth.data.KothData;

public class CommandAirdropList extends VCommand {

	public CommandAirdropList(VCommand parent, boolean noConsole) {
		super(parent, noConsole);
		this.addCommand("list");
	}

	@Override
	protected CommandType runCommand(Kronos main, CommandSender sender, String... args) {
		
		if (main.getA().getL().size() > 0){
			StringBuilder b = new StringBuilder();
			for(AirDrop d : main.getA().getL()){
				b.append(d.getName() + "§7, §a");
			}
			sender.sendMessage(main.getPrefix() + " §7Liste des Airdrops§8: §a"+ b.toString());
		}else{
			sender.sendMessage(main.getPrefix() + " §7Liste des Airdrops§8: §aAucun");
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
