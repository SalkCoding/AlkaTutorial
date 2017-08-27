package net.alkaonline.alkatutorial;

import net.alkaonline.alkatutorial.listener.PlayerChatListener;
import net.alkaonline.alkatutorial.listener.PlayerJoinListener;
import net.alkaonline.alkatutorial.listener.PlayerMoveListener;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;

public class AlkaTutorial extends JavaPlugin {

    private static AlkaTutorial instance;
    private World world;

    public static AlkaTutorial getInstance() {
        if (instance == null) {
            throw new IllegalStateException("AlkaTutorial not initialized!");
        } else {
            return instance;
        }
    }

    public AlkaTutorial() {
        if (instance == null) {
            instance = this;
        } else {
            throw new IllegalStateException("This class is singleton.");
        }
    }

    @Override
    public void onEnable() {
        world = Bukkit.getWorld(getConfig().getString("world", "world"));

        getServer().getPluginManager().registerEvents(new PlayerMoveListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerChatListener(), this);
    }

    @Override
    public void onDisable() {
        saveConfig();
    }

    public World getWorld() {
        return world;
    }
}
