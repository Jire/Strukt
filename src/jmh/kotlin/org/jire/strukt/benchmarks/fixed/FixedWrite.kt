package org.jire.strukt.benchmarks.fixed

import org.jire.strukt.benchmarks.Write
import org.openjdk.jmh.annotations.TearDown

open class FixedWrite : Write<FixedPoint>(fixedPoints, fixedPointX) {
	
	@TearDown
	fun tearDown() {
		fixedPoints.free()
	}
	
}