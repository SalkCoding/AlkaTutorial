package net.alkaonline.alkatutorial.tutorialstr;

import net.alkaonline.alkatutorial.AlkaTutorial;
import net.alkaonline.alkatutorial.blockeffect.BlockTutorial;
import net.alkaonline.alkatutorial.flyingeffect.Spectate;
import net.md_5.bungee.api.ChatColor;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

import static net.alkaonline.alkatutorial.util.MessageFormats.*;

public class TutorialStr {

    private final Player player;
    private final Location lastLoc;
    private int page = 0;
    private BukkitTask task;
    private final BlockTutorial blocktutorial;
    private static final Page[] list = {new Page(5, "안녕하세요!"), new Page(5, "안녕하세요!", "지금부터 당신을 위한 인게임 튜토리얼을 시작합니다"),
            new Page(4, "안녕하세요!", "지금부터 당신을 위한 인게임 튜토리얼을 시작합니다",
                    ChatColor.translateAlternateColorCodes('&', "&7[&e주의&7]&c튜토리얼 도중 게임을 나가시면 처음부터 다시 시작합니다!")),
            new Page(5, "체력,배고픔 칸 위를 보시면 돈, 힘, 수분 수치가 추가되어있음을 보실 수 있습니다"), // 3
            new Page(4, "체력,배고픔 칸 위를 보시면 돈, 힘, 수분 수치가 추가되어있음을 보실 수 있습니다", "먼저, 힘에 대한 설명입니다"),
            new Page(4, "체력,배고픔 칸 위를 보시면 돈, 힘, 수분 수치가 추가되어있음을 보실 수 있습니다", "먼저, 힘에 대한 설명입니다",
                    "힘은 여러 버프 혹은 디버프로 조절되는 가상의 수치입니다"),
            new Page(3, "체력,배고픔 칸 위를 보시면 돈, 힘, 수분 수치가 추가되어있음을 보실 수 있습니다", "먼저, 힘에 대한 설명입니다",
                    "힘은 여러 버프 혹은 디버프로 조절되는 가상의 수치입니다", "힘이 높을수록 높은 등급의 도구 및 무기를 다루기가 쉬워지며"),
            new Page(3, "체력,배고픔 칸 위를 보시면 돈, 힘, 수분 수치가 추가되어있음을 보실 수 있습니다", "먼저, 힘에 대한 설명입니다",
                    "힘은 여러 버프 혹은 디버프로 조절되는 가상의 수치입니다", "힘이 높을수록 높은 등급의 도구 및 무기를 다루기가 쉬워지며",
                    "방어구를 아무런 패널티 없이 입을 수 있게 되고,"),
            new Page(2, "체력,배고픔 칸 위를 보시면 돈, 힘, 수분 수치가 추가되어있음을 보실 수 있습니다", "먼저, 힘에 대한 설명입니다",
                    "힘은 여러 버프 혹은 디버프로 조절되는 가상의 수치입니다", "힘이 높을수록 높은 등급의 도구 및 무기를 다루기가 쉬워지며",
                    "방어구를 아무런 패널티 없이 입을 수 있게 되고,", "활의 공격력이 강해집니다"),
            new Page(2, "체력,배고픔 칸 위를 보시면 돈, 힘, 수분 수치가 추가되어있음을 보실 수 있습니다", "먼저, 힘에 대한 설명입니다",
                    "힘은 여러 버프 혹은 디버프로 조절되는 가상의 수치입니다", "힘이 높을수록 높은 등급의 도구 및 무기를 다루기가 쉬워지며",
                    "방어구를 아무런 패널티 없이 입을 수 있게 되고,", "활의 공격력이 강해집니다", "반면 힘이 낮다면"),
            new Page(1, "체력,배고픔 칸 위를 보시면 돈, 힘, 수분 수치가 추가되어있음을 보실 수 있습니다", "먼저, 힘에 대한 설명입니다",
                    "힘은 여러 버프 혹은 디버프로 조절되는 가상의 수치입니다", "힘이 높을수록 높은 등급의 도구 및 무기를 다루기가 쉬워지며",
                    "방어구를 아무런 패널티 없이 입을 수 있게 되고,", "활의 공격력이 강해집니다", "반면 힘이 낮다면", "도구, 무기, 방어구를 사용하는데 있어 패널티가 있고"),
            new Page(5, "활이 더욱 잘 빗나가게 됩니다"), new Page(5, "힘 수치를 높이는 방법은 두 가지가 있습니다"),
            new Page(5, "힘 수치를 높이는 방법은 두 가지가 있습니다", "첫 번째로, 영양소관리입니다"),
            new Page(4, "힘 수치를 높이는 방법은 두 가지가 있습니다", "첫 번째로, 영양소관리입니다",
                    ChatColor.translateAlternateColorCodes('&', "영양소 GUI는 단축 명령어 &a/ak&f를 통해 확인할 수 있습니다")),
            new Page(4, "힘 수치를 높이는 방법은 두 가지가 있습니다", "첫 번째로, 영양소관리입니다",
                    ChatColor.translateAlternateColorCodes('&', "영양소 GUI는 단축 명령어 &a/ak&f를 통해 확인할 수 있습니다"),
                    "음식마다 가지고 있는 영양소가 있으며,"),
            new Page(3, "힘 수치를 높이는 방법은 두 가지가 있습니다", "첫 번째로, 영양소관리입니다",
                    ChatColor.translateAlternateColorCodes('&', "영양소 GUI는 단축 명령어 &a/ak&f를 통해 확인할 수 있습니다"),
                    "음식마다 가지고 있는 영양소가 있으며,", "음식을 먹을 때마다 해당 영양소가 2씩 증가하고"),
            new Page(2, "힘 수치를 높이는 방법은 두 가지가 있습니다", "첫 번째로, 영양소관리입니다",
                    ChatColor.translateAlternateColorCodes('&', "영양소 GUI는 단축 명령어 &a/ak&f를 통해 확인할 수 있습니다"),
                    "음식마다 가지고 있는 영양소가 있으며,", "음식을 먹을 때마다 해당 영양소가 2씩 증가하고", "해당하지 않는 영양소가 1씩 감소합니다"),
            new Page(2, "힘 수치를 높이는 방법은 두 가지가 있습니다", "첫 번째로, 영양소관리입니다",
                    ChatColor.translateAlternateColorCodes('&', "영양소 GUI는 단축 명령어 &a/ak&f를 통해 확인할 수 있습니다"),
                    "음식마다 가지고 있는 영양소가 있으며,", "음식을 먹을 때마다 해당 영양소가 2씩 증가하고", "해당하지 않는 영양소가 1씩 감소합니다",
                    "이 영양소 수치를 잘 관리하면 최대 5의 힘 수치를 올릴 수 있지만"),
            new Page(1, "힘 수치를 높이는 방법은 두 가지가 있습니다", "첫 번째로, 영양소관리입니다",
                    ChatColor.translateAlternateColorCodes('&', "영양소 GUI는 단축 명령어 &a/ak&f를 통해 확인할 수 있습니다"),
                    "음식마다 가지고 있는 영양소가 있으며,", "음식을 먹을 때마다 해당 영양소가 2씩 증가하고", "해당하지 않는 영양소가 1씩 감소합니다",
                    "이 영양소 수치를 잘 관리하면 최대 5의 힘 수치를 올릴 수 있지만", "영양소가 부족하면 여러 디버프로 고생할 수도 있습니다"),
            new Page(5, "두 번째로, 포션 효과입니다"), new Page(5, "두 번째로, 포션 효과입니다", "힘의 포션, 재생의 포션 효과가 지속 중일 시에는 1의 힘 수치를,"),
            new Page(4, "두 번째로, 포션 효과입니다", "힘의 포션, 재생의 포션 효과가 지속 중일 시에는 1의 힘 수치를,",
                    "행운의 포션 효과가 지속 중일 시네는 3의 힘 수치를 추가로 얻을 수 있습니다"),
            new Page(4, "두 번째로, 포션 효과입니다", "힘의 포션, 재생의 포션 효과가 지속 중일 시에는 1의 힘 수치를,",
                    "행운의 포션 효과가 지속 중일 시네는 3의 힘 수치를 추가로 얻을 수 있습니다", "힘 수치는 디버프, 사망 등의 이유로 떨어질 수도 있습니다"),
            new Page(5, "아이템 자체를 강화하여 필요 힘 수치를 낮추는 것도 방법입니다"),
            new Page(5, "아이템 자체를 강화하여 필요 힘 수치를 낮추는 것도 방법입니다", "강화를 위해선 모루가 필요하며, 아이템에 인첸트북을 통해 강화를 할 수 있습니다"),
            new Page(4, "아이템 자체를 강화하여 필요 힘 수치를 낮추는 것도 방법입니다", "강화를 위해선 모루가 필요하며, 아이템에 인첸트북을 통해 강화를 할 수 있습니다",
                    "제련 수치 및 강화상태에 따라 강화실패여부가 결정됩니다"),
            new Page(4, "아이템 자체를 강화하여 필요 힘 수치를 낮추는 것도 방법입니다", "강화를 위해선 모루가 필요하며, 아이템에 인첸트북을 통해 강화를 할 수 있습니다",
                    "제련 수치 및 강화상태에 따라 강화실패여부가 결정됩니다", "인첸트 테이블 및 양조대는 특정 스킬레벨이 높아야 사용가능 합니다"),
            new Page(5, "다음은 수분에 대한 설명입니다"), new Page(5, "다음은 수분에 대한 설명입니다", "수분은 배고픔과 함께 감소하며, 수분이 일정수치 이하가 될 경우"),
            new Page(4, "다음은 수분에 대한 설명입니다", "수분은 배고픔과 함께 감소하며, 수분이 일정수치 이하가 될 경우", "달리지 못하게 되니 주의하세요"),
            new Page(4, "다음은 수분에 대한 설명입니다", "수분은 배고픔과 함께 감소하며, 수분이 일정수치 이하가 될 경우", "달리지 못하게 되니 주의하세요",
                    "물병 및 포션, 우유, 여러 수분이 포함된 음식들로 회복할 수 있습니다"),
            new Page(5, "특정 발전과제를 깨면 스텟포인트를 획득할 수 있으며"),
            new Page(5, "특정 발전과제를 깨면 스텟포인트를 획득할 수 있으며", "이는 직업스킬을 얻는데 사용됩니다"),
            new Page(4, "특정 발전과제를 깨면 스텟포인트를 획득할 수 있으며", "이는 직업스킬을 얻는데 사용됩니다,", "직업스킬은 사냥꾼, 생산자, 상인, 대장장이 크게 4개로 나뉘며"),
            new Page(4, "특정 발전과제를 깨면 스텟포인트를 획득할 수 있으며", "이는 직업스킬을 얻는데 사용됩니다,", "직업스킬은 사냥꾼, 생산자, 상인, 대장장이 크게 4개로 나뉘며",
                    "해당 스킬이 높아지면 더욱 좋은 능력을 가지게 됩니다"),
            new Page(5, "마지막으로 상점입니다"), new Page(5, "마지막으로 상점입니다", "상점은 어드민 상점과 시장이 존재합니다"),
            new Page(4, "마지막으로 상점입니다", "상점은 어드민 상점과 시장이 존재합니다", "어드민 상점의 경우 몇몇 물건들이 시세에 맞춰져 있으며,"),
            new Page(4, "마지막으로 상점입니다", "상점은 어드민 상점과 시장이 존재합니다", "어드민 상점의 경우 몇몇 물건들이 시세에 맞춰져 있으며,",
                    "구매하거나 판매함에 따라 시세가 계속해서 변경됩니다"),
            new Page(3, "마지막으로 상점입니다", "상점은 어드민 상점과 시장이 존재합니다", "어드민 상점의 경우 몇몇 물건들이 시세에 맞춰져 있으며,",
                    "구매하거나 판매함에 따라 시세가 계속해서 변경됩니다", "시장은 유저들이 직접 자신의 물건을 판매하거나"),
            new Page(3, "마지막으로 상점입니다", "상점은 어드민 상점과 시장이 존재합니다", "어드민 상점의 경우 몇몇 물건들이 시세에 맞춰져 있으며,",
                    "구매하거나 판매함에 따라 시세가 계속해서 변경됩니다", "시장은 유저들이 직접 자신의 물건을 판매하거나", "다른 사람의 물건을 살 수 있습니다"),
            new Page(5, "서버에 대한 간단한 튜토리얼이 끝났습니다. 어떠셨나요?"),
            new Page(5, "서버에 대한 간단한 튜토리얼이 끝났습니다. 어떠셨나요?", "알카서버만의 특별함을 확인할 수 있었을 겁니다"),
            new Page(4, "서버에 대한 간단한 튜토리얼이 끝났습니다. 어떠셨나요?", "알카서버만의 특별함을 확인할 수 있었을 겁니다",
                    "아직 알카 서버를 제대로 즐기기 위해서는 알아가야 할 것들이 많습니다"),
            new Page(4, "서버에 대한 간단한 튜토리얼이 끝났습니다. 어떠셨나요?", "알카서버만의 특별함을 확인할 수 있었을 겁니다",
                    "아직 알카 서버를 제대로 즐기기 위해서는 알아가야 할 것들이 많습니다", "보다 자세한 내용이 카페 및 웹사이트에 안내되어 있습니다."),
            new Page(3, "서버에 대한 간단한 튜토리얼이 끝났습니다. 어떠셨나요?", "알카서버만의 특별함을 확인할 수 있었을 겁니다",
                    "아직 알카 서버를 제대로 즐기기 위해서는 알아가야 할 것들이 많습니다", "보다 자세한 내용이 카페 및 웹사이트에 안내되어 있습니다.",
                    ChatColor.translateAlternateColorCodes('&', "&b카페 주소 : http://cafe.naver.com/alkaonline"))};

