package org.jire.strukt.fixed

import net.openhft.chronicle.core.OS
import org.jire.strukt.DoubleField
import org.jire.strukt.Strukts
import kotlin.reflect.KClass

class FixedDoubleField(
	type: KClass<*>, strukts: Strukts,
	override val default: Double
) : AbstractFixedField(type, strukts), DoubleField {
	
	override fun get(address: Long) = OS.memory().readDouble(pointer(address))
	
	override fun set(address: Long, value: Double) = OS.memory().writeDouble(pointer(address), value)
	
}