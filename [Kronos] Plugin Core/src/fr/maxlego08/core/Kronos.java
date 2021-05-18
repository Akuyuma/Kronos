package fr.maxlego08.core;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;

import fr.maxlego08.core.command.CommandManager;
import fr.maxlego08.core.command.commands.airdrop.listener.AirDropListener;
import fr.maxlego08.core.command.commands.koth.listener.KothListener;
import fr.maxlego08.core.command.commands.totem.TotemManager;
import fr.maxlego08.core.command.commands.totem.listener.TotemListener;
import fr.maxlego08.core.config.ConfigManager;
import fr.maxlego08.core.listener.AngeliteBowListener;
import fr.maxlego08.core.listener.AntiBackApListener;
import fr.maxlego08.core.listener.BaguetteXrayListener;
import fr.maxlego08.core.listener.BanSwordListener;
import fr.maxlego08.core.listener.CraftItemListener;
import fr.maxlego08.core.listener.FarmToolsListener;
import fr.maxlego08.core.listener.GrapesListener;
import fr.maxlego08.core.listener.HeartOfCassandre;
import fr.maxlego08.core.listener.KuneesOfHadesListener;
import fr.maxlego08.core.listener.NetherPortal;
import fr.maxlego08.core.listener.NoInsulteListener;
import fr.maxlego08.core.listener.ObsidianListener;
import fr.maxlego08.core.listener.PowerOfHades;
import fr.maxlego08.core.listener.PowerOfHermes;
import fr.maxlego08.core.spawners.Spawners;
import fr.maxlego08.core.spawners.listener.SpawnerListener;
import fr.maxlego08.core.utils.ZPlugin;
import fr.maxlego08.core.worldguardEvent.WorldGuardListener;
import net.minecraft.util.org.apache.commons.io.FileExistsException;

public class Kronos extends ZPlugin{

	private String prefix = "§8[§eKronos§8]";
	
	private String arrow = "§6»";
	
	private CommandManager commandManager;
	
	private Spawners spawners;
	
	private static Kronos k;
	
	private ObsidianListener ol;
	
	private boolean isEnable = false;
	
	private List<String> insultes = new ArrayList<>();
	
	@Override
	public void onEnable() {
		
		if (!preEnable()) suicide();
		
		saveDefaultConfig();
		
		insultes = getConfig().getStringList("insultes");
		
		k = this;
		
		this.commandManager = new CommandManager(this);
		
		this.spawners = new Spawners();
		this.spawners.load();
		
		getA().setMain(this);
		
		loadListener();
		
		try {
			
			ol.setObsdians(c().loadObisdian());
			c().loadTotem();
			c().loadSTotem();
			getKothManager().load();
		} catch (FileExistsException e) { suicide(); e.printStackTrace(); }
		
		postEnable();
		
		isEnable = true;
	}
	
	public List<String> getInsultes() {
		return insultes;
	}
	
	public static Kronos k() {
		return k;
	}
	
	public Spawners getSpawners() {
		return spawners;
	}
	
	@Override
	public void onDisable() {
		if (isEnable){
			spawners.save();
			getKothManager().disable();
			getA().disable();
			try {
				c().saveObsidian(ol.getObsdians());
				c().saveTotem(getT().getTotem());;
				c().saveSTotem(getTs().getTotem());;
			} catch (FileExistsException e) { e.printStackTrace();}
		}
		
	}
	
	private void loadListener(){
		
		ol = new ObsidianListener(this);
		
		PluginManager pm = Bukkit.getPluginManager();
		pm.registerEvents(new WorldGuardListener(this), this, getColoredPrefix());
		pm.registerEvents(new AntiBackApListener(this), this, getColoredPrefix());
		pm.registerEvents(new GrapesListener(), this, getColoredPrefix());
		pm.registerEvents(new FarmToolsListener(), this, getColoredPrefix());
		pm.registerEvents(new HeartOfCassandre(this), this, getColoredPrefix());
		pm.registerEvents(new SpawnerListener(this, spawners), this, getColoredPrefix());
		pm.registerEvents(new PowerOfHades(), this, getColoredPrefix());
		pm.registerEvents(new PowerOfHermes(), this, getColoredPrefix());
		pm.registerEvents(new TotemListener(getT()), this, getColoredPrefix());
		pm.registerEvents(new fr.maxlego08.core.command.commands.stotem.listener.TotemListener(getTs()), this, getColoredPrefix());
		pm.registerEvents(new KothListener(this), this, getColoredPrefix());
		pm.registerEvents(new AirDropListener(this), this, getColoredPrefix());
		pm.registerEvents(new NetherPortal(), this, getColoredPrefix());
		pm.registerEvents(new AngeliteBowListener(), this, getColoredPrefix());
		pm.registerEvents(new NoInsulteListener(), this, getColoredPrefix());
		pm.registerEvents(new KuneesOfHadesListener(), this, getColoredPrefix());
		pm.registerEvents(new BaguetteXrayListener(), this, getColoredPrefix());
		pm.registerEvents(new BanSwordListener(), this, getColoredPrefix());
		pm.registerEvents(ol, this, getColoredPrefix());
		
	}
	
    public boolean canBuild(Player p, Location loc){
    	return getWorldguard().canBuild(p, loc);	
    }
	
    public String getColoredPrefix(){
    	return "§8[§e"+this.getDescription().getFullName()+"§8]";
    }
    
	public String getPrefix() {
		return prefix;
	}
	
	public CommandManager getCommandManager() {
		return commandManager;
	}
	
	public String getArrow() {
		return arrow;
	}
	
	
}
