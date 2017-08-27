package net.alkaonline.alkatutorial.listener;

import net.alkaonline.alkatutorial.tutorialstr.TutorialStr;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {

    /*private static Location STARTLOC = new Location(ConfigManager.getWorld(),682,110,-42);
    private static Location FIRSTLOC = new Location(ConfigManager.getWorld(),682,95,90);
    private static Location SECONDLOC = new Location(ConfigManager.getWorld(),620,95,90);*/


    public PlayerJoinListener() {
        /*STARTLOC.setYaw(34);
        STARTLOC.setPitch(16);
		FIRSTLOC.setYaw(130);
		FIRSTLOC.setPitch(23);*/
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        //BlockTutorial blocktutorial = new BlockTutorial(event.getPlayer(), Spectate.randomPlayerLocation());
        //blocktutorial.spawnItemOnBlock(3, new ItemStack(Material.DIAMOND));
        if (!event.getPlayer().hasPermission("tutorial.complete")) {
            TutorialStr ts = new TutorialStr(event.getPlayer());
            ts.start();
            //Spectate spe = new Spectate(event.getPlayer()/*, 0.25, STARTLOC, FIRSTLOC, SECONDLOC*/);
            //spe.start();
        } else {
            addPermission(event.getPlayer());
        }
    }

    public static void addPermission(Player player) {
        Permission per = Bukkit.getServicesManager().getRegistration(Permission.class).getProvider();
        per.playerRemoveGroup(player, per.getPrimaryGroup(player));
        //per.playerAddGroup("world", player, "user");
        for (World world : Bukkit.getWorlds()) {
            per.playerAddGroup(world.getName(), player, "user");
        }
    }

}
