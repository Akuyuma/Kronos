package fr.maxlego08.core.spawners;

import java.io.File;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.craftbukkit.libs.com.google.gson.reflect.TypeToken;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import fr.maxlego08.core.Kronos;
import fr.maxlego08.core.utils.ItemBuilder;
import fr.maxlego08.core.utils.LocationUtils;
import fr.maxlego08.core.utils.ZPlugin;
import fr.maxlego08.core.utils.ZPlugin.LogType;
import fr.maxlego08.core.utils.gson.DiscUtil;

public class Spawners extends LocationUtils{

	private transient File file = new File(ZPlugin.z().getDataFolder(), "spawners.json");
	private Map<String, List<Spawner>> spawners = new HashMap<>();
	private Map<Player, Integer> currentPage = new HashMap<>();
	
	private Map<Location, Spawner> spawnerLocation = new HashMap<>();
	
	public Map<String, List<Spawner>> getSpawners() {
		return spawners;
	}
	
	public Map<Player, Integer> getCurrentPage() {
		return currentPage;
	}
	
	public int getCurrentPage(Player player){
		return currentPage.get(player) != null ? currentPage.get(player) : 1;
	}
	
	public void setCurrentPage(Player player, int page){
		currentPage.put(player, page);
	}
	
	public boolean hasSpawner(String player){
		return getSpawner(player) != null;
	}
	
	public Spawner getSpawner(String player, int id) throws IllegalArgumentException{
		return getSpawner(player).get(id);
	}
	
	/*
	 * récupérer tous les spawners d'un joueur
	 * */
	
	public List<Spawner> getSpawner(String player){
		if (spawners.containsKey(player)){
			return spawners.get(player);
		}
		return null;
	}
	
	/*
	 * Ajouter un spawner à un joueur
	 * */
	
	public void addSpawner(String player, EntityType type, Location location, boolean powerUp){
		List<Spawner> spawners = new ArrayList<>();
		if (getSpawner(player) != null){
			spawners = getSpawner(player);
		}
		Spawner sp = new Spawner(location, player, type, powerUp);
		spawners.add(sp);
		this.spawners.put(player, spawners);
		if (location != null){
			spawnerLocation.put(location, sp);
		}
	}
	
	/*
	 * Retirer un spawner a un joueur
	 * */
	
	public boolean removeSpawner(String player, EntityType type){
		if (getSpawner(player) != null){
			List<Spawner> spawners = getSpawner(player);
			for(Spawner spawner : spawners){
				if (spawner.getType().equals(type)){
					spawners.remove(spawner);
					this.spawners.put(player, spawners);
					return true;
				}
			}
		}		
		return false;
	}
	
	/*
	 * Vérifier si le joueur a un spawner
	 * */
	
	public boolean hasSpawner(EntityType type, String player){
		if (getSpawner(player) != null){
			List<Spawner> spawners = getSpawner(player);
			for(Spawner spawner : spawners){
				if (spawner.getType().equals(type)){
					return true;
				}
			}
		}		
		return false;
	}
	
