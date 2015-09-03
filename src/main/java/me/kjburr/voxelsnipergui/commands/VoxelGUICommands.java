package me.kjburr.voxelsnipergui.commands;

import me.kjburr.voxelsnipergui.VoxelSniperGUI;
import me.kjburr.voxelsnipergui.utils.ChatUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Ace on 1/17/2015.
 */
public class VoxelGUICommands implements CommandExecutor {

    private VoxelSniperGUI voxelSniperGUI;

    public VoxelGUICommands(VoxelSniperGUI voxelSniperGUI) {
        this.voxelSniperGUI = voxelSniperGUI;
    }

    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            if (args.length == 0) {
                if (commandSender.isOp() || player.hasPermission("voxel.gui") || player.hasPermission("voxel.gui.admin")) {
                    VoxelSniperGUI.openMenu(player, voxelSniperGUI.getMainMenu());
                    return true;
                }
                player.sendMessage(ChatUtils.fixColor("&cNo permission."));
                return true;
            } else if (args.length >= 1) {
                if (args[0].equalsIgnoreCase("reload")) {
                    if (commandSender.isOp() || player.hasPermission("voxel.gui.admin")) {
                        voxelSniperGUI.voxelGUIReload(commandSender);
                        return true;
                    } else {
                        player.sendMessage(ChatUtils.fixColor("&cSorry you can't do that."));
                        return true;
                    }
                }
            }
            return true;
        }
        return false;
    }
}
