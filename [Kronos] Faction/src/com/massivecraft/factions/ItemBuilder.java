package com.massivecraft.factions;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

public class ItemBuilder {

    public static ItemStack getCreatedItemWithLore(Material Material, int Number, String Name, List<String> Lore) {
        ItemStack item = new ItemStack(Material, Number);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(Name);
        meta.setLore(Lore);
        item.setItemMeta(meta);
        return item;
    }
    
    public static ItemStack getCreatedItemWithLoreAndShort(Material Material, int Number, short data, String Name, List<String> Lore, int chance) {
        ItemStack item = new ItemStack(Material, Number, data);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(Name);
        List<String> lo = new ArrayList<String>();
        for (String a : Lore){
        	lo.add(a.replace("&", "§").replace("%custom_arrrows%", "»").replace("%chance%", chance+"%"));
        }
        meta.setLore(lo);
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack getCreatedItemWithEnchantementAndLore(Material Material, int Number, String Name, Enchantment Enchant, int EnchantLevel, boolean Visibility, List<String> Lore) {
        ItemStack item = new ItemStack(Material, Number);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(Name);
        meta.addEnchant(Enchant, EnchantLevel, Visibility);
        meta.setLore(Lore);
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack getCreatedItemWithEnchantementAndLore2(Material Material, int Number, String Name, Enchantment Enchant2, int EnchantLevel2,Enchantment Enchant, int EnchantLevel, boolean Visibility, List<String> Lore) {
        ItemStack item = new ItemStack(Material, Number);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(Name);
        meta.addEnchant(Enchant, EnchantLevel, Visibility);
        meta.addEnchant(Enchant2, EnchantLevel2, Visibility);
        meta.setLore(Lore);
        item.setItemMeta(meta);
        return item;
    }    
    
    // Gestions des têtes
    public static ItemStack getCreatedSkullPlayer(String Player) {
        ItemStack item = new ItemStack(Material.SKULL_ITEM, 1, (short) 0);
        SkullMeta meta = (SkullMeta)item.getItemMeta();
        meta.setOwner(Player);
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack getCreatedSkullPlayerWithLore(String Player, List<String> Lore) {
        ItemStack item = new ItemStack(Material.SKULL_ITEM, 1, (short) 0);
        SkullMeta meta = (SkullMeta)item.getItemMeta();
        meta.setOwner(Player);
        meta.setLore(Lore);
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack getCreatedSkullPlayerWithName(String Player, String Name) {
        ItemStack item = new ItemStack(Material.SKULL_ITEM, 1, (short) 0);
        SkullMeta meta = (SkullMeta)item.getItemMeta();
        meta.setOwner(Player);
        meta.setDisplayName(Name);
        item.setItemMeta(meta);
        return item;
    }

    @SuppressWarnings("deprecation")
	public static ItemStack getCreatedSkullPlayerWithLoreAndName(String Player, String Name ,List<String> Lore) {
        ItemStack item = new ItemStack(Material.SKULL_ITEM, 1, (short) 0, Byte.valueOf((byte) 3));
        SkullMeta meta = (SkullMeta)item.getItemMeta();
        meta.setOwner(Player);
        meta.setDisplayName(Name);
        meta.setLore(Lore);
        item.setItemMeta(meta);
        return item;
    }
}
