package io.github.woodseowl.minecraft.suntime;

import org.bukkit.World;

import java.util.logging.Logger;

public final class Clock implements Runnable {

    private final World world;
    private final Logger logger;

    public Clock(World world, Logger logger) {
        this.world = world;
        this.logger = logger;
    }

    public void run() {
        SunTime sunTime = SunTime.getInstance(world);

        world.setTime(sunTime.getRealDayTimeLong());
        logger.info("SunTime set the server time to " + sunTime.getRealDayTime());
    }
}
