package org.jire.strukt.benchmarks

import org.jire.strukt.pointed

object BenchmarkHelper {
	
	@JvmStatic
	val points = StruktPoint::class.pointed
	
	@JvmStatic
	val pointX = points(0)
	
	@JvmStatic
	val pointY = points(0)
	
	@JvmStatic
	val heapPoint = HeapPoint()
	
	@JvmStatic
	val struktPoint = StruktPoint()
	
	@JvmStatic
	fun allocatePoint(): Long {
		val point = StruktPoint()
		return point.address
	}
	
	@JvmStatic
	fun allocateAndFreePoint(): Long {
		val point = StruktPoint()
		val address = point.address
		points.free(address)
		return address
	}
	
	@JvmStatic
	fun allocatePointHeap(): Point {
		return HeapPoint()
	}
	
	@JvmStatic
	fun readPoint(): Int {
		return struktPoint.x
	}
	
	@JvmStatic
	fun readPointHeap(): Int {
		return heapPoint.x
	}
	
	@JvmStatic
	fun writePoint(): Int {
		val write = 123
		struktPoint.x = write
		return write
	}
	
	@JvmStatic
	fun writePointHeap(): Int {
		val write = 123
		heapPoint.x = write
		return write
	}
	
}