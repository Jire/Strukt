package org.jire.strukt.field

import net.openhft.chronicle.core.OS
import org.jire.strukt.Strukt
import kotlin.reflect.KProperty

class FloatField(
	strukt: Strukt,
	prop: KProperty<*>,
	offset: Int,
	val default: Float
) : StruktField(strukt, prop, offset, 4) {
	
	operator fun set(address: Long = pointer(), value: Float) = OS.memory().writeFloat(address, value)
	operator fun get(address: Long = pointer()) = OS.memory().readFloat(address)
	
	operator fun getValue(thisRef: Any?, property: KProperty<*>) = get()
	operator fun setValue(thisRef: Any?, property: KProperty<*>, value: Float) = set(value = value)
	
	override fun writeDefault(address: Long) = set(address, default)
	
	override fun toString() = get().toString()
	
}