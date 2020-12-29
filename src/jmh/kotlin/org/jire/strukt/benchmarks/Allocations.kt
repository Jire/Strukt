package org.jire.strukt.benchmarks

import org.openjdk.jmh.annotations.*
import java.util.concurrent.TimeUnit

@BenchmarkMode(Mode.SingleShotTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Benchmark)
@Fork(value = 1)
@Warmup(iterations = 0)
@Measurement(iterations = 1)
open class Allocations {
	
	private fun allocHeap(): Point = HeapPoint()
	
	@Benchmark
	fun heap() {
		allocFixedStrukt() // need to allocate one
		
		for (i in 2..CAPACITY) allocHeap()
		
		fixedPoints.free() // ...so that we can free
	}
	
	private fun allocFixedStrukt() = FixedPoint().address
	
	@Benchmark
	fun fixedStrukt() {
		allocFixedStrukt()
		
		for (i in 2..CAPACITY) allocFixedStrukt()
		
		fixedPoints.free()
	}
	
	private fun allocDynamicStrukt() = PointedPoint().address
	
	@Benchmark
	fun dynamicStrukt() {
		allocFixedStrukt()
		
		for (i in 2..CAPACITY) allocDynamicStrukt()
		
		fixedPoints.free()
	}
	
}