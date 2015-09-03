package me.kjburr.voxelsnipergui;

import com.google.common.collect.Lists;
import com.thevoxelbox.voxelsniper.VoxelSniper;
import me.kjburr.voxelsnipergui.commands.VoxelGUICommands;
import me.kjburr.voxelsnipergui.config.ConfigValues;
import me.kjburr.voxelsnipergui.menus.*;
import me.kjburr.voxelsnipergui.utils.ChatUtils;
import me.kjburr.voxelsnipergui.utils.IconMenu;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Iterator;
import java.util.List;

/**
 * Created by Ace on 1/17/2015.
 */
public class VoxelSniperGUI extends JavaPlugin {

    private VoxelSniper voxelSniper;
    private static VoxelSniperGUI instance;
    private final List<IconMenu> typeMenus = Lists.newArrayList();
    private final List<IconMenu> brushTypeMenus = Lists.newArrayList();
    private IconMenu sizeMenu;
    private IconMenu inkMenu;
    private IconMenu mainMenu;

    public void onEnable() {
        instance = this;
        voxelSniper = (VoxelSniper) getServer().getPluginManager().getPlugin("VoxelSniper");
        if (voxelSniper == null) {
            getLogger().info("Could not find VoxelSniper, Disabling.");
            getPluginLoader().disablePlugin(this);
            return;
        }
        getLogger().info("Successfully found VoxelSniper, Enabling.");
        saveDefaultConfig();
        for (ConfigValues configValues : ConfigValues.values()) {
            if (getConfig().get(configValues.getPath()) == null) {
                getConfig().set(configValues.getPath(), configValues.getDefaultValue());
            }
        }
        saveConfig();
        reloadConfig();
        getCommand("voxelgui").setExecutor(new VoxelGUICommands(this));
        new BrushTypeMenu();
        new InkMenu(this);
        new MainMenu(this);
        new SizeMenu(this);
        new TypeMenu();

    }

    public void onDisable() {
        getLogger().info("Disabled.");
    }

    public void voxelGUIReload(CommandSender sender) {
        final long startTime = System.currentTimeMillis();
        reloadConfig();
        boolean wasNull = false;
        for (ConfigValues configValues : ConfigValues.values()) {
            if (getConfig().get(configValues.getPath()) == null) {
                getConfig().set(configValues.getPath(), configValues.getDefaultValue());
                wasNull = true;
            }
        }
        removeAllMenus();
        new BrushTypeMenu();
        new InkMenu(this);
        new MainMenu(this);
        new SizeMenu(this);
        new TypeMenu();
        if (wasNull) {
            saveConfig();
            reloadConfig();
        }

        final long endTime = System.currentTimeMillis();
        sender.sendMessage(ChatUtils.fixColor("&aReloaded in " + (endTime - startTime) + " ms."));
    }

    public void removeAllMenus() {
        removeMenus(typeMenus);
        removeMenus(brushTypeMenus);
        List<IconMenu> iconMenus = Lists.newArrayList();
        iconMenus.add(inkMenu);
        iconMenus.add(sizeMenu);
        iconMenus.add(mainMenu);
        removeMenus(iconMenus);
    }

    public void removeMenus(List<IconMenu> iconMenus) {
        Iterator iterator = iconMenus.iterator();
        while (iterator.hasNext()) {
            IconMenu iconMenu = (IconMenu) iterator.next();
            iconMenu.destroy();
            iterator.remove();
        }
    }

    public static void openMenu(Player player, IconMenu iconMenu) {
        iconMenu.open(player);
    }

    public static VoxelSniperGUI getInstance() {
        return instance;
    }

    public static void log(String string) {
        VoxelSniperGUI.getInstance().getLogger().info(string);
    }

    public boolean hasSoundEffects() {
        return getConfig().getBoolean("sound-effects");
    }

    public VoxelSniper getVoxelSniper() {
        return voxelSniper;
    }

    public List<IconMenu> getBrushTypeMenus() {
        return brushTypeMenus;
    }

    public List<IconMenu> getTypeMenus() {
        return typeMenus;
    }

    public IconMenu getSizeMenu() {
        return sizeMenu;
    }

    public void setSizeMenu(IconMenu sizeMenu) {
        this.sizeMenu = sizeMenu;
    }

    public IconMenu getInkMenu() {
        return inkMenu;
    }

    public void setInkMenu(IconMenu inkMenu) {
        this.inkMenu = inkMenu;
    }

    public IconMenu getMainMenu() {
        return mainMenu;
    }

    public void setMainMenu(IconMenu mainMenu) {
        this.mainMenu = mainMenu;
    }
}
