package fr.maxlego08.core.utils;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.YamlConfiguration;

import fr.maxlego08.core.utils.ZPlugin.LogType;
import net.minecraft.util.org.apache.commons.io.FileExistsException;

public abstract class ConfigUtils {

	
	protected void createDefaultFolder(){
		if (!ZPlugin.z().getDataFolder().exists()) ZPlugin.z().getDataFolder().mkdirs();
	}
	
	protected YamlConfiguration getConfig(String name, String path) throws FileExistsException{
		File file = new File(path+"/"+name);
		if (!file.exists()) throw new FileExistsException("File " + name + " doesn't existe !");
		return YamlConfiguration.loadConfiguration(file);
	}
	
	protected File getFile(String name, String path){
		return new File(path+"/"+name);
	}
	
	protected boolean existe(String name, String path){
		return new File(path+"/"+name).exists();
	}
	
	protected boolean createFile(String path, String name){
		if (!existe(name, path)){
			File file = new File(path+"/"+name);
			try {
				file.createNewFile();
				ZPlugin.z().log("File " + path + "\\"+name+" create !", LogType.SUCCESS);
			} catch (IOException e) {
				ZPlugin.z().log("Error when creating file " + e.getMessage() , LogType.ERROR);
				return false;
			}
		}
		return false;
	}
	
	protected String getDefaultFolder(){
		return ZPlugin.z().getDataFolder().getPath();
	}
	
	protected boolean save(String name, String path, YamlConfiguration config){
		try {
			config.save(getFile(name, path));return true;
		} catch (IOException e) {return false;}
	}
	
	protected String changeLocationToString(Location location){String ret = location.getWorld().getName() + "," + location.getBlockX() + "," + location.getBlockY() + "," + location.getBlockZ();return ret;}	
	protected Location changeStringToLocation(String s){String[] a = s.split(",");World w = Bukkit.getServer().getWorld(a[0]);float x = Float.parseFloat(a[1]); float y = Float.parseFloat(a[2]);float z = Float.parseFloat(a[3]); return new Location(w, x, y, z);}
	
}
