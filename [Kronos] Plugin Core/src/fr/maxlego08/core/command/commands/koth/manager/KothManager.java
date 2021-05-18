package fr.maxlego08.core.command.commands.koth.manager;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.sk89q.worldedit.bukkit.selections.Selection;

import fr.maxlego08.core.Kronos;
import fr.maxlego08.core.command.commands.koth.data.KothData;
import fr.maxlego08.core.utils.ZPlugin;

public class KothManager {

	private List<KothData> koths = new ArrayList<>();
	
	private ZPlugin main;	
	
	private YamlConfiguration kothYml;
	private File kothFile;
	
	public KothManager(ZPlugin main) {
		this.main = main;
	}
	
	public void load(){
		kothFile = new File(main.getDataFolder()+File.separator+"koth.yml");
		if (!kothFile.exists()){try {
			kothFile.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}}
		kothYml = YamlConfiguration.loadConfiguration(kothFile);
		if (kothYml.contains("koth")){
			for(String name : kothYml.getConfigurationSection("koth.").getKeys(false)){
				
				List<?> list = kothYml.getList("koth."+name+".loots");
	    		List<ItemStack> itL = new ArrayList<>();
	    		for(int a = 0; a < list.size(); a++){
	    			itL.add((ItemStack)list.get(a));
	    		}
				koths.add(new KothData(changeStringToLocation(kothYml.getString("koth."+name+".pos1")),
						changeStringToLocation(kothYml.getString("koth."+name+".pos2")), name, kothYml.getInt("koth."+name+".timecap"), itL));
			}
		}
	}
	
	public void disable(){
		if (kothYml.contains("koth")){
			for(String name : kothYml.getConfigurationSection("koth.").getKeys(false)){
				kothYml.set("koth."+name+".pos1", null);
				kothYml.set("koth."+name+".pos2", null);
				kothYml.set("koth."+name+".timecap", null);
				kothYml.set("koth."+name+".loots", null);				
				kothYml.set("koth."+name, null);				
			}
		}
		for(KothData data : koths){
			kothYml.set("koth."+data.getName()+".pos1", changeLocationToString(data.getPos1()));
			kothYml.set("koth."+data.getName()+".pos2", changeLocationToString(data.getPos2()));
			kothYml.set("koth."+data.getName()+".timecap", data.getTimeCap());
			kothYml.set("koth."+data.getName()+".loots", data.getLoots());
			
		}
		saveKoth();
	}
	
