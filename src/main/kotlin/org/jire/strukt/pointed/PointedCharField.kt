package org.jire.strukt.pointed

import net.openhft.chronicle.core.OS
import org.jire.strukt.CharField
import org.jire.strukt.Strukts
import kotlin.reflect.KClass

class PointedCharField(
	type: KClass<*>,
	strukts: Strukts,
	override val default: Char
) : AbstractPointedField(type, strukts), CharField {
	
	override fun get(address: Long) = OS.memory().readShort(pointer(address)).toChar()
	
	override fun set(address: Long, value: Char) = OS.memory().writeShort(pointer(address), value.toShort())
	
}