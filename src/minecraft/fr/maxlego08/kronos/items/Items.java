package fr.maxlego08.kronos.items;

import java.util.Arrays;
import java.util.List;

import fr.maxlego08.kronos.Kronos;
import fr.maxlego08.kronos.init.Blocks;
import fr.maxlego08.kronos.utils.IItemRenderer;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.potion.Potion;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemBucket;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemSword;

public enum Items {

	ANGELITE("angelite", new Item(), Kronos.getKronos().getTabsDivers()),
	ANGELITE_INGOT("angelite_ingot", new Item(), Kronos.getKronos().getTabsDivers()),
	CELESTINE("celestine", new Item(), Kronos.getKronos().getTabsDivers()),
	MALACHITE("malachite", new Item(), Kronos.getKronos().getTabsDivers()),
	CELENITE("celenite", new Item(), Kronos.getKronos().getTabsDivers()),
	
	ANGELITE_PICKAXE("angelite_pickaxe", new ItemPickaxe(ToolMaterial.ANGELITE), Kronos.getKronos().getTabsOutils()),	
	ANGELITE_AXE("angelite_axe", new ItemAxe(ToolMaterial.ANGELITE), Kronos.getKronos().getTabsOutils()),	
	ANGELITE_HOE("angelite_hoe", new ItemHoe(ToolMaterial.ANGELITE), Kronos.getKronos().getTabsOutils()),	
	ANGELITE_SHOVEL("angelite_shovel", new ItemSpade(ToolMaterial.ANGELITE), Kronos.getKronos().getTabsOutils()),	
	ANGELITE_MULTITOOLS("angelite_multitools", new ItemMultiTool(ToolMaterial.ANGELITE), Kronos.getKronos().getTabsOutils()),	
	
	CELESTINE_PICKAXE("celestine_pickaxe", new ItemPickaxe(ToolMaterial.CELESTINE), Kronos.getKronos().getTabsOutils()),	
	CELESTINE_AXE("celestine_axe", new ItemAxe(ToolMaterial.CELESTINE), Kronos.getKronos().getTabsOutils()),	
	CELESTINE_HOE("celestine_hoe", new ItemHoe(ToolMaterial.CELESTINE), Kronos.getKronos().getTabsOutils()),	
	CELESTINE_SHOVEL("celestine_shovel", new ItemSpade(ToolMaterial.CELESTINE), Kronos.getKronos().getTabsOutils()),	
	CELESTINE_MULTITOOLS("celestine_multitools", new ItemMultiTool(ToolMaterial.ANGELITE), Kronos.getKronos().getTabsOutils()),	
	
	MALACHITE_PICKAXE("malachite_pickaxe", new ItemPickaxe(ToolMaterial.MALACHITE), Kronos.getKronos().getTabsOutils()),	
	MALACHITE_AXE("malachite_axe", new ItemAxe(ToolMaterial.MALACHITE), Kronos.getKronos().getTabsOutils()),	
	MALACHITE_HOE("malachite_hoe", new ItemHoe(ToolMaterial.MALACHITE), Kronos.getKronos().getTabsOutils()),	
	MALACHITE_SHOVEL("malachite_shovel", new ItemSpade(ToolMaterial.MALACHITE), Kronos.getKronos().getTabsOutils()),	
	MALACHITE_MULTITOOLS("malachite_multitools", new ItemMultiTool(ToolMaterial.ANGELITE), Kronos.getKronos().getTabsOutils()),	
	
	CELENITE_PICKAXE("celenite_pickaxe", new ItemPickaxe(ToolMaterial.CELENITE), Kronos.getKronos().getTabsOutils()),	
	CELENITE_AXE("celenite_axe", new ItemAxe(ToolMaterial.CELENITE), Kronos.getKronos().getTabsOutils()),	
	CELENITE_HOE("celenite_hoe", new ItemHoe(ToolMaterial.CELENITE), Kronos.getKronos().getTabsOutils()),	
	CELENITE_SHOVEL("celenite_shovel", new ItemSpade(ToolMaterial.CELENITE), Kronos.getKronos().getTabsOutils()),		
	CELENITE_MULTITOOLS("celenite_multitools", new ItemMultiTool(ToolMaterial.ANGELITE), Kronos.getKronos().getTabsOutils()),	
	
