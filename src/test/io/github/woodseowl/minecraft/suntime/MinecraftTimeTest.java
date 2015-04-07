package io.github.woodseowl.minecraft.suntime;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class MinecraftTimeTest {

    private MinecraftTime minecraftTime;

    @Before
    public void setUp() throws Exception {
        minecraftTime = new MinecraftTime(9000L);
    }

    @Test
    public void testCalculateDayTime() throws Exception {
        assertThat(minecraftTime.calculateDayTime(0.25), equalTo(3000));
    }

    @Test
    public void testCalculateNightTime() throws Exception {
        assertThat(minecraftTime.calculateNightTime(0.25), equalTo(15000));
    }
}