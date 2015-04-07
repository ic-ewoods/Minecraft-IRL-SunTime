package io.github.woodseowl.minecraft.suntime;

import org.bukkit.World;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class SunTimeTest {

    @Mock private World world;

    @InjectMocks private SunTime sunTime;

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void canGetTheRealTime() throws Exception {

        assertThat(sunTime.getRealTime(), is("14:38 EDT"));
    }
}