package org.jire.strukt.pointed

import net.openhft.chronicle.core.OS
import org.jire.strukt.CharField
import org.jire.strukt.Strukt
import org.jire.strukt.Strukts
import kotlin.reflect.KClass

class PointedCharField<T : Strukt>(
	type: KClass<T>,
	allocator: Strukts<T>,
	val default: Char
) : AbstractPointedStruktField<T>(2, type, allocator), CharField<T> {
	
	override fun writeDefault(address: Long) = OS.memory().writeShort(address, default.toShort())
	
	override fun invoke(address: Long) = OS.memory().readShort(pointer(address)).toChar()
	
	override fun invoke(address: Long, value: Char) = OS.memory().writeShort(pointer(address), value.toShort())
	
}