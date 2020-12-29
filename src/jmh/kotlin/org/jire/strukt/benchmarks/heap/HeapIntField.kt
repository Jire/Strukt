package org.jire.strukt.benchmarks.heap

import org.jire.strukt.AbstractField
import org.jire.strukt.IntField

class HeapIntField(
	override val default: Int,
	val heapPoint: HeapPoint,
) : AbstractField<HeapPoint>(HeapPoint::class, HeapPoints),
	IntField<HeapPoint> {
	
	override val size = 4L
	
	override fun invoke(address: Long) = heapPoint.x
	
	override fun invoke(address: Long, value: Int) {
		heapPoint.x = value
	}
	
}