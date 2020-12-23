package org.jire.strukt.field

import org.jire.strukt.Strukt
import org.jire.strukt.Strukts
import kotlin.reflect.KProperty

abstract class StruktField(
	val strukt: Strukt,
	val prop: KProperty<*>,
	val offset: Int,
	val size: Int
) {
	
	fun pointer() = strukt.cursor + offset + Strukts.SIZE_OFFSET
	
	abstract fun writeDefault(address: Long = pointer())
	
	init {
		strukt.fields.add(this@StruktField)
		strukt.offset += size
	}
	
}