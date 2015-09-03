/*
 * Copyright (c) kjburr 2015
 */

package me.kjburr.voxelsnipergui.utils;

import me.kjburr.voxelsnipergui.menus.IMenu;
import org.bukkit.Material;

import java.util.List;

/**
 * Created by Ace on 4/20/2015.
 */
public class GUIUtil {

    public static void finishMenu(IconMenu iconMenu, List<IconMenu> iconMenuList) {
        iconMenu.setOption(48, ItemUtil.createItem(Material.ARROW, 1, "&bPrevious Page", new String[]{}));
        iconMenu.setOption(49, ItemUtil.createItem(Material.REDSTONE_BLOCK, 1, "&4Exit", new String[]{}));
        iconMenu.setOption(50, ItemUtil.createItem(Material.ARROW, 1, "&bNext Page", new String[]{}));
        if(iconMenuList != null) {
            iconMenuList.add(iconMenu);
        }
    }


    public static IconMenu newPage(int page, String between, List<IconMenu> iconMenus, IMenu iMenu) {
        IconMenu iconMenu = getPage(page, iconMenus);
        return iconMenu != null ? iconMenu : iMenu.getNewMenu(page, between);
    }

    public static IconMenu getPage(int page, List<IconMenu> iconMenus) {
        if (iconMenus != null) {
            if (iconMenus.size() > page - 1) {
                return iconMenus.get(page - 1);
            }
        }
        return null;
    }
}
