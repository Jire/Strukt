package org.jire.strukt.benchmarks.pointed

import org.jire.strukt.Strukt
import org.jire.strukt.benchmarks.Point
import org.jire.strukt.benchmarks.VALUE
import org.jire.strukt.pointed

val pointedPoints = PointedPoint::class.pointed()

val pointedPointX = pointedPoints(VALUE)
val pointedPointY = pointedPoints(VALUE)

inline class PointedPoint(override val address: Long = pointedPoints()) : Point, Strukt {
	override var x: Int
		get() = pointedPointX(address)
		set(value) = pointedPointX(address, value)
	override var y: Int
		get() = pointedPointY(address)
		set(value) = pointedPointY(address, value)
}