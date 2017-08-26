package net.alkaonline.alkatutorial.tutorialstr;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.scheduler.BukkitTask;

import com.salk_coding.tutorial.Main;

import net.alkaonline.alkatutorial.BlockEffect.BlockTutorial;
import net.alkaonline.alkatutorial.PluginInterface.PluginInterface;
import net.alkaonline.alkatutorial.flyingeffect.Spectate;
import net.md_5.bungee.api.ChatColor;
import net.milkbowl.vault.permission.Permission;

public class TutorialStr implements PluginInterface {

	private Player player;
	private Location lastLoc;
	private int page = 0;
	private BukkitTask task;
	private BlockTutorial blocktutorial;
	private static Page[] list = { new Page(5, "�ȳ��ϼ���!"), new Page(5, "�ȳ��ϼ���!", "���ݺ��� ����� ���� �ΰ��� Ʃ�丮���� �����մϴ�"),
			new Page(4, "�ȳ��ϼ���!", "���ݺ��� ����� ���� �ΰ��� Ʃ�丮���� �����մϴ�",
					ChatColor.translateAlternateColorCodes('&', "&7[&e����&7]&cƩ�丮�� ���� ������ �����ø� ó������ �ٽ� �����մϴ�!")),
			new Page(5, "ü��,����� ĭ ���� ���ø� ��, ��, ���� ��ġ�� �߰��Ǿ������� ���� �� �ֽ��ϴ�"), // 3
			new Page(4, "ü��,����� ĭ ���� ���ø� ��, ��, ���� ��ġ�� �߰��Ǿ������� ���� �� �ֽ��ϴ�", "����, ���� ���� �����Դϴ�"),
			new Page(4, "ü��,����� ĭ ���� ���ø� ��, ��, ���� ��ġ�� �߰��Ǿ������� ���� �� �ֽ��ϴ�", "����, ���� ���� �����Դϴ�",
					"���� ���� ���� Ȥ�� ������� �����Ǵ� ������ ��ġ�Դϴ�"),
			new Page(3, "ü��,����� ĭ ���� ���ø� ��, ��, ���� ��ġ�� �߰��Ǿ������� ���� �� �ֽ��ϴ�", "����, ���� ���� �����Դϴ�",
					"���� ���� ���� Ȥ�� ������� �����Ǵ� ������ ��ġ�Դϴ�", "���� �������� ���� ����� ���� �� ���⸦ �ٷ�Ⱑ ��������"),
			new Page(3, "ü��,����� ĭ ���� ���ø� ��, ��, ���� ��ġ�� �߰��Ǿ������� ���� �� �ֽ��ϴ�", "����, ���� ���� �����Դϴ�",
					"���� ���� ���� Ȥ�� ������� �����Ǵ� ������ ��ġ�Դϴ�", "���� �������� ���� ����� ���� �� ���⸦ �ٷ�Ⱑ ��������",
					"���� �ƹ��� �г�Ƽ ���� ���� �� �ְ� �ǰ�,"),
			new Page(2, "ü��,����� ĭ ���� ���ø� ��, ��, ���� ��ġ�� �߰��Ǿ������� ���� �� �ֽ��ϴ�", "����, ���� ���� �����Դϴ�",
					"���� ���� ���� Ȥ�� ������� �����Ǵ� ������ ��ġ�Դϴ�", "���� �������� ���� ����� ���� �� ���⸦ �ٷ�Ⱑ ��������",
					"���� �ƹ��� �г�Ƽ ���� ���� �� �ְ� �ǰ�,", "Ȱ�� ���ݷ��� �������ϴ�"),
			new Page(2, "ü��,����� ĭ ���� ���ø� ��, ��, ���� ��ġ�� �߰��Ǿ������� ���� �� �ֽ��ϴ�", "����, ���� ���� �����Դϴ�",
					"���� ���� ���� Ȥ�� ������� �����Ǵ� ������ ��ġ�Դϴ�", "���� �������� ���� ����� ���� �� ���⸦ �ٷ�Ⱑ ��������",
					"���� �ƹ��� �г�Ƽ ���� ���� �� �ְ� �ǰ�,", "Ȱ�� ���ݷ��� �������ϴ�", "�ݸ� ���� ���ٸ�"),
			new Page(1, "ü��,����� ĭ ���� ���ø� ��, ��, ���� ��ġ�� �߰��Ǿ������� ���� �� �ֽ��ϴ�", "����, ���� ���� �����Դϴ�",
					"���� ���� ���� Ȥ�� ������� �����Ǵ� ������ ��ġ�Դϴ�", "���� �������� ���� ����� ���� �� ���⸦ �ٷ�Ⱑ ��������",
					"���� �ƹ��� �г�Ƽ ���� ���� �� �ְ� �ǰ�,", "Ȱ�� ���ݷ��� �������ϴ�", "�ݸ� ���� ���ٸ�", "����, ����, ���� ����ϴµ� �־� �г�Ƽ�� �ְ�"),
			new Page(5, "Ȱ�� ���� �� �������� �˴ϴ�"), new Page(5, "�� ��ġ�� ���̴� ����� �� ������ �ֽ��ϴ�"),
			new Page(5, "�� ��ġ�� ���̴� ����� �� ������ �ֽ��ϴ�", "ù ��°��, ����Ұ����Դϴ�"),
			new Page(4, "�� ��ġ�� ���̴� ����� �� ������ �ֽ��ϴ�", "ù ��°��, ����Ұ����Դϴ�",
					ChatColor.translateAlternateColorCodes('&', "����� GUI�� ���� ��ɾ� &a/ak&f�� ���� Ȯ���� �� �ֽ��ϴ�")),
			new Page(4, "�� ��ġ�� ���̴� ����� �� ������ �ֽ��ϴ�", "ù ��°��, ����Ұ����Դϴ�",
					ChatColor.translateAlternateColorCodes('&', "����� GUI�� ���� ��ɾ� &a/ak&f�� ���� Ȯ���� �� �ֽ��ϴ�"),
					"���ĸ��� ������ �ִ� ����Ұ� ������,"),
			new Page(3, "�� ��ġ�� ���̴� ����� �� ������ �ֽ��ϴ�", "ù ��°��, ����Ұ����Դϴ�",
					ChatColor.translateAlternateColorCodes('&', "����� GUI�� ���� ��ɾ� &a/ak&f�� ���� Ȯ���� �� �ֽ��ϴ�"),
					"���ĸ��� ������ �ִ� ����Ұ� ������,", "������ ���� ������ �ش� ����Ұ� 2�� �����ϰ�"),
			new Page(2, "�� ��ġ�� ���̴� ����� �� ������ �ֽ��ϴ�", "ù ��°��, ����Ұ����Դϴ�",
					ChatColor.translateAlternateColorCodes('&', "����� GUI�� ���� ��ɾ� &a/ak&f�� ���� Ȯ���� �� �ֽ��ϴ�"),
					"���ĸ��� ������ �ִ� ����Ұ� ������,", "������ ���� ������ �ش� ����Ұ� 2�� �����ϰ�", "�ش����� �ʴ� ����Ұ� 1�� �����մϴ�"),
			new Page(2, "�� ��ġ�� ���̴� ����� �� ������ �ֽ��ϴ�", "ù ��°��, ����Ұ����Դϴ�",
					ChatColor.translateAlternateColorCodes('&', "����� GUI�� ���� ��ɾ� &a/ak&f�� ���� Ȯ���� �� �ֽ��ϴ�"),
					"���ĸ��� ������ �ִ� ����Ұ� ������,", "������ ���� ������ �ش� ����Ұ� 2�� �����ϰ�", "�ش����� �ʴ� ����Ұ� 1�� �����մϴ�",
					"�� ����� ��ġ�� �� �����ϸ� �ִ� 5�� �� ��ġ�� �ø� �� ������"),
			new Page(1, "�� ��ġ�� ���̴� ����� �� ������ �ֽ��ϴ�", "ù ��°��, ����Ұ����Դϴ�",
					ChatColor.translateAlternateColorCodes('&', "����� GUI�� ���� ��ɾ� &a/ak&f�� ���� Ȯ���� �� �ֽ��ϴ�"),
					"���ĸ��� ������ �ִ� ����Ұ� ������,", "������ ���� ������ �ش� ����Ұ� 2�� �����ϰ�", "�ش����� �ʴ� ����Ұ� 1�� �����մϴ�",
					"�� ����� ��ġ�� �� �����ϸ� �ִ� 5�� �� ��ġ�� �ø� �� ������", "����Ұ� �����ϸ� ���� ������� ����� ���� �ֽ��ϴ�"),
			new Page(5, "�� ��°��, ���� ȿ���Դϴ�"), new Page(5, "�� ��°��, ���� ȿ���Դϴ�", "���� ����, ����� ���� ȿ���� ���� ���� �ÿ��� 1�� �� ��ġ��,"),
			new Page(4, "�� ��°��, ���� ȿ���Դϴ�", "���� ����, ����� ���� ȿ���� ���� ���� �ÿ��� 1�� �� ��ġ��,",
					"����� ���� ȿ���� ���� ���� �ó״� 3�� �� ��ġ�� �߰��� ���� �� �ֽ��ϴ�"),
			new Page(4, "�� ��°��, ���� ȿ���Դϴ�", "���� ����, ����� ���� ȿ���� ���� ���� �ÿ��� 1�� �� ��ġ��,",
					"����� ���� ȿ���� ���� ���� �ó״� 3�� �� ��ġ�� �߰��� ���� �� �ֽ��ϴ�", "�� ��ġ�� �����, ��� ���� ������ ������ ���� �ֽ��ϴ�"),
			new Page(5, "������ ��ü�� ��ȭ�Ͽ� �ʿ� �� ��ġ�� ���ߴ� �͵� ����Դϴ�"),
			new Page(5, "������ ��ü�� ��ȭ�Ͽ� �ʿ� �� ��ġ�� ���ߴ� �͵� ����Դϴ�", "��ȭ�� ���ؼ� ��簡 �ʿ��ϸ�, �����ۿ� ��þƮ���� ���� ��ȭ�� �� �� �ֽ��ϴ�"),
			new Page(4, "������ ��ü�� ��ȭ�Ͽ� �ʿ� �� ��ġ�� ���ߴ� �͵� ����Դϴ�", "��ȭ�� ���ؼ� ��簡 �ʿ��ϸ�, �����ۿ� ��þƮ���� ���� ��ȭ�� �� �� �ֽ��ϴ�",
					"���� ��ġ �� ��ȭ���¿� ���� ��ȭ���п��ΰ� �����˴ϴ�"),
			new Page(4, "������ ��ü�� ��ȭ�Ͽ� �ʿ� �� ��ġ�� ���ߴ� �͵� ����Դϴ�", "��ȭ�� ���ؼ� ��簡 �ʿ��ϸ�, �����ۿ� ��þƮ���� ���� ��ȭ�� �� �� �ֽ��ϴ�",
					"���� ��ġ �� ��ȭ���¿� ���� ��ȭ���п��ΰ� �����˴ϴ�", "��þƮ ���̺� �� ������� Ư�� ��ų������ ���ƾ� ��밡�� �մϴ�"),
			new Page(5, "������ ���п� ���� �����Դϴ�"), new Page(5, "������ ���п� ���� �����Դϴ�", "������ ����İ� �Բ� �����ϸ�, ������ ������ġ ���ϰ� �� ���"),
			new Page(4, "������ ���п� ���� �����Դϴ�", "������ ����İ� �Բ� �����ϸ�, ������ ������ġ ���ϰ� �� ���", "�޸��� ���ϰ� �Ǵ� �����ϼ���"),
			new Page(4, "������ ���п� ���� �����Դϴ�", "������ ����İ� �Բ� �����ϸ�, ������ ������ġ ���ϰ� �� ���", "�޸��� ���ϰ� �Ǵ� �����ϼ���",
					"���� �� ����, ����, ���� ������ ���Ե� ���ĵ�� ȸ���� �� �ֽ��ϴ�"),
			new Page(5, "Ư�� ���������� ���� ��������Ʈ�� ȹ���� �� ������"),
			new Page(5, "Ư�� ���������� ���� ��������Ʈ�� ȹ���� �� ������", "�̴� ������ų�� ��µ� ���˴ϴ�"),
			new Page(4, "Ư�� ���������� ���� ��������Ʈ�� ȹ���� �� ������", "�̴� ������ų�� ��µ� ���˴ϴ�,", "������ų�� ��ɲ�, ������, ����, �������� ũ�� 4���� ������"),
			new Page(4, "Ư�� ���������� ���� ��������Ʈ�� ȹ���� �� ������", "�̴� ������ų�� ��µ� ���˴ϴ�,", "������ų�� ��ɲ�, ������, ����, �������� ũ�� 4���� ������",
					"�ش� ��ų�� �������� ���� ���� �ɷ��� ������ �˴ϴ�"),
			new Page(5, "���������� �����Դϴ�"), new Page(5, "���������� �����Դϴ�", "������ ���� ������ ������ �����մϴ�"),
			new Page(4, "���������� �����Դϴ�", "������ ���� ������ ������ �����մϴ�", "���� ������ ��� ��� ���ǵ��� �ü��� ������ ������,"),
			new Page(4, "���������� �����Դϴ�", "������ ���� ������ ������ �����մϴ�", "���� ������ ��� ��� ���ǵ��� �ü��� ������ ������,",
					"�����ϰų� �Ǹ��Կ� ���� �ü��� ����ؼ� ����˴ϴ�"),
			new Page(3, "���������� �����Դϴ�", "������ ���� ������ ������ �����մϴ�", "���� ������ ��� ��� ���ǵ��� �ü��� ������ ������,",
					"�����ϰų� �Ǹ��Կ� ���� �ü��� ����ؼ� ����˴ϴ�", "������ �������� ���� �ڽ��� ������ �Ǹ��ϰų�"),
			new Page(3, "���������� �����Դϴ�", "������ ���� ������ ������ �����մϴ�", "���� ������ ��� ��� ���ǵ��� �ü��� ������ ������,",
					"�����ϰų� �Ǹ��Կ� ���� �ü��� ����ؼ� ����˴ϴ�", "������ �������� ���� �ڽ��� ������ �Ǹ��ϰų�", "�ٸ� ����� ������ �� �� �ֽ��ϴ�"),
			new Page(5, "������ ���� ������ Ʃ�丮���� �������ϴ�. ��̳���?"),
			new Page(5, "������ ���� ������ Ʃ�丮���� �������ϴ�. ��̳���?", "��ī�������� Ư������ Ȯ���� �� �־��� �̴ϴ�"),
			new Page(4, "������ ���� ������ Ʃ�丮���� �������ϴ�. ��̳���?", "��ī�������� Ư������ Ȯ���� �� �־��� �̴ϴ�",
					"���� ��ī ������ ����� ���� ���ؼ��� �˾ư��� �� �͵��� �����ϴ�"),
			new Page(4, "������ ���� ������ Ʃ�丮���� �������ϴ�. ��̳���?", "��ī�������� Ư������ Ȯ���� �� �־��� �̴ϴ�",
					"���� ��ī ������ ����� ���� ���ؼ��� �˾ư��� �� �͵��� �����ϴ�", "���� �ڼ��� ������ ī�� �� ������Ʈ�� �ȳ��Ǿ� �ֽ��ϴ�."),
			new Page(3, "������ ���� ������ Ʃ�丮���� �������ϴ�. ��̳���?", "��ī�������� Ư������ Ȯ���� �� �־��� �̴ϴ�",
					"���� ��ī ������ ����� ���� ���ؼ��� �˾ư��� �� �͵��� �����ϴ�", "���� �ڼ��� ������ ī�� �� ������Ʈ�� �ȳ��Ǿ� �ֽ��ϴ�.",
					ChatColor.translateAlternateColorCodes('&', "&bī�� �ּ� : http://cafe.naver.com/alkaonline")) };

