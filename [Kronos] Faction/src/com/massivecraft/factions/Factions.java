package com.massivecraft.factions;

import java.io.File;
import java.lang.reflect.Type;
import java.util.*;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.logging.Level;

import org.bukkit.ChatColor;

import org.bukkit.craftbukkit.libs.com.google.gson.reflect.TypeToken;
import com.massivecraft.factions.util.MiscUtil;
import com.massivecraft.factions.zcore.persist.EntityCollection;
import com.massivecraft.factions.zcore.util.TextUtil;

public class Factions extends EntityCollection<Faction>
{
	public static Factions i = new Factions();
	
	P p = P.p;
	
	private Factions()
	{
		super
		(
			Faction.class,
			new CopyOnWriteArrayList<Faction>(),
			new ConcurrentHashMap<String, Faction>(),
			new File(P.p.getDataFolder(), "factions.json"),
			P.p.gson
		);
	}
	
	@Override
	public Type getMapType()
	{
		return new TypeToken<Map<String, Faction>>(){}.getType();
	}
	
	@Override
	public boolean loadFromDisc()
	{
		if ( ! super.loadFromDisc()) return false;
		
		// Make sure the default neutral faction exists
		if ( ! this.exists("0"))
		{
			Faction faction = this.create("0");
			faction.setTag(ChatColor.DARK_GREEN+"Wilderness");
			faction.setDescription("");
		}
		
		// Make sure the safe zone faction exists
		if ( ! this.exists("-1"))
		{
			Faction faction = this.create("-1");
			faction.setTag("SafeZone");
			faction.setDescription("Une zone sans aucun danger");
		}
		else
		{
			// if SafeZone has old pre-1.6.0 name, rename it to remove troublesome " "
			Faction faction = this.getSafeZone();
			if (faction.getTag().contains(" "))
				faction.setTag("SafeZone");
		}
		
		// Make sure the war zone faction exists
		if ( ! this.exists("-2"))
		{
			Faction faction = this.create("-2");
			faction.setTag("WarZone");
			faction.setDescription("Vous devriez faire attention cet endroit est dangereux");
		}
		else
		{
			// if WarZone has old pre-1.6.0 name, rename it to remove troublesome " "
			Faction faction = this.getWarZone();
			if (faction.getTag().contains(" "))
				faction.setTag("WarZone");
		}

		// populate all faction player lists
		for (Faction faction : i.get())
		{
			faction.refreshFPlayers();
		}
		refreshClassement();
		return true;
	}
	
	
	//Classement faction
	
	public static Map<String, Integer> map = new HashMap<String, Integer>();
	
	public void refreshClassement(){
		if (i.get() != null){
			for (Faction f : i.get()){
			//	if (!f.getId().equalsIgnoreCase("0") || !f.getId().equalsIgnoreCase("-2") || !f.getId().equalsIgnoreCase("-1")){
					map.put(f.getId(), Integer.valueOf(f.getClassementPoint()));
				//}
			}
			
			List<Map.Entry<String, Integer>> entries = new ArrayList<Entry<String, Integer>>(map.entrySet());
			Collections.sort(entries, new Comparator<Entry<String, Integer>>() {
				public int compare(final Entry<String, Integer> e1, final Entry<String, Integer> e2) {
					return e2.getValue().compareTo(e1.getValue());
				}
			});
			int current = 1;
		    for (Entry<String, Integer> entry : entries) {
		    	Faction f = i.get(entry.getKey());
		    	if (f != null){
		    		f.setClassementPosition(current);
		    		current++;
		    	}
		    }
		}
		return;	
		
	}	
	
	
	
	//----------------------------------------------//
	// GET
	//----------------------------------------------//
	
	@Override
	public Faction get(String id)
	{
		if ( ! this.exists(id))
		{
			p.log(Level.WARNING, "Non existing factionId "+id+" requested! Issuing cleaning!");
			Board.clean();
			FPlayers.i.clean();
		}
		
		return super.get(id);
	}
	
	public Faction getNone()
	{
		return this.get("0");
	}
	
	public Faction getSafeZone()
	{
		return this.get("-1");
	}
	
	public Faction getWarZone()
	{
		return this.get("-2");
	}
	
	
	//----------------------------------------------//
	// Faction tag
	//----------------------------------------------//
	
	public static ArrayList<String> validateTag(String str)
	{
		ArrayList<String> errors = new ArrayList<String>();
		
		if(MiscUtil.getComparisonString(str).length() < Conf.factionTagLengthMin)
		{
			errors.add(P.p.txt.parse("§6» <i>Le nom de la faction dois avoir au minimum <h>%s<i> caractères.", Conf.factionTagLengthMin));
		}
		
		if(str.length() > Conf.factionTagLengthMax)
		{
			errors.add(P.p.txt.parse("§6» <i>Le nom de la faction ne peux pas avoir plus de <h>%s<i> caractères.", Conf.factionTagLengthMax));
		}
		
		for (char c : str.toCharArray())
		{
			if ( ! MiscUtil.substanceChars.contains(String.valueOf(c)))
			{
				errors.add(P.p.txt.parse("§6» <i>Le nom de faction doit être alphanumérique.. \"<h>%s<i>\" n'est pas autorisé.", c));
			}
		}
		
		return errors;
	}
	
	public Faction getByTag(String str)
	{
		String compStr = MiscUtil.getComparisonString(str);
		for (Faction faction : this.get())
		{
			if (faction.getComparisonTag().equals(compStr))
			{
				return faction;
			}
		}
		return null;
	}
	
	public Faction getBestTagMatch(String searchFor)
	{
		Map<String, Faction> tag2faction = new HashMap<String, Faction>();
		
		// TODO: Slow index building
		for (Faction faction : this.get())
		{
			tag2faction.put(ChatColor.stripColor(faction.getTag()), faction);
		}
		
		String tag = TextUtil.getBestStartWithCI(tag2faction.keySet(), searchFor);
		if (tag == null) return null;
		return tag2faction.get(tag);
	}
	
	public boolean isTagTaken(String str)
	{
		return this.getByTag(str) != null;
	}

}
