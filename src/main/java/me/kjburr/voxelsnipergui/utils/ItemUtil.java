package me.kjburr.voxelsnipergui.utils;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Ace on 12/4/2014.
 */
public class ItemUtil {

    public static ItemStack createItem(Material material, String displayName) {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatUtils.fixColor(displayName));
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack createItem(Material material, String displayName, String[] array) {
        ItemStack item = createItem(material, displayName);
        ItemMeta meta = item.getItemMeta();
        String[] lore = new String[array.length];
        for (int i = 0; i < array.length; i++) {
            lore[i] = ChatUtils.fixColor(array[i]);
        }
        meta.setLore(Arrays.asList(lore));
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack createItem(Material material, int amount, String displayName, String[] array) {
        ItemStack item = new ItemStack(material, amount);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatUtils.fixColor(displayName));
        String[] lore = new String[array.length];
        for (int i = 0; i < array.length; i++) {
            lore[i] = ChatUtils.fixColor(array[i]);
        }
        meta.setLore(Arrays.asList(lore));
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack createItem(Material material, int amount, String displayName, String array) {
        ItemStack item = new ItemStack(material, amount);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatUtils.fixColor(displayName));
        List<String> lore = loreParser(array);
        if(lore.size() >= 15) {
            lore.subList(15, lore.size()).clear();
        }
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }

    //color specific
    public static List<String> loreParser(String string) {
        List<String> list = Lists.newArrayList();
        for (String substring : Splitter.fixedLength(36).split(string)) {
            list.add(ChatUtils.fixColor("&d" +substring));
        }
        return list;
    }
}
