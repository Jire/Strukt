package org.jire.strukt.benchmarks

import org.jire.strukt.Strukt
import org.jire.strukt.dynamic

val pointedPoints = FixedPoint::class.dynamic()

val pointedPointX = pointedPoints(0)
val pointedPointY = pointedPoints(0)

inline class PointedPoint(override val address: Long = pointedPoints()) : Point, Strukt {
	override var x: Int
		get() = pointedPointX(address)
		set(value) = pointedPointX(address, value)
	override var y: Int
		get() = pointedPointY(address)
		set(value) = pointedPointY(address, value)
}