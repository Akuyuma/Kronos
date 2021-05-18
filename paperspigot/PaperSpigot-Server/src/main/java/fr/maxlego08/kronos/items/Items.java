package fr.maxlego08.kronos.items;

import java.util.Arrays;

import fr.maxlego08.kronos.Kronos;
import net.minecraft.server.Blocks;
import net.minecraft.server.CreativeModeTab;
import net.minecraft.server.EnumArmorMaterial;
import net.minecraft.server.EnumToolMaterial;
import net.minecraft.server.Item;
import net.minecraft.server.ItemArmor;
import net.minecraft.server.ItemAxe;
import net.minecraft.server.ItemBucket;
import net.minecraft.server.ItemFood;
import net.minecraft.server.ItemHoe;
import net.minecraft.server.ItemPickaxe;
import net.minecraft.server.ItemSpade;
import net.minecraft.server.ItemSword;
import net.minecraft.server.MobEffectList;

public enum Items {

	ANGELITE("angelite", new Item(), CreativeModeTab.tabsDivers),
	ANGELITE_INGOT("angelite_ingot", new Item(), CreativeModeTab.tabsDivers),
	CELESTINE("celestine", new Item(), CreativeModeTab.tabsDivers),
	MALACHITE("malachite", new Item(), CreativeModeTab.tabsDivers),
	CELENITE("celenite", new Item(), CreativeModeTab.tabsDivers),
	
	ANGELITE_PICKAXE("angelite_pickaxe", new ItemPickaxe(EnumToolMaterial.ANGELITE),CreativeModeTab.tabsOutil),
	ANGELITE_AXE("angelite_axe", new ItemAxe(EnumToolMaterial.ANGELITE) ,CreativeModeTab.tabsOutil),
	ANGELITE_HOE("angelite_hoe", new ItemHoe(EnumToolMaterial.ANGELITE),CreativeModeTab.tabsOutil),
	ANGELITE_SHOVEL("angelite_shovel", new ItemSpade(EnumToolMaterial.ANGELITE),CreativeModeTab.tabsOutil),
	ANGELITE_MULTITOOLS("angelite_multitools", new ItemMultiTools(EnumToolMaterial.ANGELITE),CreativeModeTab.tabsOutil),
	
	CELESTINE_PICKAXE("celestine_pickaxe", new ItemPickaxe(EnumToolMaterial.CELESTINE),CreativeModeTab.tabsOutil),
	CELESTINE_AXE("celestine_axe", new ItemAxe(EnumToolMaterial.CELESTINE),CreativeModeTab.tabsOutil),
	CELESTINE_HOE("celestine_hoe", new ItemHoe(EnumToolMaterial.CELESTINE),CreativeModeTab.tabsOutil),
	CELESTINE_SHOVEL("celestine_shovel", new ItemSpade(EnumToolMaterial.CELESTINE),CreativeModeTab.tabsOutil),
	CELESTINE_MULTITOOLS("celestine_multitools", new ItemMultiTools(EnumToolMaterial.ANGELITE),CreativeModeTab.tabsOutil),
	
	MALACHITE_PICKAXE("malachite_pickaxe", new ItemPickaxe(EnumToolMaterial.MALACHITE),CreativeModeTab.tabsOutil),
	MALACHITE_AXE("malachite_axe", new ItemAxe(EnumToolMaterial.MALACHITE),CreativeModeTab.tabsOutil),
	MALACHITE_HOE("malachite_hoe", new ItemHoe(EnumToolMaterial.MALACHITE),CreativeModeTab.tabsOutil),
	MALACHITE_SHOVEL("malachite_shovel", new ItemSpade(EnumToolMaterial.MALACHITE),CreativeModeTab.tabsOutil),
	MALACHITE_MULTITOOLS("malachite_multitools", new ItemMultiTools(EnumToolMaterial.ANGELITE),CreativeModeTab.tabsOutil),
	
	CELENITE_PICKAXE("celenite_pickaxe", new ItemPickaxe(EnumToolMaterial.CELENITE),CreativeModeTab.tabsOutil),
	CELENITE_AXE("celenite_axe", new ItemAxe(EnumToolMaterial.CELENITE),CreativeModeTab.tabsOutil),
	CELENITE_HOE("celenite_hoe", new ItemHoe(EnumToolMaterial.CELENITE),CreativeModeTab.tabsOutil),
	CELENITE_SHOVEL("celenite_shovel", new ItemSpade(EnumToolMaterial.CELENITE),CreativeModeTab.tabsOutil),	
	CELENITE_MULTITOOLS("celenite_multitools", new ItemMultiTools(EnumToolMaterial.ANGELITE), CreativeModeTab.tabsOutil),	
	
