package com.salk_coding.tutorial;

import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import net.alkaonline.alkatutorial.PluginInterface.PluginInterface;
import net.alkaonline.alkatutorial.config.ConfigManager;
import net.alkaonline.alkatutorial.event.PlayerChat;
import net.alkaonline.alkatutorial.event.PlayerJoin;
import net.alkaonline.alkatutorial.event.PlayerMove;

public class Main extends JavaPlugin implements PluginInterface {

	private static Main instance;

	public static Main getInstance() {
		if (instance == null) {
			throw new IllegalStateException("AlkaTutorial not initialized!");
		} else {
			return instance;
		}
	}


	public Main() {
		if (instance == null) {
			instance = this;
		} else {
			throw new IllegalStateException("This class is singleton.");
		}
	}

	@Override
	public void onEnable() {
		ConfigManager.loadConfig();
		Bukkit.getServer().getPluginManager().registerEvents(new PlayerMove(), this);
		Bukkit.getServer().getPluginManager().registerEvents(new PlayerJoin(), this);
		Bukkit.getServer().getPluginManager().registerEvents(new PlayerChat(), this);
	}
	
	@Override
	public void onDisable(){
		try {
			ConfigManager.saveConfig();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
