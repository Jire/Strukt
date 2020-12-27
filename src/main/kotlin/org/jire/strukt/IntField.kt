package org.jire.strukt

import net.openhft.chronicle.core.OS
import kotlin.reflect.KClass

class IntField<T : Strukt>(
	type: KClass<T>,
	val default: Int
) : StruktField<T>(4, type) {
	
	override fun writeDefault(address: Long) = OS.memory().writeInt(address, default)
	
	operator fun invoke(address: Long) = OS.memory().readInt(pointer(address))
	
	operator fun invoke(address: Long, value: Int) = OS.memory().writeInt(pointer(address), value)
	
}