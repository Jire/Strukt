package org.jire.strukt

import net.openhft.chronicle.core.OS
import kotlin.reflect.KClass

class BooleanField<T : Strukt>(
	type: KClass<T>,
	val default: Boolean
) : StruktField<T>(1, type) {
	
	override fun writeDefault(address: Long) = OS.memory().writeByte(address, if (default) 1 else 0)
	
	operator fun invoke(address: Long) = OS.memory().readByte(pointer(address)).toInt() != 0
	
	operator fun invoke(address: Long, value: Boolean) = OS.memory().writeByte(pointer(address), if (value) 1 else 0)
	
}