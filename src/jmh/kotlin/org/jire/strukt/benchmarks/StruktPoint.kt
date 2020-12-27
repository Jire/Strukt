package org.jire.strukt.benchmarks

import org.jire.strukt.Strukt
import org.jire.strukt.benchmarks.BenchmarkHelper.pointX
import org.jire.strukt.benchmarks.BenchmarkHelper.pointY
import org.jire.strukt.new

inline class StruktPoint(override val address: Long) : Point, Strukt {
	constructor() : this(new<StruktPoint>())
	
	override var x: Int
		get() = pointX(address)
		set(value) = pointX(address, value)
	override var y: Int
		get() = pointY(address)
		set(value) = pointY(address, value)
}

fun main() {
	BenchmarkHelper.pointX
	BenchmarkHelper.pointY
	BenchmarkHelper.struktPoint
	val c = StruktPoint()
	
	while (true) {
		val p = StruktPoint()
		p.x
		p.y
		p.x = 123
		p.x
		p.y = 50
		p.y
	}
}