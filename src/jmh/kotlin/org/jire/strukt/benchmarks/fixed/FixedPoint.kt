package org.jire.strukt.benchmarks.fixed

import org.jire.strukt.Strukt
import org.jire.strukt.benchmarks.CAPACITY
import org.jire.strukt.benchmarks.Point
import org.jire.strukt.benchmarks.VALUE
import org.jire.strukt.fixed

val fixedPoints = FixedPoint::class.fixed(CAPACITY, "fixedpoints.dat")

val fixedPointX = fixedPoints(VALUE)
val fixedPointY = fixedPoints(VALUE)

inline class FixedPoint(override val address: Long = fixedPoints()) : Point, Strukt {
	override var x: Int
		get() = fixedPointX(address)
		set(value) = fixedPointX(address, value)
	override var y: Int
		get() = fixedPointY(address)
		set(value) = fixedPointY(address, value)
}