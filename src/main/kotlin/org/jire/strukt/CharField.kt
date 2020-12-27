package org.jire.strukt

import net.openhft.chronicle.core.OS
import kotlin.reflect.KClass

class CharField<T : Strukt>(
	type: KClass<T>,
	val default: Char
) : StruktField<T>(2, type) {
	
	override fun writeDefault(address: Long) = OS.memory().writeShort(address, default.toShort())
	
	operator fun invoke(address: Long) = OS.memory().readShort(pointer(address)).toChar()
	
	operator fun invoke(address: Long, value: Char) = OS.memory().writeShort(pointer(address), value.toShort())
	
}