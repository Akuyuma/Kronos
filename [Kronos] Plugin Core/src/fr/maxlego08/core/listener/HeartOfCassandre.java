package fr.maxlego08.core.listener;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import fr.maxlego08.core.Kronos;

public class HeartOfCassandre implements Listener {

	private int durability = 3;
	private Kronos core;
	
	public HeartOfCassandre(Kronos core) {
		super();
		this.core = core;
	}

	@EventHandler
	public void onInteract(PlayerInteractEvent e){
		
		if (e.isCancelled()) return;
		// only need to check right-clicks and physical as of MC 1.4+; good performance boost
		if (e.getAction() != Action.RIGHT_CLICK_BLOCK && e.getAction() != Action.PHYSICAL) return;

		Block block = e.getClickedBlock();
		Player player = e.getPlayer();

		if (block == null) return;  // clicked in air, apparently
		
		if (!player.getItemInHand().getType().equals(Material.HEART_OF_CASSANDRE)) return; // check item in hand
		
		player.getItemInHand().setDurability((short) (player.getItemInHand().getDurability()+1)); // apply durability
		if (player.getItemInHand().getDurability() > durability) player.getInventory().remove(player.getItemInHand());
		
		Location location = player.getLocation();
		searchChest(location, player);
	}
	
	private void searchChest(Location location, Player player){
		Bukkit.getScheduler().runTaskAsynchronously(core, () -> {
			Chunk defaultC = location.getChunk();
			World world = location.getWorld();
			
			int chest = 0;
			
			for(int a = -4; a != 5; a++){
				for(int b = -4; b != 5; b++){
					Chunk currentC = world.getChunkAt((defaultC.getX() + a), (defaultC.getZ() + b));
					for(Block bz : currentC.getBlocks()){
						if (bz.getType().equals(Material.CHEST)
								|| bz.getType().equals(Material.MALACHITE_CHEST)
								|| bz.getType().equals(Material.ANGELITE_CHEST)
								|| bz.getType().equals(Material.CELESTINE_CHEST)
								|| bz.getType().equals(Material.CELENITE_CHEST)){
							chest++;
						}
					}
				}
			}
			
			player.sendMessage("§8[§dCassandre§8] §dIl y a actuellement §5" + chest + " §dcoffre(s) autour de toi !");		
		});
	}
	
}
