package com.jnape.palatable.benchmark;

import com.jnape.palatable.benchmark.timekeeper.TimeKeeper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.PrintStream;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class BenchmarkTest {

    @Mock private TimeKeeper  timeKeeper;
    @Mock private PrintStream printStream;

    @Test
    public void printsResultsOfBlockThatExecutesInOneSecond() {
        final long startTime = 0L;
        final long endTime = 1000L;

        when(timeKeeper.now()).thenReturn(startTime);
        when(timeKeeper.timeUnit()).thenReturn(TimeUnit.NANOSECOND);
        when(printStream.format(anyString(), anyString(), anyLong(), anyString())).thenReturn(printStream);

        new Benchmark(timeKeeper, printStream)
                .sample("Just some block", new Block() {
                    @Override
                    public void execute() {
                        when(timeKeeper.now()).thenReturn(endTime);
                    }
                })
                .printResults();

        verify(printStream).format("[%s] took %d%s", "Just some block", 1000L, "ns");
        verify(printStream).println();
        verifyNoMoreInteractions(printStream);
    }

    @Test
    public void printsResultsOfEmptySampleSet() {
        new Benchmark(timeKeeper, printStream).printResults();
        verifyNoMoreInteractions(printStream);
    }
}