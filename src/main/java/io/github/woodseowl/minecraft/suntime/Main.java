package io.github.woodseowl.minecraft.suntime;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    @Override
    public void onEnable() {
        TimeHelpers time = new TimeHelpers();
        getLogger().info("SunTime thinks it is " + time.getRealTimeString());
    }
}