	public void sendFormat(int blank, String... messages) {
		for(int i = 0;i<100;i++){
			player.sendMessage("");
		}
		player.sendMessage(FormatTop);
		blank -= 1;
		for (int i = 0; i < blank; i++) {
			player.sendMessage(FormatBlank);
		}
		for (int i = 0; i < messages.length; i++) {
			player.sendMessage(FormatMiddle + messages[i]);
		}
		for (int i = 0; i < (8 - blank - messages.length); i++) {
			player.sendMessage(FormatBlank);
		}
		player.sendMessage(FormatTop);
	}

	public TutorialStr(Player player) {
		this.player = player;
		this.lastLoc = player.getLocation();
		Location random = Spectate.randomPlayerLocation();
		blocktutorial = new BlockTutorial(player, random.clone());
		random.setX(random.getX() + 0.5);
		random.setZ(random.getZ() - 1.5);
		player.setAllowFlight(true);
		player.setFlying(true);
		player.teleport(random);
	}

	public void start() {
		task = Bukkit.getScheduler().runTaskTimer(Main.getInstance(), this::next, 50, 60);
	}

	public void next() {
		if (!(player.isOnline())) {
			Location blockLoc = new Location(player.getWorld(),player.getLocation().getX(),(player.getLocation().getY()),(player.getLocation().getZ() + 3));
			blockLoc.getBlock().setType(Material.AIR);
			task.cancel();
		}
		Location loc = player.getLocation();
		sendFormat(list[page].getFirst(), list[page].getString());
		switch (page) {
		case 0:// å
			player.playSound(loc, Sound.BLOCK_CHEST_LOCKED, 20, 100);
			blocktutorial.spawnItemOnBlock(8, Material.BARRIER, Material.BOOK, Particle.ENCHANTMENT_TABLE,
					Sound.ENTITY_PLAYER_LEVELUP);
			break;
		case 3:// ���̾Ƹ�� ��
			player.playSound(loc, Sound.BLOCK_ANVIL_PLACE, 20, 100);
			blocktutorial.spawnItemOnBlock(22, Material.BARRIER, Material.DIAMOND_SWORD, Particle.DRAGON_BREATH,
					Sound.BLOCK_SAND_BREAK);
			break;
		case 12:// ��
			player.playSound(loc, Sound.ENTITY_GENERIC_EAT, 20, 100);
			blocktutorial.spawnItemOnBlock(20, Material.BARRIER, Material.BREAD, Particle.HEART, Sound.BLOCK_SAND_BREAK);
			break;
		case 20:// ����
			player.playSound(loc, Sound.BLOCK_GLASS_STEP, 20, 100);
			blocktutorial.spawnItemOnBlock(10, Material.BARRIER, Material.POTION, Particle.TOTEM,
					Sound.BLOCK_SAND_BREAK);
			break;
		case 24:// ���
			player.playSound(loc, Sound.BLOCK_ANVIL_LAND, 20, 100);
			blocktutorial.spawnItemOnBlock(10, Material.BARRIER, Material.ANVIL, Particle.PORTAL,
					Sound.BLOCK_SAND_BREAK);
			break;
		case 28:// �� �絿��
			player.playSound(loc, Sound.BLOCK_WATERLILY_PLACE, 20, 100);
			blocktutorial.spawnItemOnBlock(10, Material.BARRIER, Material.WATER_BUCKET, Particle.WATER_BUBBLE,
					Sound.BLOCK_SAND_BREAK);
			break;
		case 32:// Ȳ�ݻ��
			player.playSound(loc, Sound.ENTITY_GENERIC_EAT, 20, 100);
			blocktutorial.spawnItemOnBlock(10, Material.BARRIER, Material.GOLDEN_APPLE, Particle.SPELL,
					Sound.BLOCK_SAND_BREAK);
			break;
		case 36:// ���޶���
			player.playSound(loc, Sound.ENTITY_PLAYER_SWIM, 20, 100);
			blocktutorial.spawnItemOnBlock(16, Material.BARRIER, Material.EMERALD, Particle.CLOUD,
					Sound.BLOCK_SAND_BREAK);
			break;
		case 42:// å
			player.playSound(loc, Sound.BLOCK_CHEST_LOCKED, 20, 100);
			blocktutorial.spawnItemOnBlock(10, Material.BARRIER, Material.BOOK, Particle.ENCHANTMENT_TABLE,
					Sound.ENTITY_PLAYER_LEVELUP);
			break;
		default:
			player.playSound(loc, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 20, 100);
		}
		page++;
		if (list.length == page) {
			player.teleport(lastLoc.getWorld().getSpawnLocation());
			player.setAllowFlight(false);
			player.setFlying(false);
			//player.setGameMode(GameMode.SURVIVAL);
			task.cancel();
			try {
				Permission per = null;
				RegisteredServiceProvider<Permission> permissionProvider = Main.getInstance().getServer()
						.getServicesManager().getRegistration(Permission.class);
				if (permissionProvider != null) {
					per = (Permission) permissionProvider.getProvider();
				}
				per.playerRemoveGroup(player, per.getPrimaryGroup(player));
				// per.playerAddGroup("world", player, "user");
				for (World world : Bukkit.getWorlds()) {
					String worldname = world.getWorldFolder().getName();
					per.playerAddGroup(worldname, player, "user");
				}
			} catch (UnsupportedOperationException e) {
				System.out.println("[AlkaTutorial] �޹̼��� �����ϴ�.");
			}
		}
	}
}