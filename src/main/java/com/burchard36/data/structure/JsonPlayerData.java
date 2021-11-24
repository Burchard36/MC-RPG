package com.burchard36.data.structure;

import com.burchard36.data.structure.stats.JsonStrengthStat;
import com.burchard36.json.JsonDataFile;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.UUID;


public class JsonPlayerData extends JsonDataFile {

    @SerializedName(value = "player_uuid")
    public String playerUuid;

    @SerializedName(value = "player_level")
    public long level;

    @SerializedName(value = "player_current_exp")
    public long currentExp;

    @SerializedName(value = "player_current_talent_points")
    public long currentTalentPoints;

    @SerializedName(value = "player_total_talent_points_earned")
    public long totalTalentPointsEarned;

    @SerializedName(value = "player_total_talent_points_spent")
    public long totalTalentPointsSpent;

    @Expose
    @SerializedName(value = "player_strength_stat")
    public JsonStrengthStat strengthStat;

    public JsonPlayerData(JavaPlugin plugin, String pathToFile) {
        super(plugin, pathToFile);

        this.playerUuid = null;
        this.level = 1;
        this.currentExp = 0;
        this.currentTalentPoints = 0;
        this.totalTalentPointsEarned = 0;
        this.totalTalentPointsSpent = 0;
        this.strengthStat = new JsonStrengthStat();
    }

    public final UUID getPlayerUUid() {
        return UUID.fromString(this.playerUuid);
    }

    public final Player getPlayer() {
        return Bukkit.getPlayer(this.getPlayerUUid());
    }
}
