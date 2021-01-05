package org.jire.strukt.fixed

import net.openhft.chronicle.core.OS
import org.jire.strukt.BooleanField
import org.jire.strukt.Strukts
import kotlin.reflect.KClass

class FixedBooleanField(
	type: KClass<*>, strukts: Strukts,
	override val default: Boolean
) : AbstractFixedField(type, strukts), BooleanField {
	
	override fun get(address: Long) = OS.memory().readByte(pointer(address)).toInt() != 0
	
	override fun set(address: Long, value: Boolean) = OS.memory().writeByte(pointer(address), if (value) 1 else 0)
	
}