package net.alkaonline.alkatutorial.flyingeffect;

import net.alkaonline.alkatutorial.PluginInterface.PluginInterface;
import net.alkaonline.alkatutorial.config.ConfigManager;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

public class Spectate implements PluginInterface{

    private final Player player;
    //private final Location start;
    //private final Location[] targets;
    //private double speed;
    //private float yaw;
    private BukkitTask task;

   // private int currentTargetIndex = 0;

    public Spectate(Player player/*, double speed, Location start, Location... targets*/){
        this.player = player;
        //this.start = start;
        //this.targets = targets;
        //this.speed = speed;
    }


    public void start() {
        player.setGameMode(GameMode.SPECTATOR);

        //task = Bukkit.getScheduler().runTaskTimer(Main.getInstance(), this::next, 100, 1);
        //start.setYaw(34);
        //start.setPitch(16);
        //player.teleport(start);
    }

    @SuppressWarnings("unused")
	private void next() {
        if (!player.isOnline()) {
            cancelTask();
            
        }
        
        
/*
        Location playerLoc = player.getLocation();
        if (playerLoc.distanceSquared(getCurrentTarget()) > speed * speed) {
            Vector vector = getCurrentTarget().toVector().subtract(playerLoc.toVector()).normalize().multiply(speed);
            playerLoc.add(vector);
            yaw = playerLoc.getYaw();
            switch(currentTargetIndex){
            case 0:
            	if(yaw < 105){
            		yaw += 0.05;
            		playerLoc.setPitch(16);
            		playerLoc.setYaw(yaw);
            	}
            	break;
            case 1:
            	if(yaw > 100){
            		yaw -= 0.05;
            		playerLoc.setYaw(yaw);
            		playerLoc.setPitch(23);
            	}
            	break;
            }
            player.teleport(playerLoc);
        } else {
            player.teleport(getCurrentTarget());
            if (targets.length == currentTargetIndex + 1) {
                cancelTask();
            } else {
                switch(currentTargetIndex){
                case 0:
                	playerLoc.setYaw(34);
                	playerLoc.setPitch(16);
                	break;
                case 1:
                	playerLoc.setYaw(130);
                	playerLoc.setPitch(23);
                	break;
                }
                currentTargetIndex++;
            }
        }
*/
    }
/*
    public Location getCurrentTarget() {
        return targets[currentTargetIndex];
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }
*/
    public void cancelTask() {
        if (task != null) {
            task.cancel();
            task = null;
        }
    }

    public static Location randomPlayerLocation(){
        Location PlayerLoc;
        int x = (int) ((Math.random() * 680) + 315);
        int z = (int) ((Math.random() * 190) - 12);
        for(Player Player : Bukkit.getOnlinePlayers()){
        	if(!Player.hasPermission(AlkaPermission)){
        		continue;
        	}
        	
            int anotherX = (int) Player.getLocation().getX();
            int anotherZ = (int) Player.getLocation().getZ();
            if((x-anotherX) > -10 || (x-anotherX) < 10){
                x = x + 10;
            }
            if((z-anotherZ) > -10 || (z-anotherZ) < 10){
                z = z + 10;
            }
        }
        PlayerLoc = new Location(ConfigManager.getWorld(),x,255,z);
        return PlayerLoc;
    }
}
