package io.github.woodseowl.minecraft.suntime;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

import java.util.logging.Logger;

public class Main extends JavaPlugin {

    private SunTime sunTime;
    private Weather weather;

    @Override
    public void onEnable() {
        Logger logger = getLogger();
        World world = getServer().getWorlds().get(0);
        BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
        sunTime = SunTime.getInstance(world);

        world.setGameRuleValue("doDaylightCycle", "false");
        logger.info("DaylightCycle game rule is " + world.getGameRuleValue("doDaylightCycle"));

        logger.info("SunTime thinks the daytime is " + sunTime.getDayTime());
        logger.info("SunTime thinks the real time is " + sunTime.getRealTime());
        logger.info(sunTime.getSunriseTime());
        logger.info(sunTime.getSunsetTime());
        logger.info("SunTime will set the server time to " + sunTime.getRealDayTime());

        world.setTime(sunTime.getRealDayTimeLong());

        scheduler.scheduleSyncRepeatingTask(this, new Clock(world, logger), 0L, 60 * 20L);

        logger.info("Weather gonna try...");
        weather = new Weather();
        logger.info("Weather for " + weather.getLocation() + ": " + weather.getCurrentConditions());

        scheduler.scheduleSyncRepeatingTask(this, new WeatherStation(world, logger, weather), 0L, 250 * 60 * 20L);

    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("realweather")) {
            sender.sendMessage("The weather IRL for " + weather.getLocation() + "...");
            sender.sendMessage("[Weather] " + weather.getCurrentConditions());
            return true;
        }
        if (cmd.getName().equalsIgnoreCase("realtime")) {
            sender.sendMessage("The time IRL is " + sunTime.getRealTime());
            return true;
        }
        if (cmd.getName().equalsIgnoreCase("sunrise")) {
            sender.sendMessage(sunTime.getSunriseTime());
            return true;
        }
        if (cmd.getName().equalsIgnoreCase("sunset")) {
            sender.sendMessage(sunTime.getSunsetTime());
            return true;
        }
        return false;
    }
}
