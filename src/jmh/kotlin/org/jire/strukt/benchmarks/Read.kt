package org.jire.strukt.benchmarks

import org.openjdk.jmh.annotations.*
import java.util.concurrent.TimeUnit

@BenchmarkMode(Mode.Throughput)
@State(Scope.Benchmark)
@Fork(value = 1)
@Warmup(iterations = 0)
@Measurement(iterations = 1, time = 1000, timeUnit = TimeUnit.MILLISECONDS)
open class Read {
	
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
	}
	
	@TearDown
	fun tearDown() {
		fixedPoints.free()
	}
	
}