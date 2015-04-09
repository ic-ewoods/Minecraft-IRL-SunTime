package io.github.woodseowl.minecraft.suntime;

import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;

import static org.hamcrest.CoreMatchers.startsWith;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class RealTimeTest {

    private RealTime realTime;

    @Before
    public void setUp() throws Exception {

        Calendar testTime = Calendar.getInstance();
        testTime.set(Calendar.HOUR_OF_DAY, 15);
        testTime.set(Calendar.MINUTE, 0);

        Calendar sunrise = Calendar.getInstance();
        sunrise.set(Calendar.HOUR_OF_DAY, 6);
        sunrise.set(Calendar.MINUTE, 0);

        Calendar sunset = Calendar.getInstance();
        sunset.set(Calendar.HOUR_OF_DAY, 18);
        sunset.set(Calendar.MINUTE, 0);

        realTime = new RealTime(testTime, sunrise, sunset);
    }

    @Test
    public void testGetTime() throws Exception {
        assertThat(realTime.getTime(), startsWith("3:00 PM"));
    }

    @Test
    public void testIsDay() throws Exception {
        assertTrue(realTime.isDay());
    }

    @Test
    public void testGetDayFraction() throws Exception {
        double expectedDayFraction = 0.75;
        double dayFraction = realTime.getDayFraction();
        assertTrue(Math.abs(dayFraction - expectedDayFraction) < 0.001);
    }

    @Test
    public void testGetNightFraction() throws Exception {
        assertTrue(realTime.getNightFraction() < 0.001);
    }
}