package org.jire.strukt.pointed

import net.openhft.chronicle.core.OS
import org.jire.strukt.ByteField
import org.jire.strukt.Strukts
import kotlin.reflect.KClass

class PointedByteField(
	type: KClass<*>,
	strukts: Strukts,
	override val default: Byte
) : AbstractPointedField(type, strukts), ByteField {
	
	override fun get(address: Long) = OS.memory().readByte(pointer(address))
	
	override fun set(address: Long, value: Byte) = OS.memory().writeByte(pointer(address), value)
	
}