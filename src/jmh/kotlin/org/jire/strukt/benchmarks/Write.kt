package org.jire.strukt.benchmarks

import org.jire.strukt.IntField
import org.jire.strukt.Strukt
import org.jire.strukt.Strukts
import org.openjdk.jmh.annotations.*
import java.util.concurrent.ThreadLocalRandom
import java.util.concurrent.TimeUnit

@BenchmarkMode(Mode.Throughput)
@State(Scope.Benchmark)
@Fork(value = 1, jvmArgsAppend = ["-Xmx64m", "-verbose:gc", "-ea"])
@Warmup(iterations = 0)
@Measurement(iterations = 1, time = 300, timeUnit = TimeUnit.MILLISECONDS)
abstract class Write<T : Strukt>(val strukts: Strukts<T>, val field: IntField<T>) {
	
	val tlr = ThreadLocalRandom.current()
	
	val address = strukts.allocate()
	
	@Benchmark
	fun write() = field(address, tlr.nextInt())
	
}