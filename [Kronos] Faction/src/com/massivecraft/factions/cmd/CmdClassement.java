package com.massivecraft.factions.cmd;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.massivecraft.factions.Faction;
import com.massivecraft.factions.Factions;

public class CmdClassement extends FCommand{

	public CmdClassement() {
		this.aliases.add("classement");
		this.aliases.add("cl");
	}

	public static Map<String, Integer> map = new HashMap<String, Integer>();
	
	@Override
	public void perform() {
		Factions.i.refreshClassement();
		
		if (Factions.i.get() != null){
			for (Faction f : Factions.i.get()){
				//if (!f.getTag().equalsIgnoreCase("§2Wilderness") || !f.getTag().equalsIgnoreCase("Warzone") || !f.getTag().equalsIgnoreCase("SafeZone")){
					map.put(f.getTag(), Integer.valueOf(f.getClassementPoint()));
				//}
			}
			
			List<Map.Entry<String, Integer>> entries = new ArrayList<Entry<String, Integer>>(map.entrySet());
			Collections.sort(entries, new Comparator<Entry<String, Integer>>() {
				public int compare(final Entry<String, Integer> e1, final Entry<String, Integer> e2) {
					return e2.getValue().compareTo(e1.getValue());
				}
			});
			int current = 1;
			int size = map.size();
			msg("§e§m--|----------------§e[§6 Classement §e]§e§m----------------|--");
			if(size == 1 || size == 2 || size == 3 || size == 4 || size == 5 || size == 6 || size == 7 || size == 8 || size == 9 || size == 10){
			    for (Entry<String, Integer> entry : entries) {
			    	msg("§e                      Top §6"+current+"§7 - §c"+entry.getKey()+" §6: "+entry.getValue()+" §epoint(s)");
			    	current++;
			    }
			    msg("§e§m--|----------------§e[§6 Classement §e]§e§m----------------|--");
			}else{
				for (Entry<String, Integer> entry : entries) {
					if(current == 1 || current == 2 || current == 3 || current == 4 || current == 5 || current == 6 || current == 7 || current == 8 || current == 9 || current == 10){
						msg("§e                      Top §6"+current+"§7 - §c"+entry.getKey()+" §6: "+entry.getValue()+" §epoint(s)");					
						current++;
			      	}
			    }
			    msg("§e§m--|----------------§e[§6 Classement §e]§e§m----------------|--");
			}
		}
		return;			
	}

	
	
}
