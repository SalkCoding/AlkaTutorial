
package net.alkaonline.alkatutorial.event;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import net.alkaonline.alkatutorial.PluginInterface.PluginInterface;

public class PlayerChat implements Listener, PluginInterface {

	@EventHandler(priority = EventPriority.LOWEST)
	public void onPlayerChat(AsyncPlayerChatEvent event) {
		for (Player player : Bukkit.getOnlinePlayers()) {
			if (!(player.hasPermission(AlkaPermission))) {
				event.getRecipients().remove(player);
			}
		}
	}

}
