package com.burchard36.commands;

import com.burchard36.Logger;
import com.burchard36.RpgPlugin;
import com.burchard36.command.ApiCommand;
import com.burchard36.data.Datas;
import com.burchard36.data.structure.JsonPlayerData;
import com.burchard36.gui.StatsGui;
import com.burchard36.json.PluginDataManager;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;

import java.util.ArrayList;

import static com.burchard36.ApiLib.convert;

public class StatsCommand {

    private final RpgPlugin plugin;
    private final ApiCommand command;

    public StatsCommand(final RpgPlugin plugin) {
        this.plugin = plugin;
        this.command = new ApiCommand("stats",
                "Shows your stats",
                "/stats",
                new ArrayList<>())
                .onPlayerSender((playerSent) -> {
                    final Player player = playerSent.getSendingPlayer();

                    final PluginDataManager manager = this.plugin.getLib().getPluginDataManager();
                    final JsonPlayerData data = (JsonPlayerData) manager.getDataMap(Datas.DATA).getDataFile(player.getUniqueId().toString());

                    if (data == null) {
                        Logger.error("&cJsonPlayerData was null when player with name: &b" + player.getName() + "&c tried to open a StatsGui");
                        player.sendMessage(Component.text(convert("&cAn internal error occurred, please contact a developer or administrator")));
                        return;
                    }

                    final StatsGui gui = new StatsGui(this.plugin);
                    gui.showTo(data);
                });
    }

    public final ApiCommand getCommand() {
        return this.command;
    }

}
