package org.jire.strukt.pointed

import net.openhft.chronicle.core.OS
import org.jire.strukt.ByteField
import org.jire.strukt.Strukt
import org.jire.strukt.Strukts
import kotlin.reflect.KClass

class PointedByteField<T : Strukt>(
	type: KClass<T>,
	strukts: Strukts<T>,
	override val default: Byte
) : AbstractPointedField<T>(type, strukts), ByteField<T> {
	
	override fun get(address: Long) = OS.memory().readByte(pointer(address))
	
	override fun set(address: Long, value: Byte) = OS.memory().writeByte(pointer(address), value)
	
}