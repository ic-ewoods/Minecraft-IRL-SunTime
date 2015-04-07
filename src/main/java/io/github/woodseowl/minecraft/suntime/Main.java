package io.github.woodseowl.minecraft.suntime;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

import java.util.logging.Logger;

public class Main extends JavaPlugin {
    @Override
    public void onEnable() {
        World world = getServer().getWorlds().get(0);
        SunTime sunTime = new SunTime(world);
        Logger logger = getLogger();

        world.setGameRuleValue("doDaylightCycle", "false");
        logger.info("DaylightCycle status is " + world.getGameRuleValue("doDaylightCycle"));

        logger.info("SunTime thinks the daytime is " + sunTime.getDayTime());
        logger.info("SunTime thinks the real time is " + sunTime.getRealTime());
        logger.info("Sunrise: " + sunTime.getSunriseTime());
        logger.info("Sunset: " + sunTime.getSunsetTime());
        logger.info("SunTime wants to set the server time to " + sunTime.getRealDayTime());

        // world.setTime(sunTime.getRealDayTimeLong());

        BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
        scheduler.scheduleSyncRepeatingTask(this, new Clock(world, logger), 0L, 60 * 20L);

    }
}
