package com.burchard36.config.configs;

import com.burchard36.json.JsonDataFile;
import org.bukkit.plugin.java.JavaPlugin;

public class DefaultConfig extends JsonDataFile {



    public DefaultConfig(JavaPlugin plugin,
                         String pathToFile) {
        super(plugin, pathToFile);
    }
}
