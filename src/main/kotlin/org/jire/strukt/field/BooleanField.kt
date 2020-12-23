package org.jire.strukt.field

import net.openhft.chronicle.core.OS
import org.jire.strukt.Strukt
import kotlin.reflect.KProperty

class BooleanField(
	strukt: Strukt,
	prop: KProperty<*>,
	offset: Int,
	val default: Boolean
) : StruktField(strukt, prop, offset, 1) {
	
	operator fun set(address: Long = pointer(), value: Boolean) = OS.memory().writeByte(address, if (value) 1 else 0)
	operator fun get(address: Long = pointer()) = OS.memory().readByte(address).toInt() != 0
	
	operator fun getValue(thisRef: Any?, property: KProperty<*>) = get()
	operator fun setValue(thisRef: Any?, property: KProperty<*>, value: Boolean) = set(value = value)
	
	override fun writeDefault(address: Long) = set(address, default)
	
	override fun toString() = get().toString()
	
}