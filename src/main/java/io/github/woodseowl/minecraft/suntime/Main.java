package io.github.woodseowl.minecraft.suntime;

import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    @Override
    public void onEnable() {
        World world = getServer().getWorlds().get(0);
        SunTime sunTime = new SunTime(world);

        world.setGameRuleValue("doDaylightCycle", "false");
        getLogger().info("DaylightCycle status is " + world.getGameRuleValue("doDaylightCycle"));

        getLogger().info("SunTime thinks the daytime is " + sunTime.getDayTime());
        getLogger().info("SunTime thinks the real time is " + sunTime.getRealTime() + " [" + sunTime.getRealMinecraftTime() + "]");
        getLogger().info("SunTime wants to set the server time to " + sunTime.getRealDayTime());

    }
}
