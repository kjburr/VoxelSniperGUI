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

import java.util.List;

/**
 * Created by Ace on 4/20/2015.
 */
public class TypeMenu implements IMenu {

    public TypeMenu() {
        List<IconMenu> iconMenus = VoxelSniperGUI.getInstance().getTypeMenus();

        int currentSlot = 0;
        int page = 1;
        IconMenu iconMenu = GUIUtil.newPage(page, "", VoxelSniperGUI.getInstance().getTypeMenus(), this);
        for (Material material : Material.values()) {
            if (currentSlot > 44) {
                GUIUtil.finishMenu(iconMenu, iconMenus);
                currentSlot = 0;
                page++;
                iconMenu = GUIUtil.newPage(page, "", iconMenus, this);
            }
            if (material != Material.AIR && material.isBlock() && !isExcluded(material) && !material.isTransparent()) {
                //VoxelSniperGUI.log(material.toString());
                iconMenu.setOption(currentSlot, ItemUtil.createItem(material, 1, "&b" + material.toString(), new String[]{}));
                currentSlot++;
            }
        }
        GUIUtil.finishMenu(iconMenu, iconMenus);
    }

    public static IconMenu openMenu(Player player, int page) {
        List<IconMenu> iconMenus = VoxelSniperGUI.getInstance().getTypeMenus();
        if (iconMenus.size() >= page) {
            iconMenus.get(page).open(player);
            return iconMenus.get(page);
        } else {
            iconMenus.get(0).open(player);
            return iconMenus.size() > 0 ? iconMenus.get(0) : null;
        }
    }

    public IconMenu getNewMenu(final int page, String between) {
        return new IconMenu(VoxelSniperGUI.getInstance().getConfig().getString(ConfigValues.TYPE_MENU_NAME.getPath()) + " (Page " + page + ")", 54, new IconMenu.OptionClickEventHandler() {

            public void onOptionClick(IconMenu.OptionClickEvent event) {
                int position = event.getPosition();
                Player player = event.getPlayer();
                if (position < 45) {
                    IconMenu iconMenu = GUIUtil.getPage(page, VoxelSniperGUI.getInstance().getTypeMenus());
                    ItemStack itemStack = iconMenu.getInventory().getItem(position);
                    if (itemStack != null) {
                        int itemID = itemStack.getData().getItemTypeId();
                        Bukkit.dispatchCommand(player, "v " + itemID);
                        SoundUtil.playSoundEffect(player);
                        VoxelSniperGUI.openMenu(player, VoxelSniperGUI.getInstance().getMainMenu());
                    }
                } else if (position == 48) {
                    if (page - 2 < 0) {
                        player.sendMessage(ChatUtils.fixColor("&cNo pages that way."));
                    } else {
                        TypeMenu.openMenu(player, page - 2);
                    }
                } else if (position == 49) {
                    VoxelSniperGUI.openMenu(player, VoxelSniperGUI.getInstance().getMainMenu());
                } else if (position == 50) {
                    List<IconMenu> iconMenus = VoxelSniperGUI.getInstance().getTypeMenus();
                    if (page >= iconMenus.size()) {
                        player.sendMessage(ChatUtils.fixColor("&cNo pages that way."));
                    } else {
                        TypeMenu.openMenu(player, page);
                    }
                }
                event.setWillClose(false);
                event.setWillDestroy(false);
            }
        }, VoxelSniperGUI.getInstance())
                .finishCreating();
    }

    private boolean isExcluded(Material material) {
        boolean b = false;
        switch (material.getId()) {
            case 8:
            case 9:
            case 10:
            case 11:
            case 26:
            case 34:
            case 36:
            case 43:
            case 124:
            case 125:
            case 181:
                return true;
        }
        return b;
    }
}
