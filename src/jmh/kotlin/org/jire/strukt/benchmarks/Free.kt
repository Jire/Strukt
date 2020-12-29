package org.jire.strukt.benchmarks

import it.unimi.dsi.fastutil.longs.LongArrayList
import it.unimi.dsi.fastutil.longs.LongList
import org.openjdk.jmh.annotations.*
import java.util.concurrent.TimeUnit

@BenchmarkMode(Mode.SingleShotTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Benchmark)
@Fork(value = 1)
@Warmup(iterations = 0)
@Measurement(iterations = 1)
open class Free {
	
	private val dynamicAddresses: LongList = LongArrayList(CAPACITY.toInt())
	
	@Setup
	fun setup() {
		for (i in 1..CAPACITY) {
			dynamicAddresses.add(PointedPoint().address)
		}
	}
	
	@Benchmark
	fun dynamic() {
		val it = dynamicAddresses.listIterator()
		while (it.hasNext()) {
			val address = it.nextLong()
			pointedPoints.free(address)
		}
	}
	
}