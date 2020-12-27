package org.jire.strukt

import net.openhft.chronicle.core.OS
import kotlin.reflect.KClass

class ByteField<T : Strukt>(
	type: KClass<T>,
	val default: Byte
) : StruktField<T>(1, type) {
	
	override fun writeDefault(address: Long) = OS.memory().writeByte(address, default)
	
	operator fun invoke(address: Long) = OS.memory().readByte(pointer(address))
	
	operator fun invoke(address: Long, value: Byte) = OS.memory().writeByte(pointer(address), value)
	
}

operator fun <T : Strukt> KClass<T>.invoke(default: Byte = 0) = ByteField(this, default)