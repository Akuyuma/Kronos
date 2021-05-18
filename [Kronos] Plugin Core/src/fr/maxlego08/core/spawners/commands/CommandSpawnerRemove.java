package fr.maxlego08.core.spawners.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import fr.maxlego08.core.Kronos;
import fr.maxlego08.core.command.VCommand;
import fr.maxlego08.core.spawners.Spawners;

public class CommandSpawnerRemove extends VCommand {

	public CommandSpawnerRemove(VCommand parent) {
		super(parent, false);
		this.addCommand("remove");
	}

	@Override
	protected CommandType runCommand(Kronos main, CommandSender sender, String... args) {
		if (args.length == 3){
			Player player = Bukkit.getPlayer(args[1]);
			
			Spawners manager = main.getSpawners();
			
			if (player != null){
				@SuppressWarnings("deprecation")
				EntityType creature = EntityType.fromName(args[2].toUpperCase());
				if (creature == null){ 
					
					return CommandType.SYNTAX_ERROR;
					
				}else{
					
					if (manager.removeSpawner(player.getName(), creature)){						
						player.sendMessage(main.getPrefix()+ " §7Vous venez de perdre un spawner.");
						sender.sendMessage(main.getPrefix()+ " §7Vous venez de retirer un spawner à §a%spawner% §7à §a%player%§7.".replace("%player%", player.getName()).replace("%spawner%", args[2].toUpperCase()));
					}else{
						sender.sendMessage(main.getPrefix()+ " §7Vous n'avez pas retirer de spawner.");
					}
				}
				
			}else{
				sender.sendMessage(main.getPrefix() + " §7Le joueur n'existe pas.");
			}
			return CommandType.SUCCESS;
		}else{
			return CommandType.SYNTAX_ERROR;
		}
	}

	@Override
	public String getPermission() {
		return "spawner.remove";
	}

	@Override
	public String getSyntax() {
		return "/spawner remove <joueur> <type>";
	}

	@Override
	public String getDescription() {
		return "Retirer un spawner";
	}

}
