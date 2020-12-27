package org.jire.strukt

import net.openhft.chronicle.core.OS
import kotlin.reflect.KClass

class FloatField<T : Strukt>(
	type: KClass<T>,
	val default: Float
) : StruktField<T>(4, type) {
	
	override fun writeDefault(address: Long) = OS.memory().writeFloat(address, default)
	
	operator fun invoke(address: Long) = OS.memory().readFloat(pointer(address))
	
	operator fun invoke(address: Long, value: Float) = OS.memory().writeFloat(pointer(address), value)
	
}

operator fun <T : Strukt> KClass<T>.invoke(default: Float = 0F) = FloatField(this, default)