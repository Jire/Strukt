package org.jire.strukt.field

import net.openhft.chronicle.core.OS
import org.jire.strukt.Strukt
import kotlin.reflect.KProperty

class CharField(
	strukt: Strukt,
	prop: KProperty<*>,
	offset: Int,
	val default: Char
) : StruktField(strukt, prop, offset, 2) {
	
	operator fun set(address: Long = pointer(), value: Char) = OS.memory().writeShort(address, value.toShort())
	operator fun get(address: Long = pointer()) = OS.memory().readShort(address).toChar()
	
	operator fun getValue(thisRef: Any?, property: KProperty<*>) = get()
	operator fun setValue(thisRef: Any?, property: KProperty<*>, value: Char) = set(value = value)
	
	override fun writeDefault(address: Long) = set(address, default)
	
	override fun toString() = get().toString()
	
}