	ANGELITE_SWORD("angelite_sword", new ItemSword(ToolMaterial.ANGELITE), Kronos.getKronos().getTabsCombat()),	
	CELESTINE_SWORD("celestine_sword", new ItemSword(ToolMaterial.CELESTINE), Kronos.getKronos().getTabsCombat()),	
	MALACHITE_SWORD("malachite_sword", new ItemSword(ToolMaterial.MALACHITE), Kronos.getKronos().getTabsCombat()),	
	CELENITE_SWORD("celenite_sword", new ItemSword(ToolMaterial.CELENITE), Kronos.getKronos().getTabsCombat()),	
	
	ANGELITE_HELMET("angelite_helmet", new ItemArmor(ItemArmor.ArmorMaterial.ANGELITE, 5, 0), Kronos.getKronos().getTabsCombat()),	
	ANGELITE_CHESTPLATE("angelite_chestplate", new ItemArmor(ItemArmor.ArmorMaterial.ANGELITE, 5, 1), Kronos.getKronos().getTabsCombat()),	
	ANGELITE_LEGGINGS("angelite_leggings", new ItemArmor(ItemArmor.ArmorMaterial.ANGELITE, 5, 2), Kronos.getKronos().getTabsCombat()),	
	ANGELITE_BOOTS("angelite_boots", new ItemArmor(ItemArmor.ArmorMaterial.ANGELITE, 5, 3), Kronos.getKronos().getTabsCombat()),	
	
	CELESTINE_HELMET("celestine_helmet", new ItemArmor(ItemArmor.ArmorMaterial.CELESTINE, 6, 0), Kronos.getKronos().getTabsCombat()),	
	CELESTINE_CHESTPLATE("celestine_chestplate", new ItemArmor(ItemArmor.ArmorMaterial.CELESTINE, 6, 1), Kronos.getKronos().getTabsCombat()),	
	CELESTINE_LEGGINGS("celestine_leggings", new ItemArmor(ItemArmor.ArmorMaterial.CELESTINE, 6, 2), Kronos.getKronos().getTabsCombat()),	
	CELESTINE_BOOTS("celestine_boots", new ItemArmor(ItemArmor.ArmorMaterial.CELESTINE, 6, 3), Kronos.getKronos().getTabsCombat()),	
	
	MALACHITE_HELMET("malachite_helmet", new ItemArmor(ItemArmor.ArmorMaterial.MALACHITE, 7, 0), Kronos.getKronos().getTabsCombat()),	
	MALACHITE_CHESTPLATE("malachite_chestplate", new ItemArmor(ItemArmor.ArmorMaterial.MALACHITE, 7, 1), Kronos.getKronos().getTabsCombat()),	
	MALACHITE_LEGGINGS("malachite_leggings", new ItemArmor(ItemArmor.ArmorMaterial.MALACHITE, 7, 2), Kronos.getKronos().getTabsCombat()),	
	MALACHITE_BOOTS("malachite_boots", new ItemArmor(ItemArmor.ArmorMaterial.MALACHITE, 7, 3), Kronos.getKronos().getTabsCombat()),	
	
	CELENITE_HELMET("celenite_helmet", new ItemArmor(ItemArmor.ArmorMaterial.CELENITE, 8, 0), Kronos.getKronos().getTabsCombat()),	
	CELENITE_CHESTPLATE("celenite_chestplate", new ItemArmor(ItemArmor.ArmorMaterial.CELENITE, 8, 1), Kronos.getKronos().getTabsCombat()),	
	CELENITE_LEGGINGS("celenite_leggings", new ItemArmor(ItemArmor.ArmorMaterial.CELENITE, 8, 2), Kronos.getKronos().getTabsCombat()),	
	CELENITE_BOOTS("celenite_boots", new ItemArmor(ItemArmor.ArmorMaterial.CELENITE, 8, 3), Kronos.getKronos().getTabsCombat()),	
	
	MALEFICIENT_SCYTHE("maleficent_scythe", new ItemSword(ToolMaterial.SCYTHE), Kronos.getKronos().getTabsCombat()),	
	
	BLADES_OF_ARES("blades_of_ares", new ItemBladesOfAres(ToolMaterial.ARES), Kronos.getKronos().getTabsCombat()),	
	
	POSITIVE_GRAPES("positive_grapes", new ItemFood(4, 0.5F, false).setAlwaysEdible(), Kronos.getKronos().getTabsBouffe(), Arrays.asList("§6» §eRetire tous les effets néfastes §7(§esauf AntiBack Ap§7)")),
	
	GOLDEN_GRAPES("golden_grapes", new ItemFood(4, 0.5F, false).setAlwaysEdible(), Kronos.getKronos().getTabsBouffe(), Arrays.asList("§6» §eRetire l'effet d'AntiBack Ap")),
	
