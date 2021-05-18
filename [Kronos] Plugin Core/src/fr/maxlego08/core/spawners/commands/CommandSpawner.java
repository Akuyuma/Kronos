package fr.maxlego08.core.spawners.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.maxlego08.core.Kronos;
import fr.maxlego08.core.command.VCommand;
import fr.maxlego08.core.spawners.Spawners;

public class CommandSpawner extends VCommand {

	public CommandSpawner(VCommand parent, boolean noConsole) {
		super(parent, noConsole);
		this.addCommand("spawners");
	}

	@Override
	protected CommandType runCommand(Kronos main, CommandSender sender, String... args) {
		Player player = (Player)sender;
		
		Spawners manager = main.getSpawners();
		
		if (manager.hasSpawner(player.getName())){
			
			manager.openSpawnerPlayer(player, 1);
			
		}else{
			sender.sendMessage(main.getPrefix() + "Vous n'avez pas de spawner !");
		}
		return CommandType.SUCCESS;
	}

	@Override
	public String getPermission() {
		return "spawners.kronos";
	}

	@Override
	public String getSyntax() {
		return "/spawners";
	}

	@Override
	public String getDescription() {
		return "ouvrir le menu des spawners";
	}

}
