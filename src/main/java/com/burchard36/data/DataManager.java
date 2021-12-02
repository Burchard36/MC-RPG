package com.burchard36.data;

import com.burchard36.Logger;
import com.burchard36.RpgPlugin;
import com.burchard36.data.structure.JsonPlayerData;
import com.burchard36.json.PluginDataMap;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;

import java.io.File;
import java.util.UUID;

public class DataManager {

    private final RpgPlugin plugin;
    private final PluginDataMap dataMap;
    private final PlayerDataEvents events;

    public DataManager(final RpgPlugin plugin) {
        Logger.log("Loading DataManager. . .");
        this.plugin = plugin;
        this.events = new PlayerDataEvents(this);
        this.dataMap = this.plugin.getLib().getPluginDataManager().createNewMap();
        this.plugin.getLib().getPluginDataManager().registerPluginMap(Datas.DATA, this.dataMap);

        Logger.log("Loading all available data files. . .");
        this.loadPlayerData();
        Bukkit.getServer().getPluginManager().registerEvents(this.events, this.plugin);
    }

    /**
     * Shuts down this class, saves data and unegisters events
     */
    public final void shutdown() {
        Logger.log("Shutting down DataManager, Saving. . . ");
        dataMap.saveAll();
        Logger.log("Shutting down DataManager, Unregistering events. . . ");
        HandlerList.unregisterAll(this.events);
    }

    /**
     * Loads a specific Player's data into cache
     * @param player Player's Object to load
     */
    public final void loadPlayerData(final Player player) {
        this.loadPlayerData(player.getUniqueId());
    }

    /**
     * Loads a specific Player'd data into cache
     * @param playerUuid UUID of Player to load
     */
    public final void loadPlayerData(final UUID playerUuid) {
        final JsonPlayerData playerData = new JsonPlayerData(this.plugin, "/data/" + playerUuid.toString() + ".json");
        this.dataMap.loadDataFile(playerUuid.toString(), playerData);
        Logger.log("Successfully loaded data for player with UUID: &b" + playerUuid.toString());
    }

    /**
     * Creates a data file for a specific player
     * @param player Player's Object to create file with
     */
    public final void createPlayerData(final Player player){
        this.createPlayerData(player.getUniqueId());
    }

    /**
     * Creates a data file for a specific player
     * @param playerUuid Player's UUID to create file with
     */
    public final void createPlayerData(final UUID playerUuid) {
        if (this.playerExist(playerUuid)) {
            Logger.warn("&eTrying to create duplicate data file for player with UUID: &b" + playerUuid + " &eAre you calling this method via external API?");
            return;
        }

        final JsonPlayerData jsonPlayerData = new JsonPlayerData(this.plugin,
                "/data/" + playerUuid.toString() + ".json");
        Logger.log("Creating new data file for player with UUID: &b" + playerUuid.toString());
        this.dataMap.loadDataFile(playerUuid.toString(), jsonPlayerData);
    }

    /**
     * Checks if a player has a JsonDataFile saved
     * @param player Player's Object to check
     * @return true if exists
     */
    public final boolean playerExists(final Player player) {
        return this.playerExist(player.getUniqueId());
    }

    /**
     * Checks if a player has a JsonDataFile saved
     * @param playerUuid Player's UUID to check
     * @return true if exists
     */
    public final boolean playerExist(final UUID playerUuid) {
        return this.dataMap.getDataFile(playerUuid.toString()) != null;
    }

    /**
     * Loads all available Files in the /data directory
     */
    public void loadPlayerData() {

        final File dataDir = new File(this.plugin.getDataFolder(), "/data");
        final File[] dir = dataDir.listFiles();
        if (dataDir.exists()) {
            if (dataDir.mkdirs()) Logger.log("&aSuccessfully created new /data directory!");
            return;
        }

        if (dir == null) return;

        for (final File dataFile : dir) {
            if (!dataFile.getName().endsWith(".json")) {
                Logger.warn("&eFile with name: &b" + dataFile.getName() + "&e exists in data folder when it shouldn't!");
                continue;
            }

            Logger.debug("&ePath: &b" + dataFile.getPath() , this.plugin);
            Logger.debug("&eName: &b" + dataFile.getName() , this.plugin);
            final JsonPlayerData jsonPlayerData = new JsonPlayerData(this.plugin, dataFile.getPath());
            this.dataMap.loadDataFile(dataFile.getName(), jsonPlayerData);
        }
    }

    public final JsonPlayerData getPlayerData(final Player player) {
        return this.getPlayerData(player.getUniqueId());
    }

    public final JsonPlayerData getPlayerData(final UUID playerUuid) {
        return (JsonPlayerData) this.dataMap.getDataFile(playerUuid.toString());
    }

}
