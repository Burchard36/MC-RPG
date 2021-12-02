package com.burchard36.config.configs.sections;

import com.google.gson.annotations.SerializedName;

public class StrengthConfig {

    @SerializedName(value = "health_per_points_spent")
    public int healthPerPoint;

    @SerializedName(value = "exp_increase_per_level_by_percent")
    public long expIncreasePerLevel; // Percentage increase

}
