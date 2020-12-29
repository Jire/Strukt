package org.jire.strukt.benchmarks

import org.openjdk.jmh.annotations.*
import java.util.concurrent.ThreadLocalRandom
import java.util.concurrent.TimeUnit

@BenchmarkMode(Mode.Throughput)
@State(Scope.Benchmark)
@Fork(value = 1)
@Warmup(iterations = 0)
@Measurement(iterations = 1, time = 300, timeUnit = TimeUnit.MILLISECONDS)
open class Read {
	
	val tlr = ThreadLocalRandom.current()
	
	val heapPoint = HeapPoint()
	
	@Benchmark
	fun heap() = heapPoint.x
	
	val fixedPoint = FixedPoint()
	
	@Benchmark
	fun fixed() = fixedPoint.x
	
	val dynamicPoint = PointedPoint()
	
	@Benchmark
	fun dynamic() = dynamicPoint.x
	
	@Setup
	fun setup() {
		heapPoint.x = tlr.nextInt()
		fixedPoint.x = tlr.nextInt()
		dynamicPoint.x = tlr.nextInt()
	}
	
	@TearDown
	fun tearDown() {
		fixedPoints.free()
	}
	
}