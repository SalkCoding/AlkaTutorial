package net.alkaonline.alkatutorial.BlockEffect;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.util.Vector;

import com.salk_coding.tutorial.Main;

public class BlockTutorial {
	
	private Location playerLoc = null;
	private Player player = null;
	
	public BlockTutorial(Player Player,Location Location){
		this.player = Player;
		this.playerLoc = Location;
		playerLoc.setYaw(-5);
		playerLoc.setPitch(0);
		player.teleport(playerLoc);
	}
	
	public void spawnItemOnBlock(int second,Material blocktype,Material item,Particle particle,Sound sound){
		Location blockLoc = new Location(player.getWorld(),playerLoc.getX(),(playerLoc.getY()),(playerLoc.getZ() + 3));
		Block block = blockLoc.getBlock();
		block.setType(blocktype);
		blockLoc.setX(playerLoc.getX() + 0.5);
		blockLoc.setY(playerLoc.getY() + 1);
		blockLoc.setZ(playerLoc.getZ() + 3.5);
		ItemStack dropitem = new ItemStack(item);
		
		if(item == Material.SKULL_ITEM){
			SkullMeta skull = (SkullMeta) dropitem.getItemMeta();
			skull.setOwner("zombie");
		}
		
		Item itemEntity = blockLoc.getWorld().dropItem(blockLoc, new ItemStack(item));
		itemEntity.setVelocity(new Vector(0,0,0));
		BlockTimer timer = new BlockTimer(player,second,itemEntity,block,blockLoc, particle,sound);
		timer.setTask(Bukkit.getScheduler().runTaskTimer(Main.getInstance(), timer, 1,20));
	}
	
}

class BlockTimer implements Runnable{

	private int second;
	private Item item;
	private Player player;
	private Block block;
	private BukkitTask task;
	private Location particleLoc;
	private Particle particle;
	private Sound sound;
	
	public void setTask(BukkitTask task){
		this.task = task;
	}
	
	public BlockTimer(Player player,int second,Item item,Block block,Location particleLoc, Particle particle,Sound sound){
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
		if(second == 0){
			item.remove();
			block.setType(Material.AIR);
			player.spawnParticle(particle, particleLoc, 100);
			player.playSound(block.getLocation(), sound, 20, 100);
			task.cancel();
		}
		else{
			if(Bukkit.getPlayer(player.getName()) == null){
				block.setType(Material.AIR);
				task.cancel();
			}
			second--;
		}
	}
	
}