	public boolean load(){
		if (file.exists()){
			ZPlugin.z().log("Loading spawners form disk", LogType.INFO);
			try{
				Type type = new TypeToken< Map<String, Map<String ,Map<String, String>>>>(){}.getType();
				Map<String, Map<String ,Map<String, String>>> map = ZPlugin.z().getGson().fromJson(DiscUtil.read(file), type);
				t(map);
				return true;
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	

	private void t(Map<String, Map<String, Map<String, String>>> map) {
		for(Entry<String, Map<String, Map<String, String>>> m : map.entrySet()){
			String pseudo = m.getKey();
			for(Entry<String, Map<String, String>> n : m.getValue().entrySet()){
				Location l = null;
				if (n.getValue().containsKey("location")) l = changeStringLocationToLocation(n.getValue().get("location"));
				boolean powerUp = Boolean.valueOf(n.getValue().get("power"));
				addSpawner(pseudo, EntityType.fromName(n.getValue().get("type")), l, powerUp);
			}
		}
		
	}

	public boolean save(){
		try{
			DiscUtil.write(file, ZPlugin.z().getGson().toJson(z()));
			return true;
		}catch (Exception e) {
			e.printStackTrace();
			ZPlugin.z().log("Failed to save the spawner to disk.", LogType.ERROR);
			return false;
		}
	}
	
	private Map<String, Map<String ,Map<String, String>>> z() {
		
		Map<String, Map<String ,Map<String, String>>> map = new HashMap<>();
		
		for(Entry<String, List<Spawner>> entry : new ArrayList<>(spawners.entrySet())){
			Map<String ,Map<String, String>> l = new HashMap<>();
			
			int id = 1;
			
			for(Spawner spawner : entry.getValue()){
				Map<String, String> betterMap = new HashMap<>();
				betterMap.put("type", spawner.getType().getName());
				if (spawner.isPlaced()) {
					betterMap.put("location", changeLocationToString(spawner.getLocation()));
				}
				betterMap.put("power", String.valueOf(spawner.isPowerUp()));
				l.put(String.valueOf(id), betterMap);
				id++;
			}
			map.put(entry.getKey(), l);
			
		}		
		
		return map;
		
	}

	public void openSpawnerPlayer(Player player, int page){
		List<Spawner> spawnersList = getSpawner(player.getName());
		if (page == 0){
			player.sendMessage(Kronos.k().getPrefix() + " §bVous ne pouvez pas voir la page §c0 §b!");
			return;
		}
		this.setCurrentPage(player, page);

		Inventory inv = Bukkit.createInventory(null, 54, "§eSpawner §6%page%§7/§6%max%".replace("%page%", String.valueOf(page))
				.replace("%max%", String.valueOf(getMaxPage(player))));
		
		int spawners = spawnersList.size();	
		int idStart = ((page - 1) * 45);
		int idEnd = page * 45;
		if (spawners < idEnd){
			idEnd = spawners;
		}
		int slot = 0;
		
		for(int l = idStart; l != idEnd; l++){
			addItem(inv, slot, l, player, spawnersList.get(l));
			slot++;	
			
		}
		if (page < getMaxPage(player) && (idEnd - idStart) >= 44){
			inv.setItem(50, ItemBuilder.getCreatedItem(Material.PAPER, 1, "§6» §ePage suivante"));
		}
		if (page <= getMaxPage(player) && page != 1){
			inv.setItem(48, ItemBuilder.getCreatedItem(Material.PAPER, 1, "§6» §ePage précedente"));
		}
		
		player.openInventory(inv);
		
	}
	
	public int getMaxPage(Player player){		
		return (getSpawner(player.getName()).size() / 45) + 1;	
	}
	
	
	@SuppressWarnings("deprecation")
	private void addItem(Inventory inv, int slot, int id, Player player, Spawner sp){
		ItemStack item = new ItemStack(Material.MOB_SPAWNER, 1);
		ItemMeta itemM = item.getItemMeta();
		
		itemM.setDisplayName("§eSpawner§7: §a"+ id);
		
		
		List<String> lores = new ArrayList<>();
		lores.add("§7§m--------------------");
		lores.add("");
		lores.add("§6» §eType§7: §6" + sp.getType().getName());
		if (sp.isPowerUp()){		
			lores.add("§6» §eLe spawner est §2Amélioré");
		}else
			lores.add("§6» §eLe spawner est normal");				
		if (sp.isPlaced()){
			lores.add("");
			lores.add("§6» §eMonde§7: §6" + sp.getLocation().getWorld().getName());
			lores.add("§6» §eX§7: §6" + sp.getLocation().getBlockX());
			lores.add("§6» §eY§7: §6" + sp.getLocation().getBlockY());
			lores.add("§6» §eZ§7: §6" + sp.getLocation().getBlockZ());
		}
		lores.add("");
		lores.add("§7§m--------------------");
		
		itemM.setLore(lores);
		item.setItemMeta(itemM);
		inv.setItem(slot, item);		
	}
	
	public boolean sendSpawner(Player sender, Player player, int id) {
		
		if (id <= getSpawner(sender.getName()).size()){
			try{
				Spawner spawner = getSpawner(sender.getName()).get(id);
				if (spawner.isPlaced()){
					spawner.removeSpawner();
				}
				List<Spawner> sp = getSpawner(sender.getName());
				sp.remove(id);
				addSpawner(player.getName(), spawner.getType(), null, spawner.isPowerUp());
				spawners.put(sender.getName(), sp);
				return true;
			}catch (Exception e) { }
			
		}		
		return false;
	}
	
	public Spawner getSpawner(Location location){
		if (getSpawnerLocation().containsKey(location)){
			return getSpawnerLocation().get(location);
		}
		return null;
	}
	
	public Map<Location, Spawner> getSpawnerLocation() {
		return spawnerLocation;
	}
	
	public boolean isSpawner(Location location){
		return getSpawnerLocation().containsKey(location);
	}
	
	public void powerUp(Location location){
		
	}
	
}
