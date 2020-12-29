package org.jire.strukt.pointed

import net.openhft.chronicle.core.OS
import org.jire.strukt.CharField
import org.jire.strukt.Strukt
import org.jire.strukt.Strukts
import kotlin.reflect.KClass

class PointedCharField<T : Strukt>(
	type: KClass<T>,
	strukts: Strukts<T>,
	override val default: Char
) : AbstractPointedField<T>(type, strukts), CharField<T> {
	
	override fun get(address: Long) = OS.memory().readShort(pointer(address)).toChar()
	
	override fun set(address: Long, value: Char) = OS.memory().writeShort(pointer(address), value.toShort())
	
}