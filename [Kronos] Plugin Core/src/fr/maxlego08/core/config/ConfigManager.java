package fr.maxlego08.core.config;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;

import fr.maxlego08.core.Kronos;
import fr.maxlego08.core.command.commands.totem.data.TotemData;
import fr.maxlego08.core.utils.ConfigUtils;
import net.minecraft.util.org.apache.commons.io.FileExistsException;

public class ConfigManager extends ConfigUtils{
	
	public void createFiles(){
		
		createDefaultFolder();
		createFile(getDefaultFolder(), "obsidian.yml");
		createFile(getDefaultFolder(), "totem.yml");
		createFile(getDefaultFolder(), "Stotem.yml");
		createFile(getDefaultFolder(), "koth.yml");
		
	}
	
	public void saveObsidian(Map<Location, Integer> obsdians) throws FileExistsException{		
		YamlConfiguration config = getConfig("obsidian.yml", getDefaultFolder());
		for(Entry<Location, Integer> entry : obsdians.entrySet()){
			config.set("obsidian."+changeLocationToString(entry.getKey()), entry.getValue());
		}
		save("obsidian.yml", getDefaultFolder(), config);
	}
	
	public Map<Location, Integer> loadObisdian() throws FileExistsException{
		YamlConfiguration config = getConfig("obsidian.yml", getDefaultFolder());
		 Map<Location, Integer> obsdians = new HashMap<>();
		 if (config.contains("obsidian")){
			 for(String location : config.getConfigurationSection("obsidian.").getKeys(false)){
				 obsdians.put(changeStringToLocation(location), config.getInt("obsidian."+location));
			 }
		 }
		 
		return obsdians;
	}
	
	public void saveTotem(TotemData data) throws FileExistsException{
		YamlConfiguration config = getConfig("totem.yml", getDefaultFolder());
		if (data.getLocation() != null){
			config.set("location", data.z());
		}
		save("totem.yml", getDefaultFolder(), config);
	}
	
	public void loadTotem() throws FileExistsException{
		YamlConfiguration config = getConfig("totem.yml", getDefaultFolder());
		if (config.contains("location")){
			Kronos.k().getT().getTotem().w(config.getString("location"));
		}
		
	}
	
	public void saveSTotem(fr.maxlego08.core.command.commands.stotem.data.TotemData data) throws FileExistsException{
		YamlConfiguration config = getConfig("Stotem.yml", getDefaultFolder());
		if (data.getLocation() != null){
			config.set("location", data.z());
		}
		save("Stotem.yml", getDefaultFolder(), config);
	}
	
	public void loadSTotem() throws FileExistsException{
		YamlConfiguration config = getConfig("Stotem.yml", getDefaultFolder());
		if (config.contains("location")){
			Kronos.k().getTs().getTotem().w(config.getString("location"));
		}
		
	}
	
}
