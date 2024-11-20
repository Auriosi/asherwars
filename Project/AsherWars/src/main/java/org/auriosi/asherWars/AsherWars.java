package org.auriosi.asherWars;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import org.auriosi.asherWars.commands.StartCommand;
import org.bukkit.plugin.java.JavaPlugin;

public final class AsherWars extends JavaPlugin {
    private final ProtocolManager protocolManager = ProtocolLibrary.getProtocolManager();
    private final GameManager gameManager = new GameManager(this);

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new GameplayDisableListeners(this), this);
        getCommand("start").setExecutor(new StartCommand(gameManager));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public GameManager getGameManager() {
        return gameManager;
    }

    public ProtocolManager getProtocolManager() {
        return protocolManager;
    }
}