    public void sendFormat(int blank, String... messages) {
        for (int i = 0; i < 100; i++) {
            player.sendMessage("");
        }
        player.sendMessage(FILLED_LINE);
        blank -= 1;
        for (int i = 0; i < blank; i++) {
            player.sendMessage(EMPTY_LINE);
        }
        for (int i = 0; i < messages.length; i++) {
            player.sendMessage(PREFIX + messages[i]);
        }
        for (int i = 0; i < (8 - blank - messages.length); i++) {
            player.sendMessage(EMPTY_LINE);
        }
        player.sendMessage(FILLED_LINE);
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
        task = Bukkit.getScheduler().runTaskTimer(AlkaTutorial.getInstance(), this::next, 50, 60);
    }

    public void next() {
        if (!(player.isOnline())) {
            Location blockLoc = new Location(player.getWorld(), player.getLocation().getX(), (player.getLocation().getY()), (player.getLocation().getZ() + 3));
            blockLoc.getBlock().setType(Material.AIR);
            task.cancel();
        }
        Location loc = player.getLocation();
        sendFormat(list[page].getFirstBlank(), list[page].getString());
        switch (page) {
            case 0:// 책
                player.playSound(loc, Sound.BLOCK_CHEST_LOCKED, 20, 100);
                blocktutorial.spawnItemOnBlock(8, Material.BARRIER, Material.BOOK, Particle.ENCHANTMENT_TABLE,
                        Sound.ENTITY_PLAYER_LEVELUP);
                break;
            case 3:// 다이아몬드 검
                player.playSound(loc, Sound.BLOCK_ANVIL_PLACE, 20, 100);
                blocktutorial.spawnItemOnBlock(22, Material.BARRIER, Material.DIAMOND_SWORD, Particle.DRAGON_BREATH,
                        Sound.BLOCK_SAND_BREAK);
                break;
            case 12:// 빵
                player.playSound(loc, Sound.ENTITY_GENERIC_EAT, 20, 100);
                blocktutorial.spawnItemOnBlock(20, Material.BARRIER, Material.BREAD, Particle.HEART, Sound.BLOCK_SAND_BREAK);
                break;
            case 20:// 포션
                player.playSound(loc, Sound.BLOCK_GLASS_STEP, 20, 100);
                blocktutorial.spawnItemOnBlock(10, Material.BARRIER, Material.POTION, Particle.TOTEM,
                        Sound.BLOCK_SAND_BREAK);
                break;
            case 24:// 모루
                player.playSound(loc, Sound.BLOCK_ANVIL_LAND, 20, 100);
                blocktutorial.spawnItemOnBlock(10, Material.BARRIER, Material.ANVIL, Particle.PORTAL,
                        Sound.BLOCK_SAND_BREAK);
                break;
            case 28:// 물 양동이
                player.playSound(loc, Sound.BLOCK_WATERLILY_PLACE, 20, 100);
                blocktutorial.spawnItemOnBlock(10, Material.BARRIER, Material.WATER_BUCKET, Particle.WATER_BUBBLE,
                        Sound.BLOCK_SAND_BREAK);
                break;
            case 32:// 황금사과
                player.playSound(loc, Sound.ENTITY_GENERIC_EAT, 20, 100);
                blocktutorial.spawnItemOnBlock(10, Material.BARRIER, Material.GOLDEN_APPLE, Particle.SPELL,
                        Sound.BLOCK_SAND_BREAK);
                break;
            case 36:// 에메랄드
                player.playSound(loc, Sound.ENTITY_PLAYER_SWIM, 20, 100);
                blocktutorial.spawnItemOnBlock(16, Material.BARRIER, Material.EMERALD, Particle.CLOUD,
                        Sound.BLOCK_SAND_BREAK);
                break;
            case 42:// 책
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
                Permission per = Bukkit.getServicesManager().getRegistration(Permission.class).getProvider();
                per.playerRemoveGroup(player, per.getPrimaryGroup(player));
                // per.playerAddGroup("world", player, "user");
                for (World world : Bukkit.getWorlds()) {
                    per.playerAddGroup(world.getName(), player, "user");
                }
            } catch (UnsupportedOperationException e) {
                System.out.println("[AlkaTutorial] 펄미션이 없습니다.");
            }
        }
    }
}
