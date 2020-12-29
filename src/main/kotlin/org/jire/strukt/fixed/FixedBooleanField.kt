package org.jire.strukt.fixed

import net.openhft.chronicle.core.OS
import org.jire.strukt.BooleanField
import org.jire.strukt.Strukt
import org.jire.strukt.Strukts
import kotlin.reflect.KClass

class FixedBooleanField<T : Strukt>(
	type: KClass<T>, strukts: Strukts<T>,
	override val default: Boolean
) : AbstractFixedField<T>(type, strukts), BooleanField<T> {
	
	override fun invoke(address: Long) = OS.memory().readByte(pointer(address)).toInt() != 0
	
	override fun invoke(address: Long, value: Boolean) = OS.memory().writeByte(pointer(address), if (value) 1 else 0)
	
}