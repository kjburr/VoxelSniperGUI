/*
 * Copyright (c) kjburr 2015
 */

package me.kjburr.voxelsnipergui.utils;

import me.kjburr.voxelsnipergui.VoxelSniperGUI;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

/**
 * Created by Ace on 4/20/2015.
 */
public class SoundUtil {

    public static void playSoundEffect(Player player) {
        if (VoxelSniperGUI.getInstance().hasSoundEffects()) {
            player.playSound(player.getLocation(), Sound.NOTE_PLING, 5, 1);
        }
    }
}
