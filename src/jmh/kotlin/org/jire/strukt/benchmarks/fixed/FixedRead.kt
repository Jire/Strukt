package org.jire.strukt.benchmarks.fixed

import org.jire.strukt.benchmarks.Read
import org.openjdk.jmh.annotations.TearDown

open class FixedRead : Read<FixedPoint>(fixedPoints, fixedPointX) {
	
	@TearDown
	fun tearDown() {
		fixedPoints.free()
	}
	
}