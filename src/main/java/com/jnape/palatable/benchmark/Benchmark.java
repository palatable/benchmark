package com.jnape.palatable.benchmark;

import com.jnape.dynamiccollection.datatype.tuple.Tuple2;
import com.jnape.dynamiccollection.list.DynamicArrayList;
import com.jnape.dynamiccollection.list.DynamicList;
import com.jnape.palatable.benchmark.timekeeper.MillisecondPrecisionTimeKeeper;
import com.jnape.palatable.benchmark.timekeeper.TimeKeeper;

import java.io.PrintStream;

import static com.jnape.dynamiccollection.datatype.tuple.TupleFactory.tuple;

public class Benchmark {

    private final TimeKeeper                            timeKeeper;
    private final PrintStream                           printStream;
    private final DynamicList<Tuple2<String, Interval>> samples;

    public Benchmark() {
        this(new MillisecondPrecisionTimeKeeper());
    }

    public Benchmark(TimeKeeper timeKeeper) {
        this(timeKeeper, System.out);
    }

    public Benchmark(TimeKeeper timeKeeper, PrintStream printStream) {
        this.timeKeeper = timeKeeper;
        this.printStream = printStream;
        samples = new DynamicArrayList<Tuple2<String, Interval>>();
    }

    public Benchmark sample(String label, Block block) {
        Interval interval = new Interval(timeKeeper);
        interval.captureStartTime();
        block.execute();
        interval.captureEndTime();

        samples.add(tuple(label, interval));
        return this;
    }

    public void printResults() {
        TimeUnit timeUnit = timeKeeper.timeUnit();

        for (Tuple2<String, Interval> sample : samples) {
            String label = sample._1;
            Interval interval = sample._2;

            printStream.format(
                    "[%s] took %d%s",
                    label,
                    interval.getDuration(),
                    timeUnit.getLabel()
            ).println();
        }
    }
}
