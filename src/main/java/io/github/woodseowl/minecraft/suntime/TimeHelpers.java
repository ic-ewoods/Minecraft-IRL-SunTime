package io.github.woodseowl.minecraft.suntime;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeHelpers {
    Date realTime;

    public TimeHelpers() {
        realTime = new Date();
    }

    public TimeHelpers(Date realTime) {
        this.realTime = realTime;
    }

    public final Date getRealTime() {
        return realTime;
    }

    public final String getRealTimeString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm aaa z");
        return dateFormat.format(realTime);
    }
}
