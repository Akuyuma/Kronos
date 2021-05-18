package fr.maxlego08.core.spawners.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.maxlego08.core.Kronos;
import fr.maxlego08.core.command.VCommand;
import fr.maxlego08.core.spawners.Spawners;

public class CommandSpawnerSend extends VCommand {

	public CommandSpawnerSend(VCommand parent) {
		super(parent, true);
		this.addCommand("send");
	}

	@Override
	protected CommandType runCommand(Kronos main, CommandSender sender, String... args) {
		if (args.length == 3 && manager.hasSpawner(((Player)sender).getName())){
			Spawners manager = main.getSpawners();
			Player player = Bukkit.getPlayer(args[1]);
			if (player != null){
				try{	
					if (manager.sendSpawner((Player)sender, player, Integer.valueOf(args[2]))){
						
						sender.sendMessage(main.getPrefix() + " §7Vous avez envoyer le spawner !");
						player.sendMessage(main.getPrefix() + " §7Vous venez de reçevoir un spawner de §a"+((Player)sender).getName()+"§7.");
						
					}else{
						sender.sendMessage(main.getPrefix() + " §7Nous n'avons pas pu envoyer le spawner !");
					}
				}catch (NumberFormatException e) {
					return CommandType.SYNTAX_ERROR;
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
		return "spawner.send";
	}

	@Override
	public String getSyntax() {
		return "/avaspawner send <joueur> <id>";
	}

	@Override
	public String getDescription() {
		return "Envoyer un spawner";
	}

}
