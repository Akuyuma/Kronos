package fr.maxlego08.core.command;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.maxlego08.core.Kronos;
import fr.maxlego08.core.command.commands.airdrop.CommandAirdrop;
import fr.maxlego08.core.command.commands.airdrop.CommandAirdropAdd;
import fr.maxlego08.core.command.commands.airdrop.CommandAirdropEditLoot;
import fr.maxlego08.core.command.commands.airdrop.CommandAirdropEnd;
import fr.maxlego08.core.command.commands.airdrop.CommandAirdropList;
import fr.maxlego08.core.command.commands.airdrop.CommandAirdropLoot;
import fr.maxlego08.core.command.commands.airdrop.CommandAirdropRemove;
import fr.maxlego08.core.command.commands.airdrop.CommandAirdropStart;
import fr.maxlego08.core.command.commands.koth.CommandKoth;
import fr.maxlego08.core.command.commands.koth.CommandKothCap;
import fr.maxlego08.core.command.commands.koth.CommandKothCreate;
import fr.maxlego08.core.command.commands.koth.CommandKothEditLoot;
import fr.maxlego08.core.command.commands.koth.CommandKothEnd;
import fr.maxlego08.core.command.commands.koth.CommandKothList;
import fr.maxlego08.core.command.commands.koth.CommandKothLoot;
import fr.maxlego08.core.command.commands.koth.CommandKothRemove;
import fr.maxlego08.core.command.commands.koth.CommandKothStart;
import fr.maxlego08.core.command.commands.totem.CommandTotem;
import fr.maxlego08.core.command.commands.totem.CommandTotemNow;
import fr.maxlego08.core.command.commands.totem.CommandTotemSet;
import fr.maxlego08.core.command.commands.totem.CommandTotemStop;
import fr.maxlego08.core.spawners.commands.CommandSpawner;
import fr.maxlego08.core.spawners.commands.CommandSpawnerAdd;
import fr.maxlego08.core.spawners.commands.CommandSpawnerRemove;
import fr.maxlego08.core.spawners.commands.CommandSpawnerSend;

public class CommandManager implements CommandExecutor {

	private Kronos main;
	private List<VCommand> commands = new ArrayList<>();
	
	public CommandManager(Kronos main) {
		this.main = main;
		
		/*
		 * Ajout des commandes
		 * */

		
		main.getCommand("spawners").setExecutor(this);
		main.getCommand("totem").setExecutor(this);
		main.getCommand("koth").setExecutor(this);
		main.getCommand("airdrop").setExecutor(this);
		main.getCommand("stotem").setExecutor(this);
		
		/*
		 * Ajout des Class
		 * */
		
		VCommand command = addCommand(new CommandSpawner(null, true));
		addCommand(new CommandSpawnerAdd(command));
		addCommand(new CommandSpawnerRemove(command));
		addCommand(new CommandSpawnerSend(command));
		
		VCommand totem = addCommand(new CommandTotem(null, false));
		addCommand(new CommandTotemSet(totem, true));
		addCommand(new CommandTotemNow(totem, false));
		addCommand(new CommandTotemStop(totem, false));
		
		VCommand stotem = addCommand(new fr.maxlego08.core.command.commands.stotem.CommandTotem(null, false));
		addCommand(new fr.maxlego08.core.command.commands.stotem.CommandTotemSet(stotem, true));
		addCommand(new fr.maxlego08.core.command.commands.stotem.CommandTotemNow(stotem, false));
		addCommand(new fr.maxlego08.core.command.commands.stotem.CommandTotemStop(stotem, false));
		
		VCommand koth = addCommand(new CommandKoth());
		addCommand(new CommandKothCreate(koth));
		addCommand(new CommandKothList(koth));
		addCommand(new CommandKothRemove(koth));
		addCommand(new CommandKothCap(koth));
		addCommand(new CommandKothStart(koth));
		addCommand(new CommandKothEnd(koth));
		addCommand(new CommandKothLoot(koth));
		addCommand(new CommandKothEditLoot(koth));
		
		VCommand Airdrop = addCommand(new CommandAirdrop(null, false));
		addCommand(new CommandAirdropAdd(Airdrop, true));
		addCommand(new CommandAirdropList(Airdrop, false));
		addCommand(new CommandAirdropRemove(Airdrop, false));
		addCommand(new CommandAirdropStart(Airdrop, false));
		addCommand(new CommandAirdropEnd(Airdrop, false));
		addCommand(new CommandAirdropLoot(Airdrop, true));
		addCommand(new CommandAirdropEditLoot(Airdrop, true));
		
	}
	
	private VCommand addCommand(VCommand command){
		commands.add(command);
		return command;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String s, String[] strings){
		
		for(VCommand vcommand : commands){
			if (vcommand.getCommands().contains(command.getName().toLowerCase())){
				if (strings.length == 0 && vcommand.getParent() == null){
					processRequirements(vcommand, sender, strings);
					return true;
				}
			}else if (strings.length != 0 && vcommand.getParent() != null && vcommand.getParent().getCommands().contains(command.getName().toLowerCase())){
				String cmd = strings[0].toLowerCase();
				if (vcommand.getCommands().contains(cmd)){
					processRequirements(vcommand, sender, strings);
					return true;
				}
			}
		}
        sender.sendMessage(main.getPrefix()+ " §cCette argument n'existe pas !");	
		return true;
	}
	
	 private void processRequirements(VCommand command, CommandSender sender, String[] strings) {
	        if (!(sender instanceof Player) && command.isNoConsole()) {
	            sender.sendMessage(main.getPrefix() + " §CVous devez être un joueur pour executer cette commande.");
	            return;
	        }
	        if (command.getPermission() == null || sender.hasPermission(command.getPermission())) {
	        	VCommand.CommandType returnType = command.runCommand(main, sender, strings);
	             if (returnType == VCommand.CommandType.SYNTAX_ERROR) {
	                 sender.sendMessage(main.getPrefix() + " §7Vous devez utilisez la commande comme ceci§8: §a" + command.getSyntax());
	             }
	            return;
	        }
	        sender.sendMessage(main.getPrefix() +  " §cVous n'avez pas la permission de faire ceci !");	
	    }
	
	public List<VCommand> getCommands() {
		return commands;
	}
	
	public int getCommand(){
		int how = 0;
		for(VCommand c : commands){
			if (c.getParent() == null){
			how++;}
		}	
		return how;
	}
	
	
}
