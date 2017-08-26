package net.alkaonline.alkatutorial.event;

import net.alkaonline.alkatutorial.PluginInterface.PluginInterface;
import net.alkaonline.alkatutorial.tutorialstr.TutorialStr;
import net.milkbowl.vault.permission.Permission;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.RegisteredServiceProvider;

import com.salk_coding.tutorial.Main;

public class PlayerJoin implements Listener,PluginInterface {

    /*private static Location STARTLOC = new Location(ConfigManager.getWorld(),682,110,-42);
    private static Location FIRSTLOC = new Location(ConfigManager.getWorld(),682,95,90);
    private static Location SECONDLOC = new Location(ConfigManager.getWorld(),620,95,90);*/


	public PlayerJoin() {
		/*STARTLOC.setYaw(34);
		STARTLOC.setPitch(16);
		FIRSTLOC.setYaw(130);
		FIRSTLOC.setPitch(23);*/
	}
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event){
    	//BlockTutorial blocktutorial = new BlockTutorial(event.getPlayer(), Spectate.randomPlayerLocation());
    	//blocktutorial.spawnItemOnBlock(3, new ItemStack(Material.DIAMOND));
		if(!event.getPlayer().hasPermission(AlkaPermission)){
			TutorialStr ts = new TutorialStr(event.getPlayer());
			ts.start();
			//Spectate spe = new Spectate(event.getPlayer()/*, 0.25, STARTLOC, FIRSTLOC, SECONDLOC*/);
			//spe.start();
		}
		else{
			acceptPermissions(event.getPlayer());
		}
	}
	
	public static void acceptPermissions(Player player){
    	try{
    		Permission per = null;
    		RegisteredServiceProvider<Permission> permissionProvider = Main.getInstance().getServer().getServicesManager().getRegistration(Permission.class);
        	if (permissionProvider != null) {
        		per = (Permission)permissionProvider.getProvider();
        	}
        	per.playerRemoveGroup(player, per.getPrimaryGroup(player));
        	//per.playerAddGroup("world", player, "user");
        	for(World world:Bukkit.getWorlds()){
        		String worldname = world.getWorldFolder().getName();
        		per.playerAddGroup(worldname, player, "user");
        	}
    	}
    	catch(UnsupportedOperationException e){
    		System.out.println("[AlkaTutorial] 펄미션이 없습니다.");
    	}
	}
	
}