	ANGELITE_SWORD("angelite_sword", new ItemSword(EnumToolMaterial.ANGELITE), CreativeModeTab.tabsCombat),
	CELESTINE_SWORD("celestine_sword", new ItemSword(EnumToolMaterial.CELESTINE), CreativeModeTab.tabsCombat),
	MALACHITE_SWORD("malachite_sword", new ItemSword(EnumToolMaterial.MALACHITE), CreativeModeTab.tabsCombat),
	CELENITE_SWORD("celenite_sword", new ItemSword(EnumToolMaterial.CELENITE), CreativeModeTab.tabsCombat),
	
	ANGELITE_HELMET("angelite_helmet", new ItemArmor(EnumArmorMaterial.ANGELITE, 5, 0), CreativeModeTab.tabsCombat),
	ANGELITE_CHESTPLATE("angelite_chestplate", new ItemArmor(EnumArmorMaterial.ANGELITE, 5, 1), CreativeModeTab.tabsCombat),
	ANGELITE_LEGGINGS("angelite_leggings", new ItemArmor(EnumArmorMaterial.ANGELITE, 5, 2), CreativeModeTab.tabsCombat),
	ANGELITE_BOOTS("angelite_boots", new ItemArmor(EnumArmorMaterial.ANGELITE, 5, 3), CreativeModeTab.tabsCombat),
	
	CELESTINE_HELMET("celestine_helmet", new ItemArmor(EnumArmorMaterial.CELESTINE, 6, 0), CreativeModeTab.tabsCombat),
	CELESTINE_CHESTPLATE("celestine_chestplate", new ItemArmor(EnumArmorMaterial.CELESTINE, 6, 1), CreativeModeTab.tabsCombat),
	CELESTINE_LEGGINGS("celestine_leggings", new ItemArmor(EnumArmorMaterial.CELESTINE, 6, 2), CreativeModeTab.tabsCombat),
	CELESTINE_BOOTS("celestine_boots", new ItemArmor(EnumArmorMaterial.CELESTINE, 6, 3), CreativeModeTab.tabsCombat),
	
	MALACHITE_HELMET("malachite_helmet", new ItemArmor(EnumArmorMaterial.MALACHITE, 7, 0), CreativeModeTab.tabsCombat),
	MALACHITE_CHESTPLATE("malachite_chestplate", new ItemArmor(EnumArmorMaterial.MALACHITE, 7, 1), CreativeModeTab.tabsCombat),
	MALACHITE_LEGGINGS("malachite_leggings", new ItemArmor(EnumArmorMaterial.MALACHITE, 7, 2), CreativeModeTab.tabsCombat),
	MALACHITE_BOOTS("malachite_boots", new ItemArmor(EnumArmorMaterial.MALACHITE, 7, 3), CreativeModeTab.tabsCombat),
	
	CELENITE_HELMET("celenite_helmet", new ItemArmor(EnumArmorMaterial.CELENITE, 8, 0), CreativeModeTab.tabsCombat),
	CELENITE_CHESTPLATE("celenite_chestplate", new ItemArmor(EnumArmorMaterial.CELENITE, 8, 1), CreativeModeTab.tabsCombat),
	CELENITE_LEGGINGS("celenite_leggings", new ItemArmor(EnumArmorMaterial.CELENITE, 8, 2), CreativeModeTab.tabsCombat),
	CELENITE_BOOTS("celenite_boots", new ItemArmor(EnumArmorMaterial.CELENITE, 8, 3), CreativeModeTab.tabsCombat),	

	MALEFICIENT_SCYTHE("maleficent_scythe", new ItemSword(EnumToolMaterial.SCYTHE), CreativeModeTab.tabsCombat),	
	
	BLADES_OF_ARES("blades_of_ares", new ItemBladesOfAres(EnumToolMaterial.ARES), CreativeModeTab.tabsCombat),	
	
	POSITIVE_GRAPES("positive_grapes", new ItemFood(4, 0.5F, false).j(),  CreativeModeTab.tabsCombat),
	
	GOLDEN_GRAPES("golden_grapes", new ItemFood(4, 0.5F, false).j(),  CreativeModeTab.tabsCombat),	
	
	HEPHAISTOS_GRAPES("hephaistos_grapes", new ItemHephaistosGrapes(4, 0.5F, false).j(),  CreativeModeTab.tabsCombat),	
	
	FARMTOOLS("farmtools", new ItemFarmTools(EnumToolMaterial.CELENITE), CreativeModeTab.tabsCombat),	
	
	CREEPER_DIVIN("creeper_divin", new ItemCreeperDivin() , CreativeModeTab.tabsDivers),	
	
