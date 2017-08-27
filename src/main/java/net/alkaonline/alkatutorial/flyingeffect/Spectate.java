package net.alkaonline.alkatutorial.flyingeffect;

import net.alkaonline.alkatutorial.AlkaTutorial;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

import java.util.Random;

public class Spectate {

    private static final Random random = new Random();

    private final Player player;
    //private final Location start;
    //private final Location[] targets;
    //private double speed;
    //private float yaw;
    private BukkitTask task;

    // private int currentTargetIndex = 0;

    public Spectate(Player player/*, double speed, Location start, Location... targets*/) {
        this.player = player;
        //this.start = start;
        //this.targets = targets;
        //this.speed = speed;
    }


    public void start() {
        player.setGameMode(GameMode.SPECTATOR);

        //task = Bukkit.getScheduler().runTaskTimer(AlkaTutorial.getInstance(), this::next, 100, 1);
        //start.setYaw(34);
        //start.setPitch(16);
        //player.teleport(start);
    }

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

    public static Location randomPlayerLocation() {
        int x = random.nextInt(680) + 315;
        int z = random.nextInt(190) - 12;
        for (Player Player : Bukkit.getOnlinePlayers()) {
            if (!Player.hasPermission("tutorial.complete")) {
                continue;
            }

            int anotherX = Player.getLocation().getBlockX();
            int anotherZ = Player.getLocation().getBlockZ();
            if ((x - anotherX) > -10 || (x - anotherX) < 10) {
                x += 10;
            }
            if ((z - anotherZ) > -10 || (z - anotherZ) < 10) {
                z += 10;
            }
        }
        return new Location(AlkaTutorial.getInstance().getWorld(), x, 255, z);
    }
}
