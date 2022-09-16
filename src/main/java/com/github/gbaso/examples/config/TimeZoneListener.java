package com.github.gbaso.examples.config;

import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.TimeZone;

import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.context.ApplicationListener;

public class TimeZoneListener implements ApplicationListener<ApplicationEnvironmentPreparedEvent> {

    @Override
    public void onApplicationEvent(ApplicationEnvironmentPreparedEvent event) {
        ZoneId utc = ZoneId.ofOffset("", ZoneOffset.UTC);
        TimeZone.setDefault(TimeZone.getTimeZone(utc));
    }

}
