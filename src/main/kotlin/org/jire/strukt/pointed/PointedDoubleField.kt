package org.jire.strukt.pointed

import net.openhft.chronicle.core.OS
import org.jire.strukt.DoubleField
import org.jire.strukt.Strukts
import kotlin.reflect.KClass

class PointedDoubleField(
	type: KClass<*>,
	strukts: Strukts,
	override val default: Double
) : AbstractPointedField(type, strukts), DoubleField {
	
	override fun get(address: Long) = OS.memory().readDouble(pointer(address))
	
	override fun set(address: Long, value: Double) = OS.memory().writeDouble(pointer(address), value)
	
}