	EYE_OF_GODS("eye_of_gods", new ItemEyeOfGod() , CreativeModeTab.tabsDivers),	
	
	HEART_OF_CASSANDRE("heart_of_cassandre", new ItemHeartOfCassandre() , CreativeModeTab.tabsDivers),	
	
	DYNAMITE("dynamite", new ItemDynamite(), CreativeModeTab.tabsDivers),	
	
	TARTARE_KEY("tartare_key", new Item(), CreativeModeTab.tabsDivers),	
	SKY_KEY("sky_key", new Item(), CreativeModeTab.tabsDivers),	
	
	STICK_OF_GODS("stick_of_god", new ItemStickOfGods(),CreativeModeTab.tabsDivers),	
	
	STICK_OF_TARTARE("stick_of_tartare", new ItemStickOfTartare(), CreativeModeTab.tabsDivers),
	
	WATER_CAPSULE("water_capsule", new ItemWaterCapsule(Blocks.WATER), CreativeModeTab.tabsDivers),
	WATER_CAPSULE_EMPTY("water_capsule_empty", new ItemWaterCapsule(Blocks.AIR), CreativeModeTab.tabsDivers),
	
	GALAAD_SWORD("galaad_sword", new ItemSword(EnumToolMaterial.CELESTINE), CreativeModeTab.tabsCombat),
	
	SOUL_OF_ANGELIC_SPAWNER("soul_of_angelic_spawner", new Item(), CreativeModeTab.tabsDivers),
	
	SOUL_OF_DEMONIC_SPAWNER("soul_of_demonic_spawner", new Item(), CreativeModeTab.tabsDivers),
	
	POWER_OF_HADES("power_of_hades", new Item().e(1), CreativeModeTab.tabsDivers),
	
	HAMMER_OF_HESTIA("power_of_hades", new ItemHammer(EnumToolMaterial.DIAMOND), CreativeModeTab.tabsOutil),
	
	CHILLI_PEPPER_ITEM("chilli_pepper_item", new ItemChilliPepper(), CreativeModeTab.tabsDivers),
	
	POWER_OF_HERMES("power_of_hermes", new Item().e(1), CreativeModeTab.tabsDivers),
	
	FIRE_GRAPES("fire_grapes", new ItemFood(4, 0.5F, false).j().a(MobEffectList.FIRE_RESISTANCE.id, 3*60, 0, 1.0F), CreativeModeTab.tabsCombat),
	SPEED_GRAPES("speed_grapes", new ItemFood(4, 0.5F, false).j().a(MobEffectList.FASTER_MOVEMENT.id, 3*60, 1, 1.0F), CreativeModeTab.tabsCombat),
	STRENGHT_GRAPES("strenght_grapes", new ItemFood(4, 0.5F, false).j().a(MobEffectList.INCREASE_DAMAGE.id, 3*60, 1, 1.0F), CreativeModeTab.tabsCombat),
	BUNCH_OF_GRAPES("bunch_of_grapes", new ItemBunch(4, 0.5F, false).j(), CreativeModeTab.tabsCombat),
	
	
	CELENITE_BOW("celenite_bow", new ItemCeleniteBow(), CreativeModeTab.tabsCombat),
	MALACHITE_BOW("malachite_bow", new ItemMalachiteBow(), CreativeModeTab.tabsCombat),
	CELESTINE_BOW("celestine_bow", new ItemCelestineBow(), CreativeModeTab.tabsCombat),
	ANGELITE_BOW("angelite_bow", new ItemAngeliteBow(), CreativeModeTab.tabsCombat),
	
	ANGELITE_ARROW("angelite_arrow", new Item(), CreativeModeTab.tabsCombat),
	
	APOLON_BOW("apalon_bow", new ItemApolonBow(), CreativeModeTab.tabsCombat),
	
	KUNEES_OF_HADES("kunees_of_hades", new Item().e(3), CreativeModeTab.tabsDivers),
	
	
	GOD_PEARL("god_pearl", new ItemGodPearl(), CreativeModeTab.tabsDivers),
	
	BAGUETTE_XRAY("baguette_xray", new Item().e(1).setMaxDurability(20), CreativeModeTab.tabsDivers),
	 
	BAN_SWORD("ban_sword", new ItemSword(EnumToolMaterial.DIAMOND), CreativeModeTab.tabsCombat);
	
	;
	
	private final String name;
	private final Item item;
	private final CreativeModeTab tabs;
	
	private Items(String name, Item item, CreativeModeTab tabs)
	{
		this.name = name;
		this.item = item;
		this.tabs = tabs;
	}
	
	public String getName()
	{
		return name;
	}
	
	public Item getItem()
	{
		return item;
	}
	
	public CreativeModeTab getTabs() {
		return tabs;
	}
	
}
