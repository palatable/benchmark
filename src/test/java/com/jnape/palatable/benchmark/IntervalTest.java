package com.jnape.palatable.benchmark;

import com.jnape.palatable.benchmark.timekeeper.TimeKeeper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class IntervalTest {

    @Mock private TimeKeeper timeKeeper;

    @Test
    public void computesDuration() {
        when(timeKeeper.now())
                .thenReturn(0L)
                .thenReturn(100L);

        Interval interval = new Interval(timeKeeper);
        interval.captureStartTime();
        interval.captureEndTime();

        assertEquals(100, interval.getDuration());
    }
}
