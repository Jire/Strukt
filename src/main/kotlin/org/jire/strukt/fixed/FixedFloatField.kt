package org.jire.strukt.fixed

import net.openhft.chronicle.core.OS
import org.jire.strukt.FloatField
import org.jire.strukt.Strukts
import kotlin.reflect.KClass

class FixedFloatField(
	type: KClass<*>, strukts: Strukts,
	override val default: Float
) : AbstractFixedField(type, strukts), FloatField {
	
	override fun get(address: Long) = OS.memory().readFloat(pointer(address))
	
	override fun set(address: Long, value: Float) = OS.memory().writeFloat(pointer(address), value)
	
}