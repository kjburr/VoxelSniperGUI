/*
 * Copyright (c) kjburr 2015
 */

package me.kjburr.voxelsnipergui.menus;

import me.kjburr.voxelsnipergui.VoxelSniperGUI;
import me.kjburr.voxelsnipergui.config.ConfigValues;
import me.kjburr.voxelsnipergui.utils.ChatUtils;
import me.kjburr.voxelsnipergui.utils.IconMenu;
import me.kjburr.voxelsnipergui.utils.ItemUtil;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;

/**
 * Created by Ace on 4/20/2015.
 */
public class MainMenu {

    public MainMenu(final VoxelSniperGUI voxelSniperGUI) {
       IconMenu iconMenu = new IconMenu(VoxelSniperGUI.getInstance().getConfig().getString(ConfigValues.MAIN_MENU_NAME.getPath()), 18, new IconMenu.OptionClickEventHandler() {

            public void onOptionClick(IconMenu.OptionClickEvent event) {
                int position = event.getPosition();
                Player player = event.getPlayer();
                if (position == 2) {
                    TypeMenu.openMenu(player, 0);
                } else if (position == 3) {
                    VoxelSniperGUI.openMenu(player, voxelSniperGUI.getSizeMenu());
                } else if (position == 4) {
                    BrushTypeMenu.openMenu(player, 0);
                } else if (position == 6) {
                    VoxelSniperGUI.openMenu(player, voxelSniperGUI.getInkMenu());
                } else if (position == 13) {
                    Bukkit.dispatchCommand(player, "vl clear");
                } else {
                    player.sendMessage(ChatUtils.fixColor("&eNot yet available.."));
                }
                event.setWillClose(false);
                event.setWillDestroy(false);
            }
        }, VoxelSniperGUI.getInstance()).finishCreating();
        iconMenu.setOption(2, ItemUtil.createItem(Material.ARROW, 1, "&bChoose your voxel type", new String[]{"&eThe driving piece of VoxelSniper."}))
                .setOption(3, ItemUtil.createItem(Material.ARROW, 1, "&eChoose brush size (Optional)", new String[]{"&eLower numbers are safer."}))
                .setOption(4, ItemUtil.createItem(Material.ARROW, 1, "&5Choose brush type (Optional)", new String[]{"&eSelect a brush of your choice to get started."}))
                .setOption(5, ItemUtil.createItem(Material.ARROW, 1, "&6Choose brush replacer (Optional)", new String[]{"&eNot yet ready."}))
                .setOption(6, ItemUtil.createItem(Material.ARROW, 1, "&6Choose ink (Optional)", new String[]{"&ePartially done."}))
                .setOption(13, ItemUtil.createItem(Material.BUCKET, 1, "&5Clear your entire brush settings", new String[]{"&4Warning clears your settings."}));
        voxelSniperGUI.setMainMenu(iconMenu);
    }
}
