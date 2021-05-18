package fr.maxlego08.core.command.commands.airdrop.manager;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import fr.maxlego08.core.Kronos;
import fr.maxlego08.core.command.commands.airdrop.data.AirDrop;
import fr.maxlego08.core.command.commands.koth.data.KothData;
import fr.maxlego08.core.utils.ZPlugin;

public class AirDropManager {

	private Kronos main;
	
	private List<AirDrop> l = new ArrayList<>();
	private List<ItemStack> loots = new ArrayList<>();
	
	private int how;
	
	private YamlConfiguration kothYml;
	private File kothFile;
	
	
	public String changeLocationToString(Location location){String ret = location.getWorld().getName() + "," + location.getBlockX() + "," + location.getBlockY() + "," + location.getBlockZ();return ret;}	
	public Location changeStringToLocation(String s){String[] a = s.split(",");World w = Bukkit.getServer().getWorld(a[0]);float x = Float.parseFloat(a[1]); float y = Float.parseFloat(a[2]);float z = Float.parseFloat(a[3]); return new Location(w, x, y, z);}		
	
	
	public AirDropManager(ZPlugin main) {
		
		kothFile = new File(main.getDataFolder()+File.separator+"airdrop.yml");
		if (!kothFile.exists()){try {
			kothFile.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}}
		kothYml = YamlConfiguration.loadConfiguration(kothFile);
		if (kothYml.contains("airdrop")){
			if (kothYml.contains("loots")){
				List<?> list = kothYml.getList("loots");
				List<ItemStack> itL = new ArrayList<>();
				for(int a = 0; a < list.size(); a++){
					itL.add((ItemStack)list.get(a));
				}
				setLoots(itL);
			}
			for(String name : kothYml.getConfigurationSection("airdrop.").getKeys(false)){
				
				l.add(new AirDrop(changeStringToLocation(kothYml.getString("airdrop."+name)), name));
			}
		}		
		
	}
	
	public void setMain(Kronos main) {
		this.main = main;
	}
	
	public void disable(){
		kothYml.set("loots", loots);
		for(AirDrop drop : l){
			kothYml.set("airdrop."+drop.getName(), changeLocationToString(drop.getLocation()));
		}
		save();
		
	}
	
	public void save(){
		try {
			kothYml.save(kothFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private boolean z = false;
	
	public List<AirDrop> getL() {
		return l;
	}
	
	public boolean isChest(Location location){
		for(AirDrop d : l) if (d.getLocation().getBlockX() == location.getBlockX()&&
				d.getLocation().getBlockZ() == location.getBlockZ()&&d
				.getLocation().getBlockY() == location.getBlockY()) return true;
		return false;
	}
	
	
	public void z(Location location, Player p){
		location.getBlock().setType(Material.AIR);
		
		Random r = new Random();
		for(int a = 0; a != 4; a++){
			location.getWorld().dropItem(location, getLoots().get(r.nextInt(getLoots().size()-1)));
		}
		how--;
		if (how != 0){
			Bukkit.broadcastMessage(main.getPrefix() + " §eLe joueur §6" + p.getName()+" §evient de trouver un coffre ! Il en rest encore §6" + how+"§e.");
		}else{
			Bukkit.broadcastMessage(main.getPrefix() + " §eLe joueur §6" + p.getName()+" §evient de trouver un coffre ! L'event est terminé !");
		}
		
	}
	
	public boolean existe(String name){
		for (AirDrop d : l) if (d.getName().equals(name)) return true;
		return false;
	}
	
	public boolean add(Location location, String name){
		if (!existe(name)){ l.add(new AirDrop(location, name));return true;}
		return false;
	}
	
	public AirDrop get(String name){
		for (AirDrop d : l) if (d.getName().equals(name)) return d;
		return null;
	}
	
	public boolean remove(String name){
		if (existe(name)){l.remove(get(name));return true;}
		return false;
	}
	
	public void spawn(CommandSender sender){
		if (!z){
			z = !z;
			for(AirDrop a : l){
				a.getLocation().getBlock().setType(Material.CHEST);
			}
			how = l.size();
			Bukkit.broadcastMessage(main.getPrefix() +" §eL'event AirDrop vient de comment ! Il y a §6" + l.size() +" §ecoffres dans la warzone !");
		}else{
			sender.sendMessage(main.getPrefix() + " §eUn airdrop est déjà en cours");
		}
	}
	
	public void stop(CommandSender sender){
		if (z){
			z = !z;
			for(AirDrop a : l){
				a.getLocation().getBlock().setType(Material.AIR);
			}
			Bukkit.broadcastMessage(main.getPrefix() +" §eL'event AirDrop de se terminer !");
		}else{
			sender.sendMessage(main.getPrefix() + " §eAucun event en cour");
		}
	}
	
	public void viewLoot(Player player){
		Inventory inv = Bukkit.createInventory(null, 54, "§eAirdrop loot");
		int slot = 0;
		for(ItemStack it : getLoots()){
			inv.setItem(slot, it);slot++;
		}
				
		player.openInventory(inv);
	}
	
	public List<ItemStack> getLoots() {
		return loots;
	}
	
	public void editLoot(Player player){
		Inventory inv = Bukkit.createInventory(null, 54, "§6Loots");
		int slot = 0;
		for(ItemStack it : getLoots()){
			inv.setItem(slot, it);slot++;
		}
		
		player.openInventory(inv);
	}
	
	public void setLoots(List<ItemStack> loots) {
		this.loots = loots;
	}
	
}
