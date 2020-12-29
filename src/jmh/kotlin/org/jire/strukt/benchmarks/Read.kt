package org.jire.strukt.benchmarks

import org.jire.strukt.IntField
import org.jire.strukt.Strukt
import org.jire.strukt.Strukts
import org.openjdk.jmh.annotations.*
import java.util.concurrent.ThreadLocalRandom
import java.util.concurrent.TimeUnit

@BenchmarkMode(Mode.Throughput)
@State(Scope.Benchmark)
@Fork(value = 1)
@Warmup(iterations = 0)
@Measurement(iterations = 1, time = 300, timeUnit = TimeUnit.MILLISECONDS)
abstract class Read<T : Strukt>(val strukts: Strukts<T>, val field: IntField<T>) {
	
	val tlr = ThreadLocalRandom.current()
	
	val address = strukts.allocate()
	
	@Benchmark
	fun read() = field(address)
	
}