package org.jire.strukt.field

import net.openhft.chronicle.core.OS
import org.jire.strukt.Strukt
import kotlin.reflect.KProperty

class LongField(
	strukt: Strukt,
	prop: KProperty<*>,
	offset: Int,
	val default: Long
) : StruktField(strukt, prop, offset, 8) {
	
	operator fun set(address: Long = pointer(), value: Long) = OS.memory().writeLong(address, value)
	operator fun get(address: Long = pointer()) = OS.memory().readLong(address)
	
	operator fun getValue(thisRef: Any?, property: KProperty<*>) = get()
	operator fun setValue(thisRef: Any?, property: KProperty<*>, value: Long) = set(value = value)
	
	override fun writeDefault(address: Long) = set(address, default)
	
	override fun toString() = get().toString()
	
}