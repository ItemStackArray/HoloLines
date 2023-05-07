package net.itemstackarray.hololines;

import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * @Class HoloLinesPlugin
 * @Date 07/05/2023
 * @Time 19:19
 * @Author ItemStackArray
 */

/**
 * HoloLines main class
 */

public final class HoloLinesPlugin extends JavaPlugin {

    // Set the instance
    @Getter
    private static HoloLinesPlugin INSTANCE;

    @Override
    public void onEnable() {
        // Initialize the instance
        INSTANCE = this;
    }
}
