package net.alkaonline.alkatutorial.blockeffect;

import net.alkaonline.alkatutorial.AlkaTutorial;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.util.Vector;

public class BlockTutorial {

    private Location playerLoc;
    private Player player;

    public BlockTutorial(Player player, Location location) {
        this.player = player;
        this.playerLoc = location;
        playerLoc.setYaw(-5);
        playerLoc.setPitch(0);
        this.player.teleport(playerLoc);
    }

    public void spawnItemOnBlock(int second, Material blocktype, Material item, Particle particle, Sound sound) {
        Location blockLoc = new Location(player.getWorld(), playerLoc.getX(), (playerLoc.getY()), (playerLoc.getZ() + 3));
        Block block = blockLoc.getBlock();
        block.setType(blocktype);
        blockLoc.setX(playerLoc.getX() + 0.5);
        blockLoc.setY(playerLoc.getY() + 1);
        blockLoc.setZ(playerLoc.getZ() + 3.5);
        ItemStack dropitem = new ItemStack(item);

        if (item == Material.SKULL_ITEM) {
            SkullMeta skull = (SkullMeta) dropitem.getItemMeta();
            skull.setOwner("zombie");
        }

        Item itemEntity = blockLoc.getWorld().dropItem(blockLoc, new ItemStack(item));
        itemEntity.setVelocity(new Vector(0, 0, 0));
        BlockTimer timer = new BlockTimer(player, second, itemEntity, block, blockLoc, particle, sound);
        timer.setTask(Bukkit.getScheduler().runTaskTimer(AlkaTutorial.getInstance(), timer, 1, 20));
    }

}
