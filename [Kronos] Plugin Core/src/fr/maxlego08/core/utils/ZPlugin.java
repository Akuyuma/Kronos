package fr.maxlego08.core.utils;

import java.lang.reflect.Modifier;

import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.libs.com.google.gson.Gson;
import org.bukkit.craftbukkit.libs.com.google.gson.GsonBuilder;
import org.bukkit.plugin.java.JavaPlugin;

import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;

import fr.maxlego08.core.command.commands.airdrop.manager.AirDropManager;
import fr.maxlego08.core.command.commands.koth.manager.KothManager;
import fr.maxlego08.core.command.commands.totem.TotemManager;
import fr.maxlego08.core.config.ConfigManager;
import fr.maxlego08.core.utils.gson.Persist;

public class ZPlugin extends JavaPlugin{

	private long enableTime;
	
	private static ZPlugin zPlugin;
	
	private Gson gson;	
	private Persist persist;
	
	private KothManager k;
	private TotemManager t;
	private fr.maxlego08.core.command.commands.stotem.TotemManager ts;
	private AirDropManager a;
	public WorldGuardPlugin wg;
	private ConfigManager configManager;
	
	private WorldEditPlugin worldEdit = (WorldEditPlugin) Bukkit.getServer().getPluginManager().getPlugin("WorldEdit");
	
	public boolean preEnable(){
		enableTime = System.currentTimeMillis();
		zPlugin = this;
		
		log("Enable start", LogType.INFO);
		
		this.gson = this.getGsonBuilder().create();
		this.persist = new Persist(this);
		
		this.configManager = new ConfigManager();
		this.configManager.createFiles();
		
		this.t = new TotemManager();
		this.ts = new fr.maxlego08.core.command.commands.stotem.TotemManager();
		this.k = new KothManager(this);
		this.a = new AirDropManager(this);
		
		
		this.wg = (WorldGuardPlugin) Bukkit.getServer().getPluginManager().getPlugin("WorldGuard");
		
		return true;
	}
	
	protected ConfigManager c() {
		return configManager;
	}
	
	public AirDropManager getA() {
		return a;
	}
	
	public boolean postEnable(){
		log("Enable done (§6" + Math.abs(enableTime - System.currentTimeMillis())+"§7ms)", LogType.INFO);
		return true;
	}
	
	public void suicide(){
		log(new String[]{"§c------------------------------------------------","§cAn error occurred while loading the plugin !","§c------------------------------------------------"}, LogType.ERROR);
		getServer().getPluginManager().disablePlugin(this);
	}
	
	public void suicide(String message){
		log(new String[]{"§c------------------------------------------------","§c"+ message,"§c------------------------------------------------"}, LogType.ERROR);
		getServer().getPluginManager().disablePlugin(this);
	}
	
	public KothManager getKothManager() {
		return k;
	}
	
	public WorldGuardPlugin getWorldguard() {
		return wg;
	}
	
	public fr.maxlego08.core.command.commands.stotem.TotemManager getTs() {
		return ts;
	}
	
	public GsonBuilder getGsonBuilder()
	{
		return new GsonBuilder()
		.setPrettyPrinting()
		.disableHtmlEscaping()
		.serializeNulls()
		.excludeFieldsWithModifiers(Modifier.TRANSIENT, Modifier.VOLATILE);
	}
	
	public Gson getGson() {
		return gson;
	}
	
	public Persist getPersist() {
		return persist;
	}
	
	public WorldEditPlugin getWorldEdit() {
		return worldEdit;
	}
	
	public static ZPlugin z() {
		return zPlugin;
	}
	
	public TotemManager getT() {
		return t;
	}
	
	/*
	 * Logger systeme
	 * */
	
	public static enum LogType{
		ERROR("§c"),
		INFO("§7"),
		SUCCESS("§2"),
		WARNING("§6");
		
		private final String color;
		
		private LogType(String color) {
			this.color = color;
		}
		
		public String getColor() {
			return color;
		}
	}
	
	public void log(String message, LogType type){
		Bukkit.getConsoleSender().sendMessage("§8[§e"+this.getDescription().getFullName()+"§8] " + type.getColor() + message);
	}
	
	public void log(String[] messages, LogType type){
		for(String message : messages){
			log(message, type);
		}
	}
	
	
}
