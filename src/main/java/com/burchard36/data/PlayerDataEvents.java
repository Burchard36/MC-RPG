package com.burchard36.data;

import com.burchard36.Logger;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerDataEvents implements Listener {

    private final DataManager manager;

    public PlayerDataEvents(final DataManager manager) {
        this.manager = manager;
        Logger.log("&aLoading PlayerDataEvents. . .");
    }
    @EventHandler
    public void onPlayerJoin(final PlayerJoinEvent event) {
        final Player player = event.getPlayer();
        final boolean playerDataExists = this.manager.playerExists(player);

        if (!playerDataExists) this.manager.createPlayerData(player);
        this.manager.loadPlayerData(player);
    }
}
