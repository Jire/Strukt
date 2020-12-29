package org.jire.strukt.benchmarks

import org.jire.strukt.Strukts
import org.openjdk.jmh.annotations.*
import java.util.concurrent.TimeUnit

@BenchmarkMode(Mode.SingleShotTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Benchmark)
@Fork(value = 1)
@Warmup(iterations = 0)
@Measurement(iterations = 1)
abstract class Allocate(val strukts: Strukts<*>) {
	
	@Benchmark
	fun allocate() {
		for (i in 2..CAPACITY) strukts.allocate()
	}
	
}