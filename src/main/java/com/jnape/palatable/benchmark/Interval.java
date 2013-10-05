package com.jnape.palatable.benchmark;

import com.jnape.palatable.benchmark.timekeeper.TimeKeeper;

import static java.lang.String.format;

class Interval {

    private final TimeKeeper             timeKeeper;
    private final AssignableOnce<Number> startTime;
    private final AssignableOnce<Number> endTime;

    public Interval(TimeKeeper timeKeeper) {
        this.timeKeeper = timeKeeper;
        startTime = new AssignableOnce<Number>();
        endTime = new AssignableOnce<Number>();
    }

    public void captureStartTime() {
        startTime.set(timeKeeper.now());
    }

    public void captureEndTime() {
        endTime.set(timeKeeper.now());
    }

    public long getDuration() {
        Double duration = endTime.get().doubleValue() - startTime.get().doubleValue();
        return duration.longValue();
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof Interval) {
            Interval that = (Interval) other;

            boolean sameStartTime = this.startTime.equals(that.startTime);
            boolean sameEndTime = this.endTime.equals(that.endTime);

            return sameStartTime && sameEndTime;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return 31 * startTime.hashCode() + endTime.hashCode();
    }

    @Override
    public String toString() {
        return format("(%s, %s)", startTime.get(), endTime.get());
    }
}