	HEPHAISTOS_GRAPES("hephaistos_grapes", new ItemHephaistosGrapes(4, 0.5F, false).setAlwaysEdible(), Kronos.getKronos().getTabsBouffe(), Arrays.asList("§6» §eDonne l'effet de NoFall pendant §63 §eminutes")),
	
	FARMTOOLS("farmtools", new ItemFarmTools(ToolMaterial.CELENITE), Kronos.getKronos().getTabsOutils()),	
	
	CREEPER_DIVIN("creeper_divin", new ItemCreeperDivin() ,Kronos.getKronos().getTabsDivers(), Arrays.asList("§6» §eSe creeper peut place dans les coffres")),
	
	EYE_OF_GODS("eye_of_gods", new ItemEyeOfGod(), Kronos.getKronos().getTabsDivers(), Arrays.asList("§6» §ePermet de voir le contenu de n'importe qu'elle coffre")),
	
	HEART_OF_CASSANDRE("heart_of_cassandre", new ItemHeartOfCassandre(), Kronos.getKronos().getTabsDivers(), Arrays.asList("§6» §ePermet de voir le nombre de coffre dans un diamettre de §6300 §eblocks")),
	
	DYNAMITE("dynamite", new ItemDynamite(), Kronos.getKronos().getTabsDivers()),
	
	TARTARE_KEY("tartare_key", new Item(), Kronos.getKronos().getTabsDivers()),
	SKY_KEY("sky_key", new Item(), Kronos.getKronos().getTabsDivers()),
	
	STICK_OF_GODS("stick_of_god", new ItemStickOfGods(), Kronos.getKronos().getTabsDivers()),
	
	STICK_OF_TARTARE("stick_of_tartare", new ItemStickOfTartare(), Kronos.getKronos().getTabsDivers(), Arrays.asList("§6» §ePermet de réduire la durabilité de l'obsidienne", "§6» §7Clique droit pour détruire l'obsidienne", "§6» §7Clique gauche pour voir la vie de l'obsidienne")),
	
	WATER_CAPSULE("water_capsule", new ItemWaterCapsule(Blocks.flowing_water), Kronos.getKronos().getTabsDivers(), Arrays.asList("§6» §ePermet prendre ou poser dans l'eau dans un claim")),
	WATER_CAPSULE_EMPTY("water_capsule_empty", new ItemWaterCapsule(Blocks.air), Kronos.getKronos().getTabsDivers(), Arrays.asList("§6» §ePermet prendre ou poser dans l'eau dans un claim")),
	
	GALAAD_SWORD("galaad_sword", new ItemSword(ToolMaterial.CELESTINE), Kronos.getKronos().getTabsCombat(), Arrays.asList("§6» §eDispose de §6looting 3§e automatiquement !")),
	
	SOUL_OF_ANGELIC_SPAWNER("soul_of_angelic_spawner", new Item().setMaxStackSize(1), Kronos.getKronos().getTabsDivers(), Arrays.asList("§6» §ePermet d'augmenter le nombre de mob d'un spawner")),
	
	SOUL_OF_DEMONIC_SPAWNER("soul_of_demonic_spawner", new Item().setMaxStackSize(1), Kronos.getKronos().getTabsDivers(), Arrays.asList("§6» §ePermet de changer le type de spawner aléatoirement")),
	
	POWER_OF_HADES("power_of_hades", new Item().setMaxStackSize(1), Kronos.getKronos().getTabsDivers(), Arrays.asList("§6» §ePermet de multiplier par §61.5§e le gain d'expérience")),
	
	HAMMER_OF_HESTIA("hammer_of_hestia", new ItemHammer(ToolMaterial.EMERALD), Kronos.getKronos().getTabsOutils(), Arrays.asList("§6» §ePermet de casser les blocs de §6stone §eet §6cobble §esur du §63x3§e !")),

	CHILLI_PEPPER_ITEM("chilli_pepper_item", new ItemChilliPepper(4, 0.5F, false), Kronos.getKronos().getTabsBouffe()),
	
	POWER_OF_HERMES("power_of_hermes", new Item().setMaxStackSize(1), Kronos.getKronos().getTabsDivers(), Arrays.asList("§6» §ePermet de multiplier par §61.5§e le gain d'argent au §6/shop§e !")),
	
