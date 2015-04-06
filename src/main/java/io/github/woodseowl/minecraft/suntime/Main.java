package io.github.woodseowl.minecraft.suntime;

import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    @Override
    public void onEnable() {
        World world = getServer().getWorlds().get(0);
        TimeHelpers time = new TimeHelpers(world);

        world.setGameRuleValue("doDaylightCycle", "false");

        getLogger().info("SunTime thinks it is " + time.getRealTimeString());

        getLogger().info("SunTime thinks the real time is " + time.getRealTimeLong());

        getLogger().info("SunTime thinks the server time is " + time.getWorldTime());

        getLogger().info("SunTime thinks the server full time is " + time.getWorldFullTime());

        getLogger().info("SunTime wants to set the server time to " + time.convertRealTimeToWorldTime(time.getRealTimeLong()));

        getLogger().info("DaylightCycle status is " + world.getGameRuleValue("doDaylightCycle"));

    }
}
