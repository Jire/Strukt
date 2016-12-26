package org.jire.strukt.members

import org.jire.strukt.Strukt
import kotlin.reflect.KProperty

class EnumMember<T : Enum<T>>(override val strukt: Strukt, override val size: Long, val default: T) : StruktMember() {
	
	operator fun getValue(thisRef: Any?, property: KProperty<*>): T
			= default.declaringClass.enumConstants[strukt.heap.readInt(pointer())]
	
	operator fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
		strukt.heap.writeInt(pointer(), value.ordinal)
	}
	
	override fun writeDefaultReference() {
		offset = strukt.heapPointer
		strukt.heap.writeInt(pointer(), default.ordinal)
		strukt.heapPointer += size
	}
	
}

fun <T : Enum<T>> Strukt.enum(defaultValue: T) = EnumMember(this, 4, defaultValue).apply { writeDefaultReference() }