package org.jire.strukt.field

import net.openhft.chronicle.core.OS
import org.jire.strukt.Strukt
import kotlin.reflect.KProperty

class DoubleField(
	strukt: Strukt,
	prop: KProperty<*>,
	offset: Int,
	val default: Double
) : StruktField(strukt, prop, offset, 8) {
	
	operator fun set(address: Long = pointer(), value: Double) = OS.memory().writeDouble(address, value)
	operator fun get(address: Long = pointer()) = OS.memory().readDouble(address)
	
	operator fun getValue(thisRef: Any?, property: KProperty<*>) = get()
	operator fun setValue(thisRef: Any?, property: KProperty<*>, value: Double) = set(value = value)
	
	override fun writeDefault(address: Long) = set(address, default)
	
	override fun toString() = get().toString()
	
}