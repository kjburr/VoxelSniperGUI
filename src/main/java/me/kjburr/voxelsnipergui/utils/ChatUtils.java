package me.kjburr.voxelsnipergui.utils;

import org.bukkit.ChatColor;

/**
 * Created by Ace on 9/2/2014.
 */
public class ChatUtils {
    public static String fixColor(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }
}
