/*
 * Copyright (c) kjburr 2015
 */

package me.kjburr.voxelsnipergui.config;

/**
 * Created by Ace on 4/20/2015.
 */
public enum ConfigValues {

    MAIN_MENU_NAME("main-menu-name", "VoxelGUI Main Menu"),
    BRUSHTYPE_MENU_NAME("brushtype-menu-name", "Brush Type"),
    TYPE_MENU_NAME("type-menu-name", "Type"),
    INK_MENU_NAME("ink-menu-name", "VoxelGUI Ink Menu"),
    SIZE_MENU_NAME("size-menu-name", "VoxelGUI Size Menu"),
    HAS_SOUND("sound-effects", true);

    private String path;
    private Object defaultValue;

    ConfigValues(String path, Object defaultValue) {
        this.path = path;
        this.defaultValue = defaultValue;
    }

    public Object getDefaultValue() {
        return defaultValue;
    }

    public String getPath() {
        return path;
    }
}
