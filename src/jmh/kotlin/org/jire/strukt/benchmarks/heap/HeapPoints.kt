package org.jire.strukt.benchmarks.heap

import org.jire.strukt.*
import org.jire.strukt.benchmarks.VALUE

val heapPointX = HeapPoints(VALUE)
val heapPointY = HeapPoints(VALUE)

object HeapPoints : AbstractStrukts<HeapPoint>() {
	
	override fun allocate() = HeapPoint().address
	
	override val type = HeapPoint::class
	
	override fun free(): Boolean {
		TODO("Not yet implemented")
	}
	
	override fun free(address: Long): Boolean {
		TODO("Not yet implemented")
	}
	
	override fun invoke(default: Byte): ByteField<HeapPoint> {
		TODO("Not yet implemented")
	}
	
	override fun invoke(default: Short): ShortField<HeapPoint> {
		TODO("Not yet implemented")
	}
	
	override fun invoke(default: Int) = HeapIntField(default, HeapPoint())
	
	override fun invoke(default: Long): LongField<HeapPoint> {
		TODO("Not yet implemented")
	}
	
	override fun invoke(default: Float): FloatField<HeapPoint> {
		TODO("Not yet implemented")
	}
	
	override fun invoke(default: Double): DoubleField<HeapPoint> {
		TODO("Not yet implemented")
	}
	
	override fun invoke(default: Char): CharField<HeapPoint> {
		TODO("Not yet implemented")
	}
	
	override fun invoke(default: Boolean): BooleanField<HeapPoint> {
		TODO("Not yet implemented")
	}
	
}