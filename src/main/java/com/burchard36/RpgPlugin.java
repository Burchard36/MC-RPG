package com.burchard36;

import com.burchard36.commands.StatsCommand;
import com.burchard36.config.ConfigManager;
import com.burchard36.config.configs.DefaultConfig;
import com.burchard36.data.DataManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class RpgPlugin extends JavaPlugin implements Api {

    private ApiLib lib;
    public static RpgPlugin INSTANCE;
    private ConfigManager configManager;
    private DataManager dataManager;

    @Override
    public void onEnable() {
        INSTANCE = this;
        this.lib = new ApiLib().initializeApi(this);

        this.configManager = new ConfigManager(this);
        this.dataManager = new DataManager(this);

        ApiLib.registerCommand(new StatsCommand(this).getCommand());
    }

    @Override
    public void onDisable() {
        this.dataManager.shutdown();

        this.dataManager = null;
    }

    public ApiLib getLib() {
        return this.lib;
    }

    public final DataManager getDataManager() {
        return this.dataManager;
    }

    public DefaultConfig getJsonConfig() {
        return this.configManager.getDefaultConfig();
    }

    @Override
    public boolean isDebug() {
        return true;
    }
}
