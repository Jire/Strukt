package org.jire.strukt.benchmarks.heap

import org.jire.strukt.benchmarks.Point
import org.jire.strukt.benchmarks.VALUE

class HeapPoint(
	override var x: Int = VALUE,
	override var y: Int = VALUE
) : Point {
	override val address = 0L
}