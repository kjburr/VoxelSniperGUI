/*
 * Copyright (c) kjburr 2015
 */

package me.kjburr.voxelsnipergui.menus;

import me.kjburr.voxelsnipergui.VoxelSniperGUI;
import me.kjburr.voxelsnipergui.config.ConfigValues;
import me.kjburr.voxelsnipergui.utils.*;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/**
 * Created by Ace on 4/20/2015.
 */

public class SizeMenu {

    public SizeMenu(final VoxelSniperGUI voxelSniperGUI) {
        IconMenu iconMenu = new IconMenu(VoxelSniperGUI.getInstance().getConfig().getString(ConfigValues.SIZE_MENU_NAME.getPath()), 54, new IconMenu.OptionClickEventHandler() {

            public void onOptionClick(IconMenu.OptionClickEvent event) {
                int position = event.getPosition();
                Player player = event.getPlayer();
                if (position < 45) {
                    ItemStack itemStack = voxelSniperGUI.getSizeMenu().getInventory().getItem(position);
                    //int number = getNumber(itemStack);
                    int number = itemStack.getAmount();
                    Bukkit.dispatchCommand(player, "b " + number);
                    SoundUtil.playSoundEffect(player);
                    VoxelSniperGUI.openMenu(player, voxelSniperGUI.getMainMenu());
                } else if (position == 48) {
                    player.sendMessage(ChatUtils.fixColor("&cNo pages that way."));
                } else if (position == 49) {
                    VoxelSniperGUI.openMenu(player, voxelSniperGUI.getMainMenu());
                } else if (position == 50) {
                    player.sendMessage(ChatUtils.fixColor("&cNo pages that way."));
                }
                event.setWillClose(false);
                event.setWillDestroy(false);
            }
        }, VoxelSniperGUI.getInstance()).finishCreating();

        for (int i = 0; i < 45; i++) {
            iconMenu.setOption(i, ItemUtil.createItem(Material.SAPLING, i + 1, "&bBrush Size", new String[]{}));
        }

        GUIUtil.finishMenu(iconMenu, null);
        voxelSniperGUI.setSizeMenu(iconMenu);
    }
}
