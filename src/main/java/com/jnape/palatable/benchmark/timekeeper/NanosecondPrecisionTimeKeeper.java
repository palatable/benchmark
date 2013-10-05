package com.jnape.palatable.benchmark.timekeeper;

import com.jnape.palatable.benchmark.TimeUnit;

import static java.lang.System.nanoTime;

@SuppressWarnings("UnusedDeclaration")
public class NanosecondPrecisionTimeKeeper implements TimeKeeper<Long> {

    @Override
    public Long now() {
        return nanoTime();
    }

    @Override
    public TimeUnit timeUnit() {
        return TimeUnit.NANOSECOND;
    }
}
