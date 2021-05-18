package fr.maxlego08.core.spawners.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import fr.maxlego08.core.Kronos;
import fr.maxlego08.core.command.VCommand;
import fr.maxlego08.core.spawners.Spawners;

public class CommandSpawnerAdd extends VCommand {

	public CommandSpawnerAdd(VCommand parent) {
		super(parent, false);
		this.addCommand("add");
	}

	@Override
	protected CommandType runCommand(Kronos main, CommandSender sender, String... args) {
		
		Spawners manager = main.getSpawners();
		
		if (args.length == 3){
			Player player = Bukkit.getPlayer(args[1]);
			if (player != null){
				@SuppressWarnings("deprecation")
				EntityType creature = EntityType.fromName(args[2].toUpperCase());
				if (creature == null){ 
					
					return CommandType.SYNTAX_ERROR;
					
				}else{
					
					manager.addSpawner(player.getName(), creature, null, false);			
					sender.sendMessage(main.getPrefix()+ " §7Vous venez de donner un spawner à §a%spawner% §7à §a%player%§7.".replace("%player%", player.getName()).replace("%spawner%", args[2].toUpperCase()));
					player.sendMessage(main.getPrefix()+ " §7Vous venez de reçevoir un spawner.");
				}
				
			}else{
				sender.sendMessage(main.getPrefix() + " §7Le joueur n'existe pas.");
			}
			return CommandType.SUCCESS;
		}else if (args.length == 4){	
			try{
				int how = Integer.parseInt(args[3]);
				Player player = Bukkit.getPlayer(args[1]);
				if (player != null){
					@SuppressWarnings("deprecation")
					EntityType creature = EntityType.fromName(args[2].toUpperCase());
					
					if (creature == null){ 
						
						return CommandType.SYNTAX_ERROR;
						
					}else{
						for(int a =0; a != how; a++){
							manager.addSpawner(player.getName(), creature, null, false);
						}
						sender.sendMessage(main.getPrefix()+ " §7Vous venez de donner x§a"+how+"§7 spawners à §a%spawner% §7à §a%player%§7.".replace("%player%", player.getName()).replace("%spawner%", args[2].toUpperCase()));
						player.sendMessage(main.getPrefix()+ " §7Vous venez de reçevoir x§6"+how+"§7 spawners.");
					}
					
				}else{
					sender.sendMessage(main.getPrefix() + " §7Le joueur n'existe pas.");
				}
				return CommandType.SUCCESS;
			}catch (NumberFormatException e) {
				return CommandType.SYNTAX_ERROR;
			}
		}else{
			return CommandType.SYNTAX_ERROR;
		}
	}

	@Override
	public String getPermission() {
		return "spawners.kronos.add";
    }

    @Override
    public String getSyntax() {
        return "/spawners add <player> <spawner> [<nombre>]";
    }

    @Override
    public String getDescription() {
        return "Donner des spawners.";
    }

}
