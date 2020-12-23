package org.jire.strukt.field

import net.openhft.chronicle.core.OS
import org.jire.strukt.Strukt
import kotlin.reflect.KProperty

class ShortField(
	strukt: Strukt,
	prop: KProperty<*>,
	offset: Int,
	val default: Short
) : StruktField(strukt, prop, offset, 2) {
	
	operator fun set(address: Long = pointer(), value: Short) = OS.memory().writeShort(address, value)
	operator fun get(address: Long = pointer()) = OS.memory().readShort(address)
	
	operator fun getValue(thisRef: Any?, property: KProperty<*>) = get()
	operator fun setValue(thisRef: Any?, property: KProperty<*>, value: Short) = set(value = value)
	
	override fun writeDefault(address: Long) = set(address, default)
	
	override fun toString() = get().toString()
	
}