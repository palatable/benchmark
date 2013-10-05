package com.jnape.palatable.benchmark;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AssignableOnceTest {

    @Test(expected = IllegalStateException.class)
    public void preventsOverwritingValue() {
        AssignableOnce<String> assignableOnce = new AssignableOnce<String>("foo");
        assignableOnce.set("bar");
    }

    @Test(expected = IllegalStateException.class)
    public void preventsReadingUninitializedValue() {
        AssignableOnce<Integer> assignableOnce = new AssignableOnce<Integer>();
        assignableOnce.get();
    }

    @Test
    public void canHoldValueGivenAtConstructionTime() {
        AssignableOnce<String> assignableOnce = new AssignableOnce<String>("foo");
        assertEquals("foo", assignableOnce.get());
    }

    @Test
    public void canHoldValueProvidedOnlyOnceAfterConstructionTime() {
        AssignableOnce<String> assignableOnce = new AssignableOnce<String>();
        assignableOnce.set("foo");
        assertEquals("foo", assignableOnce.get());
    }
}
