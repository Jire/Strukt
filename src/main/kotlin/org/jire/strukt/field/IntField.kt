package org.jire.strukt.field

import net.openhft.chronicle.core.OS
import org.jire.strukt.Strukt
import kotlin.reflect.KProperty

class IntField(
	strukt: Strukt,
	prop: KProperty<*>,
	offset: Int,
	val default: Int
) : StruktField(strukt, prop, offset, 4) {
	
	operator fun set(address: Long = pointer(), value: Int) = OS.memory().writeInt(address, value)
	operator fun get(address: Long = pointer()) = OS.memory().readInt(address)
	
	operator fun getValue(thisRef: Any?, property: KProperty<*>) = get()
	operator fun setValue(thisRef: Any?, property: KProperty<*>, value: Int) = set(value = value)
	
	override fun writeDefault(address: Long) = set(address, default)
	
	override fun toString() = get().toString()
	
}