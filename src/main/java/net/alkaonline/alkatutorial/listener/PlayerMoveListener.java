package net.alkaonline.alkatutorial.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerMoveListener implements Listener {

    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        if (!event.getPlayer().hasPermission("tutorial.complete")) {
            event.setCancelled(true);
        }
    }

}