	public void saveKoth(){
		try {
			kothYml.save(kothFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public boolean hasEnableKoth(){
		for(KothData data : koths){
			if (data.isActive()){return true;}
		}
		return false;
	}
	
	public KothData getEnableKoth(){
		for(KothData data : koths){
			if (data.isActive()){return data;}
		}
		return null;			
	}
	
	public boolean existe(String name){
		for(KothData data : koths){
			if (data.getName().equals(name)){
				return true;
			}
		}
		return false;		
	}
	
	public KothData getKoth(String name){
		for(KothData data : koths){
			if (data.getName().equals(name)){
				return data;
			}
		}
		return null;
	}
	
	public List<KothData> getKoths() {
		return koths;
	}
	
	public String changeLocationToString(Location location){String ret = location.getWorld().getName() + "," + location.getBlockX() + "," + location.getBlockY() + "," + location.getBlockZ();return ret;}	
	public Location changeStringToLocation(String s){String[] a = s.split(",");World w = Bukkit.getServer().getWorld(a[0]);float x = Float.parseFloat(a[1]); float y = Float.parseFloat(a[2]);float z = Float.parseFloat(a[3]); return new Location(w, x, y, z);}		
	
	
	public void createKoth(Player player, String name){
		if (!existe(name)){
			Selection select = main.getWorldEdit().getSelection(player);
			if (select == null) {
				player.sendMessage(Kronos.k().getPrefix() +" §7Veuillez selectionnez les deux positions avec WorldEdit.");
				return;
			}		
			List<ItemStack> it = new ArrayList<>();
			it.add(new ItemStack(Material.DIAMOND, 3));
			it.add(new ItemStack(Material.DIAMOND_SPADE, 1));
			it.add(new ItemStack(Material.DIAMOND_SWORD, 1));
			it.add(new ItemStack(Material.DIAMOND_PICKAXE, 1));
			it.add(new ItemStack(Material.DIAMOND_AXE, 1));
			koths.add(new KothData(select.getMaximumPoint(), select.getMinimumPoint(), name, 300, it));
			player.sendMessage(Kronos.k().getPrefix() + " §7Vous venez de créer le Koth §a" + name + "§7.");
			
		}else{
			player.sendMessage(Kronos.k().getPrefix() + " §7Se koth existe déjà !"); 
		}
	}
	
	public void deleteKoth(Player player, String name){
		if (existe(name)){
			koths.remove(getKoth(name));
			player.sendMessage(Kronos.k().getPrefix() + " §7Vous venez de supprimer le Koth §a" + name + "§7.");
		}else{
			player.sendMessage(Kronos.k().getPrefix() + " §7Se koth n'existe pas !"); 
		}
	}
	
	public void changeKothTime(Player player, String name, int temps){
		if (existe(name)){
			
			getKoth(name).setTimeCap(temps);
			player.sendMessage(Kronos.k().getPrefix() + " §7Vous venez de mettre le temps du koth à §a" + temps +" §7secondes.");
			
		}else{
			player.sendMessage(Kronos.k().getPrefix() + " §7Se koth n'existe pas !"); 
		}
	}
	
	public void startKoth(Player player, String name){
		if (existe(name)){
			
			if (!hasEnableKoth()){
				
				getKoth(name).setActive(true);
				Bukkit.broadcastMessage(Kronos.k().getPrefix() + " §7Le koth §a" + name +" §7vient de se lancer !");				
				
			}else{
				player.sendMessage(Kronos.k().getPrefix() + " §7Un koth est déjà en cours !"); 
			}
			
		}else{
			player.sendMessage(Kronos.k().getPrefix() + " §7Se koth n'existe pas !"); 
		}
	}
	
	public void endKoth(Player player, String name){
		if (existe(name)){
			
			if (hasEnableKoth()){
				
				if (getKoth(name).isActive()){
					
					getKoth(name).setActive(false);
					Bukkit.broadcastMessage(Kronos.k().getPrefix() + " §7Le koth §a" + name +" §7vient de se lancer !");
					
				}else{
					player.sendMessage(Kronos.k().getPrefix() + " §7Se koth n'ai pas encours !"); 
				}
				
			}else{
				player.sendMessage(Kronos.k().getPrefix() + " §7Aucune koth est en cours !"); 
			}
			
		}else{
			player.sendMessage(Kronos.k().getPrefix() + " §7Se koth n'existe pas !"); 
		}		
	}

	public boolean hasInventoryFull(Player p){
		int slot = 0;
    	ItemStack[] arrayOfItemStack;
		int x = (arrayOfItemStack = p.getInventory().getContents()).length;
		for (int i = 0; i < x; i++) {
			ItemStack contents = arrayOfItemStack[i];
			
			if ((contents == null)) {
				slot++;
			}
		}
		if (slot != 0){
			return false;
		}else{
			return true;
		}
	}
	
	public void giveLoot(Player player) {
		if (existe(player.getItemInHand().getItemMeta().getLore().get(0).replace("§6» §eKoth gagné§e: §6", ""))){
			Random r = new Random();
			KothData d = getKoth(player.getItemInHand().getItemMeta().getLore().get(0).replace("§6» §eKoth gagné§e: §6", ""));
			for(int a = 0; a != 4; a++){
				if (hasInventoryFull(player)){
					player.getWorld().dropItem(player.getLocation(), d.getLoots().get(r.nextInt(d.getLoots().size()-1)));
				}else{
					player.getInventory().addItem(d.getLoots().get(r.nextInt(d.getLoots().size()-1)));
				}
			}
			player.updateInventory();
			player.sendMessage(Kronos.k().getPrefix() + " §eVous venez d'ouvrir le loot !");
			if (player.getItemInHand().getAmount() > 1){
				player.getItemInHand().setAmount(player.getItemInHand().getAmount()-1);
			}else{
				player.setItemInHand(new ItemStack(Material.AIR));
			}
		}
	}
	
	public void viewLoot(Player player, String name){
		if (existe(name)){
			
			Inventory inv = Bukkit.createInventory(null, 54, "§eKoth loot");
			int slot = 0;
			for(ItemStack it : getKoth(name).getLoots()){
				inv.setItem(slot, it);slot++;
			}
					
			player.openInventory(inv);
			
		}else{
			player.sendMessage(Kronos.k().getPrefix() + " §eSe koth n'existe pas !");
		}
	}
	
	public void editLoot(Player player, String name){
		if (existe(name)){
			
			Inventory inv = Bukkit.createInventory(null, 54, "§6Koth Loot §a"+getKoth(name).getName());
			int slot = 0;
			for(ItemStack it : getKoth(name).getLoots()){
				inv.setItem(slot, it);slot++;
			}
			
			player.openInventory(inv);
			
		}else{
			player.sendMessage(Kronos.k().getPrefix() + " §7Se koth n'existe pas !");
		}
	}
	
}
