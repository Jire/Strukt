package org.jire.strukt.benchmarks.elastic

import org.jire.strukt.Strukt
import org.jire.strukt.benchmarks.CAPACITY
import org.jire.strukt.benchmarks.Point
import org.jire.strukt.benchmarks.VALUE
import org.jire.strukt.elastic

val elasticPoints = ElasticPoint::class.elastic(CAPACITY)

val elasticPointX = elasticPoints(VALUE)
val elasticPointY = elasticPoints(VALUE)

inline class ElasticPoint(override val address: Long = elasticPoints()) : Point, Strukt {
	override var x: Int
		get() = elasticPointX(address)
		set(value) = elasticPointX(address, value)
	override var y: Int
		get() = elasticPointY(address)
		set(value) = elasticPointY(address, value)
}