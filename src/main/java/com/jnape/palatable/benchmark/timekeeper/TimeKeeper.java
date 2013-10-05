package com.jnape.palatable.benchmark.timekeeper;

import com.jnape.palatable.benchmark.TimeUnit;

public interface TimeKeeper<CurrentTime extends Number> {

    CurrentTime now();

    TimeUnit timeUnit();
}
