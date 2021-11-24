package com.burchard36.gui;

import com.burchard36.Logger;
import com.burchard36.RpgPlugin;
import com.burchard36.data.Datas;
import com.burchard36.data.structure.JsonPlayerData;
import com.burchard36.data.structure.stats.JsonStrengthStat;
import com.burchard36.inventory.ClickableItem;
import com.burchard36.inventory.ItemWrapper;
import com.burchard36.inventory.PluginInventory;
import com.burchard36.json.PluginDataManager;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

import static com.burchard36.ApiLib.convert;

public class StatsGui {

    private final RpgPlugin plugin;
    private final PluginInventory inventory;

    public StatsGui(final RpgPlugin plugin) {
        this.plugin = plugin;
        this.inventory = new PluginInventory(32, "&e&lYour stats");
    }

    public final void showTo(final JsonPlayerData data) {
        final ClickableItem strengthItem = this.strengthClickableItem(data);
    }

    private ClickableItem backgroundItem() {
        final ItemWrapper wrapper = new ItemWrapper(new ItemStack(Material.BLACK_STAINED_GLASS_PANE));
        wrapper.setDisplayName("&f ");
    }

    private ClickableItem strengthClickableItem(final JsonPlayerData data) {
        final JsonStrengthStat stat = data.strengthStat;
        final long strengthLvl = stat.strengthLevel;
        final long strengthExp = stat.currentStrengthExp;
        final long allocatedStrengthPoints = stat.allocatedStrengthPoints;

        final ItemStack stack = new ItemStack(Material.RED_DYE);
        final ItemWrapper wrapper = new ItemWrapper(stack);
        wrapper.setDisplayName("&c&l&oStrength");
        final List<String> lore = new ArrayList<>();
        lore.add(" ");
        lore.add("&e&oCurrent Level &b&l" + strengthLvl);
        lore.add("&e&oStrength EXP &b&l" + strengthExp);
        lore.add("&e&oAllocated Strength Points &b&l" + allocatedStrengthPoints);
        wrapper.setItemLore(lore);
        return new ClickableItem(wrapper)
                .onClick((onClick) -> {

                });
    }

}
