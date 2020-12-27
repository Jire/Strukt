package org.jire.strukt.benchmarks

import org.jire.strukt.invoke

object BenchmarkHelper {
	
	@JvmStatic
	val pointX = StruktPoint::class(0)
	
	@JvmStatic
	val pointY = StruktPoint::class(0)
	
	@JvmStatic
	val heapPoint = HeapPoint()
	
	val struktPoint = StruktPoint()
	
	@JvmStatic
	fun allocatePoint(): Long {
		val point = StruktPoint()
		//point.free()
		return point.address
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
		BenchmarkHelper.heapPoint.x = write
		return write
	}
	
	@JvmStatic
	fun main(args: Array<String>) {
		allocatePoint()
		writePoint()
		readPoint()
	}
	
}