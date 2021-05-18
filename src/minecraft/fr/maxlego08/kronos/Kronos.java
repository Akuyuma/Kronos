package fr.maxlego08.kronos;

import java.util.HashMap;
import java.util.Map;

import fr.maxlego08.kronos.init.Blocks;
import fr.maxlego08.kronos.items.Items;
import fr.maxlego08.kronos.utils.Colors;
import fr.maxlego08.kronos.utils.DiscordUtils;
import fr.maxlego08.kronos.utils.IItemRenderer;
import fr.maxlego08.kronos.utils.RenderItemHelper;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.CraftingManager;

public class Kronos {

	/*
	 * Développé par Maxlego08 pour le serveur Kronos
	 * */
	
	private String clientVersion = "1.0.0";
	
	private static boolean alreadyInit = false;
	
	private static final Kronos kronos = new Kronos(); 
	
	private final CreativeTabs tabsOutils = CreativeTabs.tabsOutils;	
	private final CreativeTabs tabsCombat = CreativeTabs.tabsCombat;	
	private final CreativeTabs tabsBlocks = CreativeTabs.tabsBlocks;	
	private final CreativeTabs tabsDivers = CreativeTabs.tabsDivers;
	private final CreativeTabs tabsBouffe = CreativeTabs.tabsBouffe;
	
	public void init()
	{
		if(alreadyInit) return;
		
		/* On affiche la présence sur discord */
		
		new DiscordUtils();
		new Colors();
		
		alreadyInit = true;
	}
	
	
	public void registerItemRenderer(Item item, IItemRenderer renderer)
	{
		RenderItemHelper.registerItemRenderer(item, renderer);
	}
	
	public static Kronos getKronos() {
		return kronos;
	}
	

	public CreativeTabs getTabsOutils() {
		return tabsOutils;
	}

	public CreativeTabs getTabsBlocks() {
		return tabsBlocks;
	}

	public CreativeTabs getTabsDivers() {
		return tabsDivers;
	}
	
	public CreativeTabs getTabsCombat() {
		return tabsCombat;
	}
	
	public String getClientVersion() {
		return clientVersion;
	}
	
	public CreativeTabs getTabsBouffe() {
		return tabsBouffe;
	}
	
	public void ticks() 
	{
		
	}
	
	
}
