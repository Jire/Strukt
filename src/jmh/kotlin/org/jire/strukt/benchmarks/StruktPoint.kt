package org.jire.strukt.benchmarks

import org.jire.strukt.Strukt
import org.jire.strukt.benchmarks.BenchmarkHelper.pointX
import org.jire.strukt.benchmarks.BenchmarkHelper.pointY
import org.jire.strukt.benchmarks.BenchmarkHelper.points

inline class StruktPoint(override val address: Long = points()) : Point, Strukt {
	override var x: Int
		get() = pointX(address)
		set(value) = pointX(address, value)
	override var y: Int
		get() = pointY(address)
		set(value) = pointY(address, value)
}