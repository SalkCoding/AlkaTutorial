package net.alkaonline.alkatutorial.event;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import net.alkaonline.alkatutorial.PluginInterface.PluginInterface;

public class PlayerMove implements Listener,PluginInterface{

	@EventHandler
	public void onMove(PlayerMoveEvent event){
		if(!event.getPlayer().hasPermission(AlkaPermission)){
			event.setCancelled(true);
		}
	}
	
}
