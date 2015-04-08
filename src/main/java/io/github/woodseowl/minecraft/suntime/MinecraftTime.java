package io.github.woodseowl.minecraft.suntime;

public class MinecraftTime {
    public static final int DAY_LENGTH = 24000;
    public static final int SUNRISE = 0;
    public static final int SUNSET = 12000;
    public static final int DUSK_LENGTH = 750;
    public static final int TWILIGHT_LENGTH = 650;

    private final Long worldTime;

    public MinecraftTime(Long worldTime) {
        this.worldTime = worldTime;
    }

    public String getDayTime() {
        return worldTime.toString();
    }

    public int calculateDayTime(double dayFraction) {
        int dayTime = (int) Math.round(getLengthOfDay() * dayFraction) + (SUNRISE - DUSK_LENGTH);
        if (dayTime < 0) {
            dayTime = DAY_LENGTH + dayTime;
        }
        return dayTime;
    }

    public int calculateNightTime(double nightFraction) {
        return (int) Math.round(getLengthOfNight() * nightFraction) + (SUNSET + DUSK_LENGTH);
    }

    private Integer getLengthOfDay() {
        return SUNSET - SUNRISE + (DUSK_LENGTH * 2);
    }

    private long getLengthOfNight() {
        return (DAY_LENGTH + SUNRISE) - SUNSET - (DUSK_LENGTH * 2);
    }
}
