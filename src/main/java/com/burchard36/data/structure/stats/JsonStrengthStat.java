package com.burchard36.data.structure.stats;

import com.google.gson.annotations.SerializedName;

public class JsonStrengthStat {

    @SerializedName(value = "player_strength_level")
    public long strengthLevel;

    @SerializedName(value = "player_current_strength_exp")
    public long currentStrengthExp;

    @SerializedName(value = "player_allocated_strength_points")
    public long allocatedStrengthPoints;
}
