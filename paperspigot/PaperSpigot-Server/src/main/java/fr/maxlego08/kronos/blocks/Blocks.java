package fr.maxlego08.kronos.blocks;

import fr.maxlego08.kronos.Kronos;
import net.minecraft.server.Block;
import net.minecraft.server.BlockOre;
import net.minecraft.server.CreativeModeTab;
import net.minecraft.server.Material;

public enum Blocks {

	ANGELITE_ORE("angelite_ore", new BlockOre(), CreativeModeTab.tabsBlocks, 6.0F, 8.0F),
	ANGELITE_BLOCK("angelite_block", new BlockOre(),  CreativeModeTab.tabsBlocks, 6.0F, 8.0F),
	
	CELESTINE_ORE("celestine_ore", new BlockOre(),  CreativeModeTab.tabsBlocks, 5.5F, 7.0F),
	CELESTINE_BLOCK("celestine_block", new BlockOre(),  CreativeModeTab.tabsBlocks, 5.5F, 7.0F),
	
	MALACHITE_ORE("malachite_ore", new BlockOre(),  CreativeModeTab.tabsBlocks, 5.0F, 6.0F),
	MALACHITE_BLOCK("malachite_block", new BlockOre(),  CreativeModeTab.tabsBlocks, 5.0F, 6.0F),
	
	CELENITE_ORE("celenite_ore", new BlockOre(),  CreativeModeTab.tabsBlocks, 4.0F, 5.0F),
	CELENITE_BLOCK("celenite_block", new BlockOre(),  CreativeModeTab.tabsBlocks, 4.0F, 5.0F),
	
	RANDOM_ORE("random_ore", new BlockOre(),  CreativeModeTab.tabsBlocks, 5.0F, 6.0F),
	
	XP_ORE("xp_ore", new BlockOre(),  CreativeModeTab.tabsBlocks, 4.0F, 5.0F),
	
	
	ANGELITE_CHEST("angelite_chest", new BlockAngeliteChest(0),  CreativeModeTab.tabsBlocks, 4.0F, 5.0F),
	CELESTINE_CHEST("celestine_chest", new BlockCelestineChest(0),  CreativeModeTab.tabsBlocks, 6.0F, 10.0F),
	MALACHITE_CHEST("malachite_chest", new BlockMalachiteChest(0),  CreativeModeTab.tabsBlocks, 6.0F, 8.0F),
	CELENITE_CHEST("celenite_chest", new BlockCeleniteChest(0),  CreativeModeTab.tabsBlocks, 10.0F, 12.0F),
	
	CHILLI_PEPPER("chille_pepper", new BlockChilliPepper(), CreativeModeTab.tabsBlocks, 0.2f, 5.0f),
	
	XRAY("xray", new Block(Material.ORE),  CreativeModeTab.tabsBlocks, 4.0F, 5.0F),
	
	;
	
	private String name;
	private Block block;
	private float hardness;
	private float resistance;
	private CreativeModeTab tabs;
	
	private Blocks(String name, Block block, CreativeModeTab tabs, float hardness, float resistance)
	{
		this.name = name;
		this.block = block;
		this.tabs = tabs;
		this.resistance = resistance;
		this.hardness = hardness;
	}
	
	public String getName()
	{
		return name;
	}
	
	public Block getBlock()
	{
		return block;
	}
	
	public CreativeModeTab getTabs() {
		return tabs;
	}
	
	public float getHardness() {
		return hardness;
	}
	
	public float getResistance() {
		return resistance;
	}
}
