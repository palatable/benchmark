package com.jnape.palatable.benchmark.timekeeper;

import com.jnape.palatable.benchmark.TimeUnit;

import static java.lang.System.currentTimeMillis;

public class MillisecondPrecisionTimeKeeper implements TimeKeeper<Long> {

    @Override
    public Long now() {
        return currentTimeMillis();
    }

    @Override
    public TimeUnit timeUnit() {
        return TimeUnit.MILLISECOND;
    }
}
