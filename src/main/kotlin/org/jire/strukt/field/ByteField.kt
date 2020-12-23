package org.jire.strukt.field

import net.openhft.chronicle.core.OS
import org.jire.strukt.Strukt
import kotlin.reflect.KProperty

class ByteField(
	strukt: Strukt,
	prop: KProperty<*>,
	offset: Int,
	val default: Byte
) : StruktField(strukt, prop, offset, 1) {
	
	operator fun set(address: Long = pointer(), value: Byte) = OS.memory().writeByte(address, value)
	operator fun get(address: Long = pointer()) = OS.memory().readByte(address)
	
	operator fun getValue(thisRef: Any?, property: KProperty<*>) = get()
	operator fun setValue(thisRef: Any?, property: KProperty<*>, value: Byte) = set(value = value)
	
	override fun writeDefault(address: Long) = set(address, default)
	
	override fun toString() = get().toString()
	
}