package fr.maxlego08.core.spawners.listener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.bukkit.Material;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.scheduler.BukkitRunnable;

import com.massivecraft.factions.FPlayers;

import fr.maxlego08.core.Kronos;
import fr.maxlego08.core.spawners.Spawner;
import fr.maxlego08.core.spawners.Spawners;

public class SpawnerListener implements Listener{

	private final Kronos main;
	private final Spawners manager;
	
	private List<EntityType> types = new ArrayList<>();
	
	private Random random = new Random();
	private int how;
	
	public SpawnerListener(Kronos main, Spawners Spawners) {
		this.main = main;
		this.manager = Spawners;
		
		types.add(EntityType.CAVE_SPIDER);
		types.add(EntityType.BLAZE);
		types.add(EntityType.CHICKEN);
		types.add(EntityType.COW);
		types.add(EntityType.CREEPER);
		types.add(EntityType.ENDERMAN);
		types.add(EntityType.GHAST);
		types.add(EntityType.HORSE);
		types.add(EntityType.IRON_GOLEM);
		types.add(EntityType.MAGMA_CUBE);
		types.add(EntityType.OCELOT);
		types.add(EntityType.MUSHROOM_COW);
		types.add(EntityType.SKELETON);
		types.add(EntityType.SILVERFISH);
		types.add(EntityType.PIG_ZOMBIE);
		types.add(EntityType.PIG);
		types.add(EntityType.SHEEP);
		types.add(EntityType.SLIME);
		types.add(EntityType.SPIDER);
		types.add(EntityType.VILLAGER);
		types.add(EntityType.SQUID);
		types.add(EntityType.WITCH);
		types.add(EntityType.WOLF);
		types.add(EntityType.ZOMBIE);
		
		how = types.size();
		
	}

	private Map<Player, Integer> idPlace = new HashMap<>();
	
	@EventHandler
	public void onInventory(InventoryClickEvent e) throws IllegalArgumentException{
		Player player = (Player)e.getWhoClicked();
		if (e.getInventory().getName().startsWith("§eSpawner ")){
			e.setCancelled(true);
			if (e.getCurrentItem() == null){
				return;
			}
			if (e.getSlot() == 50 && e.getInventory().getItem(e.getSlot()).getType().equals(Material.PAPER)){
				manager.openSpawnerPlayer(player, manager.getCurrentPage(player)+1);
			}
			if (e.getSlot() == 48 && e.getInventory().getItem(e.getSlot()).getType().equals(Material.PAPER)){
				manager.openSpawnerPlayer(player, manager.getCurrentPage(player)-1);
			}
			if (e.getClickedInventory().getTitle().startsWith("§eSpawner ") && e.getCurrentItem().getType().equals(Material.MOB_SPAWNER)){
				int id = Integer.valueOf(e.getCurrentItem().getItemMeta().getDisplayName().replace("§eSpawner§7: §a", ""));
				if (manager.getSpawner(player.getName(), id).isPlaced()){
					
					manager.getSpawnerLocation().remove(manager.getSpawner(player.getName(), id).getLocation());
					manager.getSpawner(player.getName(), id).removeSpawner();
					
					player.sendMessage(main.getPrefix()+ " §7Vous venez de retirer votre spawner !");
					
					manager.openSpawnerPlayer(player, manager.getCurrentPage(player));
					return;
				}
				idPlace.put(player, id);
				player.closeInventory();
				player.sendMessage(main.getPrefix()+ " §7Vous avez §a30§7 secondes pour casser un bloc pour placer le spawner");
				new BukkitRunnable() {
					
					@Override
					public void run() {
						if (idPlace.containsKey(player)){
							idPlace.remove(player);
						}
						
					}
				}.runTaskLater(main, 20*30L);
			}
		}		
	}
	
	@EventHandler
	public void onBreak(BlockBreakEvent e){
		if (main.canBuild(e.getPlayer(), e.getBlock().getLocation())){
			if (FPlayers.i.get(e.getPlayer()).isInOwnTerritory() && idPlace.containsKey(e.getPlayer())){
				if (!e.isCancelled()){
					e.setCancelled(true);
					e.getBlock().setType(Material.MOB_SPAWNER);
					CreatureSpawner spawner = (CreatureSpawner)e.getBlock().getState();
					spawner.setSpawnedType(manager.getSpawner(e.getPlayer().getName()).get(idPlace.get(e.getPlayer())).getType());
					
					Spawner s = manager.getSpawner(e.getPlayer().getName()).get(idPlace.get(e.getPlayer()));
					
					s.setLocation(e.getBlock().getLocation());
					manager.getSpawnerLocation().put(e.getBlock().getLocation(), s);
					
					idPlace.remove(e.getPlayer());
					return;
				}
			}else if (idPlace.containsKey(e.getPlayer())){
				e.getPlayer().sendMessage(main.getPrefix() + " §7Vous devez être dans votre claim pour placer un spawner !");
			}
		}		
		if (manager.isSpawner(e.getBlock().getLocation())){
			e.setExpToDrop(0);
			
			manager.getSpawner(e.getBlock().getLocation()).removeSpawner();
			manager.getSpawnerLocation().remove(e.getBlock().getLocation());
			
		}
	}
	
	@EventHandler
	public void onInteract(PlayerInteractEvent e){
		
		if (e.getAction() == Action.RIGHT_CLICK_BLOCK){
			
			if (!e.getClickedBlock().getType().equals(Material.MOB_SPAWNER)) return;
			
			Player player = e.getPlayer();
			
			if (player.getItemInHand().getType().equals(Material.SOUL_OF_DEMONIC_SPAWNER)){
				
				CreatureSpawner spawner = (CreatureSpawner)e.getClickedBlock().getState();
				
				EntityType type = types.get(random.nextInt(how-1));
				
				spawner.setSpawnedType(type);
				
				if (manager.isSpawner(e.getClickedBlock().getLocation())){
					
					manager.getSpawner(e.getClickedBlock().getLocation()).setType(type);
					
				}
				
				player.getInventory().remove(player.getItemInHand());
				player.sendMessage(main.getPrefix() + " §eVous venez de changer le type de spawner en §6"+type.name().replace("_", "").toLowerCase()+" §e!");
				
			}
			if (player.getItemInHand().getType().equals(Material.SOUL_OF_ANGELIC_SPAWNER)){
				
				
				if (manager.isSpawner(e.getClickedBlock().getLocation())){
					
					
					manager.getSpawner(e.getClickedBlock().getLocation()).setPowerUp();
					player.getInventory().remove(player.getItemInHand());
					player.sendMessage(main.getPrefix() + " §eVous venez d'activer le mode §6SUPER PUISSANCE§e de votre spawner !");
					
				}				
				
			}
			
		}
		
	}
	
}
