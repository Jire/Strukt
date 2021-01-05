package org.jire.strukt.fixed

import net.openhft.chronicle.core.OS
import org.jire.strukt.ByteField
import org.jire.strukt.Strukts
import kotlin.reflect.KClass

class FixedByteField(
	type: KClass<*>, strukts: Strukts,
	override val default: Byte
) : AbstractFixedField(type, strukts), ByteField {
	
	override fun get(address: Long) = OS.memory().readByte(pointer(address))
	
	override fun set(address: Long, value: Byte) = OS.memory().writeByte(pointer(address), value)
	
}