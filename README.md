benchmark [![Build Status](https://travis-ci.org/palatable/benchmark.png?branch=master)](https://travis-ci.org/palatable/benchmark)
=========

Benchmarking in Java at its simplest.

installation
------------

Add the following dependency to your `pom.xml` file:

    <dependency>
        <groupId>com.jnape.palatable</groupId>
        <artifactId>benchmark</artifactId>
        <version>1.0</version>
    </dependency>

usage
-----

A `Benchmark` involves a `TimeKeeper` and `Block`s (samples).

Benchmarking printing "foo" to standard out might look like this:

```java
import com.jnape.palatable.benchmark.Benchmark;
import com.jnape.palatable.benchmark.Block;
import com.jnape.palatable.benchmark.timekeeper.MicrosecondPrecisionTimeKeeper;

class BenchmarkPrintingFoo {
    public static void main(String[] args) {
        new Benchmark(new MicrosecondPrecisionTimeKeeper())
                .sample("Printing foo", new Block() {
                    @Override
                    public void execute() {
                        System.out.println("foo");
                    }
                }).printResults();
    }
}
```

Which outputs:

```
foo
[Printing foo] took 146µs
```

Combined with Dynamic Collections, you might benchmark a factorial implementation like this:

```java
import com.jnape.dynamiccollection.lambda.dyadic.Accumulator;
import com.jnape.palatable.benchmark.Benchmark;
import com.jnape.palatable.benchmark.Block;
import com.jnape.palatable.benchmark.timekeeper.MillisecondPrecisionTimeKeeper;

import static com.jnape.dynamiccollection.lambda.dyadic.builtin.Multiply.times;
import static com.jnape.dynamiccollection.list.factory.DynamicListFactory.fromTo;
import static java.lang.String.format;

class BenchmarkFactorialWithDynamicCollections {
    private static Number factorial(Number n) {
        return fromTo(1, n).reduce(times());
    }

    public static void main(String[] args) {
        fromTo(1, 10)
                .foldLeft(
                        new Benchmark(new MillisecondPrecisionTimeKeeper()),
                        new Accumulator<Benchmark, Number>() {
                            @Override
                            public Benchmark apply(Benchmark benchmark, final Number number) {
                                return benchmark.sample(
                                        format("%s!", number),
                                        new Block() {
                                            @Override
                                            public void execute() {
                                                factorial(number);
                                            }
                                        }
                                );
                            }
                        })
                .printResults();
    }
}
```

Which outputs:

```
[1!] took 493056ns
[2!] took 44032ns
[3!] took 37888ns
[4!] took 41984ns
[5!] took 49920ns
[6!] took 61184ns
[7!] took 69120ns
[8!] took 78080ns
[9!] took 81920ns
[10!] took 93184ns
```

*Note: If you don't specify a `TimeKeeper` for an instance of `Benchmark`, it will use `MillisecondPrecisionTimeKeeper` by default.*

That's it!

license
-------

_benchmark_ is part of [palatable](http://www.github.com/palatable), which is distributed under [The MIT License](http://choosealicense.com/licenses/mit/).
