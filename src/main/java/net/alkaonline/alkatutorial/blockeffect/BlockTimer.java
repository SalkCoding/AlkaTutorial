package net.alkaonline.alkatutorial.blockeffect;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

public class BlockTimer implements Runnable {

    private int second;
    private Item item;
    private Player player;
    private Block block;
    private BukkitTask task;
    private Location particleLoc;
    private Particle particle;
    private Sound sound;

    public void setTask(BukkitTask task) {
        this.task = task;
    }

    public BlockTimer(Player player, int second, Item item, Block block, Location particleLoc, Particle particle, Sound sound) {
        this.player = player;
        this.second = second;
        this.item = item;
        this.block = block;
        this.particle = particle;
        this.sound = sound;
        this.particleLoc = particleLoc;
    }

    @Override
    public void run() {
        if (second == 0) {
            item.remove();
            block.setType(Material.AIR);
            player.spawnParticle(particle, particleLoc, 100);
            player.playSound(block.getLocation(), sound, 20, 100);
            task.cancel();
        } else {
            if (Bukkit.getPlayer(player.getName()) == null) {
                block.setType(Material.AIR);
                task.cancel();
            }
            second--;
        }
    }

}
