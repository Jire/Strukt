package org.jire.strukt

import net.openhft.chronicle.core.OS
import kotlin.reflect.KClass

class DoubleField<T : Strukt>(
	type: KClass<T>,
	val default: Double
) : StruktField<T>(8, type) {
	
	override fun writeDefault(address: Long) = OS.memory().writeDouble(address, default)
	
	operator fun invoke(address: Long) = OS.memory().readDouble(pointer(address))
	
	operator fun invoke(address: Long, value: Double) = OS.memory().writeDouble(pointer(address), value)
	
}