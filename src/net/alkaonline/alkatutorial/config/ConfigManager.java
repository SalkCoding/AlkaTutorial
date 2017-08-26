package net.alkaonline.alkatutorial.config;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.configuration.file.YamlConfiguration;

import com.salk_coding.tutorial.Main;

public class ConfigManager {
	
	
	private static World world = null;
	/*private static Location first = null;
	private static Location second= null;
	private static Location third = null;*/
	
	public static World getWorld(){
		return world;
	}
	
	public static void saveConfig() throws IOException {
		File dir = Main.getInstance().getDataFolder();
		if (!(dir.exists())) {
			dir.mkdirs();
		}
		File file = new File(Main.getInstance().getDataFolder() + "/config.yml");
		YamlConfiguration config = YamlConfiguration.loadConfiguration(file);
		if (!(file.exists())) {
			try {
				file.createNewFile();
				config.set("World", "world");
				config.save(file);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		config.set("World", world.getName());
		/*config.set("First.x", first.getX());
		config.set("First.y", first.getY());
		config.set("First.z", first.getZ());
		
		config.set("second.x", second.getX());
		config.set("second.y", second.getY());
		config.set("second.z", second.getZ());
		
		config.set("third.x", third.getX());
		config.set("third.y", third.getY());
		config.set("third.z", third.getZ());*/
		
		config.save(file);
	}

	public static void loadConfig() {
		
		File dir = Main.getInstance().getDataFolder();
		if (!(dir.exists())) {
			dir.mkdirs();
		}
		
		File file = new File(Main.getInstance().getDataFolder() + "/config.yml");
		YamlConfiguration config = YamlConfiguration.loadConfiguration(file);
		if (!(file.exists())) {
			try {
				file.createNewFile();
				config.set("World", "world");
				config.save(file);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		world = Bukkit.getWorld(config.getString("World"));
		
	}
	
}
