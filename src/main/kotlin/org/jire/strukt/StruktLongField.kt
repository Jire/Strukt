@file:JvmName("StruktFieldKt")

package org.jire.strukt

import net.openhft.chronicle.core.OS
import kotlin.reflect.KClass

class StruktLongField<T : Strukt>(
	type: KClass<T>,
	val default: Long
) : StruktField<T>(8, type) {
	
	override fun writeDefault(address: Long) = OS.memory().writeLong(address, default)
	
	operator fun invoke(address: Long) = OS.memory().readLong(pointer(address))
	
	operator fun invoke(address: Long, value: Long) = OS.memory().writeLong(pointer(address), value)
	
}