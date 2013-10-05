package com.jnape.palatable.benchmark;

public enum TimeUnit {

    MILLISECOND("ms"),
    MICROSECOND("µs"),
    NANOSECOND("ns");

    private final String label;

    private TimeUnit(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