	FIRE_GRAPES("fire_grapes", new ItemFood(4, 0.5F, false).setAlwaysEdible().setPotionEffect(Potion.fireResistance.id, 3*60, 0, 1.0F), Kronos.getKronos().getTabsBouffe(), Arrays.asList("§6» §eDonne l'effet de résistance au feux pendant §63 §eminutes")),
	SPEED_GRAPES("speed_grapes", new ItemFood(4, 0.5F, false).setAlwaysEdible().setPotionEffect(Potion.moveSpeed.id, 3*60, 1, 1.0F), Kronos.getKronos().getTabsBouffe(), Arrays.asList("§6» §eDonne l'effet de vitesse II pendant §63 §eminutes")),
	STRENGHT_GRAPES("strenght_grapes", new ItemFood(4, 0.5F, false).setAlwaysEdible().setPotionEffect(Potion.damageBoost.id, 3*60, 1, 1.0F), Kronos.getKronos().getTabsBouffe(), Arrays.asList("§6» §eDonne l'effet de force II pendant §63 §eminutes")),
	
	BUNCH_OF_GRAPES("bunch_of_grapes", new ItemFood(4, 0.5F, false).setAlwaysEdible()
			.setPotionEffect(Potion.damageBoost.id, 8*60, 1, 1.0F).setPotionEffect(Potion.fireResistance.id, 8*60, 1, 1.0F).setPotionEffect(Potion.moveSpeed.id, 8*60, 1, 1.0F), 
			Kronos.getKronos().getTabsBouffe(), Arrays.asList("§6» §eDonne l'effet de force II pendant §68 §eminutes"
					,"§6» §eDonne l'effet de vitesse II pendant §68 §eminutes",
					"§6» §eDonne l'effet de résistance au feux pendant §68 §eminutes")),
	
	 CELENITE_BOW("celenite_bow", new ItemCeleniteBow(), Kronos.getKronos().getTabsCombat()),
	 MALACHITE_BOW("malachite_bow", new ItemMalachiteBow(), Kronos.getKronos().getTabsCombat()),
	 CELESTINE_BOW("celestine_bow", new ItemCelestineBow(), Kronos.getKronos().getTabsCombat()),
	 ANGELITE_BOW("angelite_bow", new ItemAngeliteBow(), Kronos.getKronos().getTabsCombat(),  Arrays.asList("§6» §eL'enchantement §6infinity§e ne marche pas sur cet arc !")),
	
	 ANGELITE_ARROW("angelite_arrow", new Item(), Kronos.getKronos().getTabsDivers(), Arrays.asList("§6» §eFlèche pour utiliser l'arc en angélite")),
	
	 APOLON_BOW("apalon_bow", new ItemApolonBow(), Kronos.getKronos().getTabsCombat(),  Arrays.asList("§6» §eSeul arc qui peut avoir l'enchantement §6punch§e !")),
	 
	 KUNEES_OF_HADES("kunees_of_hades", new Item().setMaxDamage(3), Kronos.getKronos().getTabsDivers(), Arrays.asList("§6» §eEnlève vos effets de potion avec clique gauche"
			 ,"§6» §eQuand vous taper un joueur ça lui retire ses effets de potions")),
	 
	 GOD_PEARL("god_pearl", new ItemGodPearl(), Kronos.getKronos().getTabsDivers()),
	 
	 BAGUETTE_XRAY("baguette_xray", new Item().setMaxDamage(20).setMaxStackSize(1), Kronos.getKronos().getTabsDivers(), Arrays.asList("§6» §ePermet de créer une zone de 3x3 pour xray")),
	 
	 BAN_SWORD("ban_sword", new ItemSword(ToolMaterial.EMERALD), Kronos.getKronos().getTabsCombat()),
	 
	 
	 
	 
	;
	
	private final String name;
	private final Item item;
	private final CreativeTabs tabs;
	private IItemRenderer render;
	private List<String> informations;
	
	private Items(String name, Item item, CreativeTabs tabs)
	{
		this.name = name;
		this.item = item;
		this.tabs = tabs;
	}
	
	private Items(String name, Item item, CreativeTabs tabs, List<String> informations)
	{
		this.name = name;
		this.item = item;
		this.tabs = tabs;
		this.informations = informations;
	}
	
	private Items(String name, Item item, IItemRenderer render, CreativeTabs tabs)
	{
		this(name, item, tabs);		
		this.render = render;
	}
	
	public String getName()
	{
		return name;
	}
	
	public Item getItem()
	{
		return item;
	}
	
	public IItemRenderer getRender()
	{
		return render;
	}
	
	public boolean hasRender()
	{
		return render != null;
	}
	
	public CreativeTabs getTabs() {
		return tabs;
	}
	
	public List<String> getInformations() {
		return informations;
	}
	
	public boolean hasInformation(){
		return informations != null;
	}
	
}
