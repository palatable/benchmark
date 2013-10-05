package com.jnape.palatable.benchmark;


import com.jnape.dynamiccollection.lambda.monadic.MonadicProcedure;

public abstract class Block extends MonadicProcedure {

    @Override
    public final void execute(Object o) {
        execute();
    }

    public abstract void execute();
}
