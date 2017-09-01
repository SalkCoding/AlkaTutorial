package net.alkaonline.alkatutorial.listener;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class PlayerChatListener implements Listener {

    @EventHandler(priority = EventPriority.LOWEST)
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (!(player.hasPermission("tutorial.complete"))) {
                event.getRecipients().remove(player);
            }
        }
    }

}
