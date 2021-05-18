package fr.maxlego08.core.command;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.CommandSender;

import fr.maxlego08.core.Kronos;
import fr.maxlego08.core.spawners.Spawners;


public abstract class VCommand {

	public enum CommandType{ SUCCESS, SYNTAX_ERROR;}
	
	private final VCommand parent;
	
	protected final Spawners manager = Kronos.k().getSpawners();
	
	private final List<String> commands;
	
	private final boolean noConsole;
	
	public VCommand(VCommand parent, boolean noConsole){
		this.commands = new ArrayList<>();
		this.parent = parent;
		this.noConsole = noConsole;
	}
	
	public List<String> getCommands() {
		return commands;
	}
	
	public void addCommand(String command){
		this.commands.add(command);
	}
	
	public boolean isNoConsole() {
		return noConsole;
	}
	
	public VCommand getParent() {
		return parent;
	}
	
	protected abstract CommandType runCommand(Kronos main, CommandSender sender, String... args);
	
	public abstract String getPermission();
	
	public abstract String getSyntax();
	
	public abstract String getDescription();
	
}