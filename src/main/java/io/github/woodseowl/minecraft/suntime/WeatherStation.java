package io.github.woodseowl.minecraft.suntime;

import org.bukkit.World;

import java.util.logging.Logger;

public class WeatherStation implements Runnable {

    private final World world;
    private final Logger logger;
    private final Weather weather;

    public WeatherStation(World world, Logger logger, Weather weather) {
        this.world = world;
        this.logger = logger;
        this.weather = weather;
    }

    public void run() {
        String currentWeather = weather.getWeather();
        if (currentWeather.contains("Rain") || currentWeather.contains("Snow") || currentWeather.contains("Showers")) {
            world.setStorm(true);
            world.setWeatherDuration(500);
            logger.info("Storm underway");
        } else {
            if (world.hasStorm()) {
                world.setStorm(false);
                logger.info("Storm clearing");
            }
        }
        if (currentWeather.contains("Thunder")) {
            world.setThundering(true);
            world.setThunderDuration(500);
            logger.info("Thundering");
        } else {
            if (world.isThundering()) {
                world.setThundering(false);
                logger.info("Thunder passing");
            }
        }
    }
}
