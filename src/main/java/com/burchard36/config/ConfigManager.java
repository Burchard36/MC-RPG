package com.burchard36.config;

import com.burchard36.RpgPlugin;
import com.burchard36.config.configs.DefaultConfig;
import com.burchard36.json.PluginDataMap;

public class ConfigManager {

    private final RpgPlugin plugin;
    private PluginDataMap dataMap;
    private DefaultConfig defaultConfig;

    public ConfigManager(final RpgPlugin plugin) {
        this.plugin = plugin;
        this.dataMap = this.plugin.getLib().getPluginDataManager().createNewMap();
        this.plugin.getLib().getPluginDataManager().registerPluginMap(Configs.DEFAULT, this.dataMap);

        this.defaultConfig = new DefaultConfig(this.plugin, "/config/config.json");
        this.dataMap.loadDataFile("default_config", this.defaultConfig);
    }

    public void loadConfigs() {

    }

    public void saveConfigs() {

    }

    public final DefaultConfig getDefaultConfig() {
        return this.defaultConfig;
    }

}
