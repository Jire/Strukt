package org.jire.strukt

import net.openhft.chronicle.core.OS
import kotlin.reflect.KClass

class ShortField<T : Strukt>(
	type: KClass<T>,
	val default: Short
) : StruktField<T>(2, type) {
	
	override fun writeDefault(address: Long) = OS.memory().writeShort(address, default)
	
	operator fun invoke(address: Long) = OS.memory().readShort(pointer(address))
	
	operator fun invoke(address: Long, value: Short) = OS.memory().writeShort(pointer(address), value)
	
}