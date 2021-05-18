package fr.maxlego08.core.command.commands.totem;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import fr.maxlego08.core.Kronos;
import fr.maxlego08.core.command.commands.totem.data.TotemData;
import fr.maxlego08.core.utils.ItemBuilder;

public class TotemManager {

	private TotemData totem = new TotemData();
	private List<ItemStack> loots = new ArrayList<>();
	
	public TotemManager() {
		loots.add(ItemBuilder.getCreatedItem(Material.DIAMOND, 15, null));
	}
	
	public void setTotem(Location location){
		totem.setLocation(location);
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
	
	public void spawn(CommandSender sender){
		if (totem.getLocation() == null){
			sender.sendMessage(Kronos.k().getPrefix()+ " §cVous devez set le totem");return;
		}
		if (!totem.isActive()){ Bukkit.broadcastMessage(Kronos.k().getPrefix() + " §eLe totem vient d'apparaître en §6" + totem.getLocation().getBlockX()+", "
				+totem.getLocation().getBlockY()+", "+totem.getLocation().getBlockZ());
		totem.create(); return;}
		sender.sendMessage(Kronos.k().getPrefix()+" §cUn totem est déjà en corus");
	}
	
	public TotemData getTotem() {
		return totem;
	}

	public void giveLoot(Player player) {
		// TODO Auto-generated method stub
		
	}

	public void stop(CommandSender sender) {
		if (totem.isActive()){
			totem.delete();
			Bukkit.broadcastMessage(Kronos.k().getPrefix()+" §eLe totem vient d'être arreté !");
		}else{
			sender.sendMessage(Kronos.k().getPrefix() + " §cLe totem est pas actif");
		}
		
	}
	
}
