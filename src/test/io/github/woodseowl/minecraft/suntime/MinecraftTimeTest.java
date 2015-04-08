package io.github.woodseowl.minecraft.suntime;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class MinecraftTimeTest {

    private MinecraftTime minecraftTime;

    @Before
    public void setUp() throws Exception {
        minecraftTime = new MinecraftTime(6000L);
    }

    @Test
    public void testCalculateDayTime() throws Exception {
        assertThat(minecraftTime.calculateDayTime(0), equalTo(24000 - MinecraftTime.DUSK_LENGTH));
        assertThat(minecraftTime.calculateDayTime(1), equalTo(12000 + MinecraftTime.DUSK_LENGTH));
        assertThat(minecraftTime.calculateDayTime(0.75), equalTo(9000 + (MinecraftTime.DUSK_LENGTH / 2)));
        assertThat(minecraftTime.calculateDayTime(0.5), equalTo(6000));
        assertThat(minecraftTime.calculateDayTime(0.25), equalTo(3000 - (MinecraftTime.DUSK_LENGTH / 2)));
    }

    @Test
    public void testCalculateNightTime() throws Exception {
        assertThat(minecraftTime.calculateNightTime(0), equalTo(12000 + MinecraftTime.DUSK_LENGTH));
        assertThat(minecraftTime.calculateNightTime(1), equalTo(24000 - MinecraftTime.DUSK_LENGTH));
        assertThat(minecraftTime.calculateNightTime(0.75), equalTo(21000 - (MinecraftTime.DUSK_LENGTH / 2)));
        assertThat(minecraftTime.calculateNightTime(0.5), equalTo(18000));
        assertThat(minecraftTime.calculateNightTime(0.25), equalTo(15000 + (MinecraftTime.DUSK_LENGTH / 2)));
    }
}