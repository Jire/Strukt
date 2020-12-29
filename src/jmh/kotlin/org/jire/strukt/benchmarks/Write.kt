package org.jire.strukt.benchmarks

import org.openjdk.jmh.annotations.*
import java.util.concurrent.TimeUnit

@BenchmarkMode(Mode.Throughput)
@State(Scope.Benchmark)
@Fork(value = 1)
@Warmup(iterations = 0)
@Measurement(iterations = 1, time = 200, timeUnit = TimeUnit.MILLISECONDS)
open class Write {
	
	val heapPoint = HeapPoint()
	
	@Benchmark
	fun heap() {
		heapPoint.x = 123
	}
	
	val fixedPoint = FixedPoint()
	
	@Benchmark
	fun fixed() {
		fixedPoint.x = 123
	}
	
	val dynamicPoint = PointedPoint()
	
	@Benchmark
	fun dynamic() {
		dynamicPoint.x = 123
	}
	
	@TearDown
	fun tearDown() {
		fixedPoints.free()
	}
	
}