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
		allocFixed() // need to allocate one
		
		for (i in 2..CAPACITY) allocHeap()
		
		fixedPoints.free() // ...so that we can free
	}
	
	private fun allocFixed() = FixedPoint().address
	
	@Benchmark
	fun fixed() {
		allocFixed()
		
		for (i in 2..CAPACITY) allocFixed()
		
		fixedPoints.free()
	}
	
	private fun allocDynamic() = PointedPoint().address
	
	@Benchmark
	fun dynamic() {
		allocFixed()
		
		for (i in 2..CAPACITY) allocDynamic()
		
		fixedPoints.free()
	}